package br.com.fiap.beans;

import java.util.Date;

public class PedidoVenda {

	private int codigo;
	private StatusPedido sp;
	private Producao producao;
	private PedidoDistribuicao pedidoDistribuicao;
	private Cliente cliente;
	private Date previsaoEntrega;
	private Date dataPedido;
	private Date dataEntrega;
	private int quantidadeProduto;
	private Double valorTotal;

	public PedidoVenda() {
		super();
	}

	public PedidoVenda(int codigo, StatusPedido sp, Producao producao, PedidoDistribuicao pedidoDistribuicao,
			Cliente cliente, Date previsaoEntrega, Date dataPedido, Date dataEntrega, int quantidadeProduto,
			Double valorTotal) {
		super();
		this.codigo = codigo;
		this.sp = sp;
		this.producao = producao;
		this.pedidoDistribuicao = pedidoDistribuicao;
		this.cliente = cliente;
		this.previsaoEntrega = previsaoEntrega;
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.quantidadeProduto = quantidadeProduto;
		this.valorTotal = valorTotal;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public StatusPedido getSp() {
		return sp;
	}

	public void setSp(StatusPedido sp) {
		this.sp = sp;
	}

	public Producao getProducao() {
		return producao;
	}

	public void setProducao(Producao producao) {
		this.producao = producao;
	}

	public PedidoDistribuicao getPedidoDistribuicao() {
		return pedidoDistribuicao;
	}

	public void setPedidoDistribuicao(PedidoDistribuicao pedidoDistribuicao) {
		this.pedidoDistribuicao = pedidoDistribuicao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getPrevisaoEntrega() {
		return previsaoEntrega;
	}

	public void setPrevisaoEntrega(Date previsaoEntrega) {
		this.previsaoEntrega = previsaoEntrega;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(int quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
