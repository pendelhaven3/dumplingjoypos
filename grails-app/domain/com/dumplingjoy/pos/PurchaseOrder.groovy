package com.dumplingjoy.pos

class PurchaseOrder {

	Supplier supplier
	
	List<PurchaseOrderItem> items
	
    static constraints = {
    }
	
	static hasMany = [items: PurchaseOrderItem]
	
}
