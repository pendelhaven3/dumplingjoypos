package com.dumplingjoy.pos

import java.math.RoundingMode

class SalesInvoiceItem {

	Product product
	Unit unit
	Integer quantity
	BigDecimal unitPrice

    static constraints = {
    }
	
	static belongsTo = [salesInvoice: SalesInvoice]

	static transients = ["amount"]

	BigDecimal getAmount() {
		unitPrice.multiply(BigDecimal.valueOf((long)quantity)).setScale(2, RoundingMode.HALF_UP);
	}

}
