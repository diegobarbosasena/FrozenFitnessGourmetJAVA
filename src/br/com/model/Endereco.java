package br.com.model;

import java.util.List;

public class Endereco {
	
	private int codEndereco;
	private String logradouro;
	private String cep;
	private String numero;
	private String bairro;
	private String complemento;
	
	public List<Cidade> lstCidade;
	
	
	public int getCodEndereco() {
		return codEndereco;
	}
	public void setCodEndereco(int codEndereco) {
		this.codEndereco = codEndereco;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	@Override
	public String toString() {
		return "Endereco [codEndereco=" + codEndereco + ", logradouro=" + logradouro + ", cep=" + cep + ", numero="
				+ numero + ", bairro=" + bairro + ", complemento=" + complemento + "]";
	}
	

}
