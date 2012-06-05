package com.dumplingjoy.pos

import java.util.Date;


class SalesRequisition {

	transient springSecurityService
	
	Integer salesRequisitionNumber
	Customer customer
	PricingScheme pricingScheme
	String orderType
	List<SalesRequisitionItem> items
	boolean posted
	Date postDate
	String postedBy
	String createdBy

    static constraints = {
		salesRequisitionNumber unique: true
		orderType blank: false, inList: ["Delivery", "Walk-in"]
		postDate nullable: true
		postedBy nullable: true
		createdBy nullable: true
    }
	
	static hasMany = [items: SalesRequisitionItem]

	static transients = ["totalAmount", "totalDiscountedAmount", "totalNetAmount"]
	
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
			salesInvoice.orderType = orderType
			salesInvoice.postDate = postDate
			salesInvoice.postedBy = postedBy
			salesInvoice.encodedBy = createdBy
			
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
		total
	}

}