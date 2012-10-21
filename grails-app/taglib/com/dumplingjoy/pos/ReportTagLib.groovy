package com.dumplingjoy.pos

import java.text.DecimalFormat

import org.apache.commons.lang.StringUtils;

class ReportTagLib {

	static namespace = "report"
	
	private final static String LEFT = "left"
	private final static String RIGHT = "right"
	private final static String CURRENCY_FORMAT = "#,##0.00"

	/**
	 * Creates a new report field.
	 *
	 * @attr value REQUIRED field value
	 * @attr length field length
	 * @attr align field alignment ("left" or "right")
	 * @attr currency display value as currency amount
	 */	
	def field = { attrs, body ->
		String value = (attrs.value != null) ? attrs.value : ""
		Integer length = attrs.length as Integer
		String align = attrs.align ?: LEFT
		Boolean currency = attrs.currency ? attrs.currency as Boolean : false
		
		if (currency) {
			value = new DecimalFormat(CURRENCY_FORMAT).format(value.toDouble())
		}
		
		if (!attrs.length) {
			out << value
			return
		}
		
		if (value.length() >= length) {
			value = value.substring(0, length)
		} else {
			if (LEFT.equals(align)) {
				value = StringUtils.rightPad(value, length)
			} else {
				value = StringUtils.leftPad(value, length)
			}
		}
		
		out << value
	}

}
