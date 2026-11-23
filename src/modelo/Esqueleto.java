package modelo;

public class Esqueleto extends Inimigo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Esqueleto(String nome, int danoBase, int vidaMaxima, int vidaAtual, boolean estaDefendendo) {
		super(nome, danoBase, vidaMaxima, vidaAtual, estaDefendendo);
	}
	
	
	
	// Ação automatica personalizada
	
	@Override
	public String acaoAutomatica(Personagem alvo, int turnoAtual) {
		
		// texto que anota o que o inimigo fez e retorna para a classe que chamou.
		String textoDeRetorno = "";
		
		
		// a cada 3 turnos usa a habilidade
		if (turnoAtual % 3 == 0) {
			textoDeRetorno += usarHabilidade(alvo);
		}
		// Se estiver com metade da vida ou menos e ainda não estiver defendendo, defende nos turnos pares
		else if (getVidaAtual() <= getVidaMaxima() / 2 && turnoAtual % 2 == 0 && !this.estaDefendendo()) {
			textoDeRetorno += super.defender();
		}

		else {
			textoDeRetorno += super.atacar(alvo);
		}
		
		return textoDeRetorno;
	}

	
	
	@Override
	public String usarHabilidade(Personagem alvo) {
		
		String textoDeRetorno = "";
		// nessa habilidade o esqueleto se cura e ataca o inimigo na mesma rodada.
		
		textoDeRetorno += receberCura(15);
		textoDeRetorno += atacar(alvo);

		// A sugestão para facilitar a programação é fazer apenas uma habilidade por
		// heroi personagem / inimigo.
		return "O(a) inimigo: " + this.getNome() + " usou habilidade: Remontagem de ossos\n "+
				textoDeRetorno ;
	}

	@Override
	public int pontosRecompensa() {
		return 300;
	}

	@Override
	public String getCaminhoImagem() {
		return "/Imagens/Esqueleto.png";
	}

}
