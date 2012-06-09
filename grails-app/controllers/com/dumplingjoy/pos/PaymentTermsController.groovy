package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class PaymentTermsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: "name"
		params.order = params.order ?: "asc"
        [paymentTermsInstanceList: PaymentTerms.list(params), paymentTermsInstanceTotal: PaymentTerms.count()]
    }

    def create() {
        [paymentTermsInstance: new PaymentTerms(params)]
    }

    def save() {
        def paymentTermsInstance = new PaymentTerms(params)
        if (!paymentTermsInstance.save(flush: true)) {
            render(view: "create", model: [paymentTermsInstance: paymentTermsInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'paymentTerms.label', default: 'PaymentTerms'), paymentTermsInstance.id])
        redirect(action: "show", id: paymentTermsInstance.id)
    }

    def show() {
        def paymentTermsInstance = PaymentTerms.get(params.id)
        if (!paymentTermsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'paymentTerms.label', default: 'PaymentTerms'), params.id])
            redirect(action: "list")
            return
        }

        [paymentTermsInstance: paymentTermsInstance]
    }

    def edit() {
        def paymentTermsInstance = PaymentTerms.get(params.id)
        if (!paymentTermsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'paymentTerms.label', default: 'PaymentTerms'), params.id])
            redirect(action: "list")
            return
        }

        [paymentTermsInstance: paymentTermsInstance]
    }

    def update() {
        def paymentTermsInstance = PaymentTerms.get(params.id)
        if (!paymentTermsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'paymentTerms.label', default: 'PaymentTerms'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (paymentTermsInstance.version > version) {
                paymentTermsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'paymentTerms.label', default: 'PaymentTerms')] as Object[],
                          "Another user has updated this PaymentTerms while you were editing")
                render(view: "edit", model: [paymentTermsInstance: paymentTermsInstance])
                return
            }
        }

        paymentTermsInstance.properties = params

        if (!paymentTermsInstance.save(flush: true)) {
            render(view: "edit", model: [paymentTermsInstance: paymentTermsInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'paymentTerms.label', default: 'PaymentTerms'), paymentTermsInstance.id])
        redirect(action: "show", id: paymentTermsInstance.id)
    }

    def delete() {
        def paymentTermsInstance = PaymentTerms.get(params.id)
        if (!paymentTermsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'paymentTerms.label', default: 'PaymentTerms'), params.id])
            redirect(action: "list")
            return
        }

        try {
            paymentTermsInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'paymentTerms.label', default: 'PaymentTerms'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'paymentTerms.label', default: 'PaymentTerms'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
