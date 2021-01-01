<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><de:title default="Admin Home" /></title>
    <link
      href="<c:url value = '/templates/admin/css/style.css'/>"
      rel="stylesheet"
    />
  </head>
  <!--Font awesome-->
  <link
    rel="stylesheet"
    href="<c:url value = '/templates/general/fontawesome-free-5.13.0-web/css/all.min.css'/>"
  />
  <!-- Custom Js file -->
  <de:head />
  <body>
    <!-- Header -->
    <%@ include file="/common/admin/header.jsp"%>
    <main class="center main-fit">
      <%@ include file="/common/admin/menubar.jsp"%>
      <!-- Body -->
      <de:body />
      <!-- Footer -->
    </main>
    <%@ include file="/common/admin/footer.jsp"%>
    <script src="<c:url value='/templates/admin/js/main.js'/>"></script>
  </body>
</html>
