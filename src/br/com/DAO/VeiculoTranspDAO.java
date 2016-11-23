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

public class VeiculoTranspDAO {

	public static List<VeiculoTransp> filtrarVeiculo(int codTransp){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlFiltroVeiculo = "CALL pcd_tipoveiculo_transp(?); ";

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lstVeiculoTransp;
	}
}
