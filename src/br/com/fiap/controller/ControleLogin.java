package br.com.fiap.controller;

import java.io.IOException;
import java.sql.SQLRecoverableException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.beans.Cliente;
import br.com.fiap.beans.ItemProdutoVenda;
import br.com.fiap.bo.ClienteBO;
import br.com.fiap.excecao.RegraNegocioException;
import properties.PropertiesFactory;

@WebServlet(urlPatterns = "/controleLogin")

public class ControleLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControleLogin() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//limpando a sessao de msg
		limparAlertMensagem(request.getSession());
		
		String paginaRedirecionamento = "pages-signin.jsp";
		
		try {
			
			String opcao = request.getParameter("opcao");
			
			if(opcao.equalsIgnoreCase("limpar")){
				
				limparAlertMensagem(request.getSession());
				
				String pagina = request.getParameter("page");
				
				paginaRedirecionamento = "pages-"+pagina+".jsp";
				 
			}else if(opcao.equalsIgnoreCase("logar")){
				
				paginaRedirecionamento = "pages-signin.jsp";
				
				realizarLogin(request);
				 
				paginaRedirecionamento = "index.jsp";
				
			}else if(opcao.equalsIgnoreCase("registrar")){
				
				paginaRedirecionamento = "pages-signup.jsp";
				
				realizarRegistro(request);
				
			} else if(opcao.equalsIgnoreCase("recuperar")){
				paginaRedirecionamento = "pages-recover-password.jsp";
				
				recuperarSenha(request);
				
			}
			
			response.sendRedirect(paginaRedirecionamento);
			
		} catch (RegraNegocioException e) {
			
			mostrarAlertMensagemErro(request.getSession(), e.getMessage());
			response.sendRedirect(paginaRedirecionamento);
			
		} catch (SQLRecoverableException e) {
			
			String lingua = (String)request.getSession().getAttribute("lingua");
			String msgPropUsuario =  "banco.fiap.indisponibilidade";
			
			try {
				
				mostrarAlertMensagemErro(request.getSession(), new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));
				
			} catch (Exception e1) {
				
				String msg = "Can´t connect to FIAP Data Base. Try Again Later";
				
				if(lingua.equalsIgnoreCase("pt")){
					msg = "Não foi possivel se conectar ao Banco de Dados da FIAP. Tente novamente mais tarde";
				}else if(lingua.equalsIgnoreCase("de")){
					msg = "Kann keine Verbindung zur FIAP-Datenbank herstellen. Versuchen Sie es später noch einmal";
				}
				
				mostrarAlertMensagemErro(request.getSession(), msg);
			}
			
			response.sendRedirect("pages-signin.jsp");
			
		}		
		catch (Exception e) {
			e.printStackTrace();
			
			String lingua = (String)request.getSession().getAttribute("lingua");
			String msgPropUsuario =  "acep.erro.critico";
			
			try {
				
				mostrarAlertMensagemErro(request.getSession(), new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));
				
			} catch (Exception e1) {
				
				String msg = "Can´t connect to FIAP Data Base. Try Again Later";
				
				if(lingua.equalsIgnoreCase("pt")){
					msg = "Não foi possivel se conectar ao Banco de Dados da FIAP. Tente novamente mais tarde";
				}else if(lingua.equalsIgnoreCase("de")){
					msg = "Kann keine Verbindung zur FIAP-Datenbank herstellen. Versuchen Sie es später noch einmal";
				}
				
				mostrarAlertMensagemErro(request.getSession(), msg);
			}
			
			response.sendRedirect("pages-signin.jsp");
		}		
		
	}
	
	public void realizarLogin(HttpServletRequest request) throws RegraNegocioException, Exception{
		
		String login = request.getParameter("username");
		String senha = request.getParameter("pwd");
		
		ClienteBO bo = new ClienteBO();
		
		if(bo.validarLoginSenha(login, senha)){
			
			Cliente cliente = new ClienteBO().consultarPorLoginSenha(login, senha);
			
			//Criando a sessão do Usuário
			HttpSession sessao = request.getSession();
			
			//Criando atributos na sessão do usuário
			sessao.setAttribute("login", true);
			sessao.setAttribute("cliente",cliente);				
			
			//Limpando todas as variaveis da sessão
			sessao.setAttribute("carrinhoQtd", 0);
			sessao.setAttribute("listaItensCarrinho", new ArrayList<ItemProdutoVenda>());
			sessao.setAttribute("valorTotalCarrinho", 0);
			request.getSession().setAttribute("showMsgAlertError", false);
			request.getSession().setAttribute("msgAlert", "");
			
		}else{
			String lingua = (String)request.getSession().getAttribute("lingua");
			String msgPropUsuario =  "login.mensagem.usuario.senha.invalida";
			
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));
		}
		
	}
	
	private void realizarRegistro(HttpServletRequest request) throws RegraNegocioException, Exception{
		
		String login 		= request.getParameter("name");
		String email 		= request.getParameter("email");
		String senha 		= request.getParameter("pwd");
		String senhaConf 	= request.getParameter("pwd_confirm");
		String termoUso  	= request.getParameter("agreeterms");
		
		if(login.equals("") || email.equals("") || senha.equals("") || senhaConf.equals("")){
			
			String lingua = (String)request.getSession().getAttribute("lingua");
			String msgPropUsuario =  "registro.validar.campos";
			
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));
		}
		
		if(!senha.equals(senhaConf)){
			String lingua = (String)request.getSession().getAttribute("lingua");
			String msgPropUsuario =  "registro.senhas.diferentes";
			
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));
		}
		
		if(termoUso == null){
			
			String lingua = (String)request.getSession().getAttribute("lingua");
			String msgPropUsuario =  "registro.termo.uso";
			
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));
			
		}
		
		ClienteBO bo = new ClienteBO();
		
		Cliente cli = new Cliente();
		cli.setCodigo(bo.recuperarProximoRegistro());
		cli.setNome(login);
		cli.setEmail(email);
		cli.setNumeroTelefone(0L);
		cli.setPerfil("USUARIO");
		cli.setLogin(login);
		cli.setSenha(senha);
		
		bo.inserir(cli);
		
		String lingua = (String)request.getSession().getAttribute("lingua");
		String msgPropUsuario =  "registro.finalizada.sucesso";
		
		mostrarAlertMensagemSucesso(request.getSession(), new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));
		
	}
	
	private void recuperarSenha(HttpServletRequest request) throws RegraNegocioException, Exception{
		
		//String email = request.getParameter("email");
		
		//TODO Enviar email
		
	}
	
	private void mostrarAlertMensagemErro(HttpSession sessao, String mensagem){
		
		sessao.setAttribute("showMsgAlertError", true);
		sessao.setAttribute("msgAlert", mensagem);
		
	}
	
	private void mostrarAlertMensagemSucesso(HttpSession sessao, String mensagem){
		
		sessao.setAttribute("showMsgAlertSucess", true);
		sessao.setAttribute("msgAlert", mensagem);
		
	}
	
	private void limparAlertMensagem(HttpSession sessao){
		sessao.setAttribute("showMsgAlertSucess", false);
		sessao.setAttribute("showMsgAlertError", false);
		sessao.setAttribute("msgAlert", "");
	}

}
