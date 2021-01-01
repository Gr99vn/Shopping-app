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
	   item.innerHTML = counters[item.dataset.no-1] * item.dataset.price+ "VND";
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
	                item.innerHTML = counters[item.dataset.no-1] * item.dataset.price+ "VND";
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
	                item.innerHTML = counters[item.dataset.no-1] * item.dataset.price+ "VND";
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
	           document.querySelector("#pay").disabled = false;
	       } else {
	           document.querySelector("#pay").disabled = true;
	       }
	   });
	});
	
	const forms = document.querySelectorAll("form");
	forms.forEach(form => {
		let url = form.dataset.url;
		handleFormSubmit(form, url, success);
	});
	
});

function success(xhr, data) {
	if (data != "") {
		const json = JSON.parse(data);
		let message = "";
		if (json.success === "true") {
			console.log("success");
			if (json.redirect != null) {
				location.assign(json.redirect);
			}
		} else {
			console.log("fail");
			if (json.redirect != null) {
				location.assign(json.redirect);
			}
		}
	}
	else {
		console.log("data = null");
	}
}