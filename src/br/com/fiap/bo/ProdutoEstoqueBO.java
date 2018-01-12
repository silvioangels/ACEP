package br.com.fiap.bo;

import br.com.fiap.beans.Estoque;
import br.com.fiap.beans.Produto;
import br.com.fiap.beans.ProdutoEstoque;
import br.com.fiap.dao.ProdutoEstoqueDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class ProdutoEstoqueBO {
	
	public void inserir(ProdutoEstoque objeto) throws Exception, RegraNegocioException {

		if(objeto.getDataRecebimento().compareTo(objeto.getDataVencimento()) >= 0){
			throw new RegraNegocioException("Data de Vencimento não pode ser menor ou igual a data de Recebimento");
		}
		
		Produto produto = new ProdutoBO().consultar(objeto.getProduto().getCodigo());
		
		if(produto == null){
			throw new RegraNegocioException("Necessário inserir um produto valido.");
		}
		
		Estoque estoque = new EstoqueBO().consultar(objeto.getEstoque().getCodigo());
		
		if(estoque == null){
			throw new RegraNegocioException("Necessário inserir um estoque valido.");
		}
						
		new ProdutoEstoqueDAO().inserir(objeto);
		
	}

	public void alterar(ProdutoEstoque objeto) throws Exception, RegraNegocioException {

		if(objeto.getDataRecebimento().compareTo(objeto.getDataVencimento()) >= 0){
			throw new RegraNegocioException("Data de Vencimento não pode ser menor ou igual a data de Recebimento");
		}
		
		Produto produto = new ProdutoBO().consultar(objeto.getProduto().getCodigo());
		
		if(produto == null){
			throw new RegraNegocioException("Necessário inserir um produto valido.");
		}
		
		Estoque estoque = new EstoqueBO().consultar(objeto.getEstoque().getCodigo());
		
		if(estoque == null){
			throw new RegraNegocioException("Necessário inserir um estoque valido.");
		}

		new ProdutoEstoqueDAO().alterar(objeto);
	}

	public void excluir(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		new ProdutoEstoqueDAO().excluir(codigo);
	}

	public ProdutoEstoque consultar(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new ProdutoEstoqueDAO().consultar(codigo);
	}
	
	public int recuperarProximoRegistro() throws Exception{
		
		// TODO Regra de Negocio

		return new ProdutoEstoqueDAO().recuperarProximoRegistro();
		
	}
	
	public void excluir(int codigoEstoque, int codigoProduto, int quantidade) throws Exception{
		
		// TODO Regra de Negocio

		 new ProdutoEstoqueDAO().excluir(codigoEstoque, codigoProduto, quantidade);
		
	}

}
