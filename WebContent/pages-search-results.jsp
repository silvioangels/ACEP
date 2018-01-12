<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<!-- Basic -->
<meta charset="UTF-8">

<title><fmt:message key="site.titulo" /></title>
<meta name="description" content="results">

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
	<section class="body">
		<%@ include file="imports-header.jsp"%>
		<div class="inner-wrapper">
			<%@ include file="imports-sidebar.jsp"%>

			<section role="main" class="content-body">
				<header class="page-header">
					<h2><fmt:message key="pesquisa.cabecalho" /></h2>

					<div class="right-wrapper pull-right">
						<ol class="breadcrumbs">
							<li><a href="index.jsp"> <i class="fa fa-home"></i>
							</a></li>
							<li><span><fmt:message key="passo.pagina" /></span></li>
							<li><span><fmt:message key="passo.resultado" /></span></li>
						</ol>

						<a class="sidebar-right-toggle" data-open="sidebar-right"><i
							class="fa fa-chevron-left"></i></a>
					</div>
				</header>

				<!-- start: page -->
				<div class="panel-body col-md-8 col-md-offset-2">
					<div class="tab-content">
						<div id="everything" class="tab-pane active">
							
							<p class="total-results text-muted"><fmt:message key="pesquisa.mostrar" /> ${qtdResultadosPesquisa} <fmt:message key="pesquisa.mostrar.de" /> ${qtdResultadosPesquisa} <fmt:message key="pesquisa.mostrar.resultados" /></p>
							
							<c:if test="${mostrarMsgNenhumProdutoEncontrado == true}">
							
								<p class="total-results text-muted"><fmt:message key="pesquisa.nenhum.resultado.um" /></p>
								<p class="total-results text-muted"><fmt:message key="pesquisa.nenhum.resultado.dois" /></p>
							
							</c:if>
							

							<ul class="list-unstyled search-results-list">
							
								<c:forEach var="produto" items="${listaProdutos}" varStatus="id">
									<li>
										<a href="${pageContext.request.contextPath}/controlevendas?opcao=vendas" class="has-thumb">
											<div>
												<c:if test="${produto.codigo == 1}">
													<img src="assets/images/penwhite.jpg" width="100%" height="100%" alt="">
												</c:if>
												<c:if test="${produto.codigo == 2}">
													<img src="assets/images/penblue.jpg" width="100%" height="100%" alt="">
												</c:if>
												<c:if test="${produto.codigo == 3}">
													<img src="assets/images/penmagenta.jpg" width="100%" height="100%" alt="">
												</c:if>
												
											</div>
											<div class="result-data">
												<c:if test="${produto.codigo == 1}">
													<p class="h3 title text-primary"><fmt:message key="pesquisa.caneta" /> <fmt:message key="vendas.branco" /></p>
												</c:if>
												<c:if test="${produto.codigo == 2}">
													<p class="h3 title text-primary"><fmt:message key="pesquisa.caneta" /> <fmt:message key="vendas.azul" /></p>
												</c:if>
												<c:if test="${produto.codigo == 3}">
													<p class="h3 title text-primary"><fmt:message key="pesquisa.caneta" /> <fmt:message key="vendas.magenta" /></p>
												</c:if>
												<p class="description">
													
													<div class="col-md-12">
														<h3><fmt:message key="vendas.descricao.ambiente" />:</h3>
														<p>
															<strong><i class="fa fa-arrow-right"></i> <fmt:message key="vendas.descricao.descricao.um" /></strong>
														</p>
														<p>
															<strong><i class="fa fa-arrow-right"></i> <fmt:message key="vendas.descricao.descricao.dois" /></strong>
														</p>
							
														<h3><fmt:message key="vendas.descricao.especificacoes" />:</h3>
														<p><fmt:message key="vendas.descricao.especificacoes.um" /></p>
														<p><fmt:message key="vendas.descricao.especificacoes.dois" /></p>
														<p><fmt:message key="vendas.descricao.especificacoes.tres" /></p>
														<c:if test="${produto.codigo == 1}">
															<p><fmt:message key="vendas.descricao.especificacoes.quatro" />: <fmt:message key="vendas.branco" /></p>
														</c:if>
														<c:if test="${produto.codigo == 2}">
															<p><fmt:message key="vendas.descricao.especificacoes.quatro" />: <fmt:message key="vendas.azul" /></p>
														</c:if>
														<c:if test="${produto.codigo == 3}">
															<p><fmt:message key="vendas.descricao.especificacoes.quatro" />: <fmt:message key="vendas.magenta" /></p>
														</c:if>
														
													</div>
													
												</p>
											</div>
										</a>
									</li>
								</c:forEach>
								
							</ul>

							<hr class="solid mb-none" />

						</div>
						<div id="medias" class="tab-pane">
							<div class="row">
								<div class="col-sm-6 col-md-4 col-lg-3">
									<div class="thumbnail">
										<div class="thumb-preview">
											<a class="thumb-image" href="#"> <img
												src="assets/images/projects/project-2.jpg"
												class="img-responsive" alt="Project">
											</a>
										</div>
										<h5 class="mg-title text-weight-semibold">
											Blog<small>.png</small>
										</h5>
									</div>
								</div>
								<div class="col-sm-6 col-md-4 col-lg-3">
									<div class="thumbnail">
										<div class="thumb-preview">
											<a class="thumb-image" href="#"> <img
												src="assets/images/projects/project-5.jpg"
												class="img-responsive" alt="Project">
											</a>
										</div>
										<h5 class="mg-title text-weight-semibold">
											Friends<small>.png</small>
										</h5>
									</div>
								</div>
								<div class="col-sm-6 col-md-4 col-lg-3">
									<div class="thumbnail">
										<div class="thumb-preview">
											<a class="thumb-image" href="#"> <img
												src="assets/images/projects/project-4.jpg"
												class="img-responsive" alt="Project">
											</a>
										</div>
										<h5 class="mg-title text-weight-semibold">
											Life<small>.png</small>
										</h5>
									</div>
								</div>
								<div class="col-sm-6 col-md-4 col-lg-3">
									<div class="thumbnail">
										<div class="thumb-preview">
											<a class="thumb-image" href="#"> <img
												src="assets/images/projects/project-5.jpg"
												class="img-responsive" alt="Project">
											</a>
										</div>
										<h5 class="mg-title text-weight-semibold">
											Poetry<small>.png</small>
										</h5>
									</div>
								</div>
							</div>
						</div>
						<div id="emails" class="tab-pane">
							<p>Recent</p>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna
								aliqua. Ut enim ad minim veniam, quis nostrud exercitat.</p>
						</div>
					</div>
				</div>
				<!-- end: page -->
			</section>
		</div>

		<aside id="sidebar-right" class="sidebar-right">
			<div class="nano">
				<div class="nano-content">
					<a href="#" class="mobile-close visible-xs"> Collapse <i
						class="fa fa-chevron-right"></i>
					</a>

					<div class="sidebar-right-wrapper">

						<div class="sidebar-widget widget-calendar">
							<h6>Upcoming Tasks</h6>
							<div data-plugin-datepicker data-plugin-skin="dark"></div>

							<ul>
								<li><time datetime="2016-04-19T00:00+00:00">04/19/2016</time>
									<span>Company Meeting</span></li>
							</ul>
						</div>

						<div class="sidebar-widget widget-friends">
							<h6>Friends</h6>
							<ul>
								<li class="status-online">
									<figure class="profile-picture">
										<img src="assets/images/!sample-user.jpg" alt="Joseph Doe"
											class="img-circle">
									</figure>
									<div class="profile-info">
										<span class="name">Joseph Doe Junior</span> <span
											class="title">Hey, how are you?</span>
									</div>
								</li>
								<li class="status-online">
									<figure class="profile-picture">
										<img src="assets/images/!sample-user.jpg" alt="Joseph Doe"
											class="img-circle">
									</figure>
									<div class="profile-info">
										<span class="name">Joseph Doe Junior</span> <span
											class="title">Hey, how are you?</span>
									</div>
								</li>
								<li class="status-offline">
									<figure class="profile-picture">
										<img src="assets/images/!sample-user.jpg" alt="Joseph Doe"
											class="img-circle">
									</figure>
									<div class="profile-info">
										<span class="name">Joseph Doe Junior</span> <span
											class="title">Hey, how are you?</span>
									</div>
								</li>
								<li class="status-offline">
									<figure class="profile-picture">
										<img src="assets/images/!sample-user.jpg" alt="Joseph Doe"
											class="img-circle">
									</figure>
									<div class="profile-info">
										<span class="name">Joseph Doe Junior</span> <span
											class="title">Hey, how are you?</span>
									</div>
								</li>
							</ul>
						</div>

					</div>
				</div>
			</div>
		</aside>
	</section>

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
	<script src="assets/vendor/select2/js/select2.js"></script>
	<script src="assets/vendor/pnotify/pnotify.custom.js"></script>

	<!-- Theme Base, Components and Settings -->
	<script src="assets/javascripts/theme.js"></script>

	<!-- Theme Custom -->
	<script src="assets/javascripts/theme.custom.js"></script>

	<!-- Theme Initialization Files -->
	<script src="assets/javascripts/theme.init.js"></script>
	<script src="assets/javascripts/ui-elements/examples.modals.js"></script>


</body>
</html>