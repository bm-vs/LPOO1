package gamelogic;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Demo extends JPanel {
	private BufferedImage hero;
	private int x, y, width = 20, height = 20;

	public Demo(int xx, int yy) {
		
		this.x = xx;
		this.y = yy;
		
		try {
			hero = ImageIO.read(new File("hero.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				repaint();

			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// System.out.println("x=" + x);
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					x--;
					break;

				case KeyEvent.VK_RIGHT:
					x++;
					// System.out.println("x=" + x);
					break;

				case KeyEvent.VK_UP:
					y--;
					break;

				case KeyEvent.VK_DOWN:
					y++;
					break;
				}
				repaint();

			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(hero, x, y, x + width - 1, y + height - 1, 0, 0,
				hero.getWidth(), hero.getHeight(), null);
	}

}
