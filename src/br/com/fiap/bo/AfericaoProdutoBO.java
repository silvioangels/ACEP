package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.beans.AfericaoProduto;
import br.com.fiap.dao.AfericaoProdutoDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class AfericaoProdutoBO {

	public void inserir(AfericaoProduto objeto) throws Exception, RegraNegocioException {
		//TODO Fazer regra de negocio
		
		new AfericaoProdutoDAO().inserir(objeto);
	}

	public void alterar(AfericaoProduto objeto) throws Exception, RegraNegocioException {
		
		new AfericaoProdutoDAO().alterar(objeto);
	}

	public void excluir(int codigo) throws Exception, RegraNegocioException {

		//TODO Fazer regra de negocio

		new AfericaoProdutoDAO().excluir(codigo);
	}

	public AfericaoProduto consultar(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new AfericaoProdutoDAO().consultar(codigo);
	}
	
	public List<AfericaoProduto> consultarQualidadeProdutos() throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new AfericaoProdutoDAO().consultarQualidadeProdutos();
	}
	
}
