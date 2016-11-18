package br.com.model;

public class Transportadora {

	private int codTransportadora;
	private String nomeTransportadora;
	private String emailTransportadora;
	private String telefoneTransportadora;
	private String cnpjTransportadora;
	private String responsavelTransportadora;
	private int codEndereco;
	
	private Endereco endereco;
	
	public int getCodTransportadora() {
		return codTransportadora;
	}
	public void setCodTransportadora(int codTransportadora) {
		this.codTransportadora = codTransportadora;
	}
	public String getNomeTransportadora() {
		return nomeTransportadora;
	}
	public void setNomeTransportadora(String nomeTransportadora) {
		this.nomeTransportadora = nomeTransportadora;
	}
	public String getEmailTransportadora() {
		return emailTransportadora;
	}
	public void setEmailTransportadora(String emailTransportadora) {
		this.emailTransportadora = emailTransportadora;
	}
	public String getTelefoneTransportadora() {
		return telefoneTransportadora;
	}
	public void setTelefoneTransportadora(String telefoneTransportadora) {
		this.telefoneTransportadora = telefoneTransportadora;
	}
	public String getCnpjTransportadora() {
		return cnpjTransportadora;
	}
	public void setCnpjTransportadora(String cnpjTransportadora) {
		this.cnpjTransportadora = cnpjTransportadora;
	}
	public String getResponsavelTransportadora() {
		return responsavelTransportadora;
	}
	public void setResponsavelTransportadora(String responsavelTransportadora) {
		this.responsavelTransportadora = responsavelTransportadora;
	}
	public int getCodEndereco() {
		return codEndereco;
	}
	public void setCodEndereco(int codEndereco) {
		this.codEndereco = codEndereco;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return nomeTransportadora;
	}
	
}
