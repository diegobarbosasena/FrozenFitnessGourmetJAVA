package br.com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConexao;
import br.com.model.FuncionarioLoja;
import br.com.model.TipoUsuario;
import br.com.model.Usuario;
import br.com.model.UsuarioFuncionarioLoja;

public class UsuarioFuncionarioLojaDAO {

	public static List<UsuarioFuncionarioLoja> filtrarUsuario(){

		Connection c = MySqlConexao.ConectarDb();

		String sqlFiltrarUsuario = "select usuario_funcionario.*, usuario.*, funcionario.*, tipo_usuario.* "
				+ "from tblUsuarioFuncionarioLoja usuario_funcionario "
				+ "inner join tblUsuario usuario "
				+ "on(usuario_funcionario.codUsuario = usuario.codUsuario) "
				+ "inner join tblFuncionarioLoja funcionario "
				+ "on(usuario_funcionario.codFuncionarioLoja = funcionario.codFuncionarioLoja) "
				+ "inner join tblTipoUsuario tipo_usuario "
				+ "on(usuario.codTipoUsuario = tipo_usuario.codTipoUsuario)";

		List<UsuarioFuncionarioLoja> lstUsuario = new ArrayList<>();

		ResultSet rs;
		try {
			rs = c.createStatement().executeQuery(sqlFiltrarUsuario);

			while(rs.next()){

				UsuarioFuncionarioLoja usuarioFuncionario = new UsuarioFuncionarioLoja();
				Usuario usuario = new Usuario();
				FuncionarioLoja funcionario = new FuncionarioLoja();
				TipoUsuario tipoUsuario = new TipoUsuario();

				funcionario.setCodFuncionarioLoja(rs.getInt("codFuncionarioLoja"));
				funcionario.setCpfFuncionarioLoja(rs.getString("cpfFuncionarioLoja"));
				funcionario.setNomeFuncionarioLoja(rs.getString("nomeFuncionarioLoja"));

				usuarioFuncionario.setCodFuncionarioLoja(rs.getInt("codFuncionarioLoja"));
				usuarioFuncionario.setCodUsuario(rs.getInt("codUsuario"));
				usuarioFuncionario.setCodUsuarioFuncionarioLoja(rs.getInt("codUsuarioFuncionarioLoja"));
				usuarioFuncionario.setFuncionarioLoja(funcionario);
				usuarioFuncionario.setUsuario(usuario);

				tipoUsuario.setCodTipoUsuario(rs.getInt("codTipoUsuario"));
				tipoUsuario.setNomeTipoUsuario(rs.getString("nomeTipoUsuario"));

				usuario.setCodTipoUsuario(rs.getInt("codTipoUsuario"));
				usuario.setCodUsuario(rs.getInt("codUsuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setNomeUsuario(rs.getString("usuario"));
				usuario.setTipousuario(tipoUsuario);

				lstUsuario.add(usuarioFuncionario);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return lstUsuario;
	}
}
