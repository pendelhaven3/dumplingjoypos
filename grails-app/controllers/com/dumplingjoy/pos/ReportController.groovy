package com.dumplingjoy.pos

import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef

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
		
		def reportDef = createReportDef("salesInvoice", salesInvoiceInstance)
		
		response.contentType = "application/pdf"
		response.setHeader("contentDisposition", "inline") 
		response.outputStream << jasperService.generateReport(reportDef).toByteArray()
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
		
		def reportDef = createReportDef("stockConversion", stockQuantityConversionInstance)
		
		response.contentType = "application/pdf"
		response.setHeader("contentDisposition", "inline") 
		response.outputStream << jasperService.generateReport(reportDef).toByteArray()
	}
	
	private JasperReportDef createReportDef(String reportName, Object data) {
		new JasperReportDef(
			name: reportName + ".jasper",
			fileFormat: JasperExportFormat.PDF_FORMAT,
			reportData: [data],
			parameters: [SUBREPORT_DIR: "jasperreports/"]
		)
	}
	
	def generatePurchaseOrder() {
		def purchaseOrderInstance = PurchaseOrder.get(params.id)
		
		purchaseOrderInstance.supplier.refresh()
		purchaseOrderInstance.items.each {
			it.refresh()
			it.product.refresh()
		}
		
		def reportDef = createReportDef("purchaseOrder", purchaseOrderInstance)
		
		response.contentType = "application/pdf"
		response.setHeader("contentDisposition", "inline")
		response.outputStream << jasperService.generateReport(reportDef).toByteArray()
	}
	
}