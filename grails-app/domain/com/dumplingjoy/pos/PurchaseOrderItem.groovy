package com.dumplingjoy.pos

import java.math.RoundingMode

class PurchaseOrderItem {

	Product product
	Unit unit
	Integer quantity
	BigDecimal cost
	Integer actualQuantity
	
    static constraints = {
		quantity min: 0
		cost min: BigDecimal.ZERO
		actualQuantity nullable: true, min: 0
    }

	static belongsTo = [purchaseOrder: PurchaseOrder]

	static transients = ["grossCost", "amount", "originalAmount"]

	public BigDecimal getGrossCost() {
		ProductUnitCost.findByProductAndUnit(product, unit).grossCost
	}
	
	public BigDecimal getAmount() {
		if (!purchaseOrder.ordered) {
			return getOriginalAmount()
		}
		
		int qty = 0
		if (actualQuantity) {
			qty = actualQuantity
		}
		cost.multiply(new BigDecimal(qty)).setScale(2, RoundingMode.HALF_UP)
	}
	
	def beforeInsert() {
		if (purchaseOrder.ordered) {
			actualQuantity = quantity
			quantity = 0
		}
	}
	
	public BigDecimal getOriginalAmount() {
		cost.multiply(new BigDecimal(quantity)).setScale(2, RoundingMode.HALF_UP)
	}
	
}