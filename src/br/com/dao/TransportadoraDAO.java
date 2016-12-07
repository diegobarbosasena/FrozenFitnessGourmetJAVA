package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				+ "FROM tblTransportadora t "
				+ "LEFT JOIN tblEndereco e "
				+ "ON (t.codEndereco = e.codEndereco) "
				+ "WHERE t.razaoSocial LIKE ? "
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
				tp.setRazaoSocial(rs.getString("razaoSocial"));
				tp.setNomeFantasia(rs.getString("nomeFantasia"));
				tp.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				tp.setTelefonePrincipal(rs.getString("telefonePrincipal"));
				tp.setTelefoneContato(rs.getString("telefoneContato"));
				tp.setEmailPrincipal(rs.getString("emailPrincipal"));
				tp.setEmailContato(rs.getString("emailContato"));
				tp.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));

				tp.setEndereco(e);

				lstTranspPesq.add(tp);			
			}
			c.close();

		} catch (Exception e1) {

		}	
		return lstTranspPesq;
	}

	public static List<Transportadora> selecionarTodas() {

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelect = "SELECT t.*, e.* "
				+ "FROM tblTransportadora t "
				+ "INNER JOIN tblEndereco e "
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
				t.setRazaoSocial(rs.getString("razaoSocial"));
				t.setNomeFantasia(rs.getString("nomeFantasia"));
				t.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				t.setTelefonePrincipal(rs.getString("telefonePrincipal"));
				t.setTelefoneContato(rs.getString("telefoneContato"));
				t.setEmailPrincipal(rs.getString("emailPrincipal"));
				t.setEmailContato(rs.getString("emailContato"));
				t.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));

				t.setEndereco(e);

				lstTransp.add(t);			
			}	
		} catch (Exception e) {

		}
		return lstTransp;
	}

	public static boolean insertTransportadora(Transportadora novaTrans) {

		Connection c = MySqlConexao.ConectarDb();

		String sqlInsertTransp = "INSERT INTO `tblTransportadora` "
				+ "(`razaoSocial`, `nomeFantasia`, `cnpjTransportadora`, `telefonePrincipal`, `telefoneContato`, "
				+ "`emailPrincipal`, `emailContato`, `responsavelTransportadora`, `codEndereco`) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?,? ,?,?); ";

		PreparedStatement parametros;

		try {		
			parametros = c.prepareStatement(sqlInsertTransp);

			parametros.setString(1, novaTrans.getRazaoSocial());
			parametros.setString(2, novaTrans.getNomeFantasia());
			parametros.setString(3, novaTrans.getCnpjTransportadora());
			parametros.setString(4, novaTrans.getTelefonePrincipal());
			parametros.setString(5, novaTrans.getTelefoneContato());
			parametros.setString(6, novaTrans.getEmailPrincipal());
			parametros.setString(7, novaTrans.getEmailContato());
			parametros.setString(8, novaTrans.getResponsavelTransportadora());

			parametros.setInt(9, EnderecoDAO.buscarUltimoIdEndereco());

			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {		
			return false;
		}
	}

	public static boolean deleteTransp(int codTransp ){

		Connection c = MySqlConexao.ConectarDb();
		String sqlDeletar = "DELETE FROM tblTransportadora WHERE codTransportadora = ? ; ";

		PreparedStatement parametros;
		try {
			parametros = c.prepareStatement(sqlDeletar);
			parametros.setInt(1, codTransp);
			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {
			return false;
		}	
	}

	public static boolean update(Transportadora updateTransp){

		Connection c = MySqlConexao.ConectarDb();

		String sqlAtualizar = "UPDATE tblTransportadora "
				+ "SET razaoSocial = ?, nomeFantasia = ?, cnpjTransportadora = ?, telefonePrincipal = ?, "
				+ "telefoneContato = ?, emailPrincipal = ?, emailContato = ?, responsavelTransportadora = ? "
				+ "WHERE codTransportadora = ? ; ";

		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlAtualizar);

			parametros.setString(1, updateTransp.getRazaoSocial());
			parametros.setString(2, updateTransp.getNomeFantasia());
			parametros.setString(3, updateTransp.getCnpjTransportadora());
			parametros.setString(4, updateTransp.getTelefonePrincipal());
			parametros.setString(5, updateTransp.getTelefoneContato());
			parametros.setString(6, updateTransp.getEmailPrincipal());
			parametros.setString(7, updateTransp.getEmailContato());
			parametros.setString(8, updateTransp.getResponsavelTransportadora());
			parametros.setInt(9, updateTransp.getCodTransportadora());

			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {
			return false;	
		}
	}

}
