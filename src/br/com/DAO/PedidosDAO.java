package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				t.setNomeTransportadora(rs.getString("nomeTransportadora"));
				t.setEmailTransportadora(rs.getString("emailTransportadora"));
				t.setTelefoneTransportadora(rs.getString("telefoneTransportadora"));
				t.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				t.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));
				t.setCodEndereco(rs.getInt("codEndereco"));
				
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
		} catch (SQLException e) {
			e.printStackTrace();
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
				t.setNomeTransportadora(rs.getString("nomeTransportadora"));
				t.setEmailTransportadora(rs.getString("emailTransportadora"));
				t.setTelefoneTransportadora(rs.getString("telefoneTransportadora"));
				t.setCnpjTransportadora(rs.getString("cnpjTransportadora"));
				t.setResponsavelTransportadora(rs.getString("responsavelTransportadora"));
				t.setCodEndereco(rs.getInt("codEndereco"));
				
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
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		return lstPediPesq;
	}
	
	public static boolean updatePedido(Integer codVeic, Integer codPed, Integer codStat){
		
		Connection c = MySqlConexao.ConectarDb();
		
		String sqlAtualizarPedido = "";
		PreparedStatement parametros;
		
		if (codVeic != null){
			sqlAtualizarPedido = "UPDATE tblPedido SET codStatus = ?, codVeiculoTransp = ? WHERE codPedido = ? ;";
			
			try {
				parametros = c.prepareStatement(sqlAtualizarPedido);
				
				parametros.setInt(1, codStat);
				parametros.setInt(2, codVeic);
				parametros.setInt(3, codPed);
				
				parametros.executeUpdate();
			
				c.close();
				
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();	
				return false;	
			}		
		}
		
		else if (codVeic == null) {
			sqlAtualizarPedido = "UPDATE tblPedido SET codStatus = ? , codVeiculoTransp = null WHERE codPedido = ? ;";
			
			try {
				parametros = c.prepareStatement(sqlAtualizarPedido);
				
				parametros.setInt(1, codStat);
				parametros.setInt(2, codPed);
				
				parametros.executeUpdate();
			
				c.close();
				
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();	
				return false;	
			}
		}
		else{
			sqlAtualizarPedido = "UPDATE tblPedido SET codStatus = ? WHERE codPedido = ? ;";
			

			try {
				parametros = c.prepareStatement(sqlAtualizarPedido);
				
				parametros.setInt(1, codStat);
				parametros.setInt(2, codPed);
				
				parametros.executeUpdate();
			
				c.close();
				
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();	
				return false;	
			}
		}
	}
}
