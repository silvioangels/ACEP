package br.com.fiap.teste;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Armazem;
import br.com.fiap.bo.ArmazemBO;
import br.com.fiap.dao.ArmazemDAO;
import br.com.fiap.excecao.Excecao;
import br.com.fiap.excecao.RegraNegocioException;

public class TesteArmazem {
	
	public static void main(String[] args) {
		
		ArmazemDAO dao = null;
		try{
			dao = new ArmazemDAO();
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
					
						ArmazemBO bo = new ArmazemBO();
						
						Armazem armazem = new Armazem();
						armazem.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Armazem")));
						armazem.setNome(JOptionPane.showInputDialog("Digite o Nome"));
						armazem.setQtdArmazenamento(Integer.parseInt(JOptionPane.showInputDialog("Digite a Quantidade de Armazenamento")));
						DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						armazem.setDataRecebimento((Date)formatter.parse(JOptionPane.showInputDialog("Digite a Data de Recebimento no formato dd/MM/yyyy")));
						armazem.setDataVencimento((Date)formatter.parse(JOptionPane.showInputDialog("Digite a Data de Vencimento no formato dd/MM/yyyy")));
						armazem.setDescricaoControleDesperdicio(JOptionPane.showInputDialog("Digite o Controle de Desperdicio"));
						
						bo.inserir(armazem);
						
						JOptionPane.showMessageDialog(null, "Inserido com Sucesso");
						
					}else if(op=='C'){
						
						ArmazemBO bo = new ArmazemBO();
						Armazem armazem = bo.consultar(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Armazem")));
						
						if(armazem == null){
							JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro");
						}else{
							
							JOptionPane.showMessageDialog(null, "Armazem:\n"
									+ "codigo: "+armazem.getCodigo()
									+ "\n nome: "+armazem.getNome()
									+ "\n qtdArmazenamento: "+armazem.getQtdArmazenamento()
									+ "\n dataVencimento: "+armazem.getDataVencimento()
									+ "\n dataRecebimento: "+armazem.getDataRecebimento()
									+ "\n descricaoControleDesperdicio: "+armazem.getDescricaoControleDesperdicio()
									);
							
						}
						
					}else if(op=='E'){
						
						ArmazemBO bo = new ArmazemBO();
						bo.excluir(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Armazem")));
						
						JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
						
					}else if (op=='A') {
						
						ArmazemBO bo = new ArmazemBO();
						
						Armazem armazem = new Armazem();
						armazem.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Armazem que se deseja alterar")));
						armazem.setNome(JOptionPane.showInputDialog("Digite o Nome"));
						armazem.setQtdArmazenamento(Integer.parseInt(JOptionPane.showInputDialog("Digite a Quantidade de Armazenamento")));
						DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						armazem.setDataRecebimento((Date)formatter.parse(JOptionPane.showInputDialog("Digite a Data de Recebimento no formato dd/MM/yyyy")));
						armazem.setDataVencimento((Date)formatter.parse(JOptionPane.showInputDialog("Digite a Data de Vencimento no formato dd/MM/yyyy")));
						armazem.setDescricaoControleDesperdicio(JOptionPane.showInputDialog("Digite o Controle de Desperdicio"));
						
						bo.alterar(armazem);
						
						JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
						
					}else{
						System.out.println("Opção Inválida!");
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
