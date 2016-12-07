package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstTv;
	}

	public static List<TipoVeiculo> filtrarTransp(int codTrans){

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelectPesq = "SELECT "
				+ "tv.*, t.*, vt.* FROM tblVeiculoTransp AS vt "
				+ "INNER JOIN tblTransportadora AS t "
				+ "ON (vt.codTransportadora = t.codTransportadora) "
				+ "INNER JOIN tblTipoVeiculo AS tv "
				+ "ON (tv.codTipoVeiculo = vt.codTipoVeiculo) "
				+ "WHERE t.codTransportadora = ? " ;

		List <TipoVeiculo> lstVeicuTranspFiltro = new ArrayList<>(); 

		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlSelectPesq);

			parametros.setInt(1, codTrans);	
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

		} catch (Exception e1) {
			e1.printStackTrace();
		}	
		return lstVeicuTranspFiltro;
	}

	public static boolean insertTipoVeiculo(TipoVeiculo novoVeiculo) {

		Connection c = MySqlConexao.ConectarDb();

		String sqlInsertTransp = "INSERT INTO tblTipoVeiculo (nomeTipoVeiculo) VALUES (?)";

		PreparedStatement parametros;

		try {		
			parametros = c.prepareStatement(sqlInsertTransp);	

			parametros.setString(1, novoVeiculo.getNomeTipoVeiculo());
			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {		
			return false;
		}
	}

	public static boolean updateTipoVeiculo(TipoVeiculo updateTipoVeiculo){

		Connection c = MySqlConexao.ConectarDb();

		String sqlAtualizar = "UPDATE tblTipoVeiculo SET nomeTipoVeiculo = ? WHERE codTipoVeiculo = ? ; ";

		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlAtualizar);

			parametros.setString(1, updateTipoVeiculo.getNomeTipoVeiculo());
			parametros.setInt(2, updateTipoVeiculo.getCodTipoVeiculo());

			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {		
			return false;	
		}
	}

	public static int buscarUltimoIdTipoVeiculo() {

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelectUltimoId = "SELECT * FROM tblTipoVeiculo ORDER BY codTipoVeiculo DESC LIMIT 1";

		int ultimo_id = 0;

		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelectUltimoId);

			while(rs.next()){
				ultimo_id = rs.getInt("codTipoVeiculo");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimo_id;	
	}

	public static boolean deleteTipoVeiculo(int codTipoVeiculo){

		Connection c = MySqlConexao.ConectarDb();
		String sqlDeletar = "DELETE FROM tblTipoVeiculo WHERE codTipoVeiculo = ? ; ";

		PreparedStatement parametros;
		try {
			parametros = c.prepareStatement(sqlDeletar);
			parametros.setInt(1, codTipoVeiculo);
			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {
			return false;
		}	
	}
}
