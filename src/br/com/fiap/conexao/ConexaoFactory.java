package br.com.fiap.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

import properties.PropertiesFactory;
import weka.experiment.InstanceQuery;

public class ConexaoFactory {
	
	PropertiesFactory props;
	String db_drive;
	String db_url  ;
	String db_user ;
	String db_pass ;
	
	public ConexaoFactory() throws Exception{
		props 	 = new PropertiesFactory();
		db_drive = props.recuperarPropriedadeBanco("drive");
		db_url   = props.recuperarPropriedadeBanco("url");
		db_user  = props.recuperarPropriedadeBanco("user");
		db_pass  = props.recuperarPropriedadeBanco("pass");
		
	}
	
	public Connection getBancoConnection() throws Exception{
		Class.forName(db_drive);
		return DriverManager.getConnection(db_url,db_user , db_pass);
		
	}
	
	public InstanceQuery getWekaInstanceOracleConnection() throws Exception{
		
		InstanceQuery instTrainQuery = new InstanceQuery();
		instTrainQuery.setDatabaseURL(db_url);
		instTrainQuery.setUsername(db_user);
		instTrainQuery.setPassword(db_pass);
		return instTrainQuery;
		
	}
	
}
