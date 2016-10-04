package br.com.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
		
		System.out.println(txtNomeTrans.getText());
		System.out.println(txtEmailTrans.getText());
		System.out.println(txtTelefoneTrans.getText());
		System.out.println(txtCnpjTransp.getText());
		System.out.println(txtResponsavelTransp.getText());
		
		limparTrans();	
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
