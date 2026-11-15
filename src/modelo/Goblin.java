package modelo;

public class Goblin extends Inimigo {

	Goblin(String nome, int danoBase, int vidaMaxima, int vidaAtual) {
		super(nome, danoBase, vidaMaxima, vidaAtual);
	}

	public String usarHabilidade() {
		return "inimigo usou habilidade"; // escrevi qualquer coisa
	}

}
