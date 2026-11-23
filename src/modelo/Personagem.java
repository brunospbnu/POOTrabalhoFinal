package modelo;

import excecoes.ObjetoNuloException;
import excecoes.ValorMenorQue0Exception;
import excecoes.ValorMenorQue1Exception;

public abstract class Personagem extends Entidade {

	private int recursoAtual;
	private int recursoMaximo;
	private Inventario inventario;

	public Personagem(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoAtual, int recursoMaximo,
			boolean estaDefendendo, Inventario inventario) throws ValorMenorQue0Exception, ValorMenorQue1Exception, ObjetoNuloException {

		super(nome, danoBase, vidaMaxima, vidaAtual, estaDefendendo);
		setRecursoAtual(recursoAtual);
		setRecursoMaximo(recursoMaximo);
		setInventario(inventario);
	}

	// ----------------------------------------

	public int getRecursoAtual() {
		return recursoAtual;
	}

	public void setRecursoAtual(int recursoAtual) throws ValorMenorQue0Exception {
		if (recursoAtual < 0) {
			throw new ValorMenorQue0Exception("Recurso Atual");
		}
		this.recursoAtual = recursoAtual;
	}

	public int getRecursoMaximo() {
		return recursoMaximo;
	}

	public void setRecursoMaximo(int recursoMaximo) throws ValorMenorQue1Exception {
		if (recursoMaximo < 1) {
			throw new ValorMenorQue1Exception("Recurso Maximo");
		}
		this.recursoMaximo = recursoMaximo;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) throws ObjetoNuloException {
		if (inventario == null) {
			throw new ObjetoNuloException("O inventário");
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
