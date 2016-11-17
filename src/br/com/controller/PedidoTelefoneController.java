package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.DAO.CidadeDAO;
import br.com.DAO.ClienteDAO;
import br.com.DAO.EstadoDAO;
import br.com.ajudantes.Mascaras;
import br.com.model.Cidade;
import br.com.model.Cliente;
import br.com.model.Estado;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class PedidoTelefoneController implements Initializable{
	
	@FXML private AnchorPane anpPedidosTelefone;
	@FXML private Label lblECliente;
	@FXML private RadioButton rbSim;
	@FXML private RadioButton rbNao;
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
	@FXML private Label lblEndereco;
	@FXML private TextField txtEndereco;
	@FXML private Label lblNro;
	@FXML private TextField txtNro;
	@FXML private Label lblCep;
	@FXML private TextField txtCep;
	@FXML private Label lblBairro;
	@FXML private TextField txtBairro;
	@FXML private Label lblComple;
	@FXML private TextField txtComple;
	@FXML private Label lblEstado;
	@FXML private ComboBox <Estado> cboEstado;
	@FXML private Label lblCidade;
	@FXML private ComboBox <Cidade>cboCidade;
	@FXML private Label lblCpfDoClien;
	@FXML private ComboBox <Cliente>cboCpfClien;
	@FXML private Label lblPrato;
	@FXML private ComboBox <?>cboPrato;
	@FXML private Label lblQnt;
	@FXML private ComboBox <?>cboQnt;
	@FXML private Button btnAdicionar;
	@FXML private Button btnExcluir;
	@FXML private Label lblItens;
	@FXML private ListView <?>lsvItensPedido;
	@FXML private Button btnFinalizar;
	@FXML private Button btnCancelar;
	@FXML private Button btnCadastrar;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Mascaras.mascaraAltura(txtAltura);
		Mascaras.mascaraPeso(txtPeso);
		Mascaras.mascaraEmail(txtEmail);
		Mascaras.mascaraTelefone(txtTel);
		Mascaras.mascaraCelular(txtCel);
		Mascaras.mascaraCPF(txtCpfClien);
		
		popularComboBox();
		
		grupoRadioButton();	
		
		rbSim.setOnAction(d -> clienteCadastrado());
		rbNao.setOnAction(n -> clienteNaoCadastradrado());	
	}
	
	private void clienteCadastrado() {
		ativaDesativa(true);
		ativaDesativaVendas(false);
	}

	private void clienteNaoCadastradrado() {
		ativaDesativa(false);
		ativaDesativaVendas(true);
	}

	public void grupoRadioButton() {
		final ToggleGroup tgSN = new ToggleGroup();
		final ToggleGroup tgSx = new ToggleGroup();
		
		rbSim.setToggleGroup(tgSN);
		rbNao.setToggleGroup(tgSN);
		
		rbSexoM.setToggleGroup(tgSx);
		rbSexoF.setToggleGroup(tgSx);	
	}
	
	private void ativaDesativa(boolean ativarDesativar){
		lblNomeClien.setDisable(ativarDesativar);
		txtNomeClien.setDisable(ativarDesativar);
		lblCpfClien.setDisable(ativarDesativar);
		txtCpfClien.setDisable(ativarDesativar);
		lblDatNacClien.setDisable(ativarDesativar);
		dpDtNasc.setDisable(ativarDesativar);
		lblPeso.setDisable(ativarDesativar);
		txtPeso.setDisable(ativarDesativar);
		lblAltura.setDisable(ativarDesativar);
		txtAltura.setDisable(ativarDesativar);
		lblTelefone.setDisable(ativarDesativar);
		txtTel.setDisable(ativarDesativar);
		lblCel.setDisable(ativarDesativar);
		txtCel.setDisable(ativarDesativar);
		lblEmail.setDisable(ativarDesativar);
		txtEmail.setDisable(ativarDesativar);
		lblSexo.setDisable(ativarDesativar);
		rbSexoF.setDisable(ativarDesativar);
		rbSexoM.setDisable(ativarDesativar);
		lblEndereco.setDisable(ativarDesativar);
		txtEndereco.setDisable(ativarDesativar);
		lblNro.setDisable(ativarDesativar);
		txtNro.setDisable(ativarDesativar);
		lblCep.setDisable(ativarDesativar);
		txtCep.setDisable(ativarDesativar);
		lblBairro.setDisable(ativarDesativar);
		txtBairro.setDisable(ativarDesativar);
		lblComple.setDisable(ativarDesativar);
		txtComple.setDisable(ativarDesativar);
		lblEstado.setDisable(ativarDesativar);
		cboEstado.setDisable(ativarDesativar);
		lblCidade.setDisable(ativarDesativar);
		cboCidade.setDisable(ativarDesativar);
		btnCadastrar.setDisable(ativarDesativar);
		
	}
	
	private void ativaDesativaVendas(boolean adv){
		lblCpfDoClien.setDisable(adv);
		cboCpfClien.setDisable(adv);
		lblPrato.setDisable(adv);
		cboPrato.setDisable(adv);
		lblQnt.setDisable(adv);
		cboQnt.setDisable(adv);
		btnAdicionar.setDisable(adv);
		btnExcluir.setDisable(adv);
		lblItens.setDisable(adv);
		lsvItensPedido.setDisable(adv);
	}
	
	public void popularComboBox() {
		
		cboCpfClien.getItems().clear();
		cboCpfClien.getItems().addAll(ClienteDAO.selecionarTodosClientes());
		
		cboCidade.getItems().clear();
		cboCidade.getItems().addAll(CidadeDAO.selecionarTodasCidades());
		
		cboEstado.getItems().clear();
		cboEstado.getItems().addAll(EstadoDAO.selecionarTodosEstados());
		
		cboEstado.valueProperty().addListener(new ChangeListener<Estado>() {
			@Override
			public void changed(ObservableValue<? extends Estado> observable, Estado oldValue, Estado newValue) {			
				cboCidade.getItems().clear();
				
				if(cboEstado.getSelectionModel().getSelectedItem() !=null){
					List<Cidade> uf = CidadeDAO.filtrarCidade(cboEstado.getSelectionModel().getSelectedItem().getUf());
				
					cboCidade.getItems().addAll(uf);
				}
			}
		});
	}
	
}
