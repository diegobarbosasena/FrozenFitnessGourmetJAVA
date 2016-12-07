package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.view.Janelas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashController implements Initializable{

	@FXML private Button btnSplash;
	@FXML private ProgressIndicator piInicializa;

	private int tempo = 4000;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Timeline tempoSplash = new Timeline(new KeyFrame(
				Duration.millis(tempo),
				ae -> {
					fecharSplash();		        	
				}));
		tempoSplash.play();

		piInicializa.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
		piInicializa.setStyle(" -fx-progress-color: #00B348;");	
	}

	public void fecharSplash(){	
		Stage fs = (Stage) btnSplash.getScene().getWindow();
		fs.close();			
		inicio();
	}

	public void inicio(){
		Janelas login = new Janelas();
		Stage s = new Stage();
		login.abrir("Login.fxml", s, "Login", false);
	}

}
