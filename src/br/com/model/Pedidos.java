package br.com.model;

import java.util.Date;

public class Pedidos {

	private int codPedido;
	private String tipoPedido;
	private Date dtEntrega;
	private Date dtCompra;
	private float total;
	private int codCliente;
	private int codStatus;
	private int codVeiculoTransp;

	private Cliente cliente;
	private Status status;
	private VeiculoTransp veiculoTransp;
	private Transportadora transportadora;
	private TipoVeiculo tipoVeiculo;

	public int getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}
	public String getTipoPedido() {
		return tipoPedido;
	}
	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}
	public Date getDtEntrega() {
		return dtEntrega;
	}
	public void setDtEntrega(Date dtEntrega) {
		this.dtEntrega = dtEntrega;
	}
	public Date getDtCompra() {
		return dtCompra;
	}
	public void setDtCompra(Date dtCompra) {
		this.dtCompra = dtCompra;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public int getCodStatus() {
		return codStatus;
	}
	public void setCodStatus(int codStatus) {
		this.codStatus = codStatus;
	}
	public int getCodVeiculoTransp() {
		return codVeiculoTransp;
	}
	public void setCodVeiculoTransp(int codVeiculoTransp) {
		this.codVeiculoTransp = codVeiculoTransp;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public VeiculoTransp getVeiculoTransp() {
		return veiculoTransp;
	}
	public void setVeiculoTransp(VeiculoTransp veiculoTransp) {
		this.veiculoTransp = veiculoTransp;
	}
	public Transportadora getTransportadora() {
		return transportadora;
	}
	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}
	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	@Override
	public String toString() {
		return String.valueOf(codPedido) ;
	}

}
