package br.com.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		Janelas janela = new Janelas();
		janela.abrir("GraficoVendas.fxml", primaryStage);
	}

	public static void main(String[] args) {
		launch(args);

	}


}

