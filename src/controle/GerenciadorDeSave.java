package controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import excecoes.ObjetoNuloException;

public class GerenciadorDeSave {
	
	private String caminhoArquivo;
	private Partida partidaSalvar;
	
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
	
	
	public Partida getPartidaSalvar() {
		return partidaSalvar;
	}

	
	public void setPartidaSalvar(Partida partidaSalvar) {
		if (partidaSalvar == null) {
			throw new ObjetoNuloException("A partida a ser salva");
		}
		this.partidaSalvar = partidaSalvar;
	}
	
	
	// Métodos principais: --------------------------------------------------
	
	
	public Partida carregarPartida() throws IOException, IllegalArgumentException, ClassNotFoundException {
		
		File caminhoInformado = new File(caminhoArquivo + "\\partidaSalva.dat");
		
		if (caminhoInformado.isFile()) {
			throw new IllegalArgumentException("Deve ser selecionada uma pasta, não um arquivo !!!");
		}
		else {
			FileInputStream fis = new FileInputStream(caminhoInformado);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Partida partidaCarregada = (Partida) ois.readObject();
			ois.close();
			return partidaCarregada;
		}
	}
	
	public void salvarPartida(Partida partidaInformada) throws IllegalArgumentException, IOException {
		File caminhoInformado = new File(caminhoArquivo + "\\partidaSalva.dat");
		
		if (caminhoInformado.isFile()) {
			throw new IllegalArgumentException("Deve ser selecionada uma pasta, não um arquivo !!!");
		}
		else {
			FileOutputStream fos = new FileOutputStream(caminhoInformado);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(partidaInformada);
			oos.close();
		}
	}


}
