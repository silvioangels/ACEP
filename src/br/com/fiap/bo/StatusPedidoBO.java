package br.com.fiap.bo;

import br.com.fiap.beans.StatusPedido;
import br.com.fiap.dao.StatusPedidoDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class StatusPedidoBO {
	
	public void inserir(StatusPedido objeto) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio
						
		new StatusPedidoDAO().inserir(objeto);
		
	}

	public void alterar(StatusPedido objeto) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		new StatusPedidoDAO().alterar(objeto);
	}

	public void excluir(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		new StatusPedidoDAO().excluir(codigo);
	}

	public StatusPedido consultar(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new StatusPedidoDAO().consultar(codigo);
	}

}
