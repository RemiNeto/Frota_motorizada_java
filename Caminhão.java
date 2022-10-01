package frotaMotorizada;

public class Caminhão extends Veículo {
	
	private static final long serialVersionUID = 1L;
	
	int ton = (int) Math.round(capacidade);
	public String cap() {
		return "Carga máxima: " + this.ton + " Toneladas" ; 
	}

	
	public Caminhão (String marca, String modelo, int ano, int km, String placa, double capacidade) {
		super(marca, modelo, ano, km, placa, capacidade);
		this.tipo = "Caminhão";
	}

}