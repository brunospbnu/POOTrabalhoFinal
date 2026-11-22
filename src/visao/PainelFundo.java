package visao;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class PainelFundo extends JPanel {
	private static final long serialVersionUID = 1L;
	private final Image imagem;

	public PainelFundo(String caminho) {
		this.imagem = new ImageIcon(Menu.class.getResource(caminho)).getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imagem != null) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
		}
	}
}