package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;
import br.com.model.Cliente;
import br.com.model.Pedidos;
import br.com.model.Status;
import br.com.model.TipoVeiculo;
import br.com.model.Transportadora;
import br.com.model.VeiculoTransp;

public class PedidosDAO {

	public static List<Pedidos> selecionarTodosPedidos(){

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelect = "CALL pcd_lista_pedi_clie_sta_tipoveic_trans_veictrans() ;";

		List<Pedidos> lstPedidos = new ArrayList<>();

		ResultSet rs;
		try {	
			rs = c.createStatement().executeQuery(sqlSelect);

			while (rs.next()){

				Pedidos p = new Pedidos();
				Cliente cl = new Cliente();
				Status s = new Status();
				TipoVeiculo tv = new TipoVeiculo();
				Transportadora t = new Transportadora();
				VeiculoTransp vt = new VeiculoTransp();

				vt.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));
				vt.setPlacaVeiculo(rs.getString("placaVeiculo"));
				vt.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				vt.setCodTransportadora(rs.getInt("codTransportadora"));

				vt.setTransportadora(t);

				t.setCodTransportadora(rs.getInt("codTransportadora"));
				t.setRazaoSocial(rs.getString("razaoSocial"));
				t.setNomeFantasia(rs.getString("nomeFantasia"));
				t.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				t.setTelefonePrincipal(rs.getString("telefonePrincipal"));
				t.setTelefoneContato(rs.getString("telefoneContato"));
				t.setEmailPrincipal(rs.getString("emailPrincipal"));
				t.setEmailContato(rs.getString("emailContato"));
				t.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));

				cl.setCodCliente(rs.getInt("codCliente"));
				cl.setNomeCliente(rs.getString("nomeCliente"));
				cl.setCpfCliente(rs.getString("cpfCliente"));
				cl.setDtNascCliente(rs.getDate("dtNascCliente"));
				cl.setPeso(rs.getFloat("peso"));
				cl.setAltura(rs.getFloat("altura"));
				cl.setTelefoneCliente(rs.getString("telefoneCliente"));
				cl.setCelularCliente(rs.getString("celularCliente"));
				cl.setEmailCliente(rs.getString("emailCliente"));

				s.setCodStatus(rs.getInt("codStatus"));
				s.setStatusPedido(rs.getString("statusPedido"));

				tv.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				tv.setNomeTipoVeiculo(rs.getString("nomeTipoVeiculo"));

				p.setCodPedido(rs.getInt("codPedido"));
				p.setTipoPedido(rs.getString("tipoPedido"));
				p.setDtEntrega(rs.getDate("dtEntrega"));
				p.setDtCompra(rs.getDate("dtCompra"));
				p.setTotal(rs.getFloat("total"));
				p.setCodCliente(rs.getInt("codCliente"));
				p.setCodStatus(rs.getInt("codStatus"));
				p.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));

				p.setTipoVeiculo(tv);
				p.setTransportadora(t);
				p.setCliente(cl);
				p.setStatus(s);
				p.setVeiculoTransp(vt);

				lstPedidos.add(p);
			}
		} catch (Exception e) {

		}
		return lstPedidos;
	}

	public static List<Pedidos> filtrarPedidos(int codPedido) {

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelectPesqPedido = "CALL pcd_filtro_pedido(?)";

		List <Pedidos> lstPediPesq = new ArrayList<>(); 
		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlSelectPesqPedido);

			parametros.setInt(1, codPedido);	
			ResultSet rs = parametros.executeQuery();

			while(rs.next()){

				Pedidos p = new Pedidos();
				Cliente cl = new Cliente();
				Status s = new Status();
				TipoVeiculo tv = new TipoVeiculo();
				Transportadora t = new Transportadora();
				VeiculoTransp vt = new VeiculoTransp();

				vt.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));
				vt.setPlacaVeiculo(rs.getString("placaVeiculo"));
				vt.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				vt.setCodTransportadora(rs.getInt("codTransportadora"));

				vt.setTransportadora(t);

				t.setCodTransportadora(rs.getInt("codTransportadora"));
				t.setRazaoSocial(rs.getString("razaoSocial"));
				t.setNomeFantasia(rs.getString("nomeFantasia"));
				t.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				t.setTelefonePrincipal(rs.getString("telefonePrincipal"));
				t.setTelefoneContato(rs.getString("telefoneContato"));
				t.setEmailPrincipal(rs.getString("emailPrincipal"));
				t.setEmailContato(rs.getString("emailContato"));
				t.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));

				cl.setCodCliente(rs.getInt("codCliente"));
				cl.setNomeCliente(rs.getString("nomeCliente"));
				cl.setCpfCliente(rs.getString("cpfCliente"));
				cl.setDtNascCliente(rs.getDate("dtNascCliente"));
				cl.setPeso(rs.getFloat("peso"));
				cl.setAltura(rs.getFloat("altura"));
				cl.setTelefoneCliente(rs.getString("telefoneCliente"));
				cl.setCelularCliente(rs.getString("celularCliente"));
				cl.setEmailCliente(rs.getString("emailCliente"));

				s.setCodStatus(rs.getInt("codStatus"));
				s.setStatusPedido(rs.getString("statusPedido"));

				tv.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				tv.setNomeTipoVeiculo(rs.getString("nomeTipoVeiculo"));

				p.setCodPedido(rs.getInt("codPedido"));
				p.setTipoPedido(rs.getString("tipoPedido"));
				p.setDtEntrega(rs.getDate("dtEntrega"));
				p.setDtCompra(rs.getDate("dtCompra"));
				p.setTotal(rs.getFloat("total"));
				p.setCodCliente(rs.getInt("codCliente"));
				p.setCodStatus(rs.getInt("codStatus"));
				p.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));

				p.setTipoVeiculo(tv);
				p.setTransportadora(t);
				p.setCliente(cl);
				p.setStatus(s);
				p.setVeiculoTransp(vt);

				lstPediPesq.add(p);			
			}
			c.close();

		} catch (Exception e1) {

		}	
		return lstPediPesq;
	}

	public static Float somarTodosPedidos(int codStatus){

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelect = "SELECT SUM(total) total FROM tblPedido WHERE codStatus = ?;";

		Float total = null;

		PreparedStatement parametros;
		try {	
			parametros = c.prepareStatement(sqlSelect);

			parametros.setInt(1, codStatus);	
			ResultSet rs = parametros.executeQuery();

			while (rs.next()){

				total =(float) rs.getFloat("total");
			}
		} catch (Exception e) {

		}
		return total;
	}

	public static Float somarTodosPedidos(){

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelect = "SELECT SUM(total) total FROM tblPedido;";

		Float total = null;

		ResultSet rs;

		try {	
			rs = c.createStatement().executeQuery(sqlSelect);
			while (rs.next()){
				total =(float) rs.getFloat("total");
			}
		} catch (Exception e) {
			
		}
		return total;
	}

	public static List<Pedidos> filtrarPedidosStatus(int codStatus) {

		Connection c = MySqlConexao.ConectarDb();

		String sqlSelectPesqPedido = "CALL pcd_filtro_status(?)";

		List <Pedidos> lstPediPesq = new ArrayList<>(); 
		PreparedStatement parametros;

		try {
			parametros = c.prepareStatement(sqlSelectPesqPedido);

			parametros.setInt(1, codStatus);	
			ResultSet rs = parametros.executeQuery();

			while(rs.next()){

				Pedidos p = new Pedidos();
				Cliente cl = new Cliente();
				Status s = new Status();
				TipoVeiculo tv = new TipoVeiculo();
				Transportadora t = new Transportadora();
				VeiculoTransp vt = new VeiculoTransp();

				vt.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));
				vt.setPlacaVeiculo(rs.getString("placaVeiculo"));
				vt.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				vt.setCodTransportadora(rs.getInt("codTransportadora"));

				vt.setTransportadora(t);

				t.setCodTransportadora(rs.getInt("codTransportadora"));
				t.setRazaoSocial(rs.getString("razaoSocial"));
				t.setNomeFantasia(rs.getString("nomeFantasia"));
				t.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				t.setTelefonePrincipal(rs.getString("telefonePrincipal"));
				t.setTelefoneContato(rs.getString("telefoneContato"));
				t.setEmailPrincipal(rs.getString("emailPrincipal"));
				t.setEmailContato(rs.getString("emailContato"));
				t.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));

				cl.setCodCliente(rs.getInt("codCliente"));
				cl.setNomeCliente(rs.getString("nomeCliente"));
				cl.setCpfCliente(rs.getString("cpfCliente"));
				cl.setDtNascCliente(rs.getDate("dtNascCliente"));
				cl.setPeso(rs.getFloat("peso"));
				cl.setAltura(rs.getFloat("altura"));
				cl.setTelefoneCliente(rs.getString("telefoneCliente"));
				cl.setCelularCliente(rs.getString("celularCliente"));
				cl.setEmailCliente(rs.getString("emailCliente"));

				s.setCodStatus(rs.getInt("codStatus"));
				s.setStatusPedido(rs.getString("statusPedido"));

				tv.setCodTipoVeiculo(rs.getInt("codTipoVeiculo"));
				tv.setNomeTipoVeiculo(rs.getString("nomeTipoVeiculo"));

				p.setCodPedido(rs.getInt("codPedido"));
				p.setTipoPedido(rs.getString("tipoPedido"));
				p.setDtEntrega(rs.getDate("dtEntrega"));
				p.setDtCompra(rs.getDate("dtCompra"));
				p.setTotal(rs.getFloat("total"));
				p.setCodCliente(rs.getInt("codCliente"));
				p.setCodStatus(rs.getInt("codStatus"));
				p.setCodVeiculoTransp(rs.getInt("codVeiculoTransp"));

				p.setTipoVeiculo(tv);
				p.setTransportadora(t);
				p.setCliente(cl);
				p.setStatus(s);
				p.setVeiculoTransp(vt);

				lstPediPesq.add(p);			
			}
			c.close();

		} catch (Exception e1) {

		}	
		return lstPediPesq;
	}

	public static boolean updatePedido(Integer codVeic, Integer codPed, Integer codStat){

		boolean retorno = false;

		switch (codStat) {

		case 1:

			Connection c = MySqlConexao.ConectarDb();

			PreparedStatement parametros;

			String sqlAtualizarPedido = "UPDATE tblPedido SET codStatus = ? , codVeiculoTransp = null WHERE codPedido = ? ;";

			try {
				parametros = c.prepareStatement(sqlAtualizarPedido);

				parametros.setInt(1, codStat);
				parametros.setInt(2, codPed);

				parametros.executeUpdate();

				c.close();

				retorno = true;

			} catch (Exception e) {	
				e.printStackTrace();
				retorno =  false;	
			}
			break;	
			
		case 2:

			Connection c1 = MySqlConexao.ConectarDb();

			PreparedStatement parametros1;

			String sqlAtualizarPedido1 = "UPDATE tblPedido SET codStatus = ? , codVeiculoTransp = null WHERE codPedido = ? ;";

			try {
				parametros1 = c1.prepareStatement(sqlAtualizarPedido1);

				parametros1.setInt(1, codStat);
				parametros1.setInt(2, codPed);

				parametros1.executeUpdate();

				c1.close();

				retorno = true;

			} catch (Exception e) {

				e.printStackTrace();
				retorno = false;	
			}
			break;
			
		case 3:
			Connection c2 = MySqlConexao.ConectarDb();

			PreparedStatement parametros2;

			String sqlAtualizarPedido2 = "UPDATE tblPedido SET codStatus = ? , codVeiculoTransp = null WHERE codPedido = ? ;";

			try {
				parametros2 = c2.prepareStatement(sqlAtualizarPedido2);

				parametros2.setInt(1, codStat);
				parametros2.setInt(2, codPed);

				parametros2.executeUpdate();

				c2.close();

				retorno = true;

			} catch (Exception e) {	
				e.printStackTrace();
				retorno = false;	
			}
			break;
			
		case 4:
			Connection c3 = MySqlConexao.ConectarDb();

			PreparedStatement parametros3;

			String sqlAtualizarPedido3 = "UPDATE tblPedido SET codStatus = ? , codVeiculoTransp = ? WHERE codPedido = ? ;";

			try {
				parametros3 = c3.prepareStatement(sqlAtualizarPedido3);

				parametros3.setInt(1, codStat);
				parametros3.setInt(2, codVeic);
				parametros3.setInt(3, codPed);

				parametros3.executeUpdate();

				c3.close();

				retorno = true;

			} catch (Exception e) {	
				e.printStackTrace();
				retorno = false;	
			}
			break;
			
		case 5:

			Connection c4 = MySqlConexao.ConectarDb();

			PreparedStatement parametros4;

			String sqlAtualizarPedido4 = "UPDATE tblPedido SET codStatus = ? WHERE codPedido = ? ;";

			try {
				parametros4 = c4.prepareStatement(sqlAtualizarPedido4);

				parametros4.setInt(1, codStat);
				parametros4.setInt(2, codPed);

				parametros4.executeUpdate();

				c4.close();

				retorno = true;

			} catch (Exception e) {	
				e.printStackTrace();
				retorno = false;	
			}
			break;	
			
		case 6:

			Connection c5 = MySqlConexao.ConectarDb();

			PreparedStatement parametros5;

			String sqlAtualizarPedido5 = "UPDATE tblPedido SET codStatus = ? WHERE codPedido = ? ;";

			try {
				parametros5 = c5.prepareStatement(sqlAtualizarPedido5);

				parametros5.setInt(1, codStat);
				parametros5.setInt(2, codPed);

				parametros5.executeUpdate();

				c5.close();

				retorno = true;

			} catch (Exception e) {	
				e.printStackTrace();
				retorno = false;	
			}
			
		default:
			break;
		}
		return retorno;
	}
}
