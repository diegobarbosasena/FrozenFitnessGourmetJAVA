package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.view.Janelas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Tab;
import javafx.scene.control.Button;

public class PedidoTelefoneController implements Initializable{
	
	@FXML private AnchorPane anpPedidoTelefone;
	@FXML private TabPane tbpPedidoTel;
	@FXML private Tab tabCadClien;
	@FXML private Label lblECliente;
	@FXML private Label lblClienteFisico;
	@FXML private Label lblNomeFisico;
	@FXML private Label lblCpfFisico;
	@FXML private Label lblDtNasc;
	@FXML private Label lblPeso;
	@FXML private Label lblAltura;
	@FXML private Label lblTelFisico;
	@FXML private Label lblCelFisico;
	@FXML private Label lblEmailFisico;
	@FXML private Label lblSexo;
	@FXML private Label lblClienteJuridico;
	@FXML private Label lblTelPrincJuridico;
	@FXML private Label lblTelSecuJuridico;
	@FXML private Tab tabCadPedi;
	@FXML private Button btnAddEndereco;
	@FXML private Button btnCadastrar;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnAddEndereco.setOnAction(c -> cadastrarEndereco());
		
	}

	private void cadastrarEndereco() {
	
		EnderecoController e = new EnderecoController();
		
		Janelas janela = new Janelas();
		janela.abrirJanela("Endereco.fxml", new Stage(), "Endereco", false, e);
		
	}

}
