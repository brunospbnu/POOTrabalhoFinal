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

	public Entidade(String nome, int danoBase, int vidaMaxima, int vidaAtual) throws NomeInvalidoException, ValorMenorQue1, ValorMenorQue0, ObjetoNulo {
		setNome(nome);
		setDanoBase(danoBase);
		setVidaMaxima(vidaMaxima);
		setVidaAtual(vidaAtual);

	}

	// --------------------------------------
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws NomeInvalidoException {
		if (nome == null || nome.isBlank() || nome.length() < 3) {
			throw new NomeInvalidoException("Nome do(a) Entidade" );
		}
		this.nome = nome;
	}

	public int getDanoBase() {
		return danoBase;
	}

	public void setDanoBase(int danoBase) throws ValorMenorQue1 {
		if (danoBase < 1) {
			
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
		if (vidaAtual < 0 ) {
			throw new ValorMenorQue0("Vida Atual");
		}
		this.vidaAtual = vidaAtual;
	}
	// -----------------------------------------------

	public void receberDano(int valor) throws ValorMenorQue1  {
		if (valor < 1) {
			throw new ValorMenorQue1("Dano Recebido");
		}
		vidaAtual -= valor;
		
	}

	public void receberCura(int valor) throws ValorMenorQue1  {
		if (valor < 1) {
			throw new ValorMenorQue1("Cura Recebida");
		}
		vidaAtual += valor;
		
	}

	public void atacar(Entidade alvo) throws ObjetoNulo {
		if (alvo == null) {
			throw new ObjetoNulo("Alvo"); 
		}
		alvo.receberDano(danoBase);
	}

	public void defender() {
		// TODO fazer metodo.
	}

}