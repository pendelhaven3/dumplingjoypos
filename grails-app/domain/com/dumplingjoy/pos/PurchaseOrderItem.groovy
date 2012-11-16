package com.dumplingjoy.pos

import java.math.RoundingMode

class PurchaseOrderItem {

	Product product
	Unit unit
	Integer quantity
	BigDecimal cost
	Integer actualQuantity
	
    static constraints = {
		quantity min: 0, validator: validateQuantityGreaterThanZero
		cost min: BigDecimal.ZERO, validator: validateCostGreaterThanZero
		actualQuantity nullable: true, min: 0
    }

	static belongsTo = [purchaseOrder: PurchaseOrder]

	static transients = ["grossCost", "amount", "originalAmount"]

	private static def validateCostGreaterThanZero = { BigDecimal cost, PurchaseOrderItem item ->
		if (item.product && item.unit && item.cost != null) {
			if (cost.compareTo(BigDecimal.ZERO) == 0) {
				return "noCost.message"
			} else {
				return true
			}
		}
	}
	
	private static def validateQuantityGreaterThanZero = { Integer quantity, PurchaseOrderItem item ->
		if (item.quantity != null) {
			if (quantity == 0) {
				return "noQuantity.message"
			} else {
				return true
			}
		}
	}
	
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