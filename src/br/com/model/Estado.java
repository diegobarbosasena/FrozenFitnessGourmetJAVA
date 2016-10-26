package br.com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;

public class Estado {

	private int codEstado;
	private String nomeEstado;
	private String uf;
	
	public int getCodEstado() {
		return codEstado;
	}
	public void setCodEstado(int codEstado) {
		this.codEstado = codEstado;
	}
	public String getNomeEstado() {
		return nomeEstado;
	}
	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

	
	
	public static List<Estado> selecionarTodosEstados(){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelectEstado = "SELECT * FROM tblEstado;" ;
				
		List <Estado> lstEstado = new ArrayList<>(); 
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelectEstado);

			while(rs.next()){
				
				Estado es = new Estado();
				
				es.setCodEstado(rs.getInt("codEstado"));
				es.setNomeEstado(rs.getString("nomeEstado"));
				es.setUf(rs.getString("uf"));
			
				lstEstado.add(es);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lstEstado;
	}

	@Override
	public String toString() {
		return uf ;
	}

	
}
