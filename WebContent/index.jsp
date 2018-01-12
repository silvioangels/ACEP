<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<!-- Basic -->
<meta charset="UTF-8">

<title><fmt:message key="site.titulo" /></title>
<meta name="description" content="index">

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
					<h2><fmt:message key="passo.indice"/></h2>

					<div class="right-wrapper pull-right">
						<ol class="breadcrumbs">
							<li><a href="index.jsp"> <i class="fa fa-home"></i>
							</a></li>
							<li><span><fmt:message key="passo.pagina" /></span></li>
							<li><span><fmt:message key="passo.indice" /></span></li>
						</ol>

						<a class="sidebar-right-toggle" data-open="sidebar-right"><i class="fa fa-chevron-left"></i></a>
					</div>
				</header>
				
				<header class="panel-heading">
					<h3><img src="assets/images/acep-logo2.jpg" width="10%" height="2%" alt=""> <fmt:message key="indice.links.rapidos" /></h3>
				</header>

				<!-- start: page -->
				<div class="panel-body">
						<!-- SALES/VENDAS -->
						<div class="form-group" style="background-color: rgb(112, 200, 226) !important">	
							<div class="col-md-12 col-sm-12 col-xs-12">
								<a href="#"><h3><label><strong><fmt:message key="menu.vendas" /></strong></label></h3></a>
								<a href="${pageContext.request.contextPath}/controlevendas?opcao=vendas"> 
									<h5><i class="fa fa-shopping-cart" aria-hidden="true"></i> <span><fmt:message key="indice.vendas.gerar.ordem" /></span></h5>
								</a>
							</div>	
						</div>
						
						<!-- WAREHOUSE/ARMAZEM -->
						<div class="form-group">	
							<div class="col-md-4 col-sm-4 col-xs-4">
								<a href="#"><h3><label><strong><fmt:message key="menu.armazem" /></strong></label></h3></a>
								<a href="${pageContext.request.contextPath}/controlearmazem?opcao=armazem&idarmazem=1"> 
									<h5><span><fmt:message key="indice.armazem.materia.prima" /></span></h5>
								</a>
							</div>	
							<div class="col-md-4 col-sm-4 col-xs-4">
								<div class="col-md-8 col-sm-8 col-xs-8">
									<a href="${pageContext.request.contextPath}/controlearmazem?opcao=armazem&idarmazem=1"> 
										<h5><i class="fa fa-tasks" aria-hidden="true"></i> <span><fmt:message key="menu.armazem.a" /></span></h5>
									</a>
									<a href="${pageContext.request.contextPath}/controlearmazem?opcao=armazem&idarmazem=2"> 
										<h5><i class="fa fa-tasks" aria-hidden="true"></i> <span><fmt:message key="menu.armazem.b" /></span></h5>
									</a>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-4">
								<div class="col-md-8 col-sm-8 col-xs-8">
									<a href="${pageContext.request.contextPath}/pages-stock-movement.jsp"> 
										<h5><i class="fa fa-tasks" aria-hidden="true"></i> <span><fmt:message key="menu.ordem.distribuicao" /></span></h5>
									</a>
								</div>
							</div>	
						</div>
						
						<!-- STOCK/ESTOQUE -->
						<div class="form-group" style="background-color: rgb(112, 200, 226) !important">	
							<div class="col-md-12">
								<a href="#"><h3><label><strong><fmt:message key="menu.estoque" /></strong></label></h3></a>
								<a href="${pageContext.request.contextPath}/controleestoque?opcao=estoque&nrEstoque=1"> 
									<i class="fa fa-pie-chart" aria-hidden="true"></i> 
									<span><fmt:message key="indice.estoque.produtos" /></span>
								</a>
							</div>	
						</div>
						
						<!-- PRODUCTION/PRODUCAO -->
						<div class="form-group">	
							<div class="col-md-3">
								<a href="#"><h3><label><strong><fmt:message key="menu.producao" /></strong></label></h3></a>
								<a href="${pageContext.request.contextPath}/controleproducao?opcao=ordemservico"> 
									<h5><span><fmt:message key="indice.producao.corrente" /></span></h5>
								</a>
							</div>	
							<div class="col-md-3">
								<div class="col-md-12">
									<a href="${pageContext.request.contextPath}/controleproducao?opcao=ordemservico"> 
										<h5><i class="fa fa-gears" aria-hidden="true"></i> <span><fmt:message key="menu.producao.ordem" /></span></h5>
									</a>
									<a href="${pageContext.request.contextPath}/controleproducao?opcao=requisicao"> 
										<h5><i class="fa fa-gears" aria-hidden="true"></i> <span><fmt:message key="menu.producao.requisicao" /></span></h5>
									</a>
								</div>
							</div>
							<div class="col-md-3">
								<div class="col-md-12">
									<a href="${pageContext.request.contextPath}/pages-robots.jsp?robotimg=Robot1"> 
										<h5><i class="fa fa-gears" aria-hidden="true"></i> <span><fmt:message key="menu.producao.equipamentos.robo.um" /></span></h5>
									</a>
									<a href="${pageContext.request.contextPath}/pages-robots.jsp?robotimg=Robot2"> 
										<h5><i class="fa fa-gears" aria-hidden="true"></i> <span><fmt:message key="menu.producao.equipamentos.robo.dois" /></span></h5>
									</a>
									<a href="${pageContext.request.contextPath}/pages-sensors.jsp"> 
										<h5><i class="fa fa-gears" aria-hidden="true"></i> <span><fmt:message key="menu.producao.sensor.temp.umidade" /></span></h5>
									</a>
								</div>
							</div>
							<div class="col-md-3">
								<div class="col-md-12">
									<a href="${pageContext.request.contextPath}/controleproducao?opcao=equipamento"> 
										<h5><i class="fa fa-gears" aria-hidden="true"></i> <span><fmt:message key="menu.equipamento" /></span></h5>
									</a>
									<a href="${pageContext.request.contextPath}/controleproducao?opcao=afericao"> 
										<h5><i class="fa fa-gears" aria-hidden="true"></i> <span><fmt:message key="menu.inspecao.qualidade" /></span></h5>
									</a>
								</div>
							</div>
						</div>
						
						<!-- COURT/PATIO -->
						<div class="form-group" style="background-color: rgb(112, 200, 226) !important">	
							<div class="col-md-12">
								<a href="#"><h3><label><strong><fmt:message key="menu.patio" /></strong></label></h3></a>
								<a href="pages-court.jsp"> 
									<i class="fa fa-caret-square-o-up" aria-hidden="true"></i> 
									<span><fmt:message key="indice.patio.controle.espaco" /></span>
								</a>
							</div>	
						</div>
						
						<!-- SUPPLIES/FORNECEDOR -->
						<div class="form-group">	
							<div class="col-md-4 col-sm-4 col-xs-4">
								<a href="#"><h3><label><strong><fmt:message key="menu.fornecedores" /></strong></label></h3></a>
								<a href="pages-suppliers.jsp"> 
									<h5><span><fmt:message key="indice.fornecedor.b2b.area" /></span></h5>
								</a>
							</div>	
							<div class="col-md-2">
								<div class="col-md-12">
									<a href="pages-suppliers.jsp"> 
										<h5><i class="fa fa-users" aria-hidden="true"></i> <span><fmt:message key="menu.vendas.bic" /></span></h5>
									</a>
								</div>
							</div>
							<div class="col-md-2">
								<div class="col-md-12">
									<a href="pages-suppliers.jsp"> 
										<h5><i class="fa fa-users" aria-hidden="true"></i> <span><fmt:message key="menu.vendas.crow" /></span></h5>
									</a>
								</div>
							</div>
							<div class="col-md-3">
								<div class="col-md-12">
									<a href="pages-suppliers.jsp"> 
										<h5><i class="fa fa-users" aria-hidden="true"></i> <span><fmt:message key="menu.vendas.fabercastell" /></span></h5>
									</a>
								</div>
							</div>
						</div>
						
						<!-- REPORT/RELATORIO -->
						<div class="form-group" style="background-color: rgb(112, 200, 226) !important">	
							<div class="col-md-12">
								<a href="#"><h3><label><strong><fmt:message key="menu.report" /></strong></label></h3></a>
								<a href="pages-reports.jsp"> 
									<i class="fa fa-file-text" aria-hidden="true"></i> 
									<span><fmt:message key="indice.relatorio.gerencial" /></span>
								</a>
							</div>	
						</div>
						
						<!-- PREDICTIVE ANALISYS/ANALISE PREDITIVA -->
						<div class="form-group">	
							<div class="col-md-3">
								<a href="#"><h3><label><strong><fmt:message key="menu.analise.preditiva" /></strong></label></h3></a>
								<a href="${pageContext.request.contextPath}/controlepreditiva?opcao=abrir"> 
									<h5><span><fmt:message key="indice.analise.preditiva.mais.procurados" /></span></h5>
								</a>
							</div>	
							<div class="col-md-3">
								<div class="col-md-12">
									<a href="${pageContext.request.contextPath}/controlepreditiva?opcao=abrir"> 
										<h5><i class="fa fa-search" aria-hidden="true"></i> <span><fmt:message key="relatorios.consumo.materia.primas" /></span></h5>
									</a>
									<a href="${pageContext.request.contextPath}/controlepreditiva?opcao=abrir"> 
										<h5><i class="fa fa-search" aria-hidden="true"></i> <span><fmt:message key="relatorios.desperdicio.materia.prima" /></span></h5>
									</a>
								</div>
							</div>
							<div class="col-md-3">
								<div class="col-md-12">
									<a href="${pageContext.request.contextPath}/controlepreditiva?opcao=abrir"> 
										<h5><i class="fa fa-search" aria-hidden="true"></i> <span><fmt:message key="relatorios.dano.producao" /></span></h5>
									</a>
									<a href="${pageContext.request.contextPath}/controlepreditiva?opcao=abrir"> 
										<h5><i class="fa fa-search" aria-hidden="true"></i> <span><fmt:message key="relatorios.materia.prima.armazenamento" /></span></h5>
									</a>
								</div>
							</div>
							<div class="col-md-3">
								<div class="col-md-12">
									<a href="${pageContext.request.contextPath}/controlepreditiva?opcao=abrir"> 
										<h5><i class="fa fa-search" aria-hidden="true"></i> <span><fmt:message key="relatorios.producao.cumprida" /></span></h5>
									</a>
								</div>
							</div>
						</div>
						
						<!-- SMART CONTRACTS/CONSTRATOS INTELIGENTES -->
						<div class="form-group" style="background-color: rgb(112, 200, 226) !important">	
							<div class="col-md-12">
								<a href="#"><h3><label><strong><fmt:message key="menu.analise.contrato" /></strong></label></h3></a>
								<a href="#"> 
									<i class="fa fa-lock" aria-hidden="true"></i> 
									<span><fmt:message key="indice.contrato.inteligente.verificar" /></span>
								</a>
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