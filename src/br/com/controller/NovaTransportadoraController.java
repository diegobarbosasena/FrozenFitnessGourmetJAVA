package br.com.controller;

import java.util.List;

import br.com.model.Transportadora;
import br.com.view.Janelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovaTransportadoraController {
	@FXML
	private Button btnConcluido;
	@FXML
	private Label lblNomeTrans;
	@FXML
	private TextField txtNomeTrans;
	@FXML
	private Label lblEmailTrans;
	@FXML
	private TextField txtEmailTrans;
	@FXML
	private Label lblTelefoneTrans;
	@FXML
	private TextField txtTelefoneTrans;
	@FXML
	private Label lblResponsavelTrans;
	@FXML
	private TextField txtCnpjTransp;
	@FXML
	private Label lblCnpjTransp;
	@FXML
	private TextField txtResponsavelTransp;
	@FXML
	private Button btnCadastrarTrans;

	
	// Event Listener on Button[#btnCadastrarTrans].onAction
	@FXML
	public void cadastrarTransportadora(ActionEvent event) {
		
		System.out.println();
		System.out.println("Quase cadastro...");

		if(vazioTrans(txtCnpjTransp.getText(), txtEmailTrans.getText(), txtNomeTrans.getText(), txtResponsavelTransp.getText(), txtTelefoneTrans.getText())){
			
			System.out.println();
			System.out.println("ERRO Preencher os dados da transportadora");
			
			PopUpController erro = new PopUpController("ERRO AO CADASTRAR TRANSPORTADORA", "Preencha todos os campos!", "Fechar");
			
			Janelas j = new Janelas();
			j.abrirPopup("PopUp.fxml", new Stage(), "Erro Transportadora", false, erro);
		}
		else{
			
			List<Transportadora> lst = Transportadora.inserirTransportadora(txtNomeTrans.getText(),
					txtEmailTrans.getText(),
					txtTelefoneTrans.getText(),
					txtCnpjTransp.getText(),
					txtResponsavelTransp.getText()
					);
			
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
	}
	
	

	public boolean vazioTrans(String... strings) {		
		boolean bolean = false;
		
		for(String item : strings){
			if(item.isEmpty()){
				bolean = true;
				break;
			}else{
				bolean = false;
			}
		}
		return bolean;
	}
	
	// Event Listener on Button[#btnConcluido].onAction
	@FXML
	public void concluido(ActionEvent event) {
		Stage novaTrans = (Stage)btnConcluido.getScene().getWindow();
		novaTrans.close();
	}
	
	public void limparTrans() {
		txtCnpjTransp.clear();
		txtEmailTrans.clear();
		txtNomeTrans.clear();
		txtResponsavelTransp.clear();
		txtTelefoneTrans.clear();
	}
}
