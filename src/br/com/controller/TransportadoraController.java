package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.model.Transportadora;
import br.com.view.Janelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

public class TransportadoraController implements Initializable {
	
	@FXML private AnchorPane acpTransp;
	
	@FXML private TextField txtBuscaTrans;
	@FXML private Button btnBuscaTrans;
	@FXML private Button btnNovaTransportadora;
	@FXML private Button btnEditarTrans;
	@FXML private Button btnExcluirTrans;
	
	@FXML private TabPane tpTransp;
	@FXML private Tab tabVisualizar;
	@FXML private Tab tabCadastrar;
	
	@FXML private TableView<Transportadora> tvTransp;
	@FXML private TableColumn <Transportadora, String> clnTransp;
	@FXML private TableColumn <Transportadora, String> clnCnpj;
	@FXML private TableColumn <Transportadora, String> clnEmail;
	@FXML private TableColumn <Transportadora, String> clnFone;
	@FXML private TableColumn <Transportadora, String> clnResp;
	
	@FXML private Label lblEdicaoCadas;
	@FXML private Label lblNomeTrans;
	@FXML private TextField txtNomeTrans;
	@FXML private Label lblEmailTrans;
	@FXML private TextField txtEmailTrans;
	@FXML private Label lblTelefoneTrans;
	@FXML private TextField txtTelefoneTrans;
	@FXML private Label lblCnpjTransp;
	@FXML private TextField txtCnpjTransp;
	@FXML private Label lblResponsavelTrans;
	@FXML private TextField txtResponsavelTransp;
	@FXML private Button btnCadastrarTrans;
	@FXML private Button btnConcluido;
	@FXML private Button btnCancelarTransp;

	boolean modoEdicao = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTooltip();
		
		tabCadastrar.setDisable(true);
		
		preencherTransportadora();
		
		btnNovaTransportadora.setOnAction(m -> nova());
		
