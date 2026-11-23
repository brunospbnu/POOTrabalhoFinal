package controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import excecoes.ObjetoNuloException;

public class GerenciadorDeSave {
	
	private String caminhoArquivo;
	
	// gets e sets ----------------
	
	
	public GerenciadorDeSave(String caminhoInformado) {
		setCaminhoArquivo(caminhoInformado);
	}

	
	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	
	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}
	

	// Métodos principais: --------------------------------------------------
	
	
	public Partida carregarPartida() throws IOException, IllegalArgumentException, ClassNotFoundException, FileNotFoundException {
		
		File pasta = new File(caminhoArquivo);
		if (!pasta.isDirectory()) {
			throw new IllegalArgumentException("Deve ser selecionada uma pasta válida, não um arquivo.");
		}

		File arquivoSave = new File(pasta, "partidaSalva.dat");
		if (!arquivoSave.exists()) {
			throw new FileNotFoundException("Nenhuma partida salva foi encontrada nesta pasta.");
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoSave))) {
			return (Partida) ois.readObject();
		}
		
	}
	
	public void salvarPartida(Partida partidaInformada) throws IllegalArgumentException, IOException, ObjetoNuloException {
		
		if (partidaInformada == null) {
			throw new ObjetoNuloException("A partida a ser salva");
		}
		
		if (partidaInformada.getInimigoAtual().getVidaAtual() > 0) {
			throw new IllegalArgumentException("Não é permitido salvar durante o encontro com o inimigo, somente ao fim do encontro !!!");
		}

		File pasta = new File(caminhoArquivo);
		if (!pasta.isDirectory()) {
			throw new IllegalArgumentException("Deve ser selecionada uma pasta válida, não um arquivo.");
		}

		File arquivoSave = new File(pasta, "partidaSalva.dat");
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoSave))) {
			oos.writeObject(partidaInformada);
		}
	}


}
