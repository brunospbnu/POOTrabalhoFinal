package modelo;

public class Mago extends Personagem {

	public Mago(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoMaximo, int recursoAtual,
			Inventario mochila) {
		super(nome, danoBase, vidaMaxima, vidaAtual, recursoMaximo, recursoAtual, mochila);
	}
	
	@Override
	public String usarHabilidade() {
		return "usou habilidade";
	}

}
