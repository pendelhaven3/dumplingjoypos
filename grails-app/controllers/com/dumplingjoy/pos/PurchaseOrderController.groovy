package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured("isFullyAuthenticated()")
class PurchaseOrderController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: "purchaseOrderNumber"
		params.order = params.order ?: "desc"
		
        [purchaseOrderInstanceList: PurchaseOrder.list(params), purchaseOrderInstanceTotal: PurchaseOrder.count()]
    }

    def create() {
        [purchaseOrderInstance: new PurchaseOrder(params)]
    }

    def save() {
        def purchaseOrderInstance = new PurchaseOrder(params)
		purchaseOrderInstance.purchaseOrderNumber = PurchaseOrderSequenceNumber.getNextValue()
        if (!purchaseOrderInstance.save(flush: true)) {
            render(view: "create", model: [purchaseOrderInstance: purchaseOrderInstance])
            return
        }
		PurchaseOrderSequenceNumber.increment()

		flash.message = message(code: 'default.created.message', args: [message(code: 'purchaseOrder.label', default: 'PurchaseOrder'), purchaseOrderInstance.id])
        redirect(action: "show", id: purchaseOrderInstance.id)
    }

    def show() {
        def purchaseOrderInstance = PurchaseOrder.get(params.id)
        if (!purchaseOrderInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label', default: 'PurchaseOrder'), params.id])
            redirect(action: "list")
            return
        }

        [purchaseOrderInstance: purchaseOrderInstance]
    }

    def edit() {
        def purchaseOrderInstance = PurchaseOrder.get(params.id)
        if (!purchaseOrderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label', default: 'PurchaseOrder'), params.id])
            redirect(action: "list")
            return
        }

        [purchaseOrderInstance: purchaseOrderInstance]
    }

    def update() {
        def purchaseOrderInstance = PurchaseOrder.get(params.id)
        if (!purchaseOrderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label', default: 'PurchaseOrder'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (purchaseOrderInstance.version > version) {
                purchaseOrderInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'purchaseOrder.label', default: 'PurchaseOrder')] as Object[],
                          "Another user has updated this PurchaseOrder while you were editing")
                render(view: "edit", model: [purchaseOrderInstance: purchaseOrderInstance])
                return
            }
        }

        purchaseOrderInstance.properties = params
		purchaseOrderInstance.supplier.discard() // I have no idea why I have to do this to make it work T__T
		purchaseOrderInstance.supplier = Supplier.get(params["supplier.id"])
		purchaseOrderInstance.terms.discard() // I have no idea why I have to do this to make it work T__T
		purchaseOrderInstance.terms = PaymentTerms.get(params["terms.id"])

        if (!purchaseOrderInstance.save(flush: true)) {
            render(view: "edit", model: [purchaseOrderInstance: purchaseOrderInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'purchaseOrder.label', default: 'PurchaseOrder'), purchaseOrderInstance.id])
        redirect(action: "show", id: purchaseOrderInstance.id)
    }

    def delete() {
        def purchaseOrderInstance = PurchaseOrder.get(params.id)
        if (!purchaseOrderInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label', default: 'PurchaseOrder'), params.id])
            redirect(action: "list")
            return
        }

        try {
            purchaseOrderInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'purchaseOrder.label', default: 'PurchaseOrder'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'purchaseOrder.label', default: 'PurchaseOrder'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def markAsOrdered() {
		PurchaseOrder purchaseOrderInstance = PurchaseOrder.get(params.id)
		if (!purchaseOrderInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label')])
			redirect(action: "list")
			return
		}
		
		if (!purchaseOrderInstance.markAsOrdered()) {
			render(view: "show", model: [purchaseOrderInstance: purchaseOrderInstance])
			return
		}
		
		flash.message = message(code: 'purchaseOrder.markAsOrdered.message')
		redirect(action: "show", id: params.id)
	}

	def postPurchaseOrder() {
		PurchaseOrder purchaseOrderInstance = PurchaseOrder.get(params.id)
		if (!purchaseOrderInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label'), params.id])
			redirect(action: "list")
			return
		}
		
		purchaseOrderInstance.supplier.refresh()
		purchaseOrderInstance.terms.refresh()
		purchaseOrderInstance.items.each { it.product.refresh() }

		if (!purchaseOrderInstance.post()) {
			render(view: "show", model: [purchaseOrderInstance: purchaseOrderInstance])
			return
		}
		
		flash.message = message(code: 'default.posted.message', args: [message(code: 'purchaseOrder.label')])
		redirect(controller: "receivingReceipt", action: "show", id: purchaseOrderInstance.relatedReceivingReceipt.id)
	}

}
