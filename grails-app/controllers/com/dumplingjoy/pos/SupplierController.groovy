package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured("isFullyAuthenticated()")
class SupplierController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [supplierInstanceList: Supplier.list(params), supplierInstanceTotal: Supplier.count()]
    }

    def create() {
        [supplierInstance: new Supplier(params)]
    }

    def save() {
        def supplierInstance = new Supplier(params)
        if (!supplierInstance.save(flush: true)) {
            render(view: "create", model: [supplierInstance: supplierInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'supplier.label', default: 'Supplier'), supplierInstance.id])
        redirect(action: "show", id: supplierInstance.id)
    }

    def show() {
        def supplierInstance = Supplier.get(params.id)
        if (!supplierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'supplier.label', default: 'Supplier'), params.id])
            redirect(action: "list")
            return
        }

		supplierInstance.products = supplierInstance.products.sort {it.description}
        [supplierInstance: supplierInstance]
    }

    def edit() {
        def supplierInstance = Supplier.get(params.id)
        if (!supplierInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'supplier.label', default: 'Supplier'), params.id])
            redirect(action: "list")
            return
        }

        [supplierInstance: supplierInstance]
    }

    def update() {
        def supplierInstance = Supplier.get(params.id)
        if (!supplierInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'supplier.label', default: 'Supplier'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (supplierInstance.version > version) {
                supplierInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'supplier.label', default: 'Supplier')] as Object[],
                          "Another user has updated this Supplier while you were editing")
                render(view: "edit", model: [supplierInstance: supplierInstance])
                return
            }
        }

        supplierInstance.properties = params

        if (!supplierInstance.save(flush: true)) {
            render(view: "edit", model: [supplierInstance: supplierInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'supplier.label', default: 'Supplier'), supplierInstance.id])
        redirect(action: "show", id: supplierInstance.id)
    }

    def delete() {
        def supplierInstance = Supplier.get(params.id)
        if (!supplierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'supplier.label', default: 'Supplier'), params.id])
            redirect(action: "list")
            return
        }

        try {
            supplierInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'supplier.label', default: 'Supplier'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'supplier.label', default: 'Supplier'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def selectProductToAdd() {
        def supplierInstance = Supplier.get(params.id)
        if (!supplierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'supplier.label')])
            redirect(action: "list")
            return
        }
		
        [
			supplierInstance: supplierInstance,
			productInstanceList: getProductsNotInSupplier(supplierInstance, params), 
			productInstanceTotal: getTotalNumberOfProductsNotInSupplier(supplierInstance, params)
		]
	}
	
	private List<Product> getProductsNotInSupplier(supplierInstance, params) {
		String description = params.description ?: ""
		description = description + "%"
		
		Product.findAll(
			" from Product p where p not in (select supplierProducts from Supplier s inner join s.products supplierProducts where s.id = ?)" +
			" and lower(p.description) like lower(?)" +
			" order by p.description asc",
			[supplierInstance.id, description], [max: 10, offset: params.offset ?: 0])
	}
	
	private int getTotalNumberOfProductsNotInSupplier(Supplier supplierInstance, params) {
		String description = params.description ?: ""
		description = description + "%"
		
		Product.executeQuery( 
			" select count(*) from Product p where p not in (select supplierProducts from Supplier s inner join s.products supplierProducts where s.id = ?)" +
			" and lower(p.description) like lower(?)",
			[supplierInstance.id, description]).first()	
		}
	
	def addProduct() {
        def supplierInstance = Supplier.get(params.id)
        if (!supplierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'supplier.label', default: 'Supplier'), params.id])
            redirect(action: "list")
            return
        }
		
		Product productInstance = Product.get(params["product.id"])
		if (!productInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label')])
			redirect(action: "show", id: supplierInstance.id)
			return
		}
		
		if (supplierInstance.products.contains(productInstance)) {
			supplierInstance.errors.reject("supplier.containsProduct.message", [message(code: 'product.label')] as Object[], "supplier.containsProduct.message")
			render(
				view: "selectProductToAdd", 
				model: [
					supplierInstance: supplierInstance,
					productInstanceList: getProductsNotInSupplier(supplierInstance, params), 
					productInstanceTotal: getTotalNumberOfProductsNotInSupplier(supplierInstance, params)
				]
			)
			return
		}
		
		supplierInstance.addToProducts(productInstance)
		supplierInstance.save(flush: true)
		
		flash.message = message(code: 'default.added.message', args: [message(code: 'product.label')])
        redirect(action: "selectProductToAdd", id: supplierInstance.id, 
			params: [description: params.description, offset: params.offset])
	}
	
	def removeProduct() {
        def supplierInstance = Supplier.get(params.id)
        if (!supplierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'supplier.label', default: 'Supplier'), params.id])
            redirect(action: "list")
            return
        }
		
		Product productInstance = Product.get(params["product.id"])
		if (!productInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label')])
			redirect(action: "show", id: supplierInstance.id)
			return
		}
		
		supplierInstance.removeFromProducts(productInstance)
		supplierInstance.save(flush: true)
		
		flash.message = message(code: 'default.removed.message', args: [message(code: 'product.label')])
        redirect(action: "show", id: supplierInstance.id)
	}
	
}