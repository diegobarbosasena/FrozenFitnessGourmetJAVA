package br.com.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ajudantes.MySqlConexao;
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
		
		btnNovaTransportadora.setOnAction(m -> tpTransp.getSelectionModel().select(1));
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
	
	// Event Listener on Button[#btnCadastrarTrans].onAction
	@FXML
	public void cadastrarTransportadora(ActionEvent event) {
		
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
		btnCancelarTransp.setOnAction(n -> tpTransp.getSelectionModel().select(0));
		
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
		else if (modoEdicao){
		
			Transportadora tn = tvTransp.getSelectionModel().getSelectedItem();
			

			btnCancelarTransp.setOnAction(i -> cancelar());
			
			if(tn == null){
				PopUpController erro = new PopUpController("ERRO", "Nenhum item selecionado", "Fechar");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "Sucesso Transportadora", false, erro);	
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
			
				btnCadastrarTrans.setOnAction(a -> atualizar());
				modoEdicao = false;
				
				btnConcluido.setOnAction(p -> concluido());
				
				btnConcluido.setOnKeyPressed(k -> {
					if (k.getCode() == KeyCode.ENTER){
						concluido();
					}
				});
				
			}
		}
	}
	
	// Event Listener on Button[#btnEditarTrans].onAction
	@FXML
	public void editarTrans(ActionEvent event) {
			
		modoEdicao = true;
		btnCadastrarTrans.setDisable(false);
		inserirTransportadora();
	}
		
	private void atualizar(){
		Transportadora tn = tvTransp.getSelectionModel().getSelectedItem();
			
		btnConcluido.setDisable(true);
			
		Connection c = MySqlConexao.ConectarDb();
			
		String sqlAtualizar = "UPDATE tblTransportadora set nomeTransportadora = ?, emailTransportadora = ?, telefoneTransportadora = ?, cnpjTransportadora = ?, responsavelTransportadora = ? WHERE codTransportadora = ?";
		
		PreparedStatement parametros;
			
		try {
			parametros = c.prepareStatement(sqlAtualizar);
				
			parametros.setString(1, txtNomeTrans.getText());
			parametros.setString(2, txtEmailTrans.getText());
			parametros.setString(3, txtTelefoneTrans.getText());
			parametros.setString(4, txtCnpjTransp.getText());
			parametros.setString(5, txtResponsavelTransp.getText());
				
			parametros.setInt(6, tn.getCodTransportadora());
				
			parametros.executeUpdate();
				
			c.close();
				
			preencherTransportadora();
			limparTrans();
				
			PopUpController sucesso = new PopUpController("SUCESSO", "Transportadora Atualizada com sucesso!", "Ok");
			Janelas j = new Janelas();
			j.abrirPopup("PopUp.fxml", new Stage(), "Sucesso Transportadora", false, sucesso);
			
			btnConcluido.setDisable(false);
			btnCadastrarTrans.setDisable(true);
			btnCancelarTransp.setDisable(true);

		} catch (SQLException e) {
			e.printStackTrace();
				
			PopUpController sucesso = new PopUpController("SUCESSO", "ERRO nao atualizou!", "Ok");
			Janelas j = new Janelas();
			j.abrirPopup("PopUp.fxml", new Stage(), "ERRO ", false, sucesso);
		}
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
				preencherTransportadora();
				
				PopUpController erro = new PopUpController("SUCESSO", "EXCLUIDO", "Ok");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "Sucesso Transportadora", false, erro);
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
		
		tabVisualizar.setDisable(false);
		tpTransp.getSelectionModel().select(0);
		modoEdicao = false;

	}
	
	public void cancelar(){
		
		tpTransp.getSelectionModel().select(0);
		tabVisualizar.setDisable(false);
		modoEdicao = false;
		lblEdicaoCadas.setText("Cadastro de Transportadora");
		btnCadastrarTrans.setText("Cadastrar");
		
		limparTrans();
	}
	
}
