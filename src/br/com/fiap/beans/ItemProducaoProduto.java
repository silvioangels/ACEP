package br.com.fiap.beans;

public class ItemProducaoProduto {
	private int codigo;
	private PedidoProducao pp ;
	private Produto prod;
	private Patio pat;
	
	
	
	public ItemProducaoProduto(int codigo, PedidoProducao pp, Produto prod, Patio pat) {
		super();
		setCodigo (codigo);
		setPp (pp);
		setProd (prod);
		setPat (pat);
	}
	public ItemProducaoProduto() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public PedidoProducao getPp() {
		return pp;
	}
	public void setPp(PedidoProducao pp) {
		this.pp = pp;
	}
	public Produto getProd() {
		return prod;
	}
	public void setProd(Produto prod) {
		this.prod = prod;
	}
	public Patio getPat() {
		return pat;
	}
	public void setPat(Patio pat) {
		this.pat = pat;
	}
	
	
	
}
