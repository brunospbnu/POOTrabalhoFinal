package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import excecoes.ObjetoNuloException;
import excecoes.ValorMenorQue0Exception;
import excecoes.ValorMenorQue1Exception;
import modelo.*;

public class Partida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private Jogador jogadorPartida;
	private Personagem personagemPartida;
	private List<Inimigo> inimigos;
	private Inimigo inimigoAtual;
	private int encontroAtual;
	private int turnoAtual;
	private StatusPartida statusPartida;
	private List<String> historicoDeAcoes;

	public Partida(String nmJogador, int opcaoDeClasse) throws IllegalArgumentException, ObjetoNuloException, ValorMenorQue1Exception {
		// 1. Instancia o jogador com o nome recebido do Menu
		jogadorPartida = new Jogador(nmJogador, 0);
		// 2. Instancia a lista de histórico
		historicoDeAcoes = new ArrayList<>();
		// 3. Instancia o herói escolhido usando polimorfismo
		
		// 3.1. quantidade de poções iniciais = 2.
		Inventario inventarioInicial = new Inventario(2);
		switch (opcaoDeClasse) {
		case 0:
			personagemPartida = new Guerreiro("Guerreiro", 25, 150, 150, 30, 30, false, inventarioInicial);
			break;
		case 1:
			personagemPartida = new Mago("Mago", 15, 80, 80, 100, 100, false, inventarioInicial);
			break;
		case 2:
			personagemPartida = new Arqueiro("Arqueiro", 20, 100, 100, 50, 50, false, inventarioInicial);
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

	public void setJogadorPartida(Jogador jogadorPartida) throws ObjetoNuloException {
		if (jogadorPartida == null) {
			throw new ObjetoNuloException("O jogador");
		}
		this.jogadorPartida = jogadorPartida;
	}

	public Personagem getPersonagemPartida() {
		return personagemPartida;
	}

	public void setPersonagemPartida(Personagem personagemPartida) throws ObjetoNuloException {
		if (personagemPartida == null) {
			throw new ObjetoNuloException("O personagem");
		}
		this.personagemPartida = personagemPartida;
	}

	public List<Inimigo> getInimigos() {
		return inimigos;
	}

	public void setInimigos(List<Inimigo> inimigos) throws ObjetoNuloException{
		if (inimigos == null) {
			throw new ObjetoNuloException("A lista de inimigos");
		}
		this.inimigos = inimigos;
	}

	public Inimigo getInimigoAtual() {
		return inimigoAtual;
	}

	public void setInimigoAtual(Inimigo inimigoAtual) throws ObjetoNuloException {
		if (inimigoAtual == null) {
			throw new ObjetoNuloException("O inimigo atual");
		}
		this.inimigoAtual = inimigoAtual;
	}

	public int getEncontroAtual() {
		return encontroAtual;
	}

	public void setEncontroAtual(int encontroAtual) throws ValorMenorQue1Exception {
		if (encontroAtual < 1) {
			throw new ValorMenorQue1Exception("O encontro atual");
		}
		this.encontroAtual = encontroAtual;
	}

	public List<String> getHistoricoDeAcoes() {
		return historicoDeAcoes;
	}

	public void setHistoricoDeAcoes(List<String> historicoDeAcoes) throws ObjetoNuloException {
		if (historicoDeAcoes == null) {
			throw new ObjetoNuloException("O histórico de ações");
		}
		this.historicoDeAcoes = historicoDeAcoes;
	}

	public int getTurnoAtual() {
		return turnoAtual;
	}

	public void setTurnoAtual(int turnoAtual) throws ValorMenorQue1Exception {
		if (turnoAtual < 1) {
			throw new ValorMenorQue1Exception("O turno atual");
		}
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
		lista.add(new Goblin("Goblin Batedor", 5, 30, 30, false));

		// Inimigo 2 (Será o do Encontro 2)
		lista.add(new Esqueleto("Esqueleto Arqueiro", 10, 45, 45, false));

		// Inimigo 3 (Será o do Encontro 3 - O Boss)
		lista.add(new Bruxa("Bruxa Sombria", 15, 80, 80, false));

		return lista;
	}

	public String executarAcaoTurno(int acaoEscolhida) throws IllegalArgumentException, ObjetoNuloException, ValorMenorQue1Exception {
		
		if (isFinalizada()) {
			throw new IllegalArgumentException("Partida já finalizada. Não é possível executar ação.");
		}

		if (inimigoAtual.getVidaAtual() == 0) {
			if (acaoEscolhida == 3) {
				String textoPocao = personagemPartida.tomarPocao() + "\n";
				historicoDeAcoes.add(textoPocao);
				return textoPocao;
			}
			throw new IllegalArgumentException("O inimigo atual já foi derrotado. Avance para o próximo encontro ou use uma poção.");
		}
		
		String textoDeRetorno = "• Turno " + turnoAtual + ": \n";
		
		// O metodo visão vai fornecer um int correspondente a ação escolhida.
		
		switch (acaoEscolhida) {
		
		case 1: //atacar
			textoDeRetorno += personagemPartida.atacar(inimigoAtual) + "\n";
			break;
			
		case 2: //defender
			textoDeRetorno += personagemPartida.defender() + "\n";
			break;

		case 3: //tomar pocao
			textoDeRetorno += personagemPartida.tomarPocao() + "\n";
			break;
			
		case 4: //usar habilidade
			textoDeRetorno += personagemPartida.usarHabilidade(inimigoAtual) + "\n";
			break;

		default:
			throw new IllegalArgumentException("Ação inválida !! \n");
		}
		
		if (inimigoAtual.getVidaAtual() == 0) {
			textoDeRetorno += "\nDerrotou o(a) " + inimigoAtual.getNome();
			if (encontroAtual == 3) {
		        statusPartida = StatusPartida.VITORIA;
		        textoDeRetorno += "\n" + calcularPontuacaoFinal();
		    }
		}
		else {
			textoDeRetorno += inimigoAtual.acaoAutomatica(getPersonagemPartida(), getTurnoAtual());
			
			if (personagemPartida.getVidaAtual() == 0) {
				this.setStatusPartida(StatusPartida.DERROTA);
				textoDeRetorno += "\n" + calcularPontuacaoFinal();
			}
			else {
				turnoAtual ++;
			}
			
		}
		
		historicoDeAcoes.add(textoDeRetorno);
		
		return textoDeRetorno;
	}

	public String avancarEncontro() {

		String textoDeRetorno = "";
		
		encontroAtual++;

		if (encontroAtual == 2) {
			inimigoAtual = inimigos.get(1); // Pega o Esqueleto
			textoDeRetorno+= "Avançou para o encontro " + encontroAtual + " - Inimigo: " + inimigoAtual.getNome()+ " \n";
		} else if (encontroAtual == 3) {
			inimigoAtual = inimigos.get(2); // Pega a Bruxa / Boss
			textoDeRetorno+= "Avançou para o encontro " + encontroAtual + " - Inimigo: " + inimigoAtual.getNome()+ " \n";
		} else {
			// Se passar de 3, o jogo acabou e o jogador venceu!
			statusPartida = StatusPartida.VITORIA;
			calcularPontuacaoFinal();
			textoDeRetorno+= "Ganhou o jogo !!!\n";
		}
		historicoDeAcoes.add(textoDeRetorno);
		return textoDeRetorno;
	}

// Deixei tudo comentado e explicado a maneira que esse método funciona. Obs.: Podemos sim adicionar mais coisas, isso aqui ainda é um teste
	public String calcularPontuacaoFinal() throws ValorMenorQue0Exception {
		
		String textoDeRetorno = "";
		
		int pontuacaoTotal = 0;
		
		// 1 - Vamos supor no momento que o jogador ganhe 200 pontos a cada vitória
		if (this.statusPartida == StatusPartida.VITORIA) {
			int pontosVitoria = 200;
			// 2 - Vamos supor que também é possível ganhar pontos pelo número de poções que
			// sobraram no inventário
			// 3 - Vai pegar as poções que restaram no inventário
			int pocoesRestantes = this.personagemPartida.getInventario().getQtdPocoes();

			// 4- bônus por poção, deixei como 25, mas podemos alterar depois
			int bonusPocoes = pocoesRestantes * 25;

			// 5 - vai pegar os pontos por derrotar um inimigo em específico
			int pontosInimigo = 0;
					
			// 6- Colocado um "for" para poder somar os pontos de todos os inimigos derrotados, evitando que some somente do último.
				for (Inimigo inimigoAtual : inimigos) {
					pontosInimigo += inimigoAtual.pontosRecompensa();
					
				}
				
			// 7 - pontuacao por turnos usados:
			// Inicia com 500 e diminui 10 a cada turno adicional
			
			int turnosUsados = turnoAtual;
			int pontosTurnos = 500 - (10 * turnosUsados);
			if (pontosTurnos < 0) {
				pontosTurnos = 0;
			}

			// 8 - Soma total, incluindo os pontos pela vitória, poções, pontos do inimigo
			// que foi derrotado e pontos por turnos usados.
			pontuacaoTotal = pontosVitoria + bonusPocoes + pontosInimigo + pontosTurnos;

			// 9 - Atribuindo o total de pontos ao jogador
			this.jogadorPartida.addPontuacao(pontuacaoTotal);
			
		} else {
			// 10 - se perder não vai ganhar pontos por vitória
			this.jogadorPartida.setPontuacao(pontuacaoTotal);
		}
		
		textoDeRetorno += "Pontuação Final: " + pontuacaoTotal;
		
		return textoDeRetorno;
	}

	public boolean isFinalizada() {
		boolean finalizada = false;
		if (getStatusPartida() != StatusPartida.EM_ANDAMENTO) {
			finalizada = true;
		}
		return finalizada;

	}

}
