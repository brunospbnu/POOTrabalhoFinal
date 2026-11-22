package modelo;

import excecoes.RecursoInsuficienteException;

public class Guerreiro extends Personagem {

	public Guerreiro(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoMaximo, int recursoAtual,
			boolean estaDefendendo, Inventario mochila) throws RecursoInsuficienteException {
		
		super(nome, danoBase, vidaMaxima, vidaAtual, recursoMaximo, recursoAtual, estaDefendendo, mochila);
	}

	@Override
	public String usarHabilidade(Inimigo alvo) throws RecursoInsuficienteException{
		
		String textoDeRetorno = "Você usou habilidade: Quebra-Guarda !!!\n";
		// verifica se tem 20 de recurso, se não tiver retorna erro, 
		// se tiver recurso suficiente aplica a habilidade que da dano em dobro no inimigo e reduz o recurso em 20 pontos.
		
		if (this.getRecursoAtual() < 20) {
			throw new RecursoInsuficienteException();
		}
		
		textoDeRetorno += alvo.receberDano(this.getDanoBase() *2);
		this.setRecursoAtual(this.getRecursoAtual() - 20);

		// A sugestão para facilitar a programação é fazer apenas uma habilidade por
		// heroi personagem / inimigo.
		return textoDeRetorno;
	}

}
