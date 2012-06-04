package com.dumplingjoy.pos

import java.util.Date;
import java.util.List;

class SalesInvoice {

	Integer salesInvoiceNumber
	Customer customer
	PricingScheme pricingScheme
	String deliveryType
	List<SalesInvoiceItem> items
	Date postDate
	String postedBy

    static constraints = {
		salesInvoiceNumber unique: true
		deliveryType blank: false, inList: ["Delivery", "Walk-in"]
		postedBy blank: false
    }
	
	static hasMany = [items: SalesInvoiceItem]

	static transients = ["totalAmount", "salesInvoiceNo"]
	
	BigDecimal getTotalAmount() {
		BigDecimal totalAmount = BigDecimal.ZERO
		items.each {
			totalAmount = totalAmount.add(it.amount)
		}
		totalAmount
	}
	
	public Integer getSalesInvoiceNo() {
		salesInvoiceNumber
	}

}
