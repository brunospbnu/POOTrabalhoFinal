package modelo;

public class Goblin extends Inimigo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Goblin(String nome, int danoBase, int vidaMaxima, int vidaAtual, boolean estaDefendendo) {
		super(nome, danoBase, vidaMaxima, vidaAtual, estaDefendendo);
	}

	@Override
	public String usarHabilidade(Personagem alvo) {
		
		String textoDeRetorno = "";
		// A sugestão para facilitar a programação é fazer apenas uma habilidade por
		// heroi personagem / inimigo.
		
		// A habilidade ativa a defesa e ataca o alvo na mesma ação.
		
		textoDeRetorno += defender();
		textoDeRetorno += atacar(alvo);
		
		return "O(a) inimigo: " + this.getNome() + " usou habilidade: Evasiva malandra \n" +
							textoDeRetorno ;
	}

	@Override
	public int pontosRecompensa() {
		return 250;
	}

	@Override
	public String getCaminhoImagem() {
		return "/Imagens/Goblin.png";
	}

}
