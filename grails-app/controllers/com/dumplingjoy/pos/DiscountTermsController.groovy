package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class DiscountTermsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: "name"
		params.order = params.order ?: "asc"
        [discountTermsInstanceList: DiscountTerms.list(params), discountTermsInstanceTotal: DiscountTerms.count()]
    }

    def create() {
        [discountTermsInstance: new DiscountTerms(params)]
    }

    def save() {
        def discountTermsInstance = new DiscountTerms(params)
        if (!discountTermsInstance.save(flush: true)) {
            render(view: "create", model: [discountTermsInstance: discountTermsInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'discountTerms.label', default: 'DiscountTerms'), discountTermsInstance.id])
        redirect(action: "show", id: discountTermsInstance.id)
    }

    def show() {
        def discountTermsInstance = DiscountTerms.get(params.id)
        if (!discountTermsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'discountTerms.label', default: 'DiscountTerms'), params.id])
            redirect(action: "list")
            return
        }

        [discountTermsInstance: discountTermsInstance]
    }

    def edit() {
        def discountTermsInstance = DiscountTerms.get(params.id)
        if (!discountTermsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'discountTerms.label', default: 'DiscountTerms'), params.id])
            redirect(action: "list")
            return
        }

        [discountTermsInstance: discountTermsInstance]
    }

    def update() {
        def discountTermsInstance = DiscountTerms.get(params.id)
        if (!discountTermsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'discountTerms.label', default: 'DiscountTerms'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (discountTermsInstance.version > version) {
                discountTermsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'discountTerms.label', default: 'DiscountTerms')] as Object[],
                          "Another user has updated this DiscountTerms while you were editing")
                render(view: "edit", model: [discountTermsInstance: discountTermsInstance])
                return
            }
        }

        discountTermsInstance.properties = params

        if (!discountTermsInstance.save(flush: true)) {
            render(view: "edit", model: [discountTermsInstance: discountTermsInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'discountTerms.label', default: 'DiscountTerms'), discountTermsInstance.id])
        redirect(action: "show", id: discountTermsInstance.id)
    }

    def delete() {
        def discountTermsInstance = DiscountTerms.get(params.id)
        if (!discountTermsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'discountTerms.label', default: 'DiscountTerms'), params.id])
            redirect(action: "list")
            return
        }

        try {
            discountTermsInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'discountTerms.label', default: 'DiscountTerms'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'discountTerms.label', default: 'DiscountTerms'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
