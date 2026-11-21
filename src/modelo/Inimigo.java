package modelo;

public abstract class Inimigo extends Entidade {

	public Inimigo(String nome, int danoBase, int vidaMaxima, int vidaAtual, boolean estaDefendendo) {
		super(nome, danoBase, vidaMaxima, vidaAtual, estaDefendendo);
	}

	public String acaoAutomatica(Personagem personagem, int turnoAtual) {
		
		// texto que anota o que o inimigo fez e retorna para a classe que chamou.
		String textoDeRetorno = "";
		
		// verifica se o jogador ativou a defesa e o inimigo está com ela desativada
		// , e se sim ativa também.
		if (personagem.estaDefendendo() && !this.estaDefendendo()) {
			textoDeRetorno += super.defender();
		}
		// a cada 3 turnos usa a habilidade
		else if (turnoAtual % 3 == 0) {
			textoDeRetorno += usarHabilidade();
		}
		else {
			textoDeRetorno += super.atacar(personagem);
		}
		return textoDeRetorno;
	}

	public abstract String usarHabilidade();

	public abstract int pontosRecompensa();

}
