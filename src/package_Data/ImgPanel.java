package package_Data;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImgPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image img;

	public ImgPanel(Image img) {
		this.img = img;
	}

	public void setImage(Image img) {
		this.img = img;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
