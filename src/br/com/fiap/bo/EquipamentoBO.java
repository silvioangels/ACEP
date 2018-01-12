package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.beans.Equipamento;
import br.com.fiap.dao.EquipamentoDAO;
import br.com.fiap.excecao.RegraNegocioException;

public class EquipamentoBO {

	public void inserir(Equipamento objeto) throws Exception, RegraNegocioException {

		new EquipamentoDAO().inserir(objeto);
	}

	public void alterar(Equipamento objeto) throws Exception, RegraNegocioException {
		
		new EquipamentoDAO().alterar(objeto);
	}

	public void excluir(int codigo) throws Exception, RegraNegocioException {


		new EquipamentoDAO().excluir(codigo);
	}

	public Equipamento consultar(int codigo) throws Exception, RegraNegocioException {

		// TODO Regra de Negocio

		return new EquipamentoDAO().consultar(codigo);
	}
	
	public List<Equipamento> consultarTodosEquipamentos() throws Exception, RegraNegocioException {

		List<Equipamento> listaEquipamentos = new EquipamentoDAO().consultarTodosEquipamentos();
		
		for (Equipamento equipamento : listaEquipamentos) {
			
			boolean habilitarBotoesCrud = false;
			
			if(equipamento.getCodigo() == 1 
					|| equipamento.getCodigo() == 2
					|| equipamento.getCodigo() == 3
					|| equipamento.getCodigo() == 4){
				habilitarBotoesCrud = true;
			}
			
			equipamento.setHabilitarAlterar(habilitarBotoesCrud);
			equipamento.setHabilitarExcluir(habilitarBotoesCrud);
			
		}

		return listaEquipamentos;
	}
	
	public int recuperarProximoRegistro() throws Exception{
		
		// TODO Regra de Negocio

		return new EquipamentoDAO().recuperarProximoRegistro();
		
	}
	

}
