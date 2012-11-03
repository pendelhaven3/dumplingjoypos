package com.dumplingjoy.pos

class AdjustmentInItem {

	Product product
	Unit unit
	Integer quantity
	
    static constraints = {
		quantity min: 1
    }
	
	static belongsTo = [adjustmentIn : AdjustmentIn]
	
}