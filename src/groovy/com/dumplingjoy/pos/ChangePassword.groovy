package com.dumplingjoy.pos

import org.apache.commons.lang.StringUtils

import grails.validation.Validateable;

@Validateable
class ChangePassword {

	def springSecurityService
	
	String oldPassword
	String newPassword
	String retypePassword

	static constraints = {
		oldPassword blank: false, validator: { val, obj ->
			User user = (User)obj.springSecurityService.currentUser
			if (!obj.springSecurityService.encodePassword(val).equals(user.password)) {
				return "oldPasswordNotCorrect.message"
			} else {
				return true
			}
		}
		newPassword blank: false
		retypePassword blank: false, validator: { val, obj ->
			if (!StringUtils.isEmpty(val) && !StringUtils.isEmpty(obj.newPassword) && !val.equals(obj.newPassword)) {
				return "newAndRetypePasswordsMustBeSame.message"
			} else {
				return true
			}
		}
	}

}
