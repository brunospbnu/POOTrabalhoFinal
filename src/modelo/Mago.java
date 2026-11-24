package modelo;

import excecoes.RecursoInsuficienteException;

public class Mago extends Personagem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Mago(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoAtual, int recursoMaximo,
			boolean estaDefendendo, Inventario mochila,  int custoRecursoHabilidade) {
		super(nome, danoBase, vidaMaxima, vidaAtual, recursoAtual, recursoMaximo, estaDefendendo, mochila, custoRecursoHabilidade);
	}


	@Override
	public String usarHabilidade(Inimigo alvo) throws RecursoInsuficienteException {
		
		String textoDeRetorno = "Você usou habilidade: Bola de Fogo !!!\n";
		
		// verifica se tem 40 de recurso, se não tiver retorna erro, 
		// se tiver recurso suficiente aplica a habilidade que da danoBase x 3 de dano.
				
		if (this.getRecursoAtual() < getCustoRecursoHabilidade()) {
			throw new RecursoInsuficienteException();
			}
				
		textoDeRetorno += alvo.receberDano(this.getDanoBase() * 3);
		this.setRecursoAtual(this.getRecursoAtual() - getCustoRecursoHabilidade());

		// A sugestão para facilitar a programação é fazer apenas uma habilidade por
		// heroi personagem / inimigo.
		return textoDeRetorno;
	}


	@Override
	public String getCaminhoImagem() {
		return "/Imagens/Mago.png";
	}
	
	@Override
	public String getNomeRecurso() {
	    return "Mana";
	}

}
