package gamelogic;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	private JButton move_up;
	private JButton move_down;
	private JButton move_left;
	private JButton move_right;
	private JLabel game_status;
	double length;
	public char board[][];

	public GamePanel(Game game) {
		this.board = game.maze.board;
		this.game = game;
		
		length = 600.0 / board.length;
		
		this.setLayout(null);
		
		game_status = new JLabel("", SwingConstants.CENTER);
		game_status.setBounds(682, 410, 186, 50);
		game_status.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(game_status);

		move_up = new JButton("Cima");
		move_up.setBounds(732, 105, 88, 44);
		this.add(move_up);
		move_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyAction("w");
				requestFocus();
				repaint();
			}
		});
		
		move_left = new JButton("Esquerda");
		move_left.setBounds(682, 160, 88, 44);
		add(move_left);
		move_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyAction("a");
				requestFocus();
				repaint();
			}
		});
		
		
		move_down = new JButton("Baixo");
		move_down.setBounds(732, 215, 88, 44);
		this.add(move_down);
		move_down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyAction("s");
				requestFocus();
				repaint();
			}
		});
		
		move_right = new JButton("Direita");
		move_right.setBounds(780, 160, 88, 44);
		this.add(move_right);
		move_right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyAction("d");
				requestFocus();
				repaint();
			}
		});
		

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
		
		int lr = (int) Math.floor(length);
		int dy = (720 - board.length*lr)/2;


		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++) {
				
				int lx = 10 + j*lr;
				int ly = dy + i*lr;
				
				if (board[i][j] == 'X') {
					g.drawImage(wall, lx, ly, lr, lr,null);
				}
				if (board[i][j] == 'H') {
					g.drawImage(hero_small, lx, ly, lr, lr,null);

				}
				if (board[i][j] == 'A') {
					g.drawImage(hero, lx, ly, lr, lr,null);

				}

				if (board[i][j] == 'D') {
					g.drawImage(dragon, lx, ly, lr, lr,null);
				}
				
				if (board[i][j] == 'd') {
					g.drawImage(dragon_sleep, lx, ly, lr, lr,null);
				}

				if (board[i][j] == 'E') {
					g.drawImage(espada, lx, ly, lr, lr,null);
				}
				if (board[i][j] == 'F') {
					g.drawImage(dragon_espada, lx, ly, lr, lr,null);
				}
				if (board[i][j] == 'S') {
					g.drawImage(gate, lx, ly, lr, lr,null);
				}
			}	
	}

	private void keyAction(String key) {

		switch (game.play(game.game_mode, key)) {
		case 1:
			close();
			game_status.setText("Vit\u00F3ria!");// WIN
			break;
		case 2:
			close();
			game_status.setText("Derrota!"); // LOSE
			break;
		case 3:
			if (game.dragons.isEmpty())
				game_status.setText("Drag\u00E3o morto!"); // DRAGON DIES
			break;
		}
	}
	
	private void close() {
		setEnabled(false);
		move_up.setEnabled(false);
		move_down.setEnabled(false);
		move_left.setEnabled(false);
		move_right.setEnabled(false);
	}
}
