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
	<main class="center main-fit main-cart">
		<c:if test="${empty CARTLIST}">
			<div class="text-center pt-5">No product has been booked. Lets buy yours product.</div>
		</c:if>
		<c:if test="${not empty CARTLIST}">
			<h4 class="mb-10">Cart list item:</h4>
			<div class="cart-container">
				<c:forEach var='bookedProduct' items='${CARTLIST}' varStatus="loop">
					<form class='cform cart${loop.count}' data-url="<c:url value='/api-booked-product'/>">
						<div class='cart-item'>
							<div class='cart-id pt-15'>
								<input data-no='${loop.count}' class='pay-form-inp checkbox block mr-10' type='checkbox'
									name='bookedProductIds' value='${bookedProduct.getId()}'>
								<span>${loop.count}</span>
							</div>
							<input type='hidden' name='orderId' value='-1'> <input type='hidden' name='id'
								value='${bookedProduct.getId()}'>
							<div class="product-item-img">
								<img src="${bookedProduct.getColorSource().getSource()}" style="width: 60px; height: 70px;" />
							</div>
							<div>${bookedProduct.getProduct().getName()}</div>
							<div>${bookedProduct.getColorSource().getColor().getName()}</div>
							<div>
								<c:if test="${bookedProduct.getQuantity() > 1}">
									<button class='btn-minus' type='button' data-no='${loop.count}'>
										<i class='fas fa-minus'></i>
									</button>
								</c:if>
								<c:if test="${bookedProduct.getQuantity() <= 1}">
									<button class='btn-minus' type='button' data-no='${loop.count}' disabled>
										<i class='fas fa-minus'></i>
									</button>
								</c:if>
								<label for='quantity${loop.count}'
									class='lbl-quantity${loop.count}'>${bookedProduct.getQuantity()}</label>
								<input data-no='${loop.count}' type='text' id='quantity${loop.count}' class='inp-quantity dspnone'
									name='quantity' value='${bookedProduct.getQuantity()}' readonly size='1'>
								<button class='btn-plus' type='button' data-no='${loop.count}'>
									<i class='fas fa-plus'></i>
								</button>
								<button type='submit' data-method="PUT">Change</button>
							</div>
							<div data-no='${loop.count}' class='total' id="total${loop.count}"
								data-price='${bookedProduct.getProduct().getPrice()}' data-total="">VND</div>
							<div>
								<button type='submit' data-method="DELETE">Delete</button>
							</div>
						</div>
					</form>
				</c:forEach>
			</div>
			<form data-url="<c:url value='/api-order'/>" data-act="main" id="payform">
				<input class="pay-form-inp" type="hidden" name="status" value="order" />
				<input class="pay-form-inp" type="hidden" name="userId" value="${USERMODEL.getId()}" />
				<h3 class="dspnone mt-10 mb-10" id="amount"></h3>
				<button class="dspnone" id='pay' type='submit' data-method="POST">Payment</button>
			</form>
		</c:if>
	</main>
</body>

</html>