<c:set var="pageMaxItem" value ="12"/>
<header class="center">
	<ul class="navigation">
		<li class="nav-item"><a class="nav-link" href=""><i
				class="fas fa-bell"></i> Notification</a></li>
		<c:if test="${USERMODEL.getRole().getId() != 1}">
			<li class="nav-item"><a class="nav-link" href="https://www.google.com/"><i
				class="fas fa-question-circle"></i> Help</a></li>
		</c:if>
		<c:if test="${USERMODEL.getRole().getId() == 1}">
			<ul class="management ml-10">
				<li class="nav-item"><a class="nav-link" href="<c:url value='/admin-home'/>"><i class="fas fa-tasks"></i> Management</a></li>
			</ul>
		</c:if>
		<li class="nav-item"><a class="nav-link" href="<c:url value = '/order'/>"><i class="fas fa-sort"></i> Orders</a></li>
		<c:if test="${empty USERMODEL}">
		<li class="nav-item"><a class="nav-link" href="<c:url value = '/register?action=register'/>"><i
				class="fas fa-user-plus"></i> Register</a></li>
		<li class="nav-item"><a class="nav-link" href="<c:url value = '/login?action=login'/>"><i
				class="fas fa-sign-in-alt"></i> Login</a></li>
		</c:if>
		<c:if test="${not empty USERMODEL}">
		<li class="nav-item"><a class="nav-link" href=""><i
				class="fas fa-user"></i> ${USERMODEL.getName()}</a></li>
		<li class="nav-item"><a class="nav-link" href="<c:url value = '/logout?action=logout'/>"><i
				class="fas fa-sign-out-alt"></i> Logout</a></li>
		</c:if>
	</ul>
	<div class="extend">
		<div class="branch">
			<div class="circle">
				<a class="logo" href="<c:url value = '/home?pageMaxItem=${pageMaxItem}&currentPage=1'/>">Fuzzy Shop</a>
			</div>
		</div>
		<form action="<c:url value = '/search'/>">
<!-- 		<input type="hidden" name="currentPage" value="1"> -->
<!-- 		<input type="hidden" name="pageMaxItem" value="12"> -->
		
		<table style="width: 100%;">
			<tr>
				<td style="width: 100%;"><input type="search"
					placeholder="Search here" name="name" style="width: 100%;"  pattern="[A-Za-z0-9]+" 
					title="Text and number only." value="${searchData}">
				</td>
				<td>
					<button type="submit">
						<i class="fas fa-search"></i>
					</button>
				</td>
			</tr>
		</table>
		</form>
		<div class="cart-btn">
			<a class="shopping-cart" href="<c:url value = '/cart'/>"><i
				class="fas fa-shopping-cart"></i></a>
		</div>
	</div>

</header>