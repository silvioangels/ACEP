package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.beans.Cor;
import br.com.fiap.beans.MateriaPrima;
import br.com.fiap.conexao.ConexaoFactory;

public class MateriaPrimaDAO {
	
	private Connection con ;
	
	public MateriaPrimaDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar() throws Exception{
		con.close();
	}
	
	public void inserir(MateriaPrima objeto) throws Exception{
		
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_MATERIA_PRIMA (CD_MATERIA_PRIMA, CD_COR, DS_MATERIA_PRIMA, QTD_TOTAL) VALUES (?,?,?, ?)"
		);
		estrutura.setInt(	1, objeto.getCodigo());
		estrutura.setInt(	2, objeto.getCor().getCodigo());
		estrutura.setString(3, objeto.getDescricao());
		estrutura.setInt(	4, objeto.getQuantidade());
		estrutura.execute();
		estrutura.close();
		fechar();
		
	}
	
	public void alterar(MateriaPrima objeto) throws Exception{
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_MATERIA_PRIMA SET CD_COR = ?,DS_MATERIA_PRIMA = ?, QTD_TOTAL=?  WHERE CD_MATERIA_PRIMA = ?"
		);
		
		estrutura.setInt(	1, objeto.getCor().getCodigo());
		estrutura.setString(2, objeto.getDescricao());
		estrutura.setInt(	3, objeto.getQuantidade());
		estrutura.setInt(	4, objeto.getCodigo());
		
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
	}
	
	public void excluir(int codigo)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_MATERIA_PRIMA WHERE CD_MATERIA_PRIMA = ?"
		);
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}
	
	public MateriaPrima consultar(int codigo)throws Exception{
		MateriaPrima materiaPrima = null;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_MATERIA_PRIMA WHERE CD_MATERIA_PRIMA = ?"
		);
		
		estrutura.setInt(1, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			materiaPrima = new MateriaPrima();
			materiaPrima.setCodigo(resultado.getInt("CD_MATERIA_PRIMA"));
			Cor cor = new Cor();
			cor.setCodigo(resultado.getInt("CD_MATERIA_PRIMA"));
			materiaPrima.setCor(cor);
			materiaPrima.setDescricao(resultado.getString("DS_MATERIA_PRIMA"));
			materiaPrima.setQuantidade(resultado.getInt("QTD_TOTAL"));
		}
		
		estrutura.close();
		fechar();
		return materiaPrima;
	}

}
