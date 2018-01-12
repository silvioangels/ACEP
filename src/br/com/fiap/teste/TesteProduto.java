package br.com.fiap.teste;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Cor;
import br.com.fiap.beans.Produto;
import br.com.fiap.bo.ProdutoBO;
import br.com.fiap.dao.ProdutoDAO;
import br.com.fiap.excecao.Excecao;
import br.com.fiap.excecao.RegraNegocioException;

public class TesteProduto {
	
public static void main(String[] args) {
		
		ProdutoDAO dao = null;
		try{
			dao = new ProdutoDAO();
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
					
						ProdutoBO bo = new ProdutoBO();
						
						Produto objeto = new Produto();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Produto")));
						Cor cor = new Cor();
						cor.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Cor")));
						objeto.setCor(cor);
						
						objeto.setDescricao(JOptionPane.showInputDialog("Digite a Descrição"));
						objeto.setDescricaoCompleta(JOptionPane.showInputDialog("Digite a Descrição Completa"));
						objeto.setValorUnitario(Double.parseDouble(JOptionPane.showInputDialog("Digite o valor unitario")));
						objeto.setPercentualDesperdicio(Integer.parseInt(JOptionPane.showInputDialog("Digite o percentual de desperdicio")));
						objeto.setPercentualAvaria(Integer.parseInt(JOptionPane.showInputDialog("Digite o percentual de avaria")));
						
						bo.inserir(objeto);
						
						JOptionPane.showMessageDialog(null, "Inserido com Sucesso");
						
					}else if(op=='C'){
						
						ProdutoBO bo = new ProdutoBO();
						Produto produto = bo.consultar(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Produto")));
						
						if(produto == null){
							JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro");
						}else{
							JOptionPane.showMessageDialog(null, "Produto:"
																+ "\n codigo: "+produto.getCodigo()
																+ "\n codigo cor: "+produto.getCor().getCodigo()
																+ "\n descricao: "+produto.getDescricao()
																+ "\n descricao completa: "+produto.getDescricaoCompleta()
																+ "\n valor unitario: "+produto.getValorUnitario()
																+ "\n percentual desperdicio: "+produto.getPercentualDesperdicio()
																+ "\n percentual avaria: "+produto.getPercentualAvaria()
							);
						}
						
					}else if(op=='E'){
						
						ProdutoBO bo = new ProdutoBO();
						bo.excluir(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Produto")));
						
						JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
						
					}else if (op=='A') {
						
						ProdutoBO bo = new ProdutoBO();
						
						Produto objeto = new Produto();
						
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Produto que se deseja alterar")));
						Cor cor = new Cor();
						cor.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo da Cor")));
						objeto.setCor(cor);
						
						objeto.setDescricao(JOptionPane.showInputDialog("Digite a Descrição"));
						objeto.setDescricaoCompleta(JOptionPane.showInputDialog("Digite a Descrição Completa"));
						objeto.setValorUnitario(Double.parseDouble(JOptionPane.showInputDialog("Digite o valor unitario")));
						objeto.setPercentualDesperdicio(Integer.parseInt(JOptionPane.showInputDialog("Digite o percentual de desperdicio")));
						objeto.setPercentualAvaria(Integer.parseInt(JOptionPane.showInputDialog("Digite o percentual de avaria")));
						
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
					(null, "Continuar", "ProdutoDAO",
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
