package com.dumplingjoy.pos

import java.math.RoundingMode

import com.dumplingjoy.pos.util.Percentage


class SalesRequisitionItem {

	Product product
	Unit unit
	Integer quantity
	boolean hasPostError
	BigDecimal discount1 = BigDecimal.ZERO
	BigDecimal discount2 = BigDecimal.ZERO
	BigDecimal discount3 = BigDecimal.ZERO
	BigDecimal flatRateDiscount = BigDecimal.ZERO
	
    static constraints = {
		quantity min: 1, validator: validateEnoughQuantityAvailable
		discount1 min: BigDecimal.ZERO
		discount2 min: BigDecimal.ZERO
		discount3 min: BigDecimal.ZERO
		flatRateDiscount min: BigDecimal.ZERO
    }
	
	static belongsTo = [salesRequisition: SalesRequisition]
	
	static transients = ["unitPrice", "amount", "hasPostError", "netAmount", "discountedAmount"]
	
	private static def validateEnoughQuantityAvailable = { Integer quantity, SalesRequisitionItem item ->
		if (item.product && item.quantity > 0 && item.unit) {
			UnitQuantity available = item.product.unitQuantities.find {it.unit == item.unit}
			if (quantity > available.quantity) {
				return "notEnoughQuantityAvailable.message"
			} else {
				return true
			}
		}
	}

	public BigDecimal getUnitPrice() {
		PricingScheme pricingScheme = salesRequisition.pricingScheme
		pricingScheme.getProductUnitPrices(product).find {it.unit == unit}.price
	}
	
	// gross amount
	public BigDecimal getAmount() {
		getUnitPrice().multiply(BigDecimal.valueOf((long)quantity)).setScale(2, RoundingMode.HALF_UP)
	}

	public BigDecimal getDiscountedAmount() {
		getAmount().subtract(getNetAmount())
	}

	public BigDecimal getNetAmount() {
		BigDecimal netAmount = getAmount()
		if (discount1 && !BigDecimal.ZERO.equals(discount1)) {
			netAmount = netAmount.subtract(netAmount.multiply(new Percentage(discount1).toDecimal()))
		}
		if (discount2 && !BigDecimal.ZERO.equals(discount2)) {
			netAmount = netAmount.subtract(netAmount.multiply(new Percentage(discount2).toDecimal()))
		}
		if (discount3 && !BigDecimal.ZERO.equals(discount3)) {
			netAmount = netAmount.subtract(netAmount.multiply(new Percentage(discount3).toDecimal()))
		}
		if (flatRateDiscount) {
			netAmount = netAmount.subtract(flatRateDiscount)
		}
		netAmount
	}
	
	public boolean hasPriceLessThanCost() {
		ProductUnitPrice unitPrice = ProductUnitPrice.find("from ProductUnitPrice up where up.pricingScheme = ? and up.product = ? and up.unit = ?",
			[salesRequisition.pricingScheme, product, unit])
		return unitPrice.isLessThanCost()
	}

}
