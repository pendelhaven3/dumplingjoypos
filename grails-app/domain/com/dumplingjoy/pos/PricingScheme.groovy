package com.dumplingjoy.pos

class PricingScheme {

	String description
	List<ProductUnitPrice> unitPrices
	
    static constraints = {
		description nullable: false, blank: false
    }
	
	static hasMany = [unitPrices: ProductUnitPrice]
	
}