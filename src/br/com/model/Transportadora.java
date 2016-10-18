package br.com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;

public class Transportadora {

	private int codTransportadora;
	private String nomeTransportadora;
	private String emailTransportadora;
	private String telefoneTransportadora;
	private String cnpjTransportadora;
	private String responsavelTransportadora;
	
	public List<Endereco> lstEnd;
	
	
	public int getCodTransportadora() {
		return codTransportadora;
	}
	public void setCodTransportadora(int codTransportadora) {
		this.codTransportadora = codTransportadora;
	}
	public String getNomeTransportadora() {
		return nomeTransportadora;
	}
	public void setNomeTransportadora(String nomeTransportadora) {
		this.nomeTransportadora = nomeTransportadora;
	}
	public String getEmailTransportadora() {
		return emailTransportadora;
	}
	public void setEmailTransportadora(String emailTransportadora) {
		this.emailTransportadora = emailTransportadora;
	}
	public String getTelefoneTransportadora() {
		return telefoneTransportadora;
	}
	public void setTelefoneTransportadora(String telefoneTransportadora) {
		this.telefoneTransportadora = telefoneTransportadora;
	}
	public String getCnpjTransportadora() {
		return cnpjTransportadora;
	}
	public void setCnpjTransportadora(String cnpjTransportadora) {
		this.cnpjTransportadora = cnpjTransportadora;
	}
	public String getResponsavelTransportadora() {
		return responsavelTransportadora;
	}
	public void setResponsavelTransportadora(String responsavelTransportadora) {
		this.responsavelTransportadora = responsavelTransportadora;
	}
	

	public static List<Transportadora> selecionarTodas() {
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelect = "SELECT * FROM tblTransportadora ORDER BY codTransportadora DESC; ";
		
		List <Transportadora> lstTransp = new ArrayList<>(); 
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelect);

			while(rs.next()){
				
				Transportadora t = new Transportadora();
				
				t.setCodTransportadora(rs.getInt("codTransportadora"));
				t.setNomeTransportadora(rs.getString("nomeTransportadora"));
				t.setEmailTransportadora(rs.getString("emailTransportadora"));
				t.setTelefoneTransportadora(rs.getString("telefoneTransportadora"));
				t.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				t.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));
				
				lstTransp.add(t);			
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstTransp;
	}
	
	public static boolean insert(Transportadora novo) {
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlInsert = "INSERT INTO tblTransportadora (nomeTransportadora, emailTransportadora, telefoneTransportadora, cnpjTransportadora, responsavelTransportadora) VALUES ( ?, ?, ?, ?, ?); ";
		
		PreparedStatement parametros;
		
		try {
			parametros = c.prepareStatement(sqlInsert);

			parametros.setString(1, novo.getNomeTransportadora());
			parametros.setString(2, novo.getEmailTransportadora());
			parametros.setString(3, novo.getTelefoneTransportadora());
			parametros.setString(4, novo.getCnpjTransportadora());
			parametros.setString(5, novo.getResponsavelTransportadora());
			
			parametros.executeUpdate();
			
			c.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
	
		}
		
	}
	
	public static boolean delete(int codTransp ){
		
		Connection c = MySqlConexao.ConectarDb();
		String sqlDeletar = "DELETE FROM tblTransportadora WHERE CodTransportadora = ?;";
			
		PreparedStatement parametros;
		try {
			parametros = c.prepareStatement(sqlDeletar);
			parametros.setInt(1, codTransp);
			parametros.executeUpdate();
				
			c.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}
			
	}
	
	
}
