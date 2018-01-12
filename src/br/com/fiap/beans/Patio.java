package br.com.fiap.beans;

public class Patio {
	private int codigo;
	private Producao prod;
	private Estoque cod;
	private Patio nome;
	private double capacPatio;
	private int paletas;
	private int quantFrotas;
	private String frota;
	
	
	
	
	public Patio(int codigo, Producao prod, Estoque cod, Patio nome, double capacPatio, int paletas, int quantFrotas,
			String frota) {
		super();
		setCodigo (codigo);
		setProd (prod);  
		setCod (cod);
		setNome (nome);
		setCapacPatio (capacPatio);
		setPaletas (paletas);
		setQuantFrotas (quantFrotas);
		setFrota(frota);
	}
	public Patio() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Producao getProd() {
		return prod;
	}
	public void setProd(Producao prod) {
		this.prod = prod;
	}
	public Estoque getCod() {
		return cod;
	}
	public void setCod(Estoque cod) {
		this.cod = cod;
	}
	public Patio getNome() {
		return nome;
	}
	public void setNome(Patio nome) {
		this.nome = nome;
	}
	public double getCapacPatio() {
		return capacPatio;
	}
	public void setCapacPatio(double capacPatio) {
		this.capacPatio = capacPatio;
	}
	public int getPaletas() {
		return paletas;
	}
	public void setPaletas(int paletas) {
		this.paletas = paletas;
	}
	public int getQuantFrotas() {
		return quantFrotas;
	}
	public void setQuantFrotas(int quantFrotas) {
		this.quantFrotas = quantFrotas;
	}
	public String getFrota() {
		return frota;
	}
	public void setFrota(String frota) {
		this.frota = frota;
	}
	
	
}
