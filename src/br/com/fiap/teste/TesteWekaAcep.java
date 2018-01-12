package br.com.fiap.teste;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TesteWekaAcep {
	
	public static void main(String[] args) throws Exception {
		
		 DataSource ds= new DataSource("src/br/com/fiap/weka/weka.arff");
	        Instances ins = ds.getDataSet();
	       // System.out.println(ins.toString()); // LE o arquivo weka.arff que e a Base de Dados criada para teste
	        
	        ins.setClassIndex(2);
	        
	        NaiveBayes nb = new NaiveBayes();
	        nb.buildClassifier(ins);
	        
	        Instance novo = new DenseInstance(5);
	        novo.setDataset(ins);
	        novo.setValue(0,"magenta");
	        novo.setValue(1,"cheio");
	        
	        double probabilidade[] = nb.distributionForInstance(novo);
	        System.out.println("Não: "+probabilidade[0]);
	        System.out.println("Sim: "+probabilidade[1]); 
		
	}

}
