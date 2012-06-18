package com.dumplingjoy.pos

import grails.converters.JSON
import grails.plugins.springsecurity.Secured


@Secured("isFullyAuthenticated()")
class ReceivingReceiptItemController {

    static allowedMethods = [updateDiscounts: "POST"]

	def editDiscounts() {
		ReceivingReceipt receivingReceiptInstance = ReceivingReceipt.get(params["receivingReceipt.id"])
		if (!receivingReceiptInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceipt.label')])
			redirect(controller: "receivingReceipt", action: "list")
			return
		}

		ReceivingReceiptItem receivingReceiptItemInstance = ReceivingReceiptItem.get(params.id)
		if (!receivingReceiptItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceiptItem.label')])
			redirect(controller: "receivingReceipt", action: "show", id: receivingReceiptInstance.id)
			return
		}

		[receivingReceiptItemInstance: receivingReceiptItemInstance, receivingReceiptInstance: receivingReceiptInstance]
	}
	
	def updateDiscounts() {
		ReceivingReceipt receivingReceiptInstance = ReceivingReceipt.get(params["receivingReceipt.id"])
		if (!receivingReceiptInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceipt.label')])
			redirect(controller: "receivingReceipt", action: "list")
			return
		}

		ReceivingReceiptItem receivingReceiptItemInstance = ReceivingReceiptItem.get(params.id)
		if (!receivingReceiptItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceiptItem.label')])
			redirect(controller: "receivingReceipt", action: "show", id: receivingReceiptInstance.id)
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (receivingReceiptItemInstance.version > version) {
				receivingReceiptItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'receivingReceiptItem.label')] as Object[],
						"Another user has updated this ReceivingReceiptItem while you were editing")
				render(view: "editDiscounts", model: [receivingReceiptItemInstance: receivingReceiptItemInstance, receivingReceiptInstance: receivingReceiptInstance])
				return
			}
		}

		receivingReceiptItemInstance.properties = params
		if (!receivingReceiptItemInstance.discount1) {
			receivingReceiptItemInstance.discount1 = BigDecimal.ZERO
		}
		if (!receivingReceiptItemInstance.discount2) {
			receivingReceiptItemInstance.discount2 = BigDecimal.ZERO
		}
		if (!receivingReceiptItemInstance.discount3) {
			receivingReceiptItemInstance.discount3 = BigDecimal.ZERO
		}
		if (!receivingReceiptItemInstance.flatRateDiscount) {
			receivingReceiptItemInstance.flatRateDiscount = BigDecimal.ZERO
		}

		if (!receivingReceiptItemInstance.save(flush: true)) {
			render(view: "editDiscounts", model: [receivingReceiptItemInstance: receivingReceiptItemInstance, receivingReceiptInstance: receivingReceiptInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'receivingReceiptItem.label')])
		redirect(controller: "receivingReceipt", action: "show", id: receivingReceiptInstance.id)
	}
	
	def getDiscountedAndNetAmounts() {
		ReceivingReceiptItem receivingReceiptItemInstance = ReceivingReceiptItem.get(params.id)
		if (!receivingReceiptItemInstance) {
			render ""
			return
		}
		receivingReceiptItemInstance.discard()
		
		receivingReceiptItemInstance.discount1 = new BigDecimal(params.discount1)
		receivingReceiptItemInstance.discount2 = new BigDecimal(params.discount2)
		receivingReceiptItemInstance.discount3 = new BigDecimal(params.discount3)
		receivingReceiptItemInstance.flatRateDiscount = new BigDecimal(params.flatRateDiscount)
		
		def out = [discountedAmount: receivingReceiptItemInstance.discountedAmount, netAmount: receivingReceiptItemInstance.netAmount]
		render out as JSON
	}

}
