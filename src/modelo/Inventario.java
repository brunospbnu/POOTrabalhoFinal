package modelo;

import excecoes.ValorMenorQue0Exception;

public class Inventario {
	
	private int qtdPocoes;
	
	public Inventario(int qtdPocoes) throws ValorMenorQue0Exception {
		setQtdPocoes(qtdPocoes);
	}

	public int getQtdPocoes() {
		return qtdPocoes;
	}

	public void setQtdPocoes(int qtdPocoes) throws ValorMenorQue0Exception {
		if (qtdPocoes <0) {
			throw new ValorMenorQue0Exception("A quantidade de poções");
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
