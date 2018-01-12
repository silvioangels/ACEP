package br.com.fiap.teste;

import java.sql.Connection;

import br.com.fiap.conexao.ConexaoFactory;

public class TesteConexao {

	public static void main(String[] args) {
		Connection c= null;
		try{
			ConexaoFactory cf= new ConexaoFactory();
			c= cf.getBancoConnection();
			System.out.println("Funcionou");
		}catch(Exception e){
			System.out.println("Não Funcionou");
			e.printStackTrace();
		}finally{
			try{
				c.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}

}
