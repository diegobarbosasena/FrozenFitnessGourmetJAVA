package br.com.model;

public class Usuario {

	private int codUsuario;	
	private String nomeUsuario;		
	private String senha;		
	private int codTipoUsuario;

	private TipoUsuario tipousuario;

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getCodTipoUsuario() {
		return codTipoUsuario;
	}
	public void setCodTipoUsuario(int codTipoUsuario) {
		this.codTipoUsuario = codTipoUsuario;
	}
	public TipoUsuario getTipousuario() {
		return tipousuario;
	}
	public void setTipousuario(TipoUsuario tipousuario) {
		this.tipousuario = tipousuario;
	}
	public int getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}
