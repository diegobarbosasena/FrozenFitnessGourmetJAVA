package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.view.Janelas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class SplashController implements Initializable{
	
	@FXML private Button btnSplash;
	@FXML ProgressBar pbrProgresso;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnSplash.setOnMouseClicked(n -> fecharSplash());
		btnSplash.setOnKeyPressed(e -> { if (e.getCode() == KeyCode.ENTER) fecharSplash(); } );
		
		//fecharSplash();
	}
	
	public void inicio(){
		
		Janelas login = new Janelas();
		Stage s = new Stage();
		login.abrir("login.fxml", s, "Login", false);
	}
	
	public void fecharSplash(){	
		
		Stage fs = (Stage) btnSplash.getScene().getWindow();
		fs.close();
				
		inicio();
	}
}
