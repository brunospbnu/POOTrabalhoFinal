package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class NovaPartida extends PainelFundo {

	private static final long serialVersionUID = 1L;
	private Menu menu;

	public NovaPartida(Menu menu) {
		super("/Imagens/SomenteFundo.jpg");
		this.menu = menu;
		setLayout(null);

		// Legenda principal
		LabelImagem tituloDoMenu = new LabelImagem("/Imagens/LegendaEscolherClasse.png");
		tituloDoMenu.setBounds(180, 18, 352, 80);
		add(tituloDoMenu);

		// Imagens dos personagens
		LabelImagem cavaleiro = new LabelImagem("/Imagens/Cavaleiro.png");
		cavaleiro.setBounds(37, 115, 145, 140);
		add(cavaleiro);

		LabelImagem arqueira = new LabelImagem("/Imagens/Arqueira.png");
		arqueira.setBounds(286, 115, 145, 140);
		add(arqueira);

		LabelImagem magoPNG = new LabelImagem("/Imagens/Mago.png");
		magoPNG.setBounds(535, 115, 145, 140);
		add(magoPNG);

		// Botões de escolha (alinhados logo abaixo de cada personagem)
		BotaoImagem guerreiro = new BotaoImagem("/Imagens/BotãoEscolher.png");
		guerreiro.setBounds(37, 268, 145, 40);
		add(guerreiro);

		BotaoImagem arqueiro = new BotaoImagem("/Imagens/BotãoEscolher.png");
		arqueiro.setBounds(286, 268, 145, 40);
		add(arqueiro);

		BotaoImagem mago = new BotaoImagem("/Imagens/BotãoEscolher.png");
		mago.setBounds(535, 268, 145, 40);
		add(mago);

		// Botão de voltar
		BotaoImagem voltar = new BotaoImagem("/Imagens/BotãoVoltar.png");
		voltar.setBounds(37, 370, 145, 40);
		add(voltar);

		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaPartida.this.menu.trocarTela("telaInicial");
			}
		});

		// Redimensionamento proporcional dos componentes
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int larguraAtual = getWidth();
				int alturaAtual = getHeight();

				if (larguraAtual > 0 && alturaAtual > 0) {
					double proporcaoX = larguraAtual / 717.0;
					double proporcaoY = alturaAtual / 471.0;

					int larguraPadrao = (int) (145 * proporcaoX);
					int alturaPersonagem = (int) (140 * proporcaoY);
					int alturaBotao = (int) (40 * proporcaoY);

					tituloDoMenu.setBounds(
							(int) (180 * proporcaoX), (int) (18 * proporcaoY),
							(int) (352 * proporcaoX), (int) (80 * proporcaoY));

					cavaleiro.setBounds((int) (37 * proporcaoX), (int) (115 * proporcaoY), larguraPadrao, alturaPersonagem);
					arqueira.setBounds((int) (286 * proporcaoX), (int) (115 * proporcaoY), larguraPadrao, alturaPersonagem);
					magoPNG.setBounds((int) (535 * proporcaoX), (int) (115 * proporcaoY), larguraPadrao, alturaPersonagem);

					guerreiro.setBounds((int) (37 * proporcaoX), (int) (268 * proporcaoY), larguraPadrao, alturaBotao);
					arqueiro.setBounds((int) (286 * proporcaoX), (int) (268 * proporcaoY), larguraPadrao, alturaBotao);
					mago.setBounds((int) (535 * proporcaoX), (int) (268 * proporcaoY), larguraPadrao, alturaBotao);

					voltar.setBounds((int) (37 * proporcaoX), (int) (370 * proporcaoY), larguraPadrao, alturaBotao);
				}
			}
		});
	}
}