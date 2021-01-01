<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:set var="pageMaxItem" value ="12"/>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Home Page</title>
</head>

<body>
	<main class="center main-fit">
		<div class="function">
			<c:if test="${MODEL.getTotalPage() == 0}">
	        	<c:set var="url" value="/search"/>
	        </c:if>
	        <c:if test="${MODEL.getTotalPage() > 0}">
	        	<c:set var="url" value="/home"/>
	        </c:if>
			<form action="<c:url value='${url}'/>">
				<label for="sortBy">Sort by</label>
				<select name="sortBy" id="sortBy" class="mr-10">
					<c:if test="${!MODEL.getSortBy().equals('price')}">
						<option value="product_name" selected>Name</option>
    	            	<option value="price">Price</option>
					</c:if>
					<c:if test="${MODEL.getSortBy().equals('price')}">
						<option value="product_name">Name</option>
    	            	<option value="price" selected>Price</option>
					</c:if>
	            </select>
	            <label for="sortName">Sort type</label>
				<select name="sortName" id="sortName" class="mr-10">
					<c:if test="${!MODEL.getSortName().equals('desc')}">
						<option value="asc" selected>Ascending</option>
    	            	<option value="desc">Descent</option>
					</c:if>
					<c:if test="${MODEL.getSortName().equals('desc')}">
						<option value="asc">Ascending</option>
    	            	<option value="desc" selected>Descent</option>
					</c:if>
	            </select>
	            <c:if test="${MODEL.getCurrentPage() > 0}">
				<input type="hidden" name="currentPage" value="${MODEL.getCurrentPage()}">
	            <input type="hidden" name="pageMaxItem" value="12">
	            </c:if>
	            <c:if test="${MODEL.getTotalPage() <= 0}">
				<input type="hidden" name="name" value="${searchData}">
	            </c:if>
	            <button type="submit"><i class="fas fa-running"></i></button>
			</form>
		</div>
		<div class="wallet-container">
			<c:forEach var="product" items="${MODEL.getResultList()}">
				<fmt:formatNumber pattern="#,###" value="${product.getPrice()}" var="formattedPrice" />
				<div class='wallet'>
					<a href="<c:url value='/product?pid=${product.getId()}'/>">
						<img src="${product.getDefaultSource()}" alt="" style="height: 220px">
					</a>
					<div class="wallet-info">
						<h4 class="wallet-name">
							<a class='title' href="<c:url value='/product?pid=${product.getId()}'/>">${product.getName()}</a>
						</h4>
						<h5>${formattedPrice} VND</h5>
					</div>
				</div>
			</c:forEach>
		</div>
		<c:if test="${MODEL.getTotalPage() > 0}">
		<div class="pagination" data-totalpage="${MODEL.getTotalPage()}" data-maxpageitem="${MODEL.getPageMaxItem()}" data-currentpage="${MODEL.getCurrentPage()}">
			<a class="control-item prev-control"><i class="fas fa-caret-left"></i></a>
			<c:forEach var="index" begin="1" end="${MODEL.getTotalPage()}">
			<c:if test="${index == MODEL.getCurrentPage()}">
				<a class="pagin-item active" data-pageindex="${index}" href="<c:url value='/home?pageMaxItem=${pageMaxItem}&currentPage=${index}'/>">${index}</a>
			</c:if>
			<c:if test="${index != MODEL.getCurrentPage()}">
				<a class="pagin-item" data-pageindex="${index}" href="<c:url value='/home?pageMaxItem=${pageMaxItem}&currentPage=${index}'/>">${index}</a>
			</c:if>					
			</c:forEach>
			<a class="control-item next-control"><i class="fas fa-caret-right"></i></a>
		</div>
		<br>
		</c:if>
	</main>
	<script type="text/javascript">
		let controlNo = 0;
		if (document.querySelector(".pagination") != null) {
			const pagination = document.querySelector(".pagination");
			const totalPage = pagination.dataset.totalpage;
			const maxPageItem = pagination.dataset.maxpageitem;
			const currentPage = pagination.dataset.currentpage;
			const paginationMaxPage = 3;
			const paginItems = pagination.querySelectorAll(".pagin-item");
			let minPage = (Math.ceil(currentPage/paginationMaxPage) - 1 + controlNo) * paginationMaxPage + 1;
			let maxPage = (Math.ceil(currentPage/paginationMaxPage) + controlNo)* paginationMaxPage;
			
			setPaginItem(currentPage, controlNo, paginationMaxPage, paginItems, minPage, maxPage);
			
			document.querySelector(".prev-control").addEventListener("click", () => {
				controlNo--;
				minPage = (Math.ceil(currentPage/paginationMaxPage) - 1 + controlNo) * paginationMaxPage + 1;
				maxPage = (Math.ceil(currentPage/paginationMaxPage) + controlNo)* paginationMaxPage;
				if (minPage < 0) {
					controlNo++;	
				} else {
					setPaginItem(currentPage, controlNo, paginationMaxPage, paginItems, minPage, maxPage);	
				}
				console.log(controlNo);
			});
			
			document.querySelector(".next-control").addEventListener("click", () => {
				controlNo++;
				minPage = (Math.ceil(currentPage/paginationMaxPage) - 1 + controlNo) * paginationMaxPage + 1;
				maxPage = (Math.ceil(currentPage/paginationMaxPage) + controlNo)* paginationMaxPage;
				if (maxPage > Math.ceil(totalPage/paginationMaxPage)*paginationMaxPage) {
					controlNo--;
				} else {
					setPaginItem(currentPage, controlNo, paginationMaxPage, paginItems, minPage, maxPage);	
				}
				console.log(controlNo);
			});	
		}
		
		function setPaginItem(currentPage, controlNo, paginationMaxPage, paginItems, minPage, maxPage) {
			paginItems.forEach(item => {
				if (item.dataset.pageindex < minPage || item.dataset.pageindex > maxPage) {
					item.classList.add("inactive");
				} else {
					item.classList.remove("inactive");
				}
			});
		}
	</script>
</body>

</html>