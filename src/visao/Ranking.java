package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.Jogador;

public class Ranking extends PainelFundo {

	private static final long serialVersionUID = 1L;
	private Menu menu;
	private JLabel lblTitulo;
	private JScrollPane scrollTabela;
	private JTable tabelaRanking;
	private DefaultTableModel modeloTabela;
	private JButton btnVoltar;

	public Ranking(Menu menu) {
		super("/Imagens/MenuPrincipal.jpg");
		this.menu = menu;
		setLayout(null);

		// Título
		lblTitulo = new JLabel("RANKING DOS GUARDIÕES", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(255, 215, 100));
		lblTitulo.setBounds(110, 25, 500, 35);
		add(lblTitulo);

		// Modelo de tabela não editável
		String[] colunas = { "#", "Jogador", "Classe", "Pontuação", "Resultado" };
		modeloTabela = new DefaultTableModel(colunas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tabelaRanking = new JTable(modeloTabela);
		tabelaRanking.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabelaRanking.setRowHeight(26);
		tabelaRanking.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tabelaRanking.getTableHeader().setReorderingAllowed(false);
		tabelaRanking.setBackground(new Color(40, 28, 18));
		tabelaRanking.setForeground(Color.WHITE);
		tabelaRanking.setGridColor(new Color(140, 105, 55));

		// Centraliza colunas de posição, pontuação e resultado
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		tabelaRanking.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		tabelaRanking.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		tabelaRanking.getColumnModel().getColumn(4).setCellRenderer(centralizado);

		// Larguras iniciais sugeridas
		tabelaRanking.getColumnModel().getColumn(0).setPreferredWidth(45);
		tabelaRanking.getColumnModel().getColumn(1).setPreferredWidth(180);
		tabelaRanking.getColumnModel().getColumn(2).setPreferredWidth(120);
		tabelaRanking.getColumnModel().getColumn(3).setPreferredWidth(100);
		tabelaRanking.getColumnModel().getColumn(4).setPreferredWidth(100);

		scrollTabela = new JScrollPane(tabelaRanking);
		scrollTabela.setBounds(60, 75, 597, 305);
		scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(180, 140, 70), 2));
		add(scrollTabela);

		// Botão Voltar
		btnVoltar = new BotaoImagem("/Imagens/BotãoVoltar.png");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVoltar.setBackground(new Color(70, 40, 20));
		btnVoltar.setForeground(new Color(255, 215, 100));
		btnVoltar.setFocusPainted(false);
		btnVoltar.setBorder(BorderFactory.createLineBorder(new Color(180, 140, 70), 2));
		btnVoltar.setBounds(263, 398, 190, 45);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.trocarTela("telaInicial");
			}
		});
		add(btnVoltar);

		// Responsividade proporcional
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int larguraAtual = getWidth();
				int alturaAtual = getHeight();

				if (larguraAtual > 0 && alturaAtual > 0) {
					double px = larguraAtual / 717.0;
					double py = alturaAtual / 471.0;

					lblTitulo.setBounds((int) (110 * px), (int) (25 * py), (int) (500 * px), (int) (35 * py));
					scrollTabela.setBounds((int) (60 * px), (int) (75 * py), (int) (597 * px), (int) (305 * py));
					btnVoltar.setBounds((int) (263 * px), (int) (398 * py), (int) (190 * px), (int) (45 * py));
				}
			}
		});
	}

	public void atualizarTabela() {
		modeloTabela.setRowCount(0);

		try {
			controle.Ranking rankingControle = new controle.Ranking(menu.getPastaDados());
			rankingControle.carregarDoArquivo();

			List<Jogador> jogadores = rankingControle.getJogadores();
			int posicao = 1;

			for (Jogador j : jogadores) {
				modeloTabela.addRow(new Object[] {
						posicao + "º",
						j.getNomeJogador(),
						j.getClassePersonagem(),
						j.getPontuacao(),
						j.getResultado()
				});
				posicao++;
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
					"Erro ao carregar o ranking:\n" + ex.getMessage(),
					"Erro no Ranking", JOptionPane.ERROR_MESSAGE);
		}
	}
}