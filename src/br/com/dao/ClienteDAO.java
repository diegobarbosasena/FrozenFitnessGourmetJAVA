package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;
import br.com.model.Cliente;

public class ClienteDAO {

	public static List<Cliente> selecionarTodosClientes() {

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelect = "SELECT * FROM tblCliente ;";

		List <Cliente> lstCliente = new ArrayList<>(); 

		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelect);

			while(rs.next()){

				Cliente cl = new Cliente();

				cl.setCodCliente(rs.getInt("codCliente"));
				cl.setNomeCliente(rs.getString("nomeCliente"));
				cl.setCpfCliente(rs.getString("cpfCliente"));
				cl.setDtNascCliente(rs.getDate("dtNascCliente"));
				cl.setPeso(rs.getFloat("peso"));
				cl.setAltura(rs.getFloat("altura"));
				cl.setEmailCliente(rs.getString("emailCliente"));
				cl.setTelefoneCliente(rs.getString("telefoneCliente"));
				cl.setCelularCliente(rs.getString("celularCliente"));
				cl.setSexo(rs.getString("sexo"));

				lstCliente.add(cl);			
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstCliente;
	}

	public static List<Cliente> filtrarCliente(String nomePesquisa){

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelectPesq = "SELECT * FROM tblCliente WHERE nomeCliente LIKE ? ;";

		List <Cliente> lstCliePesq = new ArrayList<>(); 

		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlSelectPesq);

			parametros.setString(1, nomePesquisa);	
			ResultSet rs = parametros.executeQuery();

			while(rs.next()){

				Cliente cl = new Cliente();

				cl.setCodCliente(rs.getInt("codCliente"));
				cl.setNomeCliente(rs.getString("nomeCliente"));
				cl.setCpfCliente(rs.getString("cpfCliente"));
				cl.setDtNascCliente(rs.getDate("dtNascCliente"));
				cl.setPeso(rs.getFloat("peso"));
				cl.setAltura(rs.getFloat("altura"));
				cl.setTelefoneCliente(rs.getString("telefoneCliente"));
				cl.setCelularCliente(rs.getString("celularCliente"));
				cl.setCelularCliente(rs.getString("celularCliente"));

				lstCliePesq.add(cl);					
			}
			c.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}	
		return lstCliePesq;
	}

	public static boolean inserirCliente(Cliente cliente){

		Connection c = MySqlConexao.ConectarDb();

		String sqlInsertCliente = "INSERT INTO tblCliente "
				+ "(nomeCliente, cpfCliente, dtNascCliente, peso, altura, telefoneCliente, celularCliente, emailCliente, sexo) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); ";

		try {
			PreparedStatement parametros = c.prepareStatement(sqlInsertCliente);

			parametros.setString(1, cliente.getNomeCliente());
			parametros.setString(2, cliente.getCpfCliente());
			parametros.setDate(3, cliente.getDtNascCliente());
			parametros.setFloat(4, cliente.getPeso());
			parametros.setFloat(5, cliente.getAltura());
			parametros.setString(6, cliente.getTelefoneCliente());
			parametros.setString(7, cliente.getCelularCliente());
			parametros.setString(8, cliente.getEmailCliente());
			parametros.setString(9, cliente.getSexo());

			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteCliente (int codCliente ){

		Connection c = MySqlConexao.ConectarDb();

		String sqlDeletarCliente = "DELETE FROM tblCliente WHERE codCliente = ? ;" ;

		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlDeletarCliente);
			parametros.setInt(1, codCliente);
			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {
			return false;
		}	
	}

	public static boolean updateCliente(Cliente clienteUp) {

		Connection c = MySqlConexao.ConectarDb();

		String sqlAtualizar = "UPDATE tblCliente SET nomeCliente= ?, "
				+ "cpfCliente = ?, dtNascCliente = ?, peso = ?, altura = ?, "
				+ "telefoneCliente = ?, celularCliente = ?, "
				+ "emailCliente = ?, sexo = ? WHERE codCliente = ?; ";

		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlAtualizar);

			parametros.setString(1, clienteUp.getNomeCliente());
			parametros.setString(2, clienteUp.getCpfCliente());
			parametros.setDate(3, clienteUp.getDtNascCliente());
			parametros.setFloat(4, clienteUp.getPeso());
			parametros.setFloat(5, clienteUp.getAltura());
			parametros.setString(6, clienteUp.getTelefoneCliente());
			parametros.setString(7, clienteUp.getCelularCliente());
			parametros.setString(8, clienteUp.getEmailCliente());
			parametros.setString(9, clienteUp.getSexo());	
			parametros.setInt(10, clienteUp.getCodCliente());

			parametros.executeUpdate();

			c.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();

			return false;	
		}
	}

}
