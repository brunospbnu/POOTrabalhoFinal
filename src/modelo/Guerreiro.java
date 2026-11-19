package modelo;

public class Guerreiro extends Personagem {

	public Guerreiro(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoMaximo, int recursoAtual,
			boolean estaDefendendo, Inventario mochila) {
		super(nome, danoBase, vidaMaxima, vidaAtual, recursoMaximo, recursoAtual, estaDefendendo, mochila);
	}

	@Override
	public String usarHabilidade() {
		// TODO fazer metodo.

		// A sugestão para facilitar a programação é fazer apenas uma habilidade por
		// heroi personagem / inimigo.
		return "Você usou habilidade: Quebra-Guarda";
	}

}
