package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Menu() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Imagens/Icone.jpg")));
		setTitle("Guardiões da Relíquia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		// -----------------------------------

		JPanel telaInicial = new JPanel();
		contentPane.add(telaInicial, "telaInicial"); // mudei para telaInicial para deixar mais simples, nome de antes:
														// name_21736119527300
		telaInicial.setLayout(null);

		// NOVA PARTIDA
		JButton btnNovaPartida = new JButton("");
		// Carrega a imagem original para uma variável do tipo ImageIcon
		ImageIcon imagemNovaP = new ImageIcon(Menu.class.getResource("/Imagens/BotãoNovaPartida.png"));

		// Isso aqui vai remover aquele fundo retângular, deixando só o botão
		// personalizado

		btnNovaPartida.setContentAreaFilled(false);
		btnNovaPartida.setBorderPainted(false);
		btnNovaPartida.setFocusPainted(false);

		// Redimensiona a imagem do botão:
		Image imagemRedimensionada = imagemNovaP.getImage().getScaledInstance(230, 110, Image.SCALE_SMOOTH);

		btnNovaPartida.setIcon(new ImageIcon(imagemRedimensionada));
		btnNovaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (contentPane.getLayout());
				cl.show(contentPane, "novaPartida");
			}
		});
		btnNovaPartida.setBounds(10, 113, 230, 110);
		telaInicial.add(btnNovaPartida);

		// SAIR DO JOGO
		JButton btnSairDoJogo = new JButton("");

		ImageIcon imagemSair = new ImageIcon(Menu.class.getResource("/Imagens/BotãoSair.png"));

		btnSairDoJogo.setContentAreaFilled(false);
		btnSairDoJogo.setBorderPainted(false);
		btnSairDoJogo.setFocusPainted(false);

		Image imagemRedimensionada3 = imagemSair.getImage().getScaledInstance(140, 40, Image.SCALE_SMOOTH);

		btnSairDoJogo.setIcon(new ImageIcon(imagemRedimensionada3));
		btnSairDoJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSairDoJogo.setBounds(63, 299, 140, 40);
		telaInicial.add(btnSairDoJogo);

		// CONSULTAR RANKING--------
		JButton btnConsultarRanking = new JButton("");
		ImageIcon imagemCR = new ImageIcon(Menu.class.getResource("/Imagens/BotãoConsultarRanking.png"));

		btnConsultarRanking.setContentAreaFilled(false);
		btnConsultarRanking.setBorderPainted(false);
		btnConsultarRanking.setFocusPainted(false);

		Image imagemRedimensionada2 = imagemCR.getImage().getScaledInstance(180, 60, Image.SCALE_SMOOTH);

		btnConsultarRanking.setIcon(new ImageIcon(imagemRedimensionada2));

		btnConsultarRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnConsultarRanking.setBounds(37, 218, 180, 60);
		telaInicial.add(btnConsultarRanking);

		JLabel imagemDeFundo = new JLabel("");
		imagemDeFundo.setBounds(0, 0, 709, 463);
		telaInicial.add(imagemDeFundo);

		JPanel novaPartida = new NovaPartida();
		contentPane.add(novaPartida, "novaPartida");

		// 1. Carrega a imagem original APENAS UMA VEZ na memória
		ImageIcon iconeOriginal = new ImageIcon(Menu.class.getResource("/Imagens/MenuPrincipal.jpg"));
		Image imagemOriginal = iconeOriginal.getImage();

		// Evento que escuta o redimensionamento da janela
		contentPane.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent e) {
				int larguraAtual = contentPane.getWidth();
				int alturaAtual = contentPane.getHeight();

				if (larguraAtual > 0 && alturaAtual > 0) {
					// 1. Redimensiona a imagem de fundo
					imagemDeFundo.setBounds(0, 0, larguraAtual, alturaAtual);
					Image imagemAjustada = imagemOriginal.getScaledInstance(larguraAtual, alturaAtual,
							Image.SCALE_SMOOTH);
					imagemDeFundo.setIcon(new ImageIcon(imagemAjustada));

					// 2. Calcula a proporção da tela atual em relação ao tamanho base do
					// WindowBuilder
					// O tamanho base aproximado do seu contentPane original era ~717x471
					double proporcaoX = larguraAtual / 717.0;
					double proporcaoY = alturaAtual / 471.0;

					// 3. Reposiciona e redimensiona os botões multiplicando pela proporção
					// Valores base originais: btnNewButton(37, 143, 145, 40)
					btnNovaPartida.setBounds((int) (37 * proporcaoX), (int) (143 * proporcaoY),
							(int) (145 * proporcaoX), (int) (40 * proporcaoY));

					// Valores base originais: btnConsultarRanking(37, 218, 145, 40)
					btnConsultarRanking.setBounds((int) (37 * proporcaoX), (int) (218 * proporcaoY),
							(int) (145 * proporcaoX), (int) (40 * proporcaoY));

					// Valores base originais: btnSairDoJogo(37, 300, 145, 40)
					btnSairDoJogo.setBounds((int) (37 * proporcaoX), (int) (300 * proporcaoY), (int) (145 * proporcaoX),
							(int) (40 * proporcaoY));

					// Redissionamento das imagens de cada botão:

					// Botão de nova partida:
					int larguraNovaP = (int) (230 * proporcaoX);
					int alturaNovaP = (int) (110 * proporcaoY);
					btnNovaPartida.setBounds((int) (10 * proporcaoX), (int) (113 * proporcaoY), larguraNovaP,
							alturaNovaP);
					Image imgNovaP = imagemNovaP.getImage().getScaledInstance(Math.max(1, larguraNovaP),
							Math.max(1, alturaNovaP), Image.SCALE_SMOOTH);
					btnNovaPartida.setIcon(new ImageIcon(imgNovaP));

					// Botão de consultar Ranking:
					int larguraConsultar = (int) (180 * proporcaoX);
					int alturaConsultar = (int) (60 * proporcaoY);
					btnConsultarRanking.setBounds((int) (37 * proporcaoX), (int) (218 * proporcaoY), larguraConsultar,
							alturaConsultar);
					Image imgConsultar = imagemCR.getImage().getScaledInstance(Math.max(1, larguraConsultar),
							Math.max(1, alturaConsultar), Image.SCALE_SMOOTH);
					btnConsultarRanking.setIcon(new ImageIcon(imgConsultar));

					// Botão de sair:
					int larguraSair = (int) (140 * proporcaoX);
					int alturaSair = (int) (40 * proporcaoY);
					btnSairDoJogo.setBounds((int) (63 * proporcaoX), (int) (299 * proporcaoY), larguraSair, alturaSair);
					Image imgSair = imagemSair.getImage().getScaledInstance(Math.max(1, larguraSair),
							Math.max(1, alturaSair), Image.SCALE_SMOOTH);
					btnSairDoJogo.setIcon(new ImageIcon(imgSair));

				}
			}
		});
	}
}
