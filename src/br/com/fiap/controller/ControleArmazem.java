package br.com.fiap.controller;

import java.io.IOException;
import java.sql.SQLRecoverableException;
import java.util.Calendar;
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
import br.com.fiap.beans.MovimentoEstoque;
import br.com.fiap.bo.ArmazemBO;
import br.com.fiap.bo.MovimentoEstoqueBO;
import br.com.fiap.excecao.RegraNegocioException;
import properties.PropertiesFactory;

/**
 * Servlet implementation class Controle
 */
@WebServlet(urlPatterns = "/controlearmazem")

public class ControleArmazem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControleArmazem() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//limpando a sessao de msg
		limparAlertMensagem(request.getSession());
		
		String pagina = "pages-stock-storage.jsp";
		String opcao = request.getParameter("opcao");
		
		try {
			
			if(opcao.equals("armazem")){
				pagina = "pages-stock-storage.jsp";
				
				recuperarPercentualMateriasPrimas(request);
				
			}else{
				pagina = "pages-stock-movement.jsp";
				inserirMovimentacaoArmazem(request);
				
				String lingua = (String)request.getSession().getAttribute("lingua");
				String msgProp 	  =  "armazem.movimentacao.sucesso";
				String msgSucesso = new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp);
				
				request.getSession().setAttribute("showMsgAlertSucess", true);
				request.getSession().setAttribute("msgAlert", msgSucesso);
				
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
	
	private void recuperarPercentualMateriasPrimas(HttpServletRequest request) throws Exception{
		
		String idArmazem = request.getParameter("idarmazem");
		ArmazemBO bo = new ArmazemBO();
		List<Integer> listaEstoquesArmazem = bo.consultarPercentualMateriasPrimasArmazem(Integer.parseInt(idArmazem));
		
		int percAzul 	= listaEstoquesArmazem.get(0);
		int percMajenta = listaEstoquesArmazem.get(1);
		int percBranco  = listaEstoquesArmazem.get(2);
		
		request.getSession().setAttribute("percAzul"	, percAzul);
		request.getSession().setAttribute("percMajenta"	, percMajenta);
		request.getSession().setAttribute("percBranco"	, percBranco);
		request.getSession().setAttribute("idArmazem"	, idArmazem);
		
	}
	
	private void inserirMovimentacaoArmazem(HttpServletRequest request) throws Exception{
		
		String custo 					= request.getParameter("custo");
		String tipoTrans 				= request.getParameter("tipoTrans");
		String tempoEntrega 			= request.getParameter("tempoEntrega");
		String fornecimento 			= request.getParameter("fornecimento");
		String codigoArmazemSelecionado = request.getParameter("codigoArmazemSelecionado");
		
		MovimentoEstoqueBO bo = new MovimentoEstoqueBO();
		
		MovimentoEstoque movEstoq = new MovimentoEstoque();
		movEstoq.setCodigo(bo.recuperarProximoRegistro());
		Estoque est = new Estoque();
		est.setCodigo(Integer.parseInt(codigoArmazemSelecionado));
		movEstoq.setEstoque(est);
		movEstoq.setTipoDeslocamento(tipoTrans);
		movEstoq.setValorCusto(Double.parseDouble(custo));
		movEstoq.setTempoAbastecimento(Double.parseDouble(fornecimento));
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, Integer.parseInt(tempoEntrega));
		
		movEstoq.setPrazoEntrega(c.getTime());
		movEstoq.setPrecoFinal(Double.parseDouble(custo));
		
		bo.inserir(movEstoq);
		
	}
	
	private void mostrarAlertMensagem(HttpSession sessao, String mensagem){
		
		sessao.setAttribute("showMsgAlertError", true);
		sessao.setAttribute("msgAlert", mensagem);
		
	}
	
	private void limparAlertMensagem(HttpSession sessao){
		
		sessao.setAttribute("showMsgAlertError", false);
		sessao.setAttribute("showMsgAlertSucess", false);
		sessao.setAttribute("msgAlert", "");
		
	}

}
