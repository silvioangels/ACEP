package br.com.fiap.beans;

public class Estoque {

	private int codigo;
	private Armazem armazem;
	private int nrControleDesperdicio;
	private double saldoFisicoArmazem;
	private double saldoFisicoMovimentacaoEstoque;
	private double saldoFisicoCentral;

	public Estoque() {
		super();
	}

	public Estoque(int codigo, Armazem armazem, int nrControleDesperdicio, double saldoFisicoArmazem,
			double saldoFisicoMovimentacaoEstoque, double saldoFisicoCentral) {
		super();
		this.codigo = codigo;
		this.armazem = armazem;
		this.nrControleDesperdicio = nrControleDesperdicio;
		this.saldoFisicoArmazem = saldoFisicoArmazem;
		this.saldoFisicoMovimentacaoEstoque = saldoFisicoMovimentacaoEstoque;
		this.saldoFisicoCentral = saldoFisicoCentral;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Armazem getArmazem() {
		return armazem;
	}

	public void setArmazem(Armazem armazem) {
		this.armazem = armazem;
	}

	public int getNrControleDesperdicio() {
		return nrControleDesperdicio;
	}

	public void setNrControleDesperdicio(int nrControleDesperdicio) {
		this.nrControleDesperdicio = nrControleDesperdicio;
	}

	public double getSaldoFisicoArmazem() {
		return saldoFisicoArmazem;
	}

	public void setSaldoFisicoArmazem(double saldoFisicoArmazem) {
		this.saldoFisicoArmazem = saldoFisicoArmazem;
	}

	public double getSaldoFisicoMovimentacaoEstoque() {
		return saldoFisicoMovimentacaoEstoque;
	}

	public void setSaldoFisicoMovimentacaoEstoque(double saldoFisicoMovimentacaoEstoque) {
		this.saldoFisicoMovimentacaoEstoque = saldoFisicoMovimentacaoEstoque;
	}

	public double getSaldoFisicoCentral() {
		return saldoFisicoCentral;
	}

	public void setSaldoFisicoCentral(double saldoFisicoCentral) {
		this.saldoFisicoCentral = saldoFisicoCentral;
	}

}
