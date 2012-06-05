package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class ManufacturerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [manufacturerInstanceList: Manufacturer.list(params), manufacturerInstanceTotal: Manufacturer.count()]
    }

    def create() {
        [manufacturerInstance: new Manufacturer(params)]
    }

    def save() {
        def manufacturerInstance = new Manufacturer(params)
        if (!manufacturerInstance.save(flush: true)) {
            render(view: "create", model: [manufacturerInstance: manufacturerInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'manufacturer.label', default: 'Manufacturer'), manufacturerInstance.id])
        redirect(action: "show", id: manufacturerInstance.id)
    }

    def show() {
        def manufacturerInstance = Manufacturer.get(params.id)
        if (!manufacturerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'manufacturer.label', default: 'Manufacturer'), params.id])
            redirect(action: "list")
            return
        }

        [manufacturerInstance: manufacturerInstance]
    }

    def edit() {
        def manufacturerInstance = Manufacturer.get(params.id)
        if (!manufacturerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'manufacturer.label', default: 'Manufacturer'), params.id])
            redirect(action: "list")
            return
        }

        [manufacturerInstance: manufacturerInstance]
    }

    def update() {
        def manufacturerInstance = Manufacturer.get(params.id)
        if (!manufacturerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'manufacturer.label', default: 'Manufacturer'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (manufacturerInstance.version > version) {
                manufacturerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'manufacturer.label', default: 'Manufacturer')] as Object[],
                          "Another user has updated this Manufacturer while you were editing")
                render(view: "edit", model: [manufacturerInstance: manufacturerInstance])
                return
            }
        }

        manufacturerInstance.properties = params

        if (!manufacturerInstance.save(flush: true)) {
            render(view: "edit", model: [manufacturerInstance: manufacturerInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'manufacturer.label', default: 'Manufacturer'), manufacturerInstance.id])
        redirect(action: "show", id: manufacturerInstance.id)
    }

    def delete() {
        def manufacturerInstance = Manufacturer.get(params.id)
        if (!manufacturerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'manufacturer.label', default: 'Manufacturer'), params.id])
            redirect(action: "list")
            return
        }

        try {
            manufacturerInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'manufacturer.label', default: 'Manufacturer'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'manufacturer.label', default: 'Manufacturer'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
