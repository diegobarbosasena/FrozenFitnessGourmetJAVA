package br.com.controller;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import br.com.ajudantes.FormataData;
import br.com.ajudantes.Mascaras;
import br.com.dao.ClienteDAO;
import br.com.dao.ClienteJuridicoDAO;
import br.com.dao.EnderecoDAO;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
	@FXML private Button btnCancelarCadClien;
	@FXML private Label lblClienteFisicoCada;

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
		Mascaras.mascaraData(txtDtNascClienFisi);

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
		btnEcluirClienFisico.setOnAction(e -> excluirClienteFisico());
		btnEditarClienteFisico.setOnAction(f -> editarFisico());

		btnNovoClienteJuridico.setOnAction(g -> cadastrarJuridico());
		btnExcluirJuri.setOnAction(c -> excluirClienteJuridico());
		btnEditarClienteJuri.setOnAction(f -> editarJuridico());
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
		tbcDtNascFisi.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dtNascCliente"));

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
		tabCadClien.setText("Cadastrar cliente");
		lblClienteFisicoCada.setText("Cadastrar cliente físico");
		tabVisuClien.setDisable(true);

		btnConcluidoPedTel.setDisable(true);

		desabilitaFisico(false);
		desabilitaJuridico(true);

		btnCadastrarCliente.setText("Cadastrar");
		btnCadastrarCliente.setOnAction(f -> inserirFisico());
		btnCadastrarCliente.setOnKeyPressed(f -> {
			if (f.getCode() == KeyCode.ENTER) {
				inserirFisico();
			}
		});

		btnCancelar.setOnAction(l -> cancelar());
		btnCancelar.setOnKeyPressed(i -> {
			if (i.getCode() == KeyCode.ENTER) {
				cancelar();
			}
		});	
	}

	private void cancelar() {
		tbpPedidoTel.getSelectionModel().select(0);

		tabCadClien.setDisable(true);
		tabVisuClien.setDisable(false);

		limparClienteFisico();
	}

	private void inserirFisico() {

		if(! modo_edicao){

			if(validarCamposFisico(txtNomeClienFisi.getText(),txtDtNascClienFisi.getText(),txtTelClienFisi.getText(),txtCpfClienFisi.getText(),txtCelClienFisi.getText(),txtEmailClienFisi.getText(),txtPesoClienFisi.getText(),txtAlturaClienFisi.getText()))
			{
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Pedidos Telefone", "ERRO", "Preencha todos os campos!");	
			}
			else{

				Cliente cliente = new Cliente();

				if(rbSexoM.isSelected()){
					sexoSelecionado = "M";
				}
				if(rbSexoF.isSelected()){
					sexoSelecionado = "F";
				}

				cliente.setAltura(Float.parseFloat(txtAlturaClienFisi.getText()));
				cliente.setCelularCliente(txtCelClienFisi.getText());
				cliente.setCpfCliente(txtCpfClienFisi.getText());
				cliente.setEmailCliente(txtEmailClienFisi.getText());
				cliente.setNomeCliente(txtNomeClienFisi.getText());
				cliente.setPeso(Float.parseFloat(txtPesoClienFisi.getText()));
				cliente.setSexo(sexoSelecionado);
				cliente.setTelefoneCliente(txtTelClienFisi.getText());

				try {
					cliente.setDtNascCliente(FormataData.formataData(txtDtNascClienFisi.getText()));
				} catch (Exception e) {
					e.printStackTrace();
				}

				if(ClienteDAO.inserirCliente(cliente)){

					Alerta aletaInfo = new Alerta();
					aletaInfo.alertaInformation("Pedido telefone", "Sucesso", "Cliente cadastrado com sucesso!");

					btnCancelarCadClien.setDisable(true);
					btnCadastrarCliente.setDisable(true);

					btnConcluidoPedTel.setDisable(false);
					btnConcluidoPedTel.setOnAction(j -> concluido());	
					btnConcluidoPedTel.setOnKeyPressed(k -> {
						if (k.getCode() == KeyCode.ENTER) {
							concluido();
						}
					});

					limparClienteFisico();
					preencherClienteFisico();
					preencherEnderecoFisico();
				}
				else{
					Alerta alertaErro = new Alerta(); 
					alertaErro.alertaErro("Pedido telefone", "ERRO", "Cliente não cadastrado!");
				}
			}
		}
		else{

			Cliente clie = tbwClienteFisico.getSelectionModel().getSelectedItem();

			if(clie == null){
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Pedido telefone", "ERRO", "Nenhum item selecionado!");
			}
			else{
				tabVisuClien.setDisable(true);
				tabCadClien.setDisable(false);
				tbpPedidoTel.getSelectionModel().select(1);

				lblClienteFisicoCada.setText("Atualizar cliente físico");
				btnCadastrarCliente.setText("Atualizar");

				txtNomeClienFisi.setText(clie.getNomeCliente());
				txtTelClienFisi.setText(clie.getTelefoneCliente());
				txtCpfClienFisi.setText(clie.getCpfCliente());
				txtCelClienFisi.setText(clie.getCelularCliente());
				txtEmailClienFisi.setText(clie.getEmailCliente());
				txtPesoClienFisi.setText(String.valueOf(clie.getPeso()));
				txtAlturaClienFisi.setText(String.valueOf(clie.getAltura()));

				String sexodobanco = clie.getSexo();

				if(sexodobanco == "M"){
					rbSexoM.setSelected(true);
				}
				if(sexodobanco == "F"){
					rbSexoF.setSelected(true);
				}

				Date data_nasc = clie.getDtNascCliente();
				String dataBr =	FormataData.formataDataBr(data_nasc);

				txtDtNascClienFisi.setText(dataBr);

				btnCadastrarCliente.setOnAction(c -> atualizarClienteFisico());
				btnCancelarCadClien.setOnAction(c -> cancelar());
				btnConcluidoPedTel.setOnAction(d -> concluido());
			}
		}
	}

	private void atualizarClienteFisico() {

		Cliente codClienteUp = tbwClienteFisico.getSelectionModel().getSelectedItem();

		Cliente clienteUp = new Cliente();

		if(rbSexoM.isSelected()){
			sexoSelecionado = "M";
		}
		if(rbSexoF.isSelected()){
			sexoSelecionado = "F";
		}

		clienteUp.setCodCliente(codClienteUp.getCodCliente());

		clienteUp.setAltura(Float.parseFloat(txtAlturaClienFisi.getText()));
		clienteUp.setCelularCliente(txtCelClienFisi.getText());
		clienteUp.setCpfCliente(txtCpfClienFisi.getText());
		clienteUp.setEmailCliente(txtEmailClienFisi.getText());
		clienteUp.setNomeCliente(txtNomeClienFisi.getText());
		clienteUp.setPeso(Float.parseFloat(txtPesoClienFisi.getText()));
		clienteUp.setSexo(sexoSelecionado);
		clienteUp.setTelefoneCliente(txtTelClienFisi.getText());

		try {
			clienteUp.setDtNascCliente(FormataData.formataData(txtDtNascClienFisi.getText()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(validarCamposFisico(txtNomeClienFisi.getText(),txtDtNascClienFisi.getText(),txtTelClienFisi.getText(),txtCpfClienFisi.getText(),txtCelClienFisi.getText(),txtEmailClienFisi.getText(),txtPesoClienFisi.getText(),txtAlturaClienFisi.getText()))
		{
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Atualizar cliente físico", "ERRO", "Preencha todos os campos!");	
		}
		else{

			if(ClienteDAO.updateCliente(clienteUp)){

				limparClienteFisico();

				Alerta aletaInfo = new Alerta();
				aletaInfo.alertaInformation("Cliente físico", "Sucesso", "Cliente atualizado com sucesso!");

				btnConcluidoPedTel.setDisable(false);
				btnCancelarCadClien.setDisable(true);
				btnCadastrarCliente.setDisable(true);

				preencherClienteFisico();
				preencherClienteJuridico();
				preencherEnderecoFisico();

				modo_edicao = false;
			}
			else{
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Cliente físico", "ERRO", "Erro ao atualizar Cliente físico!");
			}
		}	
	}

	public void editarFisico(){

		tabCadClien.setText("Atualizar cliente fisíco");
		tabCadClien.setDisable(false);

		desabilitaJuridico(true);
		desabilitaFisico(false);

		btnCadastrarCliente.setDisable(false);
		btnCancelarCadClien.setDisable(false);
		btnConcluidoPedTel.setDisable(true);

		modo_edicao = true;

		inserirFisico();
	}

	public void excluirClienteFisico(){

		Cliente cl = tbwClienteFisico.getSelectionModel().getSelectedItem();

		if(cl == null){
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Cliente físico", "ERRO", "Nenhum item selecionado!");
		}
		else{

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Cliente físico");
			a.setHeaderText("Deseja excluir Cliente ?");
			a.setContentText("Tem certeza?");

			Stage s = (Stage) a.getDialogPane().getScene().getWindow();
			s.getIcons().add(new Image(this.getClass().getResource("/br/com/imagens/icone.png").toString()));

			ButtonType sim = new ButtonType("Sim");
			ButtonType nao = new ButtonType("Não" , ButtonData.CANCEL_CLOSE);

			DialogPane dialogPane = a.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/br/com/view/application.css").toExternalForm());

			a.getButtonTypes().setAll(sim, nao);

			Optional<ButtonType> resultado = a.showAndWait();

			if (resultado.get() == sim){

				if(ClienteDAO.deleteCliente(cl.getCodCliente())){

					Alerta aletaInfo = new Alerta();
					aletaInfo.alertaInformation("Cliente físico", "Sucesso", "Cliente excluído com sucesso!");

					preencherClienteFisico();
				}
				else{
					Alerta alertaWarning = new Alerta(); 
					alertaWarning.alertaWarning("Cliente físico", "AVISO!", "Cliente não pode ser excluido!");
				}
			}
		}
	}

	private void limparClienteFisico() {
		txtNomeClienFisi.clear();
		txtTelClienFisi.clear();
		txtCpfClienFisi.clear();
		txtCelClienFisi.clear();
		txtDtNascClienFisi.clear();
		txtEmailClienFisi.clear();
		txtPesoClienFisi.clear();
		txtAlturaClienFisi.clear();
		rbSexoM.setSelected(false);
		rbSexoF.setSelected(false);
	}

	private void concluido() {
		tbpPedidoTel.getSelectionModel().select(0);

		tabVisuClien.setDisable(false);
		tabCadClien.setDisable(true);
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

	public boolean validarCamposJuridico(String... camposJuridico) {

		boolean preenchido = false;
		for(String item : camposJuridico){
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
		lblClienteFisicoCada.setDisable(desabilitar);
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

	public void desabilitaJuridico(boolean habilitar){
		lblClienteJuridico.setDisable(habilitar);
		lblNomeContato.setDisable(habilitar);
		txtNomeClienJuri.setDisable(habilitar);
		lblTelPrincJuridico.setDisable(habilitar);
		txtTelPrinClienJuri.setDisable(habilitar);
		lblRazaoClienteJuri.setDisable(habilitar);
		txtRazaoClienJuri.setDisable(habilitar);
		lblTelSecuJuridico.setDisable(habilitar);
		txtTelContClienJuri.setDisable(habilitar);
		lblCnpjClienJuri.setDisable(habilitar);
		txtCnpjClienJuri.setDisable(habilitar);
		lblEmailPrincJuridico.setDisable(habilitar);
		txtEmailPrinClienJuri.setDisable(habilitar);
		lblInscricao.setDisable(habilitar);
		txtInscricao.setDisable(habilitar);
		lblEmailContJuridico.setDisable(habilitar);
		txtEmailContClienJuri.setDisable(habilitar);
	}

	private void cadastrarJuridico(){

		tbpPedidoTel.getSelectionModel().select(1);
		tabCadClien.setDisable(false);

		tabCadClien.setText("Cadastrar cliente");
		lblClienteJuridico.setText("Cadastrar cliente jurídico");
		tabVisuClien.setDisable(true);

		btnConcluidoPedTel.setDisable(true);

		desabilitaFisico(true);
		desabilitaJuridico(false);

		btnCadastrarCliente.setText("Cadastrar");
		btnCadastrarCliente.setOnAction(f -> inserirJuridico());
		btnCadastrarCliente.setOnKeyPressed(f -> {
			if (f.getCode() == KeyCode.ENTER) {
				inserirJuridico();
			}
		});

		btnCancelar.setOnAction(l -> cancelar());
		btnCancelar.setOnKeyPressed(i -> {
			if (i.getCode() == KeyCode.ENTER) {
				cancelar();
			}
		});	

	}

	private void inserirJuridico() {

		if(! modo_edicao){

			if(validarCamposJuridico(txtNomeClienJuri.getText(),txtTelPrinClienJuri.getText(),txtRazaoClienJuri.getText(),txtTelContClienJuri.getText(),txtCnpjClienJuri.getText(),txtEmailPrinClienJuri.getText(),txtEmailContClienJuri.getText()))
			{
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Pedidos Telefone", "ERRO", "Preencha todos os campos!");	
			}
			else{

				ClienteJuridico clienteJuridico = new ClienteJuridico();

				clienteJuridico.setNomeContato(txtNomeClienJuri.getText());
				clienteJuridico.setRazaoSocial(txtRazaoClienJuri.getText());
				clienteJuridico.setInscricaoEstadual(txtInscricao.getText());
				clienteJuridico.setCnpj(txtCnpjClienJuri.getText());
				clienteJuridico.setTelefonePrincipal(txtTelPrinClienJuri.getText());
				clienteJuridico.setTelefoneContato(txtTelContClienJuri.getText());
				clienteJuridico.setEmailPrincipal(txtEmailPrinClienJuri.getText());
				clienteJuridico.setEmailContato(txtEmailContClienJuri.getText());

				if(ClienteJuridicoDAO.inserirClienteJuridico(clienteJuridico)){

					Alerta aletaInfo = new Alerta();
					aletaInfo.alertaInformation("Pedido telefone", "Sucesso", "Cliente cadastrado com sucesso!");

					btnCancelarCadClien.setDisable(true);
					btnCadastrarCliente.setDisable(true);

					btnConcluidoPedTel.setDisable(false);
					btnConcluidoPedTel.setOnAction(j -> concluido());	
					btnConcluidoPedTel.setOnKeyPressed(k -> {
						if (k.getCode() == KeyCode.ENTER) {
							concluido();
						}
					});

					limparClienteJuridico();
					preencherClienteJuridico();	
				}
				else{
					Alerta alertaErro = new Alerta(); 
					alertaErro.alertaErro("Pedido telefone", "ERRO", "Cliente não cadastrado!");
				}	
			}
		}
		else{

			ClienteJuridico clieJuri = tbwClienteJuridico.getSelectionModel().getSelectedItem();

			if(clieJuri == null){
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Pedido telefone", "ERRO", "Nenhum item selecionado!");
			}
			else{
				tabVisuClien.setDisable(true);
				tabCadClien.setDisable(false);
				tbpPedidoTel.getSelectionModel().select(1);

				lblClienteJuridico.setText("Atualizar cliente jurídico");
				btnCadastrarCliente.setText("Atualizar");

				txtNomeClienJuri.setText(clieJuri.getNomeContato());
				txtTelPrinClienJuri.setText(clieJuri.getTelefonePrincipal());
				txtRazaoClienJuri.setText(clieJuri.getRazaoSocial());
				txtInscricao.setText(clieJuri.getInscricaoEstadual());
				txtTelContClienJuri.setText(clieJuri.getTelefoneContato());
				txtCnpjClienJuri.setText(clieJuri.getCnpj());
				txtEmailPrinClienJuri.setText(clieJuri.getEmailPrincipal());
				txtEmailContClienJuri.setText(clieJuri.getEmailContato());

				btnCadastrarCliente.setOnAction(c -> atualizarClienteJuridico());
				btnCancelarCadClien.setOnAction(c -> cancelar());
				btnConcluidoPedTel.setOnAction(d -> concluido());
			}
		}
	}

	private void atualizarClienteJuridico() {

		ClienteJuridico codClieJuriUp = tbwClienteJuridico.getSelectionModel().getSelectedItem();

		ClienteJuridico clieJuriUp = new ClienteJuridico();


		clieJuriUp.setCodClienteJuridico(codClieJuriUp.getCodClienteJuridico());
		clieJuriUp.setNomeContato(txtNomeClienJuri.getText());
		clieJuriUp.setRazaoSocial(txtRazaoClienJuri.getText());
		clieJuriUp.setCnpj(txtCnpjClienJuri.getText());
		clieJuriUp.setInscricaoEstadual(txtInscricao.getText());
		clieJuriUp.setTelefonePrincipal(txtTelPrinClienJuri.getText());
		clieJuriUp.setTelefoneContato(txtTelContClienJuri.getText());
		clieJuriUp.setEmailPrincipal(txtEmailPrinClienJuri.getText());
		clieJuriUp.setEmailContato(txtEmailContClienJuri.getText());

		if(validarCamposJuridico(txtNomeClienJuri.getText(),txtTelPrinClienJuri.getText(),txtRazaoClienJuri.getText(),txtTelContClienJuri.getText(),txtCnpjClienJuri.getText(),txtEmailPrinClienJuri.getText(),txtEmailContClienJuri.getText()))
		{
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Atualizar cliente jurídico", "ERRO", "Preencha todos os campos!");	
		}
		else{

			if(ClienteJuridicoDAO.updateClienteJuridico(clieJuriUp)){

				limparClienteJuridico();

				Alerta aletaInfo = new Alerta();
				aletaInfo.alertaInformation("Cliente jurídico", "Sucesso", "Cliente atualizado com sucesso!");

				btnConcluidoPedTel.setDisable(false);
				btnCancelarCadClien.setDisable(true);
				btnCadastrarCliente.setDisable(true);

				preencherClienteJuridico();
				preencherEnderecoFisico();

				modo_edicao = false;	
			}
			else{
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Cliente jurídico", "ERRO", "Erro ao atualizar Cliente físico!");
			}
		}

	}

	public void editarJuridico(){

		tabCadClien.setText("Atualizar cliente jurídico");
		tabCadClien.setDisable(false);

		desabilitaFisico(true);
		desabilitaJuridico(false);

		btnCadastrarCliente.setDisable(false);
		btnCancelarCadClien.setDisable(false);
		btnConcluidoPedTel.setDisable(true);

		modo_edicao = true;

		inserirJuridico();	
	}

	public void excluirClienteJuridico(){

		ClienteJuridico cj = tbwClienteJuridico.getSelectionModel().getSelectedItem();

		if(cj == null){
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Cliente jurídico", "ERRO", "Nenhum item selecionado!");
		}
		else{

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Cliente jurídico");
			a.setHeaderText("Deseja excluir Cliente ?");
			a.setContentText("Tem certeza?");

			Stage s = (Stage) a.getDialogPane().getScene().getWindow();
			s.getIcons().add(new Image(this.getClass().getResource("/br/com/imagens/icone.png").toString()));

			ButtonType sim = new ButtonType("Sim");
			ButtonType nao = new ButtonType("Não" , ButtonData.CANCEL_CLOSE);

			DialogPane dialogPane = a.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/br/com/view/application.css").toExternalForm());

			a.getButtonTypes().setAll(sim, nao);

			Optional<ButtonType> resultado = a.showAndWait();

			if (resultado.get() == sim){

				if(ClienteJuridicoDAO.deleteCliente(cj.getCodClienteJuridico())){

					Alerta aletaInfo = new Alerta();
					aletaInfo.alertaInformation("Cliente jurídico", "Sucesso", "Cliente excluído com sucesso!");

					preencherClienteJuridico();
				}
				else{
					Alerta alertaWarning = new Alerta(); 
					alertaWarning.alertaWarning("Cliente jurídico", "AVISO!", "Cliente não pode ser excluido!");
				}
			}
		}
	}

	private void limparClienteJuridico() {
		txtNomeClienJuri.clear();
		txtTelPrinClienJuri.clear();
		txtRazaoClienJuri.clear();
		txtTelContClienJuri.clear();
		txtInscricao.clear();
		txtCnpjClienJuri.clear();
		txtEmailPrinClienJuri.clear();
		txtEmailContClienJuri.clear();
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
