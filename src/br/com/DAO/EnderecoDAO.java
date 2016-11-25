package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.ajudantes.MySqlConexao;
import br.com.model.Endereco;

public class EnderecoDAO {

public static boolean insertEndereco(Endereco novoEnde) {
		
		Connection c = MySqlConexao.ConectarDb();
			
		String sqlInsertEndereco = "INSERT INTO tblEndereco "
				+ "(logradouro, cep, numero, bairro, complemento, codCidade) "
				+ "VALUES (?, ?, ?, ?, ?, ?) ";
		
		PreparedStatement parametros;
		
		try {
			
			parametros = c.prepareStatement(sqlInsertEndereco);
			
			parametros.setString(1, novoEnde.getLogradouro());
			parametros.setString(2, novoEnde.getCep());
			parametros.setString(3, novoEnde.getNumero());
			parametros.setString(4, novoEnde.getBairro());
			parametros.setString(5, novoEnde.getComplemento());
			parametros.setInt(6, novoEnde.getCidade().getCodCidade());
			
			parametros.executeUpdate();
				
			c.close();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public static boolean deleteEnde(int codEndereco2) {
		
		Connection c = MySqlConexao.ConectarDb();
		String sqlDeletar = "DELETE FROM tblEndereco WHERE codEndereco = ?;";
			
		PreparedStatement parametros;
		try {
			parametros = c.prepareStatement(sqlDeletar);
			parametros.setInt(1, codEndereco2);
			parametros.executeUpdate();
				
			c.close();
			
			return true;
			
		} catch (Exception e) {	
			return false;
		}
	}
	
	public static boolean updateEnde(Endereco upEnde){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlAtualizarEnde = "UPDATE tblEndereco set logradouro = ?, "
				+ "cep = ?, numero = ?, bairro = ?, complemento = ?, codCidade = ? WHERE codEndereco = ?";

		PreparedStatement parametros;
			
		try {
			parametros = c.prepareStatement(sqlAtualizarEnde);
			
			parametros.setString(1, upEnde.getLogradouro());
			parametros.setString(2, upEnde.getCep());
			parametros.setString(3, upEnde.getNumero());
			parametros.setString(4, upEnde.getBairro());
			parametros.setString(5, upEnde.getComplemento());
			parametros.setInt(6, upEnde.getCidade().getCodCidade());
			parametros.setInt(7, upEnde.getCodEndereco());
			
			parametros.executeUpdate();
		
			c.close();
			
			return true;
			
		} catch (Exception e) {	
			return false;	
		}
	
	}
	
	public static int buscarUltimoIdEndereco() {
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlSelectUltimoId = "SELECT * FROM tblEndereco ORDER BY codEndereco DESC LIMIT 1";

		int ultimo_id = 0;
		
		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlSelectUltimoId);

			while(rs.next()){
				ultimo_id = rs.getInt("codEndereco");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimo_id;	
	}
	
}
