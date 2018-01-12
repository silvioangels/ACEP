package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.beans.Cliente;
import br.com.fiap.conexao.ConexaoFactory;

public class ClienteDAO {

	private Connection con;

	public ClienteDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}

	public void fechar() throws Exception {
		con.close();
	}
	
	public void inserir(Cliente objeto)throws Exception{
		
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_CLIENTE (CD_CLIENTE, DS_NOME, DS_EMAIL, NR_TELEFONE, DS_PERFIL, NM_LOGIN, DS_SENHA) VALUES (?,?,?,?,?,?,?)"
		);
		
		estrutura.setInt(	1, objeto.getCodigo());
		estrutura.setString(2, objeto.getNome());
		estrutura.setString(3, objeto.getEmail());
		estrutura.setLong(	4, objeto.getNumeroTelefone());
		estrutura.setString(5, objeto.getPerfil());
		estrutura.setString(6, objeto.getLogin());
		estrutura.setString(7, objeto.getSenha());
		
		estrutura.execute();
		estrutura.close();
		fechar();
		
	}
	
	public void alterar(Cliente objeto)throws Exception{
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_CLIENTE SET DS_NOME = ?, DS_EMAIL = ?, NR_TELEFONE = ?, DS_PERFIL = ?, NM_LOGIN = ?, DS_SENHA = ? WHERE CD_CLIENTE = ?"
		);
		
		estrutura.setString(1, objeto.getNome());
		estrutura.setString(2, objeto.getEmail());
		estrutura.setLong(	3, objeto.getNumeroTelefone());
		estrutura.setString(4, objeto.getPerfil());
		estrutura.setString(5, objeto.getLogin());
		estrutura.setString(6, objeto.getSenha());
		estrutura.setInt(	7, objeto.getCodigo());
		
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
	}
	
	public void excluir(int codigo)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_CLIENTE WHERE CD_CLIENTE = ?"
		);
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}
	
	public Cliente consultar(int codigo) throws Exception{
		Cliente cliente = null;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_CLIENTE WHERE CD_CLIENTE = ?"
		);
		
		estrutura.setInt(1, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			cliente = new Cliente();
			cliente.setCodigo(resultado.getInt("CD_CLIENTE"));
			cliente.setNome(resultado.getString("DS_NOME"));
			cliente.setEmail(resultado.getString("DS_EMAIL"));
			cliente.setNumeroTelefone(resultado.getLong("NR_TELEFONE"));
			cliente.setPerfil(resultado.getString("DS_PERFIL"));
			cliente.setLogin(resultado.getString("NM_LOGIN"));
			cliente.setSenha(resultado.getString("DS_SENHA"));
		}
		
		estrutura.close();
		fechar();
		return cliente;
	}
	
	public Cliente consultarClienteLoginSenha(String login, String senha) throws Exception{
		
		Cliente cliente = null;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_CLIENTE WHERE NM_LOGIN = ? AND DS_SENHA = ?"
		);
		
		estrutura.setString(1, login);
		estrutura.setString(2, senha);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			cliente = new Cliente();
			cliente.setCodigo(resultado.getInt("CD_CLIENTE"));
			cliente.setNome(resultado.getString("DS_NOME"));
			cliente.setEmail(resultado.getString("DS_EMAIL"));
			cliente.setNumeroTelefone(resultado.getLong("NR_TELEFONE"));
			cliente.setPerfil(resultado.getString("DS_PERFIL"));
			cliente.setLogin(resultado.getString("NM_LOGIN"));
			cliente.setSenha(resultado.getString("DS_SENHA"));
			cliente.setUsuarioAdministrador(cliente.getPerfil().equalsIgnoreCase("ADMINISTRADOR"));
		}
		
		estrutura.close();
		fechar();
		return cliente;
	}
	
	public int recuperarProximoRegistro() throws Exception{
		
		int nrProximoRegistro = 0;
		
		PreparedStatement estrutura = con.prepareStatement(
				"SELECT MAX(CD_CLIENTE)+1 AS PROX_REG FROM T_ACEP_CLIENTE"
		);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			nrProximoRegistro = resultado.getInt("PROX_REG");
		}
		
		estrutura.close();
		fechar();
		return nrProximoRegistro;
	}

}
