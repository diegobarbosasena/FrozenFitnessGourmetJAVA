package br.com.ajudantes;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConexao {

	public static Connection ConectarDb(){
		
		String ip = "jdbc:mysql://10.107.134.60/dbfrozenfitness";
		String usuario = "root";
		String senha = "root" ;
		
		Connection c = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");		
			c = DriverManager.getConnection(ip, usuario, senha);
			
		} catch (Exception e) {	
			e.printStackTrace();
		}

		return c;	
	}
}
