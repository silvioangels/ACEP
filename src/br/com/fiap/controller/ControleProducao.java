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

import br.com.fiap.beans.AfericaoProduto;
import br.com.fiap.beans.Equipamento;
import br.com.fiap.beans.PedidoProducao;
import br.com.fiap.beans.PedidoVenda;
import br.com.fiap.bo.AfericaoProdutoBO;
import br.com.fiap.bo.EquipamentoBO;
import br.com.fiap.bo.ProducaoBO;
import br.com.fiap.excecao.RegraNegocioException;
import properties.PropertiesFactory;

/**
 * Servlet implementation class Controle
 */
@WebServlet(urlPatterns = "/controleproducao")

public class ControleProducao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControleProducao() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//limpando a sessao de msg
		limparAlertMensagem(request.getSession());
		
		String pagina = "pages-production-order.jsp";

		try {
			
			String opcao = request.getParameter("opcao");
			
			if(opcao.equalsIgnoreCase("ordemservico")){
				
				buscarOrdemServico(request.getSession());
				
			}else if(opcao.equalsIgnoreCase("requisicao")){
				pagina = "pages-production-request.jsp";
				
				buscarRequisicoesProducao(request.getSession());
				
			}else if(opcao.equalsIgnoreCase("equipamento")){
				pagina = "pages-production-equipment.jsp";
				
				buscarEquipamentos(request.getSession());
				
			}else if(opcao.equalsIgnoreCase("equipamento-admin")){
				pagina = "pages-production-equipment-admin-portal.jsp";
				
				realizarAcoesEquipamentoAdminPortal(request);
				
				buscarEquipamentos(request.getSession());
				
			}else if(opcao.equalsIgnoreCase("afericao")){
				pagina = "pages-production-quality.jsp";
				
				buscarQualidadeProdutos(request.getSession());
				
			}
			
			response.sendRedirect(pagina);

		} catch (RegraNegocioException e) {

			mostrarAlertMensagem(request.getSession(), e.getMessage());
			response.sendRedirect(pagina);

		} catch (SQLRecoverableException e) {

			String lingua = (String) request.getSession().getAttribute("lingua");
			String msgProp = "banco.fiap.indisponibilidade";

			try {

				mostrarAlertMensagem(request.getSession(),
						new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));

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
			String msgProp = "acep.erro.critico";

			try {

				mostrarAlertMensagem(request.getSession(),
						new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));

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
	
	private void buscarOrdemServico(HttpSession sessao) throws RegraNegocioException, Exception{
		
		ProducaoBO bo = new ProducaoBO();
		List<PedidoVenda> listaPedidoProducao = bo.consultarOrdensServico();

		sessao.setAttribute("listaPedidoProducao", listaPedidoProducao);
	}
	
	private void buscarRequisicoesProducao(HttpSession sessao) throws RegraNegocioException, Exception{
		
		ProducaoBO bo = new ProducaoBO();
		List<PedidoProducao> listaPedidoProducao = bo.buscarRequisicoesProducao();
		
		String lingua = (String)sessao.getAttribute("lingua");
		
		traduzirLinguaListaRequisicoesProducao(listaPedidoProducao, lingua);
		
		sessao.setAttribute("listaPedidoProducao", listaPedidoProducao);
		
	}
	
	private void traduzirLinguaListaRequisicoesProducao(List<PedidoProducao> listaPedidoProducao, String lingua) throws Exception{
		
		for (PedidoProducao pedidoProducao : listaPedidoProducao) {
			
			String msgProp =  "producao.requisicao.tabela.status."+pedidoProducao.getSp().getCodigo();
			
			pedidoProducao.getSp().setDescricaoTraduzida(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));
			
		}
		
	}
	
	private void buscarEquipamentos(HttpSession sessao) throws RegraNegocioException, Exception{
		
		EquipamentoBO bo = new EquipamentoBO();
		List<Equipamento> listaEquipamento= bo.consultarTodosEquipamentos();
		
		String lingua = (String)sessao.getAttribute("lingua");
		
		traduzirLinguaListaEquipamentos(listaEquipamento, lingua);

		sessao.setAttribute("listaEquipamento", listaEquipamento);
	}
	
	private void realizarAcoesEquipamentoAdminPortal(HttpServletRequest request) throws RegraNegocioException, Exception{
		EquipamentoBO bo = new EquipamentoBO();
		
		String msgProp 			= "";
		String msgAlertSucesso 	= "";
		String lingua 			= (String)request.getSession().getAttribute("lingua");
		String acao 			= request.getParameter("acao");
		String codigo 			= request.getParameter("codigoEquip");
		String descricao 		= request.getParameter("descricaoEquip");
		
		Equipamento equipamento = new Equipamento();
		equipamento.setCodigo(Integer.parseInt(codigo));
		equipamento.setDescricao(descricao);
		
		if(acao.equalsIgnoreCase("inserir")){
			
			int proxReg = bo.recuperarProximoRegistro();
			
			equipamento.setCodigo(proxReg);
			bo.inserir(equipamento);
			
			msgProp =  "equipamento.admin.inserir.sucesso";
			msgAlertSucesso = new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp);
			
		}else if(acao.equalsIgnoreCase("alterar")){
			
			bo.alterar(equipamento);
			
			msgProp =  "equipamento.admin.alterar.sucesso";
			msgAlertSucesso = new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp);
			
		}else if(acao.equalsIgnoreCase("excluir")){
			
			bo.excluir(equipamento.getCodigo());
			
			msgProp =  "equipamento.admin.excluir.sucesso";
			msgAlertSucesso = new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp);
			
		}
		
		request.getSession().setAttribute("msgAlert", msgAlertSucesso);
		request.getSession().setAttribute("showMsgAlertSucess", true);
	}
	
	private void buscarQualidadeProdutos(HttpSession sessao) throws RegraNegocioException, Exception{
		
		AfericaoProdutoBO bo = new AfericaoProdutoBO();
		List<AfericaoProduto> listaQualidadeProdutos= bo.consultarQualidadeProdutos();
		
		String lingua = (String)sessao.getAttribute("lingua");
		
		traduzirLinguaListaQualidadeProduto(listaQualidadeProdutos, lingua);

		sessao.setAttribute("listaQualidadeProdutos", listaQualidadeProdutos);
	}
	
	private void traduzirLinguaListaEquipamentos(List<Equipamento> listaPedidoProducao, String lingua) throws Exception{
		
		for (Equipamento equipamento : listaPedidoProducao) {
			
			String msgPropUsuario =  "equipamento.tabela."+equipamento.getCodigo();
			
			equipamento.setDescricaoTraduzida(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgPropUsuario));
			
		}
		
	}
	
	private void traduzirLinguaListaQualidadeProduto(List<AfericaoProduto> listaQualidadeProduto, String lingua) throws Exception{
		
		for (AfericaoProduto afericaoProduto : listaQualidadeProduto) {
			
			String msgProp =  "afericao.tabela." + afericaoProduto.getAfericao().getCodigo();
			
			afericaoProduto.getAfericao().setDescricaoTraduzida(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));
			
			msgProp =  "afericao.tabela." + afericaoProduto.getDescricaoStatus().toUpperCase();
			
			afericaoProduto.setDescricaoStatusTraduzida(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));
			
		}
		
	}

	private void mostrarAlertMensagem(HttpSession sessao, String mensagem) {

		sessao.setAttribute("showMsgAlertError", true);
		sessao.setAttribute("msgAlert", mensagem);

	}
	
	private void limparAlertMensagem(HttpSession sessao){
		
		sessao.setAttribute("showMsgAlertError", false);
		sessao.setAttribute("showMsgAlertSucess", false);
		sessao.setAttribute("msgAlert", "");
		
	}

}
