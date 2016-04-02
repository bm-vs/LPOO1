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

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage hero;
	private BufferedImage hero_small;
	private BufferedImage wall;
	private BufferedImage dragon;
	private BufferedImage espada;
	private BufferedImage dragon_espada;
	private BufferedImage dragon_sleep;
	private BufferedImage gate;
	private Game game;
	public char board[][];

	public GamePanel(Game game) {
		this.board = game.maze.board;
		this.game = game;

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
			dragon_espada = ImageIO.read(new File("dragon_espada.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			dragon_sleep = ImageIO.read(new File("dragon_sleep.jpg"));
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
		
		try {
			gate = ImageIO.read(new File("gate.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
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
				switch (e.getKeyCode()) {

				case KeyEvent.VK_LEFT:
					keyAction("a");
					break;

				case KeyEvent.VK_RIGHT:
					keyAction("d");
					break;

				case KeyEvent.VK_UP:
					keyAction("w");
					break;

				case KeyEvent.VK_DOWN:
					keyAction("s");
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
		int length;
		if (board.length >= 19)
			length = 600 / board.length;
		else
			length = 30;

		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 'X') {
					g.drawImage(wall, j * length, i * length, length, length,
							null);
				}
				if (board[i][j] == 'H') {
					g.drawImage(hero_small, j * length, i * length, length,
							length, null);

				}
				if (board[i][j] == 'A') {
					g.drawImage(hero, j * length, i * length, length, length,
							null);

				}

				if (board[i][j] == 'D') {
					g.drawImage(dragon, j * length, i * length, length, length,
							null);
				}
				
				if (board[i][j] == 'd') {
					g.drawImage(dragon_sleep, j * length, i * length, length, length,
							null);
				}

				if (board[i][j] == 'E') {
					g.drawImage(espada, j * length, i * length, length, length,
							null);
				}
				if (board[i][j] == 'F') {
					g.drawImage(dragon_espada, j * length, i * length, length, length,
							null);
				}
				if (board[i][j] == 'S') {
					g.drawImage(gate, j * length, i * length, length, length,
							null);
				}
				
				
			}

	}

	private void keyAction(String key) {

		switch (game.play(game.game_mode, key)) {
		case 1:

			// estado.setText("The hero has won!");// WIN
			break;
		case 2:
			// estado.setText("The hero has died!"); // LOSE
			break;
		case 3:
			// estado.setText("The dragon has died!"); // DRAGON DIES
			break;
		}
	}
}
