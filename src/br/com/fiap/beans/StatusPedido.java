package br.com.fiap.beans;

public class StatusPedido {
	private int codigo;
	private String descricao;
	private String descricaoTraduzida;

	public StatusPedido() {
		super();
	}

	public StatusPedido(int codigo, String descricao, String descricaoTraduzida) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoTraduzida = descricaoTraduzida;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoTraduzida() {
		return descricaoTraduzida;
	}

	public void setDescricaoTraduzida(String descricaoTraduzida) {
		this.descricaoTraduzida = descricaoTraduzida;
	}

}
