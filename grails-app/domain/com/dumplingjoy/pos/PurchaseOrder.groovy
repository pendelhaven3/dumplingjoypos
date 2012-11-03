package com.dumplingjoy.pos

import org.apache.commons.lang.StringUtils;

class PurchaseOrder {

	transient springSecurityService
	
	Integer purchaseOrderNumber
	Supplier supplier
	boolean ordered
	boolean posted
	String createdBy
	Date orderDate
	Date postDate
	String postedBy
	PaymentTerms terms
	ReceivingReceipt relatedReceivingReceipt
	String remarks
	String referenceNumber
	
	List<PurchaseOrderItem> items
	
    static constraints = {
		purchaseOrderNumber unique: true, min: 0
		orderDate nullable: true
		postDate nullable: true
		postedBy nullable: true
		createdBy nullable: true
		relatedReceivingReceipt nullable: true
		remarks nullable: true, blank: true, maxSize: 500
		referenceNumber nullable: true
    }
	
	static hasMany = [items: PurchaseOrderItem]

	static transients = ["totalAmount", "totalQuantity", "status"]
	
	public boolean containsItem(PurchaseOrderItem item) {
		return items.find {it.id != item.id && it.product.id == item.product?.id && it.unit == item.unit} != null
	}

	public BigDecimal getTotalAmount() {
		BigDecimal total = BigDecimal.ZERO
		items.each {
			total = total.add(it.amount)
		}
		total
	}
	
	public boolean post() {
		PurchaseOrder.withTransaction { status ->
			if (StringUtils.isEmpty(referenceNumber)) {
				errors.reject("default.blank.message", ["Reference Number"] as Object[], "default.blank.message")
			}
			if (hasErrors()) {
				status.setRollbackOnly()
				return false
			}
		
			ReceivingReceipt receivingReceipt = new ReceivingReceipt()
			receivingReceipt.receivingReceiptNumber = ReceivingReceiptSequenceNumber.getNextValue()
			ReceivingReceiptSequenceNumber.increment()
			receivingReceipt.supplier = supplier
			receivingReceipt.receivedDate = new Date()
			receivingReceipt.receivedBy = getCurrentUsername()
			receivingReceipt.orderDate = orderDate
			receivingReceipt.terms = terms
			receivingReceipt.relatedPurchaseOrder = this
			receivingReceipt.save(failOnError: true)
			
			posted = true
			postedBy = getCurrentUsername()
			postDate = new Date()
			relatedReceivingReceipt = receivingReceipt
			save(failOnError: true)
			
			items.each { PurchaseOrderItem item ->
				if (item.actualQuantity && item.actualQuantity > 0) {
					ReceivingReceiptItem receivingReceiptItem = new ReceivingReceiptItem()
					receivingReceiptItem.product = item.product
					receivingReceiptItem.unit = item.unit
					receivingReceiptItem.quantity = item.actualQuantity
					receivingReceiptItem.cost = item.cost
					receivingReceipt.addToItems(receivingReceiptItem)
					receivingReceiptItem.save(failOnError: true)
				}
			}

			return true
		}
	}

	def beforeInsert() {
		createdBy = getCurrentUsername()
	}

	public int getTotalQuantity() {
		int totalQuantity = 0
		items.each {
			totalQuantity += it.quantity
		}
		totalQuantity
	}
	
	public boolean markAsOrdered() {
		PurchaseOrder.withTransaction { status ->
			ordered = true
			orderDate = new Date()
			save(failOnError: true)
			return true
		}
	}
	
	private String getCurrentUsername() {
		if (springSecurityService.currentUser) {
			((User)springSecurityService.currentUser).username
		} else {
			"SYSTEM"
		}
	}
	
	public String getStatus() {
		if (posted) {
			"Posted"
		} else if (ordered) {
			"Ordered"
		} else {
			"New"
		}
	}

}