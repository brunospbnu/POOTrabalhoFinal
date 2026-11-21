package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import excecoes.NomeInvalidoException;
import modelo.Jogador;

public class InserirNome extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtEscreverONome;
	// Variável para guardar o nome.
	public static String nomeJogador = "";

	public InserirNome() {
		setLayout(null);

		// 1. Rótulo de Erro
		JLabel erro = new JLabel("");
		erro.setBounds(180, 248, 358, 25);
		erro.setFont(new Font("Tahoma", Font.BOLD, 12));
		erro.setForeground(new Color(255, 90, 90));
		erro.setHorizontalAlignment(SwingConstants.CENTER);
		add(erro);

		// 2. Botão Continuar
		JButton continuar = new JButton("");

		ImageIcon imgContinuar = new ImageIcon(InserirNome.class.getResource("/Imagens/BotãoContinuar.png"));
		Image redimensionarContinuar = imgContinuar.getImage().getScaledInstance(145, 40, Image.SCALE_SMOOTH);

		continuar.setContentAreaFilled(false);
		continuar.setBorderPainted(false);
		continuar.setFocusPainted(false);

		continuar.setIcon(new ImageIcon(redimensionarContinuar));
		continuar.setBounds(550, 370, 180, 50);
		continuar.setEnabled(false);
		add(continuar);

		// Ação do Botão
		continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pega o texto digitado e guarda na variável
				String nomeDigitado = txtEscreverONome.getText().trim();

				// Validação simples caso esteja vazio
				// if (nomeDigitado.equals("Escrever o nome aqui")) {
				// nomeDigitado = "Aventureiro";
				// }

				try {
					Jogador jogadorTemp = new Jogador(nomeDigitado, 0);
					nomeJogador = jogadorTemp.getNomeJogador();
					erro.setText("");

					// Transição com CardLayout para a próxima tela
					java.awt.Container containerPai = InserirNome.this.getParent();
					if (containerPai != null && containerPai.getLayout() instanceof java.awt.CardLayout) {
						java.awt.CardLayout cl = (java.awt.CardLayout) containerPai.getLayout();
						cl.show(containerPai, "novaPartida");
					}
				} catch (NomeInvalidoException ex) {
					erro.setText(ex.getMessage());
					continuar.setEnabled(false);
				}
			}

		});

		// 3. Imagem da Placa "Informe seu nome"
		JLabel inserirNome = new JLabel("");
		ImageIcon imgNome = new ImageIcon(InserirNome.class.getResource("/Imagens/ImagemInserirNome.png"));
		// Ajustado para o tamanho base real da placa (ex: 450x160 em vez de 44x12)
		Image redimensionarNome = imgNome.getImage().getScaledInstance(450, 160, Image.SCALE_SMOOTH);
		
				// 4. Campo de Texto
				txtEscreverONome = new JTextField() {
					@Override
					protected void paintComponent(java.awt.Graphics g) {
						super.paintComponent(g);
					}
		
					@Override
					public boolean isOpaque() {
						return false;
					}
				};
				txtEscreverONome.setText("Escrever o nome aqui");
				txtEscreverONome.setBounds(218, 195, 322, 25); // Posicionado no meio da placa
				txtEscreverONome.setFont(new Font("Tahoma", Font.BOLD, 16));
				txtEscreverONome.setHorizontalAlignment(SwingConstants.CENTER);
				txtEscreverONome.setOpaque(true);
				txtEscreverONome.setBackground(new Color(45, 20, 10));
				txtEscreverONome.setForeground(Color.YELLOW);
				txtEscreverONome.setCaretColor(Color.WHITE);
				txtEscreverONome.setBorder(null);
				
						txtEscreverONome.addFocusListener(new FocusListener() {
				
							public void focusGained(FocusEvent e) {
								if (txtEscreverONome.getText().equals("Escrever o nome aqui")) {
									txtEscreverONome.setText("");
									erro.setText("");
								}
							}
				
							public void focusLost(FocusEvent e) {
								if (txtEscreverONome.getText().trim().isEmpty()) {
									txtEscreverONome.setText("Escrever o nome aqui");
									continuar.setEnabled(false);
								}
							}
						});
						
								// Isso aqui vigia o texto em tempo real
								txtEscreverONome.getDocument().addDocumentListener(new DocumentListener() {
						
									private void checarTexto() {
										String texto = txtEscreverONome.getText().trim();
						
										if (texto.isEmpty() || texto.equals("Escrever o nome aqui") || texto.length() < 3) {
											continuar.setEnabled(false);
										} else {
											continuar.setEnabled(true);
											erro.setText("");
										}
									}
						
									public void insertUpdate(DocumentEvent e) {
										checarTexto();
									}
						
									public void removeUpdate(DocumentEvent e) {
										checarTexto();
									}
						
									public void changedUpdate(DocumentEvent e) {
										checarTexto();
									}
								});
								
										add(txtEscreverONome);
		inserirNome.setIcon(new ImageIcon(redimensionarNome));
		inserirNome.setBounds(134, 120, 450, 160);
		add(inserirNome);
		// txtEscreverONome.setColumns(10); esquece isso por enquanto

		// 5. Botão voltar
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

				java.awt.Container containerPai = InserirNome.this.getParent();
				if (containerPai != null && containerPai.getLayout() instanceof java.awt.CardLayout) {
					java.awt.CardLayout cl = (java.awt.CardLayout) containerPai.getLayout();

					cl.show(containerPai, "telaInicial");
				}
			}
		});

		// 6. Imagem de Fundo
		JLabel imagemFundo = new JLabel("");
		imagemFundo.setBounds(0, 0, 717, 471);
		add(imagemFundo);

		ImageIcon iconeOriginal = new ImageIcon(Menu.class.getResource("/Imagens/SomenteFundo.jpg"));
		Image imagemOriginal = iconeOriginal.getImage();

		// 7. Listener de Redimensionamento
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
					// 218, 195, 322, 25
					txtEscreverONome.setBounds((int) (218 * proporcaoX), (int) (195 * proporcaoY),
							(int) (322 * proporcaoX), (int) (25 * proporcaoY));

					// Redimensiona e reposiciona o botão continuar
					int larguraBotao = (int) (145 * proporcaoX);
					int alturaBotao = (int) (40 * proporcaoY);
					continuar.setBounds((int) (550 * proporcaoX), (int) (370 * proporcaoY), larguraBotao, alturaBotao);
					Image imgAvancarAjustada = imgContinuar.getImage().getScaledInstance(Math.max(1, larguraBotao),
							Math.max(1, alturaBotao), Image.SCALE_SMOOTH);
					continuar.setIcon(new ImageIcon(imgAvancarAjustada));

					// Voltar
					int larguraVoltar = (int) (145 * proporcaoX);
					int alturaVoltar = (int) (40 * proporcaoY);
					voltar.setBounds((int) (37 * proporcaoX), (int) (370 * proporcaoY), larguraVoltar, alturaVoltar);
					Image imgVoltar = imagemV.getImage().getScaledInstance(Math.max(1, larguraVoltar),
							Math.max(1, alturaVoltar), Image.SCALE_SMOOTH);
					voltar.setIcon(new ImageIcon(imgVoltar));
				}
			}
		});
	}
}