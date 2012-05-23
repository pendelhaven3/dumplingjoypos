package com.dumplingjoy.pos

class Product {

	String code
	String description
	
    static constraints = {
		code nullable:false, blank:false, unique:true
		description nullable:false, blank:false, unique:true
    }
	
	static hasMany = [
		units: Unit,
		unitQuantities: UnitQuantity
	]

}
