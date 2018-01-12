package br.com.fiap.beans;

import java.util.Date;

public class MovimentoEstoque {

	private int codigo;
	private Estoque estoque;
	private String tipoDeslocamento;
	private double valorCusto;
	private double tempoAbastecimento;
	private Date prazoEntrega;
	private double precoFinal;

	public MovimentoEstoque() {
		super();
	}

	public MovimentoEstoque(int codigo, Estoque estoque, String tipoDeslocamento, double valorCusto,
			double tempoAbastecimento, Date prazoEntrega, double precoFinal) {
		super();
		this.codigo = codigo;
		this.estoque = estoque;
		this.tipoDeslocamento = tipoDeslocamento;
		this.valorCusto = valorCusto;
		this.tempoAbastecimento = tempoAbastecimento;
		this.prazoEntrega = prazoEntrega;
		this.precoFinal = precoFinal;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public String getTipoDeslocamento() {
		return tipoDeslocamento;
	}

	public void setTipoDeslocamento(String tipoDeslocamento) {
		this.tipoDeslocamento = tipoDeslocamento;
	}

	public double getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(double valorCusto) {
		this.valorCusto = valorCusto;
	}

	public double getTempoAbastecimento() {
		return tempoAbastecimento;
	}

	public void setTempoAbastecimento(double tempoAbastecimento) {
		this.tempoAbastecimento = tempoAbastecimento;
	}

	public Date getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(Date prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public double getPrecoFinal() {
		return precoFinal;
	}

	public void setPrecoFinal(double precoFinal) {
		this.precoFinal = precoFinal;
	}

}
