package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.model.Pedidos;
import br.com.model.Status;
import br.com.model.TipoVeiculo;
import br.com.model.Transportadora;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AcompanhamentoController implements Initializable{
	
	@FXML private AnchorPane anpAcompanhamento;
	
	@FXML private TabPane tpAcomp;
	@FXML private Tab tabVisuaAcomp;
	@FXML private Tab tabEditAcomp;
	
	@FXML private TextField txtPediAcomp;
	@FXML private TextField txtCodPediAcomp;
	@FXML private TextField txtNomeClienAcomp;
	
	@FXML private TableView <Pedidos> tvPedidosAcompa;
	@FXML private TableColumn <Pedidos, String> tcCodPedAcomp;
	@FXML private TableColumn <Pedidos, String> tcClientePedAcomp;
	@FXML private TableColumn <Pedidos, String> tcStatusAcomp;
	@FXML private TableColumn <Pedidos, Transportadora> tcTranspAcomp;
	@FXML private TableColumn <Pedidos, TipoVeiculo> tcVeiculoAcomp;
	
	@FXML private Label lblCodPed;
	@FXML private Label lblNomeClien;
	@FXML private Label lblStatus;
	@FXML private Label lblTransp;
	@FXML private Label lblVeículo;
	
	@FXML private ComboBox <Status> cboStatus;
	@FXML private ComboBox <Transportadora> cboTransp;
	@FXML private ComboBox <TipoVeiculo> cboVeiculo;
	
	@FXML private Button btnBuscarPediAcom;
	@FXML private Button btnEditarPediAcom;
	@FXML private Button btnAtualizarSatus;
	@FXML private Button btnCancelarSatus;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		popularComboBoxStatus();
		btnAtualizarSatus.setOnAction(c -> teste());
		
		btnEditarPediAcom.setOnAction(g -> alterar());
	} 
	
	public void teste(){
		
		if(cboStatus.getSelectionModel().getSelectedItem().getStatusPedido().equals("Enviado para a Transportadora")){
			
			cboTransp.setDisable(false);
			cboVeiculo.setDisable(false);
		}
		else{
			cboTransp.setDisable(true);
			cboVeiculo.setDisable(true);
		}
	}
	
	public void alterar(){
		
		tpAcomp.getSelectionModel().select(1);
		
	}
	
	public void popularComboBoxStatus() {
		
		cboStatus.getItems().clear();
		cboStatus.getItems().addAll(Status.selecionarTodosStatus());
		
	}
	
	



	
	

	

}
