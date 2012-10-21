package com.dumplingjoy.pos

class ProductUnitCost {

	Product product
	Unit unit
	BigDecimal grossCost = BigDecimal.ZERO
	BigDecimal finalCost = BigDecimal.ZERO
	
    static constraints = {
		grossCost min: BigDecimal.ZERO
		finalCost min: BigDecimal.ZERO
    }
	
}
