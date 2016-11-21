package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.DAO.CidadeDAO;
import br.com.DAO.EstadoDAO;
import br.com.model.Cidade;
import br.com.model.Estado;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EnderecoController implements Initializable {
	
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
	@FXML private ComboBox <Cidade> cboCidade;
	@FXML private Button btnCadastrar;
	@FXML private Button btnCancelar;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		popularComboBox();
		
		btnCancelar.setOnAction(s -> cancelar());
		btnCancelar.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.ENTER) {
		    	cancelar();
		    }
		});
	}
	
	public void popularComboBox() {
		cboCidade.getItems().clear();
		cboCidade.getItems().addAll(CidadeDAO.selecionarTodasCidades());
		
		cboEstado.getItems().clear();
		cboEstado.getItems().addAll(EstadoDAO.selecionarTodosEstados());
		
		cboEstado.valueProperty().addListener(new ChangeListener<Estado>() {
			@Override
			public void changed(ObservableValue<? extends Estado> observable, Estado oldValue, Estado newValue) {			
				cboCidade.getItems().clear();
				
				if(cboEstado.getSelectionModel().getSelectedItem() !=null){
					List<Cidade> uf = CidadeDAO.filtrarCidade(cboEstado.getSelectionModel().getSelectedItem().getUf());
				
					cboCidade.getItems().addAll(uf);
				}
			}
		});
	}
	
	public void cancelar(){
		
		Stage endereco = (Stage) btnCancelar.getScene().getWindow();
		endereco.close();
		
	}
	
}
