package br.com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.ajudantes.MySqlConexao;

public class Endereco {
	
	private int codEndereco;
	private String logradouro;
	private String cep;
	private String numero;
	private String bairro;
	private String complemento;
	
	private Cidade cidade;
		
	
	public int getCodEndereco() {
		return codEndereco;
	}
	public void setCodEndereco(int codEndereco) {
		this.codEndereco = codEndereco;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return logradouro + " n°" +  numero + " Bairro " + bairro ;
	}
	
	public static boolean insert(Endereco novoEnde) {
		
		Connection c = MySqlConexao.ConectarDb();
			
		String sqlInsertEndereco = "INSERT INTO tblEndereco "
				+ "(logradouro, cep, numero, bairro, complemento, codCidade) "
				+ "VALUES (?, ?, ?, ?, ?, ?) ";
		
		PreparedStatement parametros;
		
		try {
			
			parametros = c.prepareStatement(sqlInsertEndereco);
			
			parametros.setString(1, novoEnde.getLogradouro());
			parametros.setString(2, novoEnde.getCep());
			parametros.setString(3, novoEnde.getNumero());
			parametros.setString(4, novoEnde.getBairro());
			parametros.setString(5, novoEnde.getComplemento());
			parametros.setInt(6, novoEnde.getCidade().getCodCidade());
			
			parametros.executeUpdate();
				
			c.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
}
