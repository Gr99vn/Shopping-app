<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
<script src="<c:url value='/templates/web/js/wallet.js'/>"></script>
</head>
<body>
	<main class="center main-fit">
		<form class='wallet-detail' data-url="<c:url value='/api-booked-product'/>">
			<div class='wallet-img'>
				<c:forEach var="colorSource" items="${PRODUCT.getColorSources()}"
						varStatus="forloop">
						<c:if test="${forloop.count == 1}">
							<img class='source active' data-act="${colorSource.getId()}" src='${colorSource.getSource()}'>
						</c:if>
						<c:if test="${forloop.count > 1}">
							<img class='source inactive' data-act="${colorSource.getId()}"  src='${colorSource.getSource()}'>
						</c:if>
					</c:forEach>
			</div>
			<div class='wallet-info'>
				<h4>Tên sản phẩm: ${PRODUCT.getName()}</h4>
				<h4>Giá: ${PRODUCT.getPrice()}đ</h4>
				<div>
					<h4>Màu sắc:</h4>
					<c:forEach var="colorSource" items="${PRODUCT.getColorSources()}"
						varStatus="forloop">
						<c:if test="${forloop.count == 1}">
							<input class="color-radio" type='radio' id='color1' name="colorSourceId"
								value='${colorSource.getId()}' checked>
							<label for="color1">${colorSource.getColor().getName()}</label>
							<br>
						</c:if>
						<c:if test="${forloop.count > 1}">
							<input class="color-radio" type='radio' id='color${forloop.count}' name='colorSourceId'
								value='${colorSource.getId()}'>
							<label for="color${forloop.count}">${colorSource.getColor().getName()}</label>
							<br>
						</c:if>
					</c:forEach>
				</div>
				<div>
					<h4>Mô tả sản phẩm:</h4>
					<p>${PRODUCT.getDes()}</p>
				</div>
				<div class='quantity'>
					Số lượng:
					<button class='btn-minus' type='button'>
						<i class='fas fa-minus'></i>
					</button>
					<label for='quantity' class='lbl-quantity'>1</label> <input
						type='text' id='quantity' class='inp-quantity dspnone'
						name='quantity' value='1' readonly size='1'>
					<button class='btn-plus' type='button'>
						<i class='fas fa-plus'></i>
					</button>
				</div>
				<input type="hidden" name="productId" value="${PRODUCT.getId()}">
				<button type='submit' data-method="POST">
					<i class='fas fa-cart-plus'></i> Buy now
				</button>
			</div>
		</form>
	</main>
</body>
</html>