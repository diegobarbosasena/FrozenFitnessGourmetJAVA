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
	
	public List<Endereco> lstEndereco;
	
	
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
	
	
	public static List<Transportadora> filtrar(String nomePesquisa){
	
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelectPesq = "SELECT * FROM tblTransportadora WHERE nomeTransportadora LIKE ? ORDER BY codTransportadora DESC; ";
		
		List <Transportadora> lstTranspPesq = new ArrayList<>(); 
		PreparedStatement parametros;
		
		try {
			parametros = c.prepareStatement(sqlSelectPesq);
			
			parametros.setString(1, nomePesquisa);	
			ResultSet rs = parametros.executeQuery();

			while(rs.next()){
				
				Transportadora tp = new Transportadora();
				
				tp.setCodTransportadora(rs.getInt("codTransportadora"));
				tp.setNomeTransportadora(rs.getString("nomeTransportadora"));
				tp.setEmailTransportadora(rs.getString("emailTransportadora"));
				tp.setTelefoneTransportadora(rs.getString("telefoneTransportadora"));
				tp.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				tp.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));
				
				lstTranspPesq.add(tp);			
			}
			c.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return lstTranspPesq;
	}
	
	public static List<Transportadora> selecionarTodas() {
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelect = "select t.codTransportadora, t.cnpjTransportadora, t.emailTransportadora, t.telefoneTransportadora, t.responsavelTransportadora, ";
		sqlSelect = sqlSelect + "e.logradouro, e.cep, e.numero, e.bairro, e.complemento from tblTransportadora as t ";
		sqlSelect = sqlSelect + "inner join tblEndereco as e on (t.codEndereco = e.codEndereco) ORDER BY codTransportadora DESC; ";
		
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
	
	public static boolean update(Transportadora up){
		
		Connection c = MySqlConexao.ConectarDb();
			
		String sqlAtualizar = "UPDATE tblTransportadora set nomeTransportadora = ?, emailTransportadora = ?, telefoneTransportadora = ?, cnpjTransportadora = ?, responsavelTransportadora = ? WHERE codTransportadora = ?";

		PreparedStatement parametros;
			
		try {
			parametros = c.prepareStatement(sqlAtualizar);
			
			parametros.setInt(6, up.codTransportadora);
			parametros.setString(1, up.nomeTransportadora);
			parametros.setString(2, up.emailTransportadora);
			parametros.setString(3, up.telefoneTransportadora);
			parametros.setString(4, up.cnpjTransportadora);
			parametros.setString(5, up.responsavelTransportadora);
					
			parametros.executeUpdate();
		
			c.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;	
		}
	}
	
}
