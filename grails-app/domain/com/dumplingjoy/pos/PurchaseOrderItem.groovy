package com.dumplingjoy.pos

class PurchaseOrderItem {

	Product product
	Unit unit
	Integer quantity
	BigDecimal cost
	
    static constraints = {
		quantity min: 0
    }
	
}
