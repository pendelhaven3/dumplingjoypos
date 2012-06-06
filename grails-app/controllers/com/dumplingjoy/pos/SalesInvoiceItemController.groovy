package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

@Secured("isFullyAuthenticated()")
class SalesInvoiceItemController {

	def showDiscounts() {
		SalesInvoice salesInvoiceInstance = SalesInvoice.get(params["salesInvoice.id"])
		if (!salesInvoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesInvoice.label')])
			redirect(controller: "salesInvoice", action: "list")
			return
		}

		SalesInvoiceItem salesInvoiceItemInstance = SalesInvoiceItem.get(params.id)
		if (!salesInvoiceItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesInvoiceItem.label')])
			redirect(controller: "salesInvoice", action: "show", id: salesInvoiceInstance.id)
			return
		}

		[salesInvoiceItemInstance: salesInvoiceItemInstance, salesInvoiceInstance: salesInvoiceInstance]
	}
	
}
