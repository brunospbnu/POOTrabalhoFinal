package modelo;

import excecoes.ValorMenorQue0;

public class Inventario {
	
	private int qtdPocoes;
	
	public Inventario(int qtdPocoes) throws ValorMenorQue0 {
		setQtdPocoes(qtdPocoes);
	}

	public int getQtdPocoes() {
		return qtdPocoes;
	}

	public void setQtdPocoes(int qtdPocoes) throws ValorMenorQue0 {
		if (qtdPocoes <0) {
			throw new ValorMenorQue0("A quantidade de poções");
		}
		this.qtdPocoes = qtdPocoes;
	}
	
	public void addPocao() {
		qtdPocoes += 1;
	}
	
	public boolean consumirPocao() {
		boolean pocaoConsumida = false;
		
		if (qtdPocoes > 0) {
			qtdPocoes -= 1;
			pocaoConsumida = true;
		}
		
		return pocaoConsumida;
	}
	
	
}
