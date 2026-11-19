package modelo;

public class Mago extends Personagem {

	public Mago(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoMaximo, int recursoAtual,
			boolean estaDefendendo, Inventario mochila) {
		super(nome, danoBase, vidaMaxima, vidaAtual, recursoMaximo, recursoAtual, estaDefendendo, mochila);
	}


	@Override
	public String usarHabilidade(Inimigo alvo) {
		// verifica se tem 40 de recurso, se não tiver retorna erro, 
		// se tiver recurso suficiente aplica a habilidade que da danoBase x 3 de dano.
				
		if (this.getRecursoAtual() < 40) {
			throw new IllegalArgumentException("Recurso Insuficiente");
			}
				
		alvo.receberDano(this.getDanoBase() * 3);
		this.setRecursoAtual(this.getRecursoAtual() - 40);

		// A sugestão para facilitar a programação é fazer apenas uma habilidade por
		// heroi personagem / inimigo.
		return "Você usou habilidade: Bola de Fogo!";
	}

}
