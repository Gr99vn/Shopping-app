<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
	<script src="<c:url value='/templates/web/js/register.js'/>"></script>
</head>

<body>
	<main class="center register main-fit">
		<c:if test="${not empty message}">
			<div class="alert" role="alert">${message}</div>
		</c:if>
		<form class="register-form" data-url="<c:url value='/api-user'/>"
			data-redirect="<c:url value='/login?action=login'/>">
			<div class="reg-col-1">
				<lable for="name">Name</lable>
				<input type="text" name="name" class="login-inp" autocomplete="off" pattern="[A-Za-z]{3, 50}"
					title="Text only length: 3 - 50.">
				<lable for="address">Address</lable>
				<input type="text" name="address" class="login-inp" autocomplete="off" pattern="[A-Za-z0-9,- ]{3,}"
					title="Accept alphabet, number and (- ,) character.">
				<lable for="phone">Phone</lable>
				<input type="text" name="phone" class="login-inp" autocomplete="off" pattern="[0-9]{9,11}"
					title="9 - 11 number.">
			</div>
			<div class="reg-col-2">
				<lable for="email">Email</lable>
				<input type="text" name="email" class="login-inp" autocomplete="off" pattern="\S+@\S+\.\S+">
				<lable for="username">Username</lable>
				<input type="text" name="username" class="login-inp" autocomplete="off" pattern="[A-Za-z0-9]{4,25}"
					title="Alphabet and number, 4 - 25 character length.">
				<lable for="password">Password</lable>
				<input type="password" name="password" class="login-inp" autocomplete="off"
					pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$" title="Minimum eight characters, at least one letter, one number and one special character!

">
				<input type="submit" value="Register" data-method="POST" />
				<a href="<c:url value='/login?action=login'/>">Or login here.</a>
			</div>
		</form>
	</main>
</body>

</html>