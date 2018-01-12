package br.com.fiap.beans;

import java.util.Date;
import java.util.List;

public class ItemProdutoVenda {

	private int codigo;
	private List<Produto> listaProduto;
	private Date dataEntrega;
	private int quantidade;

	public ItemProdutoVenda() {
		super();
	}

	public ItemProdutoVenda(int codigo, List<Produto> listaProduto, Date dataEntrega, int quantidade) {
		super();
		this.codigo = codigo;
		this.listaProduto = listaProduto;
		this.dataEntrega = dataEntrega;
		this.quantidade = quantidade;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
