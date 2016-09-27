package br.com.controller;

import br.com.view.Janelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
	@FXML
	private DatePicker dpDataInicial, dpDataFinal;

	// Event Listener on Button[#btnSair].onAction
	@FXML
	public void sair(ActionEvent event) {

		Janelas janela = new Janelas();
		janela.abrir("Login.fxml", new Stage());
		
		Stage stage = (Stage)btnSair.getScene().getWindow();
		stage.close();	
	}
	@FXML
	public void filtrar(ActionEvent event){
		System.out.println(dpDataInicial.getValue());
		System.out.println(dpDataFinal.getValue());
	}
}
