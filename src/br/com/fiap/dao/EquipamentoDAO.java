package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Equipamento;
import br.com.fiap.conexao.ConexaoFactory;

public class EquipamentoDAO {
	
	private Connection con ;
	
	public EquipamentoDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
	public void inserir(Equipamento objeto)throws Exception{
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_EQUIPAMENTO (CD_EQUIPAMENTO, DESC_EQUIPAMENTO) VALUES (?,?)"
		);
		estrutura.setInt(	1, objeto.getCodigo());
		estrutura.setString(2, objeto.getDescricao());
		estrutura.execute();
		estrutura.close();
		fechar();
	}
	
	public void alterar(Equipamento objeto)throws Exception{
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_EQUIPAMENTO SET DESC_EQUIPAMENTO = ? WHERE CD_EQUIPAMENTO = ?"
		);
		
		estrutura.setString(	1, objeto.getDescricao());
		estrutura.setInt(		2, objeto.getCodigo());
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
		
	}
	
	public void excluir(int codigo)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_EQUIPAMENTO WHERE CD_EQUIPAMENTO = ?"
		);
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}
	
	public Equipamento consultar(int codigo) throws Exception{
		Equipamento cor = null;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_EQUIPAMENTO WHERE CD_EQUIPAMENTO = ?"
		);
		
		estrutura.setInt(1, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			cor = new Equipamento();
			cor.setCodigo(resultado.getInt("CD_EQUIPAMENTO"));
			cor.setDescricao(resultado.getString("DESC_EQUIPAMENTO"));
		}
		
		estrutura.close();
		fechar();
		return cor;
	}

	public List<Equipamento> consultarTodosEquipamentos() throws Exception {
		
		List<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();
		
		PreparedStatement estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_EQUIPAMENTO ORDER BY CD_EQUIPAMENTO"
		);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			Equipamento eq = new Equipamento();
			eq.setCodigo(resultado.getInt("CD_EQUIPAMENTO"));
			eq.setDescricao(resultado.getString("DESC_EQUIPAMENTO"));
			listaEquipamentos.add(eq);
		}
		
		estrutura.close();
		fechar();
		return listaEquipamentos;
	}

	public int recuperarProximoRegistro() throws Exception {

		int proxRegistro = 0;

		PreparedStatement estrutura = con.prepareStatement(
				"SELECT MAX(CD_EQUIPAMENTO)+1 AS PROX_REG FROM T_ACEP_EQUIPAMENTO"
		);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			proxRegistro = resultado.getInt("PROX_REG");
		}
		
		return proxRegistro;
		
	}

}
