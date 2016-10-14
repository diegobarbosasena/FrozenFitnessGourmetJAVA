package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

import javafx.scene.control.RadioButton;

import javafx.scene.control.DatePicker;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GraficoVendasController implements Initializable{
	@FXML
	private AnchorPane anpGrafVendas;
	@FXML
	private RadioButton rbSemanal;
	@FXML
	private RadioButton rbMensal;
	@FXML
	private RadioButton rbTrimestral;
	@FXML
	private RadioButton rbPeriodo;
	@FXML
	private Label lblDtInicial;
	@FXML
	private Label lblDtFim;
	@FXML
	private DatePicker dtpInicio;
	@FXML
	private DatePicker dtpFim;
	@FXML
	private Button btnFiltrar;
	
	final static String p = "Perda de Peso";
    final static String g = "Ganho de Massa";
    final static String f = "Força e Resistencia";
    final static String t = "Total";
	
	@FXML private BarChart<String, Integer> brcGrafVendas;
	
	@FXML CategoryAxis xEixo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		rbSemanal.setOnAction(s -> preencherGrafico());
	}

	private void preencherGrafico() {
		
		XYChart.Series teste = new XYChart.Series();
		teste.setName("Semana");
		teste.getData().add(new XYChart.Data(p, 1000));
		teste.getData().add(new XYChart.Data(g, 2000));
		teste.getData().add(new XYChart.Data(f, 3000));
		
		XYChart.Series teste1 = new XYChart.Series();
		teste1.setName("total");
		teste1.getData().add(new XYChart.Data(t, 6000));
		
		
		brcGrafVendas.getData().addAll(teste, teste1);
		
		rbSemanal.setDisable(true);
	}	

}
