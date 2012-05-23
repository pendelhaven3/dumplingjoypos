package com.dumplingjoy.pos

class AdjustmentIn {

	Integer adjustmentInNumber
	String description
	
    static constraints = {
		adjustmentInNumber nullable: false, unique: true
		description nullable: false, blank: false
    }
	
	static hasMany = [
		items: AdjustmentInItem
	]
	
}