package com.dumplingjoy.pos

class PaymentTerms {

	String name
	
    static constraints = {
		name blank: false, unique: true
    }
	
}
