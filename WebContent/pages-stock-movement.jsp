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
<meta name="description" content="Movement">


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

<script type="text/javascript">
	function realizarCalculo(){
		
		//limpando todas as variaveis
		limparVariaveis();
		
		var tipoTrans	= "";
		var qtdAzul 	= document.getElementById("inputQtdAzul").value;
		var qtdMajenta 	= document.getElementById("inputQtdMajenta").value;
		var qtdBranco 	= document.getElementById("inputQtdBranco").value;
		var numQtdAzul 		= 0;
		var numQtdMajenta 	= 0;
		var numQtdBranco 	= 0;
				
		//validaçoes da tela
		var radios = document.getElementsByName('radioTransport');
		
		var indicadorSelecaoTrasnporte = 0;
		
		for (var i = 0, length = radios.length; i < length; i++)
		{
			
			 if (radios[i].checked)
			 {
				indicadorSelecaoTrasnporte++;
				tipoTrans	= radios[i].value;
				break;
			 }
			 
		}
		
		if(indicadorSelecaoTrasnporte == 0){
			document.getElementById("msgErrorDivLabel").innerHTML 	= recuperarMensagemLingua(1);
			document.getElementById("msgErrorDiv").style.display 	= "block";
			return;
		}
		
		if(qtdAzul == ""){
			document.getElementById("msgErrorDivLabel").innerHTML 	= recuperarMensagemLingua(2);
			document.getElementById("msgErrorDiv").style.display 	= "block";
			return;
		}else if(qtdMajenta == ""){
			document.getElementById("msgErrorDivLabel").innerHTML 	= recuperarMensagemLingua(3);
			document.getElementById("msgErrorDiv").style.display 	= "block";
			return;
		}else if(qtdBranco == ""){
			document.getElementById("msgErrorDivLabel").innerHTML 	= recuperarMensagemLingua(4);
			document.getElementById("msgErrorDiv").style.display 	= "block";
			return;
		}
		
		var numQtdAzul 		= Number(qtdAzul);
		var numQtdMajenta 	= Number(qtdMajenta);
		var numQtdBranco 	= Number(qtdBranco);
		
		if(numQtdAzul <= 0){
			document.getElementById("msgErrorDivLabel").innerHTML 	= recuperarMensagemLingua(5);
			document.getElementById("msgErrorDiv").style.display 	= "block";
			return;
		}else if(numQtdAzul >= 100){
			document.getElementById("msgErrorDivLabel").innerHTML 	= recuperarMensagemLingua(5);
			document.getElementById("msgErrorDiv").style.display 	= "block";
			return;
		}else if(numQtdMajenta <= 0){
			document.getElementById("msgErrorDivLabel").innerHTML 	= recuperarMensagemLingua(6);
			document.getElementById("msgErrorDiv").style.display 	= "block";
			return;
		}else if(numQtdMajenta >= 100){
			document.getElementById("msgErrorDivLabel").innerHTML 	= recuperarMensagemLingua(6);
			document.getElementById("msgErrorDiv").style.display 	= "block";
			return;
		}else if(numQtdBranco <= 0){
			document.getElementById("msgErrorDivLabel").innerHTML 	= recuperarMensagemLingua(7);
			document.getElementById("msgErrorDiv").style.display 	= "block";
			return;
		}else if(numQtdBranco >= 100){
			document.getElementById("msgErrorDivLabel").innerHTML 	= recuperarMensagemLingua(7);
			document.getElementById("msgErrorDiv").style.display 	= "block";
			return;
		}
		
		
		//Calculo Tempo Entrega
		var tempoEntrega = 3;
		
		if(tipoTrans == "L"){
			tempoEntrega = 10;
		}
		
		document.getElementById("tempoEntregaLabel").innerHTML = tempoEntrega;
		
		//Calculo Custo
		var custoMovimentacao = 0;
		custoMovimentacao = tempoEntrega*(numQtdAzul + numQtdMajenta + numQtdBranco);
		document.getElementById("custoLabel").innerHTML = custoMovimentacao;
		
		//Calculo Tempo Abastecimento
		var tempoAbastecimento = 0;
		tempoAbastecimento = custoMovimentacao / tempoEntrega;
		document.getElementById("fornecimentoLabel").innerHTML = tempoAbastecimento;
		
		document.getElementById("calculoArmazem").style.display = "block";
	}
	
	function limparVariaveis(){
		var msgErro = "";
		document.getElementById("msgErrorDivLabel").innerHTML 	= "";
		document.getElementById("msgErrorDiv").style.display 	= "none";
		document.getElementById("calculoArmazem").style.display = "none";
	}
	
	function recuperarMensagemLingua(codigo){
		
		var lingua = '<%= session.getAttribute("lingua") %>';
		
		if(codigo == 1){
			if(lingua == "de"){
				return "Keine Transportoption ausgewählt";
			}else if(lingua == "pt"){
				return "Não foi selecionado nenhuma opção de transporte";
			}else{
				return "No transport option selected";
			}			
		}else if(codigo == 2){
			if(lingua == "de"){
				return "Müssen Sie eine Menge der Farbe auswählen Blau";
			}else if(lingua == "pt"){
				return "Necessário selecionar uma quantidade da cor Azul";
			}else{
				return "Need to select an amount of the color Blue";
			}			
		}else if(codigo == 3){
			if(lingua == "de"){
				return "Sie müssen eine Farbmenge Magenta wählen";
			}else if(lingua == "pt"){
				return "Necessário selecionar uma quantidade da cor Majenta";
			}else{
				return "You must select a quantity of color Magenta";
			}			
		}else if(codigo == 4){
			if(lingua == "de"){
				return "Sie müssen eine Quantität der Farbe Weiß wählen";
			}else if(lingua == "pt"){
				return "Necessário selecionar uma quantidade da cor Branca";
			}else{
				return "You must select a quantity of color White";
			}
		}else if(codigo == 5){
			if(lingua == "de"){
				return "Blaue Farbe Menge sollte zwischen 1 und 99 sein";
			}else if(lingua == "pt"){
				return "Quantidade da cor Azul deve ser entre 1 e 99";
			}else{
				return "Blue color quantity should be between 1 and 99";
			}			
		}else if(codigo == 6){
			if(lingua == "de"){
				return "Farbmenge Majenta sollte zwischen 1 und 99 liegen";
			}else if(lingua == "pt"){
				return "Quantidade da cor Majenta deve ser entre 1 e 99";
			}else{
				return "Color quantity Majenta should be between 1 and 99";
			}			
		}else if(codigo == 7){
			if(lingua == "de"){
				return "Die Menge der Farbe Weiß sollte zwischen 1 und 99 liegen";
			}else if(lingua == "pt"){
				return "Quantidade da cor Branca deve ser entre 1 e 99";
			}else{
				return "Quantity of color White should be between 1 and 99";
			}			
		}
		
	}
	
	function confirmarMovimentacao(){
		//recuperando contexto
		var ctx 		= '<%= request.getContextPath() %>';
		
		//recuperando tipo transporte
		var tipoTrans	= "";
		
		var radios = document.getElementsByName('radioTransport');
		
		for (var i = 0, length = radios.length; i < length; i++)
		{
			
			 if (radios[i].checked)
			 {
				tipoTrans	= radios[i].value;
				break;
			 }
			 
		}
		
		//recuperando valor armazem selecionado
		var el = document.getElementById('selectArmazemA')
		var codigoArmazemSelecionado = el.options[el.selectedIndex].value
		
		var tempoEntrega 		= document.getElementById("tempoEntregaLabel").innerHTML;
		var custoLabel 			= document.getElementById("custoLabel").innerHTML;
		var fornecimentoLabel 	= document.getElementById("fornecimentoLabel").innerHTML;
		
		document.location.href  = ctx + "/controlearmazem?opcao=movimentacao&tempoEntrega="+tempoEntrega+"&custo="+custoLabel+"&fornecimento="+fornecimentoLabel+"&tipoTrans="+tipoTrans+"&codigoArmazemSelecionado="+codigoArmazemSelecionado;
	}

