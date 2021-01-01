<%@page import="com.javaweb.utils.ComputeUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order</title>
<link href="<c:url value = '/templates/admin/css/style2.css'/>" rel="stylesheet">
<script src="<c:url value= '/templates/admin/js/ajaxSendDataToApi.js' />"></script>
</head>
<body>
	<div class="main-fit main-content">
		<div id="order-inf" data-type="${TYPE}" data-status="${STATUS}">List ${STATUS} orders</div>
		<c:if test="${empty ORDERLIST}">
		<div>No product was in this list.</div>
		</c:if>
		<c:forEach var="order" items="${ORDERLIST}">
			<div class="order-action mt-10 text-center">
				<c:if test="${STATUS.equals('order')}">
					<fmt:formatDate var="fmtOrderTime" type="both" timeStyle="short" value="${order.getOrderTime()}" /> 
					<div>Time ${STATUS}: ${fmtOrderTime}</div>
				</c:if>
				<c:if test="${STATUS.equals('receive')}">
					<fmt:formatDate var="fmtReceiveTime" type="both" timeStyle="short" value="${order.getReceiveTime()}" /> 
					<div>Time ${STATUS}: ${fmtReceiveTime}</div>
				</c:if>
				<c:if test="${STATUS.equals('deliver')}">
					<fmt:formatDate var="fmtDeliverTime" type="both" timeStyle="short" value="${order.getDeliverTime()}" /> 
					<div>Time ${STATUS}: ${fmtDeliverTime}</div>
				</c:if>
				<c:if test="${STATUS.equals('confirm')}">
					<fmt:formatDate var="fmtConfirmTime" type="both" timeStyle="short" value="${order.getConfirmTime()}" /> 
					<div>Time ${STATUS}: ${fmtConfirmTime}</div>
				</c:if>
			</div>
			<div class="order-item">
				<div class="order-detail">
					<div class="product-list">
						<div class="client-info">
							<div>Client: ${order.getUser().getName()}</div>
							<div>Address: ${order.getUser().getAddress()}</div>
							<div>Phone: ${order.getUser().getPhone()}</div>
						</div>
						<c:forEach var='bookedProduct'
							items='${order.getBookedProducts()}' varStatus="loop">
							<fmt:formatNumber pattern="#,###" value="${bookedProduct.getProduct().getPrice()}" var="formattedPrice" />
							<c:if test="${loop.count>1}">
								<hr>
							</c:if>
							<div class='product-item'>
								<div>${loop.count}</div>
								<div class="product-item-img">
									<img src="${bookedProduct.getColorSource().getSource()}"
										style="width: 60px; height: 70px;" />
								</div>
								<div>
									<div>${bookedProduct.getProduct().getName()}</div>
									<div class="detail mt-10">
										<div>Color:
											${bookedProduct.getColorSource().getColor().getName()}</div>
										<div>Quantity:
											${bookedProduct.getQuantity()}</div>
										<div class='total'>Price:
											${formattedPrice} VND</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="order-action">
						<fmt:formatNumber pattern="#,###" value="${ComputeUtil.totalAmount(order)}" var="formattedTotal" />
						<div class="mb-10"><h4>Total: ${formattedTotal} VND</h4></div>
						<c:if test="${STATUS.equals('order')}">
							<form data-url="<c:url value='/api-admin-order'/>">
								<input type="hidden" name="id" value="${order.getId()}">
								<input type="hidden" name="status" value="receive">
								<button class="mb-10" data-method="PUT">Receive</button>
								<button data-method="DELETE">Refuse</button>
							</form>
						</c:if>
						<c:if test="${STATUS.equals('receive')}">
							<form data-url="<c:url value='/api-admin-order'/>">
								<input type="hidden" name="id" value="${order.getId()}">
								<input type="hidden" name="status" value="deliver">
								<button data-method="PUT">Deliver</button>
							</form>
						</c:if>
						<c:if test="${STATUS.equals('deliver')}">
							<form data-url="<c:url value='/api-admin-order'/>">
								<input type="hidden" name="id" value="${order.getId()}">
								<input type="hidden" name="status" value="confirm">
								<button data-method="PUT">Confirm</button>
							</form>
						</c:if>
					</div>
				</div>
			</div>

		</c:forEach>
	</div>
	<script type="text/javascript">
		const orderInf = document.querySelector("#order-inf");
		document.querySelector("."+orderInf.dataset.type+"-sub-menu").classList.remove("inactive");
		document.querySelector("."+orderInf.dataset.status+"-item").classList.add("active");
		
		const forms = document.querySelectorAll("form");
		forms.forEach(form => {
			handleFormSubmit(form, form.dataset.url, success);
		});
		
		function success(xhr, data) {
			if (data != "") {
				const json = JSON.parse(data);
				if (json.type == "update" && json.result == "success" && json.status != null) {
					alert("Update order success!");
					location.assign("/fuzzyshop/admin-orders?status="+json.status);
				} else if (json.type == "delete" && json.result == "success") {
					alert("Refuse order success!");
				}
			}
			else {
				console.log("data = null");
			}
		}

	</script>
</body>
</html>