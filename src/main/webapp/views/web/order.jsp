<%@page import="com.javaweb.utils.ComputeUtil"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<!doctype html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport"
		content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Order</title>
</head>

<body>
	<main class="main-fit center container">
		<c:if test="${empty ORDERLIST}">
			<div>No product was ordered.</div>
		</c:if>
		<c:forEach var="order" items="${ORDERLIST}" varStatus="loop">
			<c:if test="${loop.index > 0}">
				<br>
			</c:if>
			<fmt:formatDate var="fmtOrderTime" type="both" timeStyle="short" value="${order.getOrderTime()}" />  
			<div class="order-action text-center">
				<div>Order time: ${fmtOrderTime}</div>
			</div>
			<div class="order-item">
				<div class="order-detail">
					<div class="product-list">
						<c:forEach var='bookedProduct' items='${order.getBookedProducts()}' varStatus="loop">
							<fmt:formatNumber pattern="#,###" value="${bookedProduct.getProduct().getPrice()}" var="formattedPrice" />
							<c:if test="${loop.count>1}">
								<hr>
							</c:if>
							<div class='product-item'>
								<div>${loop.count}</div>
								<div class="product-item-img">
									<a href="<c:url value='/product?pid=${bookedProduct.getProduct().getId()}'/>">
										<img src="${bookedProduct.getColorSource().getSource()}" style="width: 60px; height: 70px;" />
									</a>
								</div>
								<div>
									<div class="link-name">
										<h3>
											<a href="<c:url value='/product?pid=${bookedProduct.getProduct().getId()}'/>">
												${bookedProduct.getProduct().getName()}
											</a></h3>
									</div>
									<div class="detail mt-10">
										<div>Color:
											${bookedProduct.getColorSource().getColor().getName()}</div>
										<div>Quantity: ${bookedProduct.getQuantity()}</div>
										<div class='total'>Price:
											${formattedPrice} VND</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="order-status">
						<fmt:formatNumber pattern="#,###" value="${ComputeUtil.totalAmount(order)}" var="formattedTotal" />
						<h4 class="mb-10">Total: ${formattedTotal} VND</h4>
						<p class="mb-10">Status: ${order.getStatus()}</p>
						<form data-url="<c:url value='/api-order'/>">
							<input type="hidden" name="id" value="${order.getId()}">
							<c:if test="${order.getStatus().equals('order')}">
								<button type="submit" data-method="DELETE">Cancel</button>
							</c:if>
						</form>
					</div>
				</div>
			</div>
		</c:forEach>
	</main>
	<script type="text/javascript">
		document.addEventListener("DOMContentLoaded", () => {
			const forms = document.querySelectorAll("form");
			forms.forEach(form => {
				handleFormSubmit(form, form.dataset.url, success);
			});
		})
		function success(xhr, data) {
			if (data != "") {
				const json = JSON.parse(data);
				console.log(json.result);
				if (json.redirect != null) {
					location.assign(json.redirect);
				}
			}
			else {
				console.log("data = null");
			}
		}
	</script>
</body>

</html>