package com.dumplingjoy.pos

import java.util.Date;
import java.util.List;

class SalesInvoice {

	Integer salesInvoiceNumber
	Customer customer
	PricingScheme pricingScheme
	String orderType
	List<SalesInvoiceItem> items
	Date postDate
	String postedBy
	String encodedBy

    static constraints = {
		salesInvoiceNumber unique: true
		orderType blank: false, inList: ["Delivery", "Walk-in"]
		postedBy blank: false
		encodedBy blank: false
    }
	
	static hasMany = [items: SalesInvoiceItem]

	static transients = ["totalAmount", "salesInvoiceNo", "totalQuantity", "totalDiscountedAmount", "totalNetAmount"]
	
	public BigDecimal getTotalAmount() {
		BigDecimal totalAmount = BigDecimal.ZERO
		items.each {
			totalAmount = totalAmount.add(it.amount)
		}
		totalAmount
	}
	
	public Integer getSalesInvoiceNo() {
		salesInvoiceNumber
	}
	
	public int getTotalQuantity() {
		int totalQuantity = 0
		items.each {
			totalQuantity += it.quantity
		}
		totalQuantity
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
