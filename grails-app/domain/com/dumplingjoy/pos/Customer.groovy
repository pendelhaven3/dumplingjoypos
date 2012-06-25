package com.dumplingjoy.pos

class Customer {

	String code
	String name // TODO: Change to businessname
	String contactPerson
	String contactNumber
	String faxNumber
	String address
	String modeOfPayment
	PaymentTerms terms
	String bank
	String branch
	BigDecimal creditLine
	String remarks
	
    static constraints = {
		code nullable: true // TODO: make non-nullable later
		name blank: false, unique: true
		contactPerson nullable: true
		contactNumber nullable: true
		faxNumber nullable: true
		address blank: false
		modeOfPayment nullable: true, inList: ["COD", "Check"]
		terms nullable: true
		bank nullable: true
		branch nullable: true
		creditLine nullable: true, min: BigDecimal.ZERO
		remarks nullable: true
    }
	
}