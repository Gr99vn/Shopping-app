<div class="menubar main-fit">
	<h3>Management</h3>
	<div class="menu">
		<div class="menu-item" data-act="product-sub-menu">Product <i class="fas caret-icon fa-caret-right"></i></div>
		<ul class="sub-menu product-sub-menu inactive">
			<li class="sub-menu-item">
				<a href="<c:url value='/admin-product-add'/>">Add product</a>
			</li>
			<li class="sub-menu-item">
				<a href="<c:url value='/admin-product-search'/>">Edit or delete product</a>
			</li>
		</ul>
		<div class="menu-item" data-act="order-sub-menu">Order <i class="fas caret-icon fa-caret-right"></i></div>	
		<ul class="sub-menu order-sub-menu inactive">
			<li class="sub-menu-item order-item">
				<a href="<c:url value='/admin-orders?status=order'/>">Waiting</a>
			</li>
			<li class="sub-menu-item receive-item">
				<a href="<c:url value='/admin-orders?status=receive'/>">Received</a>
			</li>
			<li class="sub-menu-item deliver-item">
				<a href="<c:url value='/admin-orders?status=deliver'/>">Delivered</a>
			</li>
			<li class="sub-menu-item confirm-item">
				<a href="<c:url value='/admin-orders?status=confirm'/>">Confirmed</a>
			</li>
		</ul>	
	</div>
</div>