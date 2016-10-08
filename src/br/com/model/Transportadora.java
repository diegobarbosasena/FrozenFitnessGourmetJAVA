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
		
		List <Transportadora> tr = new ArrayList<>(); 
		
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
				
				tr.add(t);			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tr;
	}
	
	public static List<Transportadora> inserirTransportadora(String nome, String email, String telefone, String cnpj, String responsavel ){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlInsert = "INSERT INTO tbltransportadora (nomeTransportadora,emailTransportadora,telefoneTransportadora,cnpjTransportadora,responsavelTransportadora) VALUES (?,?,?,?,?); ";
		
		PreparedStatement parametros;
		
		try {
			parametros = c.prepareStatement(sqlInsert);
			
			parametros.setString(1, nome);
			parametros.setString(2, email);
			parametros.setString(3, telefone);
			parametros.setString(4, cnpj);
			parametros.setString(5, responsavel);
			
			parametros.executeUpdate();
			
			c.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}
}
