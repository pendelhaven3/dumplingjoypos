package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class AdjustmentOutItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [adjustmentOutItemInstanceList: AdjustmentOutItem.list(params), adjustmentOutItemInstanceTotal: AdjustmentOutItem.count()]
    }

    def create() {
		def adjustmentOutInstance = AdjustmentOut.get(params.adjustmentOutId)
		if (!adjustmentOutInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOut.label'), params.id])
			redirect(controller: "adjustmentOut", action: "list")
			return
		}
		
        [adjustmentOutItemInstance: new AdjustmentOutItem(params), adjustmentOutInstance: adjustmentOutInstance]
    }

    def save() {
		def adjustmentOutInstance = AdjustmentOut.get(params.adjustmentOutId)
		if (!adjustmentOutInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOut.label'), params.id])
			redirect(controller: "adjustmentOut", action: "list")
			return
		}

        def adjustmentOutItemInstance = new AdjustmentOutItem(params)
		
		adjustmentOutItemInstance.product = Product.findByCode(params.productCode)
		adjustmentOutInstance.addToItems(adjustmentOutItemInstance)

        if (!adjustmentOutItemInstance.save(flush: true)) {
            render(view: "create", model: [adjustmentOutItemInstance: adjustmentOutItemInstance, adjustmentOutInstance: adjustmentOutInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), adjustmentOutItemInstance.id])
        redirect(controller: "adjustmentOut", action: "show", id: adjustmentOutInstance.id)
    }

    def show() {
        def adjustmentOutItemInstance = AdjustmentOutItem.get(params.id)
        if (!adjustmentOutItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), params.id])
            redirect(action: "list")
            return
        }

        [adjustmentOutItemInstance: adjustmentOutItemInstance]
    }

    def edit() {
        def adjustmentOutItemInstance = AdjustmentOutItem.get(params.id)
        if (!adjustmentOutItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), params.id])
            redirect(action: "list")
            return
        }

        [adjustmentOutItemInstance: adjustmentOutItemInstance]
    }

    def update() {
        def adjustmentOutItemInstance = AdjustmentOutItem.get(params.id)
        if (!adjustmentOutItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (adjustmentOutItemInstance.version > version) {
                adjustmentOutItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem')] as Object[],
                          "Another user has updated this AdjustmentOutItem while you were editing")
                render(view: "edit", model: [adjustmentOutItemInstance: adjustmentOutItemInstance])
                return
            }
        }

        adjustmentOutItemInstance.properties = params

        if (!adjustmentOutItemInstance.save(flush: true)) {
            render(view: "edit", model: [adjustmentOutItemInstance: adjustmentOutItemInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), adjustmentOutItemInstance.id])
        redirect(action: "show", id: adjustmentOutItemInstance.id)
    }

    def delete() {
        def adjustmentOutItemInstance = AdjustmentOutItem.get(params.id)
        if (!adjustmentOutItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), params.id])
            redirect(action: "list")
            return
        }

        try {
            adjustmentOutItemInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
