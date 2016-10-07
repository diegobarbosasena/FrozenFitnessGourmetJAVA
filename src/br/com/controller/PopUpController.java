package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpController implements Initializable{
	@FXML
	private Label lblTituloErro;
	@FXML
	private Label lblMsgErro;
	@FXML
	private Button btnPopUpFechar;
	
	private String titulo, msg, botao;
	
	public PopUpController(String titulo, String msg, String botao) {

		this.titulo = titulo;
		this.msg = msg;
		this.botao = botao;
		
		System.out.println();
		System.out.println(titulo);
		System.out.println(msg);
		System.out.println(botao);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println();
		System.out.println("Controller initializable");
	
		lblTituloErro.setText(titulo);
		lblMsgErro.setText(msg);
		btnPopUpFechar.setText(botao);
		
		btnPopUpFechar.setOnAction(x -> fecharPopUp());
	}
	
	public void fecharPopUp() {
		Stage s = (Stage) btnPopUpFechar.getScene().getWindow();
		s.close();
	}
}
