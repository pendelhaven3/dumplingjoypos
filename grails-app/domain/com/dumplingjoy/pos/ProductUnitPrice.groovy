package com.dumplingjoy.pos

class ProductUnitPrice {

	Product product
	Unit unit
	BigDecimal price = BigDecimal.ZERO
	
    static constraints = {
		price min: BigDecimal.ZERO
    }
	
	static belongsTo = [pricingScheme: PricingScheme]
	
}