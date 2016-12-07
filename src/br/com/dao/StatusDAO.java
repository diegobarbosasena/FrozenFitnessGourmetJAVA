package br.com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;
import br.com.model.Status;

public class StatusDAO {

	public static List<Status> selecionarTodosStatus() {

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelectStatus = "SELECT * FROM tblStatus";

		List <Status> lstStatus = new ArrayList<>(); 

		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelectStatus);

			while(rs.next()){

				Status s = new Status();
				s.setCodStatus(rs.getInt("codStatus"));
				s.setStatusPedido(rs.getString("statusPedido"));

				lstStatus.add(s);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstStatus;
	}

}
