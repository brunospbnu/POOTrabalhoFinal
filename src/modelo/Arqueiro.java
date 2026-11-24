package modelo;

import excecoes.RecursoInsuficienteException;

public class Arqueiro extends Personagem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Arqueiro(String nome, int danoBase, int vidaMaxima, int vidaAtual, int recursoAtual, int recursoMaximo,
			boolean estaDefendendo, Inventario mochila,  int custoRecursoHabilidade) throws RecursoInsuficienteException {
		super(nome, danoBase, vidaMaxima, vidaAtual, recursoAtual, recursoMaximo, estaDefendendo, mochila, custoRecursoHabilidade);
	}


	@Override
	public String usarHabilidade(Inimigo alvo) {
		
		String textoDeRetorno = "Você usou habilidade: Flecha Divina !!!\n";
		// verifica se tem 30 de recurso, se não tiver retorna erro, 
		// se tiver recurso suficiente aplica a habilidade que da danoBase +10 pontos e ativa a defesa.
				
		if (this.getRecursoAtual() < getCustoRecursoHabilidade()) {
			throw new RecursoInsuficienteException();
			}
				
		textoDeRetorno += alvo.receberDano(this.getDanoBase() +10);
		textoDeRetorno += this.defender();
		this.setRecursoAtual(this.getRecursoAtual() - getCustoRecursoHabilidade());

		// A sugestão para facilitar a programação é fazer apenas uma habilidade por
		// heroi personagem / inimigo.
		return textoDeRetorno;
	}


	@Override
	public String getCaminhoImagem() {
		return "/Imagens/Arqueira.png";
	}
	
	@Override
	public String getNomeRecurso() {
	    return "Foco";
	}

}
