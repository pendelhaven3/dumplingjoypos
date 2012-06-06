package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured("isFullyAuthenticated()")
class PurchaseOrderItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [purchaseOrderItemInstanceList: PurchaseOrderItem.list(params), purchaseOrderItemInstanceTotal: PurchaseOrderItem.count()]
    }

    def create() {
        [purchaseOrderItemInstance: new PurchaseOrderItem(params)]
    }

    def save() {
        def purchaseOrderItemInstance = new PurchaseOrderItem(params)
        if (!purchaseOrderItemInstance.save(flush: true)) {
            render(view: "create", model: [purchaseOrderItemInstance: purchaseOrderItemInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), purchaseOrderItemInstance.id])
        redirect(action: "show", id: purchaseOrderItemInstance.id)
    }

    def show() {
        def purchaseOrderItemInstance = PurchaseOrderItem.get(params.id)
        if (!purchaseOrderItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), params.id])
            redirect(action: "list")
            return
        }

        [purchaseOrderItemInstance: purchaseOrderItemInstance]
    }

    def edit() {
        def purchaseOrderItemInstance = PurchaseOrderItem.get(params.id)
        if (!purchaseOrderItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), params.id])
            redirect(action: "list")
            return
        }

        [purchaseOrderItemInstance: purchaseOrderItemInstance]
    }

    def update() {
        def purchaseOrderItemInstance = PurchaseOrderItem.get(params.id)
        if (!purchaseOrderItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), params.id])
            redirect(action: "list")
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

        purchaseOrderItemInstance.properties = params

        if (!purchaseOrderItemInstance.save(flush: true)) {
            render(view: "edit", model: [purchaseOrderItemInstance: purchaseOrderItemInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), purchaseOrderItemInstance.id])
        redirect(action: "show", id: purchaseOrderItemInstance.id)
    }

    def delete() {
        def purchaseOrderItemInstance = PurchaseOrderItem.get(params.id)
        if (!purchaseOrderItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), params.id])
            redirect(action: "list")
            return
        }

        try {
            purchaseOrderItemInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
