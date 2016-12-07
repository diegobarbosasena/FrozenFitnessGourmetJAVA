package br.com.model;

public class VeiculoTransp {

	private int codVeiculoTransp;
	private String placaVeiculo;
	private int codTipoVeiculo;
	private int codTransportadora;

	private TipoVeiculo tipoVeiculo;
	private Transportadora transportadora;

	public int getCodVeiculoTransp() {
		return codVeiculoTransp;
	}
	public void setCodVeiculoTransp(int codVeiculoTransp) {
		this.codVeiculoTransp = codVeiculoTransp;
	}
	public String getPlacaVeiculo() {
		return placaVeiculo;
	}
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}
	public int getCodTipoVeiculo() {
		return codTipoVeiculo;
	}
	public void setCodTipoVeiculo(int codTipoVeiculo) {
		this.codTipoVeiculo = codTipoVeiculo;
	}
	public int getCodTransportadora() {
		return codTransportadora;
	}
	public void setCodTransportadora(int codTransportadora) {
		this.codTransportadora = codTransportadora;
	}
	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	public Transportadora getTransportadora() {
		return transportadora;
	}
	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}

	@Override
	public String toString() {
		return placaVeiculo ;
	}

}
