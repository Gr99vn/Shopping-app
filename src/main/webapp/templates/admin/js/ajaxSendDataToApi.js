function handleFormSubmit(form, url, success) {
	form.addEventListener("submit", (e) => {
		e.preventDefault();
		let data = getFormData(form);
		data = JSON.stringify(data);
		ajaxCall(document.activeElement.dataset.method, url, data, success, error);
	});
}

function isValidElement (input) {
	return input.name && input.value;
}

function isValidValue (input) {
	return (!['checkbox', 'radio'].includes(input.type) || input.checked) && input.type != 'file';
}

function getFormData(form) {
	let inputs = form.querySelectorAll("input");
	inputs = [].filter.call(inputs, (input) => {
		return isValidElement(input) && isValidValue(input);
	});
	return [].reduce.call(inputs, (data, input) => {
		let value = (isNaN(parseInt(input.value))) ? input.value : parseInt(input.value);
		if (data[input.name] == null) {
			if (input.type === "checkbox") {
				data[input.name] = [value];
			} else {
				data[input.name] = value;
			}
        } else {
        	let last = [data[input.name]];
        	data[input.name] = [].concat(...last);
            data[input.name] = data[input.name].concat([value]);
        }
        return data;
	}, {});
}

function ajaxCall(method, url, data, success, error) {
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4) {
        	if (xhr.status == 200){
                success(xhr, xhr.responseText);
            } else {
            	error(xhr.status);
            }
        }
    }
    xhr.open(method, url, true);
    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8"); 
    xhr.send(data);
}

// function success() must be implement

function error(status) {
	console.log("error: "+ status);
}