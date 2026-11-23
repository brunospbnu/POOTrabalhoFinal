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

	public Jogador(String nomeJogador, int pontuacao) throws NomeInvalidoException, ValorMenorQue0Exception {
		setNomeJogador(nomeJogador);
		setPontuacao(pontuacao);
	}

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

	public void addPontuacao(int pontos) throws ValorMenorQue0Exception {
		if (pontos < 0) {
			throw new ValorMenorQue0Exception("A pontuação a ser adicionada");
		}
		pontuacao += pontos;
	}

}