		btnConcluido.setDisable(true);	
	}
	
	private void preencherTransportadora(){
		
		clnTransp.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("nomeTransportadora"));
		clnCnpj.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("cnpjTransportadora"));
		clnEmail.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("emailTransportadora"));
		clnFone.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("telefoneTransportadora"));
		clnResp.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("responsavelTransportadora"));
		
		List<Transportadora> lst = Transportadora.selecionarTodas();
		
		System.out.println(lst);
		tvTransp.getItems().clear();
		tvTransp.getItems().addAll(lst);
	}
	
	private void nova() {
		
		tabCadastrar.setText("Cadastrar");
		tpTransp.getSelectionModel().select(1);
		
		tabVisualizar.setDisable(true);
		tabCadastrar.setDisable(false);
		
		btnCadastrarTrans.setOnAction(b -> cadastrarTransportadora());
		
		btnCancelarTransp.setOnAction(v -> cancelar());
	}
	
	public void cadastrarTransportadora() {

		inserirTransportadora();

		btnConcluido.setOnAction(p -> concluido());
		
		btnConcluido.setOnKeyPressed(k -> {
			if (k.getCode() == KeyCode.ENTER){
				concluido();
			}
		});
		
		btnCancelarTransp.setOnAction(p -> cancelar());
		btnCadastrarTrans.setOnAction(x -> inserirTransportadora());
		
		btnConcluido.setDisable(false);
		
		limparTrans();			
	}
	
	private void inserirTransportadora() {
		
		if(!modoEdicao){
			
			Transportadora novo = new Transportadora();
			
			novo.setCnpjTransportadora(txtCnpjTransp.getText());
			novo.setNomeTransportadora(txtNomeTrans.getText());
			novo.setEmailTransportadora(txtEmailTrans.getText());
			novo.setResponsavelTransportadora(txtResponsavelTransp.getText());
			novo.setTelefoneTransportadora(txtTelefoneTrans.getText());
			
			if(Transportadora.insert(novo)){
				
				PopUpController sucesso = new PopUpController("SUCESSO", "Transportadora cadastrada com sucesso!", "Ok");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "Sucesso Transportadora", false, sucesso);
		
				btnCadastrarTrans.setOnAction(a -> cadastrarTransportadora());
				btnConcluido.setOnAction(p -> concluido());
					
				btnConcluido.setOnKeyPressed(k -> {
					if (k.getCode() == KeyCode.ENTER){
							concluido();
					}
				});
					
				preencherTransportadora();
				limparTrans();
			}
			else{
				PopUpController sucess = new PopUpController("ERRO", "ERRO", "Ok");
				Janelas je = new Janelas();
				je.abrirPopup("PopUp.fxml", new Stage(), "erro nao cadastrou", false, sucess);	
			}
	
		}
		else{
		
			Transportadora tn = tvTransp.getSelectionModel().getSelectedItem();
			
			if(tn == null){
				PopUpController erro = new PopUpController("ERRO", "Nenhum item selecionado", "Fechar");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "ERRO", false, erro);	
			}
			else{
				
				tabVisualizar.setDisable(true);
				
				lblEdicaoCadas.setText("Atualizar Transportadora");
				btnCadastrarTrans.setText("Atualizar");
				
				tpTransp.getSelectionModel().select(1);
				
				txtNomeTrans.setText(tn.getNomeTransportadora());
				txtEmailTrans.setText(tn.getEmailTransportadora());
				txtTelefoneTrans.setText(tn.getTelefoneTransportadora());
				txtCnpjTransp.setText(tn.getCnpjTransportadora());
				txtResponsavelTransp.setText(tn.getResponsavelTransportadora());
			
				btnCancelarTransp.setOnAction(i -> cancelar());
				
				btnCadastrarTrans.setOnAction(a -> atualizar());
				
				btnConcluido.setOnAction(p -> concluido());
				
				btnConcluido.setOnKeyPressed(k -> {
					if (k.getCode() == KeyCode.ENTER){
						concluido();
					}
				});
				modoEdicao = false;	
			}
		}
	}
	
	// Event Listener on Button[#btnEditarTrans].onAction
	@FXML
	public void editarTrans(ActionEvent event) {
		
		tabCadastrar.setText("Editar");
		
		modoEdicao = true;
		btnCadastrarTrans.setDisable(false);
		tabCadastrar.setDisable(false);
		
		inserirTransportadora();
	}
		
	private void atualizar(){
		
		btnConcluido.setDisable(true);
		
		//Transportadora cod = tvTransp.getSelectionModel().getSelectedItem();
		
		Transportadora up = new Transportadora();
		
		up.setCnpjTransportadora(txtCnpjTransp.getText());
		up.setNomeTransportadora(txtNomeTrans.getText());
		up.setEmailTransportadora(txtEmailTrans.getText());
		up.setResponsavelTransportadora(txtResponsavelTransp.getText());
		up.setTelefoneTransportadora(txtTelefoneTrans.getText());
		
		//up.setCodTransportadora(cod);
		
		Transportadora.update(up);
				
		preencherTransportadora();
		limparTrans();
				
		PopUpController sucesso = new PopUpController("SUCESSO", "Transportadora Atualizada com sucesso!", "Ok");
		Janelas j = new Janelas();
		j.abrirPopup("PopUp.fxml", new Stage(), "Sucesso Transportadora", false, sucesso);
			
		btnConcluido.setDisable(false);
		btnCadastrarTrans.setDisable(true);
		btnCancelarTransp.setDisable(true);

		
				
			
		
	}
	
	// Event Listener on Button[#btnExcluirTrans].onAction
	@FXML
	public void excluirTrans(ActionEvent event) {
		
		Transportadora t = tvTransp.getSelectionModel().getSelectedItem();
		
		if(t == null){
			PopUpController erro = new PopUpController("ERRO", "Nenhum item selecionado", "Fechar");
			Janelas j = new Janelas();
			j.abrirPopup("PopUp.fxml", new Stage(), "Sucesso Transportadora", false, erro);
		}
		else{
		
			if(Transportadora.delete(t.getCodTransportadora())){
				
				PopUpController erro = new PopUpController("SUCESSO", "EXCLUIDO", "Ok");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "Sucesso Transportadora", false, erro);
				
				preencherTransportadora();
			}
			else{
				PopUpController erro = new PopUpController("ERRO", "ERRO", "Ok");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "ERRO", false, erro);
				
			}
	
		}
	}
	
	// Event Listener on Button[#btnBuscaTrans].onAction
	@FXML
	public void buscarTransportadora(ActionEvent event) {
		
	}
	
	private void limparTrans() {
		txtCnpjTransp.clear();
		txtEmailTrans.clear();
		txtNomeTrans.clear();
		txtResponsavelTransp.clear();
		txtTelefoneTrans.clear();
	}
	
	public void concluido() {
		lblEdicaoCadas.setText("Cadastrar Transportadora");
		btnCadastrarTrans.setText("Cadastrar");
		btnCadastrarTrans.setDisable(true);
		
		btnConcluido.setDisable(true);
		btnCadastrarTrans.setDisable(false);
		btnCancelarTransp.setDisable(false);
		
		tabVisualizar.setDisable(false);
		tpTransp.getSelectionModel().select(0);
		modoEdicao = false;
		
		tabCadastrar.setDisable(true);
		
	}
	
	public void cancelar(){
		
		tpTransp.getSelectionModel().select(0);
		
		tabVisualizar.setDisable(false);
		tabCadastrar.setDisable(true);
		
		modoEdicao = false;
		
		lblEdicaoCadas.setText("Cadastro de Transportadora");
		btnCadastrarTrans.setText("Cadastrar");

		limparTrans();
	}
	
	public void initTooltip() {
		
		Tooltip.install(txtNomeTrans, new Tooltip("Digite aqui o nome da transportadora."));	
	}	
	
}
