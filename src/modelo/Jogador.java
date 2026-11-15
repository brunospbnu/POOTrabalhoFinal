package modelo;

public class Jogador {
	
	private String nomeJgador;
	private int pontuacao;
	
	public Jogador(String nomeJogador, int pontuacao) {
		setNomeJgador(nomeJogador);
		setPontuacao(pontuacao);
	}

	public String getNomeJgador() {
		return nomeJgador;
	}

	public void setNomeJgador(String nomeJgador) {
		this.nomeJgador = nomeJgador;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	public void addPontuacao(int pontos) {
		
		pontuacao += pontos;
	}
	
}
