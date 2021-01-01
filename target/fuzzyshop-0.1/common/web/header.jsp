<header class="center">
	<ul class="navigation">
		<li class="nav-item"><a class="nav-link" href="notification.jsp"><i
				class="fas fa-bell"></i> Notification</a></li>
		<li class="nav-item"><a class="nav-link" href="help.jsp"><i
				class="fas fa-question-circle"></i> Help</a></li>
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
				<a class="logo" href="<c:url value = '/home'/>"><i class="fas fa-wallet"></i>
					Shop</a>
			</div>
		</div>
		<table style="width: 100%;">
			<tr>
				<td style="width: 100%;"><input type="text"
					placeholder="Search here" name="wallet-search" style="width: 100%;">
				</td>
				<td>
					<button type="submit">
						<i class="fas fa-search"></i>
					</button>
				</td>
			</tr>
		</table>
		<div class="cart-btn">
			<a class="shopping-cart" href="<c:url value = '/cart'/>"><i
				class="fas fa-shopping-cart"></i></a>
		</div>
	</div>

</header>