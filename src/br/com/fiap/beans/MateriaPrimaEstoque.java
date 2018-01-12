package br.com.fiap.beans;

import java.util.Date;

public class MateriaPrimaEstoque {

	private int codigo;
	private MateriaPrima materiaPrima;
	private Estoque estoque;
	private Date dataVencimento;
	private Date dataRecebimento;
	private char indicadorBaixa;

	public MateriaPrimaEstoque() {
		super();
	}

	public MateriaPrimaEstoque(int codigo, MateriaPrima materiaPrima, Estoque estoque, Date dataVencimento,
			Date dataRecebimento, char indicadorBaixa) {
		super();
		this.codigo = codigo;
		this.materiaPrima = materiaPrima;
		this.estoque = estoque;
		this.dataVencimento = dataVencimento;
		this.dataRecebimento = dataRecebimento;
		this.indicadorBaixa = indicadorBaixa;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public char getIndicadorBaixa() {
		return indicadorBaixa;
	}

	public void setIndicadorBaixa(char indicadorBaixa) {
		this.indicadorBaixa = indicadorBaixa;
	}

}
