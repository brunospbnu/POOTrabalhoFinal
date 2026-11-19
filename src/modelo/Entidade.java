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
	
	// Atributo que vai controlar o estado de defesa:
	private boolean estaDefendendo;

	public Entidade(String nome, int danoBase, int vidaMaxima, int vidaAtual, boolean estaDefendendo)
			throws NomeInvalidoException, ValorMenorQue1, ValorMenorQue0, ObjetoNulo {
		setNome(nome);
		setDanoBase(danoBase);
		setVidaMaxima(vidaMaxima);
		setVidaAtual(vidaAtual);
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
			throw new ValorMenorQue1("Dano Base");
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

	public boolean estaDefendendo() {
		return estaDefendendo;
	}

	public void setEstaDefendendo(boolean estaDefendendo) {
		this.estaDefendendo = estaDefendendo;
	}

	// -----------------------------------------------

	// Metodo de receber dano considerando atributo de defesa
	public String receberDano(int valor) throws ValorMenorQue1 { 
		
		if (valor < 1) { // primeiro a validação do valor recebido.
			throw new ValorMenorQue1("Dano Recebido");
		}
		
		// metodo alterado para String para retornar a informação da defesa efetuada e do dano recebido.
		String textoDeRetorno = this.nome + " recebeu uma tentativa de ataque de: " + valor + "unidades de vida";
		
		
		// aplicação da defesa:
		if (this.estaDefendendo && valor >=2) { 
			// validação para que a defesa se aplique somente se for 2 ou mais o dano, 
			// para que ao dividir por 2 o resultado minimo seja 1 inteiro.
			
			valor = valor / 2; // ao defender, o dano do atacante será reduzido pela metade
			
			
			textoDeRetorno = this.nome + " defendeu metade do ataque! \n";

			// isso aqui vai resetar a defesa após absorver o golpe
			this.estaDefendendo = false;
		}

		
		this.vidaAtual -= valor;
		if (this.vidaAtual < 0) {
			this.vidaAtual = 0;
		}
		
		textoDeRetorno += this.nome + "Recebeu " + valor + " de dano efetivo !!!";
		
		return textoDeRetorno;

	}

	public String receberCura(int valor) throws ValorMenorQue1 {
		if (valor < 1) {
			throw new ValorMenorQue1("Cura Recebida");
		}
		
		vidaAtual += valor;
		String textoDeRetorno = this.nome + "recebeu cura de " + valor + " unidades de vida !!! ";
		return textoDeRetorno;
	}

	public String atacar(Entidade alvo) throws ObjetoNulo, ValorMenorQue1 {
		if (alvo == null) {
			throw new ObjetoNulo("Alvo");
		}
		alvo.receberDano(danoBase);
		String textoDeRetorno = this.nome + " efetuou uma tentativa de ataque de " + danoBase + " unidades de vida !!!";
		return textoDeRetorno;
	}

	// Aqui o metodo de defesa muda o atribuo para true.
	public String defender() {
		this.estaDefendendo = true;
		return this.nome + " ativou a defesa !!!";
	}

}