package modelo;

public abstract class Inimigo extends Entidade {

	public Inimigo(String nome, int danoBase, int vidaMaxima, int vidaAtual, boolean estaDefendendo) {
		super(nome, danoBase, vidaMaxima, vidaAtual, estaDefendendo);
	}

	public String acaoAutomatica(Personagem alvo, int turnoAtual) {
		
		// texto que anota o que o inimigo fez e retorna para a classe que chamou.
		String textoDeRetorno = "";
		
		// verifica se o jogador ativou a defesa e o inimigo está com ela desativada
		// , e se sim ativa também.
		if (alvo.estaDefendendo() && !this.estaDefendendo()) {
			textoDeRetorno += super.defender();
		}
		// a cada 3 turnos usa a habilidade
		else if (turnoAtual % 3 == 0) {
			textoDeRetorno += usarHabilidade(alvo);
		}
		else {
			textoDeRetorno += super.atacar(alvo);
		}
		
		return textoDeRetorno;
	}

	public abstract String usarHabilidade(Personagem alvo);

	public abstract int pontosRecompensa();

}
