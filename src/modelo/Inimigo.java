package modelo;

public abstract class Inimigo extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Inimigo(String nome, int danoBase, int vidaMaxima, int vidaAtual, boolean estaDefendendo) {
		super(nome, danoBase, vidaMaxima, vidaAtual, estaDefendendo);
	}

	public String acaoAutomatica(Personagem alvo, int turnoAtual) {
		
		// texto que anota o que o inimigo fez e retorna para a classe que chamou.
		String textoDeRetorno = "";
		
		
		// a cada 3 turnos usa a habilidade
		if (turnoAtual % 3 == 0) {
			textoDeRetorno += usarHabilidade(alvo);
		}

		else {
			textoDeRetorno += super.atacar(alvo);
		}
		
		return textoDeRetorno;
	}

	public abstract String usarHabilidade(Personagem alvo);

	public abstract int pontosRecompensa();
	
	public abstract String getCaminhoImagem();

}
