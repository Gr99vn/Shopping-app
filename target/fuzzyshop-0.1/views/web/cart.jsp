<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<script src="<c:url value='/templates/web/js/cart.js'/>"></script>
</head>
<body>
	<main class="center main-fit cart-container">
		<form class='cart mr-10' data-url="<c:url value='/api-order'/>">
			<c:forEach var='bookedProduct' items='${CARTLIST}' varStatus="loop">
				<div class='cart-id'>
					<div class="pt-5">
						<input class='checkbox block mr-10' type='checkbox' name='id' value='${bookedProduct.getId()}'> <span>${loop.count}</span>
					</div>
				</div><br>
			</c:forEach>
			<button id='pay' type='submit' data-method="POST" disabled>Payment</button>
		</form>
		
		<div>
			<c:forEach var='bookedProduct' items='${CARTLIST}' varStatus="loop">
				<form class='cart${loop.count}' data-url="<c:url value='/api-booked-product'/>" >
					<div class='cart-item'>
						<input type='hidden' name='id' value='${bookedProduct.getId()}'>
						<div class="pt-15 product-item-img"><img src="${bookedProduct.getColorSource().getSource()}" width="50" height="50"/></div>
						<div class="pt-15">${bookedProduct.getProduct().getName()}</div>
						<div class="pt-15">${bookedProduct.getColorSource().getColor().getName()}</div>
						<div class="pt-15">
							<c:if test="${bookedProduct.getQuantity() > 1}">
								<button class='btn-minus' type='button' data-no='${loop.count}'><i class='fas fa-minus'></i></button>
							</c:if>
							<c:if test="${bookedProduct.getQuantity() <= 1}">
								<button class='btn-minus' type='button' data-no='${loop.count}' disabled><i class='fas fa-minus'></i></button>
							</c:if>
							<label for='quantity${loop.count}' class='lbl-quantity${loop.count}'>${bookedProduct.getQuantity()}</label>
							<input data-no='${loop.count}' type='text' id='quantity${loop.count}' class='inp-quantity dspnone' name='quantity' value='${bookedProduct.getQuantity()}' readonly size='1'>
							<button class='btn-plus' type='button' data-no='${loop.count}'><i class='fas fa-plus'></i></button>
							<button type="submit" data-method="PUT">Change</button>
						</div>		
						<div data-no='${loop.count}' class='pt-15 total' data-price='${bookedProduct.getProduct().getPrice()}'> VND</div>
						<div class="pt-15"><button type="submit" data-method="DELETE">Delete</button></div>
					</div><br>
				</form>
			</c:forEach>
		</div>
		
	</main>
</body>
</html>