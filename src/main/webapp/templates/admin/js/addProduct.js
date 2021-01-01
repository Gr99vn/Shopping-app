let count = 0;
document.addEventListener("DOMContentLoaded", () => {
	
	document.querySelector("#file1").onchange = function(e) {
	    const file = e.target.files[0];
	    const fileReader = new FileReader();
	    fileReader.readAsDataURL(file);
	    fileReader.onload = () => {
	      const arrayBuffer = fileReader.result;	        
	      const img = {
	        name: file.name,
	        type: file.type,
	        size: file.size,
	        binary: arrayBuffer
	      }
	      document.querySelector("#defaultImg").value = img.binary;
	    }
	  };
	  document.querySelector("#file2").onchange = function(e) {
		    const file = e.target.files[0];
		    const fileReader = new FileReader();
		    fileReader.readAsDataURL(file);
		    fileReader.onload = () => {
		      const arrayBuffer = fileReader.result;	        
		      const img = {
		        name: file.name,
		        type: file.type,
		        size: file.size,
		        binary: arrayBuffer
		      }
		      document.querySelector("#imglink").value = img.binary;
		    }
		  };

	const form = document.querySelector(".form-left");
	form.querySelector("#description").addEventListener("input", ()=>{
		form.querySelector("#des").value = form.querySelector("#description").value;
	});
	
	handleFormSubmit(form, form.dataset.url, success);
	
	
	const colorSourceForm = document.querySelector(".color-source-form");
	document.querySelector(".add-product-color").addEventListener("click", () => {
		colorSourceForm.querySelector("#color-id").value = colorSourceForm.querySelector("#colors").value;
	});
	handleFormSubmit(colorSourceForm, colorSourceForm.dataset.url, success2);
	
	const dforms = document.querySelectorAll(".delete-form");
	dforms.forEach(dform => {
		handleFormSubmit(dform, dform.dataset.url, success3);
	});
	
});


function success(xhr, data) {
	if (data != "") {
		const json = JSON.parse(data);
		if (json.id != null) {
			document.querySelector("#product-id").value = parseInt(json.id);
			document.querySelector(".form-right").classList.remove("dspnone");
			alert("Add new product success!");
		}
	}
	else {
		console.log("data = null");
	}
}

function success2 (xhr, data) {
	if (data != "") {
		const json = JSON.parse(data);
		count++;
		
		const colorSourceDiv = document.querySelector(".color-source");
		let elem = document.createElement("div");
		elem.classList.add("color-source-item");
		elem.classList.add("mb-10");
		elem.setAttribute("data-csid", json.id);
		elem.innerHTML = 
			"<div class='color-source-name mr-10'>"+count +"</div>" +
				"<div class='color-source-name mr-10'>"+json.color.name+"</div>" +
				"<div class='color-source-link'><img src='"+json.source+"' style='width: 60px;height: 70px;'></div>" +
				"<form class='delete-form' data-url='/fuzzyshop/api-admin-color-source'>" +
					"<input type='hidden' name='id' value='"+json.id+"'>"+
					"<button data-method='DELETE'>Delete</button>" +
				"</form>";
		colorSourceDiv.appendChild(elem);
		const dform = elem.querySelector(".delete-form");
		handleFormSubmit(dform, dform.dataset.url, success3);
		alert("Add product color success!");
	}
	else {
		console.log("data = null");
	}
}

function success3 (xhr, data) {
	if (data != "") {
		const json = JSON.parse(data);
		if (json.success == "true") {
			const colorSourceItems = document.querySelectorAll(".color-source-item");
			colorSourceItems.forEach(item => {
				if (item.dataset.csid == json.id) {
					item.classList.add("dspnone");
				}
			});
			alert("delete product color success");
		}
	}
	else {
		console.log("data = null");
	}
}