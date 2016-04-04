package gamegui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BuildPanel extends JPanel {
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
	private JButton button_wall;
	private JButton button_hero;
	private JButton button_dragon;
	private JButton button_sword;
	private JButton button_exit;
	private int x, y;
	private int cursor = -1;
	private double length;
	private char board[][];
	private Color color_pine = new Color(32,62,71);
	private Color color_blue = new Color(76,181,245);
	private Color color_lime = new Color(179,193,0);

	public BuildPanel(char board[][]) {
		this.setBackground(color_pine);
		this.board = board;
		length = 600.0 / board.length;
		this.setLayout(null);	
		
		button_wall = new JButton("PAREDE");
		buttonConfig(button_wall, 12);
		this.add(button_wall);
		button_wall.setBounds(10, 10, 112, 30);
		button_wall.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(button_wall, e);
		    }
		});
		button_wall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAction("wall.jpg", "cursor wall");
			}});
		
		button_hero = new JButton("HER\u00D3I");
		buttonConfig(button_hero, 12);
		this.add(button_hero);
		button_hero.setBounds(122, 10, 112, 30);
		button_hero.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(button_hero, e);
		    }
		});
		button_hero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAction("hero_small.jpg", "cursor hero");
			}});
		
		button_dragon = new JButton("DRAG\u00C3O");
		buttonConfig(button_dragon, 12);
		this.add(button_dragon);
		button_dragon.setBounds(234, 10, 112, 30);
		button_dragon.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(button_dragon, e);
		    }
		});
		button_dragon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAction("dragon.jpg", "cursor dragon");
			}});
		
		button_sword = new JButton("ESPADA");
		buttonConfig(button_sword, 12);
		this.add(button_sword);
		button_sword.setBounds(346, 10, 112, 30);
		button_sword.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(button_sword, e);
		    }
		});
		button_sword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				buttonAction("sword_floor.jpg", "cursor sword");
			}});
		
		button_exit = new JButton("SA\u00CDDA");
		buttonConfig(button_exit, 12);
		this.add(button_exit);
		button_exit.setBounds(458, 10, 112, 30);
		button_exit.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(button_exit, e);
		    }
		});
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAction("gate_closed_top.jpg", "cursor exit");
			}});
		
		
		repaint();

		try {
			hero = ImageIO.read(new File("hero.jpg"));
			hero_small = ImageIO.read(new File("hero_small.jpg"));
			sword_floor = ImageIO.read(new File("sword_floor.jpg"));
			dragon_sword = ImageIO.read(new File("dragon_sword.jpg"));
			dragon_sleep = ImageIO.read(new File("dragon_sleep.jpg"));
			dragon = ImageIO.read(new File("dragon.jpg"));
			wall = ImageIO.read(new File("wall.jpg"));
			gate_closed_top = ImageIO.read(new File("gate_closed_top.jpg"));
			gate_closed_bottom = ImageIO.read(new File("gate_closed_bottom.jpg"));
			gate_closed_left = ImageIO.read(new File("gate_closed_left.jpg"));
			gate_closed_right = ImageIO.read(new File("gate_closed_right.jpg"));
			floor = ImageIO.read(new File("floor.jpg"));
			wall3d = ImageIO.read(new File("wall3d.jpg"));
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
		
		int lr = (int) Math.floor(length);
		int dy = (720 - board.length*lr)/2;
		int dx = (950 - board.length*lr)/2;

		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++) {
				
				int lx = dx + j*lr;
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
					g.drawImage(hero_small, lx, ly, lr, lr, null);
				}
				if (board[i][j] == 'A') {
					g.drawImage(hero, lx, ly, lr, lr, null);
				}

				if (board[i][j] == 'D') {
					g.drawImage(dragon, lx, ly, lr, lr, null);
				}
				if (board[i][j] == 'd') {
					g.drawImage(dragon_sleep, lx, ly, lr, lr, null);
				}
				if (board[i][j] == 'E') {
					g.drawImage(sword_floor, lx, ly, lr, lr, null);
				}
				if (board[i][j] == 'F') {
					g.drawImage(dragon_sword, lx, ly, lr, lr, null);
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

	public void mouseAction(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		int lr = (int) Math.floor(length);
		int dy = (720 - board.length*lr)/2;
		int dx = (950 - board.length*lr)/2;
		
		if ((y-dy) < board.length*length && (x-dx) < board.length*length && (y-dy) >= 0 && (x-dx) >= 0) {
			if (SwingUtilities.isLeftMouseButton(e))
				switch (cursor) {
				case 0:
					try {
						board[(y-dy)/lr][(x-dx)/lr] = 'X';
					} catch (Exception exc) {
					}
					break;
				case 1:
					if ((x-dx)/lr != 0 && (y-dy)/lr != 0 && (x-dx)/lr != board.length - 1 && (y-dy)/lr != board.length - 1) {
						board[(y-dy)/lr][(x-dx)/lr] = 'H';
						button_hero.setEnabled(false);
						setCursor(Cursor.getDefaultCursor());
						cursor = -1;
					}
					break;
				case 2:
					if ((x-dx)/lr != 0 && (y-dy)/lr != 0 && (x-dx)/lr != board.length - 1 && (y-dy)/lr != board.length - 1) {
						board[(y-dy)/lr][(x-dx)/lr] = 'D';
					}
					break;
				case 3:
					if ((x-dx)/lr != 0 && (y-dy)/lr != 0 && (x-dx)/lr != board.length - 1 && (y-dy)/lr != board.length - 1) {
						board[(y-dy)/lr][(x-dx)/lr] = 'E';
						button_sword.setEnabled(false);
						setCursor(Cursor.getDefaultCursor());
						cursor = -1;
					}
					break;
				case 4:
					if ((x-dx)/lr == 0 || (y-dy)/lr == 0 || (x-dx)/lr == board.length - 1 || (y-dy)/lr == board.length - 1) {
						board[(y-dy)/lr][(x-dx)/lr] = 'S';
						button_exit.setEnabled(false);
						setCursor(Cursor.getDefaultCursor());
						cursor = -1;
					}
					break;
				
				}
				
			else if (SwingUtilities.isRightMouseButton(e) && (x-dx)/lr != 0 && (y-dy)/lr != 0 && (x-dx)/lr != board.length - 1 && (y-dy)/lr != board.length - 1) {
				board[(y-dy)/lr][(x-dx)/lr] = ' ';
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
				if(board[i][a] == 'E') {
					n_sword = 1;
				}
				if(board[i][a] == 'S') {
					n_exit = 1;
				}
			}
		}
		
		if (n_hero == 0) {
			button_hero.setEnabled(true);
		}
		if (n_sword == 0) {
			button_sword.setEnabled(true);
		}
		if (n_exit == 0) {
			button_exit.setEnabled(true);
		}
		
		
		repaint();
	}
	
	public void buttonAction(String image, String name) {
		int l = (int) Math.round(length);
		
		ImageIcon icon = new ImageIcon(image);
		
		if (l >= 32) {
			setCursor(Toolkit.getDefaultToolkit().createCustomCursor(icon.getImage(), new java.awt.Point(16,16), name));
		}
		else {
			BufferedImage bi = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB); 
			Graphics g = bi.createGraphics();
			Color transparent = new Color(0,0,0,0);
			g.setColor(transparent);
			g.drawRect(0, 0, 32, 32);
			g.drawImage(icon.getImage(), 0, 0, l, l, null); 
			setCursor(Toolkit.getDefaultToolkit().createCustomCursor(bi, new java.awt.Point(l/2,l/2), name));
		}

		if (image == "wall.jpg") {
			cursor = 0;
		}
		else if (image == "hero_small.jpg") {
			cursor = 1;
		}
		else if (image == "dragon.jpg") {
			cursor = 2;
		}
		else if (image == "sword_floor.jpg") {
			cursor = 3;
		}
		else if (image == "gate_closed_top.jpg") {
			cursor = 4;
		}
		
		
	}

	public char[][] getBoard() {
		return board;
	}
	
	private void buttonConfig(JButton b, int font_size) {
		b.setBackground(color_blue);
		b.setForeground(new Color(255,255,255));
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
