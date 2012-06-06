package com.dumplingjoy.pos

class ReceivingReceiptSequenceNumber {

	int value
	
    static constraints = {
    }
	
	public static int getNextValue() {
		ReceivingReceiptSequenceNumber sequence = ReceivingReceiptSequenceNumber.get(1)
		return sequence.value + 1
	}

	public static void increment() {
		ReceivingReceiptSequenceNumber sequence = ReceivingReceiptSequenceNumber.get(1)
		sequence.value++
		sequence.save(failOnError: true)
	}

}
