package com.dumplingjoy.pos

import java.math.RoundingMode

class ProductUnitPrice {

	Product product
	Unit unit
	BigDecimal price = BigDecimal.ZERO
	BigDecimal cost // transient
	
    static constraints = {
		price min: BigDecimal.ZERO
    }
	
	static belongsTo = [pricingScheme: PricingScheme]
	
	static transients = ["cost", "profitAmount", "profitPercentage"]

	public BigDecimal getProfitAmount() {
		price.subtract(cost)
	}
		
	public BigDecimal getProfitPercentage() {
		if (price.doubleValue() == 0) {
			BigDecimal.ZERO
		} else {
			BigDecimal.ONE.subtract(cost.divide(price, 4, RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(2)
		}
	}
	
	public void retrieveUnitCost() {
		cost = ProductUnitCost.findByProductAndUnit(product, unit).finalCost
	}
	
	public boolean isLessThanCost() {
		if (!cost) {
			retrieveUnitCost()
		}
		return price.compareTo(cost) == -1
	}
	
	public boolean hasNoPrice() {
		return price.compareTo(BigDecimal.ZERO) == 0
	}
		
}