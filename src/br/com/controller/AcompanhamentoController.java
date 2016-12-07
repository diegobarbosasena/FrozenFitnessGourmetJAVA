package br.com.controller;

import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ajudantes.Mascaras;
import br.com.ajudantes.MySqlConexao;
import br.com.dao.PedidosDAO;
import br.com.dao.StatusDAO;
import br.com.dao.TipoVeiculoDAO;
import br.com.dao.TransportadoraDAO;
import br.com.model.Cliente;
import br.com.model.Pedidos;
import br.com.model.Status;
import br.com.model.TipoVeiculo;
import br.com.model.Transportadora;
import br.com.view.Alerta;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
	@FXML private TableColumn <Pedidos, Number>tcPrecoAcomp;
	@FXML private TableColumn <Pedidos, String>tcDtEntregaAcomp;
	@FXML private Label lblCodPed;
	@FXML private Label lblNomeClien;
	@FXML private Label lblStatus;
	@FXML private Label lblTransp;
	@FXML private Label lblVeiculo;
	@FXML private ComboBox <Status> cboStatus;
	@FXML private ComboBox <Transportadora> cboTransp;
	@FXML private ComboBox <TipoVeiculo> cboVeiculo;
	@FXML private Button btnEditarPediAcom;
	@FXML private Button btnAtualizarPediAcomp;
	@FXML private Button btnCancelarPediAcomp;
	@FXML private Button btnConcPediAcomp;
	@FXML private Button btnAtualizarAcom;
	@FXML private Label lblFiltroStatus;
	@FXML private RadioButton rbtTodos;
	@FXML private RadioButton rbtAgradPag;
	@FXML private RadioButton rbtAguarSepa;
	@FXML private RadioButton rbtPratoProdu;
	@FXML private RadioButton rbtEnviadoTransp;
	@FXML private RadioButton rbtProdTransporte;
	@FXML private RadioButton rbtEntregue;
	@FXML private Button btnGeraRelaAcom;

	int cod_status = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Mascaras.mascaraNumeroInteiro(txtPediAcomp);

		preencherPedidosAcompanhamento();
		popularComboBox();
		radioButton();

		btnGeraRelaAcom.setOnAction(d -> gerarRelatorioAcompanhamento());

		btnAtualizarAcom.setOnAction(a -> preencherPedidosAcompanhamento());
		btnAtualizarAcom.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				preencherPedidosAcompanhamento();
				popularComboBox();
			}
		});

		txtPediAcomp.textProperty().addListener(f ->{
			if(!txtPediAcomp.getText().isEmpty())
				buscarPedido();
			else
				preencherPedidosAcompanhamento();
		});

		tabEditAcomp.setDisable(true);
		cboVeiculo.setDisable(true);

		btnEditarPediAcom.setOnAction(c -> editarAcomp());
		btnCancelarPediAcomp.setOnAction(v -> cancelarAcompa());	
	} 

	private void radioButton() {

		final ToggleGroup tgFiltarPedidos = new ToggleGroup();

		rbtTodos.setToggleGroup(tgFiltarPedidos);
		rbtAgradPag.setToggleGroup(tgFiltarPedidos);
		rbtAguarSepa.setToggleGroup(tgFiltarPedidos);
		rbtPratoProdu.setToggleGroup(tgFiltarPedidos);
		rbtEnviadoTransp.setToggleGroup(tgFiltarPedidos);
		rbtProdTransporte.setToggleGroup(tgFiltarPedidos);
		rbtEntregue.setToggleGroup(tgFiltarPedidos);

		rbtTodos.setOnAction(c -> preencherPedidosAcompanhamento());
		rbtAgradPag.setOnAction(c -> preencherPedidosAcompanhamento());
		rbtAguarSepa.setOnAction(c -> preencherPedidosAcompanhamento());
		rbtPratoProdu.setOnAction(c -> preencherPedidosAcompanhamento());
		rbtEnviadoTransp.setOnAction(c -> preencherPedidosAcompanhamento());
		rbtProdTransporte.setOnAction(c -> preencherPedidosAcompanhamento());
		rbtEntregue.setOnAction(c -> preencherPedidosAcompanhamento());
	}

	private void buscarPedido() {

		List<Pedidos> lstPediFilt = PedidosDAO.filtrarPedidos(Integer.parseInt(txtPediAcomp.getText()));

		if (lstPediFilt.isEmpty()){

			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Pedidos", "ERRO", "Nenhum registro encontrado!");

			lstPediFilt.clear();
			txtPediAcomp.clear();
			preencherPedidosAcompanhamento();
		}
		else{
			tvPedidosAcompa.getItems().clear();
			tvPedidosAcompa.getItems().addAll(lstPediFilt);
		}

	}

	private void preencherPedidosAcompanhamento(){

		tcCodPedAcomp.setCellValueFactory(new PropertyValueFactory<Pedidos, Integer>("codPedido"));
		tcClientePedAcomp.setCellValueFactory(new PropertyValueFactory<Pedidos, Cliente>("cliente"));
		tcStatusAcomp.setCellValueFactory(new PropertyValueFactory<Pedidos, Status>("status"));
		tcTranspAcomp.setCellValueFactory(new PropertyValueFactory<Pedidos, Transportadora>("transportadora"));
		tcPrecoAcomp.setCellValueFactory(new PropertyValueFactory<Pedidos, Number>("total"));
		tcVeiculoAcomp.setCellValueFactory(new PropertyValueFactory<Pedidos, TipoVeiculo>("tipoVeiculo"));

		tcDtEntregaAcomp.setCellValueFactory( 		
				dtEntrega -> {
					SimpleStringProperty propriedade = new SimpleStringProperty();
					DateFormat dF = new SimpleDateFormat("dd-MM-yyyy");
					propriedade.setValue(dF.format(dtEntrega.getValue().getDtEntrega()));

					return propriedade;
				}
				);

		List<Pedidos> lstPedidos1 = PedidosDAO.selecionarTodosPedidos();	
		tvPedidosAcompa.getItems().clear();
		tvPedidosAcompa.getItems().addAll(lstPedidos1);	

		if (rbtTodos.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.selecionarTodosPedidos();	
			tvPedidosAcompa.getItems().clear();
			tvPedidosAcompa.getItems().addAll(lstPedidos);		
		}

		if (rbtAgradPag.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.filtrarPedidosStatus(1);	
			tvPedidosAcompa.getItems().clear();
			tvPedidosAcompa.getItems().addAll(lstPedidos);	

			cod_status = 1;
		}

		if (rbtAguarSepa.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.filtrarPedidosStatus(2);
			tvPedidosAcompa.getItems().clear();
			tvPedidosAcompa.getItems().addAll(lstPedidos);		

			cod_status = 2;
		}

		if (rbtPratoProdu.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.filtrarPedidosStatus(3);
			tvPedidosAcompa.getItems().clear();
			tvPedidosAcompa.getItems().addAll(lstPedidos);	

			cod_status = 3;
		}

		if (rbtEnviadoTransp.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.filtrarPedidosStatus(4);
			tvPedidosAcompa.getItems().clear();
			tvPedidosAcompa.getItems().addAll(lstPedidos);

			cod_status = 4;
		}

		if (rbtProdTransporte.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.filtrarPedidosStatus(5);
			tvPedidosAcompa.getItems().clear();
			tvPedidosAcompa.getItems().addAll(lstPedidos);

			cod_status = 5;
		}

		if (rbtEntregue.isSelected()){

			List<Pedidos> lstPedidos = PedidosDAO.filtrarPedidosStatus(6);
			tvPedidosAcompa.getItems().clear();
			tvPedidosAcompa.getItems().addAll(lstPedidos);	

			cod_status = 6;
		}

	}

	private void cancelarAcompa() {
		tpAcomp.getSelectionModel().select(0);
		tabEditAcomp.setDisable(true);
		tabVisuaAcomp.setDisable(false);

		limparAcompanhamento();
	}

	private void editarAcomp() {

		Pedidos pdAcom = tvPedidosAcompa.getSelectionModel().getSelectedItem();

		if(pdAcom == null){		
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Pedidos", "ERRO", "Nenhum item selecionado.");
		}
		else{
			tpAcomp.getSelectionModel().select(1);
			tabEditAcomp.setDisable(false);
			tabVisuaAcomp.setDisable(true);

			btnConcPediAcomp.setDisable(true);
			btnAtualizarPediAcomp.setDisable(false);
			btnCancelarPediAcomp.setDisable(false);

			lblStatus.setDisable(false);
			cboStatus.setDisable(false);

			popularComboBox();

			txtCodPediAcomp.setText(String.valueOf(pdAcom.getCodPedido()));
			txtNomeClienAcomp.setText(pdAcom.getCliente().getNomeCliente());

			for(int i=0 ; i < cboStatus.getItems().size();i++){

				Status s = cboStatus.getItems().get(i);

				if(s.getCodStatus() == pdAcom.getStatus().getCodStatus()){
					cboStatus.getSelectionModel().select(i);
					break;
				}
			}
		}
		btnAtualizarPediAcomp.setOnAction(c -> atualizarPedido());
	}

	private void atualizarPedido() {

		Integer codStat = null;
		Integer	codVeic = null;
		Integer codPed = null;

		btnConcPediAcomp.setDisable(true);

		Pedidos codPedi = tvPedidosAcompa.getSelectionModel().getSelectedItem();
		Pedidos upPedi = new Pedidos();

		if(cboStatus.getSelectionModel().getSelectedItem() != null)
			codStat = cboStatus.getSelectionModel().getSelectedItem().getCodStatus();	

		if(cboVeiculo.getSelectionModel().getSelectedItem() != null)
			codVeic = cboVeiculo.getSelectionModel().getSelectedItem().getCodTipoVeiculo();	

		codPed = codPedi.getCodPedido();

		upPedi.setCodPedido(codPed);
		upPedi.setCodStatus(codStat);

		if(codVeic != null){
			upPedi.setCodVeiculoTransp(codVeic);
		}

		if(PedidosDAO.updatePedido(codVeic, codPed, codStat)){

			Alerta aletaInfo = new Alerta();
			aletaInfo.alertaInformation("Pedidos", "SUCESSO", "Pedido Atualizado com sucesso!");

			preencherPedidosAcompanhamento();

			btnCancelarPediAcomp.setDisable(true);
			btnAtualizarPediAcomp.setDisable(true);
			btnConcPediAcomp.setDisable(false);

			lblStatus.setDisable(true);
			cboStatus.setDisable(true);

			lblTransp.setDisable(true);
			cboTransp.setDisable(true);

			lblVeiculo.setDisable(true);
			cboVeiculo.setDisable(true);

			btnConcPediAcomp.setOnAction(v -> acompConcluido());
			btnConcPediAcomp.setOnKeyPressed(f -> {
				if (f.getCode() == KeyCode.ENTER){
					acompConcluido();
				}
			});
		}
	}

	private void acompConcluido() {

		tpAcomp.getSelectionModel().select(0);
		tabEditAcomp.setDisable(true);
		tabVisuaAcomp.setDisable(false);
	}

	private void popularComboBox() {

		if(cboStatus != null){
			cboStatus.getItems().clear();
			cboStatus.getItems().addAll(StatusDAO.selecionarTodosStatus());
		}

		if(cboTransp != null){
			cboTransp.getItems().clear();
			cboTransp.getItems().addAll(TransportadoraDAO.selecionarTodas());
		}

		cboStatus.valueProperty().addListener(new ChangeListener<Status>() {
			@Override
			public void changed(ObservableValue<? extends Status> arg0, Status arg1, Status arg2) {

				if(cboStatus.getSelectionModel().getSelectedItem() != null){

					if(cboStatus.getSelectionModel().getSelectedItem().getStatusPedido().equals("Enviado para a Transportadora")){
						cboTransp.setDisable(false);
						lblTransp.setDisable(false);
					}
					else{
						lblTransp.setDisable(true);
						cboTransp.setDisable(true);
						cboTransp.getSelectionModel().clearSelection();

						lblVeiculo.setDisable(true);
						cboVeiculo.setDisable(true);
						cboVeiculo.getSelectionModel().clearSelection();
					}	
				}	
			}
		});

		cboTransp.valueProperty().addListener(new ChangeListener<Transportadora>() {
			@Override
			public void changed(ObservableValue<? extends Transportadora> observable, Transportadora oldValue,
					Transportadora newValue) {

				if(cboTransp.getSelectionModel().getSelectedItem() != null){

					lblVeiculo.setDisable(false);
					cboVeiculo.setDisable(false);

					List<TipoVeiculo> nomeVeiculo = TipoVeiculoDAO
							.filtrarTransp(cboTransp.getSelectionModel().getSelectedItem().getCodTransportadora());	

					cboVeiculo.getItems().clear();
					cboVeiculo.getItems().addAll(nomeVeiculo);
				}
				else{
					lblVeiculo.setDisable(true);
					cboVeiculo.setDisable(true);
					cboVeiculo.getSelectionModel().clearSelection();
				}
			}
		});	
	}

	public void gerarRelatorioAcompanhamento(){

		try {
			Connection c = MySqlConexao.ConectarDb();

			HashMap<String, Object> parametros = new HashMap<String, Object>();

			parametros.put("titulo", "Relatório de Pedidos");
			parametros.put("total_pedido", "R$ " + PedidosDAO.somarTodosPedidos(cod_status));
			parametros.put("cod_pedido", cod_status);
			parametros.put("imagem_logo", "src/br/com/imagens/logo.PNG");

			JasperPrint jp = JasperFillManager.fillReport("src/br/com/relatorios/relatorio.jasper", parametros, c);			
			JasperViewer jw = new JasperViewer(jp , false);

			if (jw != null)
				jw.setVisible(true);

		} catch (Exception e) {
			Alerta alertaErro = new Alerta(); 
			alertaErro.alertaErro("Relatório", "ERRO", "Erro ao gerar relatório!");
		}
	}

	private void limparAcompanhamento() {

		cboVeiculo.getSelectionModel().clearSelection();
		cboTransp.getSelectionModel().clearSelection();
		cboStatus.getSelectionModel().clearSelection();

	}

}