package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.beans.Cor;
import br.com.fiap.conexao.ConexaoFactory;

public class CorDAO {
	
	private Connection con ;
	
	public CorDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
	public void inserir(Cor objeto)throws Exception{
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_COR (CD_COR, DESC_COR) VALUES (?,?)"
		);
		estrutura.setInt(	1, objeto.getCodigo());
		estrutura.setString(2, objeto.getDescricao());
		estrutura.execute();
		estrutura.close();
		fechar();
	}
	
	public void alterar(Cor objeto)throws Exception{
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_COR SET DESC_COR = ? WHERE CD_COR = ?"
		);
		
		estrutura.setString(	1, objeto.getDescricao());
		estrutura.setInt(		2, objeto.getCodigo());
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
		
	}
	
	public void excluir(int codigo)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_COR WHERE CD_COR = ?"
		);
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}
	
	public Cor consultar(int codigo) throws Exception{
		Cor cor = null;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_COR WHERE CD_COR = ?"
		);
		
		estrutura.setInt(1, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			cor = new Cor();
			cor.setCodigo(resultado.getInt("CD_COR"));
			cor.setDescricao(resultado.getString("DESC_COR"));
		}
		
		estrutura.close();
		fechar();
		return cor;
	}

	public int consultarQuantidadeCores() throws Exception{
		
		int quantidadeCores = 0;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT COUNT(*) AS QTD_COR FROM T_ACEP_COR"
		);
		
		ResultSet resultado = estrutura.executeQuery();
		while (resultado.next()) {
			quantidadeCores = resultado.getInt("QTD_COR");
		}
		
		estrutura.close();
		fechar();
		return quantidadeCores;
	}

}
