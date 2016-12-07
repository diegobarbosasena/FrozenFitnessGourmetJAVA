package br.com.model;

public class UsuarioFuncionarioLoja {

	private int codFuncionarioLoja;
	private int codUsuario;
	private int codUsuarioFuncionarioLoja;

	private FuncionarioLoja funcionarioLoja;
	private Usuario usuario;

	public int getCodFuncionarioLoja() {
		return codFuncionarioLoja;
	}
	public void setCodFuncionarioLoja(int codFuncionarioLoja) {
		this.codFuncionarioLoja = codFuncionarioLoja;
	}
	public int getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	public int getCodUsuarioFuncionarioLoja() {
		return codUsuarioFuncionarioLoja;
	}
	public void setCodUsuarioFuncionarioLoja(int codUsuarioFuncionarioLoja) {
		this.codUsuarioFuncionarioLoja = codUsuarioFuncionarioLoja;
	}
	public FuncionarioLoja getFuncionarioLoja() {
		return funcionarioLoja;
	}
	public void setFuncionarioLoja(FuncionarioLoja funcionarioLoja) {
		this.funcionarioLoja = funcionarioLoja;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


}
