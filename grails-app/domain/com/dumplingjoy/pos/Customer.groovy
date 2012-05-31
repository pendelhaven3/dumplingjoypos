package com.dumplingjoy.pos

class Customer {

	String name
	PricingScheme defaultPricingScheme
	
    static constraints = {
		name blank: false, unique: true
    }
	
}