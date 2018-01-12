package br.com.fiap.beans;

public class Producao {

	private int codigo;
	private Equipamento equipamento;
	private Produto produto;
	private String nome;
	private int tempoMedio;
	private double qtdDesperdicioCor;
	private int qtdAvaria;

	public Producao() {
		super();
	}

	public Producao(int codigo, Equipamento equipamento, Produto produto, String nome, int tempoMedio,
			double qtdDesperdicioCor, int qtdAvaria) {
		super();
		this.codigo = codigo;
		this.equipamento = equipamento;
		this.produto = produto;
		this.nome = nome;
		this.tempoMedio = tempoMedio;
		this.qtdDesperdicioCor = qtdDesperdicioCor;
		this.qtdAvaria = qtdAvaria;
	}

	public Producao(int codigo, Produto produto, String nome, int tempoMedio, double qtdDesperdicioCor, int qtdAvaria) {
		super();
		this.codigo = codigo;
		this.produto = produto;
		this.nome = nome;
		this.tempoMedio = tempoMedio;
		this.qtdDesperdicioCor = qtdDesperdicioCor;
		this.qtdAvaria = qtdAvaria;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTempoMedio() {
		return tempoMedio;
	}

	public void setTempoMedio(int tempoMedio) {
		this.tempoMedio = tempoMedio;
	}

	public double getQtdDesperdicioCor() {
		return qtdDesperdicioCor;
	}

	public void setQtdDesperdicioCor(double qtdDesperdicioCor) {
		this.qtdDesperdicioCor = qtdDesperdicioCor;
	}

	public int getQtdAvaria() {
		return qtdAvaria;
	}

	public void setQtdAvaria(int qtdAvaria) {
		this.qtdAvaria = qtdAvaria;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

}
