package br.com.controller;

import br.com.view.Janelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private TextField txtUsuario;
	@FXML
	private Button btnEntrar;
	@FXML
	private PasswordField txtSenha;
	
	// Event Listener on Button[#btnEntrar].onAction
	@FXML
	public void login(ActionEvent event) {

		if(txtUsuario.getText().equals("teste") && txtSenha.getText().equals("123")){
			
			System.out.println();
			System.out.println("USUARIO " + txtUsuario.getText());
			System.out.println("SENHA " + txtSenha.getText());
			System.out.println("LOGIN EFETUADO COM SUCESSO...");

			limpar();
			
			Janelas layout = new Janelas();
			layout.abrir("Layout.fxml", new Stage(), "", true);
			
			Stage login = (Stage)btnEntrar.getScene().getWindow();
			login.close();
		}
		else{
			
			PopUpController erro = new PopUpController("ERRO DE LOGIN", "Usuário e Senha incorretos!", "Fechar");

			Janelas erroLogin = new Janelas();
			erroLogin.abrirPopup("PopUp.fxml", new Stage(), "Erro de Login", false, erro);
			
			limpar();
			System.out.println();
			System.out.println("ERRO DE LOGIN");
		}
	}
	public void limpar(){
		txtUsuario.clear();
		txtSenha.clear();
	}
}
