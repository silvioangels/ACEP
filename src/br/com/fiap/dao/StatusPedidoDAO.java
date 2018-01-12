package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.beans.StatusPedido;
import br.com.fiap.conexao.ConexaoFactory;

public class StatusPedidoDAO {
	
	private Connection con ;
	
	public StatusPedidoDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
	public void inserir(StatusPedido objeto)throws Exception{
		
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_STATUS_PEDIDO (CD_STATUS_PEDIDO, DS_STATUS) VALUES (?,?)"
		);
		estrutura.setInt(	1, objeto.getCodigo());
		estrutura.setString(2, objeto.getDescricao());
		estrutura.execute();
		estrutura.close();
		fechar();
		
	}
	
	public void alterar(StatusPedido objeto)throws Exception{
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_STATUS_PEDIDO SET DS_STATUS = ? WHERE CD_STATUS_PEDIDO = ?"
		);
		
		estrutura.setString(	1, objeto.getDescricao());
		estrutura.setInt(		2, objeto.getCodigo());
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
	}
	
	public void excluir(int codigo)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_STATUS_PEDIDO WHERE CD_STATUS_PEDIDO = ?"
		);
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}
	
	public StatusPedido consultar(int codigo)throws Exception{
		StatusPedido statusPedido = null;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_STATUS_PEDIDO WHERE CD_STATUS_PEDIDO = ?"
		);
		
		estrutura.setInt(1, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			statusPedido = new StatusPedido();
			statusPedido.setCodigo(resultado.getInt("CD_STATUS_PEDIDO"));
			statusPedido.setDescricao(resultado.getString("DS_STATUS"));
		}
		
		estrutura.close();
		fechar();
		return statusPedido;
	}

}
