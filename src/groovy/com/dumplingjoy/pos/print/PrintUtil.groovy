package com.dumplingjoy.pos.print

import java.text.DecimalFormat

import org.apache.commons.lang.StringUtils;

class PrintUtil {

	private static String asLeftAlignedField(String value, int size) {
		if (value.size() >= size) {
			return value.substring(0, size)
		} else {
			return StringUtils.rightPad(value, size)
		}
	}

	public static String asRightAlignedField(int value, int size) {
		String valueString = String.valueOf(value)
		if (valueString.size() >= size) {
			return valueString.substring(0, size)
		} else {
			return StringUtils.leftPad(valueString, size)
		}
	}
	
	public static String asCurrencyField(BigDecimal value, int size) {
		String valueString = new DecimalFormat("#,##0.00").format(value.toDouble())
		if (valueString.size() < size) {
			valueString = StringUtils.leftPad(valueString, size)
		}
		return valueString
	}
	
	private static String asLeftAlignedField(int value, int size) {
		return asLeftAlignedField(String.valueOf(value), size)
	}
		
}
