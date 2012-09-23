package com.dumplingjoy.pos


import java.util.Comparator;

import groovy.text.Template

import org.codehaus.groovy.grails.web.pages.GroovyPagesTemplateEngine;

import com.dumplingjoy.pos.dotmatrixprint.PrinterUtil;
import com.dumplingjoy.pos.print.SalesRequisitionPrint

class PrintService {
	
	private static final int SALES_INVOICE_ITEMS_PER_PAGE = 44
	
	GroovyPagesTemplateEngine groovyPagesTemplateEngine
	
	def printSalesRequisition(SalesRequisition salesRequisitionInstance) {
		String currentDate = new Date().format("MM/dd/yy")
		
		Template t = groovyPagesTemplateEngine.createTemplate("/report/_salesRequisition.gsp")
		Writable w = t.make([salesRequisition: new SalesRequisitionPrint(salesRequisitionInstance), currentDate: currentDate])
		StringWriter sw = new StringWriter()
		w.writeTo(sw)
		PrinterUtil.print(sw.toString())
	}
	
	def printSalesInvoice(SalesInvoice salesInvoiceInstance) {
		Collections.sort(salesInvoiceInstance.items, new Comparator<SalesInvoiceItem>() {
			public int compare(SalesInvoiceItem o1, SalesInvoiceItem o2) {
				return o1.getProduct().getDescription().compareTo(o2.getProduct().getDescription())
			}
		})

		String currentDate = new Date().format("MM/dd/yy")
		def pageItems = salesInvoiceInstance.items.collate(SALES_INVOICE_ITEMS_PER_PAGE)
		pageItems.eachWithIndex { it, index ->
			Map<String, Object> reportData = [
				salesInvoice: salesInvoiceInstance, 
				items: it, 
				currentDate: currentDate,
				currentPage: index + 1,
				totalPages: pageItems.size(),
				isLastPage: (index + 1) == pageItems.size()
			]
			printReport("/report/_salesInvoice.gsp", reportData)
		}
	}
	
	private void printReport(String template, Map<String, Object> reportData) {
		Template t = groovyPagesTemplateEngine.createTemplate(template)
		Writable w = t.make(reportData)
		StringWriter sw = new StringWriter()
		w.writeTo(sw)
		// println sw.toString()
		PrinterUtil.print(sw.toString())
	}
	
}
