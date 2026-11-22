package controle;

import modelo.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ranking {

	private List<Jogador> jogadores;
	private String caminhoArquivo;

	public Ranking() {
		jogadores = new ArrayList<Jogador>();
		carregarDoArquivo();
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
		if (jogadorInformado != null) {
			jogadores.add(jogadorInformado);
			ordenarRanking();
		}

	}

	// aqui foi mais difícil do que eu pensei que seria, mas consiste em utilizar um
	// método bolha para ordenar os jogadores por pontuação
	public void ordenarRanking() {

		int n = jogadores.size();

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {

				Jogador atual = jogadores.get(j);
				Jogador proximo = jogadores.get(j + 1);

				if (atual.getPontuacao() < proximo.getPontuacao()) {
					jogadores.set(j, proximo);
					jogadores.set(j + 1, atual);
				}
			}
		}

	}

	public void salvarNoArquivo() throws IOException {
	    
		/*
		TODO
		Ainda em desenvolvimento.
		 
		Path arquivoDestino = Paths.get(caminhoArquivo, "ranking.txt");
	    File arquivoRanking = arquivoDestino.toFile();

	    try (FileOutputStream fos = new FileOutputStream(arquivoRanking);
	         PrintWriter arquivoTexto = new PrintWriter(new OutputStreamWriter(fos))) {

	        for (Jogador jogador : jogadores) {
	            arquivoTexto.println("Jogador: " + jogador.getNomeJogador() + " / Pontuação: " + jogador.getPontuacao());
	        }
	    }
	    */
	}

	public void carregarDoArquivo() {

		// TODO fazer método.

	}

}
