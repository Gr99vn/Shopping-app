document.addEventListener("DOMContentLoaded", () => {
	document.querySelectorAll(".menu-item").forEach(item => {
		item.addEventListener("click", e => {
			document.querySelector("."+e.target.dataset.act).classList.toggle("inactive");
		})
	});
});