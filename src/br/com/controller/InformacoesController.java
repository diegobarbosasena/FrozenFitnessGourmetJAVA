package br.com.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.view.Janelas;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InformacoesController implements Initializable{
	@FXML
	private AnchorPane anpInfo;
	@FXML
	private Label lblUsuario;
	@FXML
	private Label lblHora;
	@FXML
	private Label lblData;
	@FXML
	private Button btnSair;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblUsuario.setText(System.getProperty("user.name"));

		SimpleDateFormat h = new SimpleDateFormat("HH:mm");
		lblHora.setText(h.format(new Date()));
		
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/YYYY");
		lblData.setText(d.format(new Date()));
		
	}
	// Event Listener on Button[#btnSair].onAction
	@FXML
	public void sair(ActionEvent event) {
		Janelas login = new Janelas();
		login.abrir("Login.fxml", new Stage(), "Login", false);
		
		Stage layout = (Stage)btnSair.getScene().getWindow();
		layout.close();
	}
}
