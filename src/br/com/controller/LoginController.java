package br.com.controller;

import br.com.view.Janelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

			System.out.println(txtUsuario.getText());
			System.out.println(txtSenha.getText());

			limpar();

			Janelas janela = new Janelas();
			Stage stage = (Stage)btnEntrar.getScene().getWindow();
			janela.abrir("GraficoVendas.fxml", stage);
		}
		else{
			Janelas erro = new Janelas();

			erro.abrir("Erro.fxml", new Stage());
			limpar();
			System.out.println("ERRO!");
		}

	}
	public void limpar(){
		txtUsuario.clear();
		txtSenha.clear();

	}
}
