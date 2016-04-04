package gamegui;

import java.awt.Color;
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
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gamelogic.Game;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage hero;
	private BufferedImage hero_small;
	private BufferedImage wall;
	private BufferedImage dragon;
	private BufferedImage sword_floor;
	private BufferedImage dragon_sword;
	private BufferedImage dragon_sleep;
	private BufferedImage gate_closed_top;
	private BufferedImage gate_closed_bottom;
	private BufferedImage gate_closed_left;
	private BufferedImage gate_closed_right;
	private BufferedImage floor;
	private BufferedImage wall3d;
	private Game game;
	private JButton move_up;
	private JButton move_down;
	private JButton move_left;
	private JButton move_right;
	private JLabel game_status;
	private double length;
	private int n_dragons;
	private char board[][];
	private Color color_pine = new Color(32,62,71);
	private Color color_blue = new Color(76,181,245);
	private Color color_lime = new Color(179,193,0);

	public GamePanel(Game game) {
		this.setBackground(color_pine);
		this.board = game.getMaze().getGrid();
		this.game = game;
		n_dragons = game.getNumDragons();
		
		length = 600.0 / board.length;
		
		this.setLayout(null);
		
		game_status = new JLabel("", SwingConstants.CENTER);
		game_status.setBounds(682, 410, 186, 50);
		game_status.setFont(new Font("Dialog", Font.BOLD, 20));
		game_status.setForeground(Color.WHITE);
		add(game_status);

		move_up = new JButton("CIMA");
		buttonConfig(move_up, 12);
		move_up.setBounds(732, 105, 88, 44);
		this.add(move_up);
		move_up.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(move_up, e);
		    }
		});
		move_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyAction("w");
				requestFocus();
				repaint();
			}
		});
		
		move_left = new JButton("ESQUERDA");
		buttonConfig(move_left, 12);
		move_left.setBounds(682, 160, 88, 44);
		add(move_left);
		move_left.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(move_left, e);
		    }
		});
		move_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyAction("a");
				requestFocus();
				repaint();
			}
		});
		
		
		move_down = new JButton("BAIXO");
		buttonConfig(move_down, 12);
		move_down.setBounds(732, 215, 88, 44);
		this.add(move_down);
		move_down.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(move_down, e);
		    }
		});
		move_down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyAction("s");
				requestFocus();
				repaint();
			}
		});
		
		move_right = new JButton("DIREITA");
		buttonConfig(move_right, 12);
		move_right.setBounds(780, 160, 88, 44);
		this.add(move_right);
		move_right.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(move_right, e);
		    }
		});
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
			sword_floor = ImageIO.read(new File("sword_floor.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			dragon_sword = ImageIO.read(new File("dragon_sword.jpg"));
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
			gate_closed_top = ImageIO.read(new File("gate_closed_top.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			gate_closed_bottom = ImageIO.read(new File("gate_closed_bottom.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			gate_closed_left = ImageIO.read(new File("gate_closed_left.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			gate_closed_right = ImageIO.read(new File("gate_closed_right.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			floor = ImageIO.read(new File("floor.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			wall3d = ImageIO.read(new File("wall3d.jpg"));
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
					move_left.setBackground(color_lime);
					break;

				case KeyEvent.VK_RIGHT:
					keyAction("d");
					move_right.setBackground(color_lime);
					break;

				case KeyEvent.VK_UP:
					keyAction("w");
					move_up.setBackground(color_lime);
					break;

				case KeyEvent.VK_DOWN:
					keyAction("s");
					move_down.setBackground(color_lime);
					break;					
				}
				
				repaint();

			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {

				case KeyEvent.VK_LEFT:
					move_left.setBackground(color_blue);
					break;

				case KeyEvent.VK_RIGHT:
					move_right.setBackground(color_blue);
					break;

				case KeyEvent.VK_UP:
					move_up.setBackground(color_blue);
					break;

				case KeyEvent.VK_DOWN:
					move_down.setBackground(color_blue);
					break;					
				}
				
				repaint();
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
					if (i == board.length - 1 || (board[i+1][j] != 'X' && board[i+1][j] != 'S')) {
						g.drawImage(wall3d, lx, ly, lr, lr,null);
					}
					else {
						g.drawImage(wall, lx, ly, lr, lr,null);
					}
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
					g.drawImage(sword_floor, lx, ly, lr, lr,null);
				}
				if (board[i][j] == 'F') {
					g.drawImage(dragon_sword, lx, ly, lr, lr,null);
				}
				if (board[i][j] == 'S') {
					if (i == 0)
						g.drawImage(gate_closed_top, lx, ly, lr, lr,null);
					if (i == board.length - 1) {
						g.drawImage(gate_closed_bottom, lx, ly, lr, lr,null);
					}
					if (j == 0)
						g.drawImage(gate_closed_left, lx, ly, lr, lr,null);
					if (j == board.length - 1) {
						g.drawImage(gate_closed_right, lx, ly, lr, lr,null);
					}
				}
				if (board[i][j] == ' ') {
					g.drawImage(floor, lx, ly, lr, lr, null);
				}
			}	
	}

	private void keyAction(String key) {

		switch (game.play(game.getGameMode(), key)) {
		case 1:
			close();
			game_status.setText("VIT\u00D3RIA!");// WIN
			break;
		case 2:
			close();
			game_status.setText("DERROTA!"); // LOSE
			break;
		case 3:
			if (game.getNumDragons() == 0)
				if (n_dragons == 1)
					game_status.setText("DRAG\u00C3O MORTO!");
				else {
					game_status.setText("DRAG\u00D5ES MORTOS!");
				}
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
	
	
	private void buttonConfig(JButton b, int font_size) {
		b.setBackground(color_blue);
		b.setForeground(Color.WHITE);
		Border border = new LineBorder(Color.WHITE, 2);
		b.setBorder(border);
		b.setFont(new Font("Dialog", Font.BOLD, font_size));
		b.setFocusPainted(false);
	}
	
	private void buttonHoverAction(JButton b, ChangeEvent e) {
		ButtonModel model = (ButtonModel) e.getSource();
        if (model.isRollover()) {
        	b.setBackground(color_lime);
        }
        else {
        	b.setBackground(color_blue);
        }
	}
	
}
