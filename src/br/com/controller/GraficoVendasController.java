package br.com.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import br.com.view.Janelas;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GraficoVendasController {
	@FXML
	private Label lblHora;
	@FXML
	private Label lblData;
	@FXML
	private Button btnSair;
	@FXML
	private Button btnFiltrar;

	// Event Listener on Button[#btnSair].onAction
	@FXML
	public void sair(ActionEvent event) {

		Janelas janela = new Janelas();

		Stage stage = (Stage)btnSair.getScene().getWindow();
		stage.getStyle().getClass().getResource("br/com/view/application.css");

		janela.abrir("Login.fxml", stage);
	}
}
