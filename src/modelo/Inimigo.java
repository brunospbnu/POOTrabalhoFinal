package modelo;

public abstract class Inimigo extends Entidade {

	public Inimigo(String nome, int danoBase, int vidaMaxima, int vidaAtual, boolean estaDefendendo) {
		super(nome, danoBase, vidaMaxima, vidaAtual, estaDefendendo);
	}

	public String acaoAutomatica() {
		// TODO fazer metodo.
		return "inimigo fez algo"; // escrevi qualquer coisa
	}

	public abstract String usarHabilidade();

	public abstract int pontosRecompensa();

}
