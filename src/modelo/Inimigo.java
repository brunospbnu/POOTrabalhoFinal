package modelo;

public abstract class Inimigo extends Entidade {

	public Inimigo(String nome, int danoBase, int vidaMaxima, int vidaAtual) {
		super(nome, danoBase, vidaMaxima, vidaAtual);
	}

	public String acaoAutomatica() {
		return "inimigo fez algo"; // escrevi qualquer coisa
	}

	public abstract String usarHabilidade();

}
