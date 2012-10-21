package com.dumplingjoy.pos

import java.util.List;

class Manufacturer {

	String name
	
    static constraints = {
		name blank: false, unique: true
    }
	
	List<Product> getProducts() {
		Product.findAllByManufacturer(this)
	}
	
}