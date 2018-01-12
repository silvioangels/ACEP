package br.com.fiap.teste;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Armazem;
import br.com.fiap.beans.Estoque;
import br.com.fiap.beans.MateriaPrima;
import br.com.fiap.bo.EstoqueBO;
import br.com.fiap.bo.MateriaPrimaBO;
import br.com.fiap.dao.EstoqueDAO;
import br.com.fiap.excecao.Excecao;
import br.com.fiap.excecao.RegraNegocioException;

public class TesteEstoque {
	
	public static void main(String[] args) {
		
		EstoqueDAO dao = null;
		try{
			dao = new EstoqueDAO();
			do{
				String opcao = JOptionPane.showInputDialog("Digite a opção:\n"
						+ "<I> - Inserir:\n"
						+ "<C> - Consultar:\n"
						+ "<E> - Excluir:\n"
						+ "<A> - Alterar:\n");
				char op = ' ';
				if (opcao != null && !opcao.isEmpty()) {
					op = opcao.toUpperCase().charAt(0);
				}
				
				try {
					if(op=='I') {
					
						EstoqueBO bo = new EstoqueBO();
						
						Estoque objeto = new Estoque();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Estoque")));
						Armazem armazem = new Armazem();
						armazem.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Armazem")));
						objeto.setArmazem(armazem);
						objeto.setNrControleDesperdicio(Integer.parseInt(JOptionPane.showInputDialog("Digite o numero do Controle de Desperdicio")));
						objeto.setSaldoFisicoArmazem(Double.parseDouble(JOptionPane.showInputDialog("Digite o Saldo Fisico Armazem")));
						objeto.setSaldoFisicoCentral(Double.parseDouble(JOptionPane.showInputDialog("Digite o Saldo Fisico Central")));
						objeto.setSaldoFisicoMovimentacaoEstoque(Double.parseDouble(JOptionPane.showInputDialog("Digite o Saldo Fisico Estoque")));
						
						bo.inserir(objeto);
						
						JOptionPane.showMessageDialog(null, "Inserido com Sucesso");
						
					}else if(op=='C'){
						
						opcao = JOptionPane.showInputDialog("Digite a opção:\n"
								+ "<1> - Consulta de Produto:\n"
								+ "<2> - Consulta Percentual de Materia Prima no Estoque:\n"
								+ "<3> - Consulta Percentual de Materia Prima Consumida:\n"
								+ "<4> - Verificar Compra de Materia Prima no Estoque:\n"
								);
						op = ' ';
						if (opcao != null && !opcao.isEmpty()) {
							op = opcao.toUpperCase().charAt(0);
						}
						
						if(op=='1'){
							
							EstoqueBO bo = new EstoqueBO();
							Estoque objeto = bo.consultar(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Estoque")));
							
							if(objeto == null){
								JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro");
							}else{
								
								JOptionPane.showMessageDialog(null, "Cor:\n"
										+ "codigo: "+objeto.getCodigo()
										+ "\n codigo armazem: "+objeto.getArmazem().getCodigo()
										+ "\n nr controle desperdicio: "+objeto.getNrControleDesperdicio()
										+ "\n saldo fisico armazem: "+objeto.getSaldoFisicoArmazem()
										+ "\n saldo fisico central: "+objeto.getSaldoFisicoCentral()
										+ "\n saldo fisico movimentacao estoque: "+objeto.getSaldoFisicoMovimentacaoEstoque()
										);
								
							}
							
						}else if(op=='2'){
							
							EstoqueBO bo = new EstoqueBO();
							int codigoMateriaPrima = Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Materia Prima"));
							int percentualMateriaPrima = bo.consultarPercentualMateriaPrimaDisponivelEstoque(codigoMateriaPrima);
							MateriaPrima materiaPrima = new MateriaPrimaBO().consultar(codigoMateriaPrima);
							
							JOptionPane.showMessageDialog(null, "A Materia Prima "+materiaPrima.getCodigo()+" - "+materiaPrima.getDescricao()+" tem "+percentualMateriaPrima+" % disponivel no estoque");
							
						}else if(op=='3'){
							
							EstoqueBO bo = new EstoqueBO();
							int codigoMateriaPrima = Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Materia Prima"));
							int percentualMateriaPrima = bo.consultarPercentualMateriaPrimaConsumidaEstoque(codigoMateriaPrima);
							MateriaPrima materiaPrima = new MateriaPrimaBO().consultar(codigoMateriaPrima);
							
							JOptionPane.showMessageDialog(null, "Foram consumidas "+percentualMateriaPrima+" % da Materia Prima "+materiaPrima.getCodigo()+" - "+materiaPrima.getDescricao() +" no Estoque");
							
						}else if(op=='4'){
							
							EstoqueBO bo = new EstoqueBO();
							int codigoMateriaPrima = Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Materia Prima"));
							boolean comprarMateriaPrima = bo.verificarCompraMateriaPrimaEstoque(codigoMateriaPrima);
							MateriaPrima materiaPrima = new MateriaPrimaBO().consultar(codigoMateriaPrima);
							
							if(comprarMateriaPrima){
								JOptionPane.showMessageDialog(null, "A Materia Prima "+materiaPrima.getCodigo()+" - "+materiaPrima.getDescricao()+" acabando. \n Necessário solicitar compra.");
							}else{
								JOptionPane.showMessageDialog(null, "A Materia Prima "+materiaPrima.getCodigo()+" - "+materiaPrima.getDescricao()+" suficiente. \n Não é necessário solicitar compra");
							}
							
							
						}else{
							JOptionPane.showMessageDialog(null, "Opção Inválida!");
						}
						
						
					}else if(op=='E'){
						
						EstoqueBO bo = new EstoqueBO();
						bo.excluir(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Estoque")));
						
						JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
						
					}else if (op=='A') {
						
						EstoqueBO bo = new EstoqueBO();
						
						Estoque objeto = new Estoque();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Estoque que se deseja alterar")));
						Armazem armazem = new Armazem();
						armazem.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Armazem")));
						objeto.setArmazem(armazem);
						objeto.setNrControleDesperdicio(Integer.parseInt(JOptionPane.showInputDialog("Digite a descrição do Controle de Desperdicio")));
						objeto.setSaldoFisicoArmazem(Double.parseDouble(JOptionPane.showInputDialog("Digite o Saldo Fisico Armazem")));
						objeto.setSaldoFisicoCentral(Double.parseDouble(JOptionPane.showInputDialog("Digite o Saldo Fisico Central")));
						objeto.setSaldoFisicoMovimentacaoEstoque(Double.parseDouble(JOptionPane.showInputDialog("Digite o Saldo Fisico Estoque")));
						
						bo.alterar(objeto);
						
						JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
						
					}else{
						JOptionPane.showMessageDialog(null, "Opção Inválida!");
					}
				}catch (RegraNegocioException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}catch(SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ocorreu um exceção no Banco: "+e.getMessage());
				}catch (Exception e) {
					
					if(Excecao.getExcecao(e) != null){
						
						JOptionPane.showMessageDialog(null, Excecao.getExcecao(e));
						
					}else{
						e.printStackTrace();
					}
					
				}
				
			}while(JOptionPane.showConfirmDialog
					(null, "Continuar", "ArmazemDAO",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE)==0);
		}catch(SQLRecoverableException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possivel se conectar no Banco, favor tente novamente");
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro desconhecido. Favor olhar o Console");
		}finally{
			try {
				dao.fechar();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
	}

}