package br.com.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErroTransController {
	@FXML
	public Label lblTituloErro;
	@FXML
	public Label lblMsgErroTrans;
	@FXML
	public Button btnErroFecharTrans;

	// Event Listener on Button[#btnErroFecharTrans].onAction
	@FXML
	public void erroFecharTrans(ActionEvent event) {
		
		Stage erroFecharTrans = (Stage) btnErroFecharTrans.getScene().getWindow();
		erroFecharTrans.close();
	}
	
}
