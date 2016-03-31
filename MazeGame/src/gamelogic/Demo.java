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
	private BufferedImage hero_small;
	private BufferedImage wall;
	private BufferedImage dragon;
	private BufferedImage espada;
	private int x, y;
	public char board[][];

	public Demo(char board[][]) {
		this.board = board;

		try {
			hero = ImageIO.read(new File("hero.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			hero_small = ImageIO.read(new File("hero_small.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			espada = ImageIO.read(new File("espada.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			wall = ImageIO.read(new File("wall.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			dragon = ImageIO.read(new File("dragon.jpg"));
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

		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 'X') {
					g.drawImage(wall, j * 30, i * 30, 30, 30, null);
				}
				if (board[i][j] == 'H') {
					g.drawImage(hero_small, j * 30, i * 30, 30, 30, null);
					
				}
				if (board[i][j] == 'A') {
					g.drawImage(hero, j * 30, i * 30, 30, 30, null);
					
				}

				if (board[i][j] == 'D') {
					g.drawImage(dragon, j * 30, i * 30, 30, 30, null);
				}
				
				if (board[i][j] == 'E') {
					g.drawImage(espada, j * 30, i * 30, 30, 30, null);
				}
			}

	}
}
