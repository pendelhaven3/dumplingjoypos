package com.dumplingjoy.pos

class ProductCategory {

	String type
	String name
	
    static constraints = {
		type nullable: false, blank: false, inList: ["Food", "Non-Food"]
		name nullable: false, blank: false
    }
	
}
