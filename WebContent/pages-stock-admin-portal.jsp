<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<!-- Basic -->
<meta charset="UTF-8">

<title><fmt:message key="site.titulo" /></title>
<meta name="description" content="Sales">


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
<link rel="stylesheet" href="assets/vendor/pnotify/pnotify.custom.css" />

<!-- Theme CSS -->
<link rel="stylesheet" href="assets/stylesheets/theme.css" />

<!-- Skin CSS -->
<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />

<!-- Theme Custom CSS -->
<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">

<!-- Head Libs -->
<script src="assets/vendor/modernizr/modernizr.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    
	    window.onresize = function(){
	        startDrawingChart();
	    };
	 
	    window.onload = function(){
	        startDrawingChart();
	    };
	    
	    startDrawingChart = function(){
	    	††††††††google.load("visualization", "1", {packages:["corechart"],callback: drawChart});
	    
	    	function drawChart() {
	    			
	    			var	nomeAzul	= "Blue";
	    			var	nomeMajenta	= "Majenta";
	    			var	nomeBranco	= "White";
	    			
	    			var qtdAzul 	= '<%= session.getAttribute("qtdAzul") %>';
	    			var qtdMajenta 	= '<%= session.getAttribute("qtdMajenta") %>';
	    			var qtdBranco 	= '<%= session.getAttribute("qtdBranco") %>';
	    			var lingua 		= '<%= session.getAttribute("lingua") %>';
	    			
	    			var numQtdAzul 		= Number(qtdAzul);
	    			var numQtdMajenta 	= Number(qtdMajenta);
	    			var numQtdBranco 	= Number(qtdBranco);
	    		
		    		if(lingua == "de"){
		    			nomeAzul	= "Blau";
		    			nomeMajenta	= "Majenta";
		    			nomeBranco	= "Weiﬂ";
					}else if(lingua == "pt"){
						nomeAzul	= "Azul";
		    			nomeMajenta	= "Majenta";
		    			nomeBranco	= "Branco";
					}
	    		
		      	  var data = google.visualization.arrayToDataTable([
		      	      ['QTD'     	, 'QTD' 			,{ role: 'style' }],
		      		  [nomeAzul   	,  numQtdAzul 		, 'color: #0000ff'],
		      	      [nomeMajenta	,  numQtdMajenta	, 'color: #ff00ff'],
		      	      [nomeBranco 	,  numQtdBranco		, 'color: #d3d3d3']
		      	   ]);
	
		      	   var options = {};
		          
		          var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
		          chart.draw(data, options);
			}
	    };
    
    </script>
    
    
