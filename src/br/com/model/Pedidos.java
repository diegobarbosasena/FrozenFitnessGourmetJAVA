package br.com.model;

import java.sql.Connection;
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
	
	private Cliente cliente;
	private Status status;
	
	
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
	
	
	public static List<Pedidos> selecionarTodos(){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelect = "SELECT * FROM tblPedido ORDER BY codPedido DESC; ";
		
		List<Pedidos> lstPedidos = new ArrayList<>();
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelect);
			
			while (rs.next()){
				
				Pedidos p = new Pedidos();
				
				p.setCodPedido(rs.getInt("codPedido"));
				p.setDtCompra(rs.getDate("dtCompra"));
				p.setDtEntrega(rs.getDate("dtEntrega"));
				p.setTipoPedido(rs.getString("tipoPedido"));
				
				lstPedidos.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPedidos;
	}
	
	
}
