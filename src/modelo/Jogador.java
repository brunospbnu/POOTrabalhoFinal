package modelo;

import excecoes.NomeInvalidoException;
import excecoes.ValorMenorQue0;

public class Jogador {
	
	private String nomeJgador;
	private int pontuacao;
	
	public Jogador(String nomeJogador, int pontuacao) throws NomeInvalidoException, ValorMenorQue0 {
		setNomeJgador(nomeJogador);
		setPontuacao(pontuacao);
	}

	public String getNomeJgador() {
		return nomeJgador;
	}

	public void setNomeJgador(String nomeJgador) throws NomeInvalidoException {
		if (nomeJgador.length() <3) {
			throw new NomeInvalidoException("O nome do Jogador");
		}
		this.nomeJgador = nomeJgador;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) throws ValorMenorQue0 {
		if (pontuacao < 0) {
			throw new ValorMenorQue0("A pontuação a ser definida");
		}
		this.pontuacao = pontuacao;
	}
	
	public void addPontuacao(int pontos) throws ValorMenorQue0 {
		if (pontos < 0) {
			throw new ValorMenorQue0("A pontuação a ser adicionada");
		}
		pontuacao += pontos;
	}
	
}
