package br.com.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.model.Pedidos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

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
		brcGrafVendas.setAnimated(false);
		
		rbSemanal.setOnAction(s -> preencherGraficoSemanal());
		rbMensal.setOnAction(m -> preencherGraficoMensal());
		rbTrimestral.setOnAction(t -> preencherGraficoTrimestral());
		rbPeriodo.setOnAction(p -> preencherGraficoPeriodo());
		
		xEixo.tickLabelFontProperty().set(Font.font(14));
		brcGrafVendas.setTitle("Gr�fico de Vendas");
		
	}
	
	public void radioButtonGroup() {
		final ToggleGroup tgFiltrar = new ToggleGroup();
		
		rbMensal.setToggleGroup(tgFiltrar);
		rbPeriodo.setToggleGroup(tgFiltrar);
		rbSemanal.setToggleGroup(tgFiltrar);
		rbTrimestral.setToggleGroup(tgFiltrar);
	}
	
	private void preencherGraficoSemanal() {
		
		desabilitaAbilitaData(true);
		
		XYChart.Data<String, Number> perdaPeso = new XYChart.Data<String, Number>("Perda de Peso", 18.156);	
		XYChart.Data<String, Number> ganhoMassa = new XYChart.Data<String, Number>("Ganho de Massa", 9.115);
		XYChart.Data<String, Number> forcaResis = new XYChart.Data<String, Number>("For�a e Resistencia", 17.141);
		
		XYChart.Series<String, Number> categorias = new XYChart.Series<String, Number>();
		categorias.setName("Categorias");
		categorias.getData().addAll(perdaPeso, ganhoMassa, forcaResis);
		
		XYChart.Data<String, Number> total = new XYChart.Data<String, Number>("Total", 30.250);
		
		XYChart.Series<String, Number> totalCatego = new XYChart.Series<String, Number>();
		totalCatego.setName("Total");
		totalCatego.getData().add(total);
		
		brcGrafVendas.getData().clear();
		brcGrafVendas.getData().add(categorias);
		brcGrafVendas.getData().add(totalCatego);
	}
	
	private void preencherGraficoTrimestral() {
		
		desabilitaAbilitaData(true);
		
		XYChart.Data<String, Number> perdaPeso = new XYChart.Data<String, Number>("Perda de Peso", 500.100);
		XYChart.Data<String, Number> ganhoMassa = new XYChart.Data<String, Number>("Ganho de Massa", 100.120);
		XYChart.Data<String, Number> forcaResis = new XYChart.Data<String, Number>("For�a e Resistencia", 315.350);
		
		XYChart.Series<String, Number> categorias = new XYChart.Series<String, Number>();
		categorias.setName("Categorias");
		categorias.getData().addAll(perdaPeso, ganhoMassa, forcaResis);
		
		XYChart.Data<String, Number> total = new XYChart.Data<String, Number>("Total", 915.570);
		
		XYChart.Series<String, Number> totalCatego = new XYChart.Series<String, Number>();
		totalCatego.setName("Total");
		totalCatego.getData().add(total);
		
		brcGrafVendas.getData().clear();
		brcGrafVendas.getData().add(categorias);
		brcGrafVendas.getData().add(totalCatego);
	}

	private void preencherGraficoMensal() {
		
		desabilitaAbilitaData(true);
		
		XYChart.Data<String, Number> perdaPeso = new XYChart.Data<String, Number>("Perda de Peso", 150.102);
		XYChart.Data<String, Number> ganhoMassa = new XYChart.Data<String, Number>("Ganho de Massa", 95.530);
		XYChart.Data<String, Number> forcaResis = new XYChart.Data<String, Number>("For�a e Resistencia", 110.120);
		
		XYChart.Series<String, Number> categorias = new XYChart.Series<String, Number>();
		categorias.setName("Categorias");
		categorias.getData().addAll(perdaPeso, ganhoMassa, forcaResis);
		
		XYChart.Data<String, Number> total = new XYChart.Data<String, Number>("Total", 355.752);
		
		XYChart.Series<String, Number> totalCatego = new XYChart.Series<String, Number>();
		totalCatego.setName("Total");
		totalCatego.getData().add(total);
		
		brcGrafVendas.getData().clear();
		brcGrafVendas.getData().add(categorias);
		brcGrafVendas.getData().add(totalCatego);
	
	}

	private void preencherGraficoPeriodo() {
		
		desabilitaAbilitaData(false);
	}

	public void desabilitaAbilitaData(boolean falso) {
		
		dtpInicio.setDisable(falso);
		dtpFim.setDisable(falso);
		lblDtInicial.setDisable(falso);
		lblDtFim.setDisable(falso);
	}
	
	/*public void writeExcel() throws Exception {
	    Writer writer = null;
	    try {
	        File file = new File("C:\\Person.csv.");
	        writer = new BufferedWriter(new FileWriter(file));
	       
			for (Pedidos p : ) {

	            String text = p.getCodPedido() + "," + p.getDtCompra() + "," + p.getStatus() + "\n";

	            writer.write(text);
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    finally {

	        writer.flush();
	        writer.close();
	    } 
	}*/
	
}
