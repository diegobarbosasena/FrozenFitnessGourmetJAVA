package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.model.Transportadora;
import br.com.view.Janelas;
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
		
		preencherTransportadora();
		initTooltip();
		
		tabCadastrar.setDisable(true);
	
		txtBuscaTrans.textProperty().addListener(a -> buscarTransportadora());

		btnNovaTransportadora.setOnAction(b -> nova());
		btnEditarTrans.setOnAction(c -> editarTrans());
		btnExcluirTrans.setOnAction(d -> excluirTrans());	
	}

	private void preencherTransportadora(){
		
		clnTransp.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("nomeTransportadora"));
		clnCnpj.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("cnpjTransportadora"));
		clnEmail.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("emailTransportadora"));
		clnFone.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("telefoneTransportadora"));
		clnResp.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("responsavelTransportadora"));
		
		List<Transportadora> lst = Transportadora.selecionarTodas();
		
		tvTransp.getItems().clear();
		tvTransp.getItems().addAll(lst);
	}
	
	public void initTooltip() {
		
		Tooltip.install(txtNomeTrans, new Tooltip("Digite aqui o nome da transportadora."));	
	}
	
	private void nova() {
		
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
		btnCancelarTransp.setOnAction(g -> cancelar());
	}
	
	public void cadastrarTransportadora() {

		inserirTransportadora();
		
		btnCancelarTransp.setOnAction(h -> cancelar());
		btnCancelarTransp.setOnKeyPressed(i -> {
		    if (i.getCode() == KeyCode.ENTER) {
		    	concluido();
		    }
		});	
	}
	
	private void inserirTransportadora() {
		
		if(!modoEdicao){
			
			if(validarCamposTransportadora(txtNomeTrans.getText(),txtCnpjTransp.getText(),txtEmailTrans.getText(),txtResponsavelTransp.getText(), txtTelefoneTrans.getText())){
				
				PopUpController erro = new PopUpController("ERRO", "Preencha todos os campos!", "OK");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, erro);
			}
			else{
				
				Transportadora novo = new Transportadora();
			
				novo.setNomeTransportadora(txtNomeTrans.getText());
				novo.setCnpjTransportadora(txtCnpjTransp.getText());
				novo.setEmailTransportadora(txtEmailTrans.getText());
				novo.setResponsavelTransportadora(txtResponsavelTransp.getText());
				novo.setTelefoneTransportadora(txtTelefoneTrans.getText());
				
				if(Transportadora.insert(novo)){
					
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
				}
				else{
					PopUpController erro = new PopUpController("ERRO", "Transportadora não cadastrada", "OK");
					Janelas je = new Janelas();
					je.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, erro);	
				}
			}	
		}
		else{
		
			Transportadora tn = tvTransp.getSelectionModel().getSelectedItem();
			
			if(tn == null){
				PopUpController erro = new PopUpController("ERRO", "Nenhum item selecionado", "FECHAR");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, erro);	
			}
			else{
				
				tabVisualizar.setDisable(true);
				tpTransp.getSelectionModel().select(1);
				
				lblEdicaoCadas.setText("Atualizar Transportadora");
				btnCadastrarTrans.setText("Atualizar");

				txtNomeTrans.setText(tn.getNomeTransportadora());
				txtEmailTrans.setText(tn.getEmailTransportadora());
				txtTelefoneTrans.setText(tn.getTelefoneTransportadora());
				txtCnpjTransp.setText(tn.getCnpjTransportadora());
				txtResponsavelTransp.setText(tn.getResponsavelTransportadora());
			
				btnCadastrarTrans.setOnAction(l -> atualizar());
				btnCancelarTransp.setOnAction(m -> cancelar());
				btnConcluido.setOnAction(n -> concluido());
				
				modoEdicao = false;	
			}
		}
	}
	
	private boolean validarCamposTransportadora(String... camposTrans) {
		
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
	
	private void atualizar(){
		
		Transportadora cod = tvTransp.getSelectionModel().getSelectedItem();
		
		Transportadora up = new Transportadora();
		
		up.setCnpjTransportadora(txtCnpjTransp.getText());
		up.setNomeTransportadora(txtNomeTrans.getText());
		up.setEmailTransportadora(txtEmailTrans.getText());
		up.setResponsavelTransportadora(txtResponsavelTransp.getText());
		up.setTelefoneTransportadora(txtTelefoneTrans.getText());
		
		up.setCodTransportadora(cod.getCodTransportadora());
		
		if(Transportadora.update(up)){
			
			PopUpController sucesso = new PopUpController("SUCESSO", "Transportadora Atualizada com sucesso!", "OK");
			Janelas j = new Janelas();
			j.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, sucesso);
			
			btnConcluido.setDisable(false);
			
			btnCancelarTransp.setDisable(true);
			
			btnCadastrarTrans.setDisable(true);
			
			preencherTransportadora();
			limparTrans();
			
			modoEdicao = false;
		}
		else{
			
			PopUpController erro = new PopUpController("ERRO", "Erro ao atualizar Transportadora!", "FECHAR");
			Janelas e = new Janelas();
			e.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, erro);	
		}
	}

	public void editarTrans() {
		
		tabCadastrar.setText("Editar");
		tabCadastrar.setDisable(false);
		
		btnCancelarTransp.setDisable(false);
		btnConcluido.setDisable(true);
		
		modoEdicao = true;
		
		inserirTransportadora();
	}
		
	public void excluirTrans() {
		
		Transportadora t = tvTransp.getSelectionModel().getSelectedItem();
		
		if(t == null){
			PopUpController erro = new PopUpController("ERRO", "Nenhum item selecionado", "FECHAR");
			Janelas j = new Janelas();
			j.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, erro);
		}
		else{
		
			if(Transportadora.delete(t.getCodTransportadora())){
				
				PopUpController erro = new PopUpController("SUCESSO", "Transportadora excluída com sucesso!", "OK");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, erro);
				
				preencherTransportadora();
			}
			else{
				PopUpController erro = new PopUpController("ERRO", "Transportadora não pode ser excluída", "FECHAR");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, erro);
			}
		}
	}
	
	public void buscarTransportadora() {
		
		List<Transportadora> lstTransFilt = Transportadora.filtrar("%"+txtBuscaTrans.getText()+"%");
		
		if (lstTransFilt.isEmpty()){
			
			PopUpController erro = new PopUpController("ERRO", "Nenhum registro encontrado!", "OK");
			Janelas j = new Janelas();
			j.abrirPopup("PopUp.fxml", new Stage(), "Transportadora", false, erro);
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
	
	private void limparTrans() {
		txtCnpjTransp.clear();
		txtEmailTrans.clear();
		txtNomeTrans.clear();
		txtResponsavelTransp.clear();
		txtTelefoneTrans.clear();
	}
	
}
