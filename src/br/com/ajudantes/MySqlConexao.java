package br.com.ajudantes;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConexao {

	public static Connection ConectarDb(){
		
		String jdbc = "jdbc:mysql://";
		String ip = "10.107.144.22";
		String db = "dbfrozenfitness";
		String usuario = "root";
		String senha = "root" ;
		
		Connection c = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");		
			c = DriverManager.getConnection(jdbc + ip + "/" + db, usuario, senha);
			
		} catch (Exception e) {	
			e.printStackTrace();
		}

		return c;	
	}
}