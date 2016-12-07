package br.com.model;

import java.util.Date;

public class Estoque {

	private int codEstoque;
	private Date dtValidade;
	private int qtd;
	private int qtdMin;

	public int getCodEstoque() {
		return codEstoque;
	}
	public void setCodEstoque(int codEstoque) {
		this.codEstoque = codEstoque;
	}
	public Date getDtValidade() {
		return dtValidade;
	}
	public void setDtValidade(Date dtValidade) {
		this.dtValidade = dtValidade;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public int getQtdMin() {
		return qtdMin;
	}
	public void setQtdMin(int qtdMin) {
		this.qtdMin = qtdMin;
	}

}
