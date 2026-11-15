package modelo;

public class Inventario {
	
	private int qtdPocoes;
	
	public Inventario() {
		
	}

	public int getQtdPocoes() {
		return qtdPocoes;
	}

	public void setQtdPocoes(int qtdPocoes) {
		this.qtdPocoes = qtdPocoes;
	}
	
	public void addPocao() {
		qtdPocoes += 1;
	}
	
	public boolean consumirPocao() {
		boolean pocaoConsumida = false;
		
		// TODO fazer isso.
		
		return pocaoConsumida;
	}
	
	
}
