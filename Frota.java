/*GRUPO 51 
 REMI RODRIGUES NETO
 GUSTAVO GIACOMOSSI MASS
 TÉCNOLOGO EM ANÁLISE E DESENVOLVIMENTO DE SISTEMAS*/

package frotaMotorizada;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Frota {
	
	private ArrayList<Veículo> veiculos;
	
	public Frota() {
		this.veiculos = new ArrayList<Veículo>();
	}
	
	public String[] leValores (String [] dadosIn) {
		String[] dadosOut = new String [dadosIn.length];
		
		for(int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog("Entre com " + dadosIn[i] + ": ");
		
		return dadosOut;
	}

	public Carro leCarro () {
		String [] valores = new String[5];
		String [] nomeVal = {"Marca", "Modelo", "Ano", "Quilometragem", "Placa", "Potência"};
		valores = leValores (nomeVal);
		
		int ano = this.retornaInteiro(valores[2]);
		int km = this.retornaInteiro(valores[3]);
		double capacidade = this.retornaDouble(valores[5]);
		
		Carro carro = new Carro (valores[0], valores[1], ano, km, valores[4], capacidade);
		return carro;
	}
	
	public Caminhão leCaminhão () {
		String [] valores = new String[5];
		String [] nomeVal = {"Marca", "Modelo", "Ano", "Quilometragem", "Placa", "Capacidade"};
		valores = leValores (nomeVal);
	
		int ano = this.retornaInteiro(valores[2]);
		int km = this.retornaInteiro(valores[3]);
		double capacidade = this.retornaDouble(valores[5]);
		
		Caminhão caminhão = new Caminhão (valores[0], valores[1], ano, km, valores[4], capacidade);
		return caminhão;
	}
	
	public Ônibus leÔnibus () {
		String [] valores = new String[5];
		String [] nomeVal = {"Marca", "Modelo", "Ano", "Quilometragem", "Placa", "Capacidade"};
		valores = leValores (nomeVal);
		
		int ano = this.retornaInteiro(valores[2]);
		int km = this.retornaInteiro(valores[3]);
		double capacidade = this.retornaDouble(valores[5]);
		
		Ônibus ônibus = new Ônibus (valores[0], valores[1], ano, km, valores[4], capacidade);
		return ônibus;
	}
	
	private boolean intValido(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	private boolean doubleValido(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public double retornaDouble(String entrada) {
		while(!this.doubleValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número válido.");
		}
		return Double.parseDouble(entrada);
	}
	
	public int retornaInteiro(String entrada) {
		while(!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}
	
	public void salvaVeículos (ArrayList<Veículo> veículos) {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("C:\\temp\\frota.dados"));
			for (int i=0; i< veículos.size(); i++)
				outputStream.writeObject(veículos.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Veículo> recuperaVeículos (){
		ArrayList<Veículo> veículosTemp = new ArrayList<Veículo>();
		
		ObjectInputStream inputStream = null;
		
		try {
			inputStream = new ObjectInputStream (new FileInputStream("C:\\temp\\frota.dados"));
			Object obj = null;
			while((obj =inputStream.readObject()) != null) {
				if(obj instanceof Veículo) {
					veículosTemp.add((Veículo) obj);
				}
			}
		} catch (EOFException ex) {
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex ) {
			JOptionPane.showMessageDialog(null, "Arquivo com Veículos não existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return veículosTemp;
		}
	}
	
	public void menuFrota () {
		String menu = "";
		String entrada;
		int opc1, opc2;
		
		do {
			menu = "Controle Frota Motorizada\n" + "Opções:\n" +
					"1.Adicionar Veículos\n" + "2.Mostrar Frota\n" +
					"3.Limpar Frota\n" + "4.Salvar Frota\n" +
					"5.Recuperar Frota\n" + "9.Sair";
			entrada = JOptionPane.showInputDialog(menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);
			
			switch(opc1) {
			case 1: menu = "Entrada de Veículos\n" +
					"Opções:\n" + "1.Carro\n" + "2.Caminhão\n" +
					"3.Ônibus\n";
			
				entrada = JOptionPane.showInputDialog(menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);
			
				switch(opc2) {
				case 1: veiculos.add((Veículo) leCarro());
				break;
				case 2: veiculos.add((Veículo) leCaminhão());
				break;
				case 3: veiculos.add((Veículo) leÔnibus());
				break;
				default: JOptionPane.showMessageDialog(null, "Veículo não selecionado");
			}
			
				break;
			case 2: if(veiculos.size() == 0) {
						JOptionPane.showMessageDialog(null, "Adicione veículos à frota primeiro");
						break;
			}
				String dados = "";
				for(int i=0; i< veiculos.size(); i++) {
					dados += veiculos.get(i).toString() + "------------\n";
				}
				JOptionPane.showMessageDialog(null, dados);
				break;
			case 3: if(veiculos.size() == 0) {
						JOptionPane.showMessageDialog(null, "Adicione veículos à frota primeiro");
						break;
			}
				veiculos.clear();
				JOptionPane.showMessageDialog(null, "Dados Limpos com sucesso");
				break;
			case 4: if (veiculos.size() == 0) {
						JOptionPane.showMessageDialog(null, "Adicone veículos à frota primeiro");
						break;
			}
				salvaVeículos(veiculos);
				JOptionPane.showMessageDialog(null, "Frota salva com sucesso");
				break;
			case 5: veiculos = recuperaVeículos();
				if(veiculos.size() == 0) {
					JOptionPane.showMessageDialog(null, "Sem dados para apresentar");
					break;
				}
				JOptionPane.showMessageDialog(null, "Dados recuperados com sucesso");
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Fim do aplicativo da Frota Motorizada");
				break;
			}
		}while (opc1 != 9);
	}
	
	public static void main(String[] args) {
		
		Frota frotas = new Frota();
		frotas.menuFrota();

	}

}
