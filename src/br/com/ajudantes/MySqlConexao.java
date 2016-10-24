package br.com.ajudantes;

import java.sql.Connection;
import java.sql.DriverManager;

import br.com.controller.PopUpController;
import br.com.view.Janelas;
import javafx.stage.Stage;

public class MySqlConexao {

	public static Connection ConectarDb(){
		
		String jdbc = "jdbc:mysql://";
		String ip = "10.107.134.54";
//		String ip = "10.107.144.26";
//		String ip = "192.168.1.32";
		String db = "dbsmartgourmet";
		String usuario = "root";
		String senha = "root" ;
		
		Connection c = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");	
			
			c = DriverManager.getConnection(jdbc + ip + "/" + db, usuario, senha);
			
		} catch (Exception e) {	
			//e.printStackTrace();
			
			PopUpController erro = new PopUpController("ERRO", "ERRO AO CONECTAR COM O DATABASE", "Ok");
			Janelas j = new Janelas();
			j.abrirPopup("PopUp.fxml", new Stage(), "ERRO DE CONEXÃO", false, erro);
		}

		return c;	
	}
}
