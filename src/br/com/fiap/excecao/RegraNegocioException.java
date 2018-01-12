package br.com.fiap.excecao;

public class RegraNegocioException extends Exception {
	
	private static final long serialVersionUID = -2104483237839072513L;
	
	public RegraNegocioException(String message){
		super(message);
	}
	
}