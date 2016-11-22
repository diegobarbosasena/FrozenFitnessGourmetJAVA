package br.com.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import br.com.DAO.PedidosDAO;
import br.com.ajudantes.Mascaras;
import br.com.model.Cliente;
import br.com.model.Pedidos;
import br.com.model.Status;
import br.com.view.Janelas;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
	@FXML private Button btnEditPedido;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Mascaras.mascaraNumeroInteiro(txtPedido);
		
		preencherPedidos();
		
		txtPedido.textProperty().addListener(a -> {
			if(!txtPedido.getText().isEmpty())
				buscarPedido();
			else
				preencherPedidos();
		});
	}
	
	private void buscarPedido() {
		
		List<Pedidos> lstPediFilt = PedidosDAO.filtrarPedidos(Integer.parseInt(txtPedido.getText()));
			
		if (lstPediFilt.isEmpty()){
				
			PopUpController erro = new PopUpController("ERRO", "Nenhum registro encontrado!", "OK");
			Janelas j = new Janelas();
			j.abrirPopup("PopUp.fxml", new Stage(), "Pedidos", false, erro);
				
			txtPedido.clear();
		}
		else{
			tvPedidos.getItems().clear();
			tvPedidos.getItems().addAll(lstPediFilt);
		}
	}
	
	private void preencherPedidos(){
		
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
		
		List<Pedidos> lstPedido = PedidosDAO.selecionarTodosPedidos();
		
		tvPedidos.getItems().clear();
		tvPedidos.getItems().addAll(lstPedido);
	}
	
}
