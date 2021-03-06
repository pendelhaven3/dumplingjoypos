package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured("isFullyAuthenticated()")
class UnitConversionController {

    static allowedMethods = [update: "POST", delete: "POST"]

	def productUnitPriceService
	
    def edit() {
		def productInstance = Product.get(params.productId)
        if (!productInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label'), params.id])
            redirect(controller: "product", action: "list")
            return
        }
		
        def unitConversionInstance = UnitConversion.get(params.id)
        if (!unitConversionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unitConversion.label'), params.id])
            redirect(controller: "product", action: "list")
            return
        }

        [unitConversionInstance: unitConversionInstance, productInstance: productInstance]
    }

    def update() {
		def productInstance = Product.get(params.productId)
		if (!productInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label'), params.id])
            redirect(controller: "product", action: "list")
            return
		}
		
        def unitConversionInstance = UnitConversion.get(params.id)
        if (!unitConversionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unitConversion.label', default: 'UnitConversion'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (unitConversionInstance.version > version) {
                unitConversionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'unitConversion.label', default: 'UnitConversion')] as Object[],
                          "Another user has updated this UnitConversion while you were editing")
                render(view: "edit", model: [unitConversionInstance: unitConversionInstance])
                return
            }
        }

        unitConversionInstance.properties = params

        if (!unitConversionInstance.save(flush: true)) {
            render(view: "edit", model: [unitConversionInstance: unitConversionInstance, productInstance: productInstance])
            return
        }
		
		productUnitPriceService.updateUnitPrices(productInstance)

		flash.message = message(code: 'default.updated.message', args: [message(code: 'unitConversion.label', default: 'UnitConversion'), unitConversionInstance.id])
        redirect(controller: "product", action: "show", id: productInstance.id)
    }

    def delete() {
        def unitConversionInstance = UnitConversion.get(params.id)
        if (!unitConversionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'unitConversion.label', default: 'UnitConversion'), params.id])
            redirect(action: "list")
            return
        }

        try {
            unitConversionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'unitConversion.label', default: 'UnitConversion'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'unitConversion.label', default: 'UnitConversion'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
