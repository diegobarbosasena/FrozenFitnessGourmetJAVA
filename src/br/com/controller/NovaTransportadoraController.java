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
	private Label lblEmailTrans;
	@FXML
	private Label lblTelefoneTrans;
	@FXML
	private Label lblCnpjTransp;
	@FXML
	private Label lblResponsavelTrans;
	@FXML
	private TextField txtNomeTrans;
	@FXML
	private TextField txtEmailTrans;
	@FXML
	private TextField txtTelefoneTrans;
	@FXML
	private TextField txtCnpjTransp;
	@FXML
	private TextField txtResponsavelTransp;
	@FXML
	private Button btnCadastrar;

	// Event Listener on Button[#btnCadastrar].onAction
	@FXML
	public void cadastrar(ActionEvent event) {
		
		System.out.println();
		System.out.println("JA FOI!!");
		System.out.println();
		
		if(vazioTrans(txtCnpjTransp.getText(), txtEmailTrans.getText(), txtNomeTrans.getText(), txtResponsavelTransp.getText(), txtTelefoneTrans.getText())){
			System.out.println("Preencher os dados");
			
			Janelas erroTrans = new Janelas();
			erroTrans.abrir("ErroTrans.fxml", new Stage());	
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
		boolean bo = false;
		
		for(String item : strings){
			if(item.isEmpty()){
				bo = true;
				break;
			}else{
				bo = false;
			}
		}
		return bo;
	}

	public void limparTrans() {
		txtCnpjTransp.clear();
		txtEmailTrans.clear();
		txtNomeTrans.clear();
		txtResponsavelTransp.clear();
		txtTelefoneTrans.clear();
	}
	
	// Event Listener on Button[#btnConcluido].onAction
	@FXML
	public void concluido(ActionEvent event) {	
		Stage novaTrans = (Stage)btnConcluido.getScene().getWindow();
		novaTrans.close();
	}

}
