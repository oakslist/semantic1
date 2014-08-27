function myFilterFormAction(elemId, formId) {
	var element = document.getElementById(elemId);
	var form = document.getElementById(formId);
	var url = form.action;
	element.onchange = function() {
		form.action = url + element.value;
	};
}
