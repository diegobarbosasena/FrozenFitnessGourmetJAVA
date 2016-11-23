package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.DAO.CidadeDAO;
import br.com.DAO.EnderecoDAO;
import br.com.DAO.EstadoDAO;
import br.com.DAO.TransportadoraDAO;
import br.com.DAO.VeiculoTranspDAO;
import br.com.ajudantes.Mascaras;
import br.com.model.Cidade;
import br.com.model.Endereco;
import br.com.model.Estado;
import br.com.model.TipoVeiculo;
import br.com.model.Transportadora;
import br.com.model.VeiculoTransp;
import br.com.view.Alerta;
import br.com.view.Janelas;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TransportadoraController implements Initializable{
	@FXML
	private AnchorPane acpTransp;
	@FXML
	private TabPane tpTransp;
	@FXML
	private Tab tabVisualizar;
	@FXML 
	private Tab tabCadastrarVeiculo;
	@FXML
	private TextField txtBuscaTrans;
	@FXML
	private Button btnNovaTransportadora;
	@FXML
	private Button btnEditarTrans;
	@FXML
	private Button btnExcluirTrans;
	@FXML
	private Button btnAddVeic;
	@FXML
	private TableView <Transportadora>tvTransp;
	@FXML
	private TableColumn <Transportadora, String> tbcRazao;
	@FXML
	private TableColumn <Transportadora, String> tbcTelCont;
	@FXML
	private TableColumn <Transportadora, String> tbcEmailCont;
	@FXML
	private TableColumn <Transportadora, String> tbcResp;
	@FXML
	private TableColumn <Transportadora, String> tbcEndereco;
	@FXML
	private Button btnEditarVeic;
	@FXML
	private Button btnExcluirVeic;
	@FXML
	private TableView <VeiculoTransp> tbvVeiculoTransp;
	@FXML
	private TableColumn <VeiculoTransp,TipoVeiculo> tbcTipoVeic;
	@FXML
	private TableColumn <VeiculoTransp,String> tbcPlacaVeic;
	@FXML
	private Tab tabCadastrar;
	@FXML
	private Label lblEdicaoCadas;
	@FXML
	private Label lblRazao;
	@FXML
	private TextField txtRazao;
	@FXML
	private Label lblNomeFantasia;
	@FXML
	private TextField txtNomeFant;
	@FXML
	private Label lblCnpjTransp;
	@FXML
	private TextField txtCnpjTransp;
	@FXML
	private Label lblTelPrin;
	@FXML
	private TextField txtTelPrin;
	@FXML
	private Label lblTelCont;
	@FXML
	private TextField txtTelCont;
	@FXML
	private Label lblEmailPrin;
	@FXML
	private TextField txtEmailPrin;
	@FXML
	private Label lblEmailCont;
	@FXML
	private TextField txtEmailCont;
	@FXML
	private Label lblResponsavel;
	@FXML
	private TextField txtResponsavelTransp;
	@FXML
	private Label lblEnd;
	@FXML
	private TextField txtEndTransp;
	@FXML
	private Label lblNroTransp;
	@FXML
	private TextField txtNroTransp;
	@FXML
	private Label lblCepTransp;
	@FXML
	private TextField txtCepTransp;
	@FXML
	private Label lblBairroTransp;
	@FXML
	private TextField txtBairroTransp;
	@FXML
	private Label lblComplementoTransp;
	@FXML
	private TextField txtComplementoTransp;
	@FXML
	private Label lblEstadoTransp;
	@FXML
	private ComboBox <Estado> cboEstadoTransp;
	@FXML
	private Label lblCidadeTransp;
	@FXML
	private ComboBox <Cidade> cboCidadeTransp;
	@FXML
	private Button btnCadastrarTrans;
	@FXML
	private Button btnConcluido;
	@FXML
	private Button btnCancelarTransp;
	@FXML
	private Label lblCadAtrVeic;
	@FXML
	private Label lblTransp;
	@FXML
	private TextField txtTransp;
	@FXML
	private Label lblTipoVeic;
	@FXML
	private TextField txtTipoVeic;
	@FXML
	private Label lblPlaca;
	@FXML
	private TextField txtPlaca;
	@FXML
	private Button btnCadastrarVeiculo;
	@FXML
	private Button btnCancelarVeiculo;
	
	boolean modoEdicao = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Mascaras.mascaraCNPJ(txtCnpjTransp);
		Mascaras.mascaraTelefone(txtTelPrin);
		Mascaras.mascaraTelefone(txtTelCont);
		Mascaras.mascaraEmail(txtEmailPrin);
		Mascaras.mascaraEmail(txtEmailCont);
		Mascaras.mascaraCEP(txtCepTransp);
		
		preencherTransportadora();
		initTooltip();
		
		tabCadastrar.setDisable(true);
		tabCadastrarVeiculo.setDisable(true);
	
		txtBuscaTrans.textProperty().addListener(a -> {
			if(!txtBuscaTrans.getText().isEmpty())
				buscarTransportadora();
			else
				preencherTransportadora();
		});

		btnNovaTransportadora.setOnAction(b -> nova());
		btnEditarTrans.setOnAction(c -> editarTrans());
		btnExcluirTrans.setOnAction(d -> excluirTrans());
		
		btnAddVeic.setOnAction(a -> {
			if (tvTransp.getSelectionModel().getSelectedItem() != null){
				adicionarVeiculo();
			}	
			else{
				Alerta alertaErro = new Alerta(); 
				alertaErro.alertaErro("Transportadora", "ERRO", "Selecione um item da tabela.");
			}
		});
		
		tvTransp.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Integer> arg0) {		
				if (tvTransp.getSelectionModel().getSelectedIndices() != null)
					preencherVeiculo();
			}
		});
		
		btnCancelarVeiculo.setOnAction(h -> cancelarVeiculo());
		btnCadastrarVeiculo.setOnAction(l -> cadastrarVeiculo());
		
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
		tbcEndereco.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("endereco"));
		tbcResp.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("responsavelTransportadora"));
		tbcTelCont.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("telefoneContato"));
		
		List<Transportadora> lst = TransportadoraDAO.selecionarTodas();
		
		tvTransp.getItems().clear();
		tvTransp.getItems().addAll(lst);
	}
	
	public void initTooltip() {
		
		Tooltip.install(txtRazao, new Tooltip("Digite aqui a Razão Social."));	
	}
	
	public void nova() {
		
		limparTrans();
		popularComboBox();
		
		tabCadastrar.setText("Cadastrar");
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
			
			if(
					validarCamposTransportadora( 
							txtBairroTransp.getText(),
							txtCepTransp.getText(),
							txtCnpjTransp.getText(),
							txtEmailCont.getText(),
							txtEmailPrin.getText(),
							txtTelPrin.getText(),
							txtTelCont.getText(),
							txtEndTransp.getText(),
							txtNomeFant.getText(),
							txtNroTransp.getText(),
							txtRazao.getText(),
							txtResponsavelTransp.getText()
							)	
				)		
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
					
					PopUpController sucesso = new PopUpController("SUCESSO", "Transportadora cadastrada com sucesso!", "Ok");
					Janelas jn = new Janelas();
					jn.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, sucesso);
					
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
				
				btnCadastrarTrans.setOnAction(l -> atualizar());
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
	
	public void atualizar(){
		
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
		
		if(
				validarCamposTransportadora( 
						txtBairroTransp.getText(),
						txtCepTransp.getText(),
						txtCnpjTransp.getText(),
						txtEmailCont.getText(),
						txtEmailPrin.getText(),
						txtTelPrin.getText(),
						txtTelCont.getText(),
						txtEndTransp.getText(),
						txtNomeFant.getText(),
						txtNroTransp.getText(),
						txtRazao.getText(),
						txtResponsavelTransp.getText()
				)
		){	
			
			PopUpController erro = new PopUpController("ERRO", "Preencha Todos os campos", "Fechar");
			Janelas e = new Janelas();
			e.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, erro);
		}
		else{
			
			if(TransportadoraDAO.update(upTransp) && EnderecoDAO.updateEnde(upEn)){
				
				limparTrans();
				
				PopUpController sucesso = new PopUpController("SUCESSO", "Transportadora Atualizada com sucesso!", "OK");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, sucesso);
				
				btnConcluido.setDisable(false);	
				btnCancelarTransp.setDisable(true);
				btnCadastrarTrans.setDisable(true);
				
				preencherTransportadora();
				
				modoEdicao = false;
			}
			else{
				
				PopUpController erro = new PopUpController("ERRO", "Erro ao atualizar Transportadora!", "Fechar");
				Janelas e = new Janelas();
				e.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, erro);	
			}
		}
			
	}

	public void editarTrans() {
			
		popularComboBox();
		
		tabCadastrar.setText("Editar");
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
		
			if(TransportadoraDAO.deleteTransp(t.getCodTransportadora()) ){
				
				EnderecoDAO.deleteEnde(t.getEndereco().getCodEndereco());
				
				PopUpController erro = new PopUpController("SUCESSO", "Transportadora excluída com sucesso!", "OK");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, erro);
				
				preencherTransportadora();
			}
			else{
				Alerta alertaWarning = new Alerta(); 
				alertaWarning.alertaWarning("Transportadora", "AVISO!", "Transportadora não pode ser excluida!");
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
	}
	
	private void adicionarVeiculo() {
		
		Transportadora transp = tvTransp.getSelectionModel().getSelectedItem();
		
		tabVisualizar.setDisable(true);
		tabCadastrarVeiculo.setDisable(false);
		
		tpTransp.getSelectionModel().select(2);
		
		txtTransp.setText(transp.getRazaoSocial());
	}
	
	private void cadastrarVeiculo() {
		
	}
	
	private void cancelarVeiculo (){
		tpTransp.getSelectionModel().select(0);
		
		tabVisualizar.setDisable(false);
		tabCadastrarVeiculo.setDisable(true);
		
		limparVeiculo();
	}
	
	public void limparVeiculo(){
		txtTipoVeic.clear();
		txtPlaca.clear();
	}
	
	
}
