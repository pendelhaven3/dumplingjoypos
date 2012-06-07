package com.dumplingjoy.pos

import java.util.List;

class ReceivingReceipt {

	Integer receivingReceiptNumber
	Supplier supplier
	Date receivedDate
	String receivedBy
	boolean posted
	
	List<ReceivingReceiptItem> items
	
    static constraints = {
		receivingReceiptNumber unique: true, min: 0
    }
	
	static hasMany = [items: ReceivingReceiptItem]

	static transients = ["totalAmount", "totalQuantity", "totalDiscountedAmount", "totalNetAmount"]
	
	public BigDecimal getTotalAmount() {
		BigDecimal total = BigDecimal.ZERO
		items.each {
			total = total.add(it.amount)
		}
		total
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