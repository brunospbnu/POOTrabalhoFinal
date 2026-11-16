package controle;
import java.util.ArrayList;
import java.util.List;

import modelo.*;

public class Partida {
	
	private Jogador jogadorPartida;
	private Personagem personagemPartida;
	private List<Inimigo> inimigos;
	private Inimigo inimigoAtual;
	private int encontroAtual;
	private int turnoAtual;
	private StatusPartida statusPartida;
	private List<String> historicoDeAcoes;
	
	public Partida(String nmJogador, int opcaoDeClasse) throws IllegalArgumentException {
		// 1. Instancia o jogador com o nome recebido do Menu
		jogadorPartida = new Jogador(nmJogador, 0);
		// 2. Instancia a lista de histórico
		historicoDeAcoes = new ArrayList<>();
		// 3. Instancia o herói escolhido usando polimorfismo
	    switch (opcaoDeClasse) {
	        case 0:
	            personagemPartida = new Guerreiro("Guerreiro", 25, 150, 150, 30, 30, null);
	            break;
	        case 1:
	            personagemPartida = new Mago("Mago", 15, 80, 80, 100, 100, null);
	            break;
	        case 2:
	            personagemPartida = new Arqueiro("Arqueiro", 20, 100, 100, 50, 50, null);
	            break;
	        default:
	            throw new IllegalArgumentException("Classe inválida");
	    }
	    // 4. Configura o estado inicial do jogo
	    turnoAtual = 1;
	    encontroAtual = 1;
	    statusPartida = StatusPartida.EM_ANDAMENTO;
	 // 5. Gera os inimigos da partida
	    inimigos = gerarInimigosIniciais();
	    inimigoAtual = inimigos.get(0);
	}

	public Jogador getJogadorPartida() {
		return jogadorPartida;
	}

	public void setJogadorPartida(Jogador jogadorPartida) {
		this.jogadorPartida = jogadorPartida;
	}

	public Personagem getPersonagemPartida() {
		return personagemPartida;
	}

	public void setPersonagemPartida(Personagem personagemPartida) {
		this.personagemPartida = personagemPartida;
	}

	public List<Inimigo> getInimigos() {
		return inimigos;
	}

	public void setInimigos(List<Inimigo> inimigos) {
		this.inimigos = inimigos;
	}

	public Inimigo getInimigoAtual() {
		return inimigoAtual;
	}

	public void setInimigoAtual(Inimigo inimigoAtual) {
		this.inimigoAtual = inimigoAtual;
	}

	public int getEncontroAtual() {
		return encontroAtual;
	}

	public void setEncontroAtual(int encontroAtual) {
		this.encontroAtual = encontroAtual;
	}

	public List<String> getHistoricoDeAcoes() {
		return historicoDeAcoes;
	}

	public void setHistoricoDeAcoes(List<String> historicoDeAcoes) {
		this.historicoDeAcoes = historicoDeAcoes;
	}

	public int getTurnoAtual() {
		return turnoAtual;
	}

	public void setTurnoAtual(int turnoAtual) {
		this.turnoAtual = turnoAtual;
	}

	public StatusPartida getStatusPartida() {
		return statusPartida;
	}

	public void setStatusPartida(StatusPartida statusPartida) {
		this.statusPartida = statusPartida;
	}
	
	public List<Inimigo> gerarInimigosIniciais() {
		List<Inimigo> lista = new ArrayList<>();
	    
	    // Inimigo 1 (Será o do Encontro 1)
	    lista.add(new Goblin("Goblin Batedor", 5, 30, 30));
	    
	    // Inimigo 2 (Será o do Encontro 2)
	    lista.add(new Esqueleto("Esqueleto Arqueiro", 10, 45, 45));
	    
	    // Inimigo 3 (Será o do Encontro 3 - O Boss)
	    lista.add(new Bruxa("Bruxa Sombria", 15, 80, 80));
	    
	    return lista;
	}
	
	public void executarAcaoTurno() {
		
		// TODO fazer metodo.
	}
	
	public void avancarEncontro() {
		
		encontroAtual++;

	    if (encontroAtual == 2) {
	        inimigoAtual = inimigos.get(1); // Pega o Esqueleto
	    } 
	    else if (encontroAtual == 3) {
	        inimigoAtual = inimigos.get(2); // Pega a Bruxa / Boss
	    } 
	    else {
	        // Se passar de 3, o jogo acabou e o jogador venceu!
	        statusPartida = StatusPartida.VITORIA;
	    }
	
	}
	
	public void calcularPontuacaoFinal() {
		
		// TODO fazer metodo.
		
	}
	
	public boolean isFinalizada() {
		boolean finalizada = false;
		
		// TODO fazer metodo.
		
		return finalizada;
		
	}
	
}
