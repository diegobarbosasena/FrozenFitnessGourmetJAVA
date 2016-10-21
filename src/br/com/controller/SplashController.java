package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.view.Janelas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashController implements Initializable{
	
	@FXML private Button btnSplash;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		Timeline tempoSplash = new Timeline(new KeyFrame(
		        Duration.millis(2500),
		        ae -> {
		        	fecharSplash();		        	
		        }));
		tempoSplash.play();
	}

	public void fecharSplash(){	
		
		Stage fs = (Stage) btnSplash.getScene().getWindow();
		fs.close();
				
		inicio();
	}
	
	public void inicio(){
		
		Janelas login = new Janelas();
		Stage s = new Stage();
		login.abrir("login.fxml", s, "Login", false);
	}
}
