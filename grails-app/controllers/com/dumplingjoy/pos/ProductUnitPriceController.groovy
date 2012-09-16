package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured("ROLE_MANAGER")
class ProductUnitPriceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def productUnitPriceService
	
    def edit() {
        def productUnitPriceInstance = ProductUnitPrice.get(params.id)
        if (!productUnitPriceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice'), params.id])
            redirect(action: "list")
            return
        }
		
		productUnitPriceInstance.retrieveUnitCost()
        [productUnitPriceInstance: productUnitPriceInstance]
    }

    def update() {
        def productUnitPriceInstance = ProductUnitPrice.get(params.id)
        if (!productUnitPriceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (productUnitPriceInstance.version > version) {
                productUnitPriceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice')] as Object[],
                          "Another user has updated this ProductUnitPrice while you were editing")
                render(view: "edit", model: [productUnitPriceInstance: productUnitPriceInstance])
                return
            }
        }

        productUnitPriceInstance.properties = params

		if (!productUnitPriceService.update(productUnitPriceInstance)) {
			productUnitPriceInstance.retrieveUnitCost()
            render(view: "edit", model: [productUnitPriceInstance: productUnitPriceInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'productUnitPrice.label', default: 'ProductUnitPrice'), productUnitPriceInstance.id])
        redirect(controller: "pricingScheme", action: "showProductUnitPrices", 
			id: productUnitPriceInstance.pricingScheme.id, params: ["product.id": productUnitPriceInstance.product.id])
    }

}
