<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section id="service" class="section-color">
	<div class="section-detail">
		<h1>Swedish Weather Forecast Service</h1>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<div class="content-box" style="min-height: 200px">
					<i class="icon-box fa fa-car"></i>
					<h3>
						<i class="icon-quote fa fa-quote-left"></i> Utilities <i
							class="icon-quote fa fa-quote-right"></i>
					</h3>
					<h4>User Registration and Authentication.</h4>
					<h4>Daily Weather Forecast.</h4>
					<h4>Mobile Responsive.</h4>
				</div>
			</div>
			<div class="col-md-4">
				<div class="content-box" style="min-height: 200px">
					<i class="icon-box fa fa-bank"></i>
					<h3>
						<i class="icon-quote fa fa-quote-left"></i> RESTful Service <i
							class="icon-quote fa fa-quote-right"></i>
					</h3>
					<h4>Swedish Meteorological and Hydrological Institute (SMHI) API</h4>
					<h4>Google Maps API</h4>
				</div>
			</div>
			<div class="col-md-4">
				<div class="content-box" style="min-height: 200px">
					<i class="icon-box fa fa-line-chart"></i>
					<h3>
						<i class="icon-quote fa fa-quote-left"></i> Reliable <i
							class="icon-quote fa fa-quote-right"></i>
					</h3>
					<h4>User Security</h4>
					<h4>Reliable Weather Forecast Data</h4>
				</div>
			</div>
		</div>
	</div>
</section>


<div class="modal fade login-modal" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">
			<i class="fa fa-close"></i>
		</button>
		<div class="login-button-container">
			<a href="#" data-section="login"><i class="fa fa-user"></i></a> <a
				href="#" data-section="sign-in"><i class="fa fa-pencil-square-o"></i></a>
		</div>
		<!-- ./login-button-container -->
		<div class="form-container">
			<form:form method="post" action="/swforecast/userauth"
				modelAttribute="user">
				<div id="login" class="box">
					<h2 class="title">Login in to your account</h2>
					<h3 class="sub-title">Already Have an Account? Login with your
						credentials.</h3>
					<div class="field">
						<input id="user-log" name="email" required="true"
							class="form-control" type="email" placeholder="Enter Email">
						<i class="fa fa-user user"></i>
					</div>
					<div class="field">
						<input id="password-log" name="password" required="true" min="6"
							class="form-control" type="password" placeholder="Password">
						<i class="fa fa-ellipsis-h"></i>
					</div>
					<div class="field footer-form text-right">
						<input type="submit" name="submit" value="Login"
							class="btn btn-default button-form" /> <input type="hidden"
							name="${_csrf.parameterName}" value="${_csrf.token}" />
					</div>
				</div>
			</form:form>
			<!-- ./login -->
			<form:form method="post" action="/swforecast/userauth"
				modelAttribute="user">
				<div id="sign-in" class="box">
					<h2 class="title">Sign In</h2>
					<h3 class="sub-title">Create a Free Account and Discover More</h3>
					<div class="field">
						<input id="user-sign" name="username" required="true"
							class="form-control margin-right" type="text"
							placeholder="Username"> <i class="fa fa-user user"></i>
					</div>


					<div class="field">
						<input id="email-sign" class="form-control " type="email"
							name="email" required="true" placeholder="Email"> <i
							class="fa fa-envelope-o"></i>
					</div>

					<div class="field">
						<input id="password-sign" class="form-control" type="password"
							name="password" required="true" min="6" placeholder="Password">
						<i class="fa fa-ellipsis-h"></i>
					</div>
					<div class="field">
						<input id="re-password-sign" class="form-control" type="password"
							name="repassword" required="true" min="6"
							placeholder="Repeat password"> <i
							class="fa fa-ellipsis-h"></i>
					</div>

					<div class="field footer-form text-right">
						<input type="submit" name="submit" value="Register"
							class="btn btn-default button-form"></input> <input type="hidden"
							name="${_csrf.parameterName}" value="${_csrf.token}" />
					</div>
				</div>
				<!-- ./sign-in -->
			</form:form>

			<!-- ./form-container -->
		</div>
		<!-- ./login-button-container -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


