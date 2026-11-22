package visao;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LabelImagem extends JLabel {

	private static final long serialVersionUID = 1L;
	private Image imagem;

	public LabelImagem(String caminho) {
		setImagem(caminho);
	}

	public void setImagem(String caminho) {
		if (caminho != null && LabelImagem.class.getResource(caminho) != null) {
			this.imagem = new ImageIcon(LabelImagem.class.getResource(caminho)).getImage();
		} else {
			this.imagem = null;
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (imagem != null) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
		}
		super.paintComponent(g);
	}
}