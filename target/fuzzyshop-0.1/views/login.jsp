<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<script src="<c:url value='/templates/web/js/login.js'/>"></script>
</head>
<body>
	<main class="center main-fit login">
		<c:if test="${not empty message}">
			<c:if test="${not empty type}">
				<div class="${type}" role="alert">${message}</div>
			</c:if>
			<c:if test="${empty type}">
				<div class="alert" role="alert">${message}</div>
			</c:if>
		</c:if>
		<form class="login-form" data-url="<c:url value='/api-login'/>">
			<lable for="username">
			<i class="fas fa-user"></i> Username</lable>
			<input type="text" name="username" class="login-inp"
				autocomplete="off">
			<lable for="password">
			<i class="fas fa-lock"></i> Password</lable>
			<input type="password" name="password" class="login-inp"
				autocomplete="off"> 
			<input type="submit" value="Login" class="login-inp" data-method="POST"> 
			<a href="<c:url value="/register?action=register"/>">Create new account here.</a>
		</form>
	</main>
</body>
</html>