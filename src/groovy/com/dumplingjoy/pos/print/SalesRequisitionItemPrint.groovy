package com.dumplingjoy.pos.print

import com.dumplingjoy.pos.Product
import com.dumplingjoy.pos.SalesRequisitionItem
import com.dumplingjoy.pos.Unit

class SalesRequisitionItemPrint {

	Product product
	Unit unit
	String quantity
	String unitPrice
	String amount
	
	public SalesRequisitionItemPrint(SalesRequisitionItem salesRequisitionItem) {
		product = salesRequisitionItem.product
		product.description = PrintUtil.asLeftAlignedField(product.description, 40)
		unit = salesRequisitionItem.unit
		quantity = PrintUtil.asRightAlignedField(salesRequisitionItem.quantity, 5)
		unitPrice = PrintUtil.asCurrencyField(salesRequisitionItem.unitPrice, 9)
		amount = PrintUtil.asCurrencyField(salesRequisitionItem.amount, 10)
	}
	
}
