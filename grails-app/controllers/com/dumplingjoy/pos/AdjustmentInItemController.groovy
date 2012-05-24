package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured("isFullyAuthenticated()")
class AdjustmentInItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def create() {
		AdjustmentIn adjustmentInInstance = AdjustmentIn.get(params["adjustmentIn.id"])
		if (!adjustmentInInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentIn.label'), params.id])
			redirect(controller: "adjustmentIn", action: "list")
			return
		}
		
        [adjustmentInItemInstance: new AdjustmentInItem(params), adjustmentInInstance: adjustmentInInstance]
    }

    def save() {
		AdjustmentIn adjustmentInInstance = AdjustmentIn.get(params["adjustmentIn.id"])
		if (!adjustmentInInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentIn.label'), params.id])
			redirect(controller: "adjustmentIn", action: "list")
			return
		}
		
        AdjustmentInItem adjustmentInItemInstance = new AdjustmentInItem(params)
		adjustmentInItemInstance.product = Product.get(params["product.id"])
		
		if (adjustmentInInstance.containsItem(adjustmentInItemInstance)) {
			adjustmentInItemInstance.errors.reject("adjustmentIn.containsItem.message", 
				[message(code: 'adjustmentIn.label')] as Object[], "adjustmentIn.containsItem.message")
            render(view: "create", model: [adjustmentInItemInstance: adjustmentInItemInstance, adjustmentInInstance: adjustmentInInstance])
            return
		}
		
		adjustmentInInstance.addToItems(adjustmentInItemInstance)
		
        if (!adjustmentInItemInstance.save(flush: true)) {
            render(view: "create", model: [adjustmentInItemInstance: adjustmentInItemInstance, adjustmentInInstance: adjustmentInInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem'), adjustmentInItemInstance.id])
        redirect(controller: "adjustmentIn", action: "show", id: adjustmentInInstance.id)
    }

    def edit() {
		AdjustmentIn adjustmentInInstance = AdjustmentIn.get(params["adjustmentIn.id"])
		if (!adjustmentInInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentIn.label'), params.id])
			redirect(controller: "adjustmentIn", action: "list")
			return
		}

        def adjustmentInItemInstance = AdjustmentInItem.get(params.id)
        if (!adjustmentInItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem'), params.id])
            redirect(action: "list")
            return
        }

        [adjustmentInItemInstance: adjustmentInItemInstance, adjustmentInInstance: adjustmentInInstance]
    }

    def update() {
		AdjustmentIn adjustmentInInstance = AdjustmentIn.get(params["adjustmentIn.id"])
		if (!adjustmentInInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentIn.label'), params.id])
			redirect(controller: "adjustmentIn", action: "list")
			return
		}

        AdjustmentInItem adjustmentInItemInstance = AdjustmentInItem.get(params.id)
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

		adjustmentInItemInstance.discard()
        adjustmentInItemInstance.properties = params
		
		if (adjustmentInInstance.containsItem(adjustmentInItemInstance)) {
			adjustmentInItemInstance.errors.reject("adjustmentIn.containsItem.message",
				[message(code: 'adjustmentIn.label')] as Object[], "adjustmentIn.containsItem.message")
			render(view: "edit", model: [adjustmentInItemInstance: adjustmentInItemInstance, adjustmentInInstance: adjustmentInInstance])
			return
		}

        adjustmentInItemInstance = AdjustmentInItem.get(params.id)
		adjustmentInItemInstance.properties = params
		
        if (!adjustmentInItemInstance.save(flush: true)) {
            render(view: "edit", model: [adjustmentInItemInstance: adjustmentInItemInstance, adjustmentInInstance: adjustmentInInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'adjustmentInItem.label'), adjustmentInItemInstance.id])
        redirect(controller: "adjustmentIn", action: "show", id: adjustmentInInstance.id)
    }

    def delete() {
		AdjustmentIn adjustmentInInstance = AdjustmentIn.get(params["adjustmentIn.id"])
		if (!adjustmentInInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentIn.label'), params.id])
			redirect(controller: "adjustmentIn", action: "list")
			return
		}
		
        def adjustmentInItemInstance = AdjustmentInItem.get(params.id)
        if (!adjustmentInItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem'), params.id])
            redirect(controller: "adjustmentIn", action: "list")
            return
        }

        try {
			adjustmentInInstance.removeFromItems(adjustmentInItemInstance)
			adjustmentInInstance.save(failOnError:true)
            adjustmentInItemInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'adjustmentInItem.label'), params.id])
            redirect(controller: "adjustmentIn", action: "show", id: adjustmentInInstance.id)
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
