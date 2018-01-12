<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- start: sidebar -->
<aside id="sidebar-left" class="sidebar-left">

	<div class="sidebar-header">
		<div class="sidebar-title"><fmt:message key="menu.navegacao" /></div>
		<div class="sidebar-toggle hidden-xs"
			data-toggle-class="sidebar-left-collapsed" data-target="html"
			data-fire-event="sidebar-left-toggle">
			<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
		</div>
	</div>

	<div class="nano">
		<div class="nano-content">
			<nav id="menu" class="nav-main" role="navigation">

				<ul class="nav nav-main">
					
					<!-- MENU VENDAS -->
					<li>
						<a href="${pageContext.request.contextPath}/controlevendas?opcao=vendas"> 
							<i class="fa fa-shopping-cart" aria-hidden="true"></i> 
							<span><fmt:message key="menu.vendas" /></span>
						</a>
					</li>
					
					<!-- MENU ARMAZEM -->
					<li class="nav-parent">
						<a href="#"> 
							<i class="fa fa-tasks" aria-hidden="true"></i> 
							<span><fmt:message key="menu.armazem" /></span>
						</a>
						<ul class="nav nav-children">
							<li><a href="${pageContext.request.contextPath}/controlearmazem?opcao=armazem&idarmazem=1"> <fmt:message key="menu.armazem.a" /> </a></li>
							<li><a href="${pageContext.request.contextPath}/controlearmazem?opcao=armazem&idarmazem=2"> <fmt:message key="menu.armazem.b" /> </a></li>
							<li><a href="${pageContext.request.contextPath}/pages-stock-movement.jsp"> <fmt:message key="menu.ordem.distribuicao" /> </a></li>
						</ul>
					</li>
					
					<!-- MENU ESTOQUE -->
					<li>
						<a href="${pageContext.request.contextPath}/controleestoque?opcao=estoque&nrEstoque=1"> 
							<i class="fa fa-pie-chart" aria-hidden="true"></i> 
							<span><fmt:message key="menu.estoque" /></span>
						</a>
					</li>
					
					<!-- MENU PRODUCAO -->
					<li class="nav-parent">
						<a href="#"> 
							<i class="fa fa-gears" aria-hidden="true"></i> 
							<span><fmt:message key="menu.producao" /></span>
						</a>
						<ul class="nav nav-children">
							<li><a href="${pageContext.request.contextPath}/controleproducao?opcao=ordemservico"> <fmt:message key="menu.producao.ordem" /></a>
							<li><a href="${pageContext.request.contextPath}/controleproducao?opcao=requisicao"> <fmt:message key="menu.producao.requisicao" /> </a></li>
							<li class="nav-parent"><a href="#"> <span><fmt:message key="menu.producao.equipamentos.quebrados" /></span>
							</a>
								<ul class="nav nav-children">
									<li><a href="${pageContext.request.contextPath}/pages-robots.jsp?robotimg=Robot1"> <fmt:message key="menu.producao.equipamentos.robo.um" /> </a></li>
									<li><a href="${pageContext.request.contextPath}/pages-robots.jsp?robotimg=Robot2"> <fmt:message key="menu.producao.equipamentos.robo.dois" /> </a></li>
									<li><a href="${pageContext.request.contextPath}/pages-sensors.jsp"> <fmt:message key="menu.producao.sensor.temp.umidade" /> </a></li>
								</ul></li>
							<li><a href="${pageContext.request.contextPath}/controleproducao?opcao=equipamento"> <fmt:message key="menu.equipamento" /> </a></li>
							<li><a href="${pageContext.request.contextPath}/controleproducao?opcao=afericao"> <fmt:message key="menu.inspecao.qualidade" /> </a></li>
						</ul>
					</li>
					
					<!-- MENU PATIO -->
					<li class="nav-parent">
						<a href="#"> 
							<i class="fa fa-caret-square-o-up" aria-hidden="true"></i> 
							<span><fmt:message key="menu.patio" /></span>
						</a>
						<ul class="nav nav-children">
							<li><a href="${pageContext.request.contextPath}/pages-court.jsp"> <fmt:message key="menu.patio.mapa" /> </a></li>
						</ul>
					</li>
					
					<!-- MENU FORNECEDOR -->
					<li>
						<a href="${pageContext.request.contextPath}/pages-suppliers.jsp"> 
							<i class="fa fa-users" aria-hidden="true"></i> 
							<span><fmt:message key="menu.fornecedores" /></span>
						</a>
					</li>
					
					<!-- MENU REPORTS -->
					<li>
						<a href="${pageContext.request.contextPath}/pages-reports.jsp">
							<i class="fa fa-file-text"></i> 
							<fmt:message key="menu.report" /> 
						</a>
					</li>
					
					<!-- MENU ANALISE PREDITIVA -->
					<li class="nav-parent">
						<a href="#"> 
							<i class="fa fa-search" aria-hidden="true"> </i>
							<span><fmt:message key="menu.analise.preditiva" /></span>
						</a>
						<ul class="nav nav-children">
							<li><a href="${pageContext.request.contextPath}/controlepreditiva?opcao=abrir"> <fmt:message key="menu.analise.tendencia" /> </a></li>
							<li><a href="pages-controls.jsp"> <fmt:message key="menu.analise.controle" /> </a></li>
						</ul>
					</li>
					
					<!-- MENU ANALISE CONTRATO -->
					<li>
						<a href="#">
							<i class="fa fa-lock">
							</i> <fmt:message key="menu.analise.contrato" /> 
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</aside>
<!-- end: sidebar -->
