package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.beans.Armazem;
import br.com.fiap.dao.ArmazemDAO;
import br.com.fiap.dao.EstoqueDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class ArmazemBO {
	
	public void inserir(Armazem objeto)throws Exception, RegraNegocioException{
		
		if(objeto.getQtdArmazenamento()  <= 10){
			throw new RegraNegocioException("Quantidade de armazenamento menot que o nivel minimo");
		}
		
		if(objeto.getDataRecebimento().compareTo(objeto.getDataVencimento()) >= 0){
			throw new RegraNegocioException("Data de Vencimento não pode ser menor ou igual a data de Recebimento");
		}
		
		new ArmazemDAO().inserir(objeto);
	}
	
	public void alterar(Armazem objeto)throws Exception, RegraNegocioException{
		
		if(objeto.getQtdArmazenamento()  <= 10){
			throw new RegraNegocioException("Quantidade de armazenamento menot que o nivel minimo");
		}
		
		if(objeto.getDataRecebimento().compareTo(objeto.getDataVencimento()) >= 0){
			throw new RegraNegocioException("Data de Vencimento não pode ser menor ou igual a data de Recebimento");
		}
		
		new ArmazemDAO().alterar(objeto);
	}
	
	public void excluir(int codigo)throws Exception, RegraNegocioException{
		
		//TODO Regra de Negocio
		
		new ArmazemDAO().excluir(codigo);
	}
	
	public Armazem consultar(int codigo)throws Exception, RegraNegocioException{
		
		//TODO Regra de Negocio
		
		return new ArmazemDAO().consultar(codigo);
	}
	
	public List<Integer> consultarPercentualMateriasPrimasArmazem(int codigoArmazem) throws Exception{
		int percAzul 	= 0;
		int percMajenta = 0;
		int percBranco  = 0;
		
		List<Integer> listaEstoquesArmazem = new EstoqueDAO().consultarNumeroEstoquePeloArmazem(codigoArmazem);
		
		for (Integer codigoEstoque : listaEstoquesArmazem) {
			
			percAzul    = percAzul + new EstoqueDAO().consultarPercentualMateriaPrimaDisponivelPorEstoque(codigoEstoque, 1);
			percMajenta = percMajenta + new EstoqueDAO().consultarPercentualMateriaPrimaDisponivelPorEstoque(codigoEstoque, 2);
			percBranco  = percBranco + new EstoqueDAO().consultarPercentualMateriaPrimaDisponivelPorEstoque(codigoEstoque, 3);
			
		}
		
		listaEstoquesArmazem.add(percAzul);
		listaEstoquesArmazem.add(percMajenta);
		listaEstoquesArmazem.add(percBranco);
		
		return listaEstoquesArmazem;
	}
	
}
