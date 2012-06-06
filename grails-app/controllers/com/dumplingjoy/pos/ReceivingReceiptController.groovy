package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class ReceivingReceiptController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [receivingReceiptInstanceList: ReceivingReceipt.list(params), receivingReceiptInstanceTotal: ReceivingReceipt.count()]
    }

    def create() {
        [receivingReceiptInstance: new ReceivingReceipt(params)]
    }

    def save() {
        def receivingReceiptInstance = new ReceivingReceipt(params)
        if (!receivingReceiptInstance.save(flush: true)) {
            render(view: "create", model: [receivingReceiptInstance: receivingReceiptInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'receivingReceipt.label', default: 'ReceivingReceipt'), receivingReceiptInstance.id])
        redirect(action: "show", id: receivingReceiptInstance.id)
    }

    def show() {
        def receivingReceiptInstance = ReceivingReceipt.get(params.id)
        if (!receivingReceiptInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceipt.label', default: 'ReceivingReceipt'), params.id])
            redirect(action: "list")
            return
        }

        [receivingReceiptInstance: receivingReceiptInstance]
    }

    def edit() {
        def receivingReceiptInstance = ReceivingReceipt.get(params.id)
        if (!receivingReceiptInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceipt.label', default: 'ReceivingReceipt'), params.id])
            redirect(action: "list")
            return
        }

        [receivingReceiptInstance: receivingReceiptInstance]
    }

    def update() {
        def receivingReceiptInstance = ReceivingReceipt.get(params.id)
        if (!receivingReceiptInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceipt.label', default: 'ReceivingReceipt'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (receivingReceiptInstance.version > version) {
                receivingReceiptInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'receivingReceipt.label', default: 'ReceivingReceipt')] as Object[],
                          "Another user has updated this ReceivingReceipt while you were editing")
                render(view: "edit", model: [receivingReceiptInstance: receivingReceiptInstance])
                return
            }
        }

        receivingReceiptInstance.properties = params

        if (!receivingReceiptInstance.save(flush: true)) {
            render(view: "edit", model: [receivingReceiptInstance: receivingReceiptInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'receivingReceipt.label', default: 'ReceivingReceipt'), receivingReceiptInstance.id])
        redirect(action: "show", id: receivingReceiptInstance.id)
    }

    def delete() {
        def receivingReceiptInstance = ReceivingReceipt.get(params.id)
        if (!receivingReceiptInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceipt.label', default: 'ReceivingReceipt'), params.id])
            redirect(action: "list")
            return
        }

        try {
            receivingReceiptInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'receivingReceipt.label', default: 'ReceivingReceipt'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'receivingReceipt.label', default: 'ReceivingReceipt'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
