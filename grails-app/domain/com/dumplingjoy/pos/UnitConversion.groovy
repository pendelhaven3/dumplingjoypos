package com.dumplingjoy.pos

class UnitConversion {

	Unit fromUnit
	Unit toUnit
	Integer convertedQuantity
	
    static constraints = {
		fromUnit nullable: false
		toUnit nullable: false
		convertedQuantity nullable: false, min: 0
    }
	
	static belongsTo = [product: Product]
	
}
