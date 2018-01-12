package br.com.fiap.teste;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Estoque;
import br.com.fiap.beans.Produto;
import br.com.fiap.beans.ProdutoEstoque;
import br.com.fiap.bo.ProdutoEstoqueBO;
import br.com.fiap.dao.ProdutoEstoqueDAO;
import br.com.fiap.excecao.Excecao;
import br.com.fiap.excecao.RegraNegocioException;

public class TesteProdutoEstoque {
	
public static void main(String[] args) {
		
		ProdutoEstoqueDAO dao = null;
		try{
			dao = new ProdutoEstoqueDAO();
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
					
						ProdutoEstoqueBO bo = new ProdutoEstoqueBO();
						
						ProdutoEstoque objeto = new ProdutoEstoque();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Produto Estoque")));
						Estoque estoque = new Estoque();
						estoque.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Estoque")));
						objeto.setEstoque(estoque);
						Produto produto = new Produto();
						produto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Produto")));
						objeto.setProduto(produto);
						DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						objeto.setDataVencimento((Date)formatter.parse(JOptionPane.showInputDialog("Digite a data de vencimento no formato dd/MM/yyyy")));
						objeto.setDataRecebimento((Date)formatter.parse(JOptionPane.showInputDialog("Digite a data de recebimento no formato dd/MM/yyyy")));
						
						bo.inserir(objeto);
						
						JOptionPane.showMessageDialog(null, "Inserido com Sucesso");
						
					}else if(op=='C'){
						
						ProdutoEstoqueBO bo = new ProdutoEstoqueBO();
						ProdutoEstoque produtoEstoque = bo.consultar(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do ProdutoEstoque")));
						
						if(produtoEstoque == null){
							JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro");
						}else{
							JOptionPane.showMessageDialog(null, "ProdutoEstoque:"
																+ "\n codigo: "+produtoEstoque.getCodigo()
																+ "\n codigo estoque: "+produtoEstoque.getEstoque().getCodigo()
																+ "\n codigo produto: "+produtoEstoque.getProduto().getCodigo()
																+ "\n data vencimento: "+produtoEstoque.getDataVencimento()
																+ "\n data recebimento: "+produtoEstoque.getDataRecebimento()
							);
						}
						
					}else if(op=='E'){
						
						ProdutoEstoqueBO bo = new ProdutoEstoqueBO();
						bo.excluir(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Produto Estoque")));
						
						JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
						
					}else if (op=='A') {
						
						ProdutoEstoqueBO bo = new ProdutoEstoqueBO();
						
						ProdutoEstoque objeto = new ProdutoEstoque();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo de Produto Estoque que se deseja alterar")));
						Estoque estoque = new Estoque();
						estoque.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Estoque")));
						objeto.setEstoque(estoque);
						Produto produto = new Produto();
						produto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Produto")));
						objeto.setProduto(produto);
						DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						objeto.setDataVencimento((Date)formatter.parse(JOptionPane.showInputDialog("Digite a data de vencimento no formato dd/MM/yyyy")));
						objeto.setDataRecebimento((Date)formatter.parse(JOptionPane.showInputDialog("Digite a data de recebimento no formato dd/MM/yyyy")));
						
						bo.alterar(objeto);
						
						JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
						
					}else{
						System.out.println("Opção Inválida!");
					}
				}  catch (RegraNegocioException e) {
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
					(null, "Continuar", "ProdutoEstoqueDAO",
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
