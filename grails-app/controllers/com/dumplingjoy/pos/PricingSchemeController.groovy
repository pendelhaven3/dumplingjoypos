package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class PricingSchemeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [pricingSchemeInstanceList: PricingScheme.list(params), pricingSchemeInstanceTotal: PricingScheme.count()]
    }

    def create() {
        [pricingSchemeInstance: new PricingScheme(params)]
    }

    def save() {
        def pricingSchemeInstance = new PricingScheme(params)
        if (!pricingSchemeInstance.save(flush: true)) {
            render(view: "create", model: [pricingSchemeInstance: pricingSchemeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'pricingScheme.label', default: 'PricingScheme'), pricingSchemeInstance.id])
        redirect(action: "show", id: pricingSchemeInstance.id)
    }

    def show() {
        def pricingSchemeInstance = PricingScheme.get(params.id)
        if (!pricingSchemeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'pricingScheme.label', default: 'PricingScheme'), params.id])
            redirect(action: "list")
            return
        }

        [
			pricingSchemeInstance: pricingSchemeInstance,
			allProducts: Product.list([sort: "code", order: "asc"])
		]
    }

    def edit() {
        def pricingSchemeInstance = PricingScheme.get(params.id)
        if (!pricingSchemeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pricingScheme.label', default: 'PricingScheme'), params.id])
            redirect(action: "list")
            return
        }

        [pricingSchemeInstance: pricingSchemeInstance]
    }

    def update() {
        def pricingSchemeInstance = PricingScheme.get(params.id)
        if (!pricingSchemeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pricingScheme.label', default: 'PricingScheme'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (pricingSchemeInstance.version > version) {
                pricingSchemeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'pricingScheme.label', default: 'PricingScheme')] as Object[],
                          "Another user has updated this PricingScheme while you were editing")
                render(view: "edit", model: [pricingSchemeInstance: pricingSchemeInstance])
                return
            }
        }

        pricingSchemeInstance.properties = params

        if (!pricingSchemeInstance.save(flush: true)) {
            render(view: "edit", model: [pricingSchemeInstance: pricingSchemeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'pricingScheme.label', default: 'PricingScheme'), pricingSchemeInstance.id])
        redirect(action: "show", id: pricingSchemeInstance.id)
    }

    def delete() {
        def pricingSchemeInstance = PricingScheme.get(params.id)
        if (!pricingSchemeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'pricingScheme.label', default: 'PricingScheme'), params.id])
            redirect(action: "list")
            return
        }

        try {
            pricingSchemeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'pricingScheme.label', default: 'PricingScheme'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pricingScheme.label', default: 'PricingScheme'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
