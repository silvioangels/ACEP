package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.beans.PedidoProducao;
import br.com.fiap.beans.PedidoVenda;
import br.com.fiap.beans.Producao;
import br.com.fiap.beans.Produto;
import br.com.fiap.dao.ProducaoDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class ProducaoBO {
	
	public void inserir(Producao objeto) throws Exception, RegraNegocioException {

		if(objeto.getTempoMedio() <= 5){
			throw new RegraNegocioException("Tempo medio não pode ser menor ou igual a cinco");
		}
		
		if(objeto.getQtdDesperdicioCor() < 5){
			throw new RegraNegocioException("Desperdicio de Cor não pode ser menor que cinco");
		}
		
		if(objeto.getQtdAvaria() < 2){
			throw new RegraNegocioException("Tempo medio não pode ser menor que dois");
		}
		
		Produto produto = new ProdutoBO().consultar(objeto.getProduto().getCodigo());
		if(produto == null){
			throw new RegraNegocioException("Necessário inserir um produto valido");
		}
						
		new ProducaoDAO().inserir(objeto);
		
	}

	public void alterar(Producao objeto) throws Exception, RegraNegocioException {

		if(objeto.getTempoMedio() <= 5){
			throw new RegraNegocioException("Tempo medio não pode ser menor ou igual a cinco");
		}
		
		if(objeto.getQtdDesperdicioCor() < 5){
			throw new RegraNegocioException("Desperdicio de Cor não pode ser menor que cinco");
		}
		
		if(objeto.getQtdAvaria() < 2){
			throw new RegraNegocioException("Tempo medio não pode ser menor que dois");
		}
		
		Produto produto = new ProdutoBO().consultar(objeto.getProduto().getCodigo());
		if(produto == null){
			throw new RegraNegocioException("Necessário inserir um produto valido");
		}

		new ProducaoDAO().alterar(objeto);
	}

	public void excluir(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		new ProducaoDAO().excluir(codigo);
	}

	public Producao consultar(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new ProducaoDAO().consultar(codigo);
	}
	
	public List<PedidoVenda> consultarOrdensServico() throws Exception, RegraNegocioException {
		
		// TODO Regra de Negocio
		
		return new ProducaoDAO().consultarOrdensServico();
		
	}
	
	public List<PedidoProducao> buscarRequisicoesProducao() throws Exception, RegraNegocioException {
		
		// TODO Regra de Negocio
		
		return new ProducaoDAO().buscarRequisicoesProducao();
		
	}
	

}
