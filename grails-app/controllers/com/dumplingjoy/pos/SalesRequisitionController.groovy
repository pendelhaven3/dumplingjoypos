package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class SalesRequisitionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: "salesRequisitionNumber"
		params.order = params.order ?: "desc"
		
		def salesRequisitionInstanceList = SalesRequisition.findAllByPosted(false, params)
        [salesRequisitionInstanceList: salesRequisitionInstanceList, salesRequisitionInstanceTotal: SalesRequisition.count()]
    }

    def create() {
        [salesRequisitionInstance: new SalesRequisition(params)]
    }

    def save() {
        def salesRequisitionInstance = new SalesRequisition(params)
		salesRequisitionInstance.salesRequisitionNumber = SalesRequisitionSequenceNumber.getNextValue()
        if (!salesRequisitionInstance.save(flush: true)) {
            render(view: "create", model: [salesRequisitionInstance: salesRequisitionInstance])
            return
        }
		SalesRequisitionSequenceNumber.increment()
		flash.message = message(code: 'default.created.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), salesRequisitionInstance.id])
        redirect(action: "show", id: salesRequisitionInstance.id)
    }

    def show() {
        def salesRequisitionInstance = SalesRequisition.get(params.id)
        if (!salesRequisitionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), params.id])
            redirect(action: "list")
            return
        }

        [salesRequisitionInstance: salesRequisitionInstance]
    }

    def edit() {
        def salesRequisitionInstance = SalesRequisition.get(params.id)
        if (!salesRequisitionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), params.id])
            redirect(action: "list")
            return
        }

        [salesRequisitionInstance: salesRequisitionInstance]
    }

    def update() {
        def salesRequisitionInstance = SalesRequisition.get(params.id)
        if (!salesRequisitionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (salesRequisitionInstance.version > version) {
                salesRequisitionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'salesRequisition.label', default: 'SalesRequisition')] as Object[],
                          "Another user has updated this SalesRequisition while you were editing")
                render(view: "edit", model: [salesRequisitionInstance: salesRequisitionInstance])
                return
            }
        }

        salesRequisitionInstance.properties = params
		salesRequisitionInstance.customer.discard() // I have no idea why I have to do this to make it work T__T
		salesRequisitionInstance.customer = Customer.get(params["customer.id"])
		salesRequisitionInstance.pricingScheme.discard() // I have no idea why I have to do this to make it work T__T
		salesRequisitionInstance.pricingScheme = PricingScheme.get(params["pricingScheme.id"])
		
        if (!salesRequisitionInstance.save(flush: true)) {
            render(view: "edit", model: [salesRequisitionInstance: salesRequisitionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), salesRequisitionInstance.id])
        redirect(action: "show", id: salesRequisitionInstance.id)
    }

    def delete() {
        def salesRequisitionInstance = SalesRequisition.get(params.id)
        if (!salesRequisitionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), params.id])
            redirect(action: "list")
            return
        }

        try {
            salesRequisitionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def postSalesRequisition() {
		SalesRequisition salesRequisitionInstance = SalesRequisition.get(params.id)
		if (!salesRequisitionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label'), params.id])
			redirect(action: "list")
			return
		}
		
		// force hibernate to load these properties
		salesRequisitionInstance.customer.name
		salesRequisitionInstance.pricingScheme.description
		
		if (!salesRequisitionInstance.post()) {
			render(view: "show", model: [salesRequisitionInstance: salesRequisitionInstance])
			return
		}
		
		flash.message = message(code: 'default.posted.message', args: [message(code: 'salesRequisition.label')])
		redirect(action: "show", id: params.id)
	}

}
