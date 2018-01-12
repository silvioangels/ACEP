package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.beans.Estoque;
import br.com.fiap.beans.MovimentoEstoque;
import br.com.fiap.conexao.ConexaoFactory;

public class MovimentoEstoqueDAO {
	
	private Connection con ;
	
	public MovimentoEstoqueDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
	public void inserir(MovimentoEstoque objeto)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_MOVIMENTO_ESTOQUE (CD_MOVIMENTO_ESTOQUE, CD_ESTOQUE, TP_DESLOCAMENTO, VL_CUSTO, VL_TEMPO_ABASTECIMENTO, DT_PRAZO_ENTREGA, VL_PRECO_VENDA_FINAL) VALUES (?,?,?,?,?,?,?)"
		);
		estrutura.setInt(	1, objeto.getCodigo());
		estrutura.setInt(	2, objeto.getEstoque().getCodigo());
		estrutura.setString(3, objeto.getTipoDeslocamento());
		estrutura.setDouble(4, objeto.getValorCusto());
		estrutura.setDouble(5, objeto.getTempoAbastecimento());
		estrutura.setDate(	6, new java.sql.Date(objeto.getPrazoEntrega().getTime()));
		estrutura.setDouble(7, objeto.getPrecoFinal());
		
		estrutura.execute();
		estrutura.close();
		fechar();
	}
	
	public void alterar(MovimentoEstoque objeto)throws Exception{
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_MOVIMENTO_ESTOQUE SET CD_ESTOQUE = ?, TP_DESLOCAMENTO = ?, VL_CUSTO = ?, VL_TEMPO_ABASTECIMENTO = ?, DT_PRAZO_ENTREGA = ? , VL_PRECO_VENDA_FINAL= ? WHERE CD_MOVIMENTO_ESTOQUE = ?"
		);
		
		estrutura.setInt(	1, objeto.getEstoque().getCodigo());
		estrutura.setString(2, objeto.getTipoDeslocamento());
		estrutura.setDouble(3, objeto.getValorCusto());
		estrutura.setDouble(4, objeto.getTempoAbastecimento());
		estrutura.setDate(	5, new java.sql.Date(objeto.getPrazoEntrega().getTime()));
		estrutura.setDouble(6, objeto.getPrecoFinal());
		estrutura.setInt(	7, objeto.getCodigo());
		
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
		
	}
	
	public void excluir(int codigo)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_MOVIMENTO_ESTOQUE WHERE CD_MOVIMENTO_ESTOQUE = ?"
		);
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}
	
	public MovimentoEstoque consultar(int codigo) throws Exception{
		MovimentoEstoque movEstoq = null;
		
		PreparedStatement estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_MOVIMENTO_ESTOQUE WHERE CD_MOVIMENTO_ESTOQUE = ?"
		);
		
		estrutura.setInt(1, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			movEstoq = new MovimentoEstoque();
			movEstoq.setCodigo(resultado.getInt("CD_MOVIMENTO_ESTOQUE"));
			Estoque est = new Estoque();
			est.setCodigo(resultado.getInt("CD_ESTOQUE"));
			movEstoq.setEstoque(est);
			movEstoq.setTipoDeslocamento(resultado.getString("TP_DESLOCAMENTO"));
			movEstoq.setValorCusto(resultado.getDouble("VL_CUSTO"));
			movEstoq.setTempoAbastecimento(resultado.getDouble("VL_TEMPO_ABASTECIMENTO"));
			movEstoq.setPrazoEntrega(resultado.getDate("DT_PRAZO_ENTREGA"));
			movEstoq.setPrecoFinal(resultado.getDouble("VL_PRECO_VENDA_FINAL"));
		}
		
		estrutura.close();
		fechar();
		
		return movEstoq;
	}

	public int recuperarProximoRegistro() throws Exception {
		
		int proxRegistro = 0;

		PreparedStatement estrutura = con.prepareStatement(
				"SELECT MAX(CD_MOVIMENTO_ESTOQUE)+1 AS PROX_REG FROM T_ACEP_MOVIMENTO_ESTOQUE"
		);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			proxRegistro = resultado.getInt("PROX_REG");
		}
		
		return proxRegistro;
		
	}

}
