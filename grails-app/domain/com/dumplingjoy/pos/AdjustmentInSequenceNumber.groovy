package com.dumplingjoy.pos

class AdjustmentInSequenceNumber {

	int value
	
    static constraints = {
    }
	
	public static int getNextValue() {
		AdjustmentInSequenceNumber sequence = AdjustmentInSequenceNumber.get(1)
		return sequence.value + 1
	}

	public static void increment() {
		AdjustmentInSequenceNumber sequence = AdjustmentInSequenceNumber.get(1)
		sequence.value++
		sequence.save()
	}

}
