package br.com.fiap.teste;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Cor;
import br.com.fiap.bo.CorBO;
import br.com.fiap.dao.CorDAO;
import br.com.fiap.excecao.Excecao;
import br.com.fiap.excecao.RegraNegocioException;

public class TesteCor {
	
	public static void main(String[] args) {
		
		CorDAO dao = null;
		try{
			dao = new CorDAO();
			do{
				
				String opcao = JOptionPane.showInputDialog("Digite a opção:\n"
																+ "<I> - Inserir:\n"
																+ "<C> - Consultar:\n"
																+ "<E> - Excluir:\n"
																+ "<A> - Alterar:\n");
				char op = ' ';
				if(opcao != null && !opcao.isEmpty()){
					op = opcao.toUpperCase().charAt(0);
				}				
				
				try {
					if(op=='I') {
					
						CorBO bo = new CorBO();
						
						Cor objeto = new Cor();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Cor")));
						objeto.setDescricao(JOptionPane.showInputDialog("Digite a Descrição"));
						
						bo.inserir(objeto);
						
						JOptionPane.showMessageDialog(null, "Inserido com Sucesso");
						
					
					}else if(op=='C'){
						
						CorBO bo = new CorBO();
						Cor objeto = bo.consultar(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Cor")));
						
						if(objeto == null){
							JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro");
						}else{
							
							JOptionPane.showMessageDialog(null, "Cor:\n"
									+ "codigo: "+objeto.getCodigo()
									+ "\n descrição: "+objeto.getDescricao()
							);
							
						}
						
					}else if(op=='E'){
						
						CorBO bo = new CorBO();
						bo.excluir(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Cor")));
						
						JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
						
					}else if (op=='A') {
						
						CorBO bo = new CorBO();
						
						Cor objeto = new Cor();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Cor que se deseja alterar")));
						objeto.setDescricao(JOptionPane.showInputDialog("Digite a Descrição"));
						
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
