package com.dumplingjoy.pos

class PurchaseOrder {

	Integer purchaseOrderNumber
	Supplier supplier
	
	List<PurchaseOrderItem> items
	
    static constraints = {
		purchaseOrderNumber unique: true, min: 0
    }
	
	static hasMany = [items: PurchaseOrderItem]

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

}