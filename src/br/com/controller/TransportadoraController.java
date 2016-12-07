package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import br.com.ajudantes.Mascaras;
import br.com.dao.CidadeDAO;
import br.com.dao.EnderecoDAO;
import br.com.dao.EstadoDAO;
import br.com.dao.TipoVeiculoDAO;
import br.com.dao.TransportadoraDAO;
import br.com.dao.VeiculoTranspDAO;
import br.com.model.Cidade;
import br.com.model.Endereco;
import br.com.model.Estado;
import br.com.model.TipoVeiculo;
import br.com.model.Transportadora;
import br.com.model.VeiculoTransp;
import br.com.view.Alerta;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TransportadoraController implements Initializable{

	@FXML private AnchorPane acpTransp;
	@FXML private TabPane tpTransp;
	@FXML private Tab tabVisualizar;
	@FXML private Tab tabCadastrarVeiculo;
	@FXML private TextField txtBuscaTrans;
	@FXML private Button btnNovaTransportadora;
	@FXML private Button btnEditarTrans;
	@FXML private Button btnExcluirTrans;
	@FXML private Button btnAddVeic;
	@FXML private TableView <Transportadora>tvTransp;
	@FXML private TableColumn <Transportadora, String> tbcRazao;
	@FXML private TableColumn <Transportadora, String> tbcTelCont;
	@FXML private TableColumn <Transportadora, String> tbcEmailCont;
	@FXML private TableColumn <Transportadora, String> tbcResp;
	@FXML private TableView <Endereco> tbwEnderecoTransp;
	@FXML private TableColumn <Endereco, String> tbcLogradouroTransp;
	@FXML private TableColumn <Endereco, String> tbcCepTransp;
	@FXML private TableColumn <Endereco, String> tbcNroTransp;
	@FXML private TableColumn <Endereco, String> tbcBairroTransp;
	@FXML private TableColumn <Endereco, String> tbcComplementoTransp;
	@FXML private TableColumn <Endereco, Cidade> tbcCidadeTransp;
	@FXML private TableColumn <Estado, String> tbcEstadoTransp;
	@FXML private Button btnEditarVeic;
	@FXML private Button btnExcluirVeic;
	@FXML private TableView <VeiculoTransp> tbvVeiculoTransp;
	@FXML private TableColumn <VeiculoTransp,TipoVeiculo> tbcTipoVeic;
	@FXML private TableColumn <VeiculoTransp,String> tbcPlacaVeic;
	@FXML private Tab tabCadastrar;
	@FXML private Label lblEdicaoCadas;
	@FXML private Label lblRazao;
	@FXML private TextField txtRazao;
	@FXML private Label lblNomeFantasia;
	@FXML private TextField txtNomeFant;
	@FXML private Label lblCnpjTransp;
	@FXML private TextField txtCnpjTransp;
	@FXML private Label lblTelPrin;
	@FXML private TextField txtTelPrin;
	@FXML private Label lblTelCont;
	@FXML private TextField txtTelCont;
	@FXML private Label lblEmailPrin;
	@FXML private TextField txtEmailPrin;
	@FXML private Label lblEmailCont;
	@FXML private TextField txtEmailCont;
	@FXML private Label lblResponsavel;
	@FXML private TextField txtResponsavelTransp;
	@FXML private Label lblEnd;
	@FXML private TextField txtEndTransp;
	@FXML private Label lblNroTransp;
	@FXML private TextField txtNroTransp;
	@FXML private Label lblCepTransp;
	@FXML private TextField txtCepTransp;
	@FXML private Label lblBairroTransp;
	@FXML private TextField txtBairroTransp;
	@FXML private Label lblComplementoTransp;
	@FXML private TextField txtComplementoTransp;
	@FXML private Label lblEstadoTransp;
	@FXML private ComboBox <Estado> cboEstadoTransp;
	@FXML private Label lblCidadeTransp;
	@FXML private ComboBox <Cidade> cboCidadeTransp;
	@FXML private Button btnCadastrarTrans;
	@FXML private Button btnConcluido;
	@FXML private Button btnCancelarTransp;
	@FXML private Label lblCadAtrVeic;
	@FXML private Label lblTransp;
	@FXML private TextField txtTransp;
	@FXML private Label lblTipoVeic;
	@FXML private TextField txtTipoVeic;
	@FXML private Label lblPlaca;
	@FXML private TextField txtPlaca;
	@FXML private Button btnCadastrarVeiculo;
	@FXML private Button btnConcluidoVeiculo;
	@FXML private Button btnCancelarVeiculo;
	@FXML private Button btnAtualizarTransp;
	@FXML private Button btnAtualizarVeiculo;
	@FXML private TextField txtBuscaVeiculo;

	boolean modoEdicao = false;
	boolean modoEdicaoVeic = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Mascaras.mascaraCNPJ(txtCnpjTransp);
		Mascaras.mascaraTelefone(txtTelPrin);
		Mascaras.mascaraTelefone(txtTelCont);
		Mascaras.mascaraEmail(txtEmailPrin);
		Mascaras.mascaraEmail(txtEmailCont);
		Mascaras.mascaraCEP(txtCepTransp);

		preencherTransportadora();
		preencherVeiculo();
		initTooltip();

		tabCadastrar.setDisable(true);
		tabCadastrarVeiculo.setDisable(true);

		txtBuscaTrans.textProperty().addListener(a -> {
			if(!txtBuscaTrans.getText().isEmpty())
				buscarTransportadora();
			else
				preencherTransportadora();
		});

		txtBuscaVeiculo.textProperty().addListener(g -> {
			if(! txtBuscaVeiculo.getText().isEmpty())
				buscarVeiculo();
			else
				preencherVeiculo();
		});

		btnAtualizarTransp.setOnAction(k -> preencherTransportadora());
		btnNovaTransportadora.setOnAction(b -> nova());
		btnEditarTrans.setOnAction(c -> editarTrans());
		btnExcluirTrans.setOnAction(d -> excluirTrans());

		btnAddVeic.setOnAction(a -> {
			if (tvTransp.getSelectionModel().getSelectedItem() != null){
				adicionarVeiculo();
			}	
			else{
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Transportadora", "ERRO", "Selecione um item da tabela abaixo.");
			}
		});

		tvTransp.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Integer> arg0) {		
				if (tvTransp.getSelectionModel().getSelectedIndices() != null)
					preencherVeiculo();
				preencherEnderecoTransportadora();
			}
		});

		btnCadastrarVeiculo.setOnAction(l -> adicionarVeiculo());
		btnEditarVeic.setOnAction(e -> {
			if(tbvVeiculoTransp.getSelectionModel().getSelectedItem() != null){
				editarVeiculo();
			}
			else{
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Transportadora", "ERRO", "Selecione um item da tabela abaixo.");
			}
		});

		btnAtualizarVeiculo.setOnAction(m -> preencherVeiculo());
		btnCancelarVeiculo.setOnAction(h -> cancelarVeiculo());
		btnExcluirVeic.setOnAction(c -> excluirVeiculo());	
	}

	public void popularComboBox() {
		cboCidadeTransp.getItems().clear();
		cboCidadeTransp.getItems().addAll(CidadeDAO.selecionarTodasCidades());

		cboEstadoTransp.getItems().clear();
		cboEstadoTransp.getItems().addAll(EstadoDAO.selecionarTodosEstados());

		cboEstadoTransp.valueProperty().addListener(new ChangeListener<Estado>() {
			@Override
			public void changed(ObservableValue<? extends Estado> observable, Estado oldValue, Estado newValue) {			
				cboCidadeTransp.getItems().clear();

				if(cboEstadoTransp.getSelectionModel().getSelectedItem() !=null){
					List<Cidade> uf = CidadeDAO.filtrarCidade(cboEstadoTransp.getSelectionModel().getSelectedItem().getUf());

					cboCidadeTransp.getItems().addAll(uf);
				}
			}
		});
	}

	public void preencherTransportadora(){

		tbcRazao.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("razaoSocial"));
		tbcEmailCont.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("emailContato"));
		tbcResp.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("responsavelTransportadora"));
		tbcTelCont.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("telefoneContato"));

		List<Transportadora> lst = TransportadoraDAO.selecionarTodas();

		tvTransp.getItems().clear();
		tvTransp.getItems().addAll(lst);
	}

	public void preencherEnderecoTransportadora(){

		Transportadora transp = tvTransp.getSelectionModel().getSelectedItem();

		tbcLogradouroTransp.setCellValueFactory(new PropertyValueFactory<Endereco, String>("logradouro"));
		tbcCepTransp.setCellValueFactory(new PropertyValueFactory<Endereco, String>("cep"));
		tbcNroTransp.setCellValueFactory(new PropertyValueFactory<Endereco, String>("numero"));
		tbcBairroTransp.setCellValueFactory(new PropertyValueFactory<Endereco, String>("bairro"));
		tbcComplementoTransp.setCellValueFactory(new PropertyValueFactory<Endereco, String>("complemento"));
		tbcCidadeTransp.setCellValueFactory(new PropertyValueFactory<Endereco, Cidade>("cidade"));
		tbcEstadoTransp.setCellValueFactory(new PropertyValueFactory<Estado, String>("estado"));

		if(transp != null){
			List<Endereco> lstEndereco = EnderecoDAO.filtrarEnderecoTransportadora(transp.getCodTransportadora());
			tbwEnderecoTransp.getItems().clear();
			tbwEnderecoTransp.getItems().addAll(lstEndereco);
		}
	}

	public void initTooltip() {	
		Tooltip.install(txtRazao, new Tooltip("Digite aqui a Razão Social."));	
	}

	public void nova() {

		limparTrans();
		popularComboBox();

		tabCadastrar.setText("Cadastrar transportadora");
		tpTransp.getSelectionModel().select(1);

		lblEdicaoCadas.setText("Cadastrar transportadora");

		tabVisualizar.setDisable(true);
		tabCadastrar.setDisable(false);

		btnConcluido.setDisable(true);

		btnCadastrarTrans.setText("Cadastrar");
		btnCadastrarTrans.setOnAction(e -> inserirTransportadora());
		btnCadastrarTrans.setOnKeyPressed(f -> {
			if (f.getCode() == KeyCode.ENTER) {
				inserirTransportadora();
			}
		});

		btnCancelarTransp.setDisable(false);
		btnCancelarTransp.setOnAction(z -> cancelar());
		btnCancelarTransp.setOnKeyPressed(f -> {
			if (f.getCode() == KeyCode.ENTER) {
				cancelar();
			}
		});
	}

	public void cadastrarTransportadora() {

		inserirTransportadora();

		btnCancelarTransp.setOnAction(h -> cancelar());
		btnCancelarTransp.setOnKeyPressed(i -> {
			if (i.getCode() == KeyCode.ENTER) {
				cancelar();
			}
		});	
	}

	private void inserirTransportadora() {

		if(!modoEdicao){

			if(validarCamposTransportadora(txtBairroTransp.getText(),txtCepTransp.getText(),txtCnpjTransp.getText(),txtEmailCont.getText(),txtEmailPrin.getText(),txtTelPrin.getText(),txtTelCont.getText(),txtEndTransp.getText(),txtNomeFant.getText(),txtNroTransp.getText(),txtRazao.getText(),txtResponsavelTransp.getText()))		
			{		
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Transportadora", "ERRO", "Preencha todos os campos!");
			}
			else{

				Transportadora novaTrans = new Transportadora();
				Endereco novoEnde = new Endereco();

				novoEnde.setBairro(txtBairroTransp.getText());
				novoEnde.setCep(txtCepTransp.getText());
				novoEnde.setComplemento(txtComplementoTransp.getText());
				novoEnde.setLogradouro(txtEndTransp.getText());
				novoEnde.setNumero(txtNroTransp.getText());

				novoEnde.setCidade(cboCidadeTransp.getSelectionModel().getSelectedItem());

				novaTrans.setCnpjTransportadora(txtCnpjTransp.getText());
				novaTrans.setEmailContato(txtEmailCont.getText());
				novaTrans.setEmailPrincipal(txtEmailPrin.getText());
				novaTrans.setTelefonePrincipal(txtTelPrin.getText());
				novaTrans.setTelefoneContato(txtTelCont.getText());
				novaTrans.setNomeFantasia(txtNomeFant.getText());
				novaTrans.setRazaoSocial(txtRazao.getText());
				novaTrans.setResponsavelTransportadora(txtResponsavelTransp.getText());

				if(EnderecoDAO.insertEndereco(novoEnde) && TransportadoraDAO.insertTransportadora(novaTrans)){

					Alerta aletaInfo = new Alerta();
					aletaInfo.alertaInformation("Transportadora", "Sucesso", "Transportadora cadastrada com sucesso!");

					btnConcluido.setDisable(false);
					btnConcluido.setOnAction(j -> concluido());	
					btnConcluido.setOnKeyPressed(k -> {
						if (k.getCode() == KeyCode.ENTER) {
							concluido();
						}
					});

					btnCancelarTransp.setDisable(true);

					limparTrans();
					preencherTransportadora();	
					popularComboBox();
				}
				else{
					Alerta alertaErro = new Alerta(); 
					alertaErro.alertaErro("Transportadora", "ERRO", "Transportadora não pode ser cadastrada!");	
				}
			}	
		}
		else{

			Transportadora tn = tvTransp.getSelectionModel().getSelectedItem();

			if(tn == null){
				tabCadastrar.setDisable(true);
				tabCadastrar.setText("Cadastrar transportadora");

				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Transportadora", "ERRO", "Nenhum item selecionado!");
			}
			else{

				tabVisualizar.setDisable(true);
				tpTransp.getSelectionModel().select(1);

				lblEdicaoCadas.setText("Atualizar Transportadora");
				btnCadastrarTrans.setText("Atualizar");

				txtCnpjTransp.setText(tn.getCnpjTransportadora());
				txtEmailCont.setText(tn.getEmailContato());
				txtEmailPrin.setText(tn.getEmailPrincipal());
				txtTelPrin.setText(tn.getTelefonePrincipal());
				txtTelCont.setText(tn.getTelefoneContato());
				txtNomeFant.setText(tn.getNomeFantasia());
				txtRazao.setText(tn.getRazaoSocial());
				txtResponsavelTransp.setText(tn.getResponsavelTransportadora());

				txtEndTransp.setText(tn.getEndereco().getLogradouro());
				txtCepTransp.setText(tn.getEndereco().getCep());
				txtNroTransp.setText(tn.getEndereco().getNumero());
				txtBairroTransp.setText(tn.getEndereco().getBairro());
				txtComplementoTransp.setText(tn.getEndereco().getComplemento());

				/*for(int i=0 ; i < cboEstadoTransp.getItems().size();i++){

					Estado e = cboEstadoTransp.getItems().get(i);

					if(e.getCodEstado() == tn.getEndereco().getCidade().getEstado().getCodEstado()){
						cboEstadoTransp.getSelectionModel().select(i);
						break;
					}
				}*/

				for(int i=0 ; i < cboCidadeTransp.getItems().size();i++){

					Cidade c = cboCidadeTransp.getItems().get(i);

					if(c.getCodCidade() == tn.getEndereco().getCodCidade()){
						cboCidadeTransp.getSelectionModel().select(i);
						break;
					}
				}

				btnCadastrarTrans.setOnAction(l -> atualizarTransportadora());
				btnCancelarTransp.setOnAction(m -> cancelar());
				btnConcluido.setOnAction(n -> concluido());

				modoEdicao = false;	
			}
		}
	}

	public boolean validaCidadetransportadora(Cidade selectedItem) {
		boolean preen = false;

		if (selectedItem == null){
			preen = true;
		}
		else{
			preen=false;
		}
		return preen;
	}

	public boolean validarCamposTransportadora(String... camposTrans) {

		boolean preenchido = false;
		for(String item : camposTrans){
			if(item.isEmpty()){
				preenchido = true;
				break;
			}else{
				preenchido = false;
			}
		}
		return preenchido;
	}

	public void atualizarTransportadora(){

		Transportadora codTrans = tvTransp.getSelectionModel().getSelectedItem();

		Transportadora upTransp = new Transportadora();
		Endereco upEn = new Endereco();

		upTransp.setCodTransportadora(codTrans.getCodTransportadora());
		upTransp.setCnpjTransportadora(txtCnpjTransp.getText());
		upTransp.setEmailContato(txtEmailCont.getText());
		upTransp.setEmailPrincipal(txtEmailPrin.getText());
		upTransp.setTelefonePrincipal(txtTelPrin.getText());
		upTransp.setTelefoneContato(txtTelCont.getText());
		upTransp.setNomeFantasia(txtNomeFant.getText());
		upTransp.setRazaoSocial(txtRazao.getText());
		upTransp.setResponsavelTransportadora(txtResponsavelTransp.getText());

		upEn.setLogradouro(txtEndTransp.getText());
		upEn.setCep(txtCepTransp.getText());
		upEn.setNumero(txtNroTransp.getText());
		upEn.setBairro(txtBairroTransp.getText());
		upEn.setComplemento(txtComplementoTransp.getText());

		upEn.setCodEndereco(codTrans.getEndereco().getCodEndereco());

		upEn.setCidade(cboCidadeTransp.getSelectionModel().getSelectedItem());

		if(validarCamposTransportadora(txtBairroTransp.getText(),txtCepTransp.getText(),txtCnpjTransp.getText(),txtEmailCont.getText(),txtEmailPrin.getText(),txtTelPrin.getText(),txtTelCont.getText(),txtEndTransp.getText(),txtNomeFant.getText(),txtNroTransp.getText(),txtRazao.getText(),txtResponsavelTransp.getText()))
		{	
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Transportadora", "ERRO", "Preencha Todos os campos!");
		}
		else{

			if(TransportadoraDAO.update(upTransp) && EnderecoDAO.updateEnde(upEn)){

				limparTrans();

				Alerta aletaInfo = new Alerta();
				aletaInfo.alertaInformation("Transportadora", "Sucesso", "Transportadora atualizada com sucesso!");

				btnConcluido.setDisable(false);	
				btnCancelarTransp.setDisable(true);
				btnCadastrarTrans.setDisable(true);

				preencherTransportadora();

				modoEdicao = false;
			}
			else{	
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Transportadora", "ERRO", "Erro ao atualizar Transportadora!");
			}
		}
	}

	public void editarTrans() {

		popularComboBox();

		tabCadastrar.setText("Atualizar transportadora");
		tabCadastrar.setDisable(false);

		btnCadastrarTrans.setDisable(false);
		btnCancelarTransp.setDisable(false);
		btnConcluido.setDisable(true);

		modoEdicao = true;

		inserirTransportadora();
	}

	public void excluirTrans() {

		Transportadora t = tvTransp.getSelectionModel().getSelectedItem();

		if(t == null){	
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Transportadora", "ERRO", "Nenhum item selecionado!");
		}
		else{

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Transportadora");
			a.setHeaderText("Deseja excluir Transportadora?");
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

				if(TransportadoraDAO.deleteTransp(t.getCodTransportadora()) ){

					EnderecoDAO.deleteEnde(t.getEndereco().getCodEndereco());

					Alerta aletaInfo = new Alerta();
					aletaInfo.alertaInformation("Transportadora", "Sucesso", "Transportadora excluída com sucesso!");

					preencherTransportadora();
				}
				else{
					Alerta alertaWarning = new Alerta(); 
					alertaWarning.alertaWarning("Transportadora", "AVISO!", "Transportadora não pode ser excluida!");
				}
			}	
		}
	}

	public void buscarTransportadora() {

		List<Transportadora> lstTransFilt = TransportadoraDAO.filtrar("%"+txtBuscaTrans.getText()+"%");

		if (lstTransFilt.isEmpty()){

			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaWarning("Transportadora", "", "Nenhum registro encontrado!");

			txtBuscaTrans.clear();
			preencherTransportadora();
		}
		else{
			tvTransp.getItems().clear();
			tvTransp.getItems().addAll(lstTransFilt);
		}
	}

	public void concluido() {
		tpTransp.getSelectionModel().select(0);

		tabCadastrar.setDisable(true);
		tabVisualizar.setDisable(false);
		tabCadastrarVeiculo.setDisable(true);
	}

	public void cancelar(){
		tpTransp.getSelectionModel().select(0);

		tabCadastrar.setDisable(true);
		tabVisualizar.setDisable(false);

		limparTrans();
	}

	public void limparTrans() {
		txtBairroTransp.clear();
		txtBuscaTrans.clear();
		txtCepTransp.clear();
		txtCnpjTransp.clear();
		txtComplementoTransp.clear();
		txtEmailCont.clear();
		txtEmailPrin.clear();
		txtTelPrin.clear();
		txtTelCont.clear();
		txtEndTransp.clear();
		txtNomeFant.clear();
		txtNroTransp.clear();
		txtRazao.clear();
		txtResponsavelTransp.clear();

		cboCidadeTransp.valueProperty().set(null);
		cboEstadoTransp.valueProperty().set(null);
	}


	public void preencherVeiculo(){

		Transportadora transp = tvTransp.getSelectionModel().getSelectedItem();

		tbcTipoVeic.setCellValueFactory(new PropertyValueFactory<VeiculoTransp, TipoVeiculo>("tipoVeiculo"));
		tbcPlacaVeic.setCellValueFactory(new PropertyValueFactory<VeiculoTransp ,String>("placaVeiculo"));

		if (transp != null){
			List<VeiculoTransp> lstVeiculo = VeiculoTranspDAO.filtrarVeiculo(transp.getCodTransportadora());
			tbvVeiculoTransp.getItems().clear();
			tbvVeiculoTransp.getItems().addAll(lstVeiculo);
		}
		else{
			List<VeiculoTransp> lstVeiculo = VeiculoTranspDAO.obterTodosVeiculos();
			tbvVeiculoTransp.getItems().clear();
			tbvVeiculoTransp.getItems().addAll(lstVeiculo);
		}
	}

	public void adicionarVeiculo() {

		Transportadora transp = tvTransp.getSelectionModel().getSelectedItem();

		limparVeiculo();

		tabVisualizar.setDisable(true);
		tabCadastrarVeiculo.setDisable(false);
		lblCadAtrVeic.setText("Cadastrar Veículo");

		tpTransp.getSelectionModel().select(2);
		txtTransp.setText(transp.getRazaoSocial());

		btnConcluidoVeiculo.setDisable(true);
		btnCancelarTransp.setDisable(false);
		btnCadastrarTrans.setDisable(false);

		btnCadastrarVeiculo.setOnAction(t -> inserirVeiculo());
		btnCadastrarVeiculo.setOnKeyPressed(b -> {
			if (b.getCode() == KeyCode.ENTER){
				inserirVeiculo();
			}
		});

		btnCancelarVeiculo.setOnAction(n -> cancelarVeiculo());
		btnCancelarVeiculo.setOnKeyPressed(l -> {
			if (l.getCode() == KeyCode.ENTER)
				cancelarVeiculo();
		});
	}

	public void inserirVeiculo() {

		Transportadora transp = tvTransp.getSelectionModel().getSelectedItem();

		if(!modoEdicaoVeic){

			if( validarCamposVeiculo( txtPlaca.getText() , txtTipoVeic.getText()) )		
			{		
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Veículo", "ERRO", "Preencha todos os campos!");
			}
			else{

				TipoVeiculo novoTp = new TipoVeiculo();
				VeiculoTransp novoVt = new VeiculoTransp();

				novoTp.setNomeTipoVeiculo(txtTipoVeic.getText());
				novoVt.setPlacaVeiculo(txtPlaca.getText());
				novoVt.setCodTransportadora(transp.getCodTransportadora());

				if(TipoVeiculoDAO.insertTipoVeiculo(novoTp) && VeiculoTranspDAO.insertVeiculoTransp(novoVt)){

					Alerta alertaInfo = new Alerta();
					alertaInfo.alertaInformation("Veículo", "SUCESSO", "Veículo cadastrado com sucesso!");

					btnConcluidoVeiculo.setDisable(false);
					btnConcluidoVeiculo.setOnAction(j -> concluidoVeiculo());	
					btnConcluidoVeiculo.setOnKeyPressed(k -> {
						if (k.getCode() == KeyCode.ENTER) {
							concluidoVeiculo();
						}
					});

					btnCadastrarVeiculo.setDisable(true);
					btnCancelarVeiculo.setDisable(true);

					limparVeiculo();
				}
				else{
					Alerta alertaErro = new Alerta(); 
					alertaErro.alertaErro("Veículo", "ERRO", "Veículo não pode ser cadastrado!");	
				}
			}	
		}
		else{

			VeiculoTransp vt = tbvVeiculoTransp.getSelectionModel().getSelectedItem();

			if(vt == null){
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Veículo", "ERRO", "Selecione um item da tabela abaixo!");
			}
			else{

				tabVisualizar.setDisable(true);
				tpTransp.getSelectionModel().select(2);

				lblCadAtrVeic.setText("Atualizar Veículo");
				btnCadastrarVeiculo.setText("Atualizar");

				txtPlaca.setText(vt.getPlacaVeiculo());
				txtTipoVeic.setText(vt.getTipoVeiculo().getNomeTipoVeiculo());


				btnCadastrarTrans.setOnAction(l -> atualizarVeiculo());
				btnCancelarTransp.setOnAction(m -> cancelarVeiculo());
				btnConcluidoVeiculo.setOnAction(n -> concluidoVeiculo());

				modoEdicaoVeic = false;	
			}
		}
	}

	public void atualizarVeiculo() {

		VeiculoTransp codVeiculo = tbvVeiculoTransp.getSelectionModel().getSelectedItem();

		VeiculoTransp upVeiculo = new VeiculoTransp();
		TipoVeiculo upTipo = new TipoVeiculo();

		upVeiculo.setCodTipoVeiculo(codVeiculo.getCodVeiculoTransp());
		upVeiculo.setPlacaVeiculo(txtPlaca.getText());
		upTipo.setNomeTipoVeiculo(txtTipoVeic.getText());

		if( validarCamposVeiculo( txtPlaca.getText() , txtTipoVeic.getText()) )		
		{			
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Veículo", "ERRO", "Preencha todos os campos!");
		}
		else{

			if(TipoVeiculoDAO.updateTipoVeiculo(upTipo) && VeiculoTranspDAO.updateVeiculoTransp(upVeiculo)){

				limparVeiculo();

				Alerta alertaInfo = new Alerta();
				alertaInfo.alertaInformation("Veículo", "SUCESSO", "Veículo Atualizado com sucesso!");

				btnConcluidoVeiculo.setDisable(false);
				btnCadastrarVeiculo.setDisable(true);	
				btnCancelarVeiculo.setDisable(true);

				preencherTransportadora();

				modoEdicaoVeic = false;
			}
			else{	
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Veículo", "ERRO", "Erro ao atualizar Veículo!");
			}
		}
	}

	public void editarVeiculo(){

		tabCadastrarVeiculo.setDisable(false);
		tabCadastrarVeiculo.setText("Editar veiculo");
		tabVisualizar.setDisable(true);

		btnCadastrarVeiculo.setDisable(false);
		btnCancelarVeiculo.setDisable(false);
		btnConcluidoVeiculo.setDisable(true);

		modoEdicaoVeic = true;

		inserirVeiculo();
	}

	private void excluirVeiculo(){

		VeiculoTransp vt = tbvVeiculoTransp.getSelectionModel().getSelectedItem();

		if(vt == null){	
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Veículo", "ERRO", "Nenhum item selecionado!");
		}
		else{

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Veículo");
			a.setHeaderText("Deseja excluir veículo?");
			a.setContentText("Tem certeza?");

			ButtonType sim = new ButtonType("Sim");
			ButtonType nao = new ButtonType("Não" , ButtonData.CANCEL_CLOSE);

			Stage s = (Stage) a.getDialogPane().getScene().getWindow();
			s.getIcons().add(new Image(this.getClass().getResource("/br/com/imagens/icone.png").toString()));

			DialogPane dialogPane = a.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("/br/com/view/application.css").toExternalForm());

			a.getButtonTypes().setAll(sim, nao);

			Optional<ButtonType> resultado = a.showAndWait();

			if (resultado.get() == sim){

				if(VeiculoTranspDAO.deleteVeiculoTransp(vt.getCodVeiculoTransp()) ){

					TipoVeiculoDAO.deleteTipoVeiculo(vt.getTipoVeiculo().getCodTipoVeiculo());

					Alerta alertaInfo = new Alerta();
					alertaInfo.alertaInformation("Veículo", "Sucesso", "Veiculo Excluído com Sucesso");

					preencherVeiculo();
				}
				else{
					Alerta alertaWarning = new Alerta(); 
					alertaWarning.alertaWarning("Veiculo", "AVISO!", "Veículo não pode ser excluido!");
				}
			}	
		}
	}

	public void cancelarVeiculo (){
		tpTransp.getSelectionModel().select(0);

		tabVisualizar.setDisable(false);
		tabCadastrarVeiculo.setDisable(true);

		limparVeiculo();
	}

	public void concluidoVeiculo(){

		tpTransp.getSelectionModel().select(0);
		tabVisualizar.setDisable(false);
		tabCadastrar.setDisable(true);
		tabCadastrarVeiculo.setDisable(true);
	}

	public void limparVeiculo(){
		txtTipoVeic.clear();
		txtPlaca.clear();
	}

	public boolean validarCamposVeiculo(String... camposVeiculo) {

		boolean preenchido = false;
		for(String item : camposVeiculo){
			if(item.isEmpty()){
				preenchido = true;
				break;
			}else{
				preenchido = false;
			}
		}
		return preenchido;
	}

	private void buscarVeiculo(){

		List<VeiculoTransp> lstVeicFilt = VeiculoTranspDAO.buscarVeiculo("%"+txtBuscaVeiculo.getText()+"%");

		if (lstVeicFilt.isEmpty()){

			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaWarning("Veículo", "", "Nenhum registro encontrado!");

			txtBuscaVeiculo.clear();
			preencherVeiculo();
		}
		else{
			tbvVeiculoTransp.getItems().clear();
			tbvVeiculoTransp.getItems().addAll(lstVeicFilt);
		}
	}
}
