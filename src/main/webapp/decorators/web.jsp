<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><de:title default="Home" /></title>

    <!--Font awesome-->
    <link
      rel="stylesheet"
      href="<c:url value = '/templates/general/fontawesome-free-5.13.0-web/css/all.min.css'/>"
    />
    <!-- Custom styles -->
    <link
      href="<c:url value = '/templates/web/css/style.css'/>"
      rel="stylesheet"
    />
    <!-- Custom Js file -->
    <de:head />
  </head>
  <body>
    <!-- Header -->
    <%@ include file="/common/web/header.jsp"%>

    <!-- Page Content -->
    <de:body />

    <!-- Footer -->
    <%@ include file="/common/web/footer.jsp"%>
    <script src="<c:url value='/templates/web/js/ajaxSendDataToApi.js'/>"></script>
  </body>
</html>
