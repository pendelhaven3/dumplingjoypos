package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured("isFullyAuthenticated()")
class PurchaseOrderItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def create() {
		PurchaseOrder purchaseOrderInstance = PurchaseOrder.get(params["purchaseOrder.id"])
		if (!purchaseOrderInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label'), params.id])
			redirect(controller: "purchaseOrder", action: "list")
			return
		}

        [purchaseOrderItemInstance: new PurchaseOrderItem(params), purchaseOrderInstance: purchaseOrderInstance]
    }

    def save() {
		PurchaseOrder purchaseOrderInstance = PurchaseOrder.get(params["purchaseOrder.id"])
		if (!purchaseOrderInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label')])
			redirect(controller: "purchaseOrder", action: "list")
			return
		}

        def purchaseOrderItemInstance = new PurchaseOrderItem(params)
		purchaseOrderItemInstance.product = Product.get(params["product.id"])
		
		if (purchaseOrderInstance.containsItem(purchaseOrderItemInstance)) {
			purchaseOrderItemInstance.errors.reject("default.containsItem.message",
				[message(code: 'purchaseOrder.label')] as Object[], "default.containsItem.message")
			render(view: "create", model: [purchaseOrderItemInstance: purchaseOrderItemInstance, purchaseOrderInstance: purchaseOrderInstance])
			return
		}

		purchaseOrderInstance.addToItems(purchaseOrderItemInstance)

        if (!purchaseOrderItemInstance.save(flush: true)) {
            render(view: "create", model: [purchaseOrderItemInstance: purchaseOrderItemInstance, purchaseOrderInstance: purchaseOrderInstance])
            return
        }

		flash.message = message(code: 'default.added.message', args: [message(code: 'purchaseOrderItem.label')])
        redirect(controller: "purchaseOrder", action: "show", id: purchaseOrderInstance.id)
    }

    def edit() {
		PurchaseOrder purchaseOrderInstance = PurchaseOrder.get(params["purchaseOrder.id"])
		if (!purchaseOrderInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label')])
			redirect(controller: "purchaseOrder", action: "list")
			return
		}

        def purchaseOrderItemInstance = PurchaseOrderItem.get(params.id)
        if (!purchaseOrderItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), params.id])
			redirect(controller: "purchaseOrder", action: "show", id: purchaseOrderInstance.id)
            return
        }

        [purchaseOrderItemInstance: purchaseOrderItemInstance, purchaseOrderInstance: purchaseOrderInstance]
    }

    def update() {
		PurchaseOrder purchaseOrderInstance = PurchaseOrder.get(params["purchaseOrder.id"])
		if (!purchaseOrderInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label')])
			redirect(controller: "purchaseOrder", action: "list")
			return
		}

        def purchaseOrderItemInstance = PurchaseOrderItem.get(params.id)
        if (!purchaseOrderItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), params.id])
			redirect(controller: "purchaseOrder", action: "show", id: purchaseOrderInstance.id)
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (purchaseOrderItemInstance.version > version) {
                purchaseOrderItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem')] as Object[],
                          "Another user has updated this PurchaseOrderItem while you were editing")
                render(view: "edit", model: [purchaseOrderItemInstance: purchaseOrderItemInstance])
                return
            }
        }

		purchaseOrderItemInstance.discard()
		purchaseOrderItemInstance.properties = params
		purchaseOrderItemInstance.product = Product.get(params["product.id"])
		if (!purchaseOrderItemInstance.product) {
			purchaseOrderItemInstance.unit = null
		}

		if (purchaseOrderInstance.containsItem(purchaseOrderItemInstance)) {
			purchaseOrderItemInstance.errors.reject("default.containsItem.message",
					[message(code: 'purchaseOrder.label')] as Object[], "default.containsItem.message")
			render(view: "edit", model: [purchaseOrderItemInstance: purchaseOrderItemInstance, purchaseOrderInstance: purchaseOrderInstance])
			return
		}

		// re-attach purchaseOrderItemInstance to session
		
		purchaseOrderInstance.discard()
		purchaseOrderInstance = PurchaseOrder.get(params["purchaseOrder.id"])
		
		purchaseOrderItemInstance = PurchaseOrderItem.get(params.id)
		purchaseOrderItemInstance.properties = params
		purchaseOrderItemInstance.product = Product.get(params["product.id"])
		if (!purchaseOrderItemInstance.product) {
			purchaseOrderItemInstance.unit = null
		}

        if (!purchaseOrderItemInstance.save(flush: true)) {
            render(view: "edit", model: [purchaseOrderItemInstance: purchaseOrderItemInstance, purchaseOrderInstance: purchaseOrderInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), purchaseOrderItemInstance.id])
        redirect(controller: "purchaseOrder", action: "show", id: purchaseOrderInstance.id)
    }

    def delete() {
		PurchaseOrder purchaseOrderInstance = PurchaseOrder.get(params["purchaseOrder.id"])
		if (!purchaseOrderInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label')])
			redirect(controller: "purchaseOrder", action: "list")
			return
		}

        def purchaseOrderItemInstance = PurchaseOrderItem.get(params.id)
        if (!purchaseOrderItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), params.id])
			redirect(controller: "purchaseOrder", action: "show", id: purchaseOrderInstance.id)
            return
        }

        try {
			purchaseOrderInstance.removeFromItems(purchaseOrderItemInstance)
			purchaseOrderInstance.save(failOnError: true)
            purchaseOrderItemInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), params.id])
			redirect(controller: "purchaseOrder", action: "show", id: purchaseOrderInstance.id)
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), params.id])
        redirect(controller: "purchaseOrder", action: "show", id: purchaseOrderInstance.id)
        }
    }
	
}
