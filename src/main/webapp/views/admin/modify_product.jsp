<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Or Delete Product</title>
<script src="<c:url value= '/templates/admin/js/ajaxSendDataToApi.js' />"></script>
<script src="<c:url value= '/templates/admin/js/modifyProduct.js' />"></script>
</head>
<body>
	<div class="main-content main-fit">
		<div id="product-form">
			<form class="form-left" data-url="<c:url value='/api-admin-product'/>">
				<input type="hidden" name="id" value="${PRODUCT.getId()}">
				<label for="name">Name</label><br>
				<input class="mb-20" type="text" id="name" name="name" autocomplete="off" size="40" value="${PRODUCT.getName()}"><br>
				<label for="price" class="mr-10">Price</label>
				<input class="mb-20 mr-10" type="text" id="price" name="price" autocomplete="off" value="${PRODUCT.getPrice()}"> VND<br>
				<input type="hidden" id="defaultImg" name="defaultSource" value="" placeholder="Default image link">
				<label for="file1" class="upload-file-btn">Default Image</label> 
				<input class="mb-20" type="file" id="file1" name="defaultSource" accept="image/png, image/jpeg"><br>
				<textarea class="mb-20" id="description" rows="8" cols="42">${PRODUCT.getDes()}</textarea>
				<input type="hidden" name="des" id="des" value="${PRODUCT.getDes()}">
				<button class="form-submit"  data-method="PUT">Update Product</button>
			</form>
			<div class="form-right">
				<form class="color-source-form" data-url="<c:url value='/api-admin-color-source'/>">
					<input type="hidden" name="productId" value="${PRODUCT.getId()}">
					<label for="colors" class="mr-10">Color</label>
					<select name="colorId" id="colors" class="mr-10">
					<c:forEach var="color" items="${COLORS}" varStatus="loop">
					  <option value="${color.getId()}">${color.getName()}</option>
					</c:forEach>	 
					</select>
					<input type="hidden" name="colorId" id="color-id">
					<input type="hidden" id="imglink" name="source">
					<label for="file2" class="upload-file-btn">
		            	Image
		          	</label>
		          	<input type="file" id="file2" name="source">
					<button class="add-product-color" data-method="POST">Add</button>
				</form>
				<div class="color-source mt-10">
					<c:forEach var="item" items="${PRODUCT.getColorSources()}" varStatus="loop">
						<div class="color-source-item mb-10" data-csid="${item.getId()}">
							<div class='color-source-name mr-10'>${loop.count}</div>
							<div class='color-source-name mr-10'>${item.getColor().getName()}</div>
							<div class='color-source-link'><img src ='${item.getSource()}' style="width:60px;height:70px;"></div>
							<form class="delete-form" data-url="<c:url value='/api-admin-color-source'/>">
								<input type="hidden" name="id" value="${item.getId()}">
								<button data-method="DELETE">Delete</button>
							</form>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>