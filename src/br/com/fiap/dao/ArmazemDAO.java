package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.beans.Armazem;
import br.com.fiap.conexao.ConexaoFactory;

public class ArmazemDAO {
	
	private Connection con ;

	public ArmazemDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
	public void inserir(Armazem objeto)throws Exception{
		
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_ARMAZEM (CD_ARMAZEM, NM_ARMAZEM,QT_ARMAZENADO,DT_VENCIMENTO,DT_RECEBIMENTO, DS_CONTROLE_DESPERDICIO) VALUES (?,?,?,?,?,?)"
		);
		estrutura.setInt(	1, objeto.getCodigo());
		estrutura.setString(2, objeto.getNome());
		estrutura.setInt(	3, objeto.getQtdArmazenamento());
		estrutura.setDate(	4, new java.sql.Date(objeto.getDataVencimento().getTime()));
		estrutura.setDate(	5, new java.sql.Date(objeto.getDataRecebimento().getTime()));
		estrutura.setString(6, objeto.getDescricaoControleDesperdicio());
		
		estrutura.execute();
		estrutura.close();
		fechar();
	}
	
	public void alterar(Armazem objeto)throws Exception{
		
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_ARMAZEM SET NM_ARMAZEM = ?,QT_ARMAZENADO = ?,DT_VENCIMENTO = ?,DT_RECEBIMENTO = ?, DS_CONTROLE_DESPERDICIO = ? WHERE CD_ARMAZEM = ?"
		);
		
		estrutura.setString(1, objeto.getNome());
		estrutura.setInt(	2, objeto.getQtdArmazenamento());
		estrutura.setDate(	3, new java.sql.Date(objeto.getDataVencimento().getTime()));
		estrutura.setDate(	4, new java.sql.Date(objeto.getDataRecebimento().getTime()));
		estrutura.setString(5, objeto.getDescricaoControleDesperdicio());
		estrutura.setInt(	6, objeto.getCodigo());
		
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
		
	}
	
	public void excluir(int codigo)throws Exception{
		
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_ARMAZEM WHERE CD_ARMAZEM = ?"
		);
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
		
	}
	
	public Armazem consultar(int codigo) throws Exception{
		
		Armazem armazem = null;
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_ARMAZEM WHERE CD_ARMAZEM = ?"
		);
		estrutura.setInt(1, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			armazem = new Armazem();
			armazem.setCodigo(resultado.getInt("CD_ARMAZEM"));
			armazem.setNome(resultado.getString("NM_ARMAZEM"));
			armazem.setQtdArmazenamento(resultado.getInt("QT_ARMAZENADO"));
			armazem.setDataVencimento(resultado.getDate("DT_VENCIMENTO"));
			armazem.setDataRecebimento(resultado.getDate("DT_RECEBIMENTO"));
			armazem.setDescricaoControleDesperdicio(resultado.getString("DS_CONTROLE_DESPERDICIO"));			
		}
		
		fechar();
		
		return armazem;
		
	}

}
