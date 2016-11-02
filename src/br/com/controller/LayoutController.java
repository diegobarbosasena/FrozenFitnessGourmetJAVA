package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import javafx.scene.control.TabPane;

import javafx.scene.control.Tab;

import javafx.scene.control.Menu;

public class LayoutController implements Initializable {
	
	@FXML private Menu mnVisualizar;
	@FXML private MenuItem miPedidoTel;
	@FXML private MenuItem miPedidos;
	@FXML private MenuItem miAcompa;
	@FXML private MenuItem miVendas;
	@FXML private MenuItem miRelatorios;
	@FXML private MenuItem miFatura;
	@FXML private MenuItem miGrafiVendas;
	@FXML private MenuItem miTransp;
	@FXML private TabPane tpDesk;
	@FXML private Tab tabTransp;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tpDesk.getSelectionModel().select(0);
		
		miPedidoTel.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(0);
			}
		});
		miPedidos.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(1);
			}
		});
		miAcompa.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(2);
			}
		});
		miVendas.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(3);
			}
		});
		miRelatorios.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(4);
			}
		});
		miFatura.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(5);
			}
		});
		miGrafiVendas.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(6);
			}
		});
		miTransp.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tpDesk.getSelectionModel().select(7);
			}
		});
		
	}

}
