package com.dumplingjoy.pos

class StockQuantityConversionItem {

	Product product
	Unit fromUnit
	Unit toUnit
	Integer quantity
	
    static constraints = {
		quantity min: 1
    }

	public int getConvertedQuantity() {
		return product.unitConversions.find {it.fromUnit == fromUnit && it.toUnit == toUnit}.convertedQuantity * quantity
	}
		
}
