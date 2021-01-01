<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><de:title default="Admin Home" /></title>
<link href="<c:url value = '/templates/admin/css/styles.css'/>"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css"
	rel="stylesheet" crossorigin="anonymous" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<!-- Header -->
	<%@ include file="/common/admin/header.jsp"%>
	<!-- Page content -->
	<div id="layoutSidenav">
		<%@ include file="/common/admin/menubar.jsp"%>
		<div id="layoutSidenav_content">
			<!-- Body -->
			<main> <de:body /> </main>
			<!-- Footer -->
			<%@ include file="/common/admin/footer.jsp"%>>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="<c:url value = '/templates/admin/js/scripts.js'/> "></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script
		src="<c:url value = '/templates/admin/assets/demo/chart-area-demo.js'/>"></script>
	<script
		src="<c:url value = '/templates/admin/assets/demo/chart-bar-demo.js'/>"></script>
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"
		crossorigin="anonymous"></script>
	<script src="<c:url value = '/templates/admin/assets/demo/datatables-demo.js'/>"></script>
</body>
</html>