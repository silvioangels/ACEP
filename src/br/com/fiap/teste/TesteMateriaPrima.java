package br.com.fiap.teste;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Cor;
import br.com.fiap.beans.MateriaPrima;
import br.com.fiap.bo.MateriaPrimaBO;
import br.com.fiap.dao.MateriaPrimaDAO;
import br.com.fiap.excecao.Excecao;
import br.com.fiap.excecao.RegraNegocioException;

public class TesteMateriaPrima {
	
	public static void main(String[] args) {
		
		MateriaPrimaDAO dao = null;
		try{
			dao = new MateriaPrimaDAO();
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
					
						MateriaPrimaBO bo = new MateriaPrimaBO();
						
						MateriaPrima objeto = new MateriaPrima();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Materia Prima")));
						Cor cor = new Cor();
						cor.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Cor")));
						objeto.setCor(cor);
						objeto.setDescricao(JOptionPane.showInputDialog("Digite a Descrição"));
						objeto.setQuantidade(Integer.parseInt(JOptionPane.showInputDialog("Digite a Quantidade da Materia Prima")));
						
						bo.inserir(objeto);
						
						JOptionPane.showMessageDialog(null, "Inserido com Sucesso");
						
					}else if(op=='C'){
						
						MateriaPrimaBO bo = new MateriaPrimaBO();
						MateriaPrima objeto = bo.consultar(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Cor")));
						
						if(objeto == null){
							JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro");
						}else{
							JOptionPane.showMessageDialog(null, "Cor:"
																+ "\n codigo: "+objeto.getCodigo()
																+ "\n codigo da cor: "+objeto.getCor().getCodigo()
																+ "\n descrição: "+objeto.getDescricao()
																+ "\n quantidade: "+objeto.getQuantidade()
							);
						}
						
					}else if(op=='E'){
						
						MateriaPrimaBO bo = new MateriaPrimaBO();
						bo.excluir(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Cor")));
						
						JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
						
					}else if (op=='A') {
						
						MateriaPrimaBO bo = new MateriaPrimaBO();
						
						MateriaPrima objeto = new MateriaPrima();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Materia Prima que se deseja alterar")));
						Cor cor = new Cor();
						cor.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Cor")));
						objeto.setCor(cor);
						objeto.setDescricao(JOptionPane.showInputDialog("Digite a Descrição"));
						objeto.setQuantidade(Integer.parseInt(JOptionPane.showInputDialog("Digite a Quantidade da Materia Prima")));
						
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
