package com.dumplingjoy.pos

class SalesRequisitionSequenceNumber {

	int value
	
    static constraints = {
    }
	
	public static int getNextValue() {
		SalesRequisitionSequenceNumber sequence = SalesRequisitionSequenceNumber.get(1)
		return sequence.value + 1
	}

	public static void increment() {
		SalesRequisitionSequenceNumber sequence = SalesRequisitionSequenceNumber.get(1)
		sequence.value++
		sequence.save()
	}

}
