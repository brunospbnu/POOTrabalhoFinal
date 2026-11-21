package visao;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class NovaPartida extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public NovaPartida() {

		setLayout(null);
		// Legenda principal
		JLabel tituloDoMenu = new JLabel("");
		ImageIcon legenda = new ImageIcon(NovaPartida.class.getResource("/Imagens/LegendaEscolherClasse.png"));

		Image redimensionarLegenda = legenda.getImage().getScaledInstance(352, 80, Image.SCALE_SMOOTH);

		tituloDoMenu.setIcon(new ImageIcon(redimensionarLegenda));
		tituloDoMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tituloDoMenu.setHorizontalAlignment(SwingConstants.CENTER);
		tituloDoMenu.setBounds(202, 18, 352, 80);
		add(tituloDoMenu);

		// Botão de escolha do cavaleiro
		JButton guerreiro = new JButton("");
		ImageIcon escolher1 = new ImageIcon(NovaPartida.class.getResource("/Imagens/BotãoEscolher.png"));

		Image redimensionarEscolher1 = escolher1.getImage().getScaledInstance(145, 40, Image.SCALE_SMOOTH);

		guerreiro.setContentAreaFilled(false);
		guerreiro.setBorderPainted(false);
		guerreiro.setFocusPainted(false);

		guerreiro.setIcon(new ImageIcon(redimensionarEscolher1));
		guerreiro.setBounds(37, 268, 145, 40);
		add(guerreiro);

		// Botão de escolha da arqueira
		JButton arqueiro = new JButton("");
		ImageIcon escolher2 = new ImageIcon(NovaPartida.class.getResource("/Imagens/BotãoEscolher.png"));

		Image redimensionarEscolher2 = escolher2.getImage().getScaledInstance(145, 40, Image.SCALE_SMOOTH);

		arqueiro.setContentAreaFilled(false);
		arqueiro.setBorderPainted(false);
		arqueiro.setFocusPainted(false);

		arqueiro.setIcon(new ImageIcon(redimensionarEscolher2));
		arqueiro.setBounds(327, 268, 145, 40);
		add(arqueiro);

		// Botão de escolha do mago
		JButton mago = new JButton("");
		ImageIcon escolher3 = new ImageIcon(NovaPartida.class.getResource("/Imagens/BotãoEscolher.png"));

		Image redimensionarEscolher3 = escolher3.getImage().getScaledInstance(145, 40, Image.SCALE_SMOOTH);

		mago.setContentAreaFilled(false);
		mago.setBorderPainted(false);
		mago.setFocusPainted(false);

		mago.setIcon(new ImageIcon(redimensionarEscolher3));
		mago.setBounds(622, 268, 145, 40);
		add(mago);

		// Botão de voltar
		JButton voltar = new JButton("");
		ImageIcon imagemV = new ImageIcon(NovaPartida.class.getResource("/Imagens/BotãoVoltar.png"));

		Image redimensionarImagemV = imagemV.getImage().getScaledInstance(145, 40, Image.SCALE_SMOOTH);

		voltar.setContentAreaFilled(false);
		voltar.setBorderPainted(false);
		voltar.setFocusPainted(false);

		voltar.setIcon(new ImageIcon(redimensionarImagemV));
		voltar.setBounds(37, 370, 145, 40);
		add(voltar);

		voltar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				java.awt.Container containerPai = NovaPartida.this.getParent();
				if (containerPai != null && containerPai.getLayout() instanceof java.awt.CardLayout) {
					java.awt.CardLayout cl = (java.awt.CardLayout) containerPai.getLayout();

					cl.show(containerPai, "telaInicial");
				}
			}
		});

		JLabel imagemDeFundo = new JLabel("");
		imagemDeFundo.setBounds(0, 471, 1088, 68);
		add(imagemDeFundo);

		// Imagem do Cavaleiro
		JLabel cavaleiro = new JLabel("");
		ImageIcon imagemC = new ImageIcon(NovaPartida.class.getResource("/Imagens/Cavaleiro.png"));

		Image redimensionarC = imagemC.getImage().getScaledInstance(145, 140, Image.SCALE_SMOOTH);

		cavaleiro.setIcon(new ImageIcon(redimensionarC));
		cavaleiro.setBounds(37, 115, 145, 140);
		add(cavaleiro);

		// JLabel lblNewLabel_1 = new JLabel("");
		// lblNewLabel_1.setIcon(new
		// ImageIcon(NovaPartida.class.getResource("/Imagens/Cavaleiro.png")));
		// lblNewLabel_1.setBounds(94, 150, 44, 12);
		// add(lblNewLabel_1);
		// Deixe isso comentado por enquanto

		// Imagem da Arqueira
		JLabel arqueira = new JLabel("");
		ImageIcon imagemA = new ImageIcon(NovaPartida.class.getResource("/Imagens/Arqueira.png"));

		Image redimensionarA = imagemA.getImage().getScaledInstance(145, 140, Image.SCALE_SMOOTH);

		arqueira.setIcon(new ImageIcon(redimensionarA));
		arqueira.setBounds(286, 115, 145, 140);
		add(arqueira);

		// Imagem do mago
		JLabel magoPNG = new JLabel("");
		ImageIcon imagemM = new ImageIcon(NovaPartida.class.getResource("/Imagens/Mago.png"));

		Image redimensionarM = imagemM.getImage().getScaledInstance(145, 140, Image.SCALE_SMOOTH);

		magoPNG.setIcon(new ImageIcon(redimensionarM));
		magoPNG.setBounds(535, 115, 145, 140);
		add(magoPNG);

		ImageIcon iconeOriginal = new ImageIcon(Menu.class.getResource("/Imagens/SomenteFundo.jpg"));
		Image imagemOriginal = iconeOriginal.getImage();

		// Evento que escuta o redimensionamento da janela
		Menu.contentPane.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				int larguraAtual = Menu.contentPane.getWidth();
				int alturaAtual = Menu.contentPane.getHeight();

				if (larguraAtual > 0 && alturaAtual > 0) {
					// 1. Redimensiona a imagem de fundo
					imagemDeFundo.setBounds(0, 0, larguraAtual, alturaAtual);
					Image imagemAjustada = imagemOriginal.getScaledInstance(larguraAtual, alturaAtual,
							Image.SCALE_SMOOTH);
					imagemDeFundo.setIcon(new ImageIcon(imagemAjustada));

					// Isso vai garantir que a imagem de fundo não fique por cima de qualquer outro
					// PNG

					NovaPartida.this.setComponentZOrder(imagemDeFundo, NovaPartida.this.getComponentCount() - 1);

					// 2. Calcula a proporção da tela atual em relação ao tamanho base do
					// WindowBuilder
					// O tamanho base aproximado do seu contentPane original era ~717x471
					double proporcaoX = larguraAtual / 717.0;
					double proporcaoY = alturaAtual / 471.0;

					// 3. Reposiciona e redimensiona os botões multiplicando pela proporção

					// Medidas antigas: 227, 63, 352, 48

					// Tive que fazer algumas mudanças aqui para ficar bem redimensionado com as
					// imagens. As medidas antigas estão logo acima
					tituloDoMenu.setBounds((int) (180 * proporcaoX), (int) (18 * proporcaoY), (int) (352 * proporcaoX),
							(int) (80 * proporcaoY));

					// É necessário colocar as variáveis dos PNGs aqui também, se não ao expandir o
					// tamanho da tela elas acabam mantendo o tamanho de quando o painel está
					// minimizado. Dessa forma eles acompanham a tela também

					// Legenda
					int larguraLegenda = (int) (352 * proporcaoX);
					int alturaLegenda = (int) (80 * proporcaoY);
					tituloDoMenu.setBounds((int) (180 * proporcaoX), (int) (18 * proporcaoY), larguraLegenda,
							alturaLegenda);
					Image imgLegendaAjustada = legenda.getImage().getScaledInstance(Math.max(1, larguraLegenda),
							Math.max(1, alturaLegenda), Image.SCALE_SMOOTH);
					tituloDoMenu.setIcon(new ImageIcon(imgLegendaAjustada));

					// Escolha do cavaleiro
					int larguraEscolha1 = (int) (145 * proporcaoX);
					int alturaEscolha1 = (int) (40 * proporcaoY);
					guerreiro.setBounds((int) (37 * proporcaoX), (int) (268 * proporcaoY), larguraEscolha1,
							alturaEscolha1);
					Image imgEscolha1Ajustada = escolher1.getImage().getScaledInstance(Math.max(1, larguraEscolha1),
							Math.max(1, alturaEscolha1), Image.SCALE_SMOOTH);
					guerreiro.setIcon(new ImageIcon(imgEscolha1Ajustada));

					// Escolha da arqueira
					int larguraEscolha2 = (int) (145 * proporcaoX);
					int alturaEscolha2 = (int) (40 * proporcaoY);
					arqueiro.setBounds((int) (327 * proporcaoX), (int) (268 * proporcaoY), larguraEscolha2,
							alturaEscolha2);
					Image imgEscolha2Ajustada = escolher2.getImage().getScaledInstance(Math.max(1, larguraEscolha2),
							Math.max(1, alturaEscolha2), Image.SCALE_SMOOTH);
					arqueiro.setIcon(new ImageIcon(imgEscolha2Ajustada));

					// Escolha do mago
					int larguraEscolha3 = (int) (145 * proporcaoX);
					int alturaEscolha3 = (int) (40 * proporcaoY);
					mago.setBounds((int) (622 * proporcaoX), (int) (268 * proporcaoY), larguraEscolha3, alturaEscolha3);
					Image imgEscolha3Ajustada = escolher3.getImage().getScaledInstance(Math.max(1, larguraEscolha3),
							Math.max(1, alturaEscolha3), Image.SCALE_SMOOTH);
					mago.setIcon(new ImageIcon(imgEscolha3Ajustada));

					// Voltar
					int larguraVoltar = (int) (145 * proporcaoX);
					int alturaVoltar = (int) (40 * proporcaoY);
					voltar.setBounds((int) (37 * proporcaoX), (int) (370 * proporcaoY), larguraVoltar, alturaVoltar);
					Image imgVoltar = imagemV.getImage().getScaledInstance(Math.max(1, larguraVoltar),
							Math.max(1, alturaVoltar), Image.SCALE_SMOOTH);
					voltar.setIcon(new ImageIcon(imgVoltar));

					// Cavaleiro
					int larguraCavaleiro = (int) (145 * proporcaoX);
					int alturaCavaleiro = (int) (140 * proporcaoY);
					cavaleiro.setBounds((int) (37 * proporcaoX), (int) (115 * proporcaoY), larguraCavaleiro,
							alturaCavaleiro);
					Image imgCavaleiroAjustada = imagemC.getImage().getScaledInstance(Math.max(1, larguraCavaleiro),
							Math.max(1, alturaCavaleiro), Image.SCALE_SMOOTH);
					cavaleiro.setIcon(new ImageIcon(imgCavaleiroAjustada));

					// Arqueira
					int larguraArqueira = (int) (145 * proporcaoX);
					int alturaArqueira = (int) (140 * proporcaoY);
					arqueira.setBounds((int) (286 * proporcaoX), (int) (115 * proporcaoY), larguraArqueira,
							alturaArqueira);
					Image imgArqueiraAjustada = imagemA.getImage().getScaledInstance(Math.max(1, larguraArqueira),
							Math.max(1, alturaArqueira), Image.SCALE_SMOOTH);
					arqueira.setIcon(new ImageIcon(imgArqueiraAjustada));

					// Mago
					int larguraMago = (int) (145 * proporcaoX);
					int alturaMago = (int) (140 * proporcaoY);
					magoPNG.setBounds((int) (535 * proporcaoX), (int) (115 * proporcaoY), larguraMago, alturaMago);
					Image imgMagoAjustada = imagemM.getImage().getScaledInstance(Math.max(1, larguraMago),
							Math.max(1, alturaMago), Image.SCALE_SMOOTH);
					magoPNG.setIcon(new ImageIcon(imgMagoAjustada));

					// Eu não tenho certeza se essa parte de baixo pode conflitar com as proporções
					// e ajustes feitos acima, então é melhor não apagar essa parte
					// 37, 143, 145, 40
					guerreiro.setBounds((int) (37 * proporcaoX), (int) (268 * proporcaoY), (int) (145 * proporcaoX),
							(int) (40 * proporcaoY));

					// 37, 218, 145, 40
					arqueiro.setBounds((int) (286 * proporcaoX), (int) (268 * proporcaoY), (int) (145 * proporcaoX),
							(int) (40 * proporcaoY));

					// 37, 300, 145, 40
					mago.setBounds((int) (535 * proporcaoX), (int) (268 * proporcaoY), (int) (145 * proporcaoX),
							(int) (40 * proporcaoY));

					// 37, 370, 145, 40
					voltar.setBounds((int) (37 * proporcaoX), (int) (370 * proporcaoY), (int) (145 * proporcaoX),
							(int) (40 * proporcaoY));

				}
			}
		});

	}
}
