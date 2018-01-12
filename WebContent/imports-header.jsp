<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${login != true}">
	<jsp:forward page=""></jsp:forward>
</c:if> 
	
<!-- start: header -->
<header class="header">
	
	<div class="logo-container">
		<a href="index.jsp" class="logo">
			<img src="assets/images/acep-logo.png" width="55" height="55" alt="ACEP" />
		</a>

		<div class="visible-xs toggle-sidebar-left"
			data-toggle-class="sidebar-left-opened" data-target="html"
			data-fire-event="sidebar-left-opened">

			<i class="fa fa-bars" aria-label="Toggle sidebar"></i>

		</div>
	</div>
	
	<!-- start: search & user box -->
	<div class="header-right">
	
		<a href="index.jsp" class="logo">
			<img src="assets/images/TSY_Logo.png" width="255" height="55" alt="ACEP" />
		</a>

		<form action="${pageContext.request.contextPath}/controlevendas?opcao=pesquisar" method="post" class="search nav-form">
			<div class="input-group input-search">
				<input type="text" class="form-control" name="inputSearch" id="inputSearch" placeholder=<fmt:message key="home.pesquisa" /> > 
				<span class="input-group-btn">
					<button class="btn btn-default" type="submit">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>

		<span class="separator"></span>

		<ul class="notifications">
			<li><a href="https://u.blip.ai/56da449a-4eaf-4c7d-9342-aeb37a0b409e"
				class=" notification-icon"><i class="fa fa-question-circle"></i></a>
		</ul>
		
		<span class="separator"></span>


		<ul class="notifications">
			<li><a href="controlecarrinho?opcao=detalhar" class=" notification-icon">
					<i class="fa fa-shopping-cart"></i><span class="badge">${carrinhoQtd}</span>
				</a>
		</ul>

		<span class="separator"></span>
		
		<ul class="notifications">
			<li><a href="#" class="dropdown-toggle notification-icon"
				data-toggle="dropdown"> <i class="fa fa-envelope"></i> 
				<span class="badge">4</span>
			</a>

				<div class="dropdown-menu notification-menu">
					<div class="notification-title">
						<span class="pull-right label label-default">230</span> <fmt:message key="home.mensagens" />
					</div>

					<div class="content">
						<ul>
							<li><a href="#" class="clearfix">
									<figure class="image">
										<img src="assets/images/!sample-user.jpg"
											alt="Joseph Doe Junior" class="img-circle" />
									</figure> <span class="title">Joseph Doe</span> <span class="message">Lorem ipsum dolor sit.</span>
							</a></li>
							<li><a href="#" class="clearfix">
									<figure class="image">
										<img src="assets/images/!sample-user.jpg" alt="Joseph Junior"
											class="img-circle" />
									</figure> <span class="title">Joseph Junior</span> <span
									class="message truncate">Truncated message. Lorem ipsum
										dolor sit amet, consectetur adipiscing elit. Donec sit amet
										lacinia orci. Proin vestibulum eget risus non luctus. Nunc
										cursus lacinia lacinia. Nulla molestie malesuada est ac
										tincidunt. Quisque eget convallis diam, nec venenatis risus.
										Vestibulum blandit faucibus est et malesuada. Sed interdum
										cursus dui nec venenatis. Pellentesque non nisi lobortis,
										rutrum eros ut, convallis nisi. Sed tellus turpis, dignissim
										sit amet tristique quis, pretium id est. Sed aliquam diam
										diam, sit amet faucibus tellus ultricies eu. Aliquam lacinia
										nibh a metus bibendum, eu commodo eros commodo. Sed commodo
										molestie elit, a molestie lacus porttitor id. Donec facilisis
										varius sapien, ac fringilla velit porttitor et. Nam tincidunt
										gravida dui, sed pharetra odio pharetra nec. Duis consectetur
										venenatis pharetra. Vestibulum egestas nisi quis elementum
										elementum.</span>
							</a></li>
							<li><a href="#" class="clearfix">
									<figure class="image">
										<img src="assets/images/!sample-user.jpg" alt="Joe Junior"
											class="img-circle" />
									</figure> <span class="title">Joe Junior</span> <span class="message">Lorem
										ipsum dolor sit.</span>
							</a></li>
							<li><a href="#" class="clearfix">
									<figure class="image">
										<img src="assets/images/!sample-user.jpg" alt="Joseph Junior"
											class="img-circle" />
									</figure> <span class="title">Joseph Junior</span> <span class="message">Lorem
										ipsum dolor sit amet, consectetur adipiscing elit. Donec sit
										amet lacinia orci. Proin vestibulum eget risus non luctus.
										Nunc cursus lacinia lacinia. Nulla molestie malesuada est ac
										tincidunt. Quisque eget convallis diam.</span>
							</a></li>
						</ul>

						<hr />

						<div class="text-right">
							<a href="#" class="view-more"><fmt:message key="home.visualizar" /></a>
						</div>
					</div>
				</div></li>
			<li><a href="#" class="dropdown-toggle notification-icon"
				data-toggle="dropdown"> <i class="fa fa-bell"></i> <span
					class="badge">3</span>
			</a>

				<div class="dropdown-menu notification-menu">
					<div class="notification-title">
						<span class="pull-right label label-default">3</span> <fmt:message key="home.alertas" />
					</div>

					<div class="content">
						<ul>
							<li><a href="#" class="clearfix">
									<div class="image">
										<i class="fa fa-thumbs-down bg-danger"></i>
									</div> <span class="title"><fmt:message key="home.servidor.msg" /></span> <span
									class="message"><fmt:message key="home.servidor.tempo" /></span>
							</a></li>
							<li><a href="#" class="clearfix">
									<div class="image">
										<i class="fa fa-lock bg-warning"></i>
									</div> <span class="title"><fmt:message key="home.usuario.status" /></span> <span class="message"><fmt:message key="home.usuario.tempo" /></span>
							</a></li>
							<li><a href="#" class="clearfix">
									<div class="image">
										<i class="fa fa-signal bg-success"></i>
									</div> <span class="title"><fmt:message key="home.conexao.status" /></span> <span
									class="message">10/10/2016</span>
							</a></li>
						</ul>

						<hr />

						<div class="text-right">
							<a href="#" class="view-more"><fmt:message key="home.visualizar"/></a>
						</div>
					</div>
				</div></li>
		</ul>

		<span class="separator"></span>

		<div id="userbox" class="userbox">
			<a href="#" data-toggle="dropdown">
				<figure class="profile-picture">
					<img src="assets/images/!logged-user.jpg" alt="${cliente.nome}"
						class="img-circle"
						data-lock-picture="assets/images/!logged-user.jpg" />
				</figure>
				<div class="profile-info" data-lock-name="${cliente.nome}" data-lock-email="${cliente.email}">
					<span class="name">${cliente.nome}</span> <span class="role">${cliente.perfil}</span>
				</div> <i class="fa custom-caret"></i>
			</a>

			<div class="dropdown-menu">
				<ul class="list-unstyled">
					<li class="divider"></li>
					<li><a role="menuitem" tabindex="-1"
						href="#"><i class="fa fa-user"></i><fmt:message key="home.perfil.pessoal" /></a></li>
					<li><a role="menuitem" tabindex="-1" href="#"
						data-lock-screen="true"><i class="fa fa-lock"></i><fmt:message key="home.protecao.tela" /></a></li>
					<li><a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}"><i
							class="fa fa-power-off"></i><fmt:message key="home.sair" /></a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- end: search & user box -->
</header>
<!-- end: header -->