package br.com.fiap.beans;

public class MateriaPrima {

	private int codigo;
	private Cor cor;
	private String descricao;
	private int quantidade;

	public MateriaPrima() {
		super();
	}

	public MateriaPrima(int codigo, Cor cor, String descricao, int quantidade) {
		super();
		this.codigo = codigo;
		this.cor = cor;
		this.descricao = descricao;
		this.quantidade = quantidade;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
