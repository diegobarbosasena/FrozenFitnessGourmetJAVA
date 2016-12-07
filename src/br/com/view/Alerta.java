package br.com.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Alerta {

	public void alertaErro(String titulo, String header, String mensagem) {

		Alert alert = new Alert(AlertType.ERROR);

		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(mensagem);

		DialogPane dialogPane = alert.getDialogPane();

		dialogPane.getStylesheets().add(getClass().getResource("/br/com/view/application.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");

		Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
		s.getIcons().add(new Image(this.getClass().getResource("/br/com/imagens/icone.png").toString()));

		alert.showAndWait();	
	}

	public void alertaWarning(String titulo, String header, String mensagem) {

		Alert alert = new Alert(AlertType.WARNING);

		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(mensagem);

		DialogPane dialogPane = alert.getDialogPane();

		dialogPane.getStylesheets().add(getClass().getResource("/br/com/view/application.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");

		Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
		s.getIcons().add(new Image(this.getClass().getResource("/br/com/imagens/icone.png").toString()));

		alert.showAndWait();
	}

	public void alertaInformation(String titulo, String header, String mensagem) {

		Alert alert = new Alert(AlertType.INFORMATION);

		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(mensagem);

		DialogPane dialogPane = alert.getDialogPane();

		dialogPane.getStylesheets().add(getClass().getResource("/br/com/view/application.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");

		Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
		s.getIcons().add(new Image(this.getClass().getResource("/br/com/imagens/icone.png").toString()));

		dialogPane.expandableContentProperty(); 

		alert.showAndWait();
	}

	public void alertSobreFrozen(){

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Frozen Fitness Gourmet");
		alert.setHeaderText(" ");
		alert.setContentText(" ");
		alert.initStyle(StageStyle.UTILITY);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setResizable(false);

		Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
		s.getIcons().add(new Image(this.getClass().getResource("/br/com/imagens/icone.png").toString()));

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setGraphic(new ImageView(this.getClass().getResource("/br/com/imagens/logooo.png").toString()));
		dialogPane.getStylesheets().add(getClass().getResource("/br/com/view/application.css").toExternalForm());

		String msg = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris vitae enim vitae arcu dignissim semper eu non nisi. Nunc eu suscipit nibh. Sed id vestibulum orci. Suspendisse neque sem, blandit ac luctus dignissim, sagittis non libero. Etiam laoreet mattis commodo. Nam dictum tortor a dolor varius placerat. Sed quis iaculis risus. Aliquam at venenatis ex.";

		Label label = new Label("Sobre:");

		TextArea textArea = new TextArea(msg);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxSize(30, 10);
		textArea.setMinSize(30, 10);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();		
	}

	public void alertSobreSmart(){

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Frozen Fitness Gourmet");
		alert.setHeaderText(" ");
		alert.setContentText(" ");
		alert.initStyle(StageStyle.UTILITY);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setResizable(false);

		Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
		s.getIcons().add(new Image(this.getClass().getResource("/br/com/imagens/icone.png").toString()));

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setGraphic(new ImageView(this.getClass().getResource("/br/com/imagens/logooemp.png").toString()));
		dialogPane.getStylesheets().add(getClass().getResource("/br/com/view/application.css").toExternalForm());

		String msg = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris vitae enim vitae arcu dignissim semper eu non nisi. Nunc eu suscipit nibh. Sed id vestibulum orci. Suspendisse neque sem, blandit ac luctus dignissim, sagittis non libero. Etiam laoreet mattis commodo. Nam dictum tortor a dolor varius placerat. Sed quis iaculis risus. Aliquam at venenatis ex.";

		Label label = new Label("Sobre:");

		TextArea textArea = new TextArea(msg);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxSize(30, 10);
		textArea.setMinSize(30, 10);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();		
	}

}
