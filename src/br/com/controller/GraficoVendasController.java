package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class GraficoVendasController implements Initializable{
	
	@FXML private AnchorPane anpGrafVendas;
	
	@FXML private RadioButton rbSemanal;
	@FXML private RadioButton rbMensal;
	@FXML private RadioButton rbTrimestral;
	@FXML private RadioButton rbPeriodo;
	
	@FXML private Label lblDtInicial;
	@FXML private Label lblDtFim;
	
	@FXML private DatePicker dtpInicio;
	@FXML private DatePicker dtpFim;
	
	@FXML private Button btnFiltrar;
	
	@FXML private BarChart<String, Number> brcGrafVendas;
	@FXML private CategoryAxis xEixo;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		rbSemanal.setOnAction(s -> preencherGrafico());
		xEixo.tickLabelFontProperty().set(Font.font(14));
		brcGrafVendas.setTitle("Gráfico de Vendas");
	}

	
	private void preencherGrafico() {
		
		XYChart.Data<String, Number> perdaPeso = new XYChart.Data<String, Number>("Perda de Peso", 18.156);	
		XYChart.Data<String, Number> ganhoMassa = new XYChart.Data<String, Number>("Ganho de Massa", 9.115);
		XYChart.Data<String, Number> forcaResis = new XYChart.Data<String, Number>("Força e Resistencia", 17.141);
		
		XYChart.Series<String, Number> categorias = new XYChart.Series<String, Number>();
		categorias.setName("Categorias");
		categorias.getData().addAll(perdaPeso, ganhoMassa, forcaResis);
		
		brcGrafVendas.getData().add(categorias);
		
		XYChart.Data<String, Number> total = new XYChart.Data<String, Number>("Total", 30.250);
		
		XYChart.Series<String, Number> totalCatego = new XYChart.Series<String, Number>();
		totalCatego.setName("Total");
		totalCatego.getData().add(total);
		
		brcGrafVendas.getData().add(totalCatego);
	
		
		rbSemanal.setDisable(true);
	}	

}
