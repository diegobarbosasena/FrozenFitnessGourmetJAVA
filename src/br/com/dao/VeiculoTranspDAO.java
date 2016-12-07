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

public class VeiculoTranspDAO {


	public static List<VeiculoTransp> obterTodosVeiculos(){

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelect = "SELECT vt.* , tv.* "
				+ "FROM tblVeiculoTransp vt LEFT JOIN tblTipoVeiculo tv "
				+ "ON (vt.codTipoVeiculo = tv.codTipoVeiculo) ; ";

		List <VeiculoTransp> lstVeiculo = new ArrayList<>(); 

		ResultSet rs;

		try {
			rs = c.createStatement().executeQuery(sqlSelect);

			while(rs.next()){

				TipoVeiculo tv = new TipoVeiculo();
				VeiculoTransp vt = new VeiculoTransp();

				tv.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				tv.setNomeTipoVeiculo(rs.getString("nomeTipoVeiculo"));

				vt.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));
				vt.setPlacaVeiculo(rs.getString("placaVeiculo"));
				vt.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				vt.setCodTransportadora(rs.getInt("codTransportadora"));

				vt.setTipoVeiculo(tv);

				lstVeiculo.add(vt);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstVeiculo;
	}

	public static List<VeiculoTransp> filtrarVeiculo(int codTransp){

		Connection c = MySqlConexao.ConectarDb();

		//String sqlFiltroVeiculo = "CALL pcd_tipoveiculo_transp(?); ";

		String sqlFiltroVeiculo = "SELECT vt.*, t.*, tv.* "
				+ "FROM tblTransportadora t "
				+ "LEFT JOIN tblVeiculoTransp vt ON (vt.codTransportadora = t.codTransportadora) "
				+ "LEFT JOIN tblTipoVeiculo tv  ON (tv.codTipoVeiculo = vt.codTipoVeiculo)"
				+ "WHERE t.codTransportadora = ?;";

		List <VeiculoTransp> lstVeiculoTransp = new ArrayList<>(); 

		PreparedStatement p; 
		try {
			p = c.prepareStatement(sqlFiltroVeiculo);

			p.setInt(1, codTransp);
			ResultSet rs = p.executeQuery();

			while(rs.next()){

				Transportadora t = new Transportadora();
				TipoVeiculo tv = new TipoVeiculo();
				VeiculoTransp vt = new VeiculoTransp();

				t.setCodTransportadora(rs.getInt("codTransportadora"));
				t.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				t.setEmailContato(rs.getString("emailContato"));
				t.setEmailPrincipal(rs.getString("emailPrincipal"));
				t.setTelefonePrincipal(rs.getString("telefonePrincipal"));
				t.setTelefoneContato(rs.getString("telefoneContato"));
				t.setNomeFantasia(rs.getString("nomeFantasia"));
				t.setRazaoSocial(rs.getString("razaoSocial"));
				t.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));

				tv.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				tv.setNomeTipoVeiculo(rs.getString("nomeTipoVeiculo"));

				vt.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));
				vt.setPlacaVeiculo(rs.getString("placaVeiculo"));
				vt.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				vt.setCodTransportadora(rs.getInt("codTransportadora"));

				vt.setTipoVeiculo(tv);
				vt.setTransportadora(t);

				lstVeiculoTransp.add(vt);
			}	

		} catch (Exception e) {

		}
		return lstVeiculoTransp;
	}

	public static List<VeiculoTransp> buscarVeiculo(String nomeVeiculo){

		Connection c = MySqlConexao.ConectarDb();

		//String sqlFiltroVeiculo = "CALL pcd_buscaveiculo(?); ";

		String sqlFiltroVeiculo = "SELECT vt.* , tv.* "
				+ "FROM tblVeiculoTransp vt "
				+ "LEFT JOIN tblTipoVeiculo tv "
				+ "ON (vt.codTipoVeiculo = tv.codTipoVeiculo) "
				+ "WHERE tv.nomeTipoVeiculo LIKE ? ; "; 

		List <VeiculoTransp> lstVeiculoTransp = new ArrayList<>(); 

		PreparedStatement p; 
		try {
			p = c.prepareStatement(sqlFiltroVeiculo);

			p.setString(1, nomeVeiculo);
			ResultSet rs = p.executeQuery();

			while(rs.next()){

				TipoVeiculo tv = new TipoVeiculo();
				VeiculoTransp vt = new VeiculoTransp();

				tv.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				tv.setNomeTipoVeiculo(rs.getString("nomeTipoVeiculo"));

				vt.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));
				vt.setPlacaVeiculo(rs.getString("placaVeiculo"));
				vt.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				vt.setCodTransportadora(rs.getInt("codTransportadora"));

				vt.setTipoVeiculo(tv);

				lstVeiculoTransp.add(vt);
			}	

		} catch (Exception e) {

		}
		return lstVeiculoTransp;	
	}

	public static boolean insertVeiculoTransp(VeiculoTransp novoVeiculoTransp) {

		Connection c = MySqlConexao.ConectarDb();

		String sqlInsertVeiculoTransp = "INSERT INTO tblVeiculoTransp "
				+ "(placaVeiculo, codTipoVeiculo, codTransportadora) VALUES (?,?,?) ; ";

		PreparedStatement parametros;

		try {		
			parametros = c.prepareStatement(sqlInsertVeiculoTransp);

			parametros.setString(1, novoVeiculoTransp.getPlacaVeiculo());
			parametros.setInt(2, TipoVeiculoDAO.buscarUltimoIdTipoVeiculo());
			parametros.setInt(3, novoVeiculoTransp.getCodTransportadora());

			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {	
			return false;
		}
	}

	public static boolean updateVeiculoTransp(VeiculoTransp updateVeiculoTransp){

		Connection c = MySqlConexao.ConectarDb();

		String sqlAtualizar = "UPDATE tblVeiculoTransp SET "
				+ "placaVeiculo = ?, codTipoVeiculo = ?, codTransportadora = ? WHERE codVeiculoTrans = ? ; ";

		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlAtualizar);

			parametros.setString(1, updateVeiculoTransp.getPlacaVeiculo());
			parametros.setInt(2, updateVeiculoTransp.getTipoVeiculo().getCodTipoVeiculo());
			parametros.setInt(3, updateVeiculoTransp.getTransportadora().getCodTransportadora());

			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {	
			return false;	
		}
	}

	public static boolean deleteVeiculoTransp(int codVeiculoTransp ){

		Connection c = MySqlConexao.ConectarDb();
		String sqlDeletar = "DELETE FROM tblVeiculoTransp WHERE codVeiculoTransp = ? ; ";

		PreparedStatement parametros;
		try {
			parametros = c.prepareStatement(sqlDeletar);
			parametros.setInt(1, codVeiculoTransp);
			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {	
			return false;
		}	
	}
}
