package modelo;

public class Esqueleto extends Inimigo {

	public Esqueleto(String nome, int danoBase, int vidaMaxima, int vidaAtual, boolean estaDefendendo) {
		super(nome, danoBase, vidaMaxima, vidaAtual, estaDefendendo);
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

}
