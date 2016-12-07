package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.view.Alerta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LayoutController implements Initializable {

	@FXML private Menu mnVisualizar;
	@FXML private MenuItem miPedidoTel;
	@FXML private MenuItem miPedidos;
	@FXML private MenuItem miAcompa;
	@FXML private MenuItem miGrafiVendas;
	@FXML private MenuItem miTransp;
	@FXML private MenuItem miAtualizar;
	@FXML private MenuItem miFrozen;
	@FXML private MenuItem miSmart;
	@FXML private TabPane tpDesk;
	@FXML private Tab tabTransp;

	@FXML private Button btnMinimizarPrincipal;
	@FXML private Button btnMaximizarPrincipal;
	@FXML private Button btnFecharPrincipal;

	@FXML public static HBox principal;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tpDesk.getSelectionModel().select(0);

		btnMinimizarPrincipal.setOnAction(m -> minimizar());
		btnMaximizarPrincipal.setOnAction(a -> maximizar());
		btnFecharPrincipal.setOnAction(f -> fechar());

		miPedidoTel.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT1, KeyCombination.CONTROL_DOWN ));
		miPedidoTel.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(0);
			}
		});
		miPedidos.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT2, KeyCombination.CONTROL_DOWN ));
		miPedidos.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(1);
			}
		});
		miAcompa.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT3, KeyCombination.CONTROL_DOWN ));
		miAcompa.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(2);
			}
		});

		miGrafiVendas.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT4, KeyCombination.CONTROL_DOWN ));
		miGrafiVendas.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(3);
			}
		});

		miTransp.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT5, KeyCombination.CONTROL_DOWN ));
		miTransp.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(4);
			}
		});

		miAtualizar.setAccelerator(new KeyCodeCombination(KeyCode.F5));
		miAtualizar.setOnAction(new javafx.event.EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {

				Stage stage = (Stage)tpDesk.getScene().getWindow();
				Button btnAtualizarTransp =(Button)stage.getScene().lookup("#btnAtualizarTransp");

				if(btnAtualizarTransp != null){
					btnAtualizarTransp.fire();					
				}

				Stage pedidos = (Stage)tpDesk.getScene().getWindow();
				Button btnAtualizarPedidos =(Button)pedidos.getScene().lookup("#btnAtualizarPedido");

				if(btnAtualizarPedidos != null){
					btnAtualizarPedidos.fire();
				}

				Stage acompanhamento = (Stage)tpDesk.getScene().getWindow();
				Button btnAtualizarAcom =(Button)acompanhamento.getScene().lookup("#btnAtualizarAcom");

				if(btnAtualizarAcom != null){
					btnAtualizarAcom.fire();
				}

				Stage veiculo = (Stage)tpDesk.getScene().getWindow();
				Button btnAtualizarVeiculo =(Button)veiculo.getScene().lookup("#btnAtualizarVeiculo");

				if(btnAtualizarVeiculo != null){
					btnAtualizarVeiculo.fire();
				}

				Stage pedTel = (Stage)tpDesk.getScene().getWindow();
				Button btnAtualizaJuridico =(Button)pedTel.getScene().lookup("#btnAtualizaJuridico");

				if(btnAtualizaJuridico != null){
					btnAtualizaJuridico.fire();
				}

				Stage pedTel1 = (Stage)tpDesk.getScene().getWindow();
				Button btnAtualizaFisico =(Button)pedTel1.getScene().lookup("#btnAtualizaFisico");

				if(btnAtualizaFisico != null){
					btnAtualizaFisico.fire();
				}
			}	
		});

		miFrozen.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCodeCombination.CONTROL_DOWN, KeyCodeCombination.SHIFT_DOWN));
		miFrozen.setOnAction(new javafx.event.EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {

				Alerta a = new Alerta();
				a.alertSobreFrozen();
			}		
		});

		miSmart.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN, KeyCodeCombination.SHIFT_DOWN));
		miSmart.setOnAction(new javafx.event.EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {

				Alerta a = new Alerta();
				a.alertSobreSmart();
			}		
		});	
	}

	private void minimizar() {
		Stage minimizar =  (Stage)btnMinimizarPrincipal.getScene().getWindow();
		minimizar.setIconified(true);
	}

	private void maximizar() {

		Stage maximizar =  (Stage)btnMaximizarPrincipal.getScene().getWindow();

		if (maximizar.isFullScreen())
			maximizar.setFullScreen(false);
		else
			maximizar.setFullScreen(true);
	}

	private void fechar() {
		System.exit(0);
	}

	private static double xOffset = 0;
	private static double yOffset = 0;

	public static HBox moverJanela(final Stage primaryStage) {

		principal.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});

		principal.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() - xOffset);
				primaryStage.setY(event.getScreenY() - yOffset);
			}
		});

		return principal;
	}

}
