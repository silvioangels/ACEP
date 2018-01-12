package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Cor;
import br.com.fiap.beans.Produto;
import br.com.fiap.conexao.ConexaoFactory;

public class ProdutoDAO {
	
	private Connection con ;
	
	public ProdutoDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
	public void inserir(Produto objeto)throws Exception{
		
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_PRODUTO (CD_PRODUTO, CD_COR, DS_PRODUTO, DS_COMPLETA_PRODUTO, VL_PRECO_UNITARIO, QTD_PERC_DESPERDICIO, QTD_PERC_AVARIA) VALUES (?,?,?,?,?,?,?)"
		);
		
		estrutura.setInt(	 1, objeto.getCodigo());
		estrutura.setInt(	 2, objeto.getCor().getCodigo());
		estrutura.setString( 3, objeto.getDescricao());
		estrutura.setString( 4, objeto.getDescricaoCompleta());
		estrutura.setDouble( 5, objeto.getValorUnitario());
		estrutura.setInt(	 6, objeto.getPercentualDesperdicio());
		estrutura.setInt(	 7, objeto.getPercentualAvaria());
		
		estrutura.execute();
		estrutura.close();
		fechar();
		
	}
	
	public void alterar(Produto objeto)throws Exception{
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_PRODUTO SET CD_COR = ?, DS_PRODUTO = ?, DS_COMPLETA_PRODUTO = ?, VL_PRECO_UNITARIO = ?, QTD_PERC_DESPERDICIO = ?, QTD_PERC_AVARIA = ? WHERE CD_PRODUTO = ?"
		);
		
		estrutura.setInt(	 1, objeto.getCor().getCodigo());
		estrutura.setString( 2, objeto.getDescricao());
		estrutura.setString( 3, objeto.getDescricaoCompleta());
		estrutura.setDouble( 4, objeto.getValorUnitario());
		estrutura.setInt(	 5, objeto.getPercentualDesperdicio());
		estrutura.setInt(	 6, objeto.getPercentualAvaria());
		estrutura.setInt(	 7, objeto.getCodigo());
		
		
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
	}
	
	public void excluir(int codigo)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_PRODUTO WHERE CD_PRODUTO = ?"
		);
		
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}
	
	public Produto consultar(int codigo)throws Exception{
		Produto produto = null;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_PRODUTO WHERE CD_PRODUTO = ?"
		);
		
		estrutura.setInt(1, codigo);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			produto = new Produto();
			produto.setCodigo(resultado.getInt("CD_PRODUTO"));
			Cor cor = new Cor();
			cor.setCodigo(resultado.getInt("CD_COR"));
			produto.setCor(cor);
			produto.setDescricao(resultado.getString("DS_PRODUTO"));
			produto.setDescricaoCompleta(resultado.getString("DS_COMPLETA_PRODUTO"));
			produto.setValorUnitario(resultado.getDouble("VL_PRECO_UNITARIO"));
			produto.setPercentualDesperdicio(resultado.getInt("QTD_PERC_DESPERDICIO"));
			produto.setPercentualAvaria(resultado.getInt("QTD_PERC_AVARIA"));
			
		}
		
		estrutura.close();
		fechar();
		return produto;
	}
	
	public List<Produto> recuperarProdutos()throws Exception{
		
		List<Produto> listaProdutos = new ArrayList<Produto>();
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_PRODUTO ORDER BY CD_PRODUTO"
		);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			
			Produto produto = new Produto();
			produto.setCodigo(resultado.getInt("CD_PRODUTO"));
			Cor cor = new Cor();
			cor.setCodigo(resultado.getInt("CD_COR"));
			produto.setCor(cor);
			produto.setDescricao(resultado.getString("DS_PRODUTO"));
			produto.setDescricaoCompleta(resultado.getString("DS_COMPLETA_PRODUTO"));
			produto.setValorUnitario(resultado.getDouble("VL_PRECO_UNITARIO"));
			produto.setPercentualDesperdicio(resultado.getInt("QTD_PERC_DESPERDICIO"));
			produto.setPercentualAvaria(resultado.getInt("QTD_PERC_AVARIA"));
			listaProdutos.add(produto);
			
		}
		
		estrutura.close();
		fechar();
		return listaProdutos;
	}

	public List<Produto> consultarPorDescricao(String descricao) throws Exception {

		List<Produto> listaProdutos = new ArrayList<Produto>();
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_PRODUTO where UPPER(DS_PRODUTO) LIKE UPPER('%"+descricao+"%') ORDER BY CD_PRODUTO"
		);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			
			Produto produto = new Produto();
			produto.setCodigo(resultado.getInt("CD_PRODUTO"));
			Cor cor = new Cor();
			cor.setCodigo(resultado.getInt("CD_COR"));
			produto.setCor(cor);
			produto.setDescricao(resultado.getString("DS_PRODUTO"));
			produto.setDescricaoCompleta(resultado.getString("DS_COMPLETA_PRODUTO"));
			produto.setValorUnitario(resultado.getDouble("VL_PRECO_UNITARIO"));
			produto.setPercentualDesperdicio(resultado.getInt("QTD_PERC_DESPERDICIO"));
			produto.setPercentualAvaria(resultado.getInt("QTD_PERC_AVARIA"));
			listaProdutos.add(produto);
			
		}
		
		estrutura.close();
		fechar();
		return listaProdutos;
		
	}

}
