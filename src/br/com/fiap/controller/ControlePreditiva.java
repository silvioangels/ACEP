package br.com.fiap.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLRecoverableException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.com.fiap.bo.MateriaPrimaBO;
import br.com.fiap.bo.PreditivaBO;
import br.com.fiap.excecao.RegraNegocioException;
import properties.PropertiesFactory;

/**
 * Servlet implementation class Controle
 */
@WebServlet(urlPatterns = "/controlepreditiva")

public class ControlePreditiva extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControlePreditiva() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//limpando a sessao de msg
		limparAlertMensagem(request.getSession());
			
		String pagina = "pages-trend-analysis.jsp";
		String opcao = request.getParameter("opcao");
		
		try {
			
			if(opcao.equals("abrir")){
				response.sendRedirect(pagina);
				
			}else if(opcao.equals("exportar")){
				
				exportarOpcaoSelecionada(request, response);
				
			}
			
			
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
	
	private void exportarOpcaoSelecionada(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		Boolean switchConsumoMateriaPrima	 	= request.getParameter("switchConsumoMateriaPrima") != null;
		Boolean switchDesperdicioMateriaPrima 	= request.getParameter("switchDesperdicioMateriaPrima") != null;
		Boolean switchDanoProducao 			 	= request.getParameter("switchDanoProducao") != null;
		Boolean switchMateriaPrimaArmazem	 	= request.getParameter("switchMateriaPrimaArmazem") != null;
		Boolean switchProducaoCumprida 		 	= request.getParameter("switchProducaoCumprida") != null;
		String  mesExportacao 				 	= request.getParameter("selectMes");
		String  tipoExportacao 				 	= request.getParameter("tipoExportacao");
		String  msgProp 						=  "";
		String  lingua 							= (String)request.getSession().getAttribute("lingua");
		PreditivaBO bo = new PreditivaBO();
		Map<String, List<String>> retornoAnalisePreditiva 			= new HashMap<String, List<String>>(); 
		
		//REALIZANDO VALIDACOES
		if((!switchConsumoMateriaPrima && !switchDesperdicioMateriaPrima && !switchDanoProducao && !switchMateriaPrimaArmazem && !switchProducaoCumprida)){
			msgProp =  "relatorios.preditiva.selecionar";
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));
		}
		
		if((switchDanoProducao || switchMateriaPrimaArmazem || switchProducaoCumprida)){
			msgProp =  "relatorios.nao.disponivel";
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));
		}
		
		if(bo.validarAnalisePreditivaMes(mesExportacao)){
			msgProp =  "relatorios.preditiva.validar.mes";
			throw new RegraNegocioException(new PropertiesFactory().recuperarPropriedadeMensagens(lingua, msgProp));
		}
		
		
		//REALIZANDO ANALISE PREDITIVA
		if(switchConsumoMateriaPrima){
			
			List<String> listaLabelsConsumoMateriaPrima = Arrays.asList(
					new String[]{
							new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "relatorios.medio").concat(" %"),
							new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "relatorios.alto").concat(" %"),
							new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "relatorios.muito.baixo").concat(" %"),
							new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "relatorios.alto").concat(" %"),
							new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "relatorios.baixo").concat(" %"),
					}
			);
			retornoAnalisePreditiva.put("listaLabelsConsumoMateriaPrima",listaLabelsConsumoMateriaPrima);
			
			Map<String, List<String>> retornoConsumoMateriaPrima = bo.realizarAnalisePreditivaConsumoMateriaPrima(mesExportacao);
			retornoAnalisePreditiva.putAll(retornoConsumoMateriaPrima);
		}
		
		if(switchDesperdicioMateriaPrima){
			
			List<String> listaLabelsDesperdicioMateriaPrima = Arrays.asList(
					new String[]{
							new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "relatorios.medio").concat(" %"),
							new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "relatorios.alto").concat(" %"),
							new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "relatorios.muito.baixo").concat(" %"),
							new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "relatorios.alto").concat(" %"),
							new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "relatorios.baixo").concat(" %"),
					}
			);
			retornoAnalisePreditiva.put("listaLabelsDesperdicioMateriaPrima",listaLabelsDesperdicioMateriaPrima);
			
			Map<String, List<String>> retornoDesperdicioMateriaPrima = bo.realizarAnalisePreditivaDesperdicioMateriaPrima(mesExportacao);
			retornoAnalisePreditiva.putAll(retornoDesperdicioMateriaPrima);
		}
		
		//EXPORTANDO
		if(tipoExportacao.equals("xls")){
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			if(retornoAnalisePreditiva.containsKey("listaLabelsConsumoMateriaPrima")){
				MateriaPrimaBO boMp = new MateriaPrimaBO(); 
				
				String nomeAba = new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "relatorios.consumo.materia.primas");
				String nomeMateriaPrima = new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "fornecedores.materia.prima");
				
				//Cria nova aba
				HSSFSheet sheet =  workbook.createSheet(nomeAba);
				
				//Cria a linha Cabeçalho de Labels
				List<String> listaLabelsConsumoMateriaPrima = retornoAnalisePreditiva.get("listaLabelsConsumoMateriaPrima");
				HSSFRow rowhead =   sheet.createRow(0);
				rowhead.createCell(0).setCellValue(nomeMateriaPrima);
				for (int i = 0; i < 5; i++) {
					rowhead.createCell(i+1).setCellValue(listaLabelsConsumoMateriaPrima.get(i));
				}
				
				//Cria a linha de Valores
				for (int i = 0; i < 6; i++) {
					List<String> listaValoresConsumoMateriaPrima = retornoAnalisePreditiva.get("listaValoresConsumoMateriaPrima_"+i);
					rowhead =   sheet.createRow(i+1);
					int qtdColunas = listaValoresConsumoMateriaPrima.size()+1;
					for (int j = 0; j < qtdColunas; j++) {
						if(j==0){
							rowhead.createCell(j).setCellValue(boMp.consultar(i+1).getDescricao());
						}else{
							rowhead.createCell(j).setCellValue(listaValoresConsumoMateriaPrima.get(j-1));
						}
						
					}
				}
			}
			
			if(retornoAnalisePreditiva.containsKey("listaLabelsDesperdicioMateriaPrima")){
				MateriaPrimaBO boMp = new MateriaPrimaBO(); 
				
				String nomeAba = new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "relatorios.desperdicio.materia.prima");
				String nomeMateriaPrima = new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "fornecedores.materia.prima");
				
				//Cria nova aba
				HSSFSheet sheet =  workbook.createSheet(nomeAba);
				
				//Cria a linha Cabeçalho de Labels
				List<String> listaLabelsConsumoMateriaPrima = retornoAnalisePreditiva.get("listaLabelsDesperdicioMateriaPrima");
				HSSFRow rowhead =   sheet.createRow(0);
				rowhead.createCell(0).setCellValue(nomeMateriaPrima);
				for (int i = 0; i < 5; i++) {
					rowhead.createCell(i+1).setCellValue(listaLabelsConsumoMateriaPrima.get(i));
				}
				
				//Cria a linha de Valores
				for (int i = 0; i < 6; i++) {
					List<String> listaValoresConsumoMateriaPrima = retornoAnalisePreditiva.get("listaValoresDesperdicioMateriaPrima_"+i);
					rowhead =   sheet.createRow(i+1);
					int qtdColunas = listaValoresConsumoMateriaPrima.size()+1;
					for (int j = 0; j < qtdColunas; j++) {
						if(j==0){
							rowhead.createCell(j).setCellValue(boMp.consultar(i+1).getDescricao());
						}else{
							rowhead.createCell(j).setCellValue(listaValoresConsumoMateriaPrima.get(j-1));
						}
						
					}
				}
				
			}
			
			String nomePlanilha = new PropertiesFactory().recuperarPropriedadeMensagens(lingua, "indice.analise.preditiva.mais.procurados");
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			workbook.write(outByteStream);
			workbook.close();
			byte [] outArray = outByteStream.toByteArray();
			response.setContentType("application/octet-stream"); 
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0");
			response.setHeader("Content-Disposition", "attachment; filename="+nomePlanilha+".xls");
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();
			
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
