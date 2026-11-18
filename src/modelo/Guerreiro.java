package modelo;

public class Guerreiro extends Personagem {

	public Guerreiro(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoMaximo, int recursoAtual, Inventario mochila) {
		super(nome, danoBase, vidaMaxima, vidaAtual, recursoMaximo, recursoAtual, mochila);
	}

	@Override
	public String usarHabilidade() {
		// TODO fazer metodo.
		return "usou habilidade"; // isso aqui é para um futuro teste
	}

}
