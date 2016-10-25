package br.com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;

public class Cidade {

	private int codCidade;
	private int codEstado;
	private String NomeCidade;
	
	
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
	
	
	public static List<Cidade> selecionarTodasCidades(){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelect = "select nomeCidade from tblCidade ;" ;
		
		
		List <Cidade> lstCidade = new ArrayList<>(); 
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelect);

			while(rs.next()){
				
				Cidade ci = new Cidade();
				
				ci.setNomeCidade(rs.getString("nomeCidade"));
			
				lstCidade.add(ci);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lstCidade;
		
		
	}
	
//	getItems().addAll(
//            "jacob.smith@example.com",
//            "isabella.johnson@example.com",
//            "ethan.williams@example.com",
//            "emma.jones@example.com",
//            "michael.brown@example.com");
//}

}
