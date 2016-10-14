package br.com.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ajudantes.MySqlConexao;
import br.com.model.Transportadora;
import br.com.view.Alerta;
import br.com.view.Janelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TransportadoraController implements Initializable{
	
	@FXML private AnchorPane acpTransp;
	@FXML private Button btnEditarTrans;
	@FXML private Button btnExcluirTrans;
	@FXML private Button btnNovaTransportadora;
	@FXML private TextField txtBuscaTrans;
	@FXML private Button btnBuscaTrans;
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
	
	@FXML private Label lblEdicaoCadas;
	
	@FXML private TableView<Transportadora> tvTransp;
	@FXML private TableColumn <Transportadora, String> clnTransp;
	@FXML private TableColumn <Transportadora, String> clnCnpj;
	@FXML private TableColumn <Transportadora, String> clnEmail;
	@FXML private TableColumn <Transportadora, String> clnFone;
	@FXML private TableColumn <Transportadora, String> clnResp;
	@FXML private Button btnConcluido;
	@FXML private TabPane tpTransp;
	
	boolean modoEdicao = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preencherTransportadora();
		lblEdicaoCadas.setText("Cadastro de Transportadora");
	}
	
	private static List<Transportadora> selecionarTodas() {
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelect = "SELECT * FROM tblTransportadora ORDER BY codTransportadora DESC; ";
		
		List <Transportadora> tr = new ArrayList<>(); 
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelect);

			while(rs.next()){
				
				Transportadora t = new Transportadora();
				
				t.setCodTransportadora(rs.getInt("codTransportadora"));
				t.setNomeTransportadora(rs.getString("nomeTransportadora"));
				t.setEmailTransportadora(rs.getString("emailTransportadora"));
				t.setTelefoneTransportadora(rs.getString("telefoneTransportadora"));
				t.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				t.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));
				
				tr.add(t);			
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tr;
	}
	
	private void preencherTransportadora(){

		clnTransp.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("nomeTransportadora"));
		clnCnpj.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("cnpjTransportadora"));
		clnEmail.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("emailTransportadora"));
		clnFone.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("telefoneTransportadora"));
		clnResp.setCellValueFactory(new PropertyValueFactory<Transportadora, String>("responsavelTransportadora"));
		
		List<Transportadora> lst = selecionarTodas();
		
		System.out.println(lst);
		tvTransp.getItems().clear();
		tvTransp.getItems().addAll(lst);
	}
	
	// Event Listener on Button[#btnNovaTransportadora].onAction
	@FXML
	private void novaTransportadora(ActionEvent event) {
		tpTransp.getSelectionModel().select(1);
	}
	
	@FXML
	private void cadastrarTransportadora() {
		System.out.println();
		System.out.println("Quase cadastro...");

		lblEdicaoCadas.setText("Cadastro de Transportadora");
		inserirTransportadora();
				
		System.out.println();
		System.out.println("NOME TRASNP" + txtNomeTrans.getText());
		System.out.println("EMAIL TRASNP" + txtEmailTrans.getText());
		System.out.println("TELEFONE TRASNP" + txtTelefoneTrans.getText());
		System.out.println("CnPJT TRASNP" + txtCnpjTransp.getText());
		System.out.println("RESPONSÁVEL TRASNP" + txtResponsavelTransp.getText());
				
		PopUpController sucesso = new PopUpController("SUCESSO", "Transportadora cadastrada com sucesso!", "Ok");
		Janelas j = new Janelas();
		j.abrirPopup("PopUp.fxml", new Stage(), "Sucesso Transportadora", false, sucesso);
				
		limparTrans();
				
		System.out.println();
		System.out.println("SUCESSO cadastro de transportadora");	
	}
	
	private void inserirTransportadora() {
		
		if(!modoEdicao){
			lblEdicaoCadas.setText("Cadastro de Transportadora");
			
			Connection c = MySqlConexao.ConectarDb();
			
			String sqlInsert = "INSERT INTO tblTransportadora (nomeTransportadora, emailTransportadora, telefoneTransportadora, cnpjTransportadora, responsavelTransportadora) VALUES ( ?, ?, ?, ?, ?); ";
			
			PreparedStatement parametros;
			
			try {
				parametros = c.prepareStatement(sqlInsert);
				
				parametros.setString(1, txtNomeTrans.getText());
				parametros.setString(2, txtEmailTrans.getText());
				parametros.setString(3, txtTelefoneTrans.getText());
				parametros.setString(4, txtCnpjTransp.getText());
				parametros.setString(5, txtResponsavelTransp.getText());
	
				parametros.executeUpdate();
				
				c.close();
	
				preencherTransportadora();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			btnCadastrarTrans.setOnAction(x -> inserirTransportadora());
		}	
		limparTrans();
		modoEdicao = true;
	}	
	
	private void atualizar(){
		Transportadora tn = tvTransp.getSelectionModel().getSelectedItem();
		
		Connection c = MySqlConexao.ConectarDb();
		String sqlAtualizar = "UPDATE tblTransportadora set nomeTransportadora = ?, emailTransportadora = ?, telefoneTransportadora = ?, cnpjTransportadora = ?, responsavelTransportadora = ? WHERE codTransportadora = ?";
	
		System.out.println(sqlAtualizar);
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editarTrans() {
		modoEdicao = true;
		
		lblEdicaoCadas.setText("Atualização de Transportadora");
		if(modoEdicao){
		
			Transportadora tn = tvTransp.getSelectionModel().getSelectedItem();
			
			if(tn == null){
				PopUpController erro = new PopUpController("ERRO", "Nenhum item selecionado", "Fechar");
				Janelas j = new Janelas();
				j.abrirPopup("PopUp.fxml", new Stage(), "Sucesso Transportadora", false, erro);	
				
				lblEdicaoCadas.setText("Cadastro de Transportadora");
			}
			else{
				tpTransp.getSelectionModel().select(1);
				
				btnCadastrarTrans.setText("Atualizar");
				
				txtNomeTrans.setText(tn.getNomeTransportadora());
				txtEmailTrans.setText(tn.getEmailTransportadora());
				txtTelefoneTrans.setText(tn.getTelefoneTransportadora());
				txtCnpjTransp.setText(tn.getCnpjTransportadora());
				txtResponsavelTransp.setText(tn.getResponsavelTransportadora());
			
				btnCadastrarTrans.setOnAction(a -> atualizar());
			}
		}
	}
	
	// Event Listener on Button[#btnExcluirTrans].onAction
	@FXML
	private void excluirTrans(ActionEvent event) {
		
		Transportadora t = tvTransp.getSelectionModel().getSelectedItem();
		
		if(t == null){
			PopUpController erro = new PopUpController("ERRO", "Nenhum item selecionado", "Fechar");
			Janelas j = new Janelas();
			j.abrirPopup("PopUp.fxml", new Stage(), "Sucesso Transportadora", false, erro);
		}
		else{
		
			Connection c = MySqlConexao.ConectarDb();
			String sqlDeletar = "DELETE FROM tblTransportadora WHERE CodTransportadora = ?;";
				
			PreparedStatement parametros;
			try {
				parametros = c.prepareStatement(sqlDeletar);
				parametros.setInt(1, t.getCodTransportadora());
				parametros.executeUpdate();
					
				c.close();
					
				preencherTransportadora();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	private void limparTrans() {
		txtCnpjTransp.clear();
		txtEmailTrans.clear();
		txtNomeTrans.clear();
		txtResponsavelTransp.clear();
		txtTelefoneTrans.clear();
	}

	// Event Listener on Button[#btnBuscaTrans].onAction
	@FXML
	private void buscarTransportadora(ActionEvent event) {
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
	
	@FXML 
	private void concluido(ActionEvent event) {
		tpTransp.getSelectionModel().select(0);
	}
}
