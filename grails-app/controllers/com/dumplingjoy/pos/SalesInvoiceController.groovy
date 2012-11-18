package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

@Secured("isFullyAuthenticated()")
class SalesInvoiceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: "salesInvoiceNumber"
		params.order = params.order ?: "desc"
        [salesInvoiceInstanceList: SalesInvoice.list(params), salesInvoiceInstanceTotal: SalesInvoice.count()]
    }

    def show() {
        def salesInvoiceInstance = SalesInvoice.get(params.id)
        if (!salesInvoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesInvoice.label', default: 'SalesInvoice'), params.id])
            redirect(action: "list")
            return
        }
		
		Collections.sort(salesInvoiceInstance.items, new Comparator<SalesInvoiceItem>() {
			public int compare(SalesInvoiceItem o1, SalesInvoiceItem o2) {
				return o1.getProduct().getDescription().compareTo(o2.getProduct().getDescription())
			}
		})

        [salesInvoiceInstance: salesInvoiceInstance]
    }

	def cancelSalesInvoice() {
		SalesInvoice salesInvoiceInstance = SalesInvoice.get(params.id)
		if (!salesInvoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesInvoice.label'), params.id])
			redirect(action: "list")
			return
		}
		
		if (!salesInvoiceInstance.cancel()) {
			render(view: "show", model: [salesInvoiceInstance: salesInvoiceInstance])
			return
		}
		
		flash.message = message(code: 'default.cancelled.message', args: [message(code: 'salesInvoice.label')])
		redirect(action: "show", id: salesInvoiceInstance.id)
	}

}
