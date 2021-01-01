document.addEventListener("DOMContentLoaded", () => {
	const form = document.querySelector(".register-form");
	handleFormSubmit(form, form.dataset.url, success);
});

function success(xhr, data) {
	if (data !== "") {
		const json = JSON.parse(data);
		let message = "";
		if (json.success === "true") {
			message = "Register successfully";
			alert(message);
			location.assign(document.querySelector(".register-form").dataset.redirect);
		} else {
			message = json.detail;
			alert(message);
		}
	}
}
