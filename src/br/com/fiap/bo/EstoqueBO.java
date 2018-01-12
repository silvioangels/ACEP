package br.com.fiap.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Armazem;
import br.com.fiap.beans.Estoque;
import br.com.fiap.beans.MateriaPrima;
import br.com.fiap.dao.EstoqueDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class EstoqueBO {
	
	public void inserir(Estoque objeto) throws Exception, RegraNegocioException {

		if(objeto.getNrControleDesperdicio() <= 0){
			throw new RegraNegocioException("Numero de Controle de Desperdicio não pode ser menor ou igual a zero");
		}
		
		if(objeto.getSaldoFisicoArmazem() <= 0){
			throw new RegraNegocioException("Saldo Fisico Armazem não pode ser menor ou igual a zero");
		}
		
		if(objeto.getSaldoFisicoCentral() <= 0){
			throw new RegraNegocioException("Saldo Fisico Central não pode ser menor ou igual a zero");
		}
		
		if(objeto.getSaldoFisicoMovimentacaoEstoque() <= 0){
			throw new RegraNegocioException("Saldo Fisico Movimentação não pode ser menor ou igual a zero");
		}
		
		Armazem armazem = new ArmazemBO().consultar(objeto.getArmazem().getCodigo());
		if(armazem == null){
			throw new RegraNegocioException("Necessário inserir um armazem valido.");
		}

		new EstoqueDAO().inserir(objeto);
	}

	public void alterar(Estoque objeto) throws Exception, RegraNegocioException {

		if(objeto.getNrControleDesperdicio() <= 10){
			throw new RegraNegocioException("Numero de Controle de Desperdicio não pode ser menor ou igual a dez");
		}
		
		if(objeto.getSaldoFisicoArmazem() <= 10){
			throw new RegraNegocioException("Saldo Fisico Armazem não pode ser menor ou igual a dez");
		}
		
		if(objeto.getSaldoFisicoCentral() <= 10){
			throw new RegraNegocioException("Saldo Fisico Central não pode ser menor ou igual a dez");
		}
		
		if(objeto.getSaldoFisicoMovimentacaoEstoque() <= 10){
			throw new RegraNegocioException("Saldo Fisico Movimentação não pode ser menor ou igual a dez");
		}
		
		Armazem armazem = new ArmazemBO().consultar(objeto.getArmazem().getCodigo());
		if(armazem == null){
			throw new RegraNegocioException("Necessário inserir um armazem valido");
		}

		new EstoqueDAO().alterar(objeto);
	}

	public void excluir(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		new EstoqueDAO().excluir(codigo);
	}

	public Estoque consultar(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new EstoqueDAO().consultar(codigo);
	}
	
	public int consultarPercentualMateriaPrimaDisponivelEstoque(int codigo) throws Exception, RegraNegocioException {

		MateriaPrima materiaPrima = new MateriaPrimaBO().consultar(codigo);
		
		if(materiaPrima == null){
			throw new RegraNegocioException("Necessário inserir uma materia prima valida");
		}
		

		return new EstoqueDAO().consultarPercentualMateriaPrimaDisponivelEstoque(codigo);
	}
	
	public int consultarPercentualMateriaPrimaConsumidaEstoque(int codigo) throws Exception, RegraNegocioException {

		MateriaPrima materiaPrima = new MateriaPrimaBO().consultar(codigo);
		
		if(materiaPrima == null){
			throw new RegraNegocioException("Necessário inserir uma materia prima valida");
		}
		

		return new EstoqueDAO().consultarPercentualMateriaPrimaConsumidaEstoque(codigo);
	}
	
	public boolean verificarCompraMateriaPrimaEstoque(int codigo) throws Exception, RegraNegocioException {
		
		MateriaPrima materiaPrima = new MateriaPrimaBO().consultar(codigo);
		
		if(materiaPrima == null){
			throw new RegraNegocioException("Necessário inserir uma materia prima valida");
		}
		
		int qtdPercentualMateriaPrimaDisponivel = consultarPercentualMateriaPrimaDisponivelEstoque(codigo);
		
		return qtdPercentualMateriaPrimaDisponivel <= 30;
	}
	
	public List<Integer> consultarQuantidadeProdutosEstoque(int nrEstoque) throws Exception{
		
		List<Integer> listaQuantidadeProdutos = new ArrayList<Integer>();
		int qtdBranco 	= new EstoqueDAO().recuperarQuantidadeProdutosEstoque(nrEstoque, 1);
		int qtdAzul 	= new EstoqueDAO().recuperarQuantidadeProdutosEstoque(nrEstoque, 2);
		int qtdMajenta 	= new EstoqueDAO().recuperarQuantidadeProdutosEstoque(nrEstoque, 3);
		
		listaQuantidadeProdutos.add(qtdAzul);
		listaQuantidadeProdutos.add(qtdMajenta);
		listaQuantidadeProdutos.add(qtdBranco);
		
		return listaQuantidadeProdutos;
	}
	
	public List<Integer> consultarNumeroEstoquePeloArmazem(int codigoArmazem) throws Exception{
		
		//TODO Regra de Negocio
		
		return new EstoqueDAO().consultarNumeroEstoquePeloArmazem(codigoArmazem);
		
	}
	
}
