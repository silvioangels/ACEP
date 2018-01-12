package br.com.fiap.beans;

public class Cor {

	private int codigo;
	private String descricao;

	public Cor() {
		super();
	}

	public Cor(int codigo, String descricao) {
		super();
		setCodigo(codigo);
		setDescricao(descricao);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
