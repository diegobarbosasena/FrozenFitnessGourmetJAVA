package br.com.model;

public class TipoVeiculo {

	private int codTipoVeiculo;
	private String nomeTipoVeiculo;

	public int getCodTipoVeiculo() {
		return codTipoVeiculo;
	}
	public void setCodTipoVeiculo(int codTipoVeiculo) {
		this.codTipoVeiculo = codTipoVeiculo;
	}
	public String getNomeTipoVeiculo() {
		return nomeTipoVeiculo;
	}
	public void setNomeTipoVeiculo(String nomeTipoVeiculo) {
		this.nomeTipoVeiculo = nomeTipoVeiculo;
	}

	@Override
	public String toString() {
		return nomeTipoVeiculo ;
	}

}
