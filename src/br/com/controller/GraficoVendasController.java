package br.com.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.view.Janelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GraficoVendasController implements Initializable{
	
	@FXML
	private Label lblUsuario;
	@FXML
	private Label lblHora;
	@FXML
	private Label lblData;
	@FXML
	private Label lblIni;
	@FXML
	private Label lblFim;
	@FXML
	private Button btnSair;
	@FXML
	private Button btnFiltrar;
	@FXML
	private DatePicker dpDataInicial, dpDataFinal;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lblUsuario.setText(System.getProperty("user.name"));
		
		SimpleDateFormat sdfh = new SimpleDateFormat("HH:mm");
		lblHora.setText(sdfh.format(new Date()));

		SimpleDateFormat sdfd = new SimpleDateFormat("d/MM/YYYY");
		lblData.setText(sdfd.format(new Date()));
	}

	@FXML
	public void sair(ActionEvent event) {

		Janelas janela = new Janelas();
		janela.abrir("Login.fxml", new Stage());

		Stage stage = (Stage)btnSair.getScene().getWindow();
		stage.close();
	}
	@FXML
	public void filtrar(ActionEvent event){
		System.out.println();
		System.out.println(dpDataInicial.getValue());
		System.out.println(dpDataFinal.getValue());
		
		lblIni.setText(dpDataInicial.getValue().toString());
		lblFim.setText(dpDataFinal.getValue().toString());

		dpDataFinal.setValue(null);
		dpDataInicial.setValue(null);

		System.out.println("");
	}
}
