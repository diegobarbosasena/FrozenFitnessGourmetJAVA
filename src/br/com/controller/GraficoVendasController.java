package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.BarChart;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;

public class GraficoVendasController implements Initializable {
	
	@FXML private AnchorPane anpGrafVendas;
	@FXML private RadioButton rbSemanal;
	@FXML private RadioButton rbMensal;
	@FXML private RadioButton rbTrimestral;
	@FXML private RadioButton rbPeriodo;
	@FXML private Label lblDtInicial;
	@FXML private Label lblDtFim;
	@FXML private DatePicker dtpInicio;
	@FXML private DatePicker dtpFim;
	@FXML private BarChart<String, Number> brcGrafVendas;
	@FXML private CategoryAxis xEixo;
	@FXML private Button btnRelatorio;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		radioButtonGroup();
		
		rbSemanal.setOnAction(s -> preencherGraficoSemanal());
		rbMensal.setOnAction(m -> preencherGraficoMensal());
		rbTrimestral.setOnAction(t -> preencherGraficoTrimestral());
		rbPeriodo.setOnAction(p -> preencherGraficoPeriodo());
		
		xEixo.tickLabelFontProperty().set(Font.font(14));
		brcGrafVendas.setTitle("Gráfico de Vendas");
	}
	
	public void radioButtonGroup() {
		final ToggleGroup tgFiltrar = new ToggleGroup();
		
		rbMensal.setToggleGroup(tgFiltrar);
		rbPeriodo.setToggleGroup(tgFiltrar);
		rbSemanal.setToggleGroup(tgFiltrar);
		rbTrimestral.setToggleGroup(tgFiltrar);
	}
	
	private void preencherGraficoSemanal() {
		
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
	}
	
	private void preencherGraficoTrimestral() {
		
	}

	private void preencherGraficoMensal() {
		
	}

	private void preencherGraficoPeriodo() {
		
		dtpInicio.setDisable(false);
		dtpFim.setDisable(false);
		lblDtInicial.setDisable(false);
		lblDtFim.setDisable(false);
	}

	
}
