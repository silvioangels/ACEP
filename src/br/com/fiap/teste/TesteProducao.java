package br.com.fiap.teste;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Producao;
import br.com.fiap.beans.Produto;
import br.com.fiap.bo.ProducaoBO;
import br.com.fiap.dao.ProducaoDAO;
import br.com.fiap.excecao.Excecao;
import br.com.fiap.excecao.RegraNegocioException;

public class TesteProducao {
	
	public static void main(String[] args) {
		
		ProducaoDAO dao = null;
		try{
			dao = new ProducaoDAO();
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
					
						ProducaoBO bo = new ProducaoBO();
						
						Producao objeto = new Producao();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo de Produção")));
						Produto produto = new Produto();
						produto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Produto")));
						objeto.setProduto(produto);
						objeto.setNome(JOptionPane.showInputDialog("Digite o Nome da Producao"));
						objeto.setTempoMedio(Integer.parseInt(JOptionPane.showInputDialog("Digite o Tempo Medio")));
						objeto.setQtdDesperdicioCor(Integer.parseInt(JOptionPane.showInputDialog("Digite a qtd de desperdicio cor")));
						objeto.setQtdAvaria(Integer.parseInt(JOptionPane.showInputDialog("Digite a qtd de avaria")));
						
						bo.inserir(objeto);
						
						JOptionPane.showMessageDialog(null, "Inserido com Sucesso");
						
					}else if(op=='C'){
						
						ProducaoBO bo = new ProducaoBO();
						Producao producao = bo.consultar(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Producao")));
						
						if(producao == null){
							JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro");
						}else{
							JOptionPane.showMessageDialog(null, "Producao:"
																+ "\n codigo: "+producao.getCodigo()
																+ "\n codigo produto: "+producao.getProduto().getCodigo()
																+ "\n nome: "+producao.getNome()
																+ "\n tempo medio: "+producao.getTempoMedio()
																+ "\n tempo desperdicio cor: "+producao.getQtdDesperdicioCor()
																+ "\n tempo qtd avaria: "+producao.getQtdAvaria()
							);
						}
						
					}else if(op=='E'){
						
						ProducaoBO bo = new ProducaoBO();
						bo.excluir(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Producao")));
						
						JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
						
					}else if (op=='A') {
						
						ProducaoBO bo = new ProducaoBO();
						
						Producao objeto = new Producao();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo de Produção que se deseja alterar")));
						Produto produto = new Produto();
						produto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Produto")));
						objeto.setProduto(produto);
						objeto.setNome(JOptionPane.showInputDialog("Digite o Nome da Producao"));
						objeto.setTempoMedio(Integer.parseInt(JOptionPane.showInputDialog("Digite o Tempo Medio")));
						objeto.setQtdDesperdicioCor(Integer.parseInt(JOptionPane.showInputDialog("Digite a qtd de desperdicio cor")));
						objeto.setQtdAvaria(Integer.parseInt(JOptionPane.showInputDialog("Digite a qtd de avaria")));
						
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
					(null, "Continuar", "ProducaoDAO",
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
