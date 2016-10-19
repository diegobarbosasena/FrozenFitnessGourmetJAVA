package br.com.controller;

import br.com.model.Pedidos;
import br.com.model.Status;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.TabPane;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.layout.AnchorPane;

import javafx.scene.control.Tab;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class AcompanhamentoController {
	
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
	
	@FXML private Label lblCodPed;
	@FXML private Label lblNomeClien;
	@FXML private Label lblStatus;
	
	@FXML private ComboBox <Status> cboStatus;
	
	@FXML private Button btnBuscarPediAcom;
	@FXML private Button btnEditarStatus;
	@FXML private Button btnAtualizarSatus;
	@FXML private Button btnCancelarSatus;

}
