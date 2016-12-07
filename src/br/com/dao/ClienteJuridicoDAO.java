package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;
import br.com.model.ClienteJuridico;

public class ClienteJuridicoDAO {

	public static List<ClienteJuridico> selecionarTodosClientesJuridicos() {

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelect = "SELECT * FROM tblClienteJuridico ;";

		List <ClienteJuridico> lstClientejuridico = new ArrayList<>(); 

		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelect);

			while(rs.next()){

				ClienteJuridico cj = new ClienteJuridico();

				cj.setCodClienteJuridico(rs.getInt("codClienteJuridico"));
				cj.setNomeContato(rs.getString("nomeContato"));
				cj.setRazaoSocial(rs.getString("razaoSocial"));
				cj.setCnpj(rs.getString("cnpj"));
				cj.setInscricaoEstadual(rs.getString("inscricaoEstadual"));
				cj.setTelefonePrincipal(rs.getString("telefonePrincipal"));
				cj.setTelefoneContato(rs.getString("telefoneContato"));
				cj.setEmailPrincipal(rs.getString("emailPrincipal"));
				cj.setEmailContato(rs.getString("emailContato"));

				lstClientejuridico.add(cj);			
			}	
		} catch (Exception e) {

		}
		return lstClientejuridico;
	}

	public static List<ClienteJuridico> filtrarClienteJuridico(String nomePesquisa){

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelectPesq = "SELECT * FROM tblClienteJuridico WHERE nomeContato LIKE ? ;";

		List <ClienteJuridico> lstClieJuriPesq = new ArrayList<>(); 

		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlSelectPesq);

			parametros.setString(1, nomePesquisa);	
			ResultSet rs = parametros.executeQuery();

			while(rs.next()){

				ClienteJuridico cj = new ClienteJuridico();

				cj.setCodClienteJuridico(rs.getInt("codClienteJuridico"));
				cj.setNomeContato(rs.getString("nomeContato"));
				cj.setRazaoSocial(rs.getString("razaoSocial"));
				cj.setCnpj(rs.getString("cnpj"));
				cj.setInscricaoEstadual(rs.getString("inscricaoEstadual"));
				cj.setTelefonePrincipal(rs.getString("telefonePrincipal"));
				cj.setTelefoneContato(rs.getString("telefoneContato"));
				cj.setEmailPrincipal(rs.getString("emailPrincipal"));
				cj.setEmailContato(rs.getString("emailContato"));

				lstClieJuriPesq.add(cj);			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstClieJuriPesq;
	}

	public static boolean inserirClienteJuridico(ClienteJuridico clienteJuridico){

		Connection c = MySqlConexao.ConectarDb();

		String sqlInsertCliente = "INSERT INTO tblClienteJuridico "
				+ "(nomeContato,razaoSocial, cnpj, inscricaoEstadual, telefonePrincipal, telefoneContato, emailPrincipal, emailContato) "
				+ "VALUES (?,?,?,?,?,?,?,?) ; ";

		try {
			PreparedStatement parametros = c.prepareStatement(sqlInsertCliente);

			parametros.setString(1, clienteJuridico.getNomeContato());
			parametros.setString(2, clienteJuridico.getRazaoSocial());
			parametros.setString(3, clienteJuridico.getCnpj());
			parametros.setString(4, clienteJuridico.getInscricaoEstadual());
			parametros.setString(5, clienteJuridico.getTelefonePrincipal());
			parametros.setString(6, clienteJuridico.getTelefoneContato());
			parametros.setString(7, clienteJuridico.getEmailPrincipal());
			parametros.setString(8, clienteJuridico.getEmailContato());

			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteCliente (int codClienteJuridico ){

		Connection c = MySqlConexao.ConectarDb();

		String sqlDeletarCliente = "DELETE FROM tblClienteJuridico WHERE codClienteJuridico = ? ;" ;

		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlDeletarCliente);
			parametros.setInt(1, codClienteJuridico);
			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {
			return false;
		}	
	}

	public static boolean updateClienteJuridico(ClienteJuridico clienJuriUp) {

		Connection c = MySqlConexao.ConectarDb();

		String sqlAtualizar = "UPDATE tblClienteJuridico "
				+ "SET nomeContato = ?, razaoSocial = ?, cnpj = ?, inscricaoEstadual = ?, telefonePrincipal = ?, telefoneContato = ?, emailPrincipal = ?, emailContato = ? "
				+ "WHERE codClienteJuridico = ?; ";

		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlAtualizar);

			parametros.setString(1, clienJuriUp.getNomeContato());
			parametros.setString(2, clienJuriUp.getRazaoSocial());
			parametros.setString(3, clienJuriUp.getCnpj());
			parametros.setString(4, clienJuriUp.getInscricaoEstadual());
			parametros.setString(5, clienJuriUp.getTelefonePrincipal());
			parametros.setString(6, clienJuriUp.getTelefoneContato());
			parametros.setString(7, clienJuriUp.getEmailPrincipal());
			parametros.setString(8, clienJuriUp.getEmailContato());
			parametros.setInt(9, clienJuriUp.getCodClienteJuridico());

			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;	
		}
	}
}
