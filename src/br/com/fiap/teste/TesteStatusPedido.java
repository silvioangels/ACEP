package br.com.fiap.teste;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;

import javax.swing.JOptionPane;

import br.com.fiap.beans.StatusPedido;
import br.com.fiap.bo.StatusPedidoBO;
import br.com.fiap.dao.StatusPedidoDAO;
import br.com.fiap.excecao.Excecao;
import br.com.fiap.excecao.RegraNegocioException;

public class TesteStatusPedido {
	
public static void main(String[] args) {
		
		StatusPedidoDAO dao = null;
		try{
			dao = new StatusPedidoDAO();
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
					
						StatusPedidoBO bo = new StatusPedidoBO();
						
						StatusPedido objeto = new StatusPedido();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Status Pedido")));
						objeto.setDescricao(JOptionPane.showInputDialog("Digite a descrição"));
						
						bo.inserir(objeto);
						
						JOptionPane.showMessageDialog(null, "Inserido com Sucesso");
						
					}else if(op=='C'){
						
						StatusPedidoBO bo = new StatusPedidoBO();
						StatusPedido statusPedido = bo.consultar(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Status Pedido")));
						
						if(statusPedido == null){
							JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro");
						}else{
							JOptionPane.showMessageDialog(null, "StatusPedido:"
																+ "\n codigo: "+statusPedido.getCodigo()
																+ "\n descricao: "+statusPedido.getDescricao()
							);
						}
						
					}else if(op=='E'){
						
						StatusPedidoBO bo = new StatusPedidoBO();
						bo.excluir(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Status Pedido")));
						
						JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
						
					}else if (op=='A') {
						
						StatusPedidoBO bo = new StatusPedidoBO();
						
						StatusPedido objeto = new StatusPedido();
						objeto.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Codigo do Status Pedido que se deseja alterar")));
						objeto.setDescricao(JOptionPane.showInputDialog("Digite a descrição"));
						
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
					(null, "Continuar", "StatusPedidoDAO",
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
