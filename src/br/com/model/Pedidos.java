package br.com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.ajudantes.MySqlConexao;

public class Pedidos {

	private int codPedido;
	private String tipoPedido;
	private Date dtEntrega;
	private Date dtCompra;
	private int codCliente;
	private int codStatus;
	private int codVeiculoTransp;
	
	private Cliente cliente;
	private Status status;
	private VeiculoTransp veiculoTransp;
	private Transportadora transportadora;
	private TipoVeiculo tipoVeiculo;

	
	
	public int getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}
	public String getTipoPedido() {
		return tipoPedido;
	}
	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}
	public Date getDtEntrega() {
		return dtEntrega;
	}
	public void setDtEntrega(Date dtEntrega) {
		this.dtEntrega = dtEntrega;
	}
	public Date getDtCompra() {
		return dtCompra;
	}
	public void setDtCompra(Date dtCompra) {
		this.dtCompra = dtCompra;
	}
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public int getCodStatus() {
		return codStatus;
	}
	public void setCodStatus(int codStatus) {
		this.codStatus = codStatus;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Transportadora getTransportadora() {
		return transportadora;
	}
	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}
	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	public int getCodVeiculoTransp() {
		return codVeiculoTransp;
	}
	public void setCodVeiculoTransp(int codVeiculoTransp) {
		this.codVeiculoTransp = codVeiculoTransp;
	}
	public VeiculoTransp getVeiculoTransp() {
		return veiculoTransp;
	}
	public void setVeiculoTransp(VeiculoTransp veiculoTransp) {
		this.veiculoTransp = veiculoTransp;
	}

	
	public static List<Pedidos> selecionarTodosPedidos(){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelect = "CALL pcd_lista_pedi_clie_sta_tipoveic_trans_veictrans() ;";
		
		List<Pedidos> lstPedidos = new ArrayList<>();
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelect);
			
			while (rs.next()){
				
				Pedidos p = new Pedidos();
				Cliente cl = new Cliente();
				Status s = new Status();
				TipoVeiculo tv = new TipoVeiculo();
				Transportadora t = new Transportadora();
				VeiculoTransp vt = new VeiculoTransp();
				
				vt.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));
				vt.setPlacaVeiculo(rs.getString("placaVeiculo"));
				vt.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				vt.setCodTransportadora(rs.getInt("codTransportadora"));
				
				vt.setTransportadora(t);
				
				t.setCodTransportadora(rs.getInt("codTransportadora"));
				t.setNomeTransportadora(rs.getString("nomeTransportadora"));
				t.setEmailTransportadora(rs.getString("emailTransportadora"));
				t.setTelefoneTransportadora(rs.getString("telefoneTransportadora"));
				t.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				t.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));
				t.setCodEndereco(rs.getInt("codEndereco"));
				
				cl.setCodCliente(rs.getInt("codCliente"));
				cl.setNomeCliente(rs.getString("nomeCliente"));
				cl.setCpfCliente(rs.getString("cpfCliente"));
				cl.setDtNascCliente(rs.getDate("dtNascCliente"));
				cl.setPeso(rs.getFloat("peso"));
				cl.setAltura(rs.getFloat("altura"));
				cl.setTelefoneCliente(rs.getString("telefoneCliente"));
				cl.setCelularCliente(rs.getString("celularCliente"));
				cl.setEmailCliente(rs.getString("emailCliente"));
				
				s.setCodStatus(rs.getInt("codStatus"));
				s.setStatusPedido(rs.getString("statusPedido"));
				
				tv.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				tv.setNomeTipoVeiculo(rs.getString("nomeTipoVeiculo"));
				
				p.setCodPedido(rs.getInt("codPedido"));
				p.setTipoPedido(rs.getString("tipoPedido"));
				p.setDtEntrega(rs.getDate("dtEntrega"));
				p.setDtCompra(rs.getDate("dtCompra"));
				p.setCodCliente(rs.getInt("codCliente"));
				p.setCodStatus(rs.getInt("codStatus"));
				p.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));
				
				p.setTipoVeiculo(tv);
				p.setTransportadora(t);
				p.setCliente(cl);
				p.setStatus(s);
				p.setVeiculoTransp(vt);
				
				
				lstPedidos.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPedidos;
	}
	
	public static List<Pedidos> filtrarPedidos(int codPedido) {
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelectPesqPedido = "SELECT * FROM tblPedido WHERE codPedido = ?";
		
		List <Pedidos> lstPediPesq = new ArrayList<>(); 
		PreparedStatement parametros;
		
		try {
			parametros = c.prepareStatement(sqlSelectPesqPedido);
			
			parametros.setInt(1, codPedido);	
			ResultSet rs = parametros.executeQuery();

			while(rs.next()){
				
				Pedidos p = new Pedidos();
				Cliente cl = new Cliente();
				Status s = new Status();
				VeiculoTransp v = new VeiculoTransp();
		
				cl.setCodCliente(rs.getInt("codCliente"));
				cl.setNomeCliente(rs.getString("nomeCliente"));
				cl.setCpfCliente(rs.getString("cpfCliente"));
				cl.setDtNascCliente(rs.getDate("dtNascCliente"));
				cl.setPeso(rs.getFloat("peso"));
				cl.setAltura(rs.getFloat("altura"));
				cl.setTelefoneCliente(rs.getString("telefoneCliente"));
				cl.setCelularCliente(rs.getString("celularCliente"));
				cl.setEmailCliente(rs.getString("emailCliente"));
				
				s.setCodStatus(rs.getInt("codStatus"));
				s.setStatusPedido(rs.getString("statusPedido"));
				
				v.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));
				v.setPlacaVeiculo(rs.getString("placaVeiculo"));
				v.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				v.setCodTransportadora(rs.getInt("codTransportadora"));
				
				p.setCodPedido(rs.getInt("codPedido"));
				p.setTipoPedido(rs.getString("tipoPedido"));
				p.setDtEntrega(rs.getDate("dtEntrega"));
				p.setDtCompra(rs.getDate("dtCompra"));
				p.setCodCliente(rs.getInt("codCliente"));
				p.setCodStatus(rs.getInt("codStatus"));
				p.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));
				
				p.setCliente(cl);
				p.setStatus(s);
				p.setVeiculoTransp(v);
					
				lstPediPesq.add(p);			
			}
			c.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		return lstPediPesq;
	}
	
	public static boolean updatePedido(Pedidos upPed){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlAtualizarPedido = "UPDATE "
				+ "tblPedido SET "
				+ "codStatus = ?, "
				+ "codTransportadora = ?"
				+ "codVeiculoTransp = ? WHERE codPedido = ? ;";

		PreparedStatement parametros;
			
		try {
			parametros = c.prepareStatement(sqlAtualizarPedido);
			
			parametros.setInt(1, upPed.status.getCodStatus());
			parametros.setInt(2, upPed.transportadora.getCodTransportadora());
			parametros.setInt(3, upPed.veiculoTransp.getCodVeiculoTransp());
			parametros.setInt(4, upPed.codPedido);
					
			parametros.executeUpdate();
		
			c.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();	
			return false;	
		}
		
	}
	
	
	@Override
	public String toString() {
		return codPedido+"" ;
	}
	
	
	


}
