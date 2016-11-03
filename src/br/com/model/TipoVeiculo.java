package br.com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;

public class TipoVeiculo {

	private int codTipoVeiculo;
	private String nomeTipoVeiculo;
	
	
	public int getCodTipoVeiculo() {
		return codTipoVeiculo;
	}
	public void setCodTipoVeiculo(int codTipoVeiculo) {
		this.codTipoVeiculo = codTipoVeiculo;
	}
	public String getNomeTipoVeiculo() {
		return nomeTipoVeiculo;
	}
	public void setNomeTipoVeiculo(String nomeTipoVeiculo) {
		this.nomeTipoVeiculo = nomeTipoVeiculo;
	}
	
	public static List<TipoVeiculo> filtrarTransp(String nomeFiltroTrans){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelectPesq = "SELECT "
				+ "tv.* FROM tblVeiculoTransp AS vt "
				+ "INNER JOIN tblTransportadora AS t "
				+ "ON (vt.codTransportadora = t.codTransportadora)"
				+ "INNER JOIN tblTipoVeiculo AS tv "
				+ "ON (tv.codTipoVeiculo = vt.codTipoVeiculo)"
				+ "WHERE t.nomeTransportadora = ?;";
		
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
	
	@Override
	public String toString() {
		return nomeTipoVeiculo ;
	}
	
	
	
}
