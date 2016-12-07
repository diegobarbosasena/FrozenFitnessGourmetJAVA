package br.com.model;

public class Cidade {

	private int codCidade;
	private String NomeCidade;
	private int codEstado;

	private Estado estado;

	public int getCodCidade() {
		return codCidade;
	}
	public void setCodCidade(int codCidade) {
		this.codCidade = codCidade;
	}
	public String getNomeCidade() {
		return NomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		NomeCidade = nomeCidade;
	}
	public int getCodEstado() {
		return codEstado;
	}
	public void setCodEstado(int codEstado) {
		this.codEstado = codEstado;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return NomeCidade;
	}

}
