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

        [salesInvoiceInstance: salesInvoiceInstance]
    }

}
