package com.dumplingjoy.pos

import java.math.RoundingMode
import java.util.Date;


class SalesRequisition {

	transient springSecurityService
	
	Integer salesRequisitionNumber
	Customer customer
	PricingScheme pricingScheme
	String mode
	List<SalesRequisitionItem> items
	boolean posted
	Date postDate
	String postedBy
	String createdBy
	Long salesInvoiceId
	Date dateCreated
	String remarks
	PaymentTerms paymentTerms

    static constraints = {
		salesRequisitionNumber unique: true
		mode blank: false, inList: ["Delivery", "Pick-up"]
		postDate nullable: true
		postedBy nullable: true
		createdBy nullable: true
		salesInvoiceId nullable: true
		dateCreated nullable: true
		remarks nullable: true, blank: true
		paymentTerms nullable: true
    }
	
	static hasMany = [items: SalesRequisitionItem]

	static transients = ["totalAmount", "totalDiscountedAmount", "totalNetAmount", "totalQuantity"]
	
	public BigDecimal getTotalAmount() {
		BigDecimal total = BigDecimal.ZERO
		items.each {
			total = total.add(it.amount)
		}
		total
	}

	public boolean containsItem(SalesRequisitionItem item) {
		return items.find {it.id != item.id && it.product.id == item.product?.id && it.unit == item.unit} != null
	}

	public boolean post() {
		SalesRequisition.withTransaction { status ->
			if (items.isEmpty()) {
				errors.reject("postSalesRequisition.noItems.message", [] as Object[], "postSalesRequisition.noItems.message")
			} else {
				items.each { SalesRequisitionItem item ->
					UnitQuantity unitQuantity = item.product.unitQuantities.find {it.unit == item.unit}
					unitQuantity.quantity -= item.quantity
					if (unitQuantity.quantity < 0) {
						errors.reject("postItem.notEnoughQuantityAvailable.message", [item.product.code, item.unit] as Object[],
							"postItem.notEnoughQuantityAvailable.message")
						item.hasPostError = true
					} else {
						unitQuantity.save(failOnError: true)
					}
					
					ProductUnitPrice unitPrice = ProductUnitPrice.find("from ProductUnitPrice up where up.pricingScheme = ? and up.product = ? and up.unit = ?",
						[pricingScheme, item.product, item.unit])
					if (unitPrice.hasNoPrice()) {
						errors.reject("postItem.noSellingPrice.message", [item.product.code, item.unit] as Object[],
							"postItem.noSellingPrice.message")
						item.hasPostError = true
					} else if (unitPrice.isLessThanCost()) {
						errors.reject("postItem.priceLessThanCost.message", [item.product.code, item.unit] as Object[],
							"postItem.priceLessThanCost.message")
						item.hasPostError = true
					}
				}
			}
			if (hasErrors()) {
				status.setRollbackOnly()
				return false
			}
			posted = true
			postDate = new Date()
			if (springSecurityService.currentUser) {
				postedBy = ((User)springSecurityService.currentUser).username
			}
			save(failOnError: true, deepValidate: false)
			
			SalesInvoice salesInvoice = new SalesInvoice()
			salesInvoice.salesInvoiceNumber = SalesInvoiceSequenceNumber.getNextValue()
			SalesInvoiceSequenceNumber.increment()
			salesInvoice.customer = customer
			salesInvoice.pricingScheme = pricingScheme
			salesInvoice.mode = mode
			salesInvoice.postDate = postDate
			salesInvoice.postedBy = postedBy
			salesInvoice.encodedBy = createdBy
			salesInvoice.paymentTerms = paymentTerms
			salesInvoice.remarks = remarks
			
			items.each { SalesRequisitionItem item ->
				SalesInvoiceItem salesInvoiceItem = new SalesInvoiceItem()
				salesInvoiceItem.product = item.product
				salesInvoiceItem.unit = item.unit
				salesInvoiceItem.quantity = item.quantity
				salesInvoiceItem.unitPrice = item.getUnitPrice()
				salesInvoiceItem.discount1 = item.discount1
				salesInvoiceItem.discount2 = item.discount2
				salesInvoiceItem.discount3 = item.discount3
				salesInvoiceItem.flatRateDiscount = item.flatRateDiscount
				salesInvoice.addToItems(salesInvoiceItem)
			}
			
			salesInvoice.save(failOnError: true)
			salesInvoiceId = salesInvoice.id
			return true
		}
	}
	
	def beforeInsert() {
		if (springSecurityService.currentUser) {
			createdBy = ((User)springSecurityService.currentUser).username
		}
	}
	
	public BigDecimal getTotalDiscountedAmount() {
		BigDecimal total = BigDecimal.ZERO
		items.each {
			total = total.add(it.discountedAmount)
		}
		total
	}

	public BigDecimal getTotalNetAmount() {
		BigDecimal total = BigDecimal.ZERO
		items.each {
			total = total.add(it.netAmount)
		}
		total.setScale(0, RoundingMode.CEILING)
	}

	public int getTotalQuantity() {
		int totalQuantity = 0
		items.each {
			totalQuantity += it.quantity
		}
		totalQuantity
	}

}