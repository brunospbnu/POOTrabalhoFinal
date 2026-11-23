package controle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import excecoes.ObjetoNuloException;
import modelo.Jogador;

public class Ranking {

	private final String assinaturaArquivo = "GUARDIOES_DA_RELIQUIA_RANKING";
	private final int versaoFormato = 1;
	private final String nomeArquivoFinal = "ranking.dat";
	private final String nomeArquivoTemp = "ranking.tmp";

	private List<Jogador> jogadores;
	private String caminhoArquivo;

	public Ranking(String caminhoPasta) {
		this.jogadores = new ArrayList<>();
		setCaminhoArquivo(caminhoPasta);
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Jogador> jogadores) {
		if (jogadores == null) {
			throw new ObjetoNuloException("A lista de jogadores do ranking");
		}
		this.jogadores = jogadores;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		if (caminhoArquivo == null || caminhoArquivo.trim().isEmpty()) {
			throw new IllegalArgumentException("O caminho da pasta do ranking não pode ser vazio.");
		}
		this.caminhoArquivo = caminhoArquivo;
	}

	// METODOS PRINCIPAIS: ---------------------------------------------
	
	
	
	public void addPontuacaoJogador(Jogador jogadorInformado, String resultado) {
		if (jogadorInformado == null) {
			throw new ObjetoNuloException("O jogador");
		}
		if (resultado != null && !resultado.trim().isEmpty()) {
			jogadorInformado.setResultado(resultado);
		}
		jogadores.add(jogadorInformado);
		ordenarRanking();
	}

	/**
	 * Ordena o ranking usando Bubble Sort:
	 * 1º Maior pontuação
	 * 2º Desempate: Vitória antes de Derrota
	 * 3º Desempate: Ordem alfabética pelo nome do jogador
	 */
	
	public void ordenarRanking() {
		int n = jogadores.size();

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				Jogador atual = jogadores.get(j);
				Jogador proximo = jogadores.get(j + 1);

				if (deveTrocar(atual, proximo)) {
					jogadores.set(j, proximo);
					jogadores.set(j + 1, atual);
				}
			}
		}
	}

	private boolean deveTrocar(Jogador atual, Jogador proximo) {
		// 1º critério: pontuação decrescente
		
		/*
		Se atual tem menos pontos que proximo (ex.: atual = 300, proximo = 500), 
		a expressão 300 < 500 resulta em true — ou seja, 
		deve trocar os dois de posição para que o 500 vá para frente.

		Se atual tem mais pontos que proximo (ex.: atual = 500, proximo = 300), 
		a expressão 500 < 300 resulta em false — ou seja, já está na ordem certa e não troca.
		
		 */
		if (atual.getPontuacao() != proximo.getPontuacao()) {
			return atual.getPontuacao() < proximo.getPontuacao();
		}

		// 2º critério: Vitória antes de Derrota
		String resAtual;
		if (atual.getResultado() != null) {
			resAtual = atual.getResultado();
		} else {
			resAtual = "";
		}

		String resProximo;
		if (proximo.getResultado() != null) {
			resProximo = proximo.getResultado();
		} else {
			resProximo = "";
		}

		boolean atualVenceu = resAtual.equalsIgnoreCase("Vitória");
		boolean proximoVenceu = resProximo.equalsIgnoreCase("Vitória");
		
		if (atualVenceu != proximoVenceu) {
			return !atualVenceu && proximoVenceu;
		}

		// 3º critério: ordem alfabética crescente pelo nome
		return atual.getNomeJogador().compareToIgnoreCase(proximo.getNomeJogador()) > 0;
	}

	
	public void salvarNoArquivo() throws IOException {
		Path pasta = Paths.get(caminhoArquivo);
		if (!Files.exists(pasta) || !Files.isDirectory(pasta)) {
			throw new IllegalArgumentException("Deve ser selecionada uma pasta válida para salvar o ranking.");
		}

		Path arquivoTemp = pasta.resolve(nomeArquivoTemp);
		Path arquivoFinal = pasta.resolve(nomeArquivoFinal);

		try {
			try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(arquivoTemp))) {
				oos.writeUTF(assinaturaArquivo);
				oos.writeInt(versaoFormato);
				oos.writeObject(jogadores);
				oos.flush();
			}

			try {
				Files.move(arquivoTemp, arquivoFinal,
						StandardCopyOption.REPLACE_EXISTING,
						StandardCopyOption.ATOMIC_MOVE);
			} catch (AtomicMoveNotSupportedException e) {
				Files.move(arquivoTemp, arquivoFinal, StandardCopyOption.REPLACE_EXISTING);
			}

		} catch (IOException ex) {
			try {
				Files.deleteIfExists(arquivoTemp);
			} catch (IOException ignored) {
			}
			throw new IOException("Falha ao gravar o arquivo de ranking: " + ex.getMessage(), ex);
		}
	}

	public void carregarDoArquivo() throws IOException, ClassNotFoundException {
		Path pasta = Paths.get(caminhoArquivo);
		if (!Files.exists(pasta) || !Files.isDirectory(pasta)) {
			throw new IllegalArgumentException("Deve ser selecionada uma pasta válida para carregar o ranking.");
		}

		Path arquivoRanking = pasta.resolve(nomeArquivoFinal);
		if (!Files.exists(arquivoRanking)) {
			this.jogadores = new ArrayList<>();
			return;
		}

		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(arquivoRanking))) {
			String assinaturaLida = ois.readUTF();
			if (!assinaturaArquivo.equals(assinaturaLida)) {
				throw new IOException("Formato do arquivo de ranking inválido.");
			}

			int versaoLida = ois.readInt();
			if (versaoLida != versaoFormato) {
				throw new IOException("Versão do arquivo de ranking incompatível.");
			}

			Object objetoLido = ois.readObject();
			
			List<?> listaGenerica = (List<?>) objetoLido;
			List<Jogador> listaValidada = new ArrayList<>();

			for (Object item : listaGenerica) {
			    if (!(item instanceof Jogador)) {
			        throw new IOException("O arquivo de ranking contém dados inválidos.");
			    }
			    listaValidada.add((Jogador) item);
			}

			this.jogadores = listaValidada;
			
			ordenarRanking();

		} catch (IOException | ClassNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new IOException("Arquivo de ranking corrompido ou ilegível.", ex);
		}
	}

}