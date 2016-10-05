package br.com.view;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;

public class Alerta {

	public void alerta(String titulo, String mensagem) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		
		DialogPane dialogPane = alert.getDialogPane();
		
		dialogPane.getStylesheets().add(
		   getClass().getResource("/br/com/view/application.css").toExternalForm());
		
		dialogPane.getStyleClass().add("myDialog");

		alert.showAndWait();
	}
}
