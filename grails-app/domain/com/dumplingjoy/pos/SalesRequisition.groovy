package com.dumplingjoy.pos

import java.util.Date;


class SalesRequisition {

	Integer salesRequisitionNumber
	Customer customer
	PricingScheme pricingScheme
	List<SalesRequisitionItem> items
	boolean posted
	Date postDate
	String postedBy

    static constraints = {
		salesRequisitionNumber unique: true
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

}
