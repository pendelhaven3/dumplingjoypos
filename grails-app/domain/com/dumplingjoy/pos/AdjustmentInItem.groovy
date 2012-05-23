package com.dumplingjoy.pos

class AdjustmentInItem {

	Product product
	Unit unit
	Integer quantity
	
    static constraints = {
		product nullable: false
		unit nullable: false
		quantity nullable: false, min: 1
    }
	
}