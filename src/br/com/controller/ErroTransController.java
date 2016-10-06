package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErroTransController implements Initializable{
	
	@FXML
	public Label lblTituloErro;
	@FXML
	public Label lblMsgErroTrans;
	@FXML
	public Button btnErroFecharTrans;
	
	private String titulo, msg;
	
	public ErroTransController(String titulo, String msg){
		this.titulo = titulo;
		this.msg = msg;
		
		System.out.println(titulo + msg);
	}

	// Event Listener on Button[#btnErroFecharTrans].onAction
	
	public void erroFecharTrans() {
		
		Stage erroFecharTrans = (Stage) btnErroFecharTrans.getScene().getWindow();
		erroFecharTrans.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println("controller ");
		
		lblTituloErro.setText(titulo);
		lblMsgErroTrans.setText(msg);
		
		btnErroFecharTrans.setOnAction(x -> erroFecharTrans());
					
	}
		
}
