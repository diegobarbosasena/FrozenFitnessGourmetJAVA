package br.com.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Janelas extends Stage{

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
	
	public void abrirSplash(String arquivo, Stage primaryStage){
		
		Parent p;
		try {
			p = FXMLLoader.load(getClass().getResource(arquivo));
			
			primaryStage.setScene(new Scene(p));
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void abrirJanela(String arquivo, Stage stage, String titulo, boolean redimensionar, Object controller){
	
		Parent p;
		try {
			
			FXMLLoader load = new FXMLLoader(getClass().getResource(arquivo));
			
			load.setController(controller);
			
			p = load.load();
			
			stage.setScene(new Scene(p));
			stage.setTitle(titulo);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.resizableProperty().set(redimensionar);
			stage.show();
			
			javafx.scene.image.Image icone = new javafx.scene.image
					.Image(getClass().getResourceAsStream("/br/com/view/imagens/icone.png"));
			stage.getIcons().add(icone);
			
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
			
			javafx.scene.image.Image icone = new javafx.scene.image
					.Image(getClass().getResourceAsStream("/br/com/view/imagens/icone.png"));
			
			primaryStage.getIcons().add(icone);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
