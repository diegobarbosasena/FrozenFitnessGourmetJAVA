package br.com.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		Janelas login = new Janelas();
		login.abrir("NovaTransportadora.fxml", primaryStage, "Login", false);	
	}

	public static void main(String[] args) {
		launch(args);
	}

}

