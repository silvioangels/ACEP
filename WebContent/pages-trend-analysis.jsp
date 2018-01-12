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
<meta name="description" content="reports">

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

<!-- Specific Page Vendor CSS -->

<link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
<link rel="stylesheet"
	href="assets/vendor/jquery-ui/jquery-ui.theme.css" />
<link rel="stylesheet" href="assets/vendor/select2/css/select2.css" />
<link rel="stylesheet"
	href="assets/vendor/select2-bootstrap-theme/select2-bootstrap.min.css" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap-multiselect/bootstrap-multiselect.css" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap-tagsinput/bootstrap-tagsinput.css" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap-colorpicker/css/bootstrap-colorpicker.css" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap-timepicker/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="assets/vendor/dropzone/basic.css" />
<link rel="stylesheet" href="assets/vendor/dropzone/dropzone.css" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap-markdown/css/bootstrap-markdown.min.css" />
<link rel="stylesheet" href="assets/vendor/summernote/summernote.css" />
<link rel="stylesheet"
	href="assets/vendor/codemirror/lib/codemirror.css" />
<link rel="stylesheet" href="assets/vendor/codemirror/theme/monokai.css" />

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
					<h2><fmt:message key="relatorios.cabecalho" /></h2>

					<div class="right-wrapper pull-right">
						<ol class="breadcrumbs">
							<li><a href="index.jsp"> <i class="fa fa-home"></i>
							</a></li>
							<li><span><fmt:message key="passo.pagina" /></span></li>
							<li><span><fmt:message key="passo.report" /></span></li>
						</ol>

						<a class="sidebar-right-toggle" data-open="sidebar-right"><i
							class="fa fa-chevron-left"></i></a>
					</div>
				</header>

				<!-- start: page -->
				<div class="panel-body col-md-8 col-md-offset-2">
					
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
					
					<form class="form-horizontal form-bordered" action="${pageContext.request.contextPath}/controlepreditiva?opcao=exportar" method="post">

						<div class="form-group">
							<div class="col-md-12">
								<div class="switch switch-sm switch-primary col-md-2">
									<input type="checkbox" name="switchConsumoMateriaPrima" data-plugin-ios-switch>
								</div>
								<div class="col-md-6">
									<h4><fmt:message key="relatorios.consumo.materia.primas" /></h4>
								</div>
							</div>

							<div class="col-md-12">
								<div class="switch switch-sm switch-primary col-md-2">
									<input type="checkbox" name="switchDesperdicioMateriaPrima" data-plugin-ios-switch>
								</div>
								<div class="col-md-6">
									<h4><fmt:message key="relatorios.desperdicio.materia.prima" /></h4>
								</div>
							</div>

							<div class="col-md-12">
								<div class="switch switch-sm switch-primary col-md-2">
									<input type="checkbox" name="switchDanoProducao" data-plugin-ios-switch>
								</div>
								<div class="col-md-6">
									<h4><fmt:message key="relatorios.dano.producao" /></h4>
								</div>
							</div>

							<div class="col-md-12">
								<div class="switch switch-sm switch-primary col-md-2">
									<input type="checkbox" name="switchMateriaPrimaArmazem" data-plugin-ios-switch>
								</div>
								<div class="col-md-6">
									<h4><fmt:message key="relatorios.materia.prima.armazenamento" /></h4>
								</div>
							</div>

							<div class="col-md-12">
								<div class="switch switch-sm switch-primary col-md-2">
									<input type="checkbox" name="switchProducaoCumprida" data-plugin-ios-switch>
								</div>
								<div class="col-md-6">
									<h4><fmt:message key="relatorios.producao.cumprida" /></h4>
								</div>
							</div>

						</div>
						
						<div class="form-group">
							<div class="col-md-6">
								<div class="col-md-12 form-group">
									<label for=""><h4><fmt:message key="relatorios.mes" /></h4></label> 
									<select id="selectMes" name="selectMes" class="form-control">
										<option value="01" ><fmt:message key="relatorios.janeiro" /></option>
										<option value="02"><fmt:message key="relatorios.fevereiro" /></option>
										<option value="03"><fmt:message key="relatorios.marco" /></option>
										<option value="04"><fmt:message key="relatorios.abril" /></option>
										<option value="05"><fmt:message key="relatorios.maio" /></option>
										<option value="06"><fmt:message key="relatorios.junho" /></option>
										<option value="07"><fmt:message key="relatorios.julho" /></option>
										<option value="08"><fmt:message key="relatorios.agosto" /></option>
										<option value="09"><fmt:message key="relatorios.setembro" /></option>
										<option value="10"><fmt:message key="relatorios.outubro" /></option>
										<option value="11"><fmt:message key="relatorios.novembro" /></option>
										<option value="12"><fmt:message key="relatorios.dezembro" /></option>
									</select>
								</div>
							</div>
	
							<div class="col-md-6">
								<div class="col-md-12 form-group">
									<label for=""><h4><fmt:message key="relatorios.exportacao" /></h4></label> 
									<select id="tipoExportacao" name="tipoExportacao" class="form-control">
										<option value="xls"><fmt:message key="relatorios.excel" /></option>
										<option disabled="disabled" value="cvs"><fmt:message key="relatorios.cvs" /></option>
										<option disabled="disabled" value="pdf"><fmt:message key="relatorios.pdf" /></option>
										<option disabled="disabled" value="txt"><fmt:message key="relatorios.txt" /></option>
									</select>
								</div>
							</div>
						</div>
						
						<div class="form-group">

							<div class="pull-right">
								<div class="col-md-12">
									<input type="submit" value="<fmt:message key="relatorios.gerar.relatorio" />" class="btn btn-info">
								</div>
							</div>
						
						</div>
						

					</form>

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
	<script src="assets/vendor/jquery-ui/jquery-ui.js"></script>
	<script
		src="assets/vendor/jqueryui-touch-punch/jqueryui-touch-punch.js"></script>
	<script src="assets/vendor/select2/js/select2.js"></script>
	<script
		src="assets/vendor/bootstrap-multiselect/bootstrap-multiselect.js"></script>
	<script src="assets/vendor/jquery-maskedinput/jquery.maskedinput.js"></script>
	<script src="assets/vendor/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>
	<script
		src="assets/vendor/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script
		src="assets/vendor/bootstrap-timepicker/bootstrap-timepicker.js"></script>
	<script src="assets/vendor/fuelux/js/spinner.js"></script>
	<script src="assets/vendor/dropzone/dropzone.js"></script>
	<script src="assets/vendor/bootstrap-markdown/js/markdown.js"></script>
	<script src="assets/vendor/bootstrap-markdown/js/to-markdown.js"></script>
	<script src="assets/vendor/bootstrap-markdown/js/bootstrap-markdown.js"></script>
	<script src="assets/vendor/codemirror/lib/codemirror.js"></script>
	<script src="assets/vendor/codemirror/addon/selection/active-line.js"></script>
	<script src="assets/vendor/codemirror/addon/edit/matchbrackets.js"></script>
	<script src="assets/vendor/codemirror/mode/javascript/javascript.js"></script>
	<script src="assets/vendor/codemirror/mode/xml/xml.js"></script>
	<script src="assets/vendor/codemirror/mode/htmlmixed/htmlmixed.js"></script>
	<script src="assets/vendor/codemirror/mode/css/css.js"></script>
	<script src="assets/vendor/summernote/summernote.js"></script>
	<script src="assets/vendor/bootstrap-maxlength/bootstrap-maxlength.js"></script>
	<script src="assets/vendor/ios7-switch/ios7-switch.js"></script>
	<script
		src="assets/vendor/bootstrap-confirmation/bootstrap-confirmation.js"></script>
	<!-- Theme Base, Components and Settings -->
	<script src="assets/javascripts/theme.js"></script>

	<!-- Theme Custom -->
	<script src="assets/javascripts/theme.custom.js"></script>

	<!-- Theme Initialization Files -->
	<script src="assets/javascripts/theme.init.js"></script>
	<script src="assets/javascripts/ui-elements/examples.modals.js"></script>
	<script src="assets/javascripts/forms/examples.advanced.form.js"></script>
	<script src="assets/javascripts/ui-elements/examples.modals.js"></script>


</body>
</html>
