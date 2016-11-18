package br.com.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				
				cl.setNomeCliente(rs.getString("nomeCliente"));
				cl.setCpfCliente(rs.getString("cpfCliente"));
				cl.setDtNascCliente(rs.getDate("dtNascCliente"));
				cl.setPeso(rs.getFloat("peso"));
				cl.setAltura(rs.getFloat("altura"));
				cl.setTelefoneCliente(rs.getString("telefoneCliente"));
				cl.setCelularCliente(rs.getString("celularCliente"));
				cl.setCelularCliente(rs.getString("celularCliente"));
				
				lstCliente.add(cl);			
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCliente;
	}

	public static boolean inserirCliente(Cliente cliente){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlInsertCliente = "INSERT INTO tblCliente "
				+ "(nomeCliente, cpfCliente, dtNascCliente, peso, altura, telefoneCliente, celularCliente, emailCliente, sexo) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?); ";
		
		try {
			PreparedStatement parametros = c.prepareStatement(sqlInsertCliente);
			
			parametros.setString(1, cliente.getNomeCliente());
			parametros.setString(2, cliente.getCpfCliente());
			parametros.setDate(3, (Date) cliente.getDtNascCliente());
			parametros.setFloat(4, cliente.getPeso());
			parametros.setFloat(5, cliente.getAltura());
			parametros.setString(6, cliente.getTelefoneCliente());
			parametros.setString(7, cliente.getCelularCliente());
			parametros.setString(8, cliente.getEmailCliente());
			
			parametros.executeUpdate();
			
			c.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteCliente (int codCliente ){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlDeletarCliente = "DELETE FROM tblCliente  WHERE codCliente ? ;" ;
		
		PreparedStatement parametros;
		
		try {
			parametros = c.prepareStatement(sqlDeletarCliente);
			parametros.setInt(1, codCliente);
			parametros.executeUpdate();
		
			c.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	public static int buscarUltimoIdCliente() {
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelectUltimoId = "SELECT * FROM tblCliente ORDER BY codEndereco DESC LIMIT 1";

		int ultimo_id = 0;
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelectUltimoId);

			while(rs.next()){
				ultimo_id = rs.getInt("codEndereco");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ultimo_id;	
	}
		
}
