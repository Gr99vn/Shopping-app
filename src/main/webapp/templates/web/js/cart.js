document.addEventListener("DOMContentLoaded", () => {
	const inpCounters = document.querySelectorAll(".inp-quantity");
	const minusBtns = document.querySelectorAll(".btn-minus");
	const plusBtns = document.querySelectorAll(".btn-plus");
	const checkboxs = document.querySelectorAll(".checkbox");
	const totals = document.querySelectorAll(".total");
	
	let counters  = [];
	inpCounters.forEach(inp => {
	    counters.push(inp.value);
	});
	
	totals.forEach(item => {
		let totalPrice = counters[item.dataset.no-1] * item.dataset.price;
		item.dataset.total = totalPrice;
		item.innerHTML = formatNumber(totalPrice) + " VND";
	});
	minusBtns.forEach(btn => {
	    btn.addEventListener("click", (e) => {
	        counters[btn.dataset.no-1]--;
	        inpCounters[btn.dataset.no-1].setAttribute("value", counters[btn.dataset.no-1]);
	        document.querySelector(".lbl-quantity" + btn.dataset.no).innerHTML = counters[btn.dataset.no-1];
	        if (counters[btn.dataset.no-1] === 1) {
	            btn.disabled = true;
	        }
	        totals.forEach(item => {
	            if (item.dataset.no === e.target.dataset.no) {
//	                item.innerHTML = formatNumber(counters[item.dataset.no-1] * item.dataset.price) + " VND";
	                let totalPrice = counters[item.dataset.no-1] * item.dataset.price;
	        		item.dataset.total = totalPrice;
	        		item.innerHTML = formatNumber(totalPrice) + " VND";
	            }
	        });
	    });
	});
	
	
	plusBtns.forEach(btn => {
	    btn.addEventListener("click", (e) => {
	        minusBtns.forEach(mbtn => {
	           if (mbtn.dataset.no === btn.dataset.no) {
	               mbtn.disabled = false;
	           } 
	        });
	        counters[btn.dataset.no-1]++;
	        inpCounters[btn.dataset.no-1].setAttribute("value", counters[btn.dataset.no-1]);
	        document.querySelector(".lbl-quantity" + btn.dataset.no).innerHTML = counters[btn.dataset.no-1];
	        totals.forEach(item => {
	            if (item.dataset.no === e.target.dataset.no) {
//	                item.innerHTML = formatNumber(counters[item.dataset.no-1] * item.dataset.price) + " VND";
	            	let totalPrice = counters[item.dataset.no-1] * item.dataset.price;
	        		item.dataset.total = totalPrice;
	        		item.innerHTML = formatNumber(totalPrice) + " VND";
	            }
	        });
	    }); 
	});
	
	let checkboxNum = 0;
	checkboxs.forEach(cb => {
	   cb.addEventListener('change', e => {
	       if (cb.checked === true) {
	           checkboxNum++;
	       } else if (cb.checked === false){
	           checkboxNum--;
	       }
	       if (checkboxNum > 0) {
	    	   let total = 0;
	    	   checkboxs.forEach(checkbox => {
	    		   if (checkbox.checked === true) {
	    	           let temp = parseInt(document.querySelector("#total"+checkbox.dataset.no).dataset.total);
	    	           if (!isNaN(temp)) {
	    	        	   total += temp;
	    	           }
	    	       }
	    	   });
	    	   document.querySelector("#amount").innerHTML = "Total amount: "+ formatNumber(total) + " VND";
	    	   document.querySelector("#amount").classList.remove("dspnone");
	           document.querySelector("#pay").classList.remove("dspnone");
	       } else {
	    	   document.querySelector("#amount").classList.add("dspnone");
	           document.querySelector("#pay").classList.add("dspnone");
	       }
	   });
	});
	
	const forms = document.querySelectorAll(".cform");
	forms.forEach(form => {
		if (form.dataset.act != "main") {
			let url = form.dataset.url;
			handleFormSubmit(form, url, success);
		}
	});
	
	const payform = document.querySelector("#payform");
	handleFormSubmit3(payform, payform.dataset.url, success);
	
});

function success(xhr, data) {
	if (data != "") {
		const json = JSON.parse(data);
		let message = "";
		if (json.success == "true") {
			let str = json.type;
			alert((str.charAt(0).toUpperCase() + str.slice(1)) + " cart item success!");
			if (json.redirect != null) {
				location.assign(json.redirect);
			}
		} else {
			if (json.redirect != null) {
				location.assign(json.redirect);
			}
		}
	}
	else {
		console.log("data = null");
	}
}

function handleFormSubmit3(form, url, success) {
	form.addEventListener("submit", (e) => {
		e.preventDefault();
		let divElement = document.querySelector(".main-cart");
		let data = getData(divElement);
		data = JSON.stringify(data);
		ajaxCall(document.activeElement.dataset.method, url, data, success, error);
	});
}

function isValidElement (input) {
	return input.name && input.value;
}

function isValidValue (input) {
	return (!['checkbox', 'radio'].includes(input.type) || input.checked);
}

function getData(divElement) {
	let inputs = divElement.querySelectorAll(".pay-form-inp");
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

function formatNumber (num) {
    return num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
}