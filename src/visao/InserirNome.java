package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InserirNome extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtEscreverONome;

	// Variável estática para guardar o nome e usá-lo nas outras classes
	public static String nomeJogador = "";

	public InserirNome() {
		setLayout(null);

		// 1. Imagem da Placa "Informe seu nome"
		JLabel inserirNome = new JLabel("");
		ImageIcon imgNome = new ImageIcon(InserirNome.class.getResource("/Imagens/ImagemInserirNome.png"));
		// Ajustado para o tamanho base real da placa (ex: 450x160 em vez de 44x12)
		Image redimensionarNome = imgNome.getImage().getScaledInstance(450, 160, Image.SCALE_SMOOTH);
		inserirNome.setIcon(new ImageIcon(redimensionarNome));
		inserirNome.setBounds(134, 120, 450, 160);
		add(inserirNome);

		// 2. Campo de Texto
		txtEscreverONome = new JTextField();
		txtEscreverONome.setText("Escrever o nome aqui");
		txtEscreverONome.setBounds(180, 200, 358, 45); // Posicionado no meio da placa
		txtEscreverONome.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtEscreverONome.setHorizontalAlignment(SwingConstants.CENTER);
		txtEscreverONome.setOpaque(false);
		txtEscreverONome.setBorder(null);
		txtEscreverONome.setForeground(Color.YELLOW);
		add(txtEscreverONome);
		txtEscreverONome.setColumns(10);

		// 3. Botão Continuar
		JButton continuar = new JButton("");
		// IMPORTANTE: Adicionada a extensão .png no caminho da imagem
		ImageIcon imgContinuar = new ImageIcon(InserirNome.class.getResource("/Imagens/BotãoContinuar.png"));
		Image redimensionarContinuar = imgContinuar.getImage().getScaledInstance(145, 40, Image.SCALE_SMOOTH);

		continuar.setContentAreaFilled(false);
		continuar.setBorderPainted(false);
		continuar.setFocusPainted(false);

		continuar.setIcon(new ImageIcon(redimensionarContinuar));
		continuar.setBounds(286, 310, 145, 40);
		add(continuar);

		// Ação do Botão
		continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pega o texto digitado e guarda na variável
				nomeJogador = txtEscreverONome.getText();

				// Validação simples caso esteja vazio
				if (nomeJogador.trim().isEmpty() || nomeJogador.equals("Escrever o nome aqui")) {
					nomeJogador = "Aventureiro";
				}

				// Transição com CardLayout para a próxima tela
				java.awt.Container containerPai = InserirNome.this.getParent();
				if (containerPai != null && containerPai.getLayout() instanceof java.awt.CardLayout) {
					java.awt.CardLayout cl = (java.awt.CardLayout) containerPai.getLayout();
					cl.show(containerPai, "novaPartida");
				}
			}
		});

		// 4. Imagem de Fundo
		JLabel imagemFundo = new JLabel("");
		imagemFundo.setBounds(0, 0, 717, 471);
		add(imagemFundo);

		ImageIcon iconeOriginal = new ImageIcon(Menu.class.getResource("/Imagens/SomenteFundo.jpg"));
		Image imagemOriginal = iconeOriginal.getImage();

		// 5. Listener de Redimensionamento (Estrutura corrigida)
		Menu.contentPane.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				int larguraAtual = Menu.contentPane.getWidth();
				int alturaAtual = Menu.contentPane.getHeight();

				if (larguraAtual > 0 && alturaAtual > 0) {
					// Redimensiona o fundo
					imagemFundo.setBounds(0, 0, larguraAtual, alturaAtual);
					Image imagemAjustada = imagemOriginal.getScaledInstance(larguraAtual, alturaAtual,
							Image.SCALE_SMOOTH);
					imagemFundo.setIcon(new ImageIcon(imagemAjustada)); // Corrigido o erro de digitação "imageeFundo"

					// Garante que o fundo fica na camada mais abaixo
					InserirNome.this.setComponentZOrder(imagemFundo, InserirNome.this.getComponentCount() - 1);

					// Proporções
					double proporcaoX = larguraAtual / 717.0;
					double proporcaoY = alturaAtual / 471.0;

					// Redimensiona e reposiciona a placa
					int larguraPlaca = (int) (450 * proporcaoX);
					int alturaPlaca = (int) (160 * proporcaoY);
					inserirNome.setBounds((int) (134 * proporcaoX), (int) (120 * proporcaoY), larguraPlaca,
							alturaPlaca);
					Image imgPlacaAjustada = imgNome.getImage().getScaledInstance(Math.max(1, larguraPlaca),
							Math.max(1, alturaPlaca), Image.SCALE_SMOOTH);

					inserirNome.setIcon(new ImageIcon(imgPlacaAjustada));

					// Redimensiona e reposiciona o campo de texto
					txtEscreverONome.setBounds((int) (180 * proporcaoX), (int) (200 * proporcaoY),
							(int) (358 * proporcaoX), (int) (45 * proporcaoY));

					// Redimensiona e reposiciona o botão continuar
					int larguraBotao = (int) (145 * proporcaoX);
					int alturaBotao = (int) (40 * proporcaoY);
					continuar.setBounds((int) (286 * proporcaoX), (int) (310 * proporcaoY), larguraBotao, alturaBotao);
					Image imgAvancarAjustada = imgContinuar.getImage().getScaledInstance(Math.max(1, larguraBotao),
							Math.max(1, alturaBotao), Image.SCALE_SMOOTH);
					continuar.setIcon(new ImageIcon(imgAvancarAjustada));
				}
			}
		});
	}
}