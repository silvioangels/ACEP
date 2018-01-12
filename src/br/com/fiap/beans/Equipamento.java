package br.com.fiap.beans;

public class Equipamento {

	private int codigo;
	private String descricao;
	private String descricaoTraduzida;
	private boolean habilitarAlterar;
	private boolean habilitarExcluir;

	public Equipamento() {
		super();
	}

	public Equipamento(int codigo, String descricao, String descricaoTraduzida) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoTraduzida = descricaoTraduzida;
	}

	public Equipamento(int codigo, String descricao, String descricaoTraduzida, boolean habilitarAlterar,
			boolean habilitarExcluir) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoTraduzida = descricaoTraduzida;
		this.habilitarAlterar = habilitarAlterar;
		this.habilitarExcluir = habilitarExcluir;
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

	public boolean isHabilitarAlterar() {
		return habilitarAlterar;
	}

	public void setHabilitarAlterar(boolean habilitarAlterar) {
		this.habilitarAlterar = habilitarAlterar;
	}

	public boolean isHabilitarExcluir() {
		return habilitarExcluir;
	}

	public void setHabilitarExcluir(boolean habilitarExcluir) {
		this.habilitarExcluir = habilitarExcluir;
	}

}
