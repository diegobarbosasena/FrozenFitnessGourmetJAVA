package br.com.controller;

import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ajudantes.Mascaras;
import br.com.ajudantes.MySqlConexao;
import br.com.dao.PedidosDAO;
import br.com.model.Cliente;
import br.com.model.Pedidos;
import br.com.model.Status;
import br.com.view.Alerta;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class PedidosController implements Initializable{

	@FXML private AnchorPane anpPedidos;
	@FXML private TableView <Pedidos> tvPedidos;
	@FXML private TableColumn <Pedidos, String> tcCodPed;
	@FXML private TableColumn <Pedidos, String> tcTipoPed;
	@FXML private TableColumn <Pedidos, String> tcDtComp;
	@FXML private TableColumn <Pedidos, String> tcDtEntr;
	@FXML private TableColumn <Pedidos, Cliente> tcClien;
	@FXML private TableColumn <Pedidos, Status> tcStatus;
	@FXML private TableColumn <Pedidos, String> tcPreco;

	@FXML private TextField txtPedido;
	@FXML private Button btnAtualizarPedido;
	@FXML private Label lblFiltroStatusPedi;
	@FXML private RadioButton rbtTodosPedi;
	@FXML private RadioButton rbtAgradPagPedi;
	@FXML private RadioButton rbtAguarSepaPedi;
	@FXML private RadioButton rbtPratoProduPedi;
	@FXML private RadioButton rbtEnviadoTranspPedi;
	@FXML private RadioButton rbtProdTransportePedi;
	@FXML private RadioButton rbtEntreguePedi;
	@FXML private Button btnRelaPedi;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Mascaras.mascaraNumeroInteiro(txtPedido);

		preencherPedidos();
		buttonRadio();

		btnRelaPedi.setOnAction(r -> gerarRelatorioPedido());

		btnAtualizarPedido.setOnAction(a -> preencherPedidos());
		btnAtualizarPedido.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				preencherPedidos();
			}
		});

		txtPedido.textProperty().addListener(a -> {
			if(!txtPedido.getText().isEmpty())
				buscarPedido();
			else
				preencherPedidos();
		});	
	}

	private void buttonRadio() {

		final ToggleGroup tgFiltarPedi = new ToggleGroup();

		rbtTodosPedi.setToggleGroup(tgFiltarPedi);
		rbtAgradPagPedi.setToggleGroup(tgFiltarPedi);
		rbtAguarSepaPedi.setToggleGroup(tgFiltarPedi);
		rbtPratoProduPedi.setToggleGroup(tgFiltarPedi);
		rbtEnviadoTranspPedi.setToggleGroup(tgFiltarPedi);
		rbtProdTransportePedi.setToggleGroup(tgFiltarPedi);
		rbtEntreguePedi.setToggleGroup(tgFiltarPedi);

		rbtTodosPedi.setOnAction(c -> preencherPedidos());
		rbtAgradPagPedi.setOnAction(c -> preencherPedidos());
		rbtAguarSepaPedi.setOnAction(c -> preencherPedidos());
		rbtPratoProduPedi.setOnAction(c -> preencherPedidos());
		rbtEnviadoTranspPedi.setOnAction(c -> preencherPedidos());
		rbtProdTransportePedi.setOnAction(c -> preencherPedidos());
		rbtEntreguePedi.setOnAction(c -> preencherPedidos());

	}

	private void buscarPedido() {

		List<Pedidos> lstPediFilt = PedidosDAO.filtrarPedidos(Integer.parseInt(txtPedido.getText()));

		if (lstPediFilt.isEmpty()){

			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaWarning("Transportadora", "ERRO", "Nenhum registro encontrado!");

			txtPedido.clear();
		}
		else{
			tvPedidos.getItems().clear();
			tvPedidos.getItems().addAll(lstPediFilt);
		}
	}

	public void preencherPedidos(){

		tcCodPed.setCellValueFactory(new PropertyValueFactory<Pedidos, String>("codPedido"));
		tcTipoPed.setCellValueFactory(new PropertyValueFactory<Pedidos, String>("tipoPedido"));

		tcDtEntr.setCellValueFactory( 

				dtEntrega -> {
					SimpleStringProperty propriedade = new SimpleStringProperty();
					DateFormat dF = new SimpleDateFormat("dd-MM-yyyy");
					propriedade.setValue(dF.format(dtEntrega.getValue().getDtEntrega()));
					return propriedade;
				});

		tcDtComp.setCellValueFactory( 
				dtCompra -> {
					SimpleStringProperty propriedade = new SimpleStringProperty();
					DateFormat dF = new SimpleDateFormat("dd-MM-yyyy");
					propriedade.setValue(dF.format(dtCompra.getValue().getDtCompra()));
					return propriedade;
				});

		tcClien.setCellValueFactory(new PropertyValueFactory<Pedidos, Cliente>("cliente"));
		tcStatus.setCellValueFactory(new PropertyValueFactory<Pedidos, Status>("status"));
		tcPreco.setCellValueFactory(new PropertyValueFactory<Pedidos, String>("total"));

		List<Pedidos> lstPedido2 = PedidosDAO.selecionarTodosPedidos();

		tvPedidos.getItems().clear();
		tvPedidos.getItems().addAll(lstPedido2);


		if (rbtTodosPedi.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.selecionarTodosPedidos();	
			tvPedidos.getItems().clear();
			tvPedidos.getItems().addAll(lstPedidos);		
		}

		if (rbtAgradPagPedi.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.filtrarPedidosStatus(1);	
			tvPedidos.getItems().clear();
			tvPedidos.getItems().addAll(lstPedidos);		
		}

		if (rbtAguarSepaPedi.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.filtrarPedidosStatus(2);
			tvPedidos.getItems().clear();
			tvPedidos.getItems().addAll(lstPedidos);		
		}

		if (rbtPratoProduPedi.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.filtrarPedidosStatus(3);
			tvPedidos.getItems().clear();
			tvPedidos.getItems().addAll(lstPedidos);		
		}

		if (rbtEnviadoTranspPedi.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.filtrarPedidosStatus(4);
			tvPedidos.getItems().clear();
			tvPedidos.getItems().addAll(lstPedidos);		
		}

		if (rbtProdTransportePedi.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.filtrarPedidosStatus(5);
			tvPedidos.getItems().clear();
			tvPedidos.getItems().addAll(lstPedidos);		
		}

		if (rbtEntreguePedi.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.filtrarPedidosStatus(6);
			tvPedidos.getItems().clear();
			tvPedidos.getItems().addAll(lstPedidos);		
		}
	}

	public void gerarRelatorioPedido(){

		try {
			Connection c = MySqlConexao.ConectarDb();

			HashMap<String, Object> parametros = new HashMap<String, Object>();

			parametros.put("titulo", "Relatorio de Pedidos");
			parametros.put("total", "R$ " + PedidosDAO.somarTodosPedidos());
			parametros.put("imagem_logo", "src/br/com/imagens/logo.PNG");

			JasperPrint jp = JasperFillManager.fillReport("src/br/com/relatorios/relatorio_pedidos.jasper", parametros, c);			
			JasperViewer jw = new JasperViewer(jp , false);

			if (jw != null)
				jw.setVisible(true);

		} catch (Exception e) {
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Relatorio", "ERRO", "Erro ao gerar relatorio!");
		}
	}


}
