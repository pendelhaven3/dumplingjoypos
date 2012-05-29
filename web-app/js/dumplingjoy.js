function focusOnLoad(id) {
	$(document).ready( function() {
		$("#" + id).focus()
	});	
}
function allCaps(textField) {
	textField.value = textField.value.toUpperCase();
}
function isInteger(value) {
	var regex = /^\d+$/ 
	return regex.test(value)	
}