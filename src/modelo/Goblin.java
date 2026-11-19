package modelo;

public class Goblin extends Inimigo {

	public Goblin(String nome, int danoBase, int vidaMaxima, int vidaAtual, boolean estaDefendendo) {
		super(nome, danoBase, vidaMaxima, vidaAtual, estaDefendendo);
	}

	@Override
	public String usarHabilidade() {
		// TODO fazer metodo.

		// A sugestão para facilitar a programação é fazer apenas uma habilidade por
		// heroi personagem / inimigo.
		return "O(a) inimigo: " + this.getNome() + " usou habilidade: Evasiva malandra";
	}

}
