package visao;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private InserirNome telaInserirNome;
	private Partida telaPartida;

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
		int alturaBase = 60;

		// Painel inicial com imagem de fundo responsiva
		PainelFundo telaInicial = new PainelFundo("/Imagens/MenuPrincipal.jpg");
		telaInicial.setLayout(null);
		contentPane.add(telaInicial, "telaInicial");

		// NOVA PARTIDA
		BotaoImagem btnNovaPartida = new BotaoImagem("/Imagens/BotãoNovaPartida.png");
		btnNovaPartida.setBounds(xBase, 130, larguraBase, alturaBase);
		btnNovaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trocarTela("inserirNome");
			}
		});
		telaInicial.add(btnNovaPartida);

		// CONSULTAR RANKING
		BotaoImagem btnConsultarRanking = new BotaoImagem("/Imagens/BotãoConsultarRanking.png");
		btnConsultarRanking.setBounds(xBase, 210, larguraBase, alturaBase);
		btnConsultarRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ação do ranking
			}
		});
		telaInicial.add(btnConsultarRanking);

		// SAIR DO JOGO
		BotaoImagem btnSairDoJogo = new BotaoImagem("/Imagens/BotãoSair.png");
		btnSairDoJogo.setBounds(xBase, 290, larguraBase, alturaBase);
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

					btnNovaPartida.setBounds(x, (int) (130 * proporcaoY), largura, altura);
					btnConsultarRanking.setBounds(x, (int) (210 * proporcaoY), largura, altura);
					btnSairDoJogo.setBounds(x, (int) (290 * proporcaoY), largura, altura);
				}
			}
		});
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
			javax.swing.JOptionPane.showMessageDialog(this,
					"Erro ao iniciar partida: " + ex.getMessage(),
					"Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
		}
	}
}