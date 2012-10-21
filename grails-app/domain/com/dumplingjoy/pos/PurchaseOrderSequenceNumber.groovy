package com.dumplingjoy.pos

class PurchaseOrderSequenceNumber {

	int value
	
    static constraints = {
    }
	
	public static int getNextValue() {
		PurchaseOrderSequenceNumber sequence = PurchaseOrderSequenceNumber.get(1)
		return sequence.value + 1
	}

	public static void increment() {
		PurchaseOrderSequenceNumber sequence = PurchaseOrderSequenceNumber.get(1)
		sequence.value++
		sequence.save(failOnError: true)
	}

}
