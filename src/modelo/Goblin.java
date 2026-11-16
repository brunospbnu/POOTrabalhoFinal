package modelo;

public class Goblin extends Inimigo {

	public Goblin(String nome, int danoBase, int vidaMaxima, int vidaAtual) {
		super(nome, danoBase, vidaMaxima, vidaAtual);
	}
	
	@Override
	public String usarHabilidade() {
		return "inimigo usou habilidade"; // escrevi qualquer coisa
	}

}
