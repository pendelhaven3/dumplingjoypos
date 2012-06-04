package com.dumplingjoy.pos

import java.util.Date;


class SalesRequisition {

	transient springSecurityService
	
	Integer salesRequisitionNumber
	Customer customer
	PricingScheme pricingScheme
	String deliveryType
	List<SalesRequisitionItem> items
	boolean posted
	Date postDate
	String postedBy

    static constraints = {
		salesRequisitionNumber unique: true
		deliveryType blank: false, inList: ["Delivery", "Walk-in"]
		postDate nullable: true
		postedBy nullable: true
    }
	
	static hasMany = [items: SalesRequisitionItem]

	static transients = ["totalAmount"]
	
	BigDecimal getTotalAmount() {
		BigDecimal totalAmount = BigDecimal.ZERO
		items.each {
			totalAmount = totalAmount.add(it.amount)
		}
		totalAmount
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
			salesInvoice.deliveryType = deliveryType
			salesInvoice.postDate = postDate
			salesInvoice.postedBy = postedBy
			
			items.each { SalesRequisitionItem item ->
				SalesInvoiceItem salesInvoiceItem = new SalesInvoiceItem()
				salesInvoiceItem.product = item.product
				salesInvoiceItem.unit = item.unit
				salesInvoiceItem.quantity = item.quantity
				salesInvoiceItem.unitPrice = item.getUnitPrice()
				salesInvoice.addToItems(salesInvoiceItem)
			}
			
			salesInvoice.save(failOnError: true)
			
			return true
		}
	}

}
