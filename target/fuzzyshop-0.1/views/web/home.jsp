<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
	<main class="center main-fit">
		<div class="wallet-container">
			<c:forEach var="product" items="${PRODUCTLIST}">
				<div class='wallet'>
					<a href="<c:url value='/product?pid=${product.getId()}'/>"> 
					<img src="${product.getDefaultSource()}" alt="">
					</a>
					<div class="wallet-info">
						<h4 class="wallet-name">
							<a class='title'
								href="<c:url value='/product?pid=${product.getId()}'/>">${product.getName()}</a>
						</h4>
						<h5>${product.getPrice()}</h5>
					</div>
				</div>
			</c:forEach>
		</div>
	</main>
</body>
</html>