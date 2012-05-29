package com.dumplingjoy.pos

class StockQuantityConversionItem {

	Product product
	Unit fromUnit
	Unit toUnit
	Integer quantity
	
    static constraints = {
		quantity min: 1, validator: validateEnoughQuantityAvailable
    }

	public int getConvertedQuantity() {
		return product.unitConversions.find {it.fromUnit == fromUnit && it.toUnit == toUnit}.convertedQuantity * quantity
	}

	private static def validateEnoughQuantityAvailable = { Integer quantity, StockQuantityConversionItem item ->
		if (item.product && item.quantity > 0 && item.fromUnit) {
			UnitQuantity available = item.product.unitQuantities.find {it.unit == item.fromUnit}
			if (quantity > available.quantity) {
				return "notenoughquantityavailable.message"
			} else {
				return true
			}
		}
	}

}
