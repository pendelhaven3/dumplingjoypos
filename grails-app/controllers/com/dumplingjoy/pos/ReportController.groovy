package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

@Secured("isFullyAuthenticated()")
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
		chain(controller: 'jasper', action: 'index', model: [data: [salesInvoiceInstance]], params: params)
	}
	
	def generateStockQuantityConversion() {
		def stockQuantityConversionInstance = StockQuantityConversion.get(params.id)
		stockQuantityConversionInstance.items.each {
			it.refresh()
			it.product.refresh()
			it.product.unitConversions.each {
				it.refresh()
			}
		}
		
		params._format = "PDF"
		params._file = "stockConversion"
		params._name = "stockConversion"
		params.SUBREPORT_DIR = "jasperreports/"
		chain(controller: 'jasper', action: 'index', model: [data: [stockQuantityConversionInstance]], params: params)
	}
	
}
