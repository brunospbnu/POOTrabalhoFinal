package modelo;

import excecoes.ObjetoNulo;
import excecoes.ValorMenorQue0;
import excecoes.ValorMenorQue1;

public abstract class Personagem extends Entidade {

	private int recursoAtual;
	private int recursoMaximo;
	private Inventario inventario;

	public Personagem(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoAtual, int recursoMaximo,
			Inventario mochila) throws ValorMenorQue0, ValorMenorQue1, ObjetoNulo {
		
		super(nome, danoBase, vidaMaxima, vidaAtual);
		setRecursoAtual(recursoAtual);
		setRecursoMaximo(recursoMaximo);
		setMochila(mochila);
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

	public void setRecursoMaximo(int recursoMaximo)  throws ValorMenorQue1 {
		if (recursoMaximo < 1) {
			throw new ValorMenorQue1("Recurso Maximo");
		}
		this.recursoMaximo = recursoMaximo;
	}

	public Inventario getMochila() {
		return inventario;
	}

	public void setMochila(Inventario inventario) throws ObjetoNulo {
		if (inventario == null) {
			throw new ObjetoNulo("Mochila");
		}
		this.inventario = inventario;
	}

	// --------------------------------------

	public void abrirInventario() {
		// TODO fazer metodo.
	}

	public String tomarPocao() {
		// TODO fazer metodo.
		return "tomou poção"; // escrevi qualquer coisa
	}

	public abstract String usarHabilidade();

}
