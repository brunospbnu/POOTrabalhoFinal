package modelo;

public class Arqueiro extends Personagem {

	public Arqueiro(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoMaximo, int recursoAtual,
			Inventario mochila) {
		super(nome, danoBase, vidaMaxima, vidaAtual, recursoMaximo, recursoAtual, mochila);
	}

	public String usarHabilidade() {
		return "usou habilidade";
	}

}
