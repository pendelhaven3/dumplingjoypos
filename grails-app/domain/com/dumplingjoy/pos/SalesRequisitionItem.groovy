package com.dumplingjoy.pos

import java.math.RoundingMode

class SalesRequisitionItem {

	Product product
	Unit unit
	Integer quantity
	boolean hasPostError
	
    static constraints = {
		quantity min: 1
    }
	
	static belongsTo = [salesRequisition: SalesRequisition]
	
	static transients = ["unitPrice", "amount", "hasPostError"]
	
	BigDecimal getUnitPrice() {
		PricingScheme pricingScheme = salesRequisition.pricingScheme
		pricingScheme.getProductUnitPrices(product).find {it.unit == unit}.price
	}
	
	BigDecimal getAmount() {
		getUnitPrice().multiply(BigDecimal.valueOf((long)quantity)).setScale(2, RoundingMode.HALF_UP);
	}

}
