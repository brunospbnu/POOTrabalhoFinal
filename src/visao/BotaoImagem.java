package visao;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BotaoImagem extends JButton {

	private static final long serialVersionUID = 1L;
	private Image imagem;

	public BotaoImagem(String caminho) {
		try {
			BufferedImage original = ImageIO.read(BotaoImagem.class.getResource(caminho));
			this.imagem = recortarBordasTransparentes(original);
		} catch (IOException | IllegalArgumentException e) {
			this.imagem = new ImageIcon(BotaoImagem.class.getResource(caminho)).getImage();
		}

		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
	}

	private BufferedImage recortarBordasTransparentes(BufferedImage img) {
		int largura = img.getWidth();
		int altura = img.getHeight();
		int minX = largura, minY = altura, maxX = -1, maxY = -1;

		for (int y = 0; y < altura; y++) {
			for (int x = 0; x < largura; x++) {
				int alpha = (img.getRGB(x, y) >> 24) & 0xff;
				if (alpha > 15) {
					if (x < minX) minX = x;
					if (x > maxX) maxX = x;
					if (y < minY) minY = y;
					if (y > maxY) maxY = y;
				}
			}
		}

		if (maxX < minX || maxY < minY) {
			return img;
		}
		return img.getSubimage(minX, minY, (maxX - minX) + 1, (maxY - minY) + 1);
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