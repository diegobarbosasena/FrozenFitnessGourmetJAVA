package br.com.controller;

import br.com.model.Cidade;
import br.com.model.Estado;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.layout.AnchorPane;

public class EnderecoController {
	
	@FXML private AnchorPane anpCadasEnder;
	@FXML private Label lblCadasEnder;
	@FXML private Label lblLogradouro;
	@FXML private TextField txtLogradouro;
	@FXML private Label lblCep;
	@FXML private TextField txtCep;
	@FXML private Label lblNro;
	@FXML private TextField txtNro;
	@FXML private Label lblBairro;
	@FXML private TextField txtBairro;
	@FXML private Label lblComplemento;
	@FXML private TextField txtComplemento;
	@FXML private Label lblEstado;
	@FXML private ComboBox <Estado> cboEstado;
	@FXML private Label lblCidade;
	@FXML private ComboBox <Cidade>cboCidade;
	@FXML private Button btnCadastrar;
	@FXML private Button btnCancelar;

}
