package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured
import grails.validation.Validateable



class UserController {

	def springSecurityService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

	@Secured("ROLE_MANAGER")
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

	@Secured("ROLE_MANAGER")
    def create() {
        [userInstance: new User(params)]
    }

	@Secured("ROLE_MANAGER")
    def save() {
        User userInstance = new User(params)
		userInstance.password = "password"
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

		if (params.hasManagerPrivilege) {
			new UserRole(user: userInstance, role: Role.findByAuthority("ROLE_MANAGER")).save(failOnError: true)
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

	@Secured("ROLE_MANAGER")
    def show() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

	@Secured("ROLE_MANAGER")
    def edit() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

	@Secured("ROLE_MANAGER")
    def update() {
        User userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }
		
        if (params.version) {
            def version = params.version.toLong()
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }	
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }
		
		if (params.hasManagerPrivilege && !userInstance.isManager()) {
			new UserRole(user: userInstance, role: Role.findByAuthority("ROLE_MANAGER")).save(failOnError: true)
		} else if (!params.hasManagerPrivilege && userInstance.isManager()) {
			UserRole.findByUserAndRole(userInstance, Role.findByAuthority("ROLE_MANAGER")).delete(failOnError: true)		
		}
		
		flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }
	
	@Secured("isFullyAuthenticated()")
	def editPassword() {
		[changePasswordInstance: new ChangePassword()]
	}
	
	@Secured("isFullyAuthenticated()")
	def updatePassword () {
		User userInstance = springSecurityService.currentUser
		
		ChangePassword changePasswordInstance = new ChangePassword()
		changePasswordInstance.oldPassword = params.oldPassword
		changePasswordInstance.newPassword = params.newPassword
		changePasswordInstance.retypePassword = params.retypePassword
		changePasswordInstance.springSecurityService = springSecurityService
		
		if (!changePasswordInstance.validate()) {
			render(view: "editPassword", model: [changePasswordInstance: changePasswordInstance])
			return
		}
		
		userInstance.password = params.newPassword
		
		if (!userInstance.save(flush: true)) {
			render(view: "edit", model: [userInstance: userInstance])
			return
		}

		flash.message = message(code: 'passwordUpdated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
		redirect(action: "editPassword")
	}
	
	@Secured("ROLE_MANAGER")
	def resetPassword () {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
			redirect(action: "list")
			return
		}
		
		userInstance.password = "password"
        userInstance.save(failOnError: true)
		
		flash.message = message(code: 'passwordReset.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
		redirect(action: "show", id: userInstance.id)
	}


}