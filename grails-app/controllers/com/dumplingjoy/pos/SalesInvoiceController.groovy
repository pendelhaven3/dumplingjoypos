package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class SalesInvoiceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [salesInvoiceInstanceList: SalesInvoice.list(params), salesInvoiceInstanceTotal: SalesInvoice.count()]
    }

    def create() {
        [salesInvoiceInstance: new SalesInvoice(params)]
    }

    def save() {
        def salesInvoiceInstance = new SalesInvoice(params)
        if (!salesInvoiceInstance.save(flush: true)) {
            render(view: "create", model: [salesInvoiceInstance: salesInvoiceInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'salesInvoice.label', default: 'SalesInvoice'), salesInvoiceInstance.id])
        redirect(action: "show", id: salesInvoiceInstance.id)
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

    def edit() {
        def salesInvoiceInstance = SalesInvoice.get(params.id)
        if (!salesInvoiceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesInvoice.label', default: 'SalesInvoice'), params.id])
            redirect(action: "list")
            return
        }

        [salesInvoiceInstance: salesInvoiceInstance]
    }

    def update() {
        def salesInvoiceInstance = SalesInvoice.get(params.id)
        if (!salesInvoiceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesInvoice.label', default: 'SalesInvoice'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (salesInvoiceInstance.version > version) {
                salesInvoiceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'salesInvoice.label', default: 'SalesInvoice')] as Object[],
                          "Another user has updated this SalesInvoice while you were editing")
                render(view: "edit", model: [salesInvoiceInstance: salesInvoiceInstance])
                return
            }
        }

        salesInvoiceInstance.properties = params

        if (!salesInvoiceInstance.save(flush: true)) {
            render(view: "edit", model: [salesInvoiceInstance: salesInvoiceInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'salesInvoice.label', default: 'SalesInvoice'), salesInvoiceInstance.id])
        redirect(action: "show", id: salesInvoiceInstance.id)
    }

    def delete() {
        def salesInvoiceInstance = SalesInvoice.get(params.id)
        if (!salesInvoiceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesInvoice.label', default: 'SalesInvoice'), params.id])
            redirect(action: "list")
            return
        }

        try {
            salesInvoiceInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'salesInvoice.label', default: 'SalesInvoice'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'salesInvoice.label', default: 'SalesInvoice'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
