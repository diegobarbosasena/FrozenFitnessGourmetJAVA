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

public class ClienteEnderecoDAO {

	public static List<ClienteEndereco> filtrarClienteEndereco(int codCliente) {

		Connection c = MySqlConexao.ConectarDb();

		String sqlFiltroEndereco = "SELECT  cidade.*, estado.*, endereco.*, clie_ende.*, cliente.* "
				+ "FROM tblEstado estado LEFT JOIN tblCidade cidade "
				+ "ON (cidade.codEstado = estado.codEstado) "
				+ "LEFT JOIN tblEndereco endereco ON (cidade.codCidade = endereco.codCidade) "
				+ "LEFT JOIN tblClienteEnd clie_ende ON (endereco.codEndereco = clie_ende.codEndereco) "
				+ "LEFT JOIN tblCliente cliente ON (cliente.codCliente = clie_ende.codCliente) "
				+ "WHERE cliente.codCliente = ? ;";

		List <ClienteEndereco> lstClienteEndereco = new ArrayList<>(); 

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

				lstClienteEndereco.add(clie_ende);
			}	
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return lstClienteEndereco;
	}

}
