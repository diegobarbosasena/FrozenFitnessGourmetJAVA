package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;
import br.com.model.Cidade;
import br.com.model.ClienteJuridico;
import br.com.model.ClienteJuridicoEndereco;
import br.com.model.Endereco;
import br.com.model.Estado;

public class ClienteJuridicoEnderecoDAO {

	public static List<ClienteJuridicoEndereco> filtrarClienteJuridicoEndereco(int codCliente) {

		Connection c = MySqlConexao.ConectarDb();

		String sqlFiltroEndereco = "SELECT  cidade.*, estado.*, endereco.*, clie_juri_ende.*, clie_juri.* "
				+ "FROM tblEstado estado LEFT JOIN tblCidade cidade ON (cidade.codEstado = estado.codEstado) "
				+ "LEFT JOIN tblEndereco endereco ON (cidade.codCidade = endereco.codCidade) "
				+ "LEFT JOIN tblClienteJuridicoEnd clie_juri_ende "
				+ "ON (endereco.codEndereco = clie_juri_ende.codEndereco) "
				+ "LEFT JOIN tblClienteJuridico clie_juri "
				+ "ON (clie_juri.codClienteJuridico = clie_juri_ende.codClienteJuridico)"
				+ "WHERE clie_juri.codClienteJuridico = ? ;";

		List <ClienteJuridicoEndereco> lstClienteJuridicoEndereco = new ArrayList<>(); 

		PreparedStatement p; 
		try {
			p = c.prepareStatement(sqlFiltroEndereco);

			p.setInt(1, codCliente);
			ResultSet rs = p.executeQuery();

			while(rs.next()){

				Estado estado = new Estado();
				Cidade cidade = new Cidade();
				Endereco endereco = new Endereco();	
				ClienteJuridico clie_juri = new ClienteJuridico();
				ClienteJuridicoEndereco clie_juri_ende = new ClienteJuridicoEndereco();

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

				clie_juri.setCodClienteJuridico(rs.getInt("codClienteJuridico"));
				clie_juri.setNomeContato(rs.getString("nomeContato"));
				clie_juri.setRazaoSocial(rs.getString("razaoSocial"));
				clie_juri.setInscricaoEstadual(rs.getString("inscricaoEstadual"));
				clie_juri.setTelefonePrincipal(rs.getString("telefonePrincipal"));
				clie_juri.setTelefoneContato(rs.getString("telefoneContato"));
				clie_juri.setEmailPrincipal(rs.getString("emailPrincipal"));
				clie_juri.setEmailContato(rs.getString("emailContato"));

				clie_juri_ende.setCodClienteJuridicoEnd(rs.getInt("codClienteJuridicoEnd"));
				clie_juri_ende.setCodClienteJuridico(rs.getInt("codClienteJuridico"));
				clie_juri_ende.setCodEndereco(rs.getInt("codEndereco"));

				lstClienteJuridicoEndereco.add(clie_juri_ende);
			}	
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return lstClienteJuridicoEndereco;
	}
}
