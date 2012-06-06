package com.dumplingjoy.pos

class PurchaseOrder {

	Integer purchaseOrderNumber
	Supplier supplier
	boolean ordered
	boolean posted
	Long receivingReceiptId
	
	List<PurchaseOrderItem> items
	
    static constraints = {
		purchaseOrderNumber unique: true, min: 0
		receivingReceiptId nullable: true
    }
	
	static hasMany = [items: PurchaseOrderItem]

	static transients = ["totalAmount"]
	
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
			ReceivingReceipt receivingReceipt = new ReceivingReceipt()
			receivingReceipt.receivingReceiptNumber = ReceivingReceiptSequenceNumber.getNextValue()
			ReceivingReceiptSequenceNumber.increment()
			receivingReceipt.supplier = supplier
			receivingReceipt.receivedDate = new Date()
			
			items.each { PurchaseOrderItem item ->
				if (item.actualQuantity && item.actualQuantity > 0) {
					ReceivingReceiptItem receivingReceiptItem = new ReceivingReceiptItem()
					receivingReceiptItem.product = item.product
					receivingReceiptItem.unit = item.unit
					receivingReceiptItem.quantity = item.actualQuantity
					receivingReceiptItem.cost = item.cost
					receivingReceipt.addToItems(receivingReceiptItem)
				} 
			}
			posted = true
			save(failOnError: true)
			receivingReceipt.save(failOnError: true)
			receivingReceiptId = receivingReceipt.id
			return true
		}
	}

}