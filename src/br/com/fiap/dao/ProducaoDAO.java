package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Cliente;
import br.com.fiap.beans.Equipamento;
import br.com.fiap.beans.PedidoProducao;
import br.com.fiap.beans.PedidoVenda;
import br.com.fiap.beans.Producao;
import br.com.fiap.beans.Produto;
import br.com.fiap.beans.StatusPedido;
import br.com.fiap.conexao.ConexaoFactory;

public class ProducaoDAO {
	
	private Connection con ;
	
	public ProducaoDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
	public void inserir(Producao objeto)throws Exception{
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_PRODUCAO (CD_PRODUCAO, CD_PRODUTO, NM_PRODUCAO, NR_TEMPO_MEDIO, QT_DESPERDICIO_COR, QT_AVARIA) VALUES (?,?,?,?,?,?)"
		);
		
		estrutura.setInt(	 1, objeto.getCodigo());
		estrutura.setInt(	 2, objeto.getProduto().getCodigo());
		estrutura.setString( 3, objeto.getNome());
		estrutura.setInt(	 4, objeto.getTempoMedio());
		estrutura.setDouble( 5, objeto.getQtdDesperdicioCor());
		estrutura.setDouble( 6, objeto.getQtdAvaria());
		
		estrutura.execute();
		estrutura.close();
		fechar();
	}
	
	public void alterar(Producao objeto)throws Exception{
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_PRODUCAO SET CD_PRODUTO = ?, NM_PRODUCAO = ?, NR_TEMPO_MEDIO = ?, QT_DESPERDICIO_COR = ?, QT_AVARIA = ? WHERE CD_PRODUCAO = ?"
		);
		
		estrutura.setInt(	 1, objeto.getProduto().getCodigo());
		estrutura.setString( 2, objeto.getNome());
		estrutura.setInt(	 3, objeto.getTempoMedio());
		estrutura.setDouble( 4, objeto.getQtdDesperdicioCor());
		estrutura.setDouble( 5, objeto.getQtdAvaria());
		estrutura.setInt(	 6, objeto.getCodigo());
		
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
	}
	
	public void excluir(int codigo)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_PRODUCAO WHERE CD_PRODUCAO = ?"
		);
		
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}
	
	public Producao consultar(int codigo) throws Exception{
		Producao producao = null;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_PRODUCAO WHERE CD_PRODUCAO = ?"
		);
		
		estrutura.setInt(1, codigo);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			producao = new Producao();
			producao.setCodigo(resultado.getInt("CD_PRODUCAO"));
			Produto produto = new Produto();
			produto.setCodigo(resultado.getInt("CD_PRODUTO"));
			producao.setProduto(produto);
			producao.setNome(resultado.getString("NM_PRODUCAO"));
			producao.setTempoMedio(resultado.getInt("NR_TEMPO_MEDIO"));
			producao.setQtdDesperdicioCor(resultado.getDouble("QT_DESPERDICIO_COR"));
			producao.setQtdAvaria(resultado.getInt("QT_AVARIA"));
			
		}
		
		estrutura.close();
		fechar();
		return producao;
	}
	
	public List<PedidoVenda> consultarOrdensServico() throws Exception{
		
		List<PedidoVenda> listaPedidoVenda = new ArrayList<PedidoVenda>();
		
		PreparedStatement estrutura = con.prepareStatement(
				"SELECT  CD_PEDIDO_PRODUCAO, EP.CD_EQUIPAMENTO, CD_PRODUCAO, P.NM_PRODUCAO, P.NR_TEMPO_MEDIO, QT_DESPERDICIO_COR, QT_AVARIA"
				+" FROM T_ACEP_PEDIDO_PRODUCAO PP"
				+" INNER JOIN T_ACEP_ITEM_PRODUCAO_PRODUTO IPP USING(CD_PEDIDO_PRODUCAO) "
				+" INNER JOIN T_ACEP_PRODUCAO P USING (CD_PRODUCAO)"
				+" INNER JOIN T_ACEP_EQUIPAMENTO_PRODUCAO EP USING (CD_PRODUCAO)"
				+" ORDER BY CD_PEDIDO_PRODUCAO"
		);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			
			PedidoVenda pv 	 = new PedidoVenda();
			Producao 	prod = new Producao();
			Equipamento eq 	 = new Equipamento();
			
			pv.setCodigo(resultado.getInt("CD_PEDIDO_PRODUCAO"));
			
			eq.setCodigo(resultado.getInt("CD_EQUIPAMENTO"));
			prod.setEquipamento(eq);
			
			prod.setCodigo(resultado.getInt("CD_PRODUCAO"));
			prod.setNome(resultado.getString("NM_PRODUCAO"));
			prod.setTempoMedio(resultado.getInt("NR_TEMPO_MEDIO"));
			prod.setQtdDesperdicioCor(resultado.getInt("QT_DESPERDICIO_COR"));
			prod.setQtdAvaria(resultado.getInt("QT_AVARIA"));
			pv.setProducao(prod);
			listaPedidoVenda.add(pv);
		}
		
		estrutura.close();
		fechar();
		
		return listaPedidoVenda;		
	}
	
	public List<PedidoProducao> buscarRequisicoesProducao() throws Exception{
		
		SimpleDateFormat formatadorDatas = new SimpleDateFormat("dd/MM/yyyy");
		List<PedidoProducao> listaPedidoProducao = new ArrayList<PedidoProducao>();
		
		PreparedStatement estrutura = con.prepareStatement(
				 " SELECT  CD_PEDIDO_PRODUCAO, PP.CD_STATUS_PEDIDO, SP.DS_STATUS, PP.DT_PREVISAO_ENTREGA, PP.QT_PRODUTO, PP.VL_TOTAL, C.DS_NOME"
				+" FROM T_ACEP_PEDIDO_PRODUCAO PP"
				+" INNER JOIN T_ACEP_STATUS_PEDIDO SP ON SP.CD_STATUS_PEDIDO = PP.CD_STATUS_PEDIDO"
				+" INNER JOIN T_ACEP_PEDIDO_VENDA PV USING(CD_PEDIDO_PRODUCAO) "
				+" INNER JOIN T_ACEP_CLIENTE C USING (CD_CLIENTE)"
				+" ORDER BY CD_PEDIDO_PRODUCAO"
		);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			
			PedidoProducao pv = new PedidoProducao();
			pv.setCodigo(resultado.getInt("CD_PEDIDO_PRODUCAO"));
			StatusPedido sp = new StatusPedido();
			sp.setCodigo(resultado.getInt("CD_STATUS_PEDIDO"));
			sp.setDescricao(resultado.getString("DS_STATUS"));
			pv.setSp(sp);
			pv.setPrevisaoEntrega(resultado.getDate("DT_PREVISAO_ENTREGA"));
			pv.setPrevisaoEntregaFormatada(formatadorDatas.format(pv.getPrevisaoEntrega()).toString());
			pv.setQuantidade(resultado.getInt("QT_PRODUTO"));
			pv.setValorTotal(resultado.getInt("VL_TOTAL"));
			Cliente c = new Cliente();
			c.setNome(resultado.getString("DS_NOME"));
			pv.setCliente(c);
			
			listaPedidoProducao.add(pv);
		}
			
		
		estrutura.close();
		fechar();
		
		return listaPedidoProducao;		
	}
	

}
