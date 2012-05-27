package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured("isFullyAuthenticated()")
class AdjustmentOutController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: "adjustmentOutNumber"
		params.order = params.order ?: "desc"

        [adjustmentOutInstanceList: AdjustmentOut.list(params), adjustmentOutInstanceTotal: AdjustmentOut.count()]
    }

    def create() {
        [adjustmentOutInstance: new AdjustmentOut(params)]
    }

    def save() {
        def adjustmentOutInstance = new AdjustmentOut(params)
		adjustmentOutInstance.adjustmentOutNumber = AdjustmentOutSequenceNumber.getNextValue()
        if (!adjustmentOutInstance.save(flush: true)) {
            render(view: "create", model: [adjustmentOutInstance: adjustmentOutInstance])
            return
        }
		AdjustmentOutSequenceNumber.increment()
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'adjustmentOut.label', default: 'AdjustmentOut'), adjustmentOutInstance.id])
        redirect(action: "show", id: adjustmentOutInstance.id)
    }

    def show() {
        def adjustmentOutInstance = AdjustmentOut.get(params.id)
        if (!adjustmentOutInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOut.label', default: 'AdjustmentOut'), params.id])
            redirect(action: "list")
            return
        }

        [adjustmentOutInstance: adjustmentOutInstance]
    }

    def edit() {
        def adjustmentOutInstance = AdjustmentOut.get(params.id)
        if (!adjustmentOutInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOut.label', default: 'AdjustmentOut'), params.id])
            redirect(action: "list")
            return
        }

        [adjustmentOutInstance: adjustmentOutInstance]
    }

    def update() {
        def adjustmentOutInstance = AdjustmentOut.get(params.id)
        if (!adjustmentOutInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOut.label', default: 'AdjustmentOut'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (adjustmentOutInstance.version > version) {
                adjustmentOutInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'adjustmentOut.label', default: 'AdjustmentOut')] as Object[],
                          "Another user has updated this AdjustmentOut while you were editing")
                render(view: "edit", model: [adjustmentOutInstance: adjustmentOutInstance])
                return
            }
        }

        adjustmentOutInstance.properties = params

        if (!adjustmentOutInstance.save(flush: true)) {
            render(view: "edit", model: [adjustmentOutInstance: adjustmentOutInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'adjustmentOut.label', default: 'AdjustmentOut'), adjustmentOutInstance.id])
        redirect(action: "show", id: adjustmentOutInstance.id)
    }

    def delete() {
        def adjustmentOutInstance = AdjustmentOut.get(params.id)
        if (!adjustmentOutInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOut.label', default: 'AdjustmentOut'), params.id])
            redirect(action: "list")
            return
        }

        try {
            adjustmentOutInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'adjustmentOut.label', default: 'AdjustmentOut'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'adjustmentOut.label', default: 'AdjustmentOut'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def postAdjustmentOut() {
		AdjustmentOut adjustmentOutInstance = AdjustmentOut.get(params.id)
		if (!adjustmentOutInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentOut.label'), params.id])
			redirect(action: "list")
			return
		}
		
		if (!adjustmentOutInstance.post()) {
            render(view: "show", model: [adjustmentOutInstance: adjustmentOutInstance])
			return
		}
		
		flash.message = message(code: 'default.posted.message', args: [message(code: 'adjustmentOut.label')])
		redirect(action: "show", id: params.id)
	}
	
}
