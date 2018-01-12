package br.com.fiap.bo;

import br.com.fiap.beans.Cor;
import br.com.fiap.dao.CorDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class CorBO {

	public void inserir(Cor objeto) throws Exception, RegraNegocioException {

		if(consultarQuantidadeCores() >= 3){
			throw new RegraNegocioException("Não pode ser inserido mais que 3 cores");
		}

		new CorDAO().inserir(objeto);
	}

	public void alterar(Cor objeto) throws Exception, RegraNegocioException {
		
		new CorDAO().alterar(objeto);
	}

	public void excluir(int codigo) throws Exception, RegraNegocioException {

		if(consultarQuantidadeCores() == 1){
			throw new RegraNegocioException("Não é possivel excluir, deve existir ao menos uma cor");
		}

		new CorDAO().excluir(codigo);
	}

	public Cor consultar(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new CorDAO().consultar(codigo);
	}
	
	public int consultarQuantidadeCores() throws Exception, RegraNegocioException{
		return new CorDAO().consultarQuantidadeCores();
	}

}
