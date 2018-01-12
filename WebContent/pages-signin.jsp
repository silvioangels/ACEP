<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<!-- Basic -->
<meta charset="UTF-8">

<title><fmt:message key="site.titulo" /></title>
<meta name="description" content="Sign in">

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

   <script>

    if(navigator.userAgent.indexOf("Chrome") == -1 )
    {
    	//recuperando contexto
		var ctx = '<%= request.getContextPath() %>';
    	
    	document.location.href  = ctx + "/pages-browser-access.jsp";
    }
   
    </script>

</head>
<body>
	<!-- start: page -->
	<section class="body-sign">
		<div class="center-sign">
			<a href="/" class="logo pull-left"> <img
				src="assets/images/acep-logo.png" height="85" alt="ACEP" />
			</a>
			<a href="${pageContext.request.contextPath}/ServI18N?lingua=pt" class="logo pull-left"> <img
				src="assets/images/Flag_Brazil.png" height="45" alt="ACEP" />
			</a>
			<a href="${pageContext.request.contextPath}/ServI18N?lingua=en" class="logo pull-left"> <img
				src="assets/images/Flag_United_States.svg" height="45" alt="ACEP" />
			</a>
			<a href="${pageContext.request.contextPath}/ServI18N?lingua=de" class="logo pull-left"> <img
				src="assets/images/Flag_Germany.png" height="45" alt="ACEP" />
			</a>			
			<div class="panel panel-sign">
				<div class="panel-title-sign mt-xl text-right">
					<h2 class="title text-uppercase text-weight-bold m-none">
						<i class="fa fa-user mr-xs"></i><fmt:message key="login.sigin" />
					</h2>
				</div>
				<div class="panel-body">
					<form action="${pageContext.request.contextPath}/controleLogin?opcao=logar" method="post">
						<div class="form-group mb-lg">
							<label><fmt:message key="login.usuario" /></label>
							<div class="input-group input-group-icon">
								<input name="username" type="text" class="form-control input-lg" />
								<span class="input-group-addon"> <span
									class="icon icon-lg"> <i class="fa fa-user"></i>
								</span>
								</span>
							</div>
						</div>

						<div class="form-group mb-lg">
							<div class="clearfix">
								<label class="pull-left"><fmt:message key="login.senha" /></label> <a
									href="${pageContext.request.contextPath}/controleLogin?opcao=limpar&page=recover-password" class="pull-right"><fmt:message key="login.esqueceu" /></a>
							</div>
							<div class="input-group input-group-icon">
								<input name="pwd" type="password" class="form-control input-lg" />
								<span class="input-group-addon"> <span
									class="icon icon-lg"> <i class="fa fa-lock"></i>
								</span>
								</span>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-8">
								<div class="checkbox-custom checkbox-default">
									<input id="RememberMe" name="rememberme" type="checkbox" /> <label
										for="RememberMe"><fmt:message key="login.lembrar" /></label>
								</div>
							</div>
							<div class="col-sm-4 text-right">
								<button type="submit" class="btn btn-primary hidden-xs"><fmt:message key="login.sigin" /></button>
								<button type="submit"
									class="btn btn-primary btn-block btn-lg visible-xs mt-lg"><fmt:message key="login.sigin" /></button>
							</div>
						</div>

						<span class="mt-lg mb-lg line-thru text-center text-uppercase">
							<span><fmt:message key="login.ou" /></span>
						</span>

						<p class="text-center">
							<fmt:message key="login.conta" /> <a href="${pageContext.request.contextPath}/controleLogin?opcao=limpar&page=signup"><fmt:message key="login.criar" /></a>
						</p>
						
						<c:if test="${showMsgAlertError == true}">
							<div class="alert alert-danger">
							  <strong>${msgAlert}</strong>
							</div>
						</c:if>

					</form>
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
