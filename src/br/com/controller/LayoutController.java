package br.com.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.view.Alerta;
import br.com.view.Janelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LayoutController implements Initializable {
	@FXML
	private Label lblUsuario;
	@FXML
	private Label lblHora;
	@FXML
	private Label lblData;
	@FXML
	private Button btnSair;
	@FXML
	private TextField txtBuscaPedido;
	@FXML
	private Button btnBuscaPedido;
	@FXML
	private Button btnEditarPedido;
	@FXML
	private Button btnExcluirPedido;
	@FXML
	private TextField txtBuscaPedidoAcompa;
	@FXML
	private Button btnBuscaPedidoAcompa;
	@FXML
	private Button btnEditarPedidoAcomp;
	@FXML
	private Button btnExcluirPedidoAcomp;
	@FXML
	private DatePicker dpDataInicial;
	@FXML
	private DatePicker dpDataFinal;
	@FXML
	private Button btnFiltrar;
	@FXML
	private Label lblIni;
	@FXML
	private Label lblFim;
	@FXML
	private TextField txtBuscaTrans;
	@FXML
	private Button btnBuscaTrans;
	@FXML
	private Button btnNovaTransportadora;
	@FXML
	private Button btnEditarTrans;
	@FXML
	private Button btnExcluirTrans;

	// Event Listener on Button[#btnSair].onAction
	@FXML
	public void sair(ActionEvent event) {
		Janelas login = new Janelas();
		login.abrir("Login.fxml", new Stage(), "Login", false);
		
		Stage layout = (Stage)btnSair.getScene().getWindow();
		layout.close();
	}
	// Event Listener on Button[#btnBuscaPedido].onAction
	@FXML
	public void buscarPedido(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnEditarPedido].onAction
	@FXML
	public void editarPedido(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnExcluirPedido].onAction
	@FXML
	public void excluirPedido(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnBuscaPedidoAcompa].onAction
	@FXML
	public void buscarPedidoAcompa(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnEditarPedidoAcomp].onAction
	@FXML
	public void editarPedidoAcomp(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnExcluirPedidoAcomp].onAction
	@FXML
	public void excluirPedidoAcomp(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnFiltrar].onAction
	@FXML
	public void filtrarVendas(ActionEvent event) {
		System.out.println();
		System.out.println(dpDataInicial.getValue());
		System.out.println(dpDataFinal.getValue());
		
		lblIni.setText(dpDataInicial.getValue().toString());
		lblFim.setText(dpDataFinal.getValue().toString());
		
		dpDataInicial.setValue(null);
		dpDataFinal.setValue(null);
	}
	// Event Listener on Button[#btnBuscaTrans].onAction
	@FXML
	public void buscarTransportadora(ActionEvent event) {
		
		if(txtBuscaTrans.getText().isEmpty()){
			
			System.out.println();
			System.out.println("ERRO ao buscar transportadora");
			txtBuscaTrans.clear();
			
			Alerta e = new Alerta();
			e.alerta("ERRO", "Preencha o nome da transportadora!");
			
		}else{
			
			System.out.println();
			System.out.println("Buscou Transportadora");
			System.out.println(txtBuscaTrans.getText());
			
			txtBuscaTrans.clear();
		}
	}
	// Event Listener on Button[#btnNovaTransportadora].onAction
	@FXML
	public void novaTransportadora(ActionEvent event) {
		Janelas novaTrans = new Janelas();
		novaTrans.abrir("NovaTransportadora.fxml", new Stage(), "Cadastrar nova Transportadora", false);
	}
	// Event Listener on Button[#btnEditarTrans].onAction
	@FXML
	public void editarTrans(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnExcluirTrans].onAction
	@FXML
	public void excluirTrans(ActionEvent event) {
		// TODO Autogenerated
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		lblUsuario.setText(System.getProperty("user.name"));
		
		SimpleDateFormat h = new SimpleDateFormat("HH:mm");
		lblHora.setText(h.format(new Date()));
		
		SimpleDateFormat sdfd = new SimpleDateFormat("dd/MM/YYYY");
		lblData.setText(sdfd.format(new Date()));
		
		btnExcluirTrans.setDisable(false);
	}
}
