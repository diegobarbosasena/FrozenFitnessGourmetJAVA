package br.com.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ResourceBundle;

import br.com.DAO.PedidosDAO;
import br.com.ajudantes.MySqlConexao;
import br.com.view.Alerta;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
	@FXML private TabPane tbpGraficos;
	@FXML private Tab tabVendas;
	@FXML private Tab tabFaturamento;
	@FXML private RadioButton rbSemanal1;
	@FXML private RadioButton rbMensal1;
	@FXML private RadioButton rbTrimestral1;
	@FXML private RadioButton rbPeriodo1;
	@FXML private Label lblDtInicial1;
	@FXML private Label lblDtFim1;
	@FXML private DatePicker dtpInicio1;
	@FXML private DatePicker dtpFim1;
	@FXML private Button btnRelatorio1;
	@FXML private LineChart<String, Number> lcFaturamento;
	@FXML private CategoryAxis xEixoLC;
	@FXML private NumberAxis yEixoLC;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnRelatorio1.setOnAction(d -> populateLineChart());
		
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
		
		XYChart.Data<String, Number> perdaPeso = new XYChart.Data<String, Number>("Perda de Peso", 150.10);
		XYChart.Data<String, Number> ganhoMassa = new XYChart.Data<String, Number>("Ganho de Massa", 95.53);
		XYChart.Data<String, Number> forcaResis = new XYChart.Data<String, Number>("Força e Resistencia", 110.12);
		
		System.out.println(forcaResis.getNode());
		System.out.println(ganhoMassa.getNode());
		System.out.println(perdaPeso.getNode());
		
		XYChart.Series<String, Number> categorias = new XYChart.Series<String, Number>();
		categorias.setName("Categorias");
		categorias.getData().addAll(perdaPeso, ganhoMassa, forcaResis);	
		
		XYChart.Data<String, Number> total = new XYChart.Data<String, Number>("Total", 355.752);
		
		XYChart.Series<String, Number> totalCatego = new XYChart.Series<String, Number>();
		totalCatego.setName("Total");
		totalCatego.getData().add(total);
		
		
		
		displayLabelForData(forcaResis);
		displayLabelForData(ganhoMassa);
		displayLabelForData(perdaPeso);
		displayLabelForData(total);

		brcGrafVendas.getData().clear();
		brcGrafVendas.getData().add(categorias);
		brcGrafVendas.getData().add(totalCatego);
		
		
	}
	
	private void displayLabelForData(XYChart.Data<String, Number> data) {
		final Node node = data.getNode();
		final Text dataText = new Text(data.getYValue() + "");

		
		
		node.parentProperty().addListener(new ChangeListener<Parent>() {
			@Override public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
				Group parentGroup = (Group) parent;
				parentGroup.getChildren().add(dataText);
			}
		});

		node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
			@Override public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
				dataText.setLayoutX(
						Math.round(
								bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2
								)
						);
				dataText.setLayoutY(
						Math.round(
								bounds.getMinY() - dataText.prefHeight(-1) * 0.5
								)
						);
			}
		});
				
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
			
			parametros.put("titulo", "Relatório de Pedidos");
			parametros.put("total_pedido", "R$ " + PedidosDAO.somarTodosPedidos(1));
			parametros.put("cod_pedido", 3);
			parametros.put("imagem_logo", "src/br/com/view/imagens/logo.png");

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
	
	private void populateLineChart(){
		
		lcFaturamento.setCreateSymbols(false);
		lcFaturamento.setAnimated(false);
	
		
		xEixoLC.setLabel("ano");
		
		//lcFaturamento =  new LineChart<>(xEixoLC, yEixoLC);
	
		XYChart.Series series = new XYChart.Series();
		series.setName("faturamento");
		
		lcFaturamento.setTitle("Gráfico de faturamento");
		
		series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 14));
        series.getData().add(new XYChart.Data("Mar", 15));
        series.getData().add(new XYChart.Data("Apr", 24));
        series.getData().add(new XYChart.Data("May", 34));
        series.getData().add(new XYChart.Data("Jun", 36));
        series.getData().add(new XYChart.Data("Jul", 22));
        series.getData().add(new XYChart.Data("Aug", 45));
        series.getData().add(new XYChart.Data("Sep", 43));
        series.getData().add(new XYChart.Data("Oct", 17));
        series.getData().add(new XYChart.Data("Nov", 29));
        series.getData().add(new XYChart.Data("Dec", 25));
		
		lcFaturamento.getData().clear();
		lcFaturamento.getData().add(series);
		
		
	}
}
