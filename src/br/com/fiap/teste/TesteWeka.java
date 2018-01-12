package br.com.fiap.teste;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TesteWeka {
	
	public static void main(String[] args) throws Exception {
		
		DataSource ds = new DataSource("src/br/com/fiap/weka/vendas.arff");
		Instances ins = ds.getDataSet();
		System.out.println(ins.toString());
		
		//gasta_muito
		ins.setClassIndex(3);
		
		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(ins);
		
		Instance novo = new DenseInstance(3);
		novo.setDataset(ins);
		novo.setValue(0, "M");
		novo.setValue(1, "20-39");
		novo.setValue(2, "Nao");
		
		double probabilidade [] = nb.distributionForInstance(novo);
		
		System.out.println("Sim: "+probabilidade[0]);
		System.out.println("Não: "+probabilidade[1]);
	}

}
