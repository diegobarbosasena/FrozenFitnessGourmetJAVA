package br.com.model;

public class ClienteEndereco {

	private int codClienteEnd;
	private int codEndereco;
	private int codCliente;

	private Endereco endereco;
	private Cliente cliente;

	public int getCodClienteEnd() {
		return codClienteEnd;
	}
	public void setCodClienteEnd(int codClienteEnd) {
		this.codClienteEnd = codClienteEnd;
	}
	public int getCodEndereco() {
		return codEndereco;
	}
	public void setCodEndereco(int codEndereco) {
		this.codEndereco = codEndereco;
	}
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


}
