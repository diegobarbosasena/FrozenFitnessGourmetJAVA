package br.com.model;

public class Status {

	private int codStatus;
	private String statusPedido;

	public int getCodStatus() {
		return codStatus;
	}
	public void setCodStatus(int codStatus) {
		this.codStatus = codStatus;
	}
	public String getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}

	@Override
	public String toString() {
		return statusPedido;
	}

}
