package br.com.fiap.bo;

import br.com.fiap.beans.Estoque;
import br.com.fiap.beans.MateriaPrima;
import br.com.fiap.beans.MateriaPrimaEstoque;
import br.com.fiap.dao.MateriaPrimaEstoqueDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class MateriaPrimaEstoqueBO {
	
	public void inserir(MateriaPrimaEstoque objeto) throws Exception, RegraNegocioException {

		if(objeto.getDataRecebimento().compareTo(objeto.getDataVencimento()) >= 0){
			throw new RegraNegocioException("Data de Vencimento não pode ser menor ou igual a data de Recebimento");
		}
		
		MateriaPrima materiaPrima = new MateriaPrimaBO().consultar(objeto.getMateriaPrima().getCodigo());
		if(materiaPrima == null){
			throw new RegraNegocioException("Necessário inserir uma materia prima valida");
		}
		
		Estoque estoque = new EstoqueBO().consultar(objeto.getEstoque().getCodigo());
		if(estoque == null){
			throw new RegraNegocioException("Necessário inserir um estoque valido");
		}

		new MateriaPrimaEstoqueDAO().inserir(objeto);
	}

	public void alterar(MateriaPrimaEstoque objeto) throws Exception, RegraNegocioException {

		if(objeto.getDataRecebimento().compareTo(objeto.getDataVencimento()) >= 0){
			throw new RegraNegocioException("Data de Vencimento não pode ser menor ou igual a data de Recebimento");
		}
		
		MateriaPrima materiaPrima = new MateriaPrimaBO().consultar(objeto.getMateriaPrima().getCodigo());
		if(materiaPrima == null){
			throw new RegraNegocioException("Necessário inserir uma materia prima valida");
		}
		
		Estoque estoque = new EstoqueBO().consultar(objeto.getEstoque().getCodigo());
		if(estoque == null){
			throw new RegraNegocioException("Necessário inserir um estoque valido");
		}

		new MateriaPrimaEstoqueDAO().alterar(objeto);
	}

	public void excluir(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		new MateriaPrimaEstoqueDAO().excluir(codigo);
	}

	public MateriaPrimaEstoque consultar(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new MateriaPrimaEstoqueDAO().consultar(codigo);
	}

}
