package br.com.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.model.Cliente;
import br.com.model.Pedidos;
import br.com.model.Status;
import br.com.model.TipoVeiculo;
import br.com.model.Transportadora;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AcompanhamentoController implements Initializable{
	
	@FXML private AnchorPane anpAcompanhamento;
	
	@FXML private TabPane tpAcomp;
	@FXML private Tab tabVisuaAcomp;
	@FXML private Tab tabEditAcomp;
	
	@FXML private TextField txtPediAcomp;
	@FXML private TextField txtCodPediAcomp;
	@FXML private TextField txtNomeClienAcomp;
	
	@FXML private TableView <Pedidos> tvPedidosAcompa;
	@FXML private TableColumn <Pedidos, Integer> tcCodPedAcomp;
	@FXML private TableColumn <Pedidos, Cliente> tcClientePedAcomp;
	@FXML private TableColumn <Pedidos, Status> tcStatusAcomp;
	@FXML private TableColumn <Pedidos, Transportadora> tcTranspAcomp;
	@FXML private TableColumn <Pedidos, TipoVeiculo> tcVeiculoAcomp;
	
	@FXML private Label lblCodPed;
	@FXML private Label lblNomeClien;
	@FXML private Label lblStatus;
	@FXML private Label lblTransp;
	@FXML private Label lblVeículo;
	
	@FXML private ComboBox <Status> cboStatus;
	@FXML private ComboBox <Transportadora> cboTransp;
	@FXML private ComboBox <TipoVeiculo> cboVeiculo;
	
	@FXML private Button btnBuscarPediAcom;
	@FXML private Button btnEditarPediAcom;
	@FXML private Button btnAtualizarPediAcomp;
	@FXML private Button btnCancelarPediAcomp;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		preencherPedidosAcompanhamento();
		popularComboBox();
		
		tabEditAcomp.setDisable(true);
		
		btnEditarPediAcom.setOnAction(c -> editarAcomp());
		btnCancelarPediAcomp.setOnAction(v -> cancelarAcompa());
		
	} 
	
	public void preencherPedidosAcompanhamento(){
		
		tcCodPedAcomp.setCellValueFactory(new PropertyValueFactory<Pedidos, Integer>("codPedido"));
		tcClientePedAcomp.setCellValueFactory(new PropertyValueFactory<Pedidos, Cliente>("cliente"));
		tcStatusAcomp.setCellValueFactory(new PropertyValueFactory<Pedidos, Status>("status"));
		tcTranspAcomp.setCellValueFactory(new PropertyValueFactory<Pedidos, Transportadora>("transportadora"));
		tcVeiculoAcomp.setCellValueFactory(new PropertyValueFactory<Pedidos, TipoVeiculo>("tipoVeiculo"));
	
		List<Pedidos> lstPedidos = Pedidos.selecionarTodosPedidos();
		
		tvPedidosAcompa.getItems().clear();
		tvPedidosAcompa.getItems().addAll(lstPedidos);
		
	}
	
	public void cancelarAcompa() {
		tpAcomp.getSelectionModel().select(0);
		tabEditAcomp.setDisable(true);
		tabVisuaAcomp.setDisable(false);
		
		limparAcompanhamento();
	}

	public void editarAcomp() {
		tpAcomp.getSelectionModel().select(1);
		tabEditAcomp.setDisable(false);
		tabVisuaAcomp.setDisable(true);
		
		popularComboBox();
		
	}

	public void popularComboBox() {
		
		cboStatus.getItems().clear();
		cboStatus.getItems().addAll(Status.selecionarTodosStatus());
		
		cboVeiculo.getItems().clear();
		cboVeiculo.getItems().addAll(TipoVeiculo.selecionarTodos());
		
		cboTransp.getItems().clear();
		cboTransp.getItems().addAll(Transportadora.selecionarTodas());
		
		if (cboStatus != null){
			cboStatus.valueProperty().addListener(new ChangeListener<Status>() {
				@Override
				public void changed(ObservableValue<? extends Status> arg0, Status arg1, Status arg2) {
						
					if(cboStatus.getSelectionModel().getSelectedItem().getStatusPedido().equals("Enviado para a Transportadora")){
						cboTransp.setDisable(false);
						cboVeiculo.setDisable(false);
					}
					else{
						cboTransp.setDisable(true);
						cboVeiculo.setDisable(true);
					}
				}
			});
		}
		
		
		cboTransp.valueProperty().addListener(new ChangeListener<Transportadora>() {
			@Override
			public void changed(ObservableValue<? extends Transportadora> observable, Transportadora oldValue,
					Transportadora newValue) {
				if(cboVeiculo.getSelectionModel().getSelectedItem() != null){
					
					List<TipoVeiculo> nomeVeiculo = TipoVeiculo.filtrarTransp(cboTransp.getSelectionModel().getSelectedItem().getNomeTransportadora());	
					cboVeiculo.getItems().addAll(nomeVeiculo);
				}
			}
		});
		
	}
	
	public void limparAcompanhamento() {
		
		//cboStatus.getSelectionModel().clearSelection();
		
	}


}
