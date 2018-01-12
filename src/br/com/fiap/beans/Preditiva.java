package br.com.fiap.beans;

import java.util.Date;

public class Preditiva {

	private int codigo;
	private Cor cor;
	private Equipamento equipamento;
	private Producao producao;
	private Armazem armazem;
	private MateriaPrima materiaPrima;
	private StatusPedido statusPedido;
	private Double quantidadeDesperdicio;
	private Double quantidadeAvaria;
	private Double quantidadeConsumida;
	private Date dataInclusao;

	public Preditiva() {
		super();
	}

	public Preditiva(int codigo, Cor cor, Equipamento equipamento, Producao producao, Armazem armazem,
			MateriaPrima materiaPrima, StatusPedido statusPedido, Double quantidadeDesperdicio, Double quantidadeAvaria,
			Double quantidadeConsumida, Date dataInclusao) {
		super();
		this.codigo = codigo;
		this.cor = cor;
		this.equipamento = equipamento;
		this.producao = producao;
		this.armazem = armazem;
		this.materiaPrima = materiaPrima;
		this.statusPedido = statusPedido;
		this.quantidadeDesperdicio = quantidadeDesperdicio;
		this.quantidadeAvaria = quantidadeAvaria;
		this.quantidadeConsumida = quantidadeConsumida;
		this.dataInclusao = dataInclusao;
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

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Producao getProducao() {
		return producao;
	}

	public void setProducao(Producao producao) {
		this.producao = producao;
	}

	public Armazem getArmazem() {
		return armazem;
	}

	public void setArmazem(Armazem armazem) {
		this.armazem = armazem;
	}

	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public Double getQuantidadeDesperdicio() {
		return quantidadeDesperdicio;
	}

	public void setQuantidadeDesperdicio(Double quantidadeDesperdicio) {
		this.quantidadeDesperdicio = quantidadeDesperdicio;
	}

	public Double getQuantidadeAvaria() {
		return quantidadeAvaria;
	}

	public void setQuantidadeAvaria(Double quantidadeAvaria) {
		this.quantidadeAvaria = quantidadeAvaria;
	}

	public Double getQuantidadeConsumida() {
		return quantidadeConsumida;
	}

	public void setQuantidadeConsumida(Double quantidadeConsumida) {
		this.quantidadeConsumida = quantidadeConsumida;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

}
