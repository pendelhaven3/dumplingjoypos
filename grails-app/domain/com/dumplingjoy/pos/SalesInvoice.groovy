package com.dumplingjoy.pos

import java.math.RoundingMode
import java.util.Date;
import java.util.List;

class SalesInvoice {

	Integer salesInvoiceNumber
	Customer customer
	PricingScheme pricingScheme
	String mode
	List<SalesInvoiceItem> items
	Date postDate
	String postedBy
	String encodedBy
	String remarks
	PaymentTerms paymentTerms

    static constraints = {
		salesInvoiceNumber unique: true
		mode blank: false, inList: ["Delivery", "Pick-up"]
		postedBy blank: false
		encodedBy blank: false
		remarks nullable: true, blank: true
		paymentTerms nullable: true
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
		total.setScale(0, RoundingMode.CEILING)
	}

}
