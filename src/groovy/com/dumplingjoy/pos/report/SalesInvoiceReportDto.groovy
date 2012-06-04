package com.dumplingjoy.pos.report

import com.dumplingjoy.pos.Customer
import com.dumplingjoy.pos.SalesInvoice
import com.dumplingjoy.pos.SalesRequisitionItem

class SalesInvoiceReportDto {

	Integer salesInvoiceNo
	Customer customer
	Date postDate
	String deliveryType
	List<SalesRequisitionItem> items
	
	public SalesInvoiceReportDto(SalesInvoice salesInvoice) {
		salesInvoiceNo = salesInvoice.salesInvoiceNumber
		customer = salesInvoice.customer
		customer.name
		customer.address
		postDate = salesInvoice.postDate
		deliveryType = salesInvoice.deliveryType
		items = salesInvoice.items
	}
	
}
