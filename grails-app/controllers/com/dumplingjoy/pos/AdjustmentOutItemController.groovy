package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured("isFullyAuthenticated()")
class AdjustmentOutItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def create() {
		AdjustmentOut adjustmentOutInstance = AdjustmentOut.get(params["adjustmentOut.id"])
		if (!adjustmentOutInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOut.label'), params.id])
			redirect(controller: "adjustmentOut", action: "list")
			return
		}
		
        [adjustmentOutItemInstance: new AdjustmentOutItem(params), adjustmentOutInstance: adjustmentOutInstance]
    }

    def save() {
		AdjustmentOut adjustmentOutInstance = AdjustmentOut.get(params["adjustmentOut.id"])
		if (!adjustmentOutInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOut.label'), params.id])
			redirect(controller: "adjustmentOut", action: "list")
			return
		}

        def adjustmentOutItemInstance = new AdjustmentOutItem(params)
		adjustmentOutItemInstance.product = Product.get(params["product.id"])
		
		if (adjustmentOutInstance.containsItem(adjustmentOutItemInstance)) {
			adjustmentOutItemInstance.errors.reject("default.containsItem.message",
				[message(code: 'adjustmentOut.label')] as Object[], "default.containsItem.message")
			render(view: "create", model: [adjustmentOutItemInstance: adjustmentOutItemInstance, adjustmentOutInstance: adjustmentOutInstance])
			return
		}

		adjustmentOutInstance.addToItems(adjustmentOutItemInstance)

        if (!adjustmentOutItemInstance.save(flush: true)) {
            render(view: "create", model: [adjustmentOutItemInstance: adjustmentOutItemInstance, adjustmentOutInstance: adjustmentOutInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), adjustmentOutItemInstance.id])
        redirect(controller: "adjustmentOut", action: "show", id: adjustmentOutInstance.id)
    }

    def edit() {
		AdjustmentOut adjustmentOutInstance = AdjustmentOut.get(params["adjustmentOut.id"])
		if (!adjustmentOutInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOut.label'), params.id])
			redirect(controller: "adjustmentOut", action: "list")
			return
		}

        def adjustmentOutItemInstance = AdjustmentOutItem.get(params.id)
        if (!adjustmentOutItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), params.id])
			redirect(controller: "adjustmentOut", action: "show", id: adjustmentOutInstance.id)
            return
        }

        [adjustmentOutItemInstance: adjustmentOutItemInstance, adjustmentOutInstance: adjustmentOutInstance]
    }

    def update() {
		AdjustmentOut adjustmentOutInstance = AdjustmentOut.get(params["adjustmentOut.id"])
		if (!adjustmentOutInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOut.label'), params.id])
			redirect(controller: "adjustmentOut", action: "list")
			return
		}

        def adjustmentOutItemInstance = AdjustmentOutItem.get(params.id)
        if (!adjustmentOutItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), params.id])
			redirect(controller: "adjustmentOut", action: "show", id: adjustmentOutInstance.id)
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (adjustmentOutItemInstance.version > version) {
                adjustmentOutItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem')] as Object[],
                          "Another user has updated this AdjustmentOutItem while you were editing")
                render(view: "edit", model: [adjustmentOutItemInstance: adjustmentOutItemInstance, adjustmentOutInstance: adjustmentOutInstance])
                return
            }
        }

		adjustmentOutItemInstance.discard()
        adjustmentOutItemInstance.properties = params
		adjustmentOutItemInstance.product = Product.get(params["product.id"])
		if (!adjustmentOutItemInstance.product) {
			adjustmentOutItemInstance.unit = null
		}

		if (adjustmentOutInstance.containsItem(adjustmentOutItemInstance)) {
			adjustmentOutItemInstance.errors.reject("default.containsItem.message",
				[message(code: 'adjustmentOut.label')] as Object[], "default.containsItem.message")
			render(view: "edit", model: [adjustmentOutItemInstance: adjustmentOutItemInstance, adjustmentOutInstance: adjustmentOutInstance])
			return
		}

		// re-attach adjustmentOutItemInstance to session
        adjustmentOutItemInstance = AdjustmentOutItem.get(params.id)
		adjustmentOutItemInstance.properties = params
		adjustmentOutItemInstance.product = Product.get(params["product.id"])
		if (!adjustmentOutItemInstance.product) {
			adjustmentOutItemInstance.unit = null
		}
		
		adjustmentOutInstance.discard()

        if (!adjustmentOutItemInstance.save(flush: true)) {
            render(view: "edit", model: [adjustmentOutItemInstance: adjustmentOutItemInstance, adjustmentOutInstance: adjustmentOutInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'adjustmentOutItem.label'), adjustmentOutItemInstance.id])
        redirect(controller: "adjustmentOut", action: "show", id: adjustmentOutInstance.id)
    }

    def delete() {
		AdjustmentOut adjustmentOutInstance = AdjustmentOut.get(params["adjustmentOut.id"])
		if (!adjustmentOutInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOut.label'), params.id])
			redirect(controller: "adjustmentOut", action: "list")
			return
		}

        def adjustmentOutItemInstance = AdjustmentOutItem.get(params.id)
        if (!adjustmentOutItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), params.id])
			redirect(controller: "adjustmentOut", action: "show", id: adjustmentOutInstance.id)
            return
        }

        try {
			adjustmentOutInstance.removeFromItems(adjustmentOutItemInstance)
			adjustmentOutInstance.save(failOnError:true)
            adjustmentOutItemInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), params.id])
            redirect(controller: "adjustmentOut", action: "show", id: adjustmentOutInstance.id)
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem'), params.id])
			redirect(controller: "adjustmentOut", action: "show", id: adjustmentOutInstance.id)
        }
    }
}
