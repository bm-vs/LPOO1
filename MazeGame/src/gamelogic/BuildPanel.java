package gamelogic;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BuildPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage hero;
	private BufferedImage hero_small;
	private BufferedImage wall;
	private BufferedImage dragon;
	private BufferedImage espada;
	private BufferedImage dragon_espada;
	private BufferedImage dragon_sleep;
	private BufferedImage gate;
	private JButton button_wall;
	private JButton button_hero;
	private JButton button_dragon;
	private JButton button_sword;
	private JButton button_exit;
	private JButton button_play;
	private int x, y;
	private int cursor = -1;
	public char board[][];

	public BuildPanel(Game game) {
		this.setLayout(null);	
		
		button_wall = new JButton("Parede");
		this.add(button_wall);
		button_wall.setBounds(0, 0, 100, 30);
		button_wall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("wall.jpg").getImage(),
						new java.awt.Point(15,15),"cursor wall"));
				cursor = 0;
			}});
		
		button_hero = new JButton("Her�i");
		this.add(button_hero);
		button_hero.setBounds(100, 0, 100, 30);
		button_hero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("hero.jpg").getImage(),
						new java.awt.Point(15,15),"cursor hero"));
				cursor = 1;
			}});
		
		button_dragon = new JButton("Drag�o");
		this.add(button_dragon);
		button_dragon.setBounds(200, 0, 100, 30);
		button_dragon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("dragon.jpg").getImage(),
						new java.awt.Point(15,15),"cursor dragon"));
				cursor = 2;
			}});
		
		button_sword = new JButton("Espada");
		this.add(button_sword);
		button_sword.setBounds(300, 0, 100, 30);
		button_sword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("espada.jpg").getImage(),
						new java.awt.Point(15,15),"cursor sword"));
				cursor = 3;
			}});
		
		button_exit = new JButton("Sa�da");
		this.add(button_exit);
		button_exit.setBounds(400, 0, 100, 30);
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("gate.jpg").getImage(),
						new java.awt.Point(15,15),"cursor exit"));
				cursor = 4;
			}});
		
		
		this.board = game.maze.board;
		repaint();

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


		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				mouseAction(e);
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {}
			
		});
		
		
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
						
			@Override
			public void mousePressed(MouseEvent e) {
				mouseAction(e);
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
					g.drawImage(wall, j * length, 50 + i * length, length, length,
							null);
				}
				if (board[i][j] == 'H') {
					g.drawImage(hero_small, j * length, 50 + i * length, length,
							length, null);

				}
				if (board[i][j] == 'A') {
					g.drawImage(hero, j * length, 50 + i * length, length, length,
							null);

				}

				if (board[i][j] == 'D') {
					g.drawImage(dragon, j * length, 50 + i * length, length, length,
							null);
				}
				
				if (board[i][j] == 'd') {
					g.drawImage(dragon_sleep, j * length, 50 + i * length, length, length,
							null);
				}

				if (board[i][j] == 'E') {
					g.drawImage(espada, j * length, 50 + i * length, length, length,
							null);
				}
				if (board[i][j] == 'F') {
					g.drawImage(dragon_espada, j * length, 50 + i * length, length, length,
							null);
				}
				if (board[i][j] == 'S') {
					g.drawImage(gate, j * length, 50 + i * length, length, length,
							null);
				}
			}

	}
	
	
	public void mouseAction(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		int length;
		if (board.length >= 19)
			length = 600 / board.length;
		else
			length = 30;
		
		if ((y-50)/length < board.length && x/length < board.length && (y-50) >= 0 && x >= 0) {
			if (SwingUtilities.isLeftMouseButton(e))
				switch (cursor) {
				case 0:
					board[(y-50)/length][x/length] = 'X';
					break;
				case 1:
					if (x/length != 0 && (y-50)/length != 0 && x/length != board.length - 1 && (y-50)/length != board.length - 1) {
						board[(y-50)/length][x/length] = 'H';
						button_hero.setEnabled(false);
						setCursor(Cursor.getDefaultCursor());
						cursor = -1;
					}
					break;
				case 2:
					if (x/length != 0 && (y-50)/length != 0 && x/length != board.length - 1 && (y-50)/length != board.length - 1) {
						board[(y-50)/length][x/length] = 'D';
					}
					break;
				case 3:
					if (x/length != 0 && (y-50)/length != 0 && x/length != board.length - 1 && (y-50)/length != board.length - 1) {
						board[(y-50)/length][x/length] = 'E';
						button_sword.setEnabled(false);
						setCursor(Cursor.getDefaultCursor());
						cursor = -1;
					}
					break;
				case 4:
					if (x/length == 0 || (y-50)/length == 0 || x/length == board.length - 1 || (y-50)/length == board.length - 1) {
						board[(y-50)/length][x/length] = 'S';
						button_exit.setEnabled(false);
						setCursor(Cursor.getDefaultCursor());
						cursor = -1;
					}
					break;
				
				}
				
			else if (SwingUtilities.isRightMouseButton(e) && x/length != 0 && (y-50)/length != 0 && x/length != board.length - 1 && (y-50)/length != board.length - 1) {
				board[(y-50)/length][x/length] = ' ';
			}
		}
		
		int n_hero = 0;
		int n_sword = 0;
		int n_exit = 0;		
		
		for (int i = 0; i < board.length; i++) {
			for (int a = 0; a < board.length; a++) {
				if(board[i][a] == 'H') {
					n_hero = 1;
				}
				else if(board[i][a] == 'E') {
					n_sword = 1;
				}
				else if(board[i][a] == 'S') {
					n_exit = 1;
				}
			}
		}
		
		if (n_hero == 0) {
			button_hero.setEnabled(true);
		}
		else if (n_sword == 0) {
			button_sword.setEnabled(true);
		}
		else if (n_exit == 0) {
			button_exit.setEnabled(true);
		}
		
		
		repaint();
	}
	
	
}