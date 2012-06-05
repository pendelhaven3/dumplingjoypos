package com.dumplingjoy.pos

class Manufacturer {

	String name
	
    static constraints = {
		name blank: false, unique: true
    }
	
}