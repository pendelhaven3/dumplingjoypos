package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class ProductUnitPriceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [productUnitPriceInstanceList: ProductUnitPrice.list(params), productUnitPriceInstanceTotal: ProductUnitPrice.count()]
    }

    def create() {
        [productUnitPriceInstance: new ProductUnitPrice(params)]
    }

    def save() {
        def productUnitPriceInstance = new ProductUnitPrice(params)
        if (!productUnitPriceInstance.save(flush: true)) {
            render(view: "create", model: [productUnitPriceInstance: productUnitPriceInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice'), productUnitPriceInstance.id])
        redirect(action: "show", id: productUnitPriceInstance.id)
    }

    def show() {
        def productUnitPriceInstance = ProductUnitPrice.get(params.id)
        if (!productUnitPriceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice'), params.id])
            redirect(action: "list")
            return
        }

        [productUnitPriceInstance: productUnitPriceInstance]
    }

    def edit() {
        def productUnitPriceInstance = ProductUnitPrice.get(params.id)
        if (!productUnitPriceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice'), params.id])
            redirect(action: "list")
            return
        }

        [productUnitPriceInstance: productUnitPriceInstance]
    }

    def update() {
        def productUnitPriceInstance = ProductUnitPrice.get(params.id)
        if (!productUnitPriceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (productUnitPriceInstance.version > version) {
                productUnitPriceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice')] as Object[],
                          "Another user has updated this ProductUnitPrice while you were editing")
                render(view: "edit", model: [productUnitPriceInstance: productUnitPriceInstance])
                return
            }
        }

        productUnitPriceInstance.properties = params

        if (!productUnitPriceInstance.save(flush: true)) {
            render(view: "edit", model: [productUnitPriceInstance: productUnitPriceInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice'), productUnitPriceInstance.id])
        redirect(action: "show", id: productUnitPriceInstance.id)
    }

    def delete() {
        def productUnitPriceInstance = ProductUnitPrice.get(params.id)
        if (!productUnitPriceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice'), params.id])
            redirect(action: "list")
            return
        }

        try {
            productUnitPriceInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
