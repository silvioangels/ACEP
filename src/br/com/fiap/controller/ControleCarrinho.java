package br.com.fiap.controller;

import java.io.IOException;
import java.sql.SQLRecoverableException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.beans.ItemProdutoVenda;
import br.com.fiap.beans.Produto;
import br.com.fiap.bo.ProdutoBO;
import br.com.fiap.excecao.RegraNegocioException;
import properties.PropertiesFactory;

@WebServlet(urlPatterns = "/controlecarrinho")

public class ControleCarrinho extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControleCarrinho() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//limpando a sessao de msg
		limparAlertMensagem(request.getSession());
		
		String pagina = "pages-cart.jsp";
				
		try {
			
			String opcao = request.getParameter("opcao");
			
			if(opcao.equalsIgnoreCase("adicionar")){//Adicionar da Tela de Vendas
				pagina = "pages-sales.jsp";
				
				realizarInclusaoCarrinho(request);
				
				pagina = "pages-cart.jsp";
				
			}else if(opcao.equalsIgnoreCase("excluir")){//Excluir da Tela de Carrinho
				pagina = "pages-cart.jsp";
				
				realizarExclusaoCarrinho(request);
				
			}else if(opcao.equalsIgnoreCase("finalizar")){//Finalizar da Tela de Carrinho
				pagina = "pages-cart.jsp";
				
				realizarFinalizacaoCarrinho(request);
				
			}else if(opcao.equalsIgnoreCase("detalhar")){//Ver o Carrinho da Tela de Indice
				pagina = "pages-cart.jsp";
				
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
	
	@SuppressWarnings("unchecked")
	private void realizarExclusaoCarrinho(HttpServletRequest request) throws RegraNegocioException, Exception{
		
		List<ItemProdutoVenda> listaItensCarrinho 	= (List<ItemProdutoVenda>) request.getSession().getAttribute("listaItensCarrinho");
		int item 									= Integer.parseInt(request.getParameter("item"));
		int produto 								= Integer.parseInt(request.getParameter("produto"));
		
		for (int i = 0; i < listaItensCarrinho.size(); i++) {
			ItemProdutoVenda itemProdutoVenda = listaItensCarrinho.get(i);
			
			if(itemProdutoVenda.getCodigo() == item){
				
				if(itemProdutoVenda.getListaProduto().size() == 1){
					listaItensCarrinho.remove(i);
					break;
				}else{
					
					for (int j = 0; j < itemProdutoVenda.getListaProduto().size(); j++) {
						Produto produtoItem = itemProdutoVenda.getListaProduto().get(j);
						
						if(produtoItem.getCodigo() == produto){
							itemProdutoVenda.getListaProduto().remove(j);
							break;							
						}
						
					}
					
				}
				
			}
			
		}
		
		//Criando a sessão do Usuário
		HttpSession sessao = request.getSession();
		int carrinhoQtd = 0;
		
		//Criando atributos na sessão do usuário
		Double valorTotalCarrinho = 0.0;
		for (ItemProdutoVenda itemProdutoVenda : listaItensCarrinho) {
			for (Produto prod : itemProdutoVenda.getListaProduto()) {
				carrinhoQtd++;
				valorTotalCarrinho = valorTotalCarrinho + (prod.getValorUnitario()*prod.getQuantidade());
			}
		}
		
		sessao.setAttribute("carrinhoQtd", carrinhoQtd);
		sessao.setAttribute("listaItensCarrinho", listaItensCarrinho);
		sessao.setAttribute("valorTotalCarrinho", valorTotalCarrinho);
		
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	private void realizarInclusaoCarrinho(HttpServletRequest request) throws RegraNegocioException, Exception{
		
		String selecionadoCorAzul 					= request.getParameter("inlineCheckbox1");
		String selecionadoCorMagenta 				= request.getParameter("inlineCheckbox2");
		String selecionadoCorBranca 				= request.getParameter("inlineCheckbox3");
		String dataEntrega 							= request.getParameter("dataEntrega");
		String qtdAzul 								= request.getParameter("qtdAzul");
		String qtdMajenta 							= request.getParameter("qtdMajenta");
		String qtdBranco 							= request.getParameter("qtdBranco");
		List<ItemProdutoVenda> listaItensCarrinho 	= (List<ItemProdutoVenda>) request.getSession().getAttribute("listaItensCarrinho");  
		String lingua 								= (String)request.getSession().getAttribute("lingua");
		String msgPropUsuario						= "";
		int quantidadeTotal 						= 0;
		int carrinhoQtd 							= 0;
		Double valorTotalCarrinho 					= 0.0;
		
		//REALIZANDO VALIDACOES
		if((selecionadoCorBranca == null && selecionadoCorMagenta == null && selecionadoCorAzul == null )){
			msgPropUsuario =  "vendas.validacao.cor";
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));
		}
		
		try{
			new Date(dataEntrega);
		}catch (Exception e) {
			msgPropUsuario =  "vendas.validacao.data";
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));
		}
		
		//Recuperando Todos os Produtos		
		ProdutoBO bo = new ProdutoBO();
		List<Produto> listaProdutosMarca = bo.recuperarProdutos();
		
		if(listaItensCarrinho == null){
			listaItensCarrinho = new ArrayList<ItemProdutoVenda>(); 
		}
		
		List<Produto> listaProdutosCarrinho = new ArrayList<Produto>(); 
		
		for (Produto produto : listaProdutosMarca) {
			
			if(selecionadoCorBranca != null && produto.getCor().getCodigo() == 1){
				produto.setQuantidade(Integer.parseInt(qtdBranco));
				quantidadeTotal = quantidadeTotal + Integer.parseInt(qtdBranco);
			}else if(selecionadoCorAzul != null && produto.getCor().getCodigo() == 2){
				produto.setQuantidade(Integer.parseInt(qtdAzul));
				quantidadeTotal = quantidadeTotal + Integer.parseInt(qtdAzul);
			}else if(selecionadoCorMagenta != null && produto.getCor().getCodigo() == 3){
				produto.setQuantidade(Integer.parseInt(qtdMajenta));
				quantidadeTotal = quantidadeTotal + Integer.parseInt(qtdMajenta);
			}
			
			if(selecionadoCorBranca != null && produto.getCor().getCodigo() == 1){
				listaProdutosCarrinho.add(produto);
			}else if(selecionadoCorAzul != null && produto.getCor().getCodigo() == 2){
				listaProdutosCarrinho.add(produto);
			}else if(selecionadoCorMagenta != null && produto.getCor().getCodigo() == 3){
				listaProdutosCarrinho.add(produto);
			}
		}
		
		ItemProdutoVenda itemCarrinho = new ItemProdutoVenda();
		itemCarrinho.setCodigo(listaItensCarrinho.size()+1);
		itemCarrinho.setListaProduto(listaProdutosCarrinho);				
		itemCarrinho.setDataEntrega(new Date(dataEntrega));
		itemCarrinho.setQuantidade(quantidadeTotal);
		listaItensCarrinho.add(itemCarrinho);

		listaProdutosCarrinho = new ArrayList<Produto>();				
		for (ItemProdutoVenda itemProdutoVenda : listaItensCarrinho) {
			for (Produto produto : itemProdutoVenda.getListaProduto()) {
				carrinhoQtd++;
				valorTotalCarrinho = valorTotalCarrinho + (produto.getValorUnitario()*produto.getQuantidade());
				listaProdutosCarrinho.add(produto);
			}
		}
		
		request.getSession().setAttribute("carrinhoQtd", carrinhoQtd);
		request.getSession().setAttribute("listaItensCarrinho", listaItensCarrinho);
		request.getSession().setAttribute("valorTotalCarrinho", valorTotalCarrinho);
		
	}
	
	private void mostrarAlertMensagem(HttpSession sessao, String mensagem){
		
		sessao.setAttribute("showMsgAlertError", true);
		sessao.setAttribute("msgAlert", mensagem);
		
	}
	
	private void limparAlertMensagem(HttpSession sessao){
		sessao.setAttribute("showMsgAlertSucess", false);
		sessao.setAttribute("showMsgAlertError", false);
		sessao.setAttribute("msgAlert", "");
	}
	
	private void realizarFinalizacaoCarrinho(HttpServletRequest request) throws RegraNegocioException, Exception{
		
		//TODO Inserir na base
		
		limparAlertMensagem(request.getSession());
		request.getSession().setAttribute("showMsgAlertSucess", true);
		String lingua = (String)request.getSession().getAttribute("lingua");
		String mensagemSucesso = new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "carrinho.venda.finalizada.sucesso");
		request.getSession().setAttribute("msgAlert", mensagemSucesso);
		
	}
	

}
