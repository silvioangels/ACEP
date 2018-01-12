<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<!-- Basic -->
<meta charset="UTF-8">
<title><fmt:message key="site.titulo" /></title>
<meta name="description" content="Forgot Password">
<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<!-- Web Fonts  -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light"
	rel="stylesheet" type="text/css">

<!-- Vendor CSS -->
<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />

<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.css" />
<link rel="stylesheet"
	href="assets/vendor/magnific-popup/magnific-popup.css" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap-datepicker/css/bootstrap-datepicker3.css" />

<!-- Theme CSS -->
<link rel="stylesheet" href="assets/stylesheets/theme.css" />

<!-- Skin CSS -->
<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />

<!-- Theme Custom CSS -->
<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">

<!-- Head Libs -->
<script src="assets/vendor/modernizr/modernizr.js"></script>
</head>
<body>
	<!-- start: page -->
	<section class="body-sign">
		<div class="center-sign">
			<a href="/" class="logo pull-left"> <img
				src="assets/images/acep-logo.png" height="80" alt="ACEP" />
			</a>

			<div class="panel panel-sign">
				<div class="panel-title-sign mt-xl text-right">
					<h2 class="title text-uppercase text-weight-bold m-none">
						<i class="fa fa-user mr-xs"></i> <fmt:message key="recuperar.titulo" />
					</h2>
				</div>
				<div class="panel-body">
					<div class="alert alert-info">
						<p class="m-none text-weight-semibold h6"><fmt:message key="recuperar.saudacao" />
						</p>
					</div>

					<form action="${pageContext.request.contextPath}/controleLogin?opcao=recuperar" method="post">
						<div class="form-group mb-none">
							<div class="input-group">
								<input name="username" type="email" placeholder="E-mail"
									class="form-control input-lg" /> <span class="input-group-btn">
									<button class="btn btn-primary btn-lg" type="submit"><fmt:message key="recuperar.reset" /></button>
								</span>
							</div>
						</div>

						<p class="text-center mt-lg">
							<fmt:message key="recuperar.remember" /> <a href="${pageContext.request.contextPath}/controleLogin?opcao=limpar&page=signin"><fmt:message key="recuperar.logar" /></a>
						</p>
					</form>
					
						<c:if test="${showMsgAlertError == true}">
							<div class="alert alert-danger">
							  <strong>${msgAlert}</strong>
							</div>
						</c:if>
						
						<c:if test="${showMsgAlertSucess == true}">
							<div class="alert alert-success">
							  <strong>${msgAlert}</strong>
							</div>
						</c:if>
				</div>
			</div>

		</div>
	</section>
	<!-- end: page -->

	<!-- Vendor -->
	<script src="assets/vendor/jquery/jquery.js"></script>

	<script
		src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>

	<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>

	<script src="assets/vendor/nanoscroller/nanoscroller.js"></script>

	<script
		src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

	<script src="assets/vendor/magnific-popup/jquery.magnific-popup.js"></script>

	<script src="assets/vendor/jquery-placeholder/jquery-placeholder.js"></script>

	<!-- Theme Base, Components and Settings -->
	<script src="assets/javascripts/theme.js"></script>

	<!-- Theme Custom -->
	<script src="assets/javascripts/theme.custom.js"></script>

	<!-- Theme Initialization Files -->
	<script src="assets/javascripts/theme.init.js"></script>

</body>
</html>
