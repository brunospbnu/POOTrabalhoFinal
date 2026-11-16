package modelo;

public class Esqueleto extends Inimigo {

	public Esqueleto(String nome, int danoBase, int vidaMaxima, int vidaAtual) {
		super(nome, danoBase, vidaMaxima, vidaAtual);
	}
	
	@Override
	public String usarHabilidade() {
		// TODO fazer metodo.
		return "inimigo usou habilidade"; // escrevi qualquer coisa
	}

}
