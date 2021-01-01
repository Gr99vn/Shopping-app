<c:set var="pageMaxItem" value ="12"/>
<header class="center">
	<nav>
		<div class="branch">
			<a class="logo" href="<c:url value = '/home?pageMaxItem=${pageMaxItem}&currentPage=1'/>">Fuzzy Shop</a>
		</div>
		<ul class="navigation">
			<c:if test="${not empty USERMODEL}">
			<li class="nav-item"><a class="nav-link" href=""><i
					class="fas fa-user"></i> ${USERMODEL.getName()}</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value = '/logout?action=logout'/>"><i
					class="fas fa-sign-out-alt"></i> Logout</a></li>
			</c:if>
		</ul>
	</nav>

</header>