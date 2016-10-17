package br.com.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Janelas{

	public void abrir(String arquivo, Stage primaryStage, String titulo, boolean redimensionar) {

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(arquivo));
			primaryStage.setScene( new Scene(root));
			primaryStage.setTitle(titulo);
			primaryStage.resizableProperty().set(redimensionar);
			primaryStage.show();
			
			javafx.scene.image.Image icone = new javafx.scene.image.Image(
					getClass().getResourceAsStream("/br/com/view/imagens/icone.png"));
			
			primaryStage.getIcons().add(icone);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void abrirPopup(String arquivo, Stage primaryStage, String titulo, boolean redimensionar, Object controller) {

		Parent root;
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(arquivo));	
			
			loader.setController(controller);
			
			root = loader.load();
					
			primaryStage.setScene( new Scene(root));
			primaryStage.setTitle(titulo);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.resizableProperty().set(redimensionar);
			primaryStage.show();
			
			javafx.scene.image.Image icone = new javafx.scene.image.Image(
					getClass().getResourceAsStream("/br/com/view/imagens/icone.png"));
			
			primaryStage.getIcons().add(icone);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
