package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.dao.UsuarioFuncionarioLojaDAO;
import br.com.model.UsuarioFuncionarioLoja;
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
	@FXML private Button btnMinimizarLogin;
	@FXML private Button btnFecharLogin;
	
	public static String usuario_login;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnMinimizarLogin.setOnAction(m -> minimizarLogin());
		
		btnFecharLogin.setOnAction(f -> fecharLogin());
		
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

	private void minimizarLogin() {
		Stage minimizar = (Stage)btnMinimizarLogin.getScene().getWindow();
		minimizar.setIconified(true);
	}

	private void fecharLogin() {
		System.exit(0);
	}

	@FXML 
	public void login() {

		List<UsuarioFuncionarioLoja> lstRetorno = UsuarioFuncionarioLojaDAO.filtrarUsuario();

		for (UsuarioFuncionarioLoja usuarioFuncionarioLoja : lstRetorno) {

			if(txtUsuario.getText().equals(usuarioFuncionarioLoja.getUsuario().getNomeUsuario()) && 
					txtSenha.getText().equals(usuarioFuncionarioLoja.getUsuario().getSenha()))
			{
				usuario_login = usuarioFuncionarioLoja.getUsuario().getNomeUsuario();

				limparLogin();

				Janelas layout = new Janelas();
				layout.abrir("Layout.fxml", new Stage(), "", true);

				Stage login = (Stage)btnEntrar.getScene().getWindow();
				login.close();
				break;
			}
			else{
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Login", "ERRO", "Usuário e Senha incorretos!");

				txtSenha.clear();
			}
		}
	}
	private void limparLogin(){
		txtUsuario.clear();
		txtSenha.clear();
	}

}
