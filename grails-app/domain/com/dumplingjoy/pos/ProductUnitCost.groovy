package com.dumplingjoy.pos

class ProductUnitCost {

	Product product
	Unit unit
	BigDecimal cost = BigDecimal.ZERO
	
    static constraints = {
		cost min: BigDecimal.ZERO
    }
	
}
