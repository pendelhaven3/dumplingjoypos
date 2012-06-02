package com.dumplingjoy.pos

class SalesInvoiceSequenceNumber {

	int value
	
    static constraints = {
    }
	
	public static int getNextValue() {
		SalesInvoiceSequenceNumber sequence = SalesInvoiceSequenceNumber.get(1)
		return sequence.value + 1
	}

	public static void increment() {
		SalesInvoiceSequenceNumber sequence = SalesInvoiceSequenceNumber.get(1)
		sequence.value++
		sequence.save()
	}

}