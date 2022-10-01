package frotaMotorizada;
import java.io.Serializable;

public abstract class Veículo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String marca;
	private String modelo;
	private int ano;
	private int km;
	private String placa;
	protected String tipo;
	protected double capacidade;
	
	public Veículo(String marca, String modelo, int ano, int km, String placa, double capacidade) {
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.km = km;
		this.placa = placa;
		this.capacidade = capacidade;
	}
	
	public String toString() {
		String retorno = "";
		retorno += "Tipo: " + this.tipo + "\n";
		retorno += "Marca: " + this.marca + "\n";
		retorno += "Modelo: " + this.modelo + "\n";
		retorno += "Ano: " + this.ano + "\n";
		retorno += "Quilometragem: " + this.km + "\n";
		retorno += "Placa: " + this.placa + "\n";
		retorno += cap() + "\n";
		return retorno;
	}
	public abstract String cap();
}