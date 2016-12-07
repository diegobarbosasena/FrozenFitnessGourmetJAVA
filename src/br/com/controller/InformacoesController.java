package br.com.controller;

import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import br.com.view.Janelas;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InformacoesController implements Initializable{

	@FXML private AnchorPane anpInfo;
	@FXML private Label lblUsuario;
	@FXML private Label lblHora;
	@FXML private Label lblData;
	@FXML private Button btnSair;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnSair.setOnAction(s -> sair());

		lblUsuario.setText(LoginController.usuario_login);

		SimpleDateFormat d = new SimpleDateFormat("dd/MM/YYYY");
		lblData.setText(d.format(new Date()));

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					final SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							lblHora.setText(hora.format(new Date()));
						}
					});
					try {
						Thread.sleep(1000);
					} catch (Exception ex) {
						break;
					}
				}
			}
		});
		t.start();
	}

	private void sair() {
		Janelas login = new Janelas();
		login.abrir("Login.fxml", new Stage(), "Login", false);

		Stage layout = (Stage)btnSair.getScene().getWindow();
		layout.close();
	}
}
