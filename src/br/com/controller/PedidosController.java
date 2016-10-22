package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.model.Pedidos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class PedidosController implements Initializable{
	
	@FXML private AnchorPane anpPedidos;
	
	@FXML private TableView <Pedidos> tvPedidos;
	
	@FXML private TableColumn <Pedidos, String> tcCodPed;
	@FXML private TableColumn <Pedidos, String> tcTipoPed;
	@FXML private TableColumn <Pedidos, String> tcDtComp;
	@FXML private TableColumn <Pedidos, String> tcDtEntr;
	@FXML private TableColumn <Pedidos, String> tcClien;
	@FXML private TableColumn <Pedidos, String> tcStatus;
	
	@FXML private TextField txtPedido;
	
	@FXML private Button btnBuscarPedido;
	@FXML private Button btnEditPedido;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preencherPedidos();
	}
	
	private void preencherPedidos(){
		
		tcCodPed.setCellValueFactory(new PropertyValueFactory<Pedidos, String>("codPedido"));
		tcTipoPed.setCellValueFactory(new PropertyValueFactory<Pedidos, String>("tipoPedido"));
		tcDtEntr.setCellValueFactory(new PropertyValueFactory<Pedidos, String>("dtEntrega"));
		tcDtComp.setCellValueFactory(new PropertyValueFactory<Pedidos, String>("dtCompra"));
		
		List<Pedidos> lstPedido = Pedidos.selecionarTodos();
		
		tvPedidos.getItems().clear();
		tvPedidos.getItems().addAll(lstPedido);
	}

}
