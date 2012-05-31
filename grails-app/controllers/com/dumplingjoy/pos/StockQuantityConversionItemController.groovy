package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

@Secured("isFullyAuthenticated()")
class StockQuantityConversionItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

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
			stockQuantityConversionItemInstance.errors.reject("stockQuantityConversion.containsItem.message", 
				[] as Object[], "stockQuantityConversion.containsItem.message")
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

    def edit() {
		StockQuantityConversion stockQuantityConversionInstance = StockQuantityConversion.get(params["stockQuantityConversion.id"])
		if (!stockQuantityConversionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversion.label'), params.id])
			redirect(controller: "stockQuantityConversion", action: "list")
			return
		}

        def stockQuantityConversionItemInstance = StockQuantityConversionItem.get(params.id)
        if (!stockQuantityConversionItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), params.id])
			redirect(controller: "stockQuantityConversion", action: "list")
            return
        }

        [stockQuantityConversionItemInstance: stockQuantityConversionItemInstance, stockQuantityConversionInstance: stockQuantityConversionInstance]
    }

    def update() {
		StockQuantityConversion stockQuantityConversionInstance = StockQuantityConversion.get(params["stockQuantityConversion.id"])
		if (!stockQuantityConversionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversion.label'), params.id])
			redirect(controller: "stockQuantityConversion", action: "list")
			return
		}

        def stockQuantityConversionItemInstance = StockQuantityConversionItem.get(params.id)
        if (!stockQuantityConversionItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), params.id])
			redirect(controller: "stockQuantityConversion", action: "list")
            return
        }

		if (params.version) {
			def version = params.version.toLong()
			if (stockQuantityConversionItemInstance.version > version) {
				stockQuantityConversionItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem')] as Object[],
						  "Another user has updated this StockQuantityConversionItem while you were editing")
				render(view: "edit", model: [stockQuantityConversionItemInstance: stockQuantityConversionItemInstance, stockQuantityConversionInstance: stockQuantityConversionInstance])
				return
			}
		}

		// detach adjustmentInItemInstance from session to perform external validations first
		// without saving changes immediately
		stockQuantityConversionItemInstance.discard()
		stockQuantityConversionItemInstance.properties = params
		stockQuantityConversionItemInstance.product = Product.get(params["product.id"])
		if (!stockQuantityConversionItemInstance.product) {
			stockQuantityConversionItemInstance.fromUnit = null
			stockQuantityConversionItemInstance.toUnit = null
		}
		
		if (stockQuantityConversionInstance.containsItem(stockQuantityConversionItemInstance)) {
			stockQuantityConversionItemInstance.errors.reject("default.containsItem.message",
				[message(code: 'stockQuantityConversion.label')] as Object[], "default.containsItem.message")
			render(view: "edit", model: [stockQuantityConversionItemInstance: stockQuantityConversionItemInstance, stockQuantityConversionInstance: stockQuantityConversionInstance])
			return
		}

		// re-attach stockQuantityConversionItemInstance to session after external validations pass
		stockQuantityConversionItemInstance = StockQuantityConversionItem.get(params.id)
		stockQuantityConversionItemInstance.properties = params
		stockQuantityConversionItemInstance.product = Product.get(params["product.id"])
		if (!stockQuantityConversionItemInstance.product) {
			stockQuantityConversionItemInstance.fromUnit = null
			stockQuantityConversionItemInstance.toUnit = null
		}

        if (!stockQuantityConversionItemInstance.save(flush: true)) {
            render(view: "edit", model: [stockQuantityConversionItemInstance: stockQuantityConversionItemInstance, stockQuantityConversionInstance: stockQuantityConversionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), stockQuantityConversionItemInstance.id])
        redirect(controller: "stockQuantityConversion", action: "show", id: stockQuantityConversionInstance.id)
    }

    def delete() {
		StockQuantityConversion stockQuantityConversionInstance = StockQuantityConversion.get(params["stockQuantityConversion.id"])
		if (!stockQuantityConversionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversion.label'), params.id])
			redirect(controller: "stockQuantityConversion", action: "list")
			return
		}

        def stockQuantityConversionItemInstance = StockQuantityConversionItem.get(params.id)
        if (!stockQuantityConversionItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), params.id])
			redirect(controller: "stockQuantityConversion", action: "list")
            return
        }

        try {
			stockQuantityConversionInstance.removeFromItems(stockQuantityConversionItemInstance)
			stockQuantityConversionInstance.save(failOnError:true)
            stockQuantityConversionItemInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), params.id])
			redirect(controller: "stockQuantityConversion", action: "show", id: stockQuantityConversionInstance.id)
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
