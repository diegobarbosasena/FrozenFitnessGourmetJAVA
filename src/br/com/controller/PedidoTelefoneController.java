package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.ajudantes.Mascaras;
import br.com.model.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;

import javafx.scene.control.ComboBox;

import javafx.scene.layout.AnchorPane;

import javafx.scene.control.RadioButton;

import javafx.scene.control.DatePicker;

public class PedidoTelefoneController implements Initializable {
	
	@FXML private AnchorPane anpPedidosTelefone;
	@FXML private Label lblECliente;
	@FXML private RadioButton rbSim;
	@FXML private RadioButton rbNao;
	@FXML private Label lblListaCliente;
	@FXML private ComboBox<Cliente> cboCliente;
	@FXML private Label lblNomeClien;
	@FXML private TextField txtNomeClien;
	@FXML private Label lblCpfClien;
	@FXML private TextField txtCpfClien;
	@FXML private Label lblDatNacClien;
	@FXML private DatePicker dpDtNasc;
	@FXML private Label lblPeso;
	@FXML private TextField txtPeso;
	@FXML private Label lblAltura;
	@FXML private TextField txtAltura;
	@FXML private Label lblTelefone;
	@FXML private TextField txtTel;
	@FXML private Label lblCel;
	@FXML private TextField txtCel;
	@FXML private Label lblEmail;
	@FXML private TextField txtEmail;
	@FXML private Label lblSexo;
	@FXML private RadioButton rbSexoM;
	@FXML private RadioButton rbSexoF;
	@FXML private Label lblCpfDoClien;
	@FXML private ComboBox<?> cboCpfClien;
	@FXML private Label lblPrato;
	@FXML private ComboBox<?> cboPrato;
	@FXML private Label lblQnt;
	@FXML private ComboBox<?> cboQnt;
	@FXML private Button btnAdicionar;
	@FXML private Button btnExcluir;
	@FXML private Label lblItens;
	@FXML private ListView<?> lsvItensPedido;
	@FXML private Button btnFinalizar;
	@FXML private Button btnCancelar;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Mascaras.pesoMask(txtPeso);
		Mascaras.alturaMask(txtAltura);
		
		grupoRadioButton();	
		
		cboCliente.getItems().clear();
		cboCliente.getItems().addAll(Cliente.selecionarTodosClientes());
	}

	public void grupoRadioButton() {
		final ToggleGroup tgSN = new ToggleGroup();
		final ToggleGroup tgSx = new ToggleGroup();
		
		rbSim.setToggleGroup(tgSN);
		rbNao.setToggleGroup(tgSN);
		
		rbSexoM.setToggleGroup(tgSx);
		rbSexoF.setToggleGroup(tgSx);
	}

}
