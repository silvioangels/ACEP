package br.com.fiap.controller;

import java.io.IOException;
import java.sql.SQLRecoverableException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.beans.Estoque;
import br.com.fiap.beans.Produto;
import br.com.fiap.beans.ProdutoEstoque;
import br.com.fiap.bo.EstoqueBO;
import br.com.fiap.bo.ProdutoEstoqueBO;
import br.com.fiap.excecao.RegraNegocioException;
import properties.PropertiesFactory;

/**
 * Servlet implementation class Controle
 */
@WebServlet(urlPatterns = "/controleestoque")

public class ControleEstoque extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControleEstoque() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//limpando a sessao de msg
		limparAlertMensagem(request.getSession());
			
		String pagina = "pages-stock.jsp";
		String opcao = request.getParameter("opcao");
		
		try {
			
			if(opcao.equals("estoque")){
				
				consultarQuantidadeProdutosPorEstoque(request);
				
			}else if(opcao.equals("admin")){
				pagina = "pages-stock-admin-portal.jsp";
				
				consultarQuantidadeProdutosTodosEstoques(request);
				
			}else if(opcao.equals("validar")){
				pagina = "pages-stock-admin-portal.jsp";
				
				validarInserirProdutoEstoque(request);
				consultarQuantidadeProdutosTodosEstoques(request);
				
			}
			
			response.sendRedirect(pagina);

		} catch (RegraNegocioException e) {

			mostrarAlertMensagem(request.getSession(), e.getMessage());
			response.sendRedirect(pagina);

		} catch (SQLRecoverableException e) {

			String lingua = (String) request.getSession().getAttribute("lingua");
			String msgPropUsuario = "banco.fiap.indisponibilidade";

			try {

				mostrarAlertMensagem(request.getSession(),
						new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));

			} catch (Exception e1) {

				String msg = "Can´t connect to FIAP Data Base. Try Again Later";

				if (lingua.equalsIgnoreCase("pt")) {
					msg = "Não foi possivel se conectar ao Banco de Dados da FIAP. Tente novamente mais tarde";
				} else if (lingua.equalsIgnoreCase("de")) {
					msg = "Kann keine Verbindung zur FIAP-Datenbank herstellen. Versuchen Sie es später noch einmal";
				}

				mostrarAlertMensagem(request.getSession(), msg);
			}

			response.sendRedirect("pages-signin.jsp");

		} catch (Exception e) {
			e.printStackTrace();

			String lingua = (String) request.getSession().getAttribute("lingua");
			String msgPropUsuario = "acep.erro.critico";

			try {

				mostrarAlertMensagem(request.getSession(),
						new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));

			} catch (Exception e1) {

				String msg = "Can´t connect to FIAP Data Base. Try Again Later";

				if (lingua.equalsIgnoreCase("pt")) {
					msg = "Não foi possivel se conectar ao Banco de Dados da FIAP. Tente novamente mais tarde";
				} else if (lingua.equalsIgnoreCase("de")) {
					msg = "Kann keine Verbindung zur FIAP-Datenbank herstellen. Versuchen Sie es später noch einmal";
				}

				mostrarAlertMensagem(request.getSession(), msg);
			}

			response.sendRedirect("pages-signin.jsp");
		}

	}
	
	private void consultarQuantidadeProdutosPorEstoque(HttpServletRequest request) throws Exception{
		
		String nrEstoque = request.getParameter("nrEstoque");
		
		EstoqueBO bo = new EstoqueBO(); 
		List<Integer> listaQuantidadeProdutos = bo.consultarQuantidadeProdutosEstoque(Integer.parseInt(nrEstoque));
		
		int qtdAzul 	= listaQuantidadeProdutos.get(0);
		int qtdMajenta 	= listaQuantidadeProdutos.get(1);
		int qtdBranco 	= listaQuantidadeProdutos.get(2);
		
		request.getSession().setAttribute("qtdAzul"		, qtdAzul);
		request.getSession().setAttribute("qtdMajenta"	, qtdMajenta);
		request.getSession().setAttribute("qtdBranco"	, qtdBranco);
		request.getSession().setAttribute("nrEstoque"	, nrEstoque);
		
	}
	
	private void consultarQuantidadeProdutosTodosEstoques(HttpServletRequest request) throws Exception{
		
		EstoqueBO bo = new EstoqueBO(); 
		
		List<Integer> listaQuantidadeProdutosEstoque1 = bo.consultarQuantidadeProdutosEstoque(1);
		List<Integer> listaQuantidadeProdutosEstoque2 = bo.consultarQuantidadeProdutosEstoque(2);
		List<Integer> listaQuantidadeProdutosEstoque3 = bo.consultarQuantidadeProdutosEstoque(3);
		
		int qtdAzul 	= listaQuantidadeProdutosEstoque1.get(0)+listaQuantidadeProdutosEstoque2.get(0)+listaQuantidadeProdutosEstoque3.get(0);
		int qtdMajenta 	= listaQuantidadeProdutosEstoque1.get(1)+listaQuantidadeProdutosEstoque2.get(1)+listaQuantidadeProdutosEstoque3.get(1);
		int qtdBranco 	= listaQuantidadeProdutosEstoque1.get(2)+listaQuantidadeProdutosEstoque2.get(2)+listaQuantidadeProdutosEstoque3.get(2);
		
		request.getSession().setAttribute("qtdAzul"		, qtdAzul);
		request.getSession().setAttribute("qtdMajenta"	, qtdMajenta);
		request.getSession().setAttribute("qtdBranco"	, qtdBranco);
		
	}
	
	@SuppressWarnings("deprecation")
	private void validarInserirProdutoEstoque(HttpServletRequest request) throws Exception{
		
		String selecionadoEstoqueUm 				= request.getParameter("inlineCheckbox1");
		String selecionadoEstoqueDois 				= request.getParameter("inlineCheckbox2");
		String selecionadoEstoqueTres 				= request.getParameter("inlineCheckbox3");
		String selecionadoCorBranca 				= request.getParameter("inlineCheckbox4");
		String selecionadoCorMagenta 				= request.getParameter("inlineCheckbox5");
		String selecionadoCorAzul 					= request.getParameter("inlineCheckbox6");
		String qtdEstoque 							= request.getParameter("qtdEstoque");
		String dataVencimento 						= request.getParameter("dataVencimento");
		String dataRecebimento 						= request.getParameter("dataRecebimento");
		String radioAcao 							= request.getParameter("radioAcao");
		String lingua 								= (String)request.getSession().getAttribute("lingua");
		String msgProp;
		
		//REALIZANDO VALIDACOES
		if((selecionadoEstoqueUm == null && selecionadoEstoqueDois == null && selecionadoEstoqueTres == null )){
			msgProp =  "estoque.validacao.estoque";
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));
		}
		
		if((selecionadoCorBranca == null && selecionadoCorMagenta == null && selecionadoCorAzul == null )){
			msgProp =  "vendas.validacao.cor";
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));
		}
		
		if(qtdEstoque.equalsIgnoreCase("0")){
			msgProp =  "vendas.validacao.quantidade";
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));
		}
		
		try{
			new Date(dataVencimento);
		}catch (Exception e) {
			msgProp =  "vendas.validacao.data";
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));
		} 
		
		try{
			new Date(dataRecebimento);
		}catch (Exception e) {
			msgProp =  "vendas.validacao.data";
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));
		} 
		
		if((radioAcao == null)){
			msgProp =  "estoque.validacao.acao";
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));
		}
		
		int numQtdEstoque = Integer.parseInt(qtdEstoque);
		String msgAlertSucesso = ""; 
		
		if(radioAcao.equals("I")){
			
			if(selecionadoEstoqueUm != null){
				
				if(selecionadoCorBranca != null){
					
					realizarInsercao(numQtdEstoque, 1, 1, dataVencimento, dataRecebimento);
					
				}
				if(selecionadoCorAzul != null){
					
					realizarInsercao(numQtdEstoque, 1, 2, dataVencimento, dataRecebimento);
					
				}
				if(selecionadoCorMagenta != null){
					
					realizarInsercao(numQtdEstoque, 1, 3, dataVencimento, dataRecebimento);
					
				}
				
			}
			
			if(selecionadoEstoqueDois != null){
				
				if(selecionadoCorBranca != null){
					
					realizarInsercao(numQtdEstoque, 1, 1, dataVencimento, dataRecebimento);
					
				}
				if(selecionadoCorAzul != null){
					
					realizarInsercao(numQtdEstoque, 1, 2, dataVencimento, dataRecebimento);
					
				}
				if(selecionadoCorMagenta != null){
					
					realizarInsercao(numQtdEstoque, 1, 3, dataVencimento, dataRecebimento);
					
				}
				
			}
				
			if(selecionadoEstoqueTres != null){
				
				if(selecionadoCorBranca != null){
					
					realizarInsercao(numQtdEstoque, 1, 1, dataVencimento, dataRecebimento);
					
				}
				if(selecionadoCorAzul != null){
					
					realizarInsercao(numQtdEstoque, 1, 2, dataVencimento, dataRecebimento);
					
				}
				if(selecionadoCorMagenta != null){
					
					realizarInsercao(numQtdEstoque, 1, 3, dataVencimento, dataRecebimento);
					
				}
				
			}
			
			msgProp =  "estoque.admin.inserir.sucesso";
			msgAlertSucesso = new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp);
			
		}else{
			
			ProdutoEstoqueBO bo = new ProdutoEstoqueBO();
			
			if(selecionadoEstoqueUm != null){
				
				if(selecionadoCorBranca != null){
					
					bo.excluir(1, 1, numQtdEstoque);
					
				}
				if(selecionadoCorAzul != null){
					
					bo.excluir(1, 2, numQtdEstoque);
					
				}
				if(selecionadoCorMagenta != null){
					
					bo.excluir(1, 3, numQtdEstoque);
					
				}
				
			}
			
			if(selecionadoEstoqueDois != null){
				
				if(selecionadoCorBranca != null){
					
					bo.excluir(2, 1, numQtdEstoque);
					
				}
				if(selecionadoCorAzul != null){
					
					bo.excluir(2, 2, numQtdEstoque);
					
				}
				if(selecionadoCorMagenta != null){
					
					bo.excluir(2, 3, numQtdEstoque);
					
				}
				
			}
				
			if(selecionadoEstoqueTres != null){
				
				if(selecionadoCorBranca != null){
					
					bo.excluir(3, 1, numQtdEstoque);
					
				}
				if(selecionadoCorAzul != null){
					
					bo.excluir(3, 2, numQtdEstoque);
					
				}
				if(selecionadoCorMagenta != null){
					
					bo.excluir(3, 3, numQtdEstoque);
					
				}
				
			}
			
			msgProp =  "estoque.admin.excluir.sucesso";
			msgAlertSucesso = new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp);
		}
		
		request.getSession().setAttribute("msgAlert", msgAlertSucesso);
		request.getSession().setAttribute("showMsgAlertSucess", true);
		
	}
	
	@SuppressWarnings("deprecation")
	private void realizarInsercao(int numQtdEstoque, int codEstoque, int codProduto,String dataVencimento,String dataRecebimento) throws Exception{
		ProdutoEstoqueBO bo = new ProdutoEstoqueBO();
		
		int proxRegistro = bo.recuperarProximoRegistro();
		
		for (int i = 0; i < numQtdEstoque; i++, proxRegistro++) {
			ProdutoEstoque prodEstoq = new ProdutoEstoque();
			prodEstoq.setCodigo(proxRegistro);
			Estoque estoque = new Estoque();
			estoque.setCodigo(codEstoque);
			prodEstoq.setEstoque(estoque);
			Produto prod = new Produto();
			prod.setCodigo(codProduto);			
			prodEstoq.setProduto(prod);
			prodEstoq.setDataVencimento(new Date(dataVencimento));
			prodEstoq.setDataRecebimento(new Date(dataRecebimento));
			bo.inserir(prodEstoq);
		}
		
	}
	
	private void mostrarAlertMensagem(HttpSession sessao, String mensagem) {

		sessao.setAttribute("showMsgAlertError", true);
		sessao.setAttribute("msgAlert", mensagem);

	}
	
	private void limparAlertMensagem(HttpSession sessao){
		
		sessao.setAttribute("showMsgAlertError"	, false);
		sessao.setAttribute("showMsgAlertSucess", false);
		sessao.setAttribute("msgAlert"			, "");
		sessao.setAttribute("nrEstoque"			, 0);
		
	}

}
