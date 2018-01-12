package br.com.fiap.excecao;

public class Excecao extends Exception{

	private static final long serialVersionUID = -927136378802009062L;

	public Excecao(){
		
	}
	
	public static String getExcecao (Exception e){
		
		String mensagem = null;
		
		if(e.getClass().getName().equals("java.lang.NumberFormatException")){
			mensagem =  "N�mero inv�lido";
		}
		
		if(e.getClass().getName().equals("java.text.ParseException")){
			
			if(e.getMessage().contains("date")){
				mensagem =  "Data inv�lida";
			}
			
		}
		
		return mensagem;
		
	}
	
}
