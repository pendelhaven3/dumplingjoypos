package com.dumplingjoy.pos


import groovy.text.Template

import org.codehaus.groovy.grails.web.pages.GroovyPagesTemplateEngine;

import com.dumplingjoy.pos.dotmatrixprint.PrinterUtil;
import com.dumplingjoy.pos.print.SalesRequisitionPrint

class PrintService {

	GroovyPagesTemplateEngine groovyPagesTemplateEngine
	
	def printSalesRequisition(SalesRequisition salesRequisitionInstance) {
		String currentDate = new Date().format("MM/dd/yy")
		
		Template t = groovyPagesTemplateEngine.createTemplate("/report/_salesRequisition.gsp")
		Writable w = t.make([salesRequisition: new SalesRequisitionPrint(salesRequisitionInstance), currentDate: currentDate])
		StringWriter sw = new StringWriter()
		w.writeTo(sw)
		PrinterUtil.print(sw.toString())
	}
	
}
