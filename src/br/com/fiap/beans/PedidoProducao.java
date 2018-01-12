package br.com.fiap.beans;

import java.util.Date;

public class PedidoProducao {
	private int codigo;
	private StatusPedido sp;
	private Date previsaoEntrega;
	private String previsaoEntregaFormatada;
	private Date entrega;
	private double quantidade;
	private double valorTotal;
	private Cliente cliente;

	public PedidoProducao() {
		super();
	}

	public PedidoProducao(int codigo, StatusPedido sp, Date previsaoEntrega, Date entrega, double quantidade,
			double valorTotal, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.sp = sp;
		this.previsaoEntrega = previsaoEntrega;
		this.entrega = entrega;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
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

	public Date getPrevisaoEntrega() {
		return previsaoEntrega;
	}

	public void setPrevisaoEntrega(Date previsaoEntrega) {
		this.previsaoEntrega = previsaoEntrega;
	}

	public Date getEntrega() {
		return entrega;
	}

	public void setEntrega(Date entrega) {
		this.entrega = entrega;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getPrevisaoEntregaFormatada() {
		return previsaoEntregaFormatada;
	}

	public void setPrevisaoEntregaFormatada(String previsaoEntregaFormatada) {
		this.previsaoEntregaFormatada = previsaoEntregaFormatada;
	}

}
