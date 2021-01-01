document.addEventListener("DOMContentLoaded", () => {
	const dforms = document.querySelectorAll(".delete-form");
	dforms.forEach(dform => {
		handleFormSubmit(dform, dform.dataset.url, success3);
	});
});


function success3 (xhr, data) {
	if (data != "") {
		const json = JSON.parse(data);
		if (json.success == "true") {
			const productItem = document.querySelectorAll(".product-item");
			productItem.forEach(item => {
				if (item.dataset.csid == json.id) {
					item.classList.add("inactive");
				}
			});
			alert("Delete product success!");
		}
	}
	else {
		console.log("data = null");
	}
}