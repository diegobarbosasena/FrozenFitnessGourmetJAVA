package br.com.model;

public class ClienteJuridicoEndereco {

	private int codClienteJuridicoEnd;
	private int codEndereco;
	private int codClienteJuridico;

	private Endereco endereco;
	private ClienteJuridico cliente_juridico;

	public int getCodClienteJuridicoEnd() {
		return codClienteJuridicoEnd;
	}
	public void setCodClienteJuridicoEnd(int codClienteJuridicoEnd) {
		this.codClienteJuridicoEnd = codClienteJuridicoEnd;
	}
	public int getCodEndereco() {
		return codEndereco;
	}
	public void setCodEndereco(int codEndereco) {
		this.codEndereco = codEndereco;
	}
	public int getCodClienteJuridico() {
		return codClienteJuridico;
	}
	public void setCodClienteJuridico(int codClienteJuridico) {
		this.codClienteJuridico = codClienteJuridico;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public ClienteJuridico getCliente_juridico() {
		return cliente_juridico;
	}
	public void setCliente_juridico(ClienteJuridico cliente_juridico) {
		this.cliente_juridico = cliente_juridico;
	}



}
