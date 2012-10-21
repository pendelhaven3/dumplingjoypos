package com.dumplingjoy.pos

class Customer {

	String code
	String name // TODO: Change to businessname
	String contactPerson
	String contactNumbers
	String faxNumber
	String address
	String paymentMode
	PaymentTerms terms
	String bank
	String branch
	BigDecimal creditLine
	String remarks
	
    static constraints = {
		code nullable: false, blank: false, unique: true 
		name nullable: false, blank: false, unique: true
		contactPerson nullable: true
		contactNumbers nullable: true
		faxNumber nullable: true
		address blank: false
		paymentMode nullable: true, inList: ["COD", "Check"]
		terms nullable: true
		bank nullable: true
		branch nullable: true
		creditLine nullable: true, min: BigDecimal.ZERO
		remarks nullable: true
    }
	
}