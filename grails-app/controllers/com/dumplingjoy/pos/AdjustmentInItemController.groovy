package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class AdjustmentInItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [adjustmentInItemInstanceList: AdjustmentInItem.list(params), adjustmentInItemInstanceTotal: AdjustmentInItem.count()]
    }

    def create() {
        [adjustmentInItemInstance: new AdjustmentInItem(params)]
    }

    def save() {
        def adjustmentInItemInstance = new AdjustmentInItem(params)
        if (!adjustmentInItemInstance.save(flush: true)) {
            render(view: "create", model: [adjustmentInItemInstance: adjustmentInItemInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem'), adjustmentInItemInstance.id])
        redirect(action: "show", id: adjustmentInItemInstance.id)
    }

    def show() {
        def adjustmentInItemInstance = AdjustmentInItem.get(params.id)
        if (!adjustmentInItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem'), params.id])
            redirect(action: "list")
            return
        }

        [adjustmentInItemInstance: adjustmentInItemInstance]
    }

    def edit() {
        def adjustmentInItemInstance = AdjustmentInItem.get(params.id)
        if (!adjustmentInItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem'), params.id])
            redirect(action: "list")
            return
        }

        [adjustmentInItemInstance: adjustmentInItemInstance]
    }

    def update() {
        def adjustmentInItemInstance = AdjustmentInItem.get(params.id)
        if (!adjustmentInItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (adjustmentInItemInstance.version > version) {
                adjustmentInItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem')] as Object[],
                          "Another user has updated this AdjustmentInItem while you were editing")
                render(view: "edit", model: [adjustmentInItemInstance: adjustmentInItemInstance])
                return
            }
        }

        adjustmentInItemInstance.properties = params

        if (!adjustmentInItemInstance.save(flush: true)) {
            render(view: "edit", model: [adjustmentInItemInstance: adjustmentInItemInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem'), adjustmentInItemInstance.id])
        redirect(action: "show", id: adjustmentInItemInstance.id)
    }

    def delete() {
        def adjustmentInItemInstance = AdjustmentInItem.get(params.id)
        if (!adjustmentInItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem'), params.id])
            redirect(action: "list")
            return
        }

        try {
            adjustmentInItemInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
