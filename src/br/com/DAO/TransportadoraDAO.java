package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;
import br.com.model.Endereco;
import br.com.model.Transportadora;

public class TransportadoraDAO {

	public static List<Transportadora> filtrar(String nomePesquisa){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelectPesq = "SELECT "
				+ "t.*, e.* "
				+ "FROM tblTransportadora AS t INNER JOIN tblEndereco AS e "
				+ "ON (t.codEndereco = e.codEndereco) WHERE nomeTransportadora LIKE ? "
				+ "ORDER BY codTransportadora DESC ;";
	
		List <Transportadora> lstTranspPesq = new ArrayList<>(); 
		PreparedStatement parametros;
		
		try {
			parametros = c.prepareStatement(sqlSelectPesq);
			
			parametros.setString(1, nomePesquisa);	
			ResultSet rs = parametros.executeQuery();

			while(rs.next()){
				
				Transportadora tp = new Transportadora();
				
				Endereco e = new Endereco();
				
				e.setCodEndereco(rs.getInt("codEndereco"));
				e.setLogradouro(rs.getString("logradouro"));
				e.setCep(rs.getString("cep"));
				e.setNumero(rs.getString("numero"));
				e.setBairro(rs.getString("bairro"));
				e.setComplemento(rs.getString("complemento"));
				
				tp.setCodTransportadora(rs.getInt("codTransportadora"));
				tp.setNomeTransportadora(rs.getString("nomeTransportadora"));
				tp.setEmailTransportadora(rs.getString("emailTransportadora"));
				tp.setTelefoneTransportadora(rs.getString("telefoneTransportadora"));
				tp.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				tp.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));
				
				tp.setEndereco(e);
				
				lstTranspPesq.add(tp);			
			}
			c.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		return lstTranspPesq;
	}
	
	public static List<Transportadora> selecionarTodas() {
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelect = "SELECT t.*, e.* "
				+ "FROM tblTransportadora AS t "
				+ "INNER JOIN tblEndereco AS e "
				+ "ON (t.codEndereco = e.codEndereco) "
				+ "ORDER BY codTransportadora DESC; ";

		List <Transportadora> lstTransp = new ArrayList<>(); 
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelect);

			while(rs.next()){
				
				Transportadora t = new Transportadora();
				Endereco e = new Endereco();
				
				e.setCodEndereco(rs.getInt("codEndereco"));
				e.setLogradouro(rs.getString("logradouro"));
				e.setCep(rs.getString("cep"));
				e.setNumero(rs.getString("numero"));
				e.setBairro(rs.getString("bairro"));
				e.setComplemento(rs.getString("complemento"));
				e.setCodCidade(rs.getInt("codCidade"));
					
				t.setCodTransportadora(rs.getInt("codTransportadora"));
				t.setNomeTransportadora(rs.getString("nomeTransportadora"));
				t.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				t.setEmailTransportadora(rs.getString("emailTransportadora"));
				t.setTelefoneTransportadora(rs.getString("telefoneTransportadora"));
				t.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));
			
				t.setEndereco(e);
				
				lstTransp.add(t);			
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstTransp;
	}
	
	public static boolean insertTransportadora(Transportadora novaTrans) {
		
		Connection c = MySqlConexao.ConectarDb();
			
		String sqlInsertTransp = "INSERT INTO tblTransportadora "
				+ "(nomeTransportadora, emailTransportadora, telefoneTransportadora, cnpjTransportadora, responsavelTransportadora, codEndereco) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?); ";
			
		PreparedStatement parametros;
		
		try {		
			parametros = c.prepareStatement(sqlInsertTransp);
			
			parametros.setString(1, novaTrans.getNomeTransportadora());
			parametros.setString(2, novaTrans.getEmailTransportadora());
			parametros.setString(3, novaTrans.getTelefoneTransportadora());
			parametros.setString(4, novaTrans.getCnpjTransportadora());
			parametros.setString(5, novaTrans.getResponsavelTransportadora());
			
			parametros.setInt(6, EnderecoDAO.buscarUltimoId());
			
			parametros.executeUpdate();
			
			c.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();	
			return false;
		}
	}
	
	public static boolean deleteTransp(int codTransp ){
		
		Connection c = MySqlConexao.ConectarDb();
		String sqlDeletar = "DELETE FROM tblTransportadora WHERE codTransportadora = ?;";
		
		PreparedStatement parametros;
		try {
			parametros = c.prepareStatement(sqlDeletar);
			parametros.setInt(1, codTransp);
			parametros.executeUpdate();
				
			c.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();	
			return false;
		}
			
	}
	
	public static boolean update(Transportadora up){
		
		Connection c = MySqlConexao.ConectarDb();
			
		String sqlAtualizar = "UPDATE "
				+ "tblTransportadora set nomeTransportadora = ?, emailTransportadora = ?, "
				+ "telefoneTransportadora = ?, cnpjTransportadora = ?, responsavelTransportadora = ? "
				+ "WHERE codTransportadora = ?";

		PreparedStatement parametros;
			
		try {
			parametros = c.prepareStatement(sqlAtualizar);
			
			parametros.setInt(6, up.getCodTransportadora());
			parametros.setString(1, up.getNomeTransportadora());
			parametros.setString(2, up.getEmailTransportadora());
			parametros.setString(3, up.getTelefoneTransportadora());
			parametros.setString(4, up.getCnpjTransportadora());
			parametros.setString(5, up.getResponsavelTransportadora());
					
			parametros.executeUpdate();
		
			c.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();	
			return false;	
		}
	}
	
}
