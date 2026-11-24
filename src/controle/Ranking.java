package controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
	private final String nomeArquivoFinal = "ranking.txt";
	private final String nomeArquivoTemp = "ranking.tmp";
	private final String separador = ";";

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
			try (BufferedWriter writer = Files.newBufferedWriter(arquivoTemp, StandardCharsets.UTF_8)) {
				// Linha 1: assinatura e versão do formato
				writer.write(assinaturaArquivo + separador + versaoFormato);
				writer.newLine();

				// Linha 2: cabeçalho legível para o Bloco de Notas
				writer.write("NOME;CLASSE;PONTUACAO;RESULTADO");
				writer.newLine();

				// Linhas seguintes: registros dos jogadores
				for (Jogador jogador : jogadores) {
					String nome = limparCampo(jogador.getNomeJogador());
					String classe = limparCampo(jogador.getClassePersonagem());
					int pontuacao = jogador.getPontuacao();
					String resultado = limparCampo(jogador.getResultado());

					writer.write(nome + separador + classe + separador + pontuacao + separador + resultado);
					writer.newLine();
				}
				writer.flush();
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

	public void carregarDoArquivo() throws IOException {
		Path pasta = Paths.get(caminhoArquivo);
		if (!Files.exists(pasta) || !Files.isDirectory(pasta)) {
			throw new IllegalArgumentException("Deve ser selecionada uma pasta válida para carregar o ranking.");
		}

		Path arquivoRanking = pasta.resolve(nomeArquivoFinal);
		if (!Files.exists(arquivoRanking)) {
			this.jogadores = new ArrayList<>();
			return;
		}

		List<Jogador> listaValidada = new ArrayList<>();

		try (BufferedReader reader = Files.newBufferedReader(arquivoRanking, StandardCharsets.UTF_8)) {
			// 1. Valida assinatura e versão na primeira linha
			String primeiraLinha = reader.readLine();
			if (primeiraLinha == null) {
				throw new IOException("O arquivo de ranking está vazio.");
			}

			String[] cabecalhoVersao = primeiraLinha.split(separador);
			if (cabecalhoVersao.length != 2 || !assinaturaArquivo.equals(cabecalhoVersao[0])) {
				throw new IOException("Formato do arquivo de ranking inválido.");
			}

			int versaoLida = Integer.parseInt(cabecalhoVersao[1].trim());
			if (versaoLida != versaoFormato) {
				throw new IOException("Versão do arquivo de ranking incompatível (esperada: "
						+ versaoFormato + ", encontrada: " + versaoLida + ").");
			}

			// 2. Pula a linha de cabeçalho das colunas (NOME;CLASSE;PONTUACAO;RESULTADO)
			reader.readLine();

			// 3. Lê e valida cada linha de jogador
			String linha;
			while ((linha = reader.readLine()) != null) {
				if (linha.trim().isEmpty()) {
					continue;
				}

				String[] partes = linha.split(separador, -1);
				if (partes.length != 4) {
					throw new IOException("Linha com formato inválido no arquivo de ranking: " + linha);
				}

				String nome = partes[0].trim();
				String classe = partes[1].trim();
				int pontuacao = Integer.parseInt(partes[2].trim());
				String resultado = partes[3].trim();

				Jogador jogador = new Jogador(nome, pontuacao);
				jogador.setClassePersonagem(classe);
				jogador.setPontuacao(pontuacao);
				jogador.setResultado(resultado);

				listaValidada.add(jogador);
			}

			this.jogadores = listaValidada;
			ordenarRanking();

		} catch (NumberFormatException ex) {
			throw new IOException("Arquivo de ranking corrompido: valor numérico inválido.", ex);
		} catch (IOException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new IOException("Arquivo de ranking corrompido ou ilegível.", ex);
		}
	}

	private String limparCampo(String texto) {
		if (texto == null) {
			return "";
		}
		return texto.replace(separador, ",").replace("\n", " ").replace("\r", " ").trim();
	}

}