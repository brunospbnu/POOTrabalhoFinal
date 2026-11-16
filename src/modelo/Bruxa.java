package modelo;

public class Bruxa extends Inimigo{

	public Bruxa(String nome, int danoBase, int vidaMaxima, int vidaAtual) {
		super(nome, danoBase, vidaMaxima, vidaAtual);
	}

	@Override
	public String usarHabilidade() {
		// TODO fazer metodo.
		return "inimigo usou habilidade"; // escrevi qualquer coisa
	}

	
}
