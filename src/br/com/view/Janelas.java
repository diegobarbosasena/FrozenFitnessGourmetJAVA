package br.com.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Janelas extends Stage{

	public void abrir(String arquivo, Stage primaryStage, String titulo, boolean redimensionar) {

		Parent root;	
		try {
			root = FXMLLoader.load(getClass().getResource(arquivo));
			primaryStage.setScene( new Scene(root));
			primaryStage.setTitle(titulo);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.resizableProperty().set(redimensionar);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abrirSplash(String arquivo, Stage primaryStage){

		Parent p;
		try {
			p = FXMLLoader.load(getClass().getResource(arquivo));

			primaryStage.setScene(new Scene(p));
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
