package frotaMotorizada;

public class Carro extends Veículo {
	
	private static final long serialVersionUID = 1L;
	
	public String cap() {
		return "Motor de: " + this.capacidade + " CV";
	}
	
	public Carro(String marca, String modelo, int ano, int km, String placa, double capacidade) {
		super(marca, modelo, ano, km, placa, capacidade);
		this.tipo = "Carro";
	}
	
}