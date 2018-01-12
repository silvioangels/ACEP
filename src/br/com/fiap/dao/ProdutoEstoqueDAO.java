package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.beans.Estoque;
import br.com.fiap.beans.Produto;
import br.com.fiap.beans.ProdutoEstoque;
import br.com.fiap.conexao.ConexaoFactory;

public class ProdutoEstoqueDAO {
	
	private Connection con ;
	
	public ProdutoEstoqueDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
	public void inserir(ProdutoEstoque objeto)throws Exception{
		
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_PRODUTO_ESTOQUE (CD_PRODUTO_ESTOQUE, CD_ESTOQUE, CD_PRODUTO, DT_VENCIMENTO, DT_RECEBIMENTO) VALUES (?,?,?,?,?)"
		);
		
		estrutura.setInt(	 1, objeto.getCodigo());
		estrutura.setInt(	 2, objeto.getEstoque().getCodigo());
		estrutura.setInt(	 3, objeto.getProduto().getCodigo());
		estrutura.setDate(	 4, new java.sql.Date(objeto.getDataVencimento().getTime()));
		estrutura.setDate(	 5, new java.sql.Date(objeto.getDataRecebimento().getTime()));
		
		estrutura.execute();
		estrutura.close();
		fechar();
		
	}
	
	public void alterar(ProdutoEstoque objeto)throws Exception{
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_PRODUTO_ESTOQUE SET CD_ESTOQUE = ?, CD_PRODUTO	 = ?, DT_VENCIMENTO = ?, DT_RECEBIMENTO = ? WHERE CD_PRODUTO_ESTOQUE = ?"
		);
		
		estrutura.setInt(	 1, objeto.getEstoque().getCodigo());
		estrutura.setInt(	 2, objeto.getProduto().getCodigo());
		estrutura.setDate(	 3, new java.sql.Date(objeto.getDataVencimento().getTime()));
		estrutura.setDate(	 4, new java.sql.Date(objeto.getDataRecebimento().getTime()));
		estrutura.setInt(	 5, objeto.getCodigo());
		
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
	}
	
	public void excluir(int codigo)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_PRODUTO_ESTOQUE WHERE CD_PRODUTO_ESTOQUE = ?"
		);
		
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}
	
	public ProdutoEstoque consultar(int codigo)throws Exception{
		ProdutoEstoque produtoEstoque = null;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_PRODUTO_ESTOQUE WHERE CD_PRODUTO_ESTOQUE = ?"
		);
		
		estrutura.setInt(1, codigo);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			produtoEstoque = new ProdutoEstoque();
			produtoEstoque.setCodigo(resultado.getInt("CD_PRODUTO_ESTOQUE"));
			Estoque estoque = new Estoque();
			estoque.setCodigo(resultado.getInt("CD_ESTOQUE"));
			produtoEstoque.setEstoque(estoque);
			Produto produto = new Produto();
			produto.setCodigo(resultado.getInt("CD_PRODUTO"));
			produtoEstoque.setProduto(produto);
			produtoEstoque.setDataVencimento(resultado.getDate("DT_VENCIMENTO"));
			produtoEstoque.setDataRecebimento(resultado.getDate("DT_RECEBIMENTO"));
			
			
		}
		
		estrutura.close();
		fechar();
		return produtoEstoque;
	}

	public int recuperarProximoRegistro() throws Exception {
		
		int proxRegistro = 0;

		PreparedStatement estrutura = con.prepareStatement(
				"SELECT MAX(CD_PRODUTO_ESTOQUE)+1 AS PROX_REG FROM T_ACEP_PRODUTO_ESTOQUE"
		);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			proxRegistro = resultado.getInt("PROX_REG");
		}
		
		return proxRegistro;
	}

	public void excluir(int codigoEstoque, int codigoProduto, int quantidade) throws Exception {
		PreparedStatement estrutura = con.prepareStatement(
				  " DELETE FROM T_ACEP_PRODUTO_ESTOQUE"
				+ " WHERE   CD_PRODUTO_ESTOQUE IN "
				+ " ("
				+ "   SELECT  CD_PRODUTO_ESTOQUE FROM ("
				+ "      SELECT  *"
				+ "      FROM    T_ACEP_PRODUTO_ESTOQUE"
				+ "      WHERE   CD_ESTOQUE = ?"
				+ "      AND     CD_PRODUTO = ?"
				+ "      ORDER BY CD_PRODUTO_ESTOQUE DESC"
				+ "   ) WHERE   ROWNUM  <= ?"
				+ " )"
		);
		
		estrutura.setInt(1, codigoEstoque);
		estrutura.setInt(2, codigoProduto);
		estrutura.setInt(3, quantidade);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}

}
