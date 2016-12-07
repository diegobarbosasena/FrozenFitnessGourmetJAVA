package br.com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;
import br.com.model.Estado;

public class EstadoDAO {

	public static List<Estado> selecionarTodosEstados(){

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelectEstado = "SELECT * FROM tblEstado ORDER BY nomeEstado;" ;

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
		} catch (Exception e) {

		}
		return lstEstado;
	}

}
