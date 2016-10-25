package br.com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;

public class Endereco {
	
	private int codEndereco;
	private String logradouro;
	private String cep;
	private String numero;
	private String bairro;
	private String complemento;
	
	public List<Cidade> lstCidade;
	
	
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
	@Override
	public String toString() {
		return "Endereco [codEndereco=" + codEndereco + ", logradouro=" + logradouro + ", cep=" + cep + ", numero="
				+ numero + ", bairro=" + bairro + ", complemento=" + complemento + "]";
	}
	
	
	public static  List<Endereco> selecionarTodos(){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelect = "select * from tblEndereco ;" ;
		
		System.out.println(sqlSelect);
		
		List <Endereco> lstEndereco = new ArrayList<>(); 
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelect);

			while(rs.next()){
				
				Endereco e = new Endereco();
				
				e.setCodEndereco(rs.getInt("codEndereco"));
				e.setBairro(rs.getString("logradouro"));
				e.setCep(rs.getString("cep"));
				e.setComplemento(rs.getString("complemento"));
				e.setLogradouro(rs.getString("logradouro"));
				e.setNumero(rs.getString("numero"));
			
				lstEndereco.add(e);			
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstEndereco;
	}

}
