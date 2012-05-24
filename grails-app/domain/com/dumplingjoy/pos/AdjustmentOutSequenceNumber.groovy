package com.dumplingjoy.pos

class AdjustmentOutSequenceNumber {

	int value
	
    static constraints = {
    }
	
	public static int getNextValue() {
		AdjustmentOutSequenceNumber sequence = AdjustmentOutSequenceNumber.get(1)
		return sequence.value + 1
	}

	public static void increment() {
		AdjustmentOutSequenceNumber sequence = AdjustmentOutSequenceNumber.get(1)
		sequence.value++
		sequence.save()
	}

}
