package com.dumplingjoy.pos

class Customer {

	String name
	String address
	PricingScheme defaultPricingScheme
	
    static constraints = {
		name blank: false, unique: true
		address blank: false
    }
	
}