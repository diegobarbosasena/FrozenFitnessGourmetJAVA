package br.com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;

public class Status {

	private int codStatus;
	private String statusPedido;
	
	
	public int getCodStatus() {
		return codStatus;
	}
	public void setCodStatus(int codStatus) {
		this.codStatus = codStatus;
	}
	public String getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	
	public static List<Status> selecionarTodosStatus() {
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelectStatus = "SELECT * FROM tblStatus";

		List <Status> lstStatus = new ArrayList<>(); 
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelectStatus);

			while(rs.next()){
				
				Status s = new Status();
				s.setCodStatus(rs.getInt("codStatus"));
				s.setStatusPedido(rs.getString("statusPedido"));
				
				lstStatus.add(s);	
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstStatus;
	}
	
	@Override
	public String toString() {
		return statusPedido;
	}
	
	
				
		
}
