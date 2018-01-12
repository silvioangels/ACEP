package br.com.fiap.beans;

public class Afericao {

	private int codigo;
	private int nota;
	private String descricao;
	private String descricaoTraduzida;

	public Afericao() {
		super();
	}

	public Afericao(int codigo, int nota, String descricao, String descricaoTraduzida) {
		super();
		this.codigo = codigo;
		this.nota = nota;
		this.descricao = descricao;
		this.descricaoTraduzida = descricaoTraduzida;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoTraduzida() {
		return descricaoTraduzida;
	}

	public void setDescricaoTraduzida(String descricaoTraduzida) {
		this.descricaoTraduzida = descricaoTraduzida;
	}

}
