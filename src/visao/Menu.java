package visao;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.GerenciadorDeSave;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private InserirNome telaInserirNome;
	private Partida telaPartida;
	private String pastaDados;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		// Pasta padrão inicial (pode ser alterada pelo usuário no botão "Pasta de Dados")
		this.pastaDados = System.getProperty("user.home");

		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Imagens/Icone.jpg")));
		setTitle("Guardiões da Relíquia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 512);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		// Dimensões e alinhamento padrão para todos os botões
		int xBase = 30;
		int larguraBase = 190;
		int alturaBase = 50;

		// Painel inicial com imagem de fundo responsiva
		PainelFundo telaInicial = new PainelFundo("/Imagens/MenuPrincipal.jpg");
		telaInicial.setLayout(null);
		contentPane.add(telaInicial, "telaInicial");

		// 1. NOVA PARTIDA
		BotaoImagem btnNovaPartida = new BotaoImagem("/Imagens/BotãoNovaPartida.png");
		btnNovaPartida.setBounds(xBase, 120, larguraBase, alturaBase);
		btnNovaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trocarTela("inserirNome");
			}
		});
		telaInicial.add(btnNovaPartida);

		// 2. CARREGAR PARTIDA (provisório em texto; quando criar o PNG, troque pela linha comentada)
		BotaoImagem btnCarregarPartida = new BotaoImagem("/Imagens/BotãoCarregarPartida.png");
		btnCarregarPartida.setBounds(xBase, 182, larguraBase, alturaBase);
		btnCarregarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarPartidaSalva();
			}
		});
		telaInicial.add(btnCarregarPartida);

		// 3. CONSULTAR RANKING
		BotaoImagem btnConsultarRanking = new BotaoImagem("/Imagens/BotãoConsultarRanking.png");
		btnConsultarRanking.setBounds(xBase, 244, larguraBase, alturaBase);
		btnConsultarRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ação do ranking (vamos conectar quando criarmos a tela de Ranking)
			}
		});
		telaInicial.add(btnConsultarRanking);

		// 4. PASTA DE DADOS (provisório em texto; quando criar o PNG, troque pela linha comentada)
		BotaoImagem btnConfigurarPasta = new BotaoImagem("/Imagens/BotãoPastaDados.png");
		btnConfigurarPasta.setBounds(xBase, 306, larguraBase, alturaBase);
		btnConfigurarPasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configurarPastaDados();
			}
		});
		telaInicial.add(btnConfigurarPasta);

		// 5. SAIR DO JOGO
		BotaoImagem btnSairDoJogo = new BotaoImagem("/Imagens/BotãoSair.png");
		btnSairDoJogo.setBounds(xBase, 368, larguraBase, alturaBase);
		btnSairDoJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		telaInicial.add(btnSairDoJogo);

		// Outras telas do CardLayout recebendo a referência deste Menu
		telaInserirNome = new InserirNome(this);
		contentPane.add(telaInserirNome, "inserirNome");

		JPanel novaPartida = new NovaPartida(this);
		contentPane.add(novaPartida, "novaPartida");

		telaPartida = new Partida(this);
		contentPane.add(telaPartida, "partida");

		// Reposiciona e redimensiona todos os botões proporcionalmente com o mesmo tamanho
		telaInicial.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int larguraAtual = telaInicial.getWidth();
				int alturaAtual = telaInicial.getHeight();

				if (larguraAtual > 0 && alturaAtual > 0) {
					double proporcaoX = larguraAtual / 717.0;
					double proporcaoY = alturaAtual / 471.0;

					int x = (int) (xBase * proporcaoX);
					int largura = (int) (larguraBase * proporcaoX);
					int altura = (int) (alturaBase * proporcaoY);

					btnNovaPartida.setBounds(x, (int) (120 * proporcaoY), largura, altura);
					btnCarregarPartida.setBounds(x, (int) (182 * proporcaoY), largura, altura);
					btnConsultarRanking.setBounds(x, (int) (244 * proporcaoY), largura, altura);
					btnConfigurarPasta.setBounds(x, (int) (306 * proporcaoY), largura, altura);
					btnSairDoJogo.setBounds(x, (int) (368 * proporcaoY), largura, altura);
				}
			}
		});
	}

	public String getPastaDados() {
		return pastaDados;
	}

	public void setPastaDados(String pastaDados) {
		if (pastaDados != null && !pastaDados.trim().isEmpty()) {
			this.pastaDados = pastaDados;
		}
	}

	public void configurarPastaDados() {
		JFileChooser seletor = new JFileChooser(new File(pastaDados));
		seletor.setDialogTitle("Selecione a pasta para salvar e carregar os dados do jogo");
		seletor.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		seletor.setAcceptAllFileFilterUsed(false);

		int resultado = seletor.showOpenDialog(this);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			File pastaSelecionada = seletor.getSelectedFile();
			if (pastaSelecionada != null && pastaSelecionada.isDirectory()) {
				this.pastaDados = pastaSelecionada.getAbsolutePath();
				JOptionPane.showMessageDialog(this,
						"Pasta de dados definida para:\n" + this.pastaDados,
						"Pasta Configurada", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,
						"Selecione uma pasta válida.",
						"Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void carregarPartidaSalva() {
		try {
			GerenciadorDeSave gerenciador = new GerenciadorDeSave(pastaDados);
			controle.Partida partidaCarregada = gerenciador.carregarPartida();
			telaPartida.carregarPartida(partidaCarregada);
			trocarTela("partida");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
					"Não foi possível carregar a partida:\n" + ex.getMessage(),
					"Erro ao Carregar", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void trocarTela(String nomeTela) {
		CardLayout cl = (CardLayout) contentPane.getLayout();
		cl.show(contentPane, nomeTela);
	}

	public void iniciarPartida(int opcaoDeClasse) {
		try {
			String nomeJogador = telaInserirNome.getNomeJogador();
			controle.Partida partidaControle = new controle.Partida(nomeJogador, opcaoDeClasse);
			telaPartida.carregarPartida(partidaControle);
			trocarTela("partida");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
					"Erro ao iniciar partida: " + ex.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}