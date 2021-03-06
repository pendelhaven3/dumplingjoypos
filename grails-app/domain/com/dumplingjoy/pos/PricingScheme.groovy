package com.dumplingjoy.pos

class PricingScheme {

	private static final String CANVASSER = "Canvasser"
	
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
	
	public static PricingScheme getCanvasserPricingScheme() {
		PricingScheme.findByDescription(CANVASSER)
	}
	
	public List<ProductUnitPrice> getProductUnitPrices(Product product) {
		ProductUnitPrice.findAll("from ProductUnitPrice up where up.pricingScheme = ? and up.product = ?", [this, product])
	}
	
}