package modelo;

public class Bruxa extends Inimigo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bruxa(String nome, int danoBase, int vidaMaxima, int vidaAtual, boolean estaDefendendo) {
		super(nome, danoBase, vidaMaxima, vidaAtual, estaDefendendo);
	}

	// AÇÃO PERSONALIZADA DA BRUXA:
	
	@Override
	public String acaoAutomatica(Personagem alvo, int turnoAtual) {
		
		// texto que anota o que o inimigo fez e retorna para a classe que chamou.
		String textoDeRetorno = "";
		
		
		// a cada 2 turnos usa a habilidade
		if (turnoAtual % 2 == 0) {
			textoDeRetorno += usarHabilidade(alvo);
		}
		// Se estiver com metade da vida ou menos e ainda não estiver defendendo, defende se o turno for divisivel por 7.
		else if (getVidaAtual() <= getVidaMaxima() / 2 && turnoAtual % 7 == 0 && !this.estaDefendendo()) {
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
		
		
		// nessa habilidade a bruxa ativa a defesa, recebe cura e ataca na mesma rodada.
		textoDeRetorno += defender();
		textoDeRetorno += receberCura(20);
		textoDeRetorno += atacar(alvo);

		// A sugestão para facilitar a programação é fazer apenas uma habilidade por
		// heroi personagem / inimigo.
		return "O(a) inimigo: " + this.getNome() + " usou habilidade: Cura do Submundo.\n"+
		textoDeRetorno ;
	}

	@Override
	public int pontosRecompensa() {
		return 400;
	}

	@Override
	public String getCaminhoImagem() {
		return "/Imagens/Bruxa.png";
	}

}
