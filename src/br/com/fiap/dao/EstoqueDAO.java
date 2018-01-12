package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Armazem;
import br.com.fiap.beans.Estoque;
import br.com.fiap.conexao.ConexaoFactory;

public class EstoqueDAO {
	
	private Connection con ;
	
	public EstoqueDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
	public void inserir(Estoque objeto)throws Exception{
		
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_ESTOQUE (CD_ESTOQUE, CD_ARMAZEM, NR_CONTROLE_DESPERDICIO, QT_SALDO_FISICO_ARMAZEM, QT_SALDO_FISICO_MOV_ESTOQUE, QT_SALDO_FISICO_CENTRAL) VALUES (?,?,?,?,?,?)"
		);
		
		estrutura.setInt(		1, objeto.getCodigo());
		estrutura.setInt(		2, objeto.getArmazem().getCodigo());
		estrutura.setInt(		3, objeto.getNrControleDesperdicio());
		estrutura.setDouble( 	4, objeto.getSaldoFisicoArmazem());
		estrutura.setDouble( 	5, objeto.getSaldoFisicoMovimentacaoEstoque());
		estrutura.setDouble( 	6, objeto.getSaldoFisicoCentral());
		
		estrutura.execute();
		estrutura.close();
		fechar();
		
	}
	
	public void alterar(Estoque objeto)throws Exception{
		
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_ESTOQUE SET CD_ARMAZEM = ?, NR_CONTROLE_DESPERDICIO = ?, QT_SALDO_FISICO_ARMAZEM = ?, QT_SALDO_FISICO_MOV_ESTOQUE = ?, QT_SALDO_FISICO_CENTRAL = ? WHERE CD_ESTOQUE = ?"
		);
		
		estrutura.setInt(		1, objeto.getArmazem().getCodigo());
		estrutura.setInt(		2, objeto.getNrControleDesperdicio());
		estrutura.setDouble( 	3, objeto.getSaldoFisicoArmazem());
		estrutura.setDouble( 	4, objeto.getSaldoFisicoMovimentacaoEstoque());
		estrutura.setDouble( 	5, objeto.getSaldoFisicoCentral());
		estrutura.setInt(		6, objeto.getCodigo());
		
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
		
	}
	
	public void excluir(int codigo)throws Exception{
		
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_ESTOQUE WHERE CD_ESTOQUE = ?"
		);
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
		
	}
	
	public Estoque consultar(int codigo) throws Exception{
		Estoque estoque = null;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_ESTOQUE WHERE CD_ESTOQUE = ?"
		);
		
		estrutura.setInt(1, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			estoque = new Estoque();
			estoque.setCodigo(resultado.getInt("CD_ESTOQUE"));
			Armazem armazem = new Armazem();
			armazem.setCodigo(resultado.getInt("CD_ARMAZEM"));
			estoque.setArmazem(armazem);
			
			estoque.setNrControleDesperdicio(resultado.getInt("NR_CONTROLE_DESPERDICIO"));
			estoque.setSaldoFisicoArmazem(resultado.getDouble("QT_SALDO_FISICO_ARMAZEM"));
			estoque.setSaldoFisicoMovimentacaoEstoque(resultado.getDouble("QT_SALDO_FISICO_MOV_ESTOQUE"));
			estoque.setSaldoFisicoCentral(resultado.getDouble("QT_SALDO_FISICO_CENTRAL"));
		}
		
		estrutura.close();
		fechar();
		return estoque;
	}

	public int consultarPercentualMateriaPrimaDisponivelEstoque(int codigo) throws Exception{
		PreparedStatement estrutura = null;
		int percentualMateriaPrima = 0;
		
		estrutura = con.prepareStatement(
				"SELECT ((SELECT COUNT(*) FROM T_ACEP_MATERIA_PRIMA_ESTOQUE WHERE CD_MATERIA_PRIMA = ? AND IN_BAIXA = 'N')*100)/( SELECT COUNT(*) FROM T_ACEP_MATERIA_PRIMA_ESTOQUE WHERE CD_MATERIA_PRIMA = ?) AS QTD_PERC FROM DUAL"
		);
		
		estrutura.setInt(1, codigo);
		estrutura.setInt(2, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			percentualMateriaPrima = resultado.getInt("QTD_PERC"); 
		}
		
		estrutura.close();
		fechar();
		return percentualMateriaPrima;
	}
	
	public int consultarPercentualMateriaPrimaDisponivelPorEstoque(int codigoEstoque, int codigoMateriaPrima) throws Exception{
		PreparedStatement estrutura = null;
		int percentualMateriaPrima = 0;
		
		estrutura = con.prepareStatement(
				"SELECT ((SELECT COUNT(*) FROM T_ACEP_MATERIA_PRIMA_ESTOQUE WHERE CD_MATERIA_PRIMA = ? AND CD_ESTOQUE = ? AND IN_BAIXA = 'N')*100)/( SELECT COUNT(*) FROM T_ACEP_MATERIA_PRIMA_ESTOQUE WHERE CD_MATERIA_PRIMA = ? AND CD_ESTOQUE = ?) AS QTD_PERC FROM DUAL"
		);
		
		estrutura.setInt(1, codigoMateriaPrima);
		estrutura.setInt(2, codigoEstoque);
		estrutura.setInt(3, codigoMateriaPrima);
		estrutura.setInt(4, codigoEstoque);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			percentualMateriaPrima = resultado.getInt("QTD_PERC"); 
		}
		
		estrutura.close();
		fechar();
		return percentualMateriaPrima;
	}
	
	public int consultarPercentualMateriaPrimaConsumidaEstoque(int codigo) throws Exception{
		PreparedStatement estrutura = null;
		int percentualMateriaPrima = 0;
		
		estrutura = con.prepareStatement(
				"SELECT ((SELECT COUNT(*) FROM T_ACEP_MATERIA_PRIMA_ESTOQUE WHERE CD_MATERIA_PRIMA = ? AND IN_BAIXA = 'S')*100)/( SELECT COUNT(*) FROM T_ACEP_MATERIA_PRIMA_ESTOQUE WHERE CD_MATERIA_PRIMA = ?) AS QTD_PERC FROM DUAL"
		);
		
		estrutura.setInt(1, codigo);
		estrutura.setInt(2, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			percentualMateriaPrima = resultado.getInt("QTD_PERC"); 
		}
		
		estrutura.close();
		fechar();
		return percentualMateriaPrima;
	}
	
	public int recuperarQuantidadeProdutosEstoque(int nrEstoque, int produto) throws Exception{
		
		PreparedStatement estrutura = null;
		int qtdProduto = 0;
		
		estrutura = con.prepareStatement(
				"SELECT  COUNT(*) AS QTD_PROD FROM T_ACEP_PRODUTO_ESTOQUE WHERE CD_ESTOQUE = ? AND CD_PRODUTO = ?"
		);
		
		estrutura.setInt(1, nrEstoque);
		estrutura.setInt(2, produto);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			qtdProduto = resultado.getInt("QTD_PROD"); 
		}
		
		estrutura.close();
		fechar();
		
		
		return qtdProduto;
	}

	public List<Integer> consultarNumeroEstoquePeloArmazem(int codigoArmazem) throws Exception {
		List<Integer> numerosArmazens = new ArrayList<Integer>(); 
		
		PreparedStatement estrutura = con.prepareStatement(
				"SELECT CD_ESTOQUE FROM T_ACEP_ESTOQUE WHERE CD_ARMAZEM = ?"
		);
		
		estrutura.setInt(1, codigoArmazem);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			numerosArmazens.add(resultado.getInt("CD_ESTOQUE")); 
		}
		
		estrutura.close();
		fechar();
		
		return numerosArmazens;
	}

}
