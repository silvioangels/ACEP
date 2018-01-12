package br.com.fiap.bo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fiap.beans.Preditiva;
import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.dao.PreditivaDAO;
import br.com.fiap.excecao.RegraNegocioException;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

public class PreditivaBO {

	public void inserir(Preditiva objeto) throws Exception, RegraNegocioException {
		
		// TODO Regra de Negocio

		new PreditivaDAO().inserir(objeto);
	}

	public void alterar(Preditiva objeto) throws Exception, RegraNegocioException {
		
		// TODO Regra de Negocio
		
		new PreditivaDAO().alterar(objeto);
	}

	public void excluir(int codigo) throws Exception, RegraNegocioException {
		
		// TODO Regra de Negocio

		new PreditivaDAO().excluir(codigo);
	}

	public Preditiva consultar(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new PreditivaDAO().consultar(codigo);
	}
	
	public boolean validarAnalisePreditivaMes(String mes) throws Exception, RegraNegocioException {
		
		return new PreditivaDAO().validarAnalisePreditivaMes(mes);
		
	}
	
	public Map<String, List<String>> realizarAnalisePreditivaConsumoMateriaPrima(String mes) throws Exception, RegraNegocioException {
		DecimalFormat formatadorNumeros = new DecimalFormat("#,##0.00");
		
		Map<String, List<String>> retornoConsumoMateriaPrima = new HashMap<String, List<String>>();
		
		String query = new PreditivaDAO().recuperarQueryRealizarAnalisePreditivaConsumoMateriaPrima(mes);
		
		InstanceQuery instTrainQuery = new ConexaoFactory().getWekaInstanceOracleConnection();
		Instances ins = instTrainQuery.retrieveInstances(query);
		System.out.println(ins);
		
		//Campo Status de Consumo que vai ser feito a preditibilidade
		ins.setClassIndex(5);
		
		NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(ins);
        
        for (int i = 0; i < 6; i++) {
			
        	//É criado uma nova instancia com o valor que se deseja fazer a previsão, caso é materia prima
			Instance novo = new DenseInstance(5);//5-> quantidade de atributos
			novo.setDataset(ins);
			novo.setValue(1,i+1);//-> 1 -> atributo de materia prima, i+1-> valor do codigo da materia prima
			
			double probabilidade[] = nb.distributionForInstance(novo);
			
			List<String> listaValoresConsumoMateriaPrima = new ArrayList<String>();
			
			BigDecimal probabilidadeCalculada = BigDecimal.ZERO;
			
			for (int j = 0; j < probabilidade.length; j++) {
				BigDecimal  bdProbabilidade = new BigDecimal(probabilidade[j]);
				probabilidadeCalculada = bdProbabilidade.multiply(BigDecimal.TEN.multiply(BigDecimal.TEN)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				listaValoresConsumoMateriaPrima.add(formatadorNumeros.format(probabilidadeCalculada));
			}
			
			retornoConsumoMateriaPrima.put("listaValoresConsumoMateriaPrima_"+i, listaValoresConsumoMateriaPrima);
			
		}
        
        return retornoConsumoMateriaPrima;
	}

	public Map<String, List<String>> realizarAnalisePreditivaDesperdicioMateriaPrima(String mes) throws Exception {
		DecimalFormat formatadorNumeros = new DecimalFormat("#,##0.00");
		
		Map<String, List<String>> retornoDesperdicioMateriaPrima = new HashMap<String, List<String>>();
		
		String query = new PreditivaDAO().recuperarQueryRealizarAnalisePreditivaDesperdicioMateriaPrima(mes);
		
		InstanceQuery instTrainQuery = new ConexaoFactory().getWekaInstanceOracleConnection();
		Instances ins = instTrainQuery.retrieveInstances(query);
		System.out.println(ins);
		
		//Campo Status de Consumo que vai ser feito a preditibilidade
		ins.setClassIndex(5);
		
		NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(ins);
        
        for (int i = 0; i < 6; i++) {
			
        	//É criado uma nova instancia com o valor que se deseja fazer a previsão, caso é materia prima
			Instance novo = new DenseInstance(5);//5-> quantidade de atributos
			novo.setDataset(ins);
			novo.setValue(1,i+1);//-> 1 -> atributo de materia prima, i+1-> valor do codigo da materia prima
			
			double probabilidade[] = nb.distributionForInstance(novo);
			
			List<String> listaValoresDesperdicioMateriaPrima = new ArrayList<String>();
			
			BigDecimal probabilidadeCalculada = BigDecimal.ZERO;
			
			for (int j = 0; j < probabilidade.length; j++) {
				BigDecimal  bdProbabilidade = new BigDecimal(probabilidade[j]);
				probabilidadeCalculada = bdProbabilidade.multiply(BigDecimal.TEN.multiply(BigDecimal.TEN)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				listaValoresDesperdicioMateriaPrima.add(formatadorNumeros.format(probabilidadeCalculada));
			}
			
			retornoDesperdicioMateriaPrima.put("listaValoresDesperdicioMateriaPrima_"+i, listaValoresDesperdicioMateriaPrima);
			
		}
        
        return retornoDesperdicioMateriaPrima;
	}

}
