package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.beans.Cor;
import br.com.fiap.beans.Produto;
import br.com.fiap.dao.ProdutoDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class ProdutoBO {
	
	public void inserir(Produto objeto) throws Exception, RegraNegocioException {

		if(objeto.getValorUnitario() <= 1){
			throw new RegraNegocioException("Valor Unitario não pode ser menor ou igual a um");
		}
		
		if(objeto.getPercentualDesperdicio() <= 1){
			throw new RegraNegocioException("Percentual de Desperdicio não pode ser menor ou igual a um");
		}
		
		if(objeto.getPercentualAvaria() <= 1){
			throw new RegraNegocioException("Percentual de Avaria não pode ser menor ou igual a um");
		}
		
		Cor cor = new CorBO().consultar(objeto.getCor().getCodigo());
		if(cor == null){
			throw new RegraNegocioException("Necessário inserir uma cor valida");
		}
						
		new ProdutoDAO().inserir(objeto);
		
	}

	public void alterar(Produto objeto) throws Exception, RegraNegocioException {

		if(objeto.getValorUnitario() <= 1){
			throw new RegraNegocioException("Valor Unitario não pode ser menor ou igual a um");
		}
		
		if(objeto.getPercentualDesperdicio() <= 1){
			throw new RegraNegocioException("Percentual de Desperdicio não pode ser menor ou igual a um");
		}
		
		if(objeto.getPercentualAvaria() <= 1){
			throw new RegraNegocioException("Percentual de Avaria não pode ser menor ou igual a um");
		}
		
		Cor cor = new CorBO().consultar(objeto.getCor().getCodigo());
		if(cor == null){
			throw new RegraNegocioException("Necessário inserir uma cor valida");
		}

		new ProdutoDAO().alterar(objeto);
	}

	public void excluir(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		new ProdutoDAO().excluir(codigo);
	}

	public Produto consultar(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new ProdutoDAO().consultar(codigo);
	}
	
	public List<Produto> recuperarProdutos() throws Exception, RegraNegocioException {
		// TODO Regra de Negocio
		
		List<Produto> listaProdutoMarca = new ProdutoDAO().recuperarProdutos();
		
		for (Produto produto : listaProdutoMarca) {
			
			produto.setCor(new CorBO().consultar(produto.getCor().getCodigo()));
			
		}
		
		return listaProdutoMarca;
	}
	
	public List<Produto> consultarPorDescricao(String descricao) throws Exception{
		
		//TODO Regra de Negocio
		
		return new ProdutoDAO().consultarPorDescricao(descricao);
	}

}
