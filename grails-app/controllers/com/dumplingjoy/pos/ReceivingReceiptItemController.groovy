package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class ReceivingReceiptItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [receivingReceiptItemInstanceList: ReceivingReceiptItem.list(params), receivingReceiptItemInstanceTotal: ReceivingReceiptItem.count()]
    }

    def create() {
        [receivingReceiptItemInstance: new ReceivingReceiptItem(params)]
    }

    def save() {
        def receivingReceiptItemInstance = new ReceivingReceiptItem(params)
        if (!receivingReceiptItemInstance.save(flush: true)) {
            render(view: "create", model: [receivingReceiptItemInstance: receivingReceiptItemInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'receivingReceiptItem.label', default: 'ReceivingReceiptItem'), receivingReceiptItemInstance.id])
        redirect(action: "show", id: receivingReceiptItemInstance.id)
    }

    def show() {
        def receivingReceiptItemInstance = ReceivingReceiptItem.get(params.id)
        if (!receivingReceiptItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceiptItem.label', default: 'ReceivingReceiptItem'), params.id])
            redirect(action: "list")
            return
        }

        [receivingReceiptItemInstance: receivingReceiptItemInstance]
    }

    def edit() {
        def receivingReceiptItemInstance = ReceivingReceiptItem.get(params.id)
        if (!receivingReceiptItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceiptItem.label', default: 'ReceivingReceiptItem'), params.id])
            redirect(action: "list")
            return
        }

        [receivingReceiptItemInstance: receivingReceiptItemInstance]
    }

    def update() {
        def receivingReceiptItemInstance = ReceivingReceiptItem.get(params.id)
        if (!receivingReceiptItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceiptItem.label', default: 'ReceivingReceiptItem'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (receivingReceiptItemInstance.version > version) {
                receivingReceiptItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'receivingReceiptItem.label', default: 'ReceivingReceiptItem')] as Object[],
                          "Another user has updated this ReceivingReceiptItem while you were editing")
                render(view: "edit", model: [receivingReceiptItemInstance: receivingReceiptItemInstance])
                return
            }
        }

        receivingReceiptItemInstance.properties = params

        if (!receivingReceiptItemInstance.save(flush: true)) {
            render(view: "edit", model: [receivingReceiptItemInstance: receivingReceiptItemInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'receivingReceiptItem.label', default: 'ReceivingReceiptItem'), receivingReceiptItemInstance.id])
        redirect(action: "show", id: receivingReceiptItemInstance.id)
    }

    def delete() {
        def receivingReceiptItemInstance = ReceivingReceiptItem.get(params.id)
        if (!receivingReceiptItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceiptItem.label', default: 'ReceivingReceiptItem'), params.id])
            redirect(action: "list")
            return
        }

        try {
            receivingReceiptItemInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'receivingReceiptItem.label', default: 'ReceivingReceiptItem'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'receivingReceiptItem.label', default: 'ReceivingReceiptItem'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
