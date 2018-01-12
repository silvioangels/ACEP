package br.com.fiap.beans;

public class AfericaoProduto {
	private int codigo;
	private Produto produto;
	private Afericao afericao;
	private String descricaoStatus;
	private String descricaoStatusTraduzida;
	private int nota;

	public AfericaoProduto() {
		super();
	}

	public AfericaoProduto(int codigo, Produto produto, Afericao afericao, String descricaoStatus,
			String descricaoStatusTraduzida, int nota) {
		super();
		this.codigo = codigo;
		this.produto = produto;
		this.afericao = afericao;
		this.descricaoStatus = descricaoStatus;
		this.descricaoStatusTraduzida = descricaoStatusTraduzida;
		this.nota = nota;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Afericao getAfericao() {
		return afericao;
	}

	public void setAfericao(Afericao afericao) {
		this.afericao = afericao;
	}

	public String getDescricaoStatus() {
		return descricaoStatus;
	}

	public void setDescricaoStatus(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
	}

	public String getDescricaoStatusTraduzida() {
		return descricaoStatusTraduzida;
	}

	public void setDescricaoStatusTraduzida(String descricaoStatusTraduzida) {
		this.descricaoStatusTraduzida = descricaoStatusTraduzida;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

}