</head>
<body>
	<section class="body">
		<%@ include file="imports-header.jsp"%>
		<div class="inner-wrapper">
			<%@ include file="imports-sidebar.jsp"%>
			<section role="main" class="content-body">
				<header class="page-header">
					<h2><fmt:message key="armazem.materia.prima.disponibilidade" /></h2>

					<div class="right-wrapper pull-right">
						<ol class="breadcrumbs">
							<li><a href="index.jsp"> <i class="fa fa-home"></i>
							</a></li>
							<li><span><fmt:message key="passo.pagina" /></span></li>
							<li><span><fmt:message key="menu.estoque.admin.port" /></span></li>
						</ol>

						<a class="sidebar-right-toggle" data-open="sidebar-right"><i
							class="fa fa-chevron-left"></i></a>
					</div>
				</header>

				<!-- start: page -->
				
				  

				<div class="col-md-8 col-md-offset-2">
				
					<section class="panel">
					
						<header class="panel-heading">
							
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
						
							<div class="panel-body">
									<div class="col-md-3">
										<a href="${pageContext.request.contextPath}/controleestoque?opcao=estoque&nrEstoque=1" class="btn btn-info"><fmt:message key="menu.estoque" /> 1</a>
									</div>
									<div class="col-md-3">
										<a href="${pageContext.request.contextPath}/controleestoque?opcao=estoque&nrEstoque=2" class="btn btn-info"><fmt:message key="menu.estoque" /> 2</a>
									</div>
									<div class="col-md-3">
										<a href="${pageContext.request.contextPath}/controleestoque?opcao=estoque&nrEstoque=3" class="btn btn-info"><fmt:message key="menu.estoque" /> 3</a>
									</div>
									<div class="col-md-3">
										<a href="${pageContext.request.contextPath}/controleestoque?opcao=admin" class="btn btn-info"><fmt:message key="menu.estoque.admin.port" /></a>
									</div>
							</div>
						</header>

						<header class="panel-heading">
							<h3><fmt:message key="menu.estoque.admin.port" /></h3>
						</header>
						<header class="panel-heading">
							<h3><fmt:message key="estoque.todos" /></h3>
						</header>
						
						<div id="panelbody" class="panel-body">
							
							<div id="chart_div"></div>
						
						</div>
						
						<header class="panel-heading">
							<h3><fmt:message key="menu.estoque.admin.port" /></h3>
						</header>
						
						<div class="panel-body">
							<form class="form-horizontal form-bordered" action="controleestoque?opcao=validar" method="post">
								<div class="form-group">
									<label class="col-md-3 control-label"><strong><fmt:message key="menu.estoque" /></strong></label>
									<div class="col-md-8">
										<label class="checkbox-inline"> 
											<input type="checkbox" id="inlineCheckbox1" name="inlineCheckbox1" value="1"><a href="#" class="btn btn-xs btn-primary"><i class="fa fa-file-text-o"> <fmt:message key="menu.estoque" /> 1</i></a>
										</label> 
										<label class="checkbox-inline"> 
											<input type="checkbox" id="inlineCheckbox2" name="inlineCheckbox2" value="2"><a href="#" class="btn btn-xs btn-primary"><i class="fa fa-file-text-o"> <fmt:message key="menu.estoque" /> 2</i></a>
										</label> 
										<label class="checkbox-inline"> 
											<input type="checkbox" id="inlineCheckbox3" name="inlineCheckbox3" value="3"><a href="#" class="btn btn-xs btn-primary"><i class="fa fa-file-text-o"> <fmt:message key="menu.estoque" /> 3</i></a>
										</label>
									</div>
								</div>
							
								<div class="form-group">
									<label class="col-md-3 control-label"><strong><fmt:message key="vendas.cor.produto" /></strong></label>
									<div class="col-md-6">
										<label class="checkbox-inline"> 
											<input type="checkbox" id="inlineCheckbox4" name="inlineCheckbox4" value="branco"><a href="#" class="btn btn-xs btn-primary"><i class="fa fa-file-text-o"> <fmt:message key="vendas.branco" /></i></a>
										</label> 
										<label class="checkbox-inline"> 
											<input type="checkbox" id="inlineCheckbox5" name="inlineCheckbox5" value="magenta"><a href="#" class="btn btn-xs btn-primary"><i class="fa fa-file-text-o"> <fmt:message key="vendas.magenta" /></i></a>
										</label> 
										<label class="checkbox-inline"> 
											<input type="checkbox" id="inlineCheckbox6" name="inlineCheckbox6" value="azul"><a href="#" class="btn btn-xs btn-primary"><i class="fa fa-file-text-o"> <fmt:message key="vendas.azul" /></i></a>
										</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label"><strong><fmt:message key="vendas.quantidade" /></strong></label>
									<div class="col-sm-6">
										<div class="m-md slider-primary" data-plugin-slider
											data-plugin-options='{ "value": 1, "range": "min", "max": 100 }'
											data-plugin-slider-output="#listenSlider">
											<input id="listenSlider" name="qtdEstoque" type="hidden" value="1" />
										</div>
										<p class="output">
											<fmt:message key="vendas.corrente" />
											<code><fmt:message key="vendas.quantidade" /></code>
											<fmt:message key="vendas.e" />: <b>1</b>
										</p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label"><strong><fmt:message key="estoque.data.vencimento" /></strong></label>
									<div class="col-md-6">
										<div class="input-group">
											<span class="input-group-addon"> <i class="fa fa-calendar"></i>
											</span> <input type="text" id="dataVencimento" name="dataVencimento" data-plugin-datepicker class="form-control">
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label"><strong><fmt:message key="estoque.data.recebimento" /></strong></label>
									<div class="col-md-6">
										<div class="input-group">
											<span class="input-group-addon"> <i class="fa fa-calendar"></i>
											</span> <input type="text" id="dataRecebimento" name="dataRecebimento" data-plugin-datepicker class="form-control">
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<div class="radio-custom radio-primary">
											<input type="radio" id="radioIncluir" name="radioAcao" value="I">
											<label for="radioExample2"><fmt:message key="estoque.incluir" /></label>
										</div>
										<div class="radio-custom radio-primary">
											<input type="radio" id="radioExcluir" name="radioAcao" value="E">
											<label for="radioExample3"><fmt:message key="estoque.excluir" /></label>
										</div>
									</div>
								</div>
								
								
								<div class="pull-right">
									<div class="col-md-4">
										<input type="submit" value="<fmt:message key="estoque.confirmar" />" class="btn btn-info">
									</div>
								</div>
								
							</form>
							
						</div>
						
					</section>
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

	<!-- Specific Page Vendor -->
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
	<script src="assets/vendor/pnotify/pnotify.custom.js"></script>

	<!-- Theme Base, Components and Settings -->
	<script src="assets/javascripts/theme.js"></script>

	<!-- Theme Custom -->
	<script src="assets/javascripts/theme.custom.js"></script>

	<!-- Theme Initialization Files -->
	<script src="assets/javascripts/theme.init.js"></script>

	<!-- Examples -->
	<script src="assets/javascripts/forms/examples.advanced.form.js"></script>
	<script src="assets/javascripts/ui-elements/examples.modals.js"></script>

</body>
</html>