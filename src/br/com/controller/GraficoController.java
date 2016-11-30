package br.com.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ResourceBundle;

import br.com.ajudantes.MySqlConexao;
import br.com.view.Alerta;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.chart.LineChart;

public class GraficoController implements Initializable {
	
	@FXML private AnchorPane anpGrafVendas;
	@FXML private RadioButton rbSemanal;
	@FXML private RadioButton rbMensal;
	@FXML private RadioButton rbTrimestral;
	@FXML private RadioButton rbPeriodo;
	@FXML private Label lblDtInicial;
	@FXML private Label lblDtFim;
	@FXML private DatePicker dtpInicio;
	@FXML private DatePicker dtpFim;
	@FXML private BarChart<String, Number>brcGrafVendas;
	@FXML private NumberAxis yEixo;
	@FXML private CategoryAxis xEixo;
	
	@FXML private Button btnRelatorio;
	@FXML TabPane tbpGraficos;
	@FXML Tab tabVendas;
	@FXML Tab tabFaturamento;
	@FXML RadioButton rbSemanal1;
	@FXML RadioButton rbMensal1;
	@FXML RadioButton rbTrimestral1;
	@FXML RadioButton rbPeriodo1;
	@FXML Label lblDtInicial1;
	@FXML Label lblDtFim1;
	@FXML DatePicker dtpInicio1;
	@FXML DatePicker dtpFim1;
	@FXML Button btnRelatorio1;
	@FXML LineChart <String, Number>lcFaturamento;
	@FXML CategoryAxis xEixoLC;
	@FXML NumberAxis yEixoLC;
	
	
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
		brcGrafVendas.setStyle(".default-color0.chart-bar { -fx-bar-fill: #00B348 }");
		
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
		
		brcGrafVendas.setTitle("foi sim");

		XYChart.Series<String, Number> estados = new XYChart.Series<>();
		
		try {
			Connection c = MySqlConexao.ConectarDb();
			
			String sqlSelectEstado = "SELECT "
					+ "pedido.*, cliente.*, clie_ende.*, endereco.*, cidade.*, estado.* "
					+ "FROM tblPedido pedido INNER JOIN tblCliente cliente "
					+ "ON(pedido.codCliente = cliente.codCliente) "
					+ "INNER JOIN tblClienteEnd clie_ende "
					+ "ON(cliente.codCliente = clie_ende.codCliente) "
					+ "INNER JOIN tblEndereco endereco "
					+ "ON(clie_ende.codEndereco = endereco.codEndereco) "
					+ "INNER JOIN tblCidade cidade "
					+ "ON(endereco.codCidade = cidade.codCidade) "
					+ "INNER JOIN tblEstado estado "
					+ "ON(cidade.codEstado = estado.codEstado)" ;

			ResultSet rs = c.createStatement().executeQuery(sqlSelectEstado);

			while(rs.next()){
				
				estados.getData().addAll(new XYChart.Data(rs.getString("nomeEstado"), rs.getInt("total")));
			}
			estados.setName("Estados");
			
			brcGrafVendas.getData().clear();
			brcGrafVendas.getData().add(estados);
			
		} catch (Exception e) {
		
		}
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

	public void gerarRelatorio(){
		
		try {
			Connection c = MySqlConexao.ConectarDb();
	
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("titulo", "relatorio mensal");
			parametros.put("total_pedido", 200.32);
			parametros.put("cod_pedido", 2);

			JasperPrint jp = JasperFillManager.fillReport("src/br/com/relatorios/relatorio.jasper", parametros, c);			
			JasperViewer jw = new JasperViewer(jp , false);
			
			if (jw != null)
				jw.setVisible(true);
			
		} catch (Exception e) {
			System.out.println(e);
			
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Relatório", "ERRO", "Erro ao gerar relatório!");
		}
	}
}
