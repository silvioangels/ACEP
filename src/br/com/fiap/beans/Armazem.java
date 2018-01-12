package br.com.fiap.beans;

import java.util.Date;

public class Armazem {

	private int codigo;
	private String nome;
	private int qtdArmazenamento;
	private Date dataVencimento;
	private Date dataRecebimento;
	private String descricaoControleDesperdicio;
	
	public Armazem() {
		super();
	}

	public Armazem(int codigo, String nome, int qtdArmazenamento, Date dataVencimento, Date dataRecebimento,
			String descricaoControleDesperdicio) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.qtdArmazenamento = qtdArmazenamento;
		this.dataVencimento = dataVencimento;
		this.dataRecebimento = dataRecebimento;
		this.descricaoControleDesperdicio = descricaoControleDesperdicio;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdArmazenamento() {
		return qtdArmazenamento;
	}

	public void setQtdArmazenamento(int qtdArmazenamento) {
		this.qtdArmazenamento = qtdArmazenamento;
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

	public String getDescricaoControleDesperdicio() {
		return descricaoControleDesperdicio;
	}

	public void setDescricaoControleDesperdicio(String descricaoControleDesperdicio) {
		this.descricaoControleDesperdicio = descricaoControleDesperdicio;
	}

}
