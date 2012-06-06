package com.dumplingjoy.pos

import java.math.RoundingMode

import com.dumplingjoy.pos.util.Percentage

class SalesInvoiceItem {

	Product product
	Unit unit
	Integer quantity
	BigDecimal unitPrice
	BigDecimal discount1 = BigDecimal.ZERO
	BigDecimal discount2 = BigDecimal.ZERO
	BigDecimal discount3 = BigDecimal.ZERO
	BigDecimal flatRateDiscount = BigDecimal.ZERO

    static constraints = {
    }
	
	static belongsTo = [salesInvoice: SalesInvoice]

	static transients = ["amount", "discountedAmount", "netAmount", "discounted"]

	BigDecimal getAmount() {
		unitPrice.multiply(BigDecimal.valueOf((long)quantity)).setScale(2, RoundingMode.HALF_UP);
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
	
	public boolean isDiscounted() {
		discount1.compareTo(BigDecimal.ZERO) == 1 ||
		discount2.compareTo(BigDecimal.ZERO) == 1 ||
		discount3.compareTo(BigDecimal.ZERO) == 1 ||
		flatRateDiscount.compareTo(BigDecimal.ZERO) == 1
	}

}