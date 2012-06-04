package com.dumplingjoy.pos

import java.math.RoundingMode

import grails.converters.JSON

import org.springframework.dao.DataIntegrityViolationException

import com.dumplingjoy.pos.util.Percentage

class SalesRequisitionItemController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def create() {
		SalesRequisition salesRequisitionInstance = SalesRequisition.get(params["salesRequisition.id"])
		if (!salesRequisitionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), params.id])
			redirect(controller: "salesRequisition", action: "list")
			return
		}

		[salesRequisitionItemInstance: new SalesRequisitionItem(params), salesRequisitionInstance: salesRequisitionInstance]
	}

	def save() {
		SalesRequisition salesRequisitionInstance = SalesRequisition.get(params["salesRequisition.id"])
		if (!salesRequisitionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label'), params.id])
			redirect(controller: "salesRequisition", action: "list")
			return
		}

		def salesRequisitionItemInstance = new SalesRequisitionItem(params)
		salesRequisitionItemInstance.product = Product.get(params["product.id"])

		if (salesRequisitionInstance.containsItem(salesRequisitionItemInstance)) {
			salesRequisitionItemInstance.errors.reject("default.containsItem.message",
					[message(code: 'salesRequisition.label')] as Object[], "default.containsItem.message")
			render(view: "create", model: [salesRequisitionItemInstance: salesRequisitionItemInstance, salesRequisitionInstance: salesRequisitionInstance])
			return
		}

		salesRequisitionInstance.addToItems(salesRequisitionItemInstance)

		if (!salesRequisitionItemInstance.save(flush: true)) {
			render(view: "create", model: [salesRequisitionItemInstance: salesRequisitionItemInstance, salesRequisitionInstance: salesRequisitionInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), salesRequisitionItemInstance.id])
		redirect(controller: "salesRequisition", action: "show", id: salesRequisitionInstance.id)
	}

	def edit() {
		SalesRequisition salesRequisitionInstance = SalesRequisition.get(params["salesRequisition.id"])
		if (!salesRequisitionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), params.id])
			redirect(controller: "salesRequisition", action: "list")
			return
		}

		def salesRequisitionItemInstance = SalesRequisitionItem.get(params.id)
		if (!salesRequisitionItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), params.id])
			redirect(controller: "salesRequisition", action: "show", id: salesRequisitionInstance.id)
			return
		}

		[salesRequisitionItemInstance: salesRequisitionItemInstance, salesRequisitionInstance: salesRequisitionInstance]
	}

	def update() {
		SalesRequisition salesRequisitionInstance = SalesRequisition.get(params["salesRequisition.id"])
		if (!salesRequisitionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label'), params.id])
			redirect(controller: "salesRequisition", action: "list")
			return
		}

		SalesRequisitionItem salesRequisitionItemInstance = SalesRequisitionItem.get(params.id)
		if (!salesRequisitionItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), params.id])
			redirect(controller: "salesRequisition", action: "show", id: salesRequisitionInstance.id)
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (salesRequisitionItemInstance.version > version) {
				salesRequisitionItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem')] as Object[],
						"Another user has updated this SalesRequisitionItem while you were editing")
				render(view: "edit", model: [salesRequisitionItemInstance: salesRequisitionItemInstance, salesRequisitionInstance: salesRequisitionInstance])
				return
			}
		}

		salesRequisitionItemInstance.discard()
		salesRequisitionItemInstance.properties = params
		salesRequisitionItemInstance.product = Product.get(params["product.id"])
		if (!salesRequisitionItemInstance.product) {
			salesRequisitionItemInstance.unit = null
		}

		if (salesRequisitionInstance.containsItem(salesRequisitionItemInstance)) {
			salesRequisitionItemInstance.errors.reject("default.containsItem.message",
					[message(code: 'salesRequisition.label')] as Object[], "default.containsItem.message")
			render(view: "edit", model: [salesRequisitionItemInstance: salesRequisitionItemInstance, salesRequisitionInstance: salesRequisitionInstance])
			return
		}

		// re-attach salesRequisitionItemInstance to session
		
		salesRequisitionInstance.discard()
		salesRequisitionInstance = SalesRequisition.get(params["salesRequisition.id"])
		
		salesRequisitionItemInstance = SalesRequisitionItem.get(params.id)
		salesRequisitionItemInstance.properties = params
		salesRequisitionItemInstance.product = Product.get(params["product.id"])
		if (!salesRequisitionItemInstance.product) {
			salesRequisitionItemInstance.unit = null
		}

		if (!salesRequisitionItemInstance.save(flush: true)) {
			render(view: "edit", model: [salesRequisitionItemInstance: salesRequisitionItemInstance, salesRequisitionInstance: salesRequisitionInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), salesRequisitionItemInstance.id])
		redirect(controller: "salesRequisition", action: "show", id: salesRequisitionInstance.id)
	}

	def delete() {
		SalesRequisition salesRequisitionInstance = SalesRequisition.get(params["salesRequisition.id"])
		if (!salesRequisitionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), params.id])
			redirect(controller: "salesRequisition", action: "list")
			return
		}

		def salesRequisitionItemInstance = SalesRequisitionItem.get(params.id)
		if (!salesRequisitionItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), params.id])
			redirect(controller: "salesRequisition", action: "list")
			return
		}

		try {
			salesRequisitionInstance.removeFromItems(salesRequisitionItemInstance)
			salesRequisitionInstance.save(failOnError: true)
			salesRequisitionItemInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), params.id])
			redirect(controller: "salesRequisition", action: "show", id: salesRequisitionInstance.id)
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), params.id])
			redirect(controller: "salesRequisition", action: "show", id: salesRequisitionInstance.id)
		}
	}
	
	def editDiscounts() {
		SalesRequisition salesRequisitionInstance = SalesRequisition.get(params["salesRequisition.id"])
		if (!salesRequisitionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label', default: 'SalesRequisition'), params.id])
			redirect(controller: "salesRequisition", action: "list")
			return
		}

		SalesRequisitionItem salesRequisitionItemInstance = SalesRequisitionItem.get(params.id)
		if (!salesRequisitionItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), params.id])
			redirect(controller: "salesRequisition", action: "show", id: salesRequisitionInstance.id)
			return
		}

		[salesRequisitionItemInstance: salesRequisitionItemInstance, salesRequisitionInstance: salesRequisitionInstance]
	}
	
	def updateDiscounts() {
		SalesRequisition salesRequisitionInstance = SalesRequisition.get(params["salesRequisition.id"])
		if (!salesRequisitionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisition.label'), params.id])
			redirect(controller: "salesRequisition", action: "list")
			return
		}

		SalesRequisitionItem salesRequisitionItemInstance = SalesRequisitionItem.get(params.id)
		if (!salesRequisitionItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), params.id])
			redirect(controller: "salesRequisition", action: "show", id: salesRequisitionInstance.id)
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (salesRequisitionItemInstance.version > version) {
				salesRequisitionItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem')] as Object[],
						"Another user has updated this SalesRequisitionItem while you were editing")
				render(view: "editDiscounts", model: [salesRequisitionItemInstance: salesRequisitionItemInstance, salesRequisitionInstance: salesRequisitionInstance])
				return
			}
		}

        salesRequisitionItemInstance.properties = params
		if (!salesRequisitionItemInstance.discount1) {
			salesRequisitionItemInstance.discount1 = BigDecimal.ZERO
		}
		if (!salesRequisitionItemInstance.discount2) {
			salesRequisitionItemInstance.discount2 = BigDecimal.ZERO
		}
		if (!salesRequisitionItemInstance.discount3) {
			salesRequisitionItemInstance.discount3 = BigDecimal.ZERO
		}
		if (!salesRequisitionItemInstance.flatRateDiscount) {
			salesRequisitionItemInstance.flatRateDiscount = BigDecimal.ZERO
		}

		if (!salesRequisitionItemInstance.save(flush: true)) {
			render(view: "editDiscounts", model: [salesRequisitionItemInstance: salesRequisitionItemInstance, salesRequisitionInstance: salesRequisitionInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem'), salesRequisitionItemInstance.id])
		redirect(controller: "salesRequisition", action: "show", id: salesRequisitionInstance.id)
	}
	
	def getDiscountedAndNetAmounts() {
		SalesRequisitionItem salesRequisitionItemInstance = SalesRequisitionItem.get(params.id)
		if (!salesRequisitionItemInstance) {
			render ""
			return
		}
		salesRequisitionItemInstance.getUnitPrice()
		salesRequisitionItemInstance.discard()
		
		salesRequisitionItemInstance.discount1 = new BigDecimal(params.discount1)
		salesRequisitionItemInstance.discount2 = new BigDecimal(params.discount2)
		salesRequisitionItemInstance.discount3 = new BigDecimal(params.discount3)
		salesRequisitionItemInstance.flatRateDiscount = new BigDecimal(params.flatRateDiscount)
		
		def out = [discountedAmount: salesRequisitionItemInstance.discountedAmount, netAmount: salesRequisitionItemInstance.netAmount]
		render out as JSON
	}

}
