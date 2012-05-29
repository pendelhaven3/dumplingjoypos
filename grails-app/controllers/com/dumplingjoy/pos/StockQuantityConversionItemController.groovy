package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class StockQuantityConversionItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [stockQuantityConversionItemInstanceList: StockQuantityConversionItem.list(params), stockQuantityConversionItemInstanceTotal: StockQuantityConversionItem.count()]
    }

    def create() {
		StockQuantityConversion stockQuantityConversionInstance = StockQuantityConversion.get(params["stockQuantityConversion.id"])
		if (!stockQuantityConversionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversion.label'), params.id])
			redirect(controller: "stockQuantityConversion", action: "list")
			return
		}

        [stockQuantityConversionItemInstance: new StockQuantityConversionItem(params), stockQuantityConversionInstance: stockQuantityConversionInstance]
    }

    def save() {
		StockQuantityConversion stockQuantityConversionInstance = StockQuantityConversion.get(params["stockQuantityConversion.id"])
		if (!stockQuantityConversionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversion.label'), params.id])
			redirect(controller: "stockQuantityConversion", action: "list")
			return
		}

        StockQuantityConversionItem stockQuantityConversionItemInstance = new StockQuantityConversionItem(params)
		stockQuantityConversionItemInstance.product = Product.get(params["product.id"])
		
		if (stockQuantityConversionInstance.containsItem(stockQuantityConversionItemInstance)) {
			stockQuantityConversionItemInstance.errors.reject("default.containsItem.message", 
				[message(code: 'stockQuantityConversionItem.label')] as Object[], "default.containsItem.message")
            render(view: "create", model: [stockQuantityConversionItemInstance: stockQuantityConversionItemInstance, stockQuantityConversionInstance: stockQuantityConversionInstance])
            return
		}
			
		stockQuantityConversionInstance.addToItems(stockQuantityConversionItemInstance)
		
        if (!stockQuantityConversionItemInstance.save(flush: true)) {
            render(view: "create", model: [
				stockQuantityConversionItemInstance: stockQuantityConversionItemInstance,
				stockQuantityConversionInstance: stockQuantityConversionInstance
			])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), stockQuantityConversionItemInstance.id])
        redirect(controller: "stockQuantityConversion", action: "show", id: stockQuantityConversionInstance.id)
    }

    def show() {
        def stockQuantityConversionItemInstance = StockQuantityConversionItem.get(params.id)
        if (!stockQuantityConversionItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), params.id])
            redirect(action: "list")
            return
        }

        [stockQuantityConversionItemInstance: stockQuantityConversionItemInstance]
    }

    def edit() {
        def stockQuantityConversionItemInstance = StockQuantityConversionItem.get(params.id)
        if (!stockQuantityConversionItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), params.id])
            redirect(action: "list")
            return
        }

        [stockQuantityConversionItemInstance: stockQuantityConversionItemInstance]
    }

    def update() {
        def stockQuantityConversionItemInstance = StockQuantityConversionItem.get(params.id)
        if (!stockQuantityConversionItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (stockQuantityConversionItemInstance.version > version) {
                stockQuantityConversionItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem')] as Object[],
                          "Another user has updated this StockQuantityConversionItem while you were editing")
                render(view: "edit", model: [stockQuantityConversionItemInstance: stockQuantityConversionItemInstance])
                return
            }
        }

        stockQuantityConversionItemInstance.properties = params

        if (!stockQuantityConversionItemInstance.save(flush: true)) {
            render(view: "edit", model: [stockQuantityConversionItemInstance: stockQuantityConversionItemInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), stockQuantityConversionItemInstance.id])
        redirect(action: "show", id: stockQuantityConversionItemInstance.id)
    }

    def delete() {
        def stockQuantityConversionItemInstance = StockQuantityConversionItem.get(params.id)
        if (!stockQuantityConversionItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), params.id])
            redirect(action: "list")
            return
        }

        try {
            stockQuantityConversionItemInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
