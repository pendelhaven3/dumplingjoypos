package com.dumplingjoy.pos

class PaymentTerms {

	String name
	Integer numberOfDays
	
    static constraints = {
		name blank: false, unique: true
		numberOfDays min: 0
    }
	
}
