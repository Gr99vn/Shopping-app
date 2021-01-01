document.addEventListener("DOMContentLoaded", () => {
	const form = document.querySelector(".login-form");
	handleFormSubmit(form, form.dataset.url, success);
});

function success(xhr, data) {
	if (data != "") {
		const json = JSON.parse(data);
		let message = "";
		if (json.success === "true") {
			message = "Login successfully";
			alert(message);
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