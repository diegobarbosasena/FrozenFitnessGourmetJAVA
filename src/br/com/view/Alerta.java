package br.com.view;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;

public class Alerta {

	public void alertaWarning(String titulo, String header, String mensagem) {
		
		Alert alert = new Alert(AlertType.WARNING);
		
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(mensagem);
		
		DialogPane dialogPane = alert.getDialogPane();
		
		dialogPane.getStylesheets().add(getClass().getResource("/br/com/view/application.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");

		alert.showAndWait();
	}
}
