package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class StockQuantityConversionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: "stockQuantityConversionNumber"
		params.order = params.order ?: "desc"
        [stockQuantityConversionInstanceList: StockQuantityConversion.list(params), stockQuantityConversionInstanceTotal: StockQuantityConversion.count()]
    }

    def create() {
        [stockQuantityConversionInstance: new StockQuantityConversion(params)]
    }

    def save() {
        def stockQuantityConversionInstance = new StockQuantityConversion(params)
		stockQuantityConversionInstance.stockQuantityConversionNumber = StockQuantityConversionSequenceNumber.getNextValue()
        if (!stockQuantityConversionInstance.save(flush: true)) {
            render(view: "create", model: [stockQuantityConversionInstance: stockQuantityConversionInstance])
            return
        }
		StockQuantityConversionSequenceNumber.increment()

		flash.message = message(code: 'default.created.message', args: [message(code: 'stockQuantityConversion.label', default: 'StockQuantityConversion'), stockQuantityConversionInstance.id])
        redirect(action: "show", id: stockQuantityConversionInstance.id)
    }

    def show() {
        def stockQuantityConversionInstance = StockQuantityConversion.get(params.id)
        if (!stockQuantityConversionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversion.label', default: 'StockQuantityConversion'), params.id])
            redirect(action: "list")
            return
        }

        [stockQuantityConversionInstance: stockQuantityConversionInstance]
    }

    def edit() {
        def stockQuantityConversionInstance = StockQuantityConversion.get(params.id)
        if (!stockQuantityConversionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversion.label', default: 'StockQuantityConversion'), params.id])
            redirect(action: "list")
            return
        }

        [stockQuantityConversionInstance: stockQuantityConversionInstance]
    }

    def update() {
        def stockQuantityConversionInstance = StockQuantityConversion.get(params.id)
        if (!stockQuantityConversionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversion.label', default: 'StockQuantityConversion'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (stockQuantityConversionInstance.version > version) {
                stockQuantityConversionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'stockQuantityConversion.label', default: 'StockQuantityConversion')] as Object[],
                          "Another user has updated this StockQuantityConversion while you were editing")
                render(view: "edit", model: [stockQuantityConversionInstance: stockQuantityConversionInstance])
                return
            }
        }

        stockQuantityConversionInstance.properties = params

        if (!stockQuantityConversionInstance.save(flush: true)) {
            render(view: "edit", model: [stockQuantityConversionInstance: stockQuantityConversionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'stockQuantityConversion.label', default: 'StockQuantityConversion'), stockQuantityConversionInstance.id])
        redirect(action: "show", id: stockQuantityConversionInstance.id)
    }

    def delete() {
        def stockQuantityConversionInstance = StockQuantityConversion.get(params.id)
        if (!stockQuantityConversionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversion.label', default: 'StockQuantityConversion'), params.id])
            redirect(action: "list")
            return
        }

        try {
            stockQuantityConversionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'stockQuantityConversion.label', default: 'StockQuantityConversion'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stockQuantityConversion.label', default: 'StockQuantityConversion'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def postStockQuantityConversion() {
		StockQuantityConversion stockQuantityConversionInstance = StockQuantityConversion.get(params.id)
		if (!stockQuantityConversionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockQuantityConversion.label'), params.id])
			redirect(action: "list")
			return
		}
		
		if (!stockQuantityConversionInstance.post()) {
            render(view: "show", model: [stockQuantityConversionInstance: stockQuantityConversionInstance])
			return
		}
		
		flash.message = message(code: 'default.posted.message', args: [message(code: 'stockQuantityConversion.label')])
		redirect(action: "show", id: params.id)
	}
	
}
