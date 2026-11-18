package modelo;

public class Goblin extends Inimigo {

	public Goblin(String nome, int danoBase, int vidaMaxima, int vidaAtual) {
		super(nome, danoBase, vidaMaxima, vidaAtual);
	}
	
	@Override
	public String usarHabilidade() {
		// TODO fazer metodo.
		
		
		// A sugestão para facilitar a programação é fazer apenas uma habilidade por heroi personagem / inimigo.
		return "O(a) inimigo: " + this.getNome() +" usou habilidade: Evasiva malandra";
	}

}
