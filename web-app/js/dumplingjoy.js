function focusOnLoad(id) {
	$(document).ready( function() {
		$("#" + id).focus()
	});	
}
function allCaps(textField) {
	textField.value = textField.value.toUpperCase();
}
function isPositiveInteger(value) {
	var regex = /^\d+$/ 
	return regex.test(value)	
}
function isPositiveDecimal(value) {
	var regex = /^\d+(\.\d{1,2})?$/
	return regex.test(value)	
}