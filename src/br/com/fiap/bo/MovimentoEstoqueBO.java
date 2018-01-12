package br.com.fiap.bo;

import br.com.fiap.beans.MovimentoEstoque;
import br.com.fiap.dao.MovimentoEstoqueDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class MovimentoEstoqueBO {

	public void inserir(MovimentoEstoque objeto) throws Exception, RegraNegocioException {

		//TODO Regra de Negocio
		
		new MovimentoEstoqueDAO().inserir(objeto);
	}

	public void alterar(MovimentoEstoque objeto) throws Exception, RegraNegocioException {
		
		new MovimentoEstoqueDAO().alterar(objeto);
	}

	public void excluir(int codigo) throws Exception, RegraNegocioException {


		new MovimentoEstoqueDAO().excluir(codigo);
	}

	public MovimentoEstoque consultar(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new MovimentoEstoqueDAO().consultar(codigo);
	}
	
	public int recuperarProximoRegistro() throws Exception{
		
		// TODO Regra de Negocio

		return new MovimentoEstoqueDAO().recuperarProximoRegistro();
		
	}
	

}
