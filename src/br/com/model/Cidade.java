package br.com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;

public class Cidade {

	private int codCidade;
	private int codEstado;
	private String NomeCidade;
	
	private Estado estado;
	
	public int getCodCidade() {
		return codCidade;
	}
	public void setCodCidade(int codCidade) {
		this.codCidade = codCidade;
	}
	public int getCodEstado() {
		return codEstado;
	}
	public void setCodEstado(int codEstado) {
		this.codEstado = codEstado;
	}
	public String getNomeCidade() {
		return NomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		NomeCidade = nomeCidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public static List<Cidade> selecionarTodasCidades(){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelectCidade = "SELECT * FROM tblCidade ;" ;
				
		List <Cidade> lstCidade = new ArrayList<>(); 
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelectCidade);

			while(rs.next()){
				
				Cidade ci = new Cidade();
				
				ci.setCodCidade(rs.getInt("codCidade"));
				ci.setNomeCidade(rs.getString("nomeCidade"));
			
				lstCidade.add(ci);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lstCidade;
	}

	public static List<Cidade> filtrarCidade(String nomeFiltroCidade){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelectPesqCidade = "SELECT "
				+ "c.codCidade, c.nomeCidade, c.codEstado, "
				+ "e.codEstado, e.nomeEstado, e.uf "
				+ "FROM tblCidade as c INNER JOIN tblEstado as e "
				+ "ON (c.codEstado = e.codEstado) "
				+ "WHERE uf = ?;" ;
		
		List<Cidade> lstCidadePesq = new ArrayList<>(); 
		PreparedStatement parametros;
		
		try {
			parametros = c.prepareStatement(sqlSelectPesqCidade);
			
			parametros.setString(1, nomeFiltroCidade);	
			ResultSet rs = parametros.executeQuery();

			while(rs.next()){
				
				Estado es = new Estado();
				Cidade ci = new Cidade();
				
				es.setCodEstado(rs.getInt("codEstado"));
				es.setNomeEstado(rs.getString("nomeEstado"));
				es.setUf(rs.getString("uf"));
				
				ci.setCodCidade(rs.getInt("codCidade"));
				ci.setCodEstado(rs.getInt("codEstado"));
				ci.setNomeCidade(rs.getString("nomeCidade"));
				
				ci.setEstado(es);
				
				lstCidadePesq.add(ci);		
			}
			c.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return lstCidadePesq;
	}
	
	@Override
	public String toString() {
		return  NomeCidade;
	}
	
	

}
