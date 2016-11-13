package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;
import br.com.model.TipoVeiculo;
import br.com.model.Transportadora;
import br.com.model.VeiculoTransp;

public class TipoVeiculoDAO {
	
	public static List<TipoVeiculo> selecionarTodos(){
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelect = "SELECT * FROM tblTipoVeiculo ";

		List <TipoVeiculo> lstTv = new ArrayList<>(); 
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelect);

			while(rs.next()){
				
				TipoVeiculo tv = new TipoVeiculo();
				
				tv.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				tv.setNomeTipoVeiculo(rs.getString("nomeTipoVeiculo"));
				
				lstTv.add(tv);
	
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lstTv;
	}
	
	public static List<TipoVeiculo> filtrarTransp(String nomeFiltroTrans){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelectPesq = "SELECT "
				+ "tv.*, t.*, vt.* FROM tblVeiculoTransp AS vt "
				+ "INNER JOIN tblTransportadora AS t "
				+ "ON (vt.codTransportadora = t.codTransportadora) "
				+ "INNER JOIN tblTipoVeiculo AS tv "
				+ "ON (tv.codTipoVeiculo = vt.codTipoVeiculo) "
				+ "WHERE t.nomeTransportadora = ? ;";
		
		List <TipoVeiculo> lstVeicuTranspFiltro = new ArrayList<>(); 
		PreparedStatement parametros;
		
		try {
			parametros = c.prepareStatement(sqlSelectPesq);
			
			parametros.setString(1, nomeFiltroTrans);	
			ResultSet rs = parametros.executeQuery();

			while(rs.next()){
				
				Transportadora t = new Transportadora();
				TipoVeiculo tv = new TipoVeiculo();
				VeiculoTransp vt = new VeiculoTransp();
				
				t.setCodTransportadora(rs.getInt("codTransportadora"));
				
				tv.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				tv.setNomeTipoVeiculo(rs.getString("nomeTipoVeiculo"));
				
				vt.setTransportadora(t);
				
				
				lstVeicuTranspFiltro.add(tv);			
			}
			c.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		return lstVeicuTranspFiltro;
	}
	
}