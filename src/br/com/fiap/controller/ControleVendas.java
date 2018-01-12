package br.com.fiap.controller;

import java.io.IOException;
import java.sql.SQLRecoverableException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.beans.Produto;
import br.com.fiap.bo.ProdutoBO;
import br.com.fiap.excecao.RegraNegocioException;
import properties.PropertiesFactory;

/**
 * Servlet implementation class Controle
 */
@WebServlet(urlPatterns = "/controlevendas")

public class ControleVendas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControleVendas() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//limpando a sessao de msg
		limparAlertMensagem(request.getSession());
		
		String pagina = "pages-sales.jsp";
		String opcao = request.getParameter("opcao");
		
		try {
			
			if(opcao.equals("vendas")){
				pagina = "pages-sales.jsp";
				
				buscarPrecoProdutos(request.getSession());
				
			}else if(opcao.equals("pesquisar")){
				pagina = "pages-search-results.jsp";
				
				realizarPesquisaVendas(request);
				
			}
			
			response.sendRedirect(pagina);
			
		} catch (RegraNegocioException e) {
			
			mostrarAlertMensagem(request.getSession(), e.getMessage());
			response.sendRedirect(pagina);
			
		} catch (SQLRecoverableException e) {
			
			String lingua = (String)request.getSession().getAttribute("lingua");
			String msgPropUsuario =  "banco.fiap.indisponibilidade";
			
			try {
				
				mostrarAlertMensagem(request.getSession(), new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));
				
			} catch (Exception e1) {
				
				String msg = "Can´t connect to FIAP Data Base. Try Again Later";
				
				if(lingua.equalsIgnoreCase("pt")){
					msg = "Não foi possivel se conectar ao Banco de Dados da FIAP. Tente novamente mais tarde";
				}else if(lingua.equalsIgnoreCase("de")){
					msg = "Kann keine Verbindung zur FIAP-Datenbank herstellen. Versuchen Sie es später noch einmal";
				}
				
				mostrarAlertMensagem(request.getSession(), msg);
			}
			
			response.sendRedirect("pages-signin.jsp");
			
		}		
		catch (Exception e) {
			e.printStackTrace();
			
			String lingua = (String)request.getSession().getAttribute("lingua");
			String msgPropUsuario =  "acep.erro.critico";
			
			try {
				
				mostrarAlertMensagem(request.getSession(), new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));
				
			} catch (Exception e1) {
				
				String msg = "Can´t connect to FIAP Data Base. Try Again Later";
				
				if(lingua.equalsIgnoreCase("pt")){
					msg = "Não foi possivel se conectar ao Banco de Dados da FIAP. Tente novamente mais tarde";
				}else if(lingua.equalsIgnoreCase("de")){
					msg = "Kann keine Verbindung zur FIAP-Datenbank herstellen. Versuchen Sie es später noch einmal";
				}
				
				mostrarAlertMensagem(request.getSession(), msg);
			}
			
			response.sendRedirect("pages-signin.jsp");
		}
		
	}
	
	private void buscarPrecoProdutos(HttpSession sessao) throws RegraNegocioException, Exception{
		
		ProdutoBO bo = new ProdutoBO();
		List<Produto> listaProdutosMarca = bo.recuperarProdutos();
		
		for (Produto produto : listaProdutosMarca) {
			
			if(produto.getCor().getCodigo() == 1){
				
				sessao.setAttribute("valorunitariobranco", produto.getValorUnitario());
				
			}else if(produto.getCor().getCodigo() == 2){
				
				sessao.setAttribute("valorunitarioazul", produto.getValorUnitario());
				
			}else {
				
				sessao.setAttribute("valorunitariomagenta", produto.getValorUnitario());
				
			}	
			
		}
		
	}
	
	private void realizarPesquisaVendas(HttpServletRequest request) throws Exception{
		ProdutoBO bo = new ProdutoBO();
		
		String lingua = (String)request.getSession().getAttribute("lingua");
		String filtro = request.getParameter("inputSearch");
		
		filtro = validarFiltroLinguas(lingua, filtro);
		
		List<Produto> listaProdutos = bo.consultarPorDescricao(filtro);
		
		if(listaProdutos.size() == 0){
			listaProdutos = bo.recuperarProdutos();
			request.getSession().setAttribute("mostrarMsgNenhumProdutoEncontrado", true);
		}
		
		request.getSession().setAttribute("qtdResultadosPesquisa", listaProdutos.size());
		request.getSession().setAttribute("listaProdutos", listaProdutos);
	}
	
	private String validarFiltroLinguas(String lingua, String filtro) throws Exception {
		
		String filtroTraduzidoPortugues = "";
		
		if(lingua.equals("en") ||
				lingua.equals("de")){
			String [] linguaProp = {"en","de"};
			String [] msgProp = {"pesquisa.caneta", "vendas.branco","vendas.magenta","vendas.azul"};
			
			for (String ling : linguaProp) {
				for (String msg : msgProp) {
					
					String palavra = new PropertiesFactory().recuperarPropriedadeMensagens(ling, msg).trim();
					
					if(filtro.toUpperCase().contains(palavra.toUpperCase())){
						filtroTraduzidoPortugues = filtro.toUpperCase().replace(palavra.toUpperCase(), new PropertiesFactory().recuperarPropriedadeMensagens("pt", msg));
					}
				}
				
			}
			
			if(filtroTraduzidoPortugues.equals("")){
				filtroTraduzidoPortugues = filtro;
			}
			
		}else{
			filtroTraduzidoPortugues = filtro;
		}
		
		return filtroTraduzidoPortugues;
	}
	
	private void mostrarAlertMensagem(HttpSession sessao, String mensagem){
		
		sessao.setAttribute("showMsgAlertError", true);
		sessao.setAttribute("msgAlert", mensagem);
		
	}
	
	private void limparAlertMensagem(HttpSession sessao){
		
		sessao.setAttribute("mostrarMsgNenhumProdutoEncontrado", false);
		sessao.setAttribute("showMsgAlertError", false);
		sessao.setAttribute("showMsgAlertSucess", false);
		sessao.setAttribute("msgAlert", "");
		
	}

}
