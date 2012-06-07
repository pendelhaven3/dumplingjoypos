package com.dumplingjoy.pos

class DiscountTerms {

	String name
	
    static constraints = {
		name blank: false, unique: true
    }
	
}
