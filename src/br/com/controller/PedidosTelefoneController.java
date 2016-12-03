package br.com.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import br.com.DAO.ClienteDAO;
import br.com.DAO.ClienteJuridicoDAO;
import br.com.DAO.EnderecoDAO;
import br.com.ajudantes.Mascaras;
import br.com.model.Cidade;
import br.com.model.Cliente;
import br.com.model.ClienteJuridico;
import br.com.model.Endereco;
import br.com.model.Estado;
import br.com.view.Alerta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class PedidosTelefoneController implements Initializable{
	
	@FXML private AnchorPane anpPedidoTelefone;
	@FXML private TabPane tbpPedidoTel;
	@FXML private Tab tabVisuClien;
	@FXML private Label lblClienteFisi;
	@FXML private TextField txtBuscaClienFisico;
	@FXML private Button btnAtualizaFisico;
	@FXML private Button btnNovoClienFisico;
	@FXML private Button btnEditarClienteFisico;
	@FXML private Button btnEcluirClienFisico;
	@FXML private Button btnAdicionarEndeFisico;
	
	@FXML private TableView <Cliente>tbwClienteFisico;
	@FXML private TableColumn <Cliente, String>tbcNomeFisi;
	@FXML private TableColumn <Cliente, String> tbcCpfFisi;
	@FXML private TableColumn <Cliente, String> tbcTelFisi;
	@FXML private TableColumn <Cliente, String> tbcCelFisi;
	@FXML private TableColumn <Cliente, String> tbcEmailFisi;
	@FXML private TableColumn <Cliente, String> tbcSexoFisi;
	@FXML private TableColumn <Cliente, String> tbcDtNascFisi;
	@FXML private TableColumn <Cliente, String> tbcCodClienteFisico;
	
	@FXML private Label lblClienteJuri;
	@FXML private TextField txtBuscaClienJuridico;
	@FXML private Button btnAtualizaJuridico;
	@FXML private Button btnNovoClienteJuridico;
	@FXML private Button btnEditarClienteJuri;
	@FXML private Button btnExcluirJuri;
	@FXML private Button btnAdicionarEndeJuri;
	
	@FXML private TableView <ClienteJuridico>tbwClienteJuridico;
	@FXML private TableColumn <ClienteJuridico ,String>tbcNomeJuri;
	@FXML private TableColumn <ClienteJuridico ,String> tbcRazaoJuri;
	@FXML private TableColumn <ClienteJuridico ,String> tbcTelPricJuri;
	@FXML private TableColumn <ClienteJuridico ,String> tbcTelContJuri;
	@FXML private TableColumn <ClienteJuridico ,String> tbcEmailPricJuri;
	@FXML private TableColumn <ClienteJuridico ,String> tbcEmailContaJuri;
	@FXML private TableColumn <ClienteJuridico ,String> tbcCodJuri;
	
	@FXML private TableView <Endereco>tbwEndereco;
	
	@FXML private TableColumn <Endereco, String>tbcLogradouro;
	@FXML private TableColumn <Endereco, String> tbcCep;
	@FXML private TableColumn <Endereco, String> tbcNro;
	@FXML private TableColumn <Endereco, String> tbcBairro;
	@FXML private TableColumn <Endereco, String> tbcComplemento;
	@FXML private TableColumn <Endereco, Cidade> tbcCidade;
	@FXML private TableColumn <Endereco, String> tbcEstado;
	
	@FXML private Button btnEditarEndereco;
	@FXML private Tab tabCadClien;
	@FXML private Label lblClienteFisico;
	@FXML private Label lblNomeFisico;
	@FXML private TextField txtNomeClienFisi;
	@FXML private Label lblCpfFisico;
	@FXML private TextField txtCpfClienFisi;
	@FXML private Label lblDtNasc;
	@FXML private TextField txtDtNascClienFisi;
	@FXML private Label lblPeso;
	@FXML private TextField txtPesoClienFisi;
	@FXML private Label lblAltura;
	@FXML private TextField txtAlturaClienFisi;
	@FXML private Label lblTelFisico;
	@FXML private TextField txtTelClienFisi;
	@FXML private Label lblCelFisico;
	@FXML private TextField txtCelClienFisi;
	@FXML private Label lblEmailFisico;
	@FXML private TextField txtEmailClienFisi;
	@FXML private Label lblSexo;
	@FXML private RadioButton rbSexoM;
	@FXML private RadioButton rbSexoF;
	@FXML private Label lblClienteJuridico;
	@FXML private Label lblNomeContato;
	@FXML private TextField txtNomeClienJuri;
	@FXML private Label lblRazaoClienteJuri;
	@FXML private TextField txtRazaoClienJuri;
	@FXML private Label lblCnpjClienJuri;
	@FXML private TextField txtCnpjClienJuri;
	@FXML private Label lblInscricao;
	@FXML private TextField txtInscricao;
	@FXML private Label lblTelPrincJuridico;
	@FXML private TextField txtTelPrinClienJuri;
	@FXML private Label lblTelSecuJuridico;
	@FXML private TextField txtTelContClienJuri;
	@FXML private Label lblEmailPrincJuridico;
	@FXML private TextField txtEmailPrinClienJuri;
	@FXML private Label lblEmailContJuridico;
	@FXML private TextField txtEmailContClienJuri;
	@FXML private Tab tabCadEnde;
	@FXML private Label lblCadasEnder;
	@FXML private TextField txtNomeClienteEnd;
	@FXML private Label lblLogradouro;
	@FXML private TextField txtLogradouro;
	@FXML private Label lblCep;
	@FXML private TextField txtCep;
	@FXML private Label lblNro;
	@FXML private TextField txtNro;
	@FXML private Label lblBairro;
	@FXML private TextField txtBairro;
	@FXML private Label lblComplemento;
	@FXML private TextField txtComplemento;
	@FXML private Label lblEstado;
	@FXML private ComboBox <Estado>cboEstado;
	@FXML private Label lblCidade;
	@FXML private ComboBox <Cidade>cboCidade;
	@FXML private Button btnCancelar;
	@FXML private Label lblNomeClienteEnde;
	@FXML private Label lblTotal;
	@FXML private Tab tabCadPedi;
	@FXML private Button btnCadastrarCliente;
	@FXML private Button btnCadastrarEndereco;
	@FXML private Button btnConcluidoPedTel;
	
	
	private boolean modo_edicao = false;
	
	String sexoSelecionado = "";
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Mascaras.mascaraCNPJ(txtCnpjClienJuri);
		Mascaras.mascaraAltura(txtAlturaClienFisi);
		Mascaras.mascaraCEP(txtCep);
		Mascaras.mascaraCelular(txtCelClienFisi);
		Mascaras.mascaraEmail(txtEmailClienFisi);
		Mascaras.mascaraEmail(txtEmailContClienJuri);
		Mascaras.mascaraEmail(txtEmailPrinClienJuri);
		Mascaras.mascaraTelefone(txtTelClienFisi);
		Mascaras.mascaraTelefone(txtTelContClienJuri);
		Mascaras.mascaraTelefone(txtTelPrinClienJuri);
		Mascaras.mascaraCPF(txtCpfClienFisi);
	
		btnAtualizaFisico.setOnAction(k -> preencherClienteFisico());
		btnAtualizaFisico.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.ENTER) {
		    	preencherClienteFisico();
		    	
		    }
		});
		
		btnAtualizaJuridico.setOnAction(g -> preencherClienteJuridico());
		btnAtualizaJuridico.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.ENTER) {
		    	preencherClienteJuridico();
		    	
		    }
		});
	
		tabCadClien.setDisable(true);
		tabCadEnde.setDisable(true);
		tabCadPedi.setDisable(true);
		
		preencherClienteFisico();
		preencherClienteJuridico();
		groupRadio();
		
		tbwClienteFisico.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Integer> c) {
				if(tbwClienteFisico.getSelectionModel().getSelectedIndices() != null){
					preencherEnderecoFisico();
				}
			}
		});
		
		tbwClienteJuridico.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Integer> c) {
				if(tbwClienteJuridico.getSelectionModel().getSelectedIndices() != null){
					//preencherEnderecoJuridico();
				}		
			}		
		});
		
		btnNovoClienFisico.setOnAction(c -> cadastrarFisico());
		
		btnNovoClienteJuridico.setOnAction(f -> cadastrarJuridico());
	}

	private void preencherClienteJuridico() {
		
		tbcCodJuri.setCellValueFactory(new PropertyValueFactory<ClienteJuridico, String>("codClienteJuridico"));
		tbcNomeJuri.setCellValueFactory(new PropertyValueFactory<ClienteJuridico, String>("nomeContato"));
		tbcRazaoJuri.setCellValueFactory(new PropertyValueFactory<ClienteJuridico, String>("razaoSocial"));
		tbcTelPricJuri.setCellValueFactory(new PropertyValueFactory<ClienteJuridico, String>("telefonePrincipal"));
		tbcTelContJuri.setCellValueFactory(new PropertyValueFactory<ClienteJuridico, String>("telefoneContato"));
		tbcEmailPricJuri.setCellValueFactory(new PropertyValueFactory<ClienteJuridico, String>("emailPrincipal"));
		tbcEmailContaJuri.setCellValueFactory(new PropertyValueFactory<ClienteJuridico, String>("emailContato"));
		
		List<ClienteJuridico> lstClieJuridico = ClienteJuridicoDAO.selecionarTodosClientesJuridicos();
		
		tbwClienteJuridico.getItems().clear();
		tbwClienteJuridico.getItems().addAll(lstClieJuridico);
		
	}

	private void preencherClienteFisico() {
		
		tbcCodClienteFisico.setCellValueFactory(new PropertyValueFactory<Cliente, String>("codCliente"));
		tbcNomeFisi.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nomeCliente"));
		tbcCpfFisi.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpfCliente"));
		
		tbcDtNascFisi.setCellValueFactory(
			dtNacs -> {
				SimpleStringProperty propriedade = new SimpleStringProperty();
				DateFormat dF = new SimpleDateFormat("dd-MM-yyyy");
				if(dtNacs.getValue().getDtNascCliente() != null){
				propriedade.setValue(dF.format(dtNacs.getValue().getDtNascCliente()));
				
				}
				return propriedade;
		});
		
		tbcTelFisi.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefoneCliente"));
		tbcCelFisi.setCellValueFactory(new PropertyValueFactory<Cliente, String>("celularCliente"));
		tbcEmailFisi.setCellValueFactory(new PropertyValueFactory<Cliente, String>("emailCliente"));
		tbcSexoFisi.setCellValueFactory(new PropertyValueFactory<Cliente, String>("sexo"));
		
		List<Cliente> lstClienteFisico = ClienteDAO.selecionarTodosClientes();
		
		tbwClienteFisico.getItems().clear();
		tbwClienteFisico.getItems().addAll(lstClienteFisico);	
	}
	
	
	private void cadastrarFisico() {
		
		tbpPedidoTel.getSelectionModel().select(1);
		tabCadClien.setDisable(false);
		tabVisuClien.setDisable(true);
		
		desabilitaFisico(false);
	
		btnCadastrarCliente.setText("Cadastrar");
		btnCadastrarCliente.setOnAction(f -> inserirFisico());
		
		
	}
	

	private void inserirFisico() {
		
		if(! modo_edicao){
		
		/*	if(validarCamposFisico(txtNomeClienFisi.getText(),txtDtNascClienFisi.getText(),txtTelClienFisi.getText(),txtCpfClienFisi.getText(),txtCelClienFisi.getText(),txtEmailClienFisi.getText(),txtPesoClienFisi.getText(),txtAlturaClienFisi.getText()))
			{
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Pedidos Telefone", "ERRO", "Preencha todos os campos!");	
			}
			else{*/
				
			Cliente cli = new Cliente();
			/*	
				if(rbSexoM.isSelected()){
					sexoSelecionado = "M";
				}
				if(rbSexoF.isSelected()){
					sexoSelecionado = "F";
				}
				
				Cliente cliente = new Cliente();
				
				cliente.setNomeCliente(txtNomeClienFisi.getText());
				cliente.setCpfCliente(txtCpfClienFisi.getText());
				cliente.setTelefoneCliente(txtTelClienFisi.getText());
				cliente.setCelularCliente(txtCelClienFisi.getText());
				cliente.setEmailCliente(txtEmailClienFisi.getText());
				cliente.setSexo(sexoSelecionado);
				
				
				Double peso = Double.parseDouble(txtPesoClienFisi.getText());
				Double altura = Double.parseDouble(txtAlturaClienFisi.getText());
			
				txtDtNascClienFisi.getUserData();
				
				
				cliente.setPeso(peso);
				cliente.setAltura(altura);
					
				
				System.out.println("data " +txtDtNascClienFisi.getUserData());
				System.out.println("altura parsada " +altura);
				
				*/
				
				
				cli.setAltura(Float.parseFloat("1.23"));
				cli.setCelularCliente("123");
				cli.setCpfCliente("123");
				
				cli.setEmailCliente("ca@cds.com");
				cli.setNomeCliente("nome cliente");
				cli.setPeso(Float.parseFloat("12.3"));
				cli.setSexo("M");
				cli.setTelefoneCliente("123");
				
				if(ClienteDAO.inserirCliente(cli)){
					System.out.println("CHEGOU");
					Alerta aletaInfo = new Alerta();
					aletaInfo.alertaInformation("Pedido telefone", "Sucesso", "Cliente cadastrado com sucesso!");
					
				}
			
			/*	
			}*/
				
			
			
			
			
		}
		
	}
	
	public boolean validarCamposFisico(String... camposFisico) {
		
		boolean preenchido = false;
		for(String item : camposFisico){
			if(item.isEmpty()){
				preenchido = true;
				break;
			}else{
				preenchido = false;
			}
		}
		return preenchido;
	}

	public void desabilitaFisico(boolean desabilitar) {
		lblClienteFisico.setDisable(desabilitar);
		lblNomeFisico.setDisable(desabilitar);
		txtNomeClienFisi.setDisable(desabilitar);
		lblTelFisico.setDisable(desabilitar);
		txtTelClienFisi.setDisable(desabilitar);
		lblCpfFisico.setDisable(desabilitar);
		txtCpfClienFisi.setDisable(desabilitar);
		lblCelFisico.setDisable(desabilitar);
		txtCelClienFisi.setDisable(desabilitar);
		lblDtNasc.setDisable(desabilitar);
		txtDtNascClienFisi.setDisable(desabilitar);
		lblEmailFisico.setDisable(desabilitar);
		txtEmailClienFisi.setDisable(desabilitar);
		lblPeso.setDisable(desabilitar);
		txtPesoClienFisi.setDisable(desabilitar);
		lblAltura.setDisable(desabilitar);
		txtAlturaClienFisi.setDisable(desabilitar);
		lblSexo.setDisable(desabilitar);
		rbSexoM.setDisable(desabilitar);
		rbSexoF.setDisable(desabilitar);
	}
	
	private void cadastrarJuridico(){
		
		
	}
	
	private void preencherEnderecoFisico(){
		
		Cliente cliente = tbwClienteFisico.getSelectionModel().getSelectedItem();
	
		tbcLogradouro.setCellValueFactory(new PropertyValueFactory<Endereco, String>("logradouro"));
		tbcCep.setCellValueFactory(new PropertyValueFactory<Endereco, String>("cep"));
		tbcNro.setCellValueFactory(new PropertyValueFactory<Endereco, String>("numero"));
		tbcBairro.setCellValueFactory(new PropertyValueFactory<Endereco, String>("bairro"));
		tbcComplemento.setCellValueFactory(new PropertyValueFactory<Endereco, String>("endereco"));
		tbcCidade.setCellValueFactory(new PropertyValueFactory<Endereco, Cidade>("cidade"));
		tbcEstado.setCellValueFactory(new PropertyValueFactory<Endereco, String>("codEstado"));
	
		if (cliente != null){
			List<Endereco> lstClienteEnd = EnderecoDAO.filtrarEnderecoCliente(cliente.getCodCliente());
			tbwEndereco.getItems().clear();
			tbwEndereco.getItems().addAll(lstClienteEnd);
		}
	}	
	
	/*private void preencherEnderecoJuridico(){	
		ClienteJuridico clie_juri = tbwClienteJuridico.getSelectionModel().getSelectedItem();
		
		tbcLogradouro.setCellValueFactory(new PropertyValueFactory<ClienteEndereco, Endereco>("codEndereco"));
		tbcCep.setCellValueFactory(new PropertyValueFactory<ClienteEndereco, Endereco>("cep"));
		tbcNro.setCellValueFactory(new PropertyValueFactory<ClienteEndereco, Endereco>("numero"));
		tbcBairro.setCellValueFactory(new PropertyValueFactory<ClienteEndereco, Endereco>("bairro"));
		tbcComplemento.setCellValueFactory(new PropertyValueFactory<ClienteEndereco, Endereco>("endereco"));
		tbcCidade.setCellValueFactory(new PropertyValueFactory<ClienteEndereco, Cidade>("cidade"));
		tbcEstado.setCellValueFactory(new PropertyValueFactory<ClienteEndereco, Estado>("estado"));
		
		if (clie_juri != null){
			
			List<ClienteJuridicoEndereco> lstClieJuriEnde = ClienteJuridicoEnderecoDAO.filtrarClienteJuridicoEndereco(clie_juri.getCodClienteJuridico());
			tbwEndereco.getItems().clear();
			tbwEndereco.getItems().addAll(lstClieJuriEnde);
		}
		
	}*/
	
	private void groupRadio() {
		
		final ToggleGroup sexo = new ToggleGroup();
		
		rbSexoF.setToggleGroup(sexo);
		rbSexoM.setToggleGroup(sexo);
		
		
		
		
	}

}
