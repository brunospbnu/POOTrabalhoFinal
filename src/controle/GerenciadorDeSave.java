package controle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import excecoes.ObjetoNuloException;

public class GerenciadorDeSave {

	private final String assinaturaArquivo = "GUARDIOES_DA_RELIQUIA_SAVE";
	private final int versaoFormato = 1;
	private final String nomeArquivoFinal = "partidaSalva.dat";
	private final String nomeArquivoTemp = "partidaSalva.tmp";

	private String caminhoArquivo;

	// gets e sets ----------------

	public GerenciadorDeSave(String caminhoInformado) {
		setCaminhoArquivo(caminhoInformado);
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		if (caminhoArquivo == null || caminhoArquivo.trim().isEmpty()) {
			throw new IllegalArgumentException("O caminho da pasta não pode ser vazio.");
		}
		this.caminhoArquivo = caminhoArquivo;
	}

	// Métodos principais: --------------------------------------------------

	public Partida carregarPartida() throws IOException, IllegalArgumentException, ClassNotFoundException, FileNotFoundException {

		Path pasta = Paths.get(caminhoArquivo);
		if (!Files.exists(pasta) || !Files.isDirectory(pasta)) {
			throw new IllegalArgumentException("Deve ser selecionada uma pasta válida, não um arquivo.");
		}

		Path arquivoSave = pasta.resolve(nomeArquivoFinal);
		if (!Files.exists(arquivoSave) || !Files.isRegularFile(arquivoSave)) {
			throw new FileNotFoundException("Nenhuma partida salva foi encontrada nesta pasta.");
		}

		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(arquivoSave))) {
			String assinaturaLida = ois.readUTF();
			if (!assinaturaArquivo.equals(assinaturaLida)) {
				throw new IOException("Formato de arquivo inválido.");
			}

			int versaoLida = ois.readInt();
			if (versaoLida != versaoFormato) {
				throw new IOException("Versão do arquivo de salvamento incompatível (esperada: "
						+ versaoFormato + ", encontrada: " + versaoLida + ").");
			}

			Object objetoLido = ois.readObject();
			if (!(objetoLido instanceof Partida)) {
				throw new IOException("O arquivo não contém uma partida válida.");
			}

			Partida partidaCarregada = (Partida) objetoLido;
			validarConsistencia(partidaCarregada);

			return partidaCarregada;

		} catch (IOException | ClassNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new IOException("Arquivo de salvamento corrompido ou ilegível.", ex);
		}
	}

	public void salvarPartida(Partida partidaInformada) throws IllegalArgumentException, IOException, ObjetoNuloException {

		if (partidaInformada == null) {
			throw new ObjetoNuloException("A partida a ser salva");
		}

		if (partidaInformada.getInimigoAtual() != null && partidaInformada.getInimigoAtual().getVidaAtual() > 0) {
			throw new IllegalArgumentException("Não é permitido salvar durante o encontro com o inimigo, somente ao fim do encontro !!!");
		}

		Path pasta = Paths.get(caminhoArquivo);
		if (!Files.exists(pasta) || !Files.isDirectory(pasta)) {
			throw new IllegalArgumentException("Deve ser selecionada uma pasta válida, não um arquivo.");
		}

		Path arquivoTemp = pasta.resolve(nomeArquivoTemp);
		Path arquivoFinal = pasta.resolve(nomeArquivoFinal);

		try {
			// 1. Grava primeiro no arquivo temporário com cabeçalho e versão
			try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(arquivoTemp))) {
				oos.writeUTF(assinaturaArquivo);
				oos.writeInt(versaoFormato);
				oos.writeObject(partidaInformada);
				oos.flush();
			}

			// 2. Só substitui o arquivo oficial após a gravação temporária concluir sem erros
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
				// Evita mascarar a exceção principal de gravação
			}
			throw new IOException("Falha ao gravar o arquivo de salvamento: " + ex.getMessage(), ex);
		}
	}

	private void validarConsistencia(Partida partida) throws IOException {
		if (partida.getJogadorPartida() == null
				|| partida.getPersonagemPartida() == null
				|| partida.getInimigos() == null
				|| partida.getInimigos().isEmpty()
				|| partida.getInimigoAtual() == null
				|| partida.getStatusPartida() == null
				|| partida.getEncontroAtual() < 1
				|| partida.getTurnoAtual() < 1) {
			throw new IOException("Dados da partida estão inconsistentes ou incompletos.");
		}
	}

}