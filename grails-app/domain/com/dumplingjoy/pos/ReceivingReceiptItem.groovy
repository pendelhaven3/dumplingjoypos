package com.dumplingjoy.pos

import java.math.RoundingMode

import com.dumplingjoy.pos.util.Percentage

class ReceivingReceiptItem {

	Product product
	Unit unit
	Integer quantity
	BigDecimal cost
	BigDecimal discount1 = BigDecimal.ZERO
	BigDecimal discount2 = BigDecimal.ZERO
	BigDecimal discount3 = BigDecimal.ZERO
	BigDecimal flatRateDiscount = BigDecimal.ZERO

    static constraints = {
		quantity min: 1
		discount1 min: BigDecimal.ZERO
		discount2 min: BigDecimal.ZERO
		discount3 min: BigDecimal.ZERO
		flatRateDiscount min: BigDecimal.ZERO
    }
	
	static belongsTo = [receivingReceipt: ReceivingReceipt]

	// gross amount
	public BigDecimal getAmount() {
		cost.multiply(BigDecimal.valueOf((long)quantity)).setScale(2, RoundingMode.HALF_UP)
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

}
