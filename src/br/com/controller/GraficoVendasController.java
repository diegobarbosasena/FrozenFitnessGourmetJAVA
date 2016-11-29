package br.com.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;

import br.com.ajudantes.MySqlConexao;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
		brcGrafVendas.setTitle("Gráfico de Vendas");
		
		btnRelatorio.setOnAction(r -> gerarRelatorio());
	}
	
	private void radioButtonGroup() {
		final ToggleGroup tgFiltrar = new ToggleGroup();
		
		rbMensal.setToggleGroup(tgFiltrar);
		rbPeriodo.setToggleGroup(tgFiltrar);
		rbSemanal.setToggleGroup(tgFiltrar);
		rbTrimestral.setToggleGroup(tgFiltrar);
	}
	
	private void preencherGraficoSemanal() {
		
		limpaData();
		desabilitaAbilitaData(true);
		
		XYChart.Data<String, Number> perdaPeso = new XYChart.Data<String, Number>("Perda de Peso", 18.156);	
		XYChart.Data<String, Number> ganhoMassa = new XYChart.Data<String, Number>("Ganho de Massa", 9.115);
		XYChart.Data<String, Number> forcaResis = new XYChart.Data<String, Number>("Força e Resistencia", 17.141);
		
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
		
		limpaData();
		desabilitaAbilitaData(true);
		
		XYChart.Data<String, Number> perdaPeso = new XYChart.Data<String, Number>("Perda de Peso", 500.100);
		XYChart.Data<String, Number> ganhoMassa = new XYChart.Data<String, Number>("Ganho de Massa", 100.120);
		XYChart.Data<String, Number> forcaResis = new XYChart.Data<String, Number>("Força e Resistencia", 315.350);
		
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
		
		limpaData();
		desabilitaAbilitaData(true);
		
		XYChart.Data<String, Number> perdaPeso = new XYChart.Data<String, Number>("Perda de Peso", 150.102);
		XYChart.Data<String, Number> ganhoMassa = new XYChart.Data<String, Number>("Ganho de Massa", 95.530);
		XYChart.Data<String, Number> forcaResis = new XYChart.Data<String, Number>("Força e Resistencia", 110.120);
		
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

	private void desabilitaAbilitaData(boolean falso) {
		
		dtpInicio.setDisable(falso);
		dtpFim.setDisable(falso);
		lblDtInicial.setDisable(falso);
		lblDtFim.setDisable(falso);
	}
	
	private void limpaData(){
		dtpInicio.setValue(null);
		dtpFim.setValue(null);
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
	public void gerarRelatorio(){
		
		try {
			Connection c = MySqlConexao.ConectarDb();
			//HashMap parametro = new HashMap();
			JasperPrint jp = JasperFillManager.fillReport("src/br/com/relatorios/relatorio_semanal.jasper", new HashMap<>(), c);
			JasperViewer jw = new JasperViewer(jp);
			jw.setVisible(true);
			
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
