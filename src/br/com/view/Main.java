package br.com.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		Janelas splash = new Janelas();
		splash.abrirSplash("Splash.fxml", primaryStage);
		//splash.abrir("Layout.fxml", primaryStage, null, true);
	}

	public static void main(String[] args) {
		launch(args);
		System.exit(0);
	}
}