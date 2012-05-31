package com.dumplingjoy.pos

class PricingScheme {

	String description
	List<ProductUnitPrice> unitPrices
	
    static constraints = {
		description nullable: false, blank: false, unique: true
    }
	
	static hasMany = [unitPrices: ProductUnitPrice]
	
	def beforeInsert() {
		createUnitPrices()
	}
	
	private void createUnitPrices() {
		Product.list().each { Product product ->
			product.units.each { Unit unit ->
				addToUnitPrices(new ProductUnitPrice(product: product, unit: unit))
			}
		}
	}
	
}