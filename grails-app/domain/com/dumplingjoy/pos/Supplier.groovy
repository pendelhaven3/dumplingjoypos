package com.dumplingjoy.pos

class Supplier {

	String name
	List<Product> products
	
    static constraints = {
		name blank: false, unique: true
    }

	static hasMany = [products: Product]
		
}