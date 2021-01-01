<%@ include file = "/common/taglib.jsp" %>
<c:set var="pageMaxItem" value="12"/>
<c:redirect url = "/home?pageMaxItem=${pageMaxItem}&currentPage=1"/>
