package modelo;

public class Bruxa extends Inimigo {

	public Bruxa(String nome, int danoBase, int vidaMaxima, int vidaAtual, boolean estaDefendendo) {
		super(nome, danoBase, vidaMaxima, vidaAtual, estaDefendendo);
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
		return "O(a) inimigo: " + this.getNome() + " usou habilidade: Cura do Submundo"+
		textoDeRetorno ;
	}

	@Override
	public int pontosRecompensa() {
		return 400;
	}

}
