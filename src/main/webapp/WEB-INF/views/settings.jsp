<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="agent-page">
	<div class="container" style="min-height: 360px">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<div class="form-container">
				<form:form class="form-contact" method="post"
					action="/swforecast/settings" modelAttribute="settings">
					<div class="row">
						<h1 class="title">Email Notification</h1>
						<h4 class="sub-title">Select City to get weather forecast
						</h4>

						<div class="col-md-12 col-sm-12"
							style="padding-bottom: 10px; padding-top: 10px">
							<select class="form-control" name="cityname" required>
								<c:forEach items="${citylist}" var="city">
									<option value="${city}">${city}</option>
								</c:forEach>
							</select>
						</div>

						<div class="col-md-12 col-sm-12 text-center">
							<button type="submit" class="btn btn-default btn-lg">
								<span class=""></span> Send Weather Info
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</section>