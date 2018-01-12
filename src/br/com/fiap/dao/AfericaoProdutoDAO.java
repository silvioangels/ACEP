package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Afericao;
import br.com.fiap.beans.AfericaoProduto;
import br.com.fiap.beans.Produto;
import br.com.fiap.conexao.ConexaoFactory;

public class AfericaoProdutoDAO {
	
	private Connection con ;
	
	public AfericaoProdutoDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
	public void inserir(AfericaoProduto objeto)throws Exception{
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_AFERICAO_PRODUTO (CD_AFERICAO_PRODUTO, CD_PRODUTO, CD_AFERICAO, DS_STATUS, NR_NOTA) VALUES (?,?,?,?,?)"
		);
		estrutura.setInt(   1, objeto.getCodigo());
		estrutura.setInt(   2, objeto.getProduto().getCodigo());
		estrutura.setInt(   3, objeto.getAfericao().getCodigo());
		estrutura.setString(4, objeto.getDescricaoStatus());
		estrutura.setInt(   5, objeto.getNota());
		estrutura.execute();
		estrutura.close();
		fechar();
	}
	
	public void alterar(AfericaoProduto objeto)throws Exception{
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_AFERICAO_PRODUTO SET CD_PRODUTO = ?, CD_AFERICAO = ?, DS_STATUS = ?, NR_NOTA = ?	 WHERE CD_AFERICAO_PRODUTO = ?"
		);
		
		estrutura.setInt(   1, objeto.getProduto().getCodigo());
		estrutura.setInt(   2, objeto.getAfericao().getCodigo());
		estrutura.setString(3, objeto.getDescricaoStatus());
		estrutura.setInt(   4, objeto.getNota());
		estrutura.setInt(   5, objeto.getCodigo());
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
		
	}
	
	public void excluir(int codigo)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_AFERICAO_PRODUTO WHERE CD_AFERICAO_PRODUTO = ?"
		);
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}
	
	public AfericaoProduto consultar(int codigo) throws Exception{
		AfericaoProduto ap = null;
		
		PreparedStatement estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_AFERICAO_PRODUTO WHERE CD_AFERICAO_PRODUTO = ?"
		);
		
		estrutura.setInt(1, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			ap = new AfericaoProduto();
			ap.setCodigo(resultado.getInt("CD_AFERICAO_PRODUTO"));
			
			Produto p = new Produto();
			p.setCodigo(resultado.getInt("CD_PRODUTO"));
			ap.setProduto(p);
			
			Afericao af = new Afericao();
			af.setCodigo(resultado.getInt("CD_AFERICAO"));
			ap.setAfericao(af);
			
			ap.setDescricaoStatus(resultado.getString("DS_STATUS"));
			ap.setNota(resultado.getInt("NR_NOTA"));
			
		}
		
		estrutura.close();
		fechar();
		return ap;
	}
	
	public List<AfericaoProduto> consultarQualidadeProdutos() throws Exception {
		
		List<AfericaoProduto> listaQualidadeProdutos = new ArrayList<AfericaoProduto>();
		
		PreparedStatement estrutura = con.prepareStatement(
				  "SELECT  AP.CD_AFERICAO_PRODUTO, CD_PRODUTO, P.DS_PRODUTO, CD_AFERICAO, A.DS_AFERICAO, AP.DS_STATUS, AP.NR_NOTA"
				+ " FROM    T_ACEP_AFERICAO_PRODUTO AP"
				+ " INNER JOIN T_ACEP_PRODUTO P USING (CD_PRODUTO)"
				+ " INNER JOIN T_ACEP_AFERICAO A USING (CD_AFERICAO)"
				+ " ORDER BY AP.CD_AFERICAO_PRODUTO"
		);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {

			AfericaoProduto ap = new AfericaoProduto();
			ap.setCodigo(resultado.getInt("CD_AFERICAO_PRODUTO"));
			
			Produto p = new Produto();
			p.setCodigo(resultado.getInt("CD_PRODUTO"));
			p.setDescricao(resultado.getString("DS_PRODUTO"));
			ap.setProduto(p);
			
			Afericao af = new Afericao();
			af.setCodigo(resultado.getInt("CD_AFERICAO"));
			af.setDescricao(resultado.getString("DS_AFERICAO"));
			ap.setAfericao(af);
			
			ap.setDescricaoStatus(resultado.getString("DS_STATUS"));
			ap.setNota(resultado.getInt("NR_NOTA"));
			
			listaQualidadeProdutos.add(ap);
		}
		
		estrutura.close();
		fechar();
		return listaQualidadeProdutos;
	}


}
