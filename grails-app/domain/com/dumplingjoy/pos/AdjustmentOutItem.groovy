package com.dumplingjoy.pos

class AdjustmentOutItem {

	Product product
	Unit unit
	Integer quantity
	
    static constraints = {
		product nullable: false
		unit nullable: false
		quantity nullable: false, min: 1
    }
	
}
