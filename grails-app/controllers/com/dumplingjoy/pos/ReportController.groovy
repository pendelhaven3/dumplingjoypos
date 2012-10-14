package com.dumplingjoy.pos

import org.apache.commons.lang.StringUtils
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.codehaus.groovy.grails.web.pages.GroovyPagesTemplateEngine

import grails.plugins.springsecurity.Secured
import groovy.text.Template

@Secured("isFullyAuthenticated()")
class ReportController {

	def jasperService
	GroovyPagesTemplateEngine groovyPagesTemplateEngine
	def printService
	
	def generateSalesRequisition() {
		def salesRequisitionInstance = SalesRequisition.get(params.id)
		printService.printSalesRequisition(salesRequisitionInstance)
	}
	
	def generateSalesInvoice() {
		/*
		def salesInvoiceInstance = SalesInvoice.get(params.id)
		
		salesInvoiceInstance.customer.refresh()
		salesInvoiceInstance.pricingScheme.refresh()
		salesInvoiceInstance.items.each {
			it.refresh()
			it.product.refresh()
		}
		
		downloadReport(response, "salesInvoice", salesInvoiceInstance)
		*/
		
		def salesInvoiceInstance = SalesInvoice.get(params.id)
		printService.printSalesInvoice(salesInvoiceInstance)
	}
	
	def generateStockQuantityConversion() {
		/*
		def stockQuantityConversionInstance = StockQuantityConversion.get(params.id)
		stockQuantityConversionInstance.items.each {
			it.refresh()
			it.product.refresh()
			it.product.unitConversions.each {
				it.refresh()
			}
		}
		
		downloadReport(response, "stockConversion", stockQuantityConversionInstance)
		*/
		
		def stockQuantityConversionInstance = StockQuantityConversion.get(params.id)
		printService.printStockQuantityConversion(stockQuantityConversionInstance)
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
//		
//		purchaseOrderInstance.supplier.refresh()
//		purchaseOrderInstance.items.each {
//			it.refresh()
//			it.product.refresh()
//		}
//		
//		downloadReport(response, "purchaseOrder", purchaseOrderInstance)
		
		Template t = groovyPagesTemplateEngine.createTemplate("/report/_purchaseOrder.gsp")
		
		Writable w = t.make([purchaseOrder: purchaseOrderInstance])
		StringWriter sw = new StringWriter()
		w.writeTo(sw)
		println sw.toString()
	}
	
	def generateReceivingReceipt() {
		def receivingReceiptInstance = ReceivingReceipt.get(params.id)
		
		receivingReceiptInstance.supplier.refresh()
		receivingReceiptInstance.terms.refresh()
		receivingReceiptInstance.relatedPurchaseOrder.refresh()
		receivingReceiptInstance.items.each {
			it.refresh()
			it.product.refresh()
		}
		
		downloadReport(response, "receivingReceipt", receivingReceiptInstance)
	}

	private def downloadReport(response, reportName, data) {
		boolean inline = false
		if (inline) {
			def reportDef = createReportDef(reportName, data)
	
			response.contentType = "application/pdf"
			response.setHeader("contentDisposition", "attachment; filename=" + reportName + ".pdf")
			response.outputStream << jasperService.generateReport(reportDef).toByteArray()
		} else {
			params._format = "PDF"
			params._file = reportName
			params._name = reportName
			params.SUBREPORT_DIR = "jasperreports/"
			chain(controller: 'jasper', action: 'index', model: [data: [data]], params: params)
		}
	}
	
	def generateAdjustmentIn() {
		def adjustmentInInstance = AdjustmentIn.get(params.id)
		printService.printAdjustmentIn(adjustmentInInstance)
	}

}