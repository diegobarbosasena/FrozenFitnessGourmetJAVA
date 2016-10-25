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
	private int codEndereco;
	
	private Endereco endereco;
	
	
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
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public int getCodEndereco() {
		return codEndereco;
	}
	public void setCodEndereco(int codEndereco) {
		this.codEndereco = codEndereco;
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
		
		String sqlSelect = "SELECT t.codTransportadora, t.nomeTransportadora, t.cnpjTransportadora, t.emailTransportadora, t.telefoneTransportadora, t.responsavelTransportadora, ";
		sqlSelect = sqlSelect + "e.codEndereco, e.logradouro, e.cep, e.numero, e.bairro, e.complemento FROM tblTransportadora AS t ";
		sqlSelect = sqlSelect + "INNER JOIN tblEndereco AS e ON (t.codEndereco = e.codEndereco) ORDER BY codTransportadora DESC; ";
		
		List <Transportadora> lstTransp = new ArrayList<>(); 
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelect);

			while(rs.next()){
				
				Transportadora t = new Transportadora();
				Endereco e = new Endereco();
				
				e.setCodEndereco(rs.getInt("codEndereco"));
				e.setLogradouro(rs.getString("logradouro"));
				e.setCep(rs.getString("cep"));
				e.setNumero(rs.getString("numero"));
				e.setBairro(rs.getString("bairro"));
				e.setComplemento(rs.getString("complemento"));
					
				t.setCodTransportadora(rs.getInt("codTransportadora"));
				t.setNomeTransportadora(rs.getString("nomeTransportadora"));
				t.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				t.setEmailTransportadora(rs.getString("emailTransportadora"));
				t.setTelefoneTransportadora(rs.getString("telefoneTransportadora"));
				t.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));
				t.setCodEndereco(rs.getInt("codEndereco"));
				
				t.setEndereco(e);
				
				lstTransp.add(t);			
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstTransp;
	}
	
	public static boolean insert(Transportadora novo, Endereco novoEn ) {
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlInsertEndereco = "INSERT INTO tblEndereco "
				+ "(logradouro, cep, numero, bairro, complemento, codCidade) "
				+ "VALUES (?, ?, ? , ?, ?, ?) ";
				
		String sqlInsertTransp = "INSERT INTO tblTransportadora "
				+ "(nomeTransportadora, emailTransportadora, telefoneTransportadora, cnpjTransportadora, responsavelTransportadora, codEndereco) "
				+ "VALUES ( ?, ?, ?, ?, ?, last_insert_id()); ";
		
		PreparedStatement parametros;
		
		try {
			parametros = c.prepareStatement(sqlInsertEndereco);

			parametros.setString(1, novoEn.getLogradouro());
			parametros.setString(2, novoEn.getCep());
			parametros.setString(3, novoEn.getNumero());
			parametros.setString(4, novoEn.getBairro());
			parametros.setString(5, novoEn.getComplemento());
			
			parametros.setInt(6, 1);
			
			parametros = c.prepareStatement(sqlInsertTransp);
			
			parametros.setString(7, novo.getNomeTransportadora());
			parametros.setString(8, novo.getEmailTransportadora());
			parametros.setString(9, novo.getTelefoneTransportadora());
			parametros.setString(10, novo.getCnpjTransportadora());
			parametros.setString(11, novo.getResponsavelTransportadora());
			
			parametros.executeUpdate();
			
			System.out.println(parametros);
			
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
