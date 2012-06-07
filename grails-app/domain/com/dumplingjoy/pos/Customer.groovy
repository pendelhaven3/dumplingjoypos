package com.dumplingjoy.pos

class Customer {

	String name
	String address
//	PricingScheme defaultPricingScheme // To implement later
	
    static constraints = {
		name blank: false, unique: true
		address blank: false
    }
	
}