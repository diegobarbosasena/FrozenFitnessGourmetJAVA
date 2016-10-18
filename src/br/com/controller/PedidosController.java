package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.model.Pedido;
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
	
	@FXML private TableView <Pedido> tvPedidos;
	
	@FXML private TableColumn <Pedido, String> tcCodPed;
	@FXML private TableColumn <Pedido, String> tcTipoPed;
	@FXML private TableColumn <Pedido, String> tcDtComp;
	@FXML private TableColumn <Pedido, String> tcDtEntr;
	@FXML private TableColumn <Pedido, String> tcClien;
	@FXML private TableColumn <Pedido, String> tcStatus;
	
	@FXML private TextField txtPedido;
	
	@FXML private Button btnBuscarPedido;
	@FXML private Button btnEditPedido;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preencherPedidos();
	}
	
	private void preencherPedidos(){
		
		tcCodPed.setCellValueFactory(new PropertyValueFactory<Pedido, String>("codPedido"));
		tcTipoPed.setCellValueFactory(new PropertyValueFactory<Pedido, String>("tipoPedido"));
		tcDtEntr.setCellValueFactory(new PropertyValueFactory<Pedido, String>("dtEntrega"));
		tcDtComp.setCellValueFactory(new PropertyValueFactory<Pedido, String>("dtCompra"));
		
		List<Pedido> lstPedido = Pedido.selecionarTodos();
		tvPedidos.getItems().clear();
		tvPedidos.getItems().addAll(lstPedido);
	}

}
