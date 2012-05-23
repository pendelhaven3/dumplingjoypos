package com.dumplingjoy.pos

class SequenceService {

	public int getNextAdjustmentInNumber() {
		AdjustmentInSequenceNumber sequenceNumber = AdjustmentInSequenceNumber.get(1) 
		sequenceNumber.value++
		sequenceNumber.save()
		return sequenceNumber.value
	}
	
}
