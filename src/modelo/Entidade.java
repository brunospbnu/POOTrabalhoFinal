package modelo;

import excecoes.NomeInvalidoException;
import excecoes.ObjetoNulo;
import excecoes.ValorMenorQue0;
import excecoes.ValorMenorQue1;

public abstract class Entidade {

	private String nome;
	private int danoBase;
	private int vidaMaxima;
	private int vidaAtual;
	// comentei em numeração todas as mudanças feitas
	// 1- novo atributo que vai controlar o estado de defesa
	private boolean estaDefendendo;

	public Entidade(String nome, int danoBase, int vidaMaxima, int vidaAtual, boolean estaDefendendo)
			throws NomeInvalidoException, ValorMenorQue1, ValorMenorQue0, ObjetoNulo {
		setNome(nome);
		setDanoBase(danoBase);
		setVidaMaxima(vidaMaxima);
		setVidaAtual(vidaAtual);
		// 2- método set
		setEstaDefendendo(estaDefendendo);

	}

	// --------------------------------------

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws NomeInvalidoException {
		if (nome == null || nome.isBlank() || nome.length() < 3) {
			throw new NomeInvalidoException("Nome do(a) Entidade");
		}
		this.nome = nome;
	}

	public int getDanoBase() {
		return danoBase;
	}

	public void setDanoBase(int danoBase) throws ValorMenorQue1 {
		if (danoBase < 1) {
			throw new ValorMenorQue1("Dano Base"); // vi que estava faltando o lançamento dessa exceção
		}
		this.danoBase = danoBase;
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public void setVidaMaxima(int vidaMaxima) throws ValorMenorQue1 {
		if (vidaMaxima < 1) {
			throw new ValorMenorQue1("Vida Maxima");
		}
		this.vidaMaxima = vidaMaxima;
	}

	public int getVidaAtual() {
		return vidaAtual;
	}

	public void setVidaAtual(int vidaAtual) throws ValorMenorQue0 {
		if (vidaAtual < 0) {
			throw new ValorMenorQue0("Vida Atual");
		}
		this.vidaAtual = vidaAtual;
	}

	// 3- método get e set do novo atributo que comentei lá em cima
	public boolean isEstaDefendendo() {
		return estaDefendendo;
	}

	public void setEstaDefendendo(boolean estaDefendendo) {
		this.estaDefendendo = estaDefendendo;
	}

	// -----------------------------------------------

	// 5- método alterado com a adição do novo atributo
	public void receberDano(int valor) throws ValorMenorQue1 {

		if (this.estaDefendendo) {
			valor = valor / 2; // ao defender, o dano do atacante será reduzido pela metade
			System.out.println(this.nome + " defendeu parte do ataque!");

			// isso aqui vai resetar a defesa após absorver o golpe
			this.estaDefendendo = false;
		}

		if (valor < 0) {
			throw new ValorMenorQue0("Dano Recebido");
		}
		this.vidaAtual -= valor;
		if (this.vidaAtual < 0) {
			this.vidaAtual = 0;
		}

	}

	public void receberCura(int valor) throws ValorMenorQue1 {
		if (valor < 1) {
			throw new ValorMenorQue1("Cura Recebida");
		}
		vidaAtual += valor;

	}

	public void atacar(Entidade alvo) throws ObjetoNulo, ValorMenorQue1 {
		if (alvo == null) {
			throw new ObjetoNulo("Alvo");
		}
		alvo.receberDano(danoBase);
	}

	// 4- aqui precisa retornar true
	public void defender() {
		this.estaDefendendo = true;
	}

}