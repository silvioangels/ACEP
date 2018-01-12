package br.com.fiap.teste;

import weka.core.Instances;
import weka.experiment.InstanceQuery;

public class TesteWekaOracle {
	
	public static void main(String[] args) throws Exception {
		
		InstanceQuery instTrainQuery = new InstanceQuery();
		instTrainQuery.setDatabaseURL("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL");
		instTrainQuery.setUsername("RM78986");
		instTrainQuery.setPassword("010688");
		Instances trainInsts = instTrainQuery.retrieveInstances("select * from t_acep_preditiva");
		
		System.out.println(trainInsts.toString());
		
	}

}
