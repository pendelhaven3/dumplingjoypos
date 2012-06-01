package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class SalesRequisitionItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def create() {
        SalesRequisition salesRequisitionInstance = SalesRequisition.get(params["salesRequisition.id"])
        if (!salesRequisitionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), params.id])
            redirect(controller: "salesRequisition", action: "list")
            return
        }
		
        [salesRequisitionItemInstance: new SalesRequisitionItem(params), salesRequisitionInstance: salesRequisitionInstance]
    }

    def save() {
        SalesRequisition salesRequisitionInstance = SalesRequisition.get(params["salesRequisition.id"])
        if (!salesRequisitionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label'), params.id])
            redirect(controller: "salesRequisition", action: "list")
            return
        }
		
        def salesRequisitionItemInstance = new SalesRequisitionItem(params)
        if (!salesRequisitionItemInstance.save(flush: true)) {
            render(view: "create", model: [salesRequisitionItemInstance: salesRequisitionItemInstance, salesRequisitionInstance: salesRequisitionInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), salesRequisitionItemInstance.id])
        redirect(controller: "salesRequisition", action: "show", id: salesRequisitionInstance.id)
    }

    def edit() {
        SalesRequisition salesRequisitionInstance = SalesRequisition.get(params["salesRequisition.id"])
        if (!salesRequisitionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), params.id])
            redirect(controller: "salesRequisition", action: "list")
            return
        }
		
        def salesRequisitionItemInstance = SalesRequisitionItem.get(params.id)
        if (!salesRequisitionItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), params.id])
            redirect(action: "list")
            return
        }

        [salesRequisitionItemInstance: salesRequisitionItemInstance]
    }

    def update() {
        def salesRequisitionItemInstance = SalesRequisitionItem.get(params.id)
        if (!salesRequisitionItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (salesRequisitionItemInstance.version > version) {
                salesRequisitionItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem')] as Object[],
                          "Another user has updated this SalesRequisitionItem while you were editing")
                render(view: "edit", model: [salesRequisitionItemInstance: salesRequisitionItemInstance])
                return
            }
        }

        salesRequisitionItemInstance.properties = params

        if (!salesRequisitionItemInstance.save(flush: true)) {
            render(view: "edit", model: [salesRequisitionItemInstance: salesRequisitionItemInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), salesRequisitionItemInstance.id])
        redirect(action: "show", id: salesRequisitionItemInstance.id)
    }

    def delete() {
        def salesRequisitionItemInstance = SalesRequisitionItem.get(params.id)
        if (!salesRequisitionItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), params.id])
            redirect(action: "list")
            return
        }

        try {
            salesRequisitionItemInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
