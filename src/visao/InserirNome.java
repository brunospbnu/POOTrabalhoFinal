package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import excecoes.NomeInvalidoException;
import modelo.Jogador;

public class InserirNome extends PainelFundo {

	private static final long serialVersionUID = 1L;
	private Menu menu;
	private JTextField txtEscreverONome;
	private String nomeJogador = "";

	public InserirNome(Menu menu) {
		super("/Imagens/SomenteFundo.jpg");
		this.menu = menu;
		setLayout(null);

		// 1. Rótulo de Erro
		JLabel erro = new JLabel("");
		erro.setBounds(180, 248, 358, 25);
		erro.setFont(new Font("Tahoma", Font.BOLD, 12));
		erro.setForeground(new Color(255, 90, 90));
		erro.setHorizontalAlignment(SwingConstants.CENTER);
		add(erro);

		// 2. Botão Continuar
		BotaoImagem continuar = new BotaoImagem("/Imagens/BotãoContinuar.png");
		continuar.setBounds(550, 370, 145, 40);
		continuar.setEnabled(false);
		add(continuar);

		continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeDigitado = txtEscreverONome.getText().trim();

				try {
					Jogador jogadorTemp = new Jogador(nomeDigitado, 0);
					nomeJogador = jogadorTemp.getNomeJogador();
					erro.setText("");
					InserirNome.this.menu.trocarTela("novaPartida");
				} catch (NomeInvalidoException ex) {
					erro.setText(ex.getMessage());
					continuar.setEnabled(false);
				}
			}
		});

		// 3. Campo de Texto (adicionado antes da placa para ficar visível por cima dela)
		txtEscreverONome = new JTextField() {
			private static final long serialVersionUID = 1L;

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
		txtEscreverONome.setBounds(218, 195, 322, 25);
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

		// 4. Imagem da Placa "Informe seu nome"
		LabelImagem inserirNome = new LabelImagem("/Imagens/ImagemInserirNome.png");
		inserirNome.setBounds(134, 120, 450, 160);
		add(inserirNome);

		// 5. Botão Voltar
		BotaoImagem voltar = new BotaoImagem("/Imagens/BotãoVoltar.png");
		voltar.setBounds(37, 370, 145, 40);
		add(voltar);

		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserirNome.this.menu.trocarTela("telaInicial");
			}
		});

		// 6. Redimensionamento proporcional dos componentes
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int larguraAtual = getWidth();
				int alturaAtual = getHeight();

				if (larguraAtual > 0 && alturaAtual > 0) {
					double proporcaoX = larguraAtual / 717.0;
					double proporcaoY = alturaAtual / 471.0;

					inserirNome.setBounds(
							(int) (134 * proporcaoX), (int) (120 * proporcaoY),
							(int) (450 * proporcaoX), (int) (160 * proporcaoY));

					txtEscreverONome.setBounds(
							(int) (218 * proporcaoX), (int) (195 * proporcaoY),
							(int) (322 * proporcaoX), (int) (25 * proporcaoY));

					erro.setBounds(
							(int) (180 * proporcaoX), (int) (248 * proporcaoY),
							(int) (358 * proporcaoX), (int) (25 * proporcaoY));

					continuar.setBounds(
							(int) (550 * proporcaoX), (int) (370 * proporcaoY),
							(int) (145 * proporcaoX), (int) (40 * proporcaoY));

					voltar.setBounds(
							(int) (37 * proporcaoX), (int) (370 * proporcaoY),
							(int) (145 * proporcaoX), (int) (40 * proporcaoY));
				}
			}
		});
	}

	public String getNomeJogador() {
		return nomeJogador;
	}
}