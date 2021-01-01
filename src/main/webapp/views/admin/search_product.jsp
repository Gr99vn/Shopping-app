<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Product</title>
<script src="<c:url value= '/templates/admin/js/ajaxSendDataToApi.js' />"></script>
<script src="<c:url value= '/templates/admin/js/searchProduct.js' />"></script>
</head>
<body>
	<div class="main-fit product-search">
		<form class="search-form mb-10">
			<input type="text" name="name" placeholder="Enter product name">
			<button >Search</button>
		</form>
		<c:if test="${not empty PRODUCTLIST}">
		<div class="product-container">
			<c:forEach var="product" items="${PRODUCTLIST}" varStatus="loop">
				<div class='product-item' data-csid="${product.getId()}">
					<div>${loop.count}</div>
					<div class='product-img'>
						<img src='${product.getDefaultSource()}' alt='' width="40" height="100">
					</div>
					<div class='product-info'>
						<div>${product.getName()}</div>
						<div>Price: ${product.getPrice()} VND</div>
						<div>Colors: 
							<c:forEach var="colorSource" items="${product.getColorSources()}" varStatus="loop">
								<c:if test="${loop.count == 1}">
									${colorSource.getColor().getName()}
								</c:if>
								<c:if test="${loop.count != 1}">
									, ${colorSource.getColor().getName()}
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class='product-action'>
						<div class='mb-10'><a href="<c:url value='/admin-product-modify?id=${product.getId()}'/>">Modify</a></div>
						<form class="delete-form" data-url="<c:url value='/api-admin-product'/>">
							<input type="hidden" name="id" value="${product.getId()}"> 
							<button type='submit' data-method='DELETE'>Delete</button>
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
		</c:if>
		
	</div>
</body>
</html>