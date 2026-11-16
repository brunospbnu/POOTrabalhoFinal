package controle;

import modelo.*;
import java.util.List;

public class Ranking {
	
	private List<Jogador> jogadores;
	private String caminhoArquivo;
	
	public Ranking() {
		
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}
	
	public void addPontuacaoJogador(Jogador jogadorInformado, String resultado) {
		
		//TODO fazer método.
	}
	
	public void ordenarRanking() {
		
		//TODO fazer método.
	}
	
	public void salvarNoArquivo() {
		
		//TODO fazer método.
	}
	
	public void carregarDoArquivo() {
		
		//TODO fazer método.
		
	}
	
}
