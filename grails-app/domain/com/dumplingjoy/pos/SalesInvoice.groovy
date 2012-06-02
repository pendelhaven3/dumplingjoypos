package com.dumplingjoy.pos

import java.util.Date;
import java.util.List;

class SalesInvoice {

	Integer salesInvoiceNumber
	Customer customer
	PricingScheme pricingScheme
	List<SalesInvoiceItem> items
	Date postDate
	String postedBy

    static constraints = {
		salesInvoiceNumber unique: true
    }
	
	static hasMany = [items: SalesInvoiceItem]

	static transients = ["totalAmount"]
	
	BigDecimal getTotalAmount() {
		BigDecimal totalAmount = BigDecimal.ZERO
		items.each {
			totalAmount = totalAmount.add(it.amount)
		}
		totalAmount
	}

}