</script>

</head>
<body>
	<section class="body">
		<%@ include file="imports-header.jsp"%>
		<div class="inner-wrapper">
			<%@ include file="imports-sidebar.jsp"%>
			<section role="main" class="content-body">
				<header class="page-header">
					<h2><fmt:message key="menu.ordem.distribuicao" /></h2>

					<div class="right-wrapper pull-right">
						<ol class="breadcrumbs">
							<li><a href="index.jsp"> <i class="fa fa-home"></i>
							</a></li>
							<li><span><fmt:message key="passo.pagina" /></span></li>
							<li><span><fmt:message key="menu.ordem.distribuicao" /></span></li>
						</ol>

						<a class="sidebar-right-toggle" data-open="sidebar-right"><i
							class="fa fa-chevron-left"></i></a>
					</div>
				</header>

				<!-- start: page -->
				<div class="col-md-8 col-md-offset-2">
					<section class="panel">
						<header class="panel-heading">
						
							<c:if test="${showMsgAlertSucess == true}">
								<div class="alert alert-success">
								  <strong>${msgAlert}</strong>
								</div>
							</c:if>
							
							<c:if test="${showMsgAlertError == true}">
								<div class="alert alert-danger">
								  <strong>${msgAlert}</strong>
								</div>
							</c:if>
						
							<div class="panel-body">
								<div class="col-md-4">
									<a href="${pageContext.request.contextPath}/controlearmazem?opcao=armazem&idarmazem=1" class="btn btn-info"><fmt:message key="menu.armazem.a" /></a>
								</div>
								<div class="col-md-4">
									<a href="${pageContext.request.contextPath}/controlearmazem?opcao=armazem&idarmazem=2" class="btn btn-info"><fmt:message key="menu.armazem.b" /></a>
								</div>
								<div class="col-md-4">
									<a href="${pageContext.request.contextPath}/pages-stock-movement.jsp" class="btn btn-info"><fmt:message key="menu.ordem.distribuicao" /></a>
								</div>
							</div>
						</header>

						<header class="panel-heading">
							<h3><fmt:message key="armazem.materia.prima.disponibilidade" /></h3>
						</header>
						<div class="panel-body">
							<div class="form-group">
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="radio-custom radio-primary">
										<input type="radio" id="radioExample2" name="radioTransport" value="A">
										<label for="radioExample2"><fmt:message key="armazem.transporte.aereo" /></label>
									</div>
									<div class="radio-custom radio-primary">
										<input type="radio" id="radioExample3" name="radioTransport" value="L">
										<label for="radioExample3"><fmt:message key="armazem.transporte.terrestre" /></label>
									</div>
								</div>
							</div>
								<div class="form-group">
									<div class="col-md-5 col-sm-5 col-xs-5">
										<input type="text" disabled="true" value="<fmt:message key="armazem.cor.azul" />" size="10">
									</div>
									<div class="col-md-2 col-sm-2 col-xs-2">
										<fmt:message key="armazem.quantidade" />
									</div>
									<div class="col-md-5 col-sm-5 col-xs-5">
										<input id="inputQtdAzul" type="number" min="1" max="99" size="14">
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-5 col-sm-5 col-xs-5">
										<input type="text" disabled="true" value="<fmt:message key="armazem.cor.majenta" />" size="10">
									</div>
									<div class="col-md-2 col-sm-2 col-xs-2">
										<fmt:message key="armazem.quantidade" />
									</div>
									<div class="col-md-5 col-sm-5 col-xs-5">
										<input id="inputQtdMajenta" type="number" min="1" max="99" size="14">
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-5 col-sm-5 col-xs-5">
										<input type="text" disabled="true" value="<fmt:message key="armazem.cor.branco" />" size="10">
									</div>
									<div class="col-md-2 col-sm-2 col-xs-2">
										<fmt:message key="armazem.quantidade" />
									</div>
									<div class="col-md-5 col-sm-5 col-xs-5">
										<input id="inputQtdBranco" type="number" min="1" max="99" size="14">
									</div>
									
								</div>
								<div class="form-group">
									<div class="col-md-2 col-sm-2 col-xs-2">
										<fmt:message key="armazem.origem" />
									</div>
									<div class="col-md-4 col-sm-4 col-xs-4">
										
										<select id="selectArmazemA" class="form-control">
										    <option value="1"><fmt:message key="menu.armazem.a" /></option>
										    <option value="2"><fmt:message key="menu.armazem.b" /></option>
										</select>
										
									</div>
									
									<div class="col-md-2 col-sm-2 col-xs-2">
										<fmt:message key="armazem.destino" />
									</div>
									
									<div class="col-md-4 col-sm-4 col-xs-4">
										
										<select id="selectArmazemB" class="form-control">
										    <option value="1"><fmt:message key="menu.armazem.a" /></option>
										    <option value="2"><fmt:message key="menu.armazem.b" /></option>
										</select>
										
									</div>
								</div>
								<div class="form-group">
									<div align="center">
										<input value="<fmt:message key="armazem.calcular" />" class="btn btn-info" onclick="realizarCalculo()">
									</div>
								</div>
							</div>
							
							<div id="msgErrorDiv" class="alert alert-danger" style="display:none">
							  <strong><label id="msgErrorDivLabel"></label></strong>
							</div>
							
							<div id="calculoArmazem" style="display:none">
								<header class="panel-heading">
									<h3><fmt:message key="armazem.calculo.valor.transferencia" /></h3>
								</header>
								
								<div class="panel-body">
									
									<div class="form-group">
									
										<div class="col-md-12 col-sm-12 col-xs-12">
											<fmt:message key="armazem.tempo.entrega" />: 
											<strong><label id="tempoEntregaLabel">0</label> <fmt:message key="armazem.dia" /></strong>
										</div>
										
										<div class="col-md-12 col-sm-12 col-xs-12">
											<fmt:message key="armazem.custo" />:  
											<fmt:message key="armazem.custo.moeda" /> <strong><label id="custoLabel">0</label></strong>
										</div>
										
										<div class="col-md-12 col-sm-12 col-xs-12">
											<fmt:message key="armazem.tempo.fornecimento" />: 
											<strong><label id="fornecimentoLabel">0</label> <fmt:message key="armazem.dia" /></strong>
										</div>
									
									</div>
									
									<div class="form-group">
										<div align="center">
											<input value="<fmt:message key="armazem.confirmar" />" class="btn btn-info" onclick="confirmarMovimentacao()">
										</div>
									</div>
								
								</div>
								
							</div>
						</section>
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
