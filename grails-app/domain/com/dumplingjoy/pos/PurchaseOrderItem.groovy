package com.dumplingjoy.pos

import java.math.RoundingMode

class PurchaseOrderItem {

	Product product
	Unit unit
	Integer quantity
	BigDecimal cost
	
    static constraints = {
		quantity min: 0
		cost min: BigDecimal.ZERO
    }

	static belongsTo = [purchaseOrder: PurchaseOrder]

	static transients = ["currentCost", "amount"] // , "hasPostError"]

	public BigDecimal getCurrentCost() {
		ProductUnitCost.findByProductAndUnit(product, unit).cost
	}
	
	public BigDecimal getAmount() {
		cost.multiply(new BigDecimal(quantity)).setScale(2, RoundingMode.HALF_UP)
	}
	
}
