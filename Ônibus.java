package frotaMotorizada;

public class Ônibus extends Veículo {
	
	private static final long serialVersionUID = 1L;
	
	int pas = (int) Math.round(capacidade);
	public String cap() {
		return "Capacidade de: " + this.pas + " Passageiros";
	}

	public Ônibus(String marca, String modelo, int ano, int km, String placa, double capacidade) {
		super(marca, modelo, ano, km, placa, capacidade);
		this.tipo = "Ônibus";
	}

}