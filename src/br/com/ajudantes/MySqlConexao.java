package br.com.ajudantes;

import java.sql.Connection;
import java.sql.DriverManager;

import br.com.view.Alerta;

public class MySqlConexao {

	public static Connection ConectarDb(){
		
		String jdbc = "jdbc:mysql://";
//		String ip = "10.107.134.60";
	//	String ip = "10.107.144.26";
//		String ip = "10.107.134.54";
		String ip = "192.168.1.34";
//		String ip = "10.107.140.14";
//		String ip = "localhost";
		String db = "dbsmartgourmet";
		String usuario = "root";
		String senha = "root" ;
		
		/* CONEXAO CON SERVIDOR*/
		/*String ip = "192.168.0.2"
		String db = "dbsmartgourmet";
		String usuario = "smartgourmet";
		String senha = "smart147852";*/
		
		Connection c = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");	
			
			c = DriverManager.getConnection(jdbc + ip + "/" + db + "?useSSL=false", usuario, senha);
			
		} catch (Exception e) {	
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("ERRO", "ERRO DE CONEXÃO", "ERRO AO CONECTAR COM O BANCO DE DADOS.");
		}
		return c;	
	}
}
