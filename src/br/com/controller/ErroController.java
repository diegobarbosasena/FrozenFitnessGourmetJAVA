package br.com.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErroController {
	@FXML
	private Label lblMsgErro;
	@FXML
	private Button btnErroFechar;

	// Event Listener on Button[#btnErroFechar].onAction
	@FXML
	public void erroFechar(ActionEvent event) {

		Stage stage = (Stage) lblMsgErro.getScene().getWindow();
		stage.close();
	}
}
