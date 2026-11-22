package modelo;

import excecoes.ObjetoNulo;
import excecoes.ValorMenorQue0;
import excecoes.ValorMenorQue1;

public abstract class Personagem extends Entidade {

	private int recursoAtual;
	private int recursoMaximo;
	private Inventario inventario;

	public Personagem(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoAtual, int recursoMaximo,
			boolean estaDefendendo, Inventario inventario) throws ValorMenorQue0, ValorMenorQue1, ObjetoNulo {

		super(nome, danoBase, vidaMaxima, vidaAtual, estaDefendendo);
		setRecursoAtual(recursoAtual);
		setRecursoMaximo(recursoMaximo);
		setInventario(inventario);
	}

	// ----------------------------------------

	public int getRecursoAtual() {
		return recursoAtual;
	}

	public void setRecursoAtual(int recursoAtual) throws ValorMenorQue0 {
		if (recursoAtual < 0) {
			throw new ValorMenorQue0("Recurso Atual");
		}
		this.recursoAtual = recursoAtual;
	}

	public int getRecursoMaximo() {
		return recursoMaximo;
	}

	public void setRecursoMaximo(int recursoMaximo) throws ValorMenorQue1 {
		if (recursoMaximo < 1) {
			throw new ValorMenorQue1("Recurso Maximo");
		}
		this.recursoMaximo = recursoMaximo;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) throws ObjetoNulo {
		if (inventario == null) {
			throw new ObjetoNulo("O inventário");
		}
		this.inventario = inventario;
	}

	// --------------------------------------

	public int abrirInventario() {
		return this.inventario.getQtdPocoes();
	}

	public String tomarPocao() {
		boolean pocaoConsumida = this.inventario.consumirPocao();
		if (pocaoConsumida) {
			this.receberCura(20);
			return this.getNome() + " tomou uma poção !!!";
		}
		else {
			return this.getNome() + " não conseguiu tomar a poção !!!";
		}
		
	}
	
	public abstract String usarHabilidade(Inimigo alvo);
	
	
	public abstract String getCaminhoImagem();
	
	
	public abstract String getNomeRecurso();

}
