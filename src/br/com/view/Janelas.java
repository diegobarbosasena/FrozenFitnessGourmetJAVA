package br.com.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Janelas {

	public void abrir(String arquivo, Stage primaryStage) {

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(arquivo));
			root.getStylesheets().add("br/com/view/application.css");

			primaryStage.setScene( new Scene(root));
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
