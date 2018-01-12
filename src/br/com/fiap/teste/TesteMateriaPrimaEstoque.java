package br.com.fiap.teste;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Estoque;
import br.com.fiap.beans.MateriaPrima;
import br.com.fiap.beans.MateriaPrimaEstoque;
import br.com.fiap.bo.MateriaPrimaEstoqueBO;
import br.com.fiap.dao.MateriaPrimaEstoqueDAO;
import br.com.fiap.excecao.Excecao;
import br.com.fiap.excecao.RegraNegocioException;

public class TesteMateriaPrimaEstoque {
	
	public static void main(String[] args) {
		
		MateriaPrimaEstoqueDAO dao = null;
		try{
			dao = new MateriaPrimaEstoqueDAO();
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
					
						MateriaPrimaEstoqueBO bo = new MateriaPrimaEstoqueBO();
						
						MateriaPrimaEstoque objeto = new MateriaPrimaEstoque();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Materia Prima Estoque")));
						MateriaPrima  materiaPrima = new MateriaPrima();
						materiaPrima.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Materia Prima")));
						objeto.setMateriaPrima(materiaPrima);
						Estoque estoque = new Estoque();
						estoque.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Estoque")));
						objeto.setEstoque(estoque);
						DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						objeto.setDataRecebimento((Date)formatter.parse(JOptionPane.showInputDialog("Digite a Data de Recebimento no formato dd/MM/yyyy")));
						objeto.setDataVencimento((Date)formatter.parse(JOptionPane.showInputDialog("Digite a Data de Vencimento no formato dd/MM/yyyy")));
						objeto.setIndicadorBaixa(JOptionPane.showInputDialog("Digite se já foi dados a baixa \n S - SIM \n N - NÂO").toUpperCase().charAt(0));
						
						bo.inserir(objeto);
						
						JOptionPane.showMessageDialog(null, "Inserido com Sucesso");
						
					}else if(op=='C'){
						
						MateriaPrimaEstoqueBO bo = new MateriaPrimaEstoqueBO();
						MateriaPrimaEstoque objeto = bo.consultar(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Materia Prima Estoque")));
						
						if(objeto == null){
							JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro");
						}else{
							JOptionPane.showMessageDialog(null, "Cor:"
																+ "\n codigo: "+objeto.getCodigo()
																+ "\n codigo materia prima: "+objeto.getMateriaPrima().getCodigo()
																+ "\n codigo estoque: "+objeto.getEstoque().getCodigo()
																+ "\n data recebimento: "+objeto.getDataRecebimento()
																+ "\n data vencimeto: "+objeto.getDataVencimento()
																+ "\n Baixa: "+objeto.getIndicadorBaixa()
																
							);
						}
						
					}else if(op=='E'){
						
						MateriaPrimaEstoqueBO bo = new MateriaPrimaEstoqueBO();
						bo.excluir(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Cor")));
						
						JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
						
					}else if (op=='A') {
						
						MateriaPrimaEstoqueBO bo = new MateriaPrimaEstoqueBO();
						
						MateriaPrimaEstoque objeto = new MateriaPrimaEstoque();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Materia Prima Estoque que se deseja alterar")));
						MateriaPrima  materiaPrima = new MateriaPrima();
						materiaPrima.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Materia Prima")));
						objeto.setMateriaPrima(materiaPrima);
						Estoque estoque = new Estoque();
						estoque.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Estoque")));
						objeto.setEstoque(estoque);
						DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						objeto.setDataRecebimento((Date)formatter.parse(JOptionPane.showInputDialog("Digite a Data de Recebimento no formato dd/MM/yyyy")));
						objeto.setDataVencimento((Date)formatter.parse(JOptionPane.showInputDialog("Digite a Data de Vencimento no formato dd/MM/yyyy")));
						objeto.setIndicadorBaixa(JOptionPane.showInputDialog("Digite se já foi dados a baixa \n S - SIM \n N - NÂO").toUpperCase().charAt(0));
						
						bo.alterar(objeto);
						
						JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
						
					}else{
						System.out.println("Opção Inválida!");
					}
				} catch (RegraNegocioException e) {
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
