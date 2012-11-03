package com.dumplingjoy.pos

class StockQuantityConversionItem {

	Product product
	Unit fromUnit
	Unit toUnit
	Integer quantity
	boolean hasPostError
	
    static constraints = {
		fromUnit validator: validateFromAndToUnits
		quantity min: 1, validator: validateEnoughQuantityAvailable
    }
	
	static transients = ["hasPostError"]

	static belongsTo = [stockQuantityConversion : StockQuantityConversion]
	
	public Integer getConvertedQuantity() {
		if (fromUnit == null || toUnit == null || quantity == null) {
			return null
		} else {
			UnitConversion unitConversion = product.unitConversions.find {it.fromUnit == fromUnit && it.toUnit == toUnit}
			if (unitConversion) {
				return unitConversion.convertedQuantity * quantity
			}  else {
				return null
			}
		}
	}

	private static def validateEnoughQuantityAvailable = { Integer quantity, StockQuantityConversionItem item ->
		if (item.product && item.quantity > 0 && item.fromUnit) {
			UnitQuantity available = item.product.unitQuantities.find {it.unit == item.fromUnit}
			if (quantity > available.quantity) {
				return "notEnoughQuantityAvailable.message"
			} else {
				return true
			}
		}
	}

	private static def validateFromAndToUnits = { Unit fromUnit, StockQuantityConversionItem item ->
		if (item.product && item.fromUnit && item.toUnit) {
			UnitConversion unitConversion = item.product.unitConversions.find {it.fromUnit == item.fromUnit && it.toUnit == item.toUnit}
			if (!unitConversion) {
				item.errors.rejectValue("fromUnit", "noUnitConversionDefined.message", 
					[item.fromUnit, item.toUnit, item.product.code] as Object[], "noUnitConversionDefined.message")
			}
		}
	}

}
