package com.dumplingjoy.pos

class Supplier {

	String name
	String address
	String contactNumber
	
	List<Product> products
	
    static constraints = {
		name blank: false, unique: true
		address blank: false
		contactNumber blank: false
    }

	static hasMany = [products: Product]
		
}