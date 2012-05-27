package com.dumplingjoy.pos

class AdjustmentOutItem {

	Product product
	Unit unit
	Integer quantity
	boolean hasPostError
	
    static constraints = {
		product nullable: false
		unit nullable: false
		quantity nullable: false, min: 1, validator: validateEnoughQuantityAvailable
    }
	
	static transients = ["hasPostError"]
	
	private static def validateEnoughQuantityAvailable = { Integer quantity, AdjustmentOutItem item ->
		if (item.product && item.quantity > 0 && item.unit) {
			UnitQuantity available = item.product.unitQuantities.find {it.unit == item.unit}
			if (quantity > available.quantity) {
				return "notenoughquantityavailable.message"
			} else {
				return true
			}
		}
	}
	
}
