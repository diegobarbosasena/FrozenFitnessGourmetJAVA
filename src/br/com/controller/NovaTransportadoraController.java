package br.com.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import br.com.view.Janelas;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

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
		System.out.println("JA FOI!!");
		System.out.println();
		
		if(vazioTrans(txtCnpjTransp.getText(), txtEmailTrans.getText(), txtNomeTrans.getText(), txtResponsavelTransp.getText(), txtTelefoneTrans.getText())){
			System.out.println("Preencher os dados");
			
			Janelas erroTrans = new Janelas();
			erroTrans.abrir("ErroTrans.fxml", new Stage(), "Erro Tranportadora", false);	
		}
		else{
			System.out.println(txtNomeTrans.getText());
			System.out.println(txtEmailTrans.getText());
			System.out.println(txtTelefoneTrans.getText());
			System.out.println(txtCnpjTransp.getText());
			System.out.println(txtResponsavelTransp.getText());
			
			limparTrans();
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
