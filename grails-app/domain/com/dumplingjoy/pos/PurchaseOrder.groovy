package com.dumplingjoy.pos

class PurchaseOrder {

	List<PurchaseOrderItem> items
	
    static constraints = {
    }
	
	static hasMany = [items: PurchaseOrderItem]
	
}
