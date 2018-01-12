package br.com.fiap.beans;

public class Produto {

	private int codigo;
	private Cor cor;
	private String descricao;
	private String descricaoCompleta;
	private double valorUnitario;
	private int percentualDesperdicio;
	private int percentualAvaria;
	private int quantidade;

	public Produto() {
		super();
	}

	public Produto(int codigo, Cor cor, String descricao, String descricaoCompleta, double valorUnitario,
			int percentualDesperdicio, int percentualAvaria, int quantidade) {
		super();
		this.codigo = codigo;
		this.cor = cor;
		this.descricao = descricao;
		this.descricaoCompleta = descricaoCompleta;
		this.valorUnitario = valorUnitario;
		this.percentualDesperdicio = percentualDesperdicio;
		this.percentualAvaria = percentualAvaria;
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

	public String getDescricaoCompleta() {
		return descricaoCompleta;
	}

	public void setDescricaoCompleta(String descricaoCompleta) {
		this.descricaoCompleta = descricaoCompleta;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getPercentualDesperdicio() {
		return percentualDesperdicio;
	}

	public void setPercentualDesperdicio(int percentualDesperdicio) {
		this.percentualDesperdicio = percentualDesperdicio;
	}

	public int getPercentualAvaria() {
		return percentualAvaria;
	}

	public void setPercentualAvaria(int percentualAvaria) {
		this.percentualAvaria = percentualAvaria;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
