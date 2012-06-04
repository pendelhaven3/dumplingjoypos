package com.dumplingjoy.pos

import com.dumplingjoy.pos.report.SalesInvoiceReportDto

class ReportController {

	def jasperService
	
	def generateSalesInvoice() {
		def salesInvoiceInstance = SalesInvoice.get(params.id)
		
		salesInvoiceInstance.customer.refresh()
		salesInvoiceInstance.pricingScheme.refresh()
		salesInvoiceInstance.items.each {
			it.refresh()
			it.product.refresh()
		}
		
		params._format = "PDF"
		params._file = "salesInvoice"
		params._name = "salesInvoice"
		params.SUBREPORT_DIR = "jasperreports/"
//		chain(controller: 'jasper', action: 'index', model: [data: [new SalesInvoiceReportDto(salesInvoiceInstance)]], params: params)
		chain(controller: 'jasper', action: 'index', model: [data: [salesInvoiceInstance]], params: params)
	}
	
}
