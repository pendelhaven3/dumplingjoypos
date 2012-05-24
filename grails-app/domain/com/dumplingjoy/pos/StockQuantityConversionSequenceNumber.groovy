package com.dumplingjoy.pos

class StockQuantityConversionSequenceNumber {

	int value
	
    static constraints = {
    }
	
	public static int getNextValue() {
		StockQuantityConversionSequenceNumber sequence = StockQuantityConversionSequenceNumber.get(1)
		return sequence.value + 1
	}

	public static void increment() {
		StockQuantityConversionSequenceNumber sequence = StockQuantityConversionSequenceNumber.get(1)
		sequence.value++
		sequence.save()
	}

}
