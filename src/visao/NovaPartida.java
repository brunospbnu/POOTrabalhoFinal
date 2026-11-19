package visao;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

		JLabel tituloDoMenu = new JLabel("Escolha uma classe para sua aventura:");
		tituloDoMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tituloDoMenu.setHorizontalAlignment(SwingConstants.CENTER);
		tituloDoMenu.setBounds(227, 63, 352, 48);
		add(tituloDoMenu);

		JButton guerreiro = new JButton("Guerreiro");
		guerreiro.setBounds(37, 77, 145, 40);
		add(guerreiro);

		JButton arqueiro = new JButton("Arqueiro");
		arqueiro.setBounds(37, 143, 145, 40);
		add(arqueiro);

		JButton mago = new JButton("Mago");
		mago.setBounds(37, 207, 145, 40);
		add(mago);

		JButton voltar = new JButton("Voltar");
		voltar.setBounds(37, 277, 145, 40);
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
		imagemDeFundo.setBounds(0, 0, 1088, 463);
		add(imagemDeFundo);

		ImageIcon iconeOriginal = new ImageIcon(Menu.class.getResource("/Imagens/MenuPrincipal.jpg"));
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

					// 2. Calcula a proporção da tela atual em relação ao tamanho base do
					// WindowBuilder
					// O tamanho base aproximado do seu contentPane original era ~717x471
					double proporcaoX = larguraAtual / 717.0;
					double proporcaoY = alturaAtual / 471.0;

					// 3. Reposiciona e redimensiona os botões multiplicando pela proporção

					// 227, 63, 352, 48

					tituloDoMenu.setBounds((int) (227 * proporcaoX), (int) (63 * proporcaoY), (int) (352 * proporcaoX),
							(int) (48 * proporcaoY));

					// 37, 77, 145, 40
					guerreiro.setBounds((int) (37 * proporcaoX), (int) (77 * proporcaoY), (int) (145 * proporcaoX),
							(int) (40 * proporcaoY));

					// 37, 143, 145, 40
					arqueiro.setBounds((int) (37 * proporcaoX), (int) (143 * proporcaoY), (int) (145 * proporcaoX),
							(int) (40 * proporcaoY));

					// 37, 207, 145, 40
					mago.setBounds((int) (37 * proporcaoX), (int) (207 * proporcaoY), (int) (145 * proporcaoX),
							(int) (40 * proporcaoY));

					// 37, 277, 145, 40
					voltar.setBounds((int) (37 * proporcaoX), (int) (277 * proporcaoY), (int) (145 * proporcaoX),
							(int) (40 * proporcaoY));

				}
			}
		});

	}
}
