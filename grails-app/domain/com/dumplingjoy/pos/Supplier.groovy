package com.dumplingjoy.pos

class Supplier {

	String name
	String address
	String contactNumber
	String contactPerson
	String faxNumber
	String emailAddress
	String tin
	PaymentTerms terms
	
	List<Product> products
	
    static constraints = {
		name blank: false, unique: true
		address blank: false
		contactNumber blank: false
		contactPerson nullable: true
		faxNumber nullable: true
		emailAddress nullable: true
		tin nullable: true
		terms nullable: true
    }

	static hasMany = [products: Product]
		
}