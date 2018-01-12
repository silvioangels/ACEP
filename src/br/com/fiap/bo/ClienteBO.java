package br.com.fiap.bo;

import br.com.fiap.beans.Cliente;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class ClienteBO {
	
	public void inserir(Cliente objeto) throws Exception, RegraNegocioException {
		//TODO Regra de Negocio
		
		new ClienteDAO().inserir(objeto);
	}
	
	public void alterar(Cliente objeto) throws Exception, RegraNegocioException {
		//TODO Regra de Negocio
		
		new ClienteDAO().alterar(objeto);
	}
	
	public void excluir(int codigo) throws Exception, RegraNegocioException {
		//TODO Regra de Negocio
		
		new ClienteDAO().excluir(codigo);
	}
	
	public Cliente consultar(int codigo) throws Exception, RegraNegocioException {
		//TODO Regra de Negocio
		
		return new ClienteDAO().consultar(codigo);
	}
	
	public Cliente consultarPorLoginSenha(String login, String senha) throws Exception, RegraNegocioException {
		//TODO Regra de Negocio
		
		return new ClienteDAO().consultarClienteLoginSenha(login, senha);
	}
	
	public boolean validarLoginSenha(String login, String senha) throws Exception, RegraNegocioException{
		
		Boolean validarLogin = false;
		
		Cliente cliente = new ClienteDAO().consultarClienteLoginSenha(login, senha);
		
		if(cliente != null){
			validarLogin = true;
		}
		
		return validarLogin;
	}
	
	public int recuperarProximoRegistro()  throws Exception, RegraNegocioException{
		
		//TODO Regra de Negocio
		
		return new ClienteDAO().recuperarProximoRegistro();
		
	}

}
