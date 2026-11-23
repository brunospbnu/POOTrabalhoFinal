package modelo;

import java.io.Serializable;

import excecoes.NomeInvalidoException;
import excecoes.ValorMenorQue0Exception;

public class Jogador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomeJogador;
	private int pontuacao;
	private String classePersonagem;
	private String resultado;
	

	public Jogador(String nomeJogador, int pontuacao) throws NomeInvalidoException, ValorMenorQue0Exception, IllegalArgumentException {
		setResultado("Em andamento");
		setNomeJogador(nomeJogador);
		setPontuacao(pontuacao);
	}

	
	// GETS e SETS ---------------------------------------
	
	public String getNomeJogador() {
		return nomeJogador;
	}

	public void setNomeJogador(String nomeJgador) throws NomeInvalidoException {
		if (nomeJgador == null || nomeJgador.length() < 3) {
			throw new NomeInvalidoException("O nome do Jogador");
		}
		this.nomeJogador = nomeJgador;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) throws ValorMenorQue0Exception {
		if (pontuacao < 0) {
			throw new ValorMenorQue0Exception("A pontuação a ser definida");
		}
		this.pontuacao = pontuacao;
	}

	
	public String getClassePersonagem() {
		return classePersonagem;
	}

	public void setClassePersonagem(String classePersonagem) throws IllegalArgumentException {
		if (classePersonagem == null || classePersonagem.trim().isEmpty()) {
			throw new IllegalArgumentException("A classe do personagem não pode ser vazia.");
		}
		this.classePersonagem = classePersonagem;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		if (resultado == null || resultado.trim().isEmpty()) {
			throw new IllegalArgumentException("O resultado não pode ser vazio.");
		}
		this.resultado = resultado;
	}
	
	
	// METODO DE ADIÇÃO: ---------------------
	
	public void addPontuacao(int pontos) throws ValorMenorQue0Exception {
		if (pontos < 0) {
			throw new ValorMenorQue0Exception("A pontuação a ser adicionada");
		}
		pontuacao += pontos;
	}

	

}
