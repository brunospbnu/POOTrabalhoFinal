package controle;
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
	
	public Partida(String nmJogador) {
		
		//TODO fazer construtor.
		
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
	
	public void executarAcaoTurno() {
		
		// TODO fazer metodo.
	}
	
	public void avancarEncontro() {
		
		// TODO fazer metodo.
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
