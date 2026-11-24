package modelo;

import excecoes.RecursoInsuficienteException;

public class Guerreiro extends Personagem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public Guerreiro(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoAtual,  int recursoMaximo,
			boolean estaDefendendo, Inventario mochila, int custoRecursoHabilidade) {
		
		super(nome, danoBase, vidaMaxima, vidaAtual, recursoAtual, recursoMaximo, estaDefendendo, mochila, custoRecursoHabilidade);
		
	}


	@Override
	public String usarHabilidade(Inimigo alvo) throws RecursoInsuficienteException{
		
		String textoDeRetorno = "Você usou habilidade: Quebra-Guarda !!!\n";
		// verifica se tem 20 de recurso, se não tiver retorna erro, 
		// se tiver recurso suficiente aplica a habilidade que da dano em dobro no inimigo e reduz o recurso em 20 pontos.
		
		if (this.getRecursoAtual() < getCustoRecursoHabilidade()) {
			throw new RecursoInsuficienteException();
		}
		
		textoDeRetorno += alvo.receberDano(this.getDanoBase() *2);
		this.setRecursoAtual(this.getRecursoAtual() - getCustoRecursoHabilidade());

		// A sugestão para facilitar a programação é fazer apenas uma habilidade por
		// heroi personagem / inimigo.
		return textoDeRetorno;
	}

	@Override
	public String getCaminhoImagem() {
		return "/Imagens/Cavaleiro.png";
	}
	
	@Override
	public String getNomeRecurso() {
	    return "Força";
	}
	
	@Override
	public String getDescricaoHabilidade() {
		return "Quebra-Guarda: causa o dobro do dano base.";
	}


}
