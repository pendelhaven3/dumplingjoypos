package com.dumplingjoy.pos.print

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.dumplingjoy.pos.Customer;
import com.dumplingjoy.pos.PaymentTerms;
import com.dumplingjoy.pos.PricingScheme;
import com.dumplingjoy.pos.SalesRequisition
import com.dumplingjoy.pos.SalesRequisitionItem;

class SalesRequisitionPrint {
	
	String salesRequisitionNumber
	CustomerPrint customer
	List<SalesRequisitionItemPrint> items = new ArrayList<SalesRequisitionItemPrint>()
	String totalAmount
	String totalItems
	String totalQuantity
	String encodedBy
	PaymentTerms paymentTerms
	String remarks

	public SalesRequisitionPrint(SalesRequisition salesRequisition) {
		salesRequisitionNumber = PrintUtil.asRightAlignedField(salesRequisition.salesRequisitionNumber, 8)
		customer = new CustomerPrint(salesRequisition.customer)
		salesRequisition.items.each {
			items.add(new SalesRequisitionItemPrint(it))
		}
		totalAmount = PrintUtil.asCurrencyField(salesRequisition.totalAmount, 12)
		totalItems = PrintUtil.asLeftAlignedField(salesRequisition.items.size(), 3)
		totalQuantity = PrintUtil.asRightAlignedField(salesRequisition.totalQuantity, 4)
		encodedBy = salesRequisition.createdBy
		paymentTerms = salesRequisition.paymentTerms
		remarks = PrintUtil.asLeftAlignedField(StringUtils.defaultString(salesRequisition.remarks), 60)
	}
	
}
