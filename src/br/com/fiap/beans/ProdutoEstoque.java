package br.com.fiap.beans;

import java.util.Date;

public class ProdutoEstoque {

	private int codigo;
	private Estoque estoque;
	private Produto produto;
	private Date dataVencimento;
	private Date dataRecebimento;

	public ProdutoEstoque() {
		super();
	}

	public ProdutoEstoque(int codigo, Estoque estoque, Produto produto, Date dataVencimento, Date dataRecebimento) {
		super();
		this.codigo = codigo;
		this.estoque = estoque;
		this.produto = produto;
		this.dataVencimento = dataVencimento;
		this.dataRecebimento = dataRecebimento;
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

}
