package com.dumplingjoy.pos

class PurchaseOrder {

	Integer purchaseOrderNumber
	Supplier supplier
	boolean ordered
	boolean posted
	
	List<PurchaseOrderItem> items
	
    static constraints = {
		purchaseOrderNumber unique: true, min: 0
    }
	
	static hasMany = [items: PurchaseOrderItem]

	static transients = ["totalAmount", "originalTotalAmount"]
	
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
	
	public BigDecimal getOriginalTotalAmount() {
		BigDecimal total = BigDecimal.ZERO
		items.each {
			total = total.add(it.originalAmount)
		}
		total
	}

}