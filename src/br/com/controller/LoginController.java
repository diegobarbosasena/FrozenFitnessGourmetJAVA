package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.view.Alerta;
import br.com.view.Janelas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	
	@FXML private TextField txtUsuario;
	@FXML private Button btnEntrar;
	@FXML private PasswordField txtSenha;
	@FXML private AnchorPane anpLogin;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnEntrar.setOnAction(x -> login());
		
		btnEntrar.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.ENTER) {
		        login();
		    }
		});
		
		txtUsuario.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.ENTER) {
		        login();
		    }
		});
		
		txtSenha.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.ENTER) {
		        login();
		    }
		});	
	}
	
	@FXML
	private void login() {

		if(txtUsuario.getText().equals("teste") && txtSenha.getText().equals("123")){
			
			limparLogin();
			
			Janelas layout = new Janelas();
			layout.abrir("Layout.fxml", new Stage(), "", true);
			
			Stage login = (Stage)btnEntrar.getScene().getWindow();
			login.close();
		}
		else{
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Login", "ERRO", "Usuário e Senha incorretos!");
			
			limparLogin();
		}
	}
	private void limparLogin(){
		txtUsuario.clear();
		txtSenha.clear();
	}
}
