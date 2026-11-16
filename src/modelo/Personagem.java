package modelo;

public abstract class Personagem extends Entidade {

	private int recursoAtual;
	private int recursoMaximo;
	private Inventario mochila;

	public Personagem(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoAtual, int recursoMaximo,
			Inventario mochila) {
		super(nome, danoBase, vidaMaxima, vidaAtual);
		setRecursoAtual(recursoAtual);
		setRecursoMaximo(recursoMaximo);
		setMochila(mochila);
	}

	// ----------------------------------------
	public int getRecursoAtual() {
		return recursoAtual;
	}

	public void setRecursoAtual(int recursoAtual) {
		this.recursoAtual = recursoAtual;
	}

	public int getRecursoMaximo() {
		return recursoMaximo;
	}

	public void setRecursoMaximo(int recursoMaximo) {
		this.recursoMaximo = recursoMaximo;
	}

	public Inventario getMochila() {
		return mochila;
	}

	public void setMochila(Inventario mochila) {
		this.mochila = mochila;
	}

	// --------------------------------------

	public void abrirInventario() {
		// TODO fazer metodo.
	}

	public String tomarPocao() {
		// TODO fazer metodo.
		return "tomou poção"; // escrevi qualquer coisa
	}

	public abstract String usarHabilidade();

}
