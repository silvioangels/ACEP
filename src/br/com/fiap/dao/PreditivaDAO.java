package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.beans.Preditiva;
import br.com.fiap.conexao.ConexaoFactory;

public class PreditivaDAO {
	
	private Connection con ;
	
	public PreditivaDAO() throws Exception {
		con = new ConexaoFactory().getBancoConnection();		
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
	public void inserir(Preditiva objeto)throws Exception{
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement(
			"INSERT INTO T_ACEP_PREDITIVA (COD_PREDITIVA, CD_COR, CD_EQUIPAMENTO, CD_PRODUCAO, CD_ARMAZEM, CD_MATERIA_PRIMA, CD_STATUS_PEDIDO, QT_DESPERDICIO, QT_AVARIA, QT_CONSUMIDA, DT_INCLUSAO) VALUES (?,?,?,?,?,?,?,?,?,?,?)"
		);
		estrutura.setInt(	1, objeto.getCodigo());
		estrutura.setInt(	2, objeto.getCodigo());
		estrutura.setInt(	3, objeto.getCodigo());
		estrutura.setInt(	4, objeto.getCodigo());
		estrutura.setInt(	5, objeto.getCodigo());
		estrutura.setInt(	6, objeto.getCodigo());
		estrutura.setInt(	7, objeto.getCodigo());
		estrutura.setInt(	8, objeto.getCodigo());
		estrutura.setInt(	9, objeto.getCodigo());
		estrutura.setInt(	10, objeto.getCodigo());
		estrutura.setDate(	11, new java.sql.Date(objeto.getDataInclusao().getTime()));
		estrutura.execute();
		estrutura.close();
		fechar();
	}
	
	public void alterar(Preditiva objeto)throws Exception{
		PreparedStatement estrutura = con.prepareStatement (
				"UPDATE T_ACEP_PREDITIVA SET DESC_COR = ?, CD_COR = ?, CD_EQUIPAMENTO = ?, CD_PRODUCAO = ?, CD_ARMAZEM = ?, CD_MATERIA_PRIMA = ?, CD_STATUS_PEDIDO = ?, QT_DESPERDICIO = ?, QT_AVARIA = ?, QT_CONSUMIDA = ?, DT_INCLUSAO = ? WHERE COD_PREDITIVA = ?"
		);
		
		estrutura.setInt(	1, objeto.getCodigo());
		estrutura.setInt(	2, objeto.getCodigo());
		estrutura.setInt(	3, objeto.getCodigo());
		estrutura.setInt(	4, objeto.getCodigo());
		estrutura.setInt(	5, objeto.getCodigo());
		estrutura.setInt(	6, objeto.getCodigo());
		estrutura.setInt(	7, objeto.getCodigo());
		estrutura.setInt(	8, objeto.getCodigo());
		estrutura.setInt(	9, objeto.getCodigo());
		estrutura.setDate(	10, new java.sql.Date(objeto.getDataInclusao().getTime()));
		estrutura.setInt(	11, objeto.getCodigo());
		estrutura.executeUpdate();
		estrutura.close();
		fechar();
		
	}
	
	public void excluir(int codigo)throws Exception{
		PreparedStatement estrutura = con.prepareStatement(
				"DELETE FROM T_ACEP_PREDITIVA WHERE COD_PREDITIVA = ?"
		);
		estrutura.setInt(1, codigo);
		
		estrutura.executeUpdate();
		estrutura.close();	
		fechar();
	}
	
	public Preditiva consultar(int codigo) throws Exception{
		Preditiva preditiva = null;
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_PREDITIVA WHERE COD_PREDITIVA = ?"
		);
		
		estrutura.setInt(1, codigo);
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			preditiva = new Preditiva();
			preditiva.setCodigo(resultado.getInt("COD_PREDITIVA"));
		}
		
		estrutura.close();
		fechar();
		return preditiva;
	}

	public boolean validarAnalisePreditivaMes(String mes) throws Exception {
		
		boolean validarAnalisePreditivaMes = true;
		
		
		PreparedStatement estrutura = null;
		
		estrutura = con.prepareStatement(
				"SELECT * FROM T_ACEP_PREDITIVA WHERE TO_CHAR(DT_INCLUSAO,'MM/YYYY') <= '"+mes+"/2017'"
		);
		
		ResultSet resultado = estrutura.executeQuery();
		
		while (resultado.next()) {
			validarAnalisePreditivaMes = false;
		}
		
		estrutura.close();
		fechar();
		
		return validarAnalisePreditivaMes;
	}
	
	public String recuperarQueryRealizarAnalisePreditivaConsumoMateriaPrima(String mes){
		return 	  " SELECT  PRED.COD_PREDITIVA, PRED.CD_MATERIA_PRIMA, PRED.QT_DESPERDICIO, PRED.QT_AVARIA, PRED.QT_CONSUMIDA,"
				+ "    CASE "
				+ "        WHEN PRED.QT_CONSUMIDA BETWEEN 800 AND 1000 THEN 'MUITO ALTO'"
				+ "        WHEN PRED.QT_CONSUMIDA BETWEEN 600 AND 799 THEN 'ALTO'"
				+ "        WHEN PRED.QT_CONSUMIDA BETWEEN 300 AND 599 THEN 'MEDIO'"
				+ "        WHEN PRED.QT_CONSUMIDA BETWEEN 100 AND 299 THEN 'BAIXO'"
				+ "    ELSE 'MUITO BAIXO' "
				+ "   END AS STATUS_CONSUMO "
				+ " FROM    T_ACEP_PREDITIVA PRED"
				+ " WHERE   CD_MATERIA_PRIMA IS NOT NULL"
				+ " AND     QT_DESPERDICIO IS NOT NULL"
				+ " AND     QT_AVARIA IS NOT NULL"
				+ " AND     QT_CONSUMIDA IS NOT NULL"
				+ " AND     TO_CHAR(DT_INCLUSAO, 'MM/YYYY') <= '"+mes+"/2017'";
	}

	public String recuperarQueryRealizarAnalisePreditivaDesperdicioMateriaPrima(String mes) {
		return 	  " SELECT  PRED.COD_PREDITIVA, PRED.CD_MATERIA_PRIMA, PRED.QT_DESPERDICIO, PRED.QT_AVARIA, PRED.QT_CONSUMIDA,"
				+ "    CASE "
				+ "        WHEN PRED.QT_DESPERDICIO BETWEEN 800 AND 1000 THEN 'MUITO ALTO'"
				+ "        WHEN PRED.QT_DESPERDICIO BETWEEN 600 AND 799  THEN 'ALTO'"
				+ "        WHEN PRED.QT_DESPERDICIO BETWEEN 300 AND 599  THEN 'MEDIO'"
				+ "        WHEN PRED.QT_DESPERDICIO BETWEEN 100 AND 299  THEN 'BAIXO'"
				+ "    ELSE 'MUITO BAIXO' "
				+ "   END AS STATUS_CONSUMO "
				+ " FROM    T_ACEP_PREDITIVA PRED"
				+ " WHERE   CD_MATERIA_PRIMA IS NOT NULL"
				+ " AND     QT_DESPERDICIO IS NOT NULL"
				+ " AND     QT_AVARIA IS NOT NULL"
				+ " AND     QT_CONSUMIDA IS NOT NULL"
				+ " AND     TO_CHAR(DT_INCLUSAO, 'MM/YYYY') <= '"+mes+"/2017'";
	}

}
