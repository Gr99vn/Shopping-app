document.addEventListener("DOMContentLoaded", () => {
	const inpCounter = document.querySelector(".inp-quantity");
	const btnMinus = document.querySelector(".btn-minus");
	const btnPlus = document.querySelector(".btn-plus");
	const imgs = document.querySelectorAll(".source"); 
	const colorRadios = document.querySelectorAll(".color-radio");
	let counter = inpCounter.getAttribute("value");
	
	btnMinus.disabled = true;
	btnMinus.addEventListener("click", (e) => {
	    if (counter === 2) {
	        counter--;
	        inpCounter.setAttribute("value", counter);
	        document.querySelector(".lbl-quantity").innerHTML = counter;
	        btnMinus.disabled = true;
	    }
	    if (counter > 2) {
	        counter--;
	        inpCounter.setAttribute("value", counter);
	        document.querySelector(".lbl-quantity").innerHTML = counter;
	    }
	    console.log(counter);
	});
	
	btnPlus.addEventListener("click", (e) => {
	    counter++;
	    if (counter > 1) {
	        btnMinus.disabled = false;
	    }
	    inpCounter.setAttribute("value", counter);
	    document.querySelector(".lbl-quantity").innerHTML = counter;
	    console.log(counter);
	});
	
	colorRadios.forEach(radio => {
		radio.addEventListener("change", e => {
			console.log(radio.checked);
			if (radio.checked) {
				console.log(radio.value);
				imgs.forEach(img => {
					if (img.dataset.act != radio.value) {
						img.classList.remove("active");
						img.classList.add("inactive");
					} else {
						img.classList.remove("inactive");
						img.classList.add("active");
					}
				});
			}
		});
	});
	
	const form = document.querySelector(".wallet-detail");
	handleFormSubmit(form, form.dataset.url, success);
	
});

function success(xhr, data) {
	if (data != "") {
		const json = JSON.parse(data);
		let message = "";
		if (json.success === "true") {
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