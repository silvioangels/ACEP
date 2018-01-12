package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.beans.Estoque;
import br.com.fiap.beans.MateriaPrima;
import br.com.fiap.beans.MateriaPrimaEstoque;
import br.com.fiap.conexao.ConexaoFactory;

public class MateriaPrimaEstoqueDAO {
	
	private Connection con ;
	
	public MateriaPrimaEstoqueDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
	public void inserir(MateriaPrimaEstoque objeto)throws Exception{
		
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_MATERIA_PRIMA_ESTOQUE (CD_MATERIA_PRIMA_ESTOQUE, CD_MATERIA_PRIMA, CD_ESTOQUE, DT_VENCIMENTO, DT_RECEBIMENTO, IN_BAIXA) VALUES (?,?,?,?,?,?)"
		);
		
		estrutura.setInt(	1, objeto.getCodigo());
		estrutura.setInt(	2, objeto.getMateriaPrima().getCodigo());
		estrutura.setInt(	3, objeto.getEstoque().getCodigo());
		estrutura.setDate(	4, new java.sql.Date(objeto.getDataVencimento().getTime()));
		estrutura.setDate(	5, new java.sql.Date(objeto.getDataRecebimento().getTime()));
		estrutura.setString(6, String.valueOf(objeto.getIndicadorBaixa()));
		
		estrutura.execute();
		estrutura.close();
		fechar();
		
	}
	
	public void alterar(MateriaPrimaEstoque objeto)throws Exception{
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_MATERIA_PRIMA_ESTOQUE SET CD_MATERIA_PRIMA = ?, CD_ESTOQUE = ?, DT_VENCIMENTO = ?, DT_RECEBIMENTO = ?, IN_BAIXA = ? WHERE CD_MATERIA_PRIMA_ESTOQUE = ?"
		);
		
		estrutura.setInt(	1, objeto.getMateriaPrima().getCodigo());
		estrutura.setInt(	2, objeto.getEstoque().getCodigo());
		estrutura.setDate(	3, new java.sql.Date(objeto.getDataVencimento().getTime()));
		estrutura.setDate(	4, new java.sql.Date(objeto.getDataRecebimento().getTime()));
		estrutura.setString(5, String.valueOf(objeto.getIndicadorBaixa()));
		estrutura.setInt(	6, objeto.getCodigo());
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
	}
	
	public void excluir(int codigo)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_MATERIA_PRIMA_ESTOQUE WHERE CD_MATERIA_PRIMA_ESTOQUE = ?"
		);
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}
	
	public MateriaPrimaEstoque consultar(int codigo)throws Exception{
		MateriaPrimaEstoque materiaPrimaEstoque = null;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_MATERIA_PRIMA_ESTOQUE WHERE CD_MATERIA_PRIMA_ESTOQUE = ?"
		);
		
		estrutura.setInt(1, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			materiaPrimaEstoque = new MateriaPrimaEstoque();
			materiaPrimaEstoque.setCodigo(resultado.getInt("CD_MATERIA_PRIMA_ESTOQUE"));
			
			MateriaPrima materiaPrima = new MateriaPrima();
			materiaPrima.setCodigo(resultado.getInt("CD_MATERIA_PRIMA"));
			materiaPrimaEstoque.setMateriaPrima(materiaPrima);
			
			Estoque estoque = new Estoque();
			estoque.setCodigo(resultado.getInt("CD_ESTOQUE"));
			materiaPrimaEstoque.setEstoque(estoque);
			
			materiaPrimaEstoque.setDataVencimento(resultado.getDate("DT_VENCIMENTO"));
			materiaPrimaEstoque.setDataRecebimento(resultado.getDate("DT_RECEBIMENTO"));
			materiaPrimaEstoque.setIndicadorBaixa(resultado.getString("IN_BAIXA").charAt(0));
			
			
		}
		
		estrutura.close();
		fechar();
		return materiaPrimaEstoque;
	}

}
