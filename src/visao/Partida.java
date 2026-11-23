package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import controle.StatusPartida;
import modelo.Inimigo;
import modelo.Personagem;

public class Partida extends PainelFundo {

	private static final long serialVersionUID = 1L;
	private Menu menu;
	private controle.Partida partidaControle;

	// Topo
	private JLabel lblInfoEncontro;

	// Lado do personagem
	private JLabel lblNomePersonagem;
	private LabelImagem imgPersonagem;
	private JProgressBar barraVidaPersonagem;
	private JProgressBar barraRecursoPersonagem;
	private JPanel painelInventario;
	private JLabel lblPocoes;
	private JButton btnTomarPocao;

	// Centro (Log de combate)
	private JTextArea txtHistorico;
	private JScrollPane scrollHistorico;

	// Lado do inimigo
	private JLabel lblNomeInimigo;
	private LabelImagem imgInimigo;
	private JProgressBar barraVidaInimigo;
	private BotaoImagem btnAvancarEncontro;

	// Botões de ação
	private BotaoImagem btnVoltar;
	private BotaoImagem btnAtacar;
	private BotaoImagem btnDefender;
	private BotaoImagem btnHabilidade;

	public Partida(Menu menu) {
		super("/Imagens/SomenteFundo.jpg");
		this.menu = menu;
		setLayout(null);

		// 1. Informações do Encontro e Turno (Topo)
		lblInfoEncontro = new JLabel("Encontro 1/3  |  Turno 1", SwingConstants.CENTER);
		lblInfoEncontro.setBounds(180, 10, 357, 28);
		lblInfoEncontro.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInfoEncontro.setForeground(new Color(255, 220, 120));
		lblInfoEncontro.setOpaque(true);
		lblInfoEncontro.setBackground(new Color(30, 18, 10, 200));
		lblInfoEncontro.setBorder(BorderFactory.createLineBorder(new Color(180, 140, 70), 2));
		add(lblInfoEncontro);

		// 2. Lado Esquerdo - Personagem
		lblNomePersonagem = new JLabel("Personagem", SwingConstants.CENTER);
		lblNomePersonagem.setBounds(25, 44, 190, 24);
		lblNomePersonagem.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNomePersonagem.setForeground(Color.WHITE);
		lblNomePersonagem.setOpaque(true);
		lblNomePersonagem.setBackground(new Color(30, 18, 10, 190));
		add(lblNomePersonagem);

		imgPersonagem = new LabelImagem("/Imagens/Cavaleiro.png");
		imgPersonagem.setBounds(47, 72, 145, 140);
		add(imgPersonagem);

		barraVidaPersonagem = new JProgressBar(0, 100);
		barraVidaPersonagem.setBounds(25, 218, 190, 22);
		barraVidaPersonagem.setStringPainted(true);
		barraVidaPersonagem.setForeground(new Color(46, 160, 67));
		barraVidaPersonagem.setBackground(new Color(45, 20, 20));
		barraVidaPersonagem.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(barraVidaPersonagem);

		barraRecursoPersonagem = new JProgressBar(0, 100);
		barraRecursoPersonagem.setBounds(25, 246, 190, 22);
		barraRecursoPersonagem.setStringPainted(true);
		barraRecursoPersonagem.setForeground(new Color(40, 110, 210));
		barraRecursoPersonagem.setBackground(new Color(25, 25, 45));
		barraRecursoPersonagem.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(barraRecursoPersonagem);

		// Painel de Inventário (lado do personagem)
		painelInventario = new JPanel(new BorderLayout(4, 4));
		painelInventario.setBounds(25, 276, 190, 82);
		painelInventario.setBackground(new Color(35, 22, 12, 215));
		painelInventario.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(new Color(180, 140, 70), 2),
				"Inventário", 0, 0, new Font("Tahoma", Font.BOLD, 11), new Color(255, 220, 120)));

		lblPocoes = new JLabel("Poções: 0", SwingConstants.CENTER);
		lblPocoes.setForeground(Color.WHITE);
		lblPocoes.setFont(new Font("Tahoma", Font.BOLD, 12));
		painelInventario.add(lblPocoes, BorderLayout.CENTER);

		btnTomarPocao = new JButton("Tomar Poção");
		btnTomarPocao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTomarPocao.setBackground(new Color(120, 45, 30));
		btnTomarPocao.setForeground(new Color(255, 230, 150));
		btnTomarPocao.setFocusPainted(false);
		btnTomarPocao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarAcao(3);
			}
		});
		painelInventario.add(btnTomarPocao, BorderLayout.SOUTH);
		add(painelInventario);

		// 3. Centro - Histórico de Combate
		txtHistorico = new JTextArea();
		txtHistorico.setEditable(false);
		txtHistorico.setLineWrap(true);
		txtHistorico.setWrapStyleWord(true);
		txtHistorico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtHistorico.setBackground(new Color(28, 18, 12));
		txtHistorico.setForeground(new Color(245, 235, 210));

		scrollHistorico = new JScrollPane(txtHistorico);
		scrollHistorico.setBounds(230, 72, 257, 286);
		scrollHistorico.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(new Color(180, 140, 70), 2),
				"Histórico de Combate", 0, 0, new Font("Tahoma", Font.BOLD, 11), new Color(255, 220, 120)));
		scrollHistorico.getViewport().setBackground(new Color(28, 18, 12));
		add(scrollHistorico);

		// 4. Lado Direito - Inimigo
		lblNomeInimigo = new JLabel("Inimigo", SwingConstants.CENTER);
		lblNomeInimigo.setBounds(502, 44, 190, 24);
		lblNomeInimigo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNomeInimigo.setForeground(new Color(255, 160, 160));
		lblNomeInimigo.setOpaque(true);
		lblNomeInimigo.setBackground(new Color(30, 18, 10, 190));
		add(lblNomeInimigo);

		imgInimigo = new LabelImagem("/Imagens/Goblin.png");
		imgInimigo.setBounds(524, 72, 145, 140);
		add(imgInimigo);

		barraVidaInimigo = new JProgressBar(0, 100);
		barraVidaInimigo.setBounds(502, 218, 190, 22);
		barraVidaInimigo.setStringPainted(true);
		barraVidaInimigo.setForeground(new Color(200, 45, 45));
		barraVidaInimigo.setBackground(new Color(45, 20, 20));
		barraVidaInimigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(barraVidaInimigo);

		// Botão Avançar Encontro (aparece quando o inimigo atual é derrotado)
		btnAvancarEncontro = new BotaoImagem("/Imagens/AvancarEncontro.png");
		btnAvancarEncontro.setBounds(502, 285, 190, 55);
		btnAvancarEncontro.setVisible(false);
		btnAvancarEncontro.setEnabled(false);
		btnAvancarEncontro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avancarParaProximoEncontro();
			}
		});
		add(btnAvancarEncontro);

		// 5. Rodapé - Botões de Ação
		btnVoltar = new BotaoImagem("/Imagens/BotãoVoltar.png");
		btnVoltar.setBounds(25, 380, 135, 42);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (partidaControle != null && !partidaControle.isFinalizada()) {
					int resposta = JOptionPane.showConfirmDialog(
							Partida.this,
							"Deseja abandonar a partida atual e voltar ao menu?",
							"Voltar ao Menu",
							JOptionPane.YES_NO_OPTION);
					if (resposta != JOptionPane.YES_OPTION) {
						return;
					}
				}
				Partida.this.menu.trocarTela("telaInicial");
			}
		});
		add(btnVoltar);

		btnAtacar = new BotaoImagem("/Imagens/BotãoAtacar.png");
		btnAtacar.setBounds(185, 380, 155, 42);
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarAcao(1);
			}
		});
		add(btnAtacar);

		btnDefender = new BotaoImagem("/Imagens/BotãoDefender.png");
		btnDefender.setBounds(360, 380, 155, 42);
		btnDefender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarAcao(2);
			}
		});
		add(btnDefender);

		btnHabilidade = new BotaoImagem("/Imagens/BotãoUsarHabilidade.png");
		btnHabilidade.setBounds(535, 380, 157, 42);
		btnHabilidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarAcao(4);
			}
		});
		add(btnHabilidade);

		// 6. Redimensionamento proporcional
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int larguraAtual = getWidth();
				int alturaAtual = getHeight();

				if (larguraAtual > 0 && alturaAtual > 0) {
					double px = larguraAtual / 717.0;
					double py = alturaAtual / 471.0;

					lblInfoEncontro.setBounds((int) (180 * px), (int) (10 * py), (int) (357 * px), (int) (28 * py));

					// Personagem
					lblNomePersonagem.setBounds((int) (25 * px), (int) (44 * py), (int) (190 * px), (int) (24 * py));
					imgPersonagem.setBounds((int) (47 * px), (int) (72 * py), (int) (145 * px), (int) (140 * py));
					barraVidaPersonagem.setBounds((int) (25 * px), (int) (218 * py), (int) (190 * px), (int) (22 * py));
					barraRecursoPersonagem.setBounds((int) (25 * px), (int) (246 * py), (int) (190 * px), (int) (22 * py));
					painelInventario.setBounds((int) (25 * px), (int) (276 * py), (int) (190 * px), (int) (82 * py));

					// Centro
					scrollHistorico.setBounds((int) (230 * px), (int) (72 * py), (int) (257 * px), (int) (286 * py));

					// Inimigo
					lblNomeInimigo.setBounds((int) (502 * px), (int) (44 * py), (int) (190 * px), (int) (24 * py));
					imgInimigo.setBounds((int) (524 * px), (int) (72 * py), (int) (145 * px), (int) (140 * py));
					barraVidaInimigo.setBounds((int) (502 * px), (int) (218 * py), (int) (190 * px), (int) (22 * py));
					btnAvancarEncontro.setBounds((int) (502 * px), (int) (285 * py), (int) (190 * px), (int) (55 * py));

					// Botões
					btnVoltar.setBounds((int) (25 * px), (int) (380 * py), (int) (135 * px), (int) (42 * py));
					btnAtacar.setBounds((int) (185 * px), (int) (380 * py), (int) (155 * px), (int) (42 * py));
					btnDefender.setBounds((int) (360 * px), (int) (380 * py), (int) (155 * px), (int) (42 * py));
					btnHabilidade.setBounds((int) (535 * px), (int) (380 * py), (int) (157 * px), (int) (42 * py));
				}
			}
		});
	}

	/**
	 * Carrega uma partida (nova ou restaurada de arquivo) na tela e atualiza os elementos visuais.
	 */
	public void carregarPartida(controle.Partida novaPartida) {
		this.partidaControle = novaPartida;
		txtHistorico.setText("");

		if (novaPartida.getHistoricoDeAcoes() != null && !novaPartida.getHistoricoDeAcoes().isEmpty()) {
			for (String registro : novaPartida.getHistoricoDeAcoes()) {
				txtHistorico.append(registro + "\n\n");
			}
		} else {
			txtHistorico.setText("A batalha começou! Enfrente o " + novaPartida.getInimigoAtual().getNome() + ".\n\n");
		}
		txtHistorico.setCaretPosition(txtHistorico.getDocument().getLength());

		atualizarTela();
	}

	private void executarAcao(int acaoEscolhida) {
		if (partidaControle == null || partidaControle.isFinalizada()) {
			return;
		}

		try {
			String resultado = partidaControle.executarAcaoTurno(acaoEscolhida);
			txtHistorico.append(resultado + "\n\n");
			txtHistorico.setCaretPosition(txtHistorico.getDocument().getLength());

			atualizarTela();

			if (partidaControle.isFinalizada()) {
				int pontuacao = partidaControle.getJogadorPartida().getPontuacao();
				int turnos = partidaControle.getTurnoAtual();

				if (partidaControle.getStatusPartida() == StatusPartida.VITORIA) {
					JOptionPane.showMessageDialog(this,
							"Parabéns, " + partidaControle.getJogadorPartida().getNomeJogador()
									+ "! Você venceu todos os inimigos!\nTurnos: " + turnos
									+ "\nPontuação final: " + pontuacao,
							"Vitória!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this,
							"Você foi derrotado!\nTurnos: " + turnos
									+ "\nPontuação final: " + pontuacao,
							"Derrota", JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void avancarParaProximoEncontro() {
		if (partidaControle == null || partidaControle.isFinalizada()) {
			return;
		}

		try {
			String resultado = partidaControle.avancarEncontro();
			txtHistorico.append(resultado + "\n");
			txtHistorico.setCaretPosition(txtHistorico.getDocument().getLength());
			atualizarTela();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void atualizarTela() {
		if (partidaControle == null) {
			return;
		}

		Personagem heroi = partidaControle.getPersonagemPartida();
		Inimigo inimigo = partidaControle.getInimigoAtual();

		// Topo
		int encontroExibido = Math.min(partidaControle.getEncontroAtual(), 3);
		lblInfoEncontro.setText("Encontro " + encontroExibido + "/3  |  Turno " + partidaControle.getTurnoAtual());

		// Nome, imagem e recurso específico do personagem
		lblNomePersonagem.setText(partidaControle.getJogadorPartida().getNomeJogador() + " (" + heroi.getNome() + ")");
		imgPersonagem.setImagem(heroi.getCaminhoImagem());
		String nomeRecurso = heroi.getNomeRecurso();

		switch (nomeRecurso) {
		case "Força":
			barraRecursoPersonagem.setForeground(new Color(210, 120, 30)); // Laranja (Guerreiro)
			break;
		case "Mana":
			barraRecursoPersonagem.setForeground(new Color(40, 110, 210)); // Azul (Mago)
			break;
		case "Foco":
			barraRecursoPersonagem.setForeground(new Color(150, 75, 200)); // Roxo (Arqueiro)
			break;
		default:
			barraRecursoPersonagem.setForeground(new Color(100, 100, 100)); // Cor padrão
			break;
		}

		// Barras do personagem
		barraVidaPersonagem.setMaximum(heroi.getVidaMaxima());
		barraVidaPersonagem.setValue(heroi.getVidaAtual());
		barraVidaPersonagem.setString("Vida: " + heroi.getVidaAtual() + " / " + heroi.getVidaMaxima());

		barraRecursoPersonagem.setMaximum(heroi.getRecursoMaximo());
		barraRecursoPersonagem.setValue(heroi.getRecursoAtual());
		barraRecursoPersonagem.setString(nomeRecurso + ": " + heroi.getRecursoAtual() + " / " + heroi.getRecursoMaximo());

		// Inimigo atual (Goblin -> Esqueleto -> Bruxa)
		if (inimigo != null) {
			lblNomeInimigo.setText(inimigo.getNome());
			imgInimigo.setImagem(inimigo.getCaminhoImagem());
			barraVidaInimigo.setMaximum(inimigo.getVidaMaxima());
			barraVidaInimigo.setValue(inimigo.getVidaAtual());
			barraVidaInimigo.setString("Vida: " + inimigo.getVidaAtual() + " / " + inimigo.getVidaMaxima());
		}

		// Controle do estado dos botões conforme o momento da partida
		boolean finalizada = partidaControle.isFinalizada();
		boolean inimigoDerrotado = (inimigo != null && inimigo.getVidaAtual() == 0);
		boolean podeAvancar = (!finalizada && inimigoDerrotado && partidaControle.getEncontroAtual() < 3);

		// Combate ativo somente enquanto a partida não terminou e o inimigo está vivo
		boolean combateAtivo = (!finalizada && !inimigoDerrotado);
		btnAtacar.setEnabled(combateAtivo);
		btnDefender.setEnabled(combateAtivo);
		btnHabilidade.setEnabled(combateAtivo);

		// Inventário (Poções) liberado durante o combate e no intervalo entre encontros
		int qtdPocoes = (heroi.getInventario() != null) ? heroi.getInventario().getQtdPocoes() : 0;
		lblPocoes.setText("Poções: " + qtdPocoes);
		btnTomarPocao.setEnabled(!finalizada && qtdPocoes > 0);

		// Botão Avançar Encontro aparece apenas no intervalo entre encontros
		btnAvancarEncontro.setVisible(podeAvancar);
		btnAvancarEncontro.setEnabled(podeAvancar);
	}
}