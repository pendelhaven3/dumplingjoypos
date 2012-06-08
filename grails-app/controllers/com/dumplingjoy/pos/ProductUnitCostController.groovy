package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class ProductUnitCostController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [productUnitCostInstanceList: ProductUnitCost.list(params), productUnitCostInstanceTotal: ProductUnitCost.count()]
    }

    def create() {
        [productUnitCostInstance: new ProductUnitCost(params)]
    }

    def save() {
        def productUnitCostInstance = new ProductUnitCost(params)
        if (!productUnitCostInstance.save(flush: true)) {
            render(view: "create", model: [productUnitCostInstance: productUnitCostInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'productUnitCost.label', default: 'ProductUnitCost'), productUnitCostInstance.id])
        redirect(action: "show", id: productUnitCostInstance.id)
    }

    def show() {
        def productUnitCostInstance = ProductUnitCost.get(params.id)
        if (!productUnitCostInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'productUnitCost.label', default: 'ProductUnitCost'), params.id])
            redirect(action: "list")
            return
        }

        [productUnitCostInstance: productUnitCostInstance]
    }

    def edit() {
        def productUnitCostInstance = ProductUnitCost.get(params.id)
        if (!productUnitCostInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'productUnitCost.label', default: 'ProductUnitCost'), params.id])
            redirect(action: "list")
            return
        }

        [productUnitCostInstance: productUnitCostInstance]
    }

    def update() {
        def productUnitCostInstance = ProductUnitCost.get(params.id)
        if (!productUnitCostInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'productUnitCost.label', default: 'ProductUnitCost'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (productUnitCostInstance.version > version) {
                productUnitCostInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'productUnitCost.label', default: 'ProductUnitCost')] as Object[],
                          "Another user has updated this ProductUnitCost while you were editing")
                render(view: "edit", model: [productUnitCostInstance: productUnitCostInstance])
                return
            }
        }

        productUnitCostInstance.properties = params

        if (!productUnitCostInstance.save(flush: true)) {
            render(view: "edit", model: [productUnitCostInstance: productUnitCostInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'productUnitCost.label', default: 'ProductUnitCost'), productUnitCostInstance.id])
        redirect(action: "show", id: productUnitCostInstance.id)
    }

    def delete() {
        def productUnitCostInstance = ProductUnitCost.get(params.id)
        if (!productUnitCostInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'productUnitCost.label', default: 'ProductUnitCost'), params.id])
            redirect(action: "list")
            return
        }

        try {
            productUnitCostInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'productUnitCost.label', default: 'ProductUnitCost'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'productUnitCost.label', default: 'ProductUnitCost'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
