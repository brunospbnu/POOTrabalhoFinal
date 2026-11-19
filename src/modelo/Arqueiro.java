package modelo;

public class Arqueiro extends Personagem {

	public Arqueiro(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoMaximo, int recursoAtual,
			boolean estaDefendendo, Inventario mochila) {
		super(nome, danoBase, vidaMaxima, vidaAtual, recursoMaximo, recursoAtual, estaDefendendo, mochila);
	}


	@Override
	public String usarHabilidade(Inimigo alvo) {
		
		// verifica se tem 30 de recurso, se não tiver retorna erro, 
		// se tiver recurso suficiente aplica a habilidade que da danoBase +10 pontos e ativa a defesa.
				
		if (this.getRecursoAtual() < 30) {
			throw new IllegalArgumentException("Recurso Insuficiente");
			}
				
		alvo.receberDano(this.getDanoBase() +10);
		this.defender();
		this.setRecursoAtual(this.getRecursoAtual() - 30);

		// A sugestão para facilitar a programação é fazer apenas uma habilidade por
		// heroi personagem / inimigo.
		return "Você usou habilidade: Flecha Divina";
	}

}
