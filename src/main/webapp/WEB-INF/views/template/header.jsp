<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="menu-base" id="header-container-box">
	<div class="info">
		<!-- info -->
		<div class="container">
			<div class="row">


				<c:choose>
					<c:when test="${username==null}">
						<div class="col-md-6"></div>
						<div id="login-pan" class="col-md-6">
							<a href="#" data-toggle="modal" data-target=".login-modal"
								data-section="sign-in"><i class="icon fa fa-pencil-square-o"></i>
								Sign up</a> <a href="#" data-toggle="modal"
								data-target=".login-modal" data-section="login"><i
								class="icon fa fa-user user"></i> Login</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="col-md-6">
							<a href="<c:url value="/dashboard"/>"><i
								class="icon fa fa-home"></i> Dashboard</a>
						</div>

						<div id="login-pan" class="col-md-6">
							<a href="<c:url value="/logout"/>"><i
								class="icon fa fa-power-off"></i> Logout</a> <a
								href="<c:url value="/settings"/>"><i class="icon fa fa-cog"></i>
								Settings</a>

						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>

	<!-- /.menu -->
	<a href="#" class="fixed-button top"><i class="fa fa-chevron-up"></i></a>
	<a href="#" class="hidden-xs fixed-button email" data-toggle="modal"
		data-target="#modal-contact" data-section="modal-contact"><i
		class="fa fa-envelope-o"></i></a>
</header>
