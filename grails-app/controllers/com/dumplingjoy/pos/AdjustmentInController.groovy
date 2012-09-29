package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured("isFullyAuthenticated()")
class AdjustmentInController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def adjustmentInService
	
    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: "adjustmentInNumber"
		params.order = params.order ?: "desc"
		
        [adjustmentInInstanceList: AdjustmentIn.list(params), adjustmentInInstanceTotal: AdjustmentIn.count()]
    }

    def create() {
        [adjustmentInInstance: new AdjustmentIn(params)]
    }

    def save() {
        def adjustmentInInstance = new AdjustmentIn(params)
		adjustmentInInstance.adjustmentInNumber = AdjustmentInSequenceNumber.getNextValue()
        if (!adjustmentInInstance.save(flush: true)) {
            render(view: "create", model: [adjustmentInInstance: adjustmentInInstance])
            return
        }
		AdjustmentInSequenceNumber.increment()
		flash.message = message(code: 'default.created.message', args: [message(code: 'adjustmentIn.label', default: 'AdjustmentIn'), adjustmentInInstance.id])
        redirect(action: "show", id: adjustmentInInstance.id)
    }

    def show() {
        def adjustmentInInstance = AdjustmentIn.get(params.id)
        if (!adjustmentInInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentIn.label', default: 'AdjustmentIn'), params.id])
            redirect(action: "list")
            return
        }

        [adjustmentInInstance: adjustmentInInstance]
    }

    def edit() {
        def adjustmentInInstance = AdjustmentIn.get(params.id)
        if (!adjustmentInInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentIn.label', default: 'AdjustmentIn'), params.id])
            redirect(action: "list")
            return
        }
		
		if (adjustmentInInstance.posted) {
			redirect(action: "show", id: adjustmentInInstance.id)
			return
		}

        [adjustmentInInstance: adjustmentInInstance]
    }

    def update() {
        def adjustmentInInstance = AdjustmentIn.get(params.id)
        if (!adjustmentInInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentIn.label', default: 'AdjustmentIn'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (adjustmentInInstance.version > version) {
                adjustmentInInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'adjustmentIn.label', default: 'AdjustmentIn')] as Object[],
                          "Another user has updated this AdjustmentIn while you were editing")
                render(view: "edit", model: [adjustmentInInstance: adjustmentInInstance])
                return
            }
        }

        adjustmentInInstance.properties = params

        if (!adjustmentInInstance.save(flush: true)) {
            render(view: "edit", model: [adjustmentInInstance: adjustmentInInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'adjustmentIn.label', default: 'AdjustmentIn'), adjustmentInInstance.id])
        redirect(action: "show", id: adjustmentInInstance.id)
    }

    def delete() {
        def adjustmentInInstance = AdjustmentIn.get(params.id)
        if (!adjustmentInInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentIn.label', default: 'AdjustmentIn'), params.id])
            redirect(action: "list")
            return
        }

        try {
            adjustmentInInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'adjustmentIn.label', default: 'AdjustmentIn'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'adjustmentIn.label', default: 'AdjustmentIn'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def postAdjustmentIn() {
		AdjustmentIn adjustmentInInstance = AdjustmentIn.get(params.id)
		if (!adjustmentInInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjustmentIn.label'), params.id])
			redirect(action: "list")
			return
		}
		
		adjustmentInInstance.post()
		
		flash.message = message(code: 'default.posted.message', args: [message(code: 'adjustmentIn.label')])
		redirect(action: "show", id: params.id)
	}
	
}
