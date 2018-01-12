package br.com.fiap.bo;

import br.com.fiap.beans.Cor;
import br.com.fiap.beans.MateriaPrima;
import br.com.fiap.dao.MateriaPrimaDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class MateriaPrimaBO {
	
	public void inserir(MateriaPrima objeto) throws Exception, RegraNegocioException {

		if(objeto.getQuantidade() <= 10){
			throw new RegraNegocioException("Quantidade não pode ser menor ou igual a dez");
		}
		
		Cor cor = new CorBO().consultar(objeto.getCodigo());
		
		if(cor == null){
			throw new RegraNegocioException("Necessário inserir uma vor valida");
		}

		new MateriaPrimaDAO().inserir(objeto);
	}

	public void alterar(MateriaPrima objeto) throws Exception, RegraNegocioException {

		if(objeto.getQuantidade() <= 10){
			throw new RegraNegocioException("Quantidade não pode ser menor ou igual a dez");
		}
		
		Cor cor = new CorBO().consultar(objeto.getCodigo());
		
		if(cor == null){
			throw new RegraNegocioException("Necessário inserir uma vor valida");
		}

		new MateriaPrimaDAO().alterar(objeto);
	}

	public void excluir(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		new MateriaPrimaDAO().excluir(codigo);
	}

	public MateriaPrima consultar(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new MateriaPrimaDAO().consultar(codigo);
	}

}
