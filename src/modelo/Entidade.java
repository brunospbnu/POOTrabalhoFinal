package modelo;

public abstract class Entidade {

	private String nome;
	private int danoBase;
	private int vidaMaxima;
	private int vidaAtual;

	public Entidade(String nome, int danoBase, int vidaMaxima, int vidaAtual) {
		setNome(nome);
		setDanoBase(danoBase);
		setVidaMaxima(vidaMaxima);
		setVidaAtual(vidaAtual);

	}

	// --------------------------------------
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDanoBase() {
		return danoBase;
	}

	public void setDanoBase(int danoBase) {
		this.danoBase = danoBase;
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public void setVidaMaxima(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}

	public int getVidaAtual() {
		return vidaAtual;
	}

	public void setVidaAtual(int vidaAtual) {
		this.vidaAtual = vidaAtual;
	}
	// -----------------------------------------------

	public void receberDano(int valor) {

	}

	public void receberCura(int valor) {

	}

	public void atacar(Entidade alvo) {

	}

	public void defender() {

	}

}