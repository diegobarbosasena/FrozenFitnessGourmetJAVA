package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;
import br.com.model.Cidade;
import br.com.model.Cliente;
import br.com.model.ClienteEndereco;
import br.com.model.Endereco;
import br.com.model.Estado;
import br.com.model.Transportadora;

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

	public static List<Endereco> filtrarEndereco(int codEnde){

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelectPesq = "SELECT en.*, es.*, cd.* FROM tblEndereco en "
				+ "LEFT JOIN tblCidade cd ON (en.codCidade = cd.codCidade) "
				+ "LEFT JOIN tblEstado es ON (es.codEstado = cd.codEstado) "
				+ "WHERE codEndereco = ? ;";

		List <Endereco> lstEnde = new ArrayList<>(); 

		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlSelectPesq);

			parametros.setInt(1, codEnde);	
			ResultSet rs = parametros.executeQuery();

			while(rs.next()){

				Endereco e = new Endereco();
				Cidade cd = new Cidade();
				Estado es = new Estado();

				e.setCodEndereco(rs.getInt("codEndereco"));
				e.setLogradouro(rs.getString("logradouro"));
				e.setCep(rs.getString("cep"));
				e.setNumero(rs.getString("numero"));
				e.setBairro(rs.getString("bairro"));
				e.setComplemento(rs.getString("complemento"));

				cd.setCodCidade(rs.getInt("codCidade"));
				cd.setNomeCidade(rs.getString("nomeCidade"));

				es.setCodEstado(rs.getInt("codEstado"));
				es.setNomeEstado(rs.getString("nomeEstado"));
				es.setUf(rs.getString("uf"));

				cd.setEstado(es);
				e.setCidade(cd);

				lstEnde.add(e);			
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return lstEnde;
	}

	public static List<Endereco> filtrarEnderecoTransportadora(int codEnde){

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelectPesq = "SELECT transportadora.*, endereco.*, cidade.*, endereco.*, estado.* "
				+ "FROM tblTransportadora transportadora "
				+ "INNER JOIN tblEndereco endereco "
				+ "ON(transportadora.codEndereco = endereco.codEndereco) "
				+ "INNER JOIN tblCidade cidade "
				+ "ON(cidade.codCidade = endereco.codCidade) "
				+ "INNER JOIN tblEstado estado "
				+ "ON(cidade.codEstado = estado.codEstado) "
				+ "WHERE transportadora.codTransportadora = ?;";

		List <Endereco> lstEndeTransp = new ArrayList<>(); 

		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlSelectPesq);

			parametros.setInt(1, codEnde);	
			ResultSet rs = parametros.executeQuery();

			while(rs.next()){

				Transportadora transportadora = new Transportadora();
				Endereco endereco = new Endereco();
				Cidade cidade = new Cidade();
				Estado estado = new Estado();

				estado.setCodEstado(rs.getInt("codEndereco"));
				estado.setNomeEstado(rs.getString("nomeEstado"));
				estado.setUf(rs.getString("uf"));

				cidade.setCodCidade(rs.getInt("codCidade"));
				cidade.setCodEstado(rs.getInt("codEstado"));
				cidade.setNomeCidade(rs.getString("nomeCidade"));
				cidade.setEstado(estado);

				endereco.setCodEndereco(rs.getInt("codEndereco"));
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setCep(rs.getString("cep"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setCodCidade(rs.getInt("codCidade"));
				endereco.setCidade(cidade);	

				transportadora.setCodTransportadora(rs.getInt("codTransportadora"));
				transportadora.setRazaoSocial(rs.getString("razaoSocial"));
				transportadora.setNomeFantasia(rs.getString("nomeFantasia"));
				transportadora.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				transportadora.setTelefonePrincipal(rs.getString("telefonePrincipal"));
				transportadora.setTelefoneContato(rs.getString("telefoneContato"));
				transportadora.setEmailPrincipal(rs.getString("emailPrincipal"));
				transportadora.setEmailContato(rs.getString("emailContato"));
				transportadora.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));
				transportadora.setCodEndereco(rs.getInt("codEndereco"));
				transportadora.setEndereco(endereco);

				lstEndeTransp.add(endereco);			
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return lstEndeTransp;
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

		}
		return ultimo_id;	
	}

	public static List<Endereco> filtrarEnderecoCliente(int codCliente) {

		Connection c = MySqlConexao.ConectarDb();

		String sqlFiltroEndereco = "SELECT cidade.*, estado.*, endereco.*, clie_ende.*, cliente.* "
				+ "FROM tblCidade cidade "
				+ "INNER JOIN tblEstado estado "
				+ "ON(cidade.codEstado = estado.codEstado) "
				+ "INNER JOIN  tblEndereco endereco "
				+ "ON(endereco.codCidade = cidade.codCidade) "
				+ "INNER JOIN tblClienteEnd clie_ende "
				+ "ON(endereco.codEndereco = clie_ende.codEndereco) "
				+ "INNER JOIN tblCliente cliente "
				+ "ON(clie_ende.codCliente = cliente.codCliente) "
				+ "WHERE cliente.codCliente = ? ;";

		List <Endereco> lstClienteEndereco = new ArrayList<>(); 

		PreparedStatement p; 
		try {
			p = c.prepareStatement(sqlFiltroEndereco);

			p.setInt(1, codCliente);
			ResultSet rs = p.executeQuery();

			while(rs.next()){

				Estado estado = new Estado();
				Cidade cidade = new Cidade();
				Endereco endereco = new Endereco();
				Cliente cliente = new Cliente();
				ClienteEndereco clie_ende = new ClienteEndereco();

				estado.setCodEstado(rs.getInt("codEndereco"));
				estado.setNomeEstado(rs.getString("nomeEstado"));
				estado.setUf(rs.getString("uf"));

				cidade.setCodCidade(rs.getInt("codCidade"));
				cidade.setCodEstado(rs.getInt("codEstado"));
				cidade.setNomeCidade(rs.getString("nomeCidade"));
				cidade.setEstado(estado);

				endereco.setCodEndereco(rs.getInt("codEndereco"));
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setCep(rs.getString("cep"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setCodCidade(rs.getInt("codCidade"));
				endereco.setCidade(cidade);

				cliente.setCodCliente(rs.getInt("codCliente"));
				cliente.setNomeCliente(rs.getString("nomeCliente"));
				cliente.setCpfCliente(rs.getString("cpfCliente"));
				cliente.setDtNascCliente(rs.getDate("dtNascCliente"));
				cliente.setPeso(rs.getFloat("peso"));
				cliente.setAltura(rs.getFloat("altura"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setTelefoneCliente(rs.getString("telefoneCliente"));
				cliente.setCelularCliente(rs.getString("celularCliente"));
				cliente.setEmailCliente(rs.getString("emailCliente"));

				clie_ende.setCodClienteEnd(rs.getInt("codClienteEnd"));
				clie_ende.setCodCliente(rs.getInt("codCliente"));
				clie_ende.setCodClienteEnd(rs.getInt("codClienteEnd"));

				clie_ende.setEndereco(endereco);
				clie_ende.setCliente(cliente);

				lstClienteEndereco.add(endereco);
			}	
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return lstClienteEndereco;
	}
}
