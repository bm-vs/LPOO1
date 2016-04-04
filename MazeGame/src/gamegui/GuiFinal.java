package gamegui;

import gamelogic.Dragon;
import gamelogic.Game;
import gamelogic.Hero;
import gamelogic.Maze;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;


public class GuiFinal {

	private JFrame frame;
	private Game game;
	private JPanel menu;
	private JPanel configuration;
	private JButton button_exit;
	private JTextField input_maze_size;
	private JTextField input_number_dragons;
	private JComboBox<String> input_dragon_type;
	private JLabel maze_size;
	private JLabel dragon_type;
	private JLabel config_status;
	private JLabel number_dragons;
	private JButton button_save;
	private int size = 11;
	private int dragons = 1;
	private int build_size = 11;
	private int frame_width = 950;
	private int frame_height = 720;
	private Color color_pine = new Color(32,62,71);
	private Color color_blue = new Color(76,181,245);
	private Color color_lime = new Color(179,193,0);
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiFinal window = new GuiFinal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GuiFinal() {
		initialize();	
	}

	private void initialize() {

		// =================================================================================================
		// WINDOW
		
		game = new Game();
		game.setGameMode("2");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Maze game");
		frame.setAutoRequestFocus(false);
		if (width < frame_width || height < frame_height) {
			frame.setBounds(0, 0, 950, 720);
		}
		else {
			frame.setBounds((width-frame_width)/2, (height-frame_height)/2, 950, 720);
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		
		// =================================================================================================
		// MENU
		
		
		menu = new JPanel();
		menu.setBackground(color_pine);
		frame.getContentPane().add(menu, "menu");
		menu.setLayout(null);
		
		// =================================================================================================
		// PLAY
		
		JButton button_play = new JButton("JOGAR");
		button_play.setBounds(375, 125, 200, 60);
		buttonConfig(button_play, 15);		
		menu.add(button_play);
		button_play.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(button_play, e);
		    }
		});
		
		button_play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setMaze(new Maze(size));
				game.setHero(new Hero(game.getMaze()));
				game.clearDragons();

				for (int i = 0; i < dragons; i++) {
					game.addDragon(new Dragon(game.getMaze()));
				}
				
				GamePanel game_panel = new GamePanel(game);
				game_panel.setLayout(null);
				
				JButton button_back = new JButton("VOLTAR");
				button_back.setBounds(10, 10, 100, 30);
				buttonConfig(button_back, 12);
				game_panel.add(button_back);
				button_back.getModel().addChangeListener(new ChangeListener() {
				    public void stateChanged(ChangeEvent e) {
				    	buttonHoverAction(button_back, e);
				    }
				});
				button_back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {					
						CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
						cl.show(frame.getContentPane(), "menu");
					}});
				
				
				frame.getContentPane().add(game_panel, "play");
				
				CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
				cl.show(frame.getContentPane(), "play");
				
				game_panel.repaint();
				game_panel.requestFocus();
			}
		});
		
		
		// =================================================================================================
		// CONFIGURATION
		
		JButton button_configuration = new JButton("CONFIGURAR");
		button_configuration.setBounds(375, 325, 200, 60);
		buttonConfig(button_configuration, 15);		
		menu.add(button_configuration);
		button_configuration.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(button_configuration, e);
		    }
		});
		button_configuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
				cl.show(frame.getContentPane(), "configuration");
			}
		});
		
		
		
		configuration = new JPanel();
		configuration.setBackground(color_pine);
		frame.getContentPane().add(configuration, "configuration");
		configuration.setLayout(null);

		maze_size = new JLabel("DIMENS\u00C3O DO LABIRINTO");
		maze_size.setForeground(Color.WHITE);
		maze_size.setFont(new Font("Design", Font.BOLD, 14));
		maze_size.setBounds(260, 146, 220, 19);
		configuration.add(maze_size);

		input_maze_size = new JTextField();
		textFieldConfig(input_maze_size);
		input_maze_size.setText(Integer.toString(size));
		input_maze_size.setBounds(490, 145, 40, 20);
		configuration.add(input_maze_size);
		input_maze_size.setColumns(3);

		number_dragons = new JLabel("N\u00DAMERO DE DRAG\u00D5ES");
		number_dragons.setForeground(Color.WHITE);
		number_dragons.setFont(new Font("Design", Font.BOLD, 14));
		number_dragons.setBounds(260, 182, 220, 19);
		configuration.add(number_dragons);

		input_number_dragons = new JTextField();
		textFieldConfig(input_number_dragons);
		input_number_dragons.setText(Integer.toString(dragons));
		input_number_dragons.setColumns(3);
		input_number_dragons.setBounds(490, 181, 40, 20);
		configuration.add(input_number_dragons);
		

		dragon_type = new JLabel("TIPO DE DRAG\u00D5ES");
		dragon_type.setForeground(Color.WHITE);
		dragon_type.setFont(new Font("Design", Font.BOLD, 14));
		dragon_type.setBounds(260, 217, 220, 19);
		configuration.add(dragon_type);

		input_dragon_type = new JComboBox<String>();
		input_dragon_type.setUI(new ComboBoxUI());
		comboBoxConfig(input_dragon_type, 14, color_pine);
		input_dragon_type.setBounds(490, 216, 200, 20);
		configuration.add(input_dragon_type);


		Object child = input_dragon_type.getAccessibleContext().getAccessibleChild(0);
		BasicComboPopup popup = (BasicComboPopup)child;
		JList<?> list = popup.getList();
		list.setSelectionBackground(color_lime);
		list.setBackground(color_pine);
		list.setForeground(Color.WHITE);
		list.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		input_dragon_type.addItem("EST\u00C1TICOS");
		input_dragon_type.addItem("MOVER");
		input_dragon_type.addItem("MOVER E ADORMECER");

		
		config_status = new JLabel("");
		config_status.setForeground(color_lime);
		config_status.setFont(new Font("Design", Font.BOLD, 14));
		config_status.setBounds(260, 290, 341, 20);
		configuration.add(config_status);
		
		
		button_save = new JButton("GUARDAR");
		buttonConfig(button_save, 12);
		button_save.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(button_save, e);
		    }
		});
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					size = Integer.parseInt(input_maze_size.getText());
					dragons = Integer.parseInt(input_number_dragons.getText());
				} catch (Exception exc) {
					config_status.setText("ARGUMENTOS INV\u00C1LIDOS");
					return;
				}
				
				input_number_dragons.setText(Integer.toString(dragons));
				input_maze_size.setText(Integer.toString(size));
				

				if (size * size < 50.0 && dragons == 1)
					config_status.setText("");
				else if (dragons <= size * size / 50.0 && dragons > 0) {
					config_status.setText("");
				} else {
					config_status.setText("N\u00DAMERO DE DRAG\u00D5ES INV\u00C1LIDO");
					return;
				}

				if (size < 5 || size > 100) {
					config_status.setText("DIMENS\u00C3O DO LABIRINTO INV\u00C1LIDO");
					return;
				}
				
				if (input_dragon_type.getSelectedItem().equals(input_dragon_type.getItemAt(0))) {
					game.setGameMode("2");
				} else if (input_dragon_type.getSelectedItem().equals(input_dragon_type.getItemAt(1))) {
					game.setGameMode("0");
				} else {
					game.setGameMode("1");
				}

				CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
				cl.show(frame.getContentPane(), "menu");
			}
		});
		button_save.setBounds(416, 342, 118, 37);
		configuration.add(button_save);
		
		// ===================================================================================================
		// BUILD
		
		JButton button_build = new JButton("CONSTRUIR");
		button_build.setBounds(375, 225, 200, 60);
		buttonConfig(button_build, 15);
		menu.add(button_build);
		button_build.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(button_build, e);
		    }
		});
		
		button_build.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				JDialog dialog = new JDialog(frame, null, true, null);
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int width = (int) screenSize.getWidth();
				int height = (int) screenSize.getHeight();
				
				int dialog_width = 340;
				int dialog_height = 125;
				
				if (width < dialog_width || height < dialog_height) {
					dialog.setBounds(0, 0, dialog_width, dialog_height);
				}
				else {
					dialog.setBounds((width-frame_width)/2 + 305, (height-frame_height)/2 + 298, dialog_width, dialog_height);
				}
				
				JPanel dialog_panel = new JPanel();
				dialog_panel.setLayout(null);
				dialog_panel.setBackground(color_pine);
				
				JLabel dialog_maze_size = new JLabel("DIMENS\u00C3O DO LABIRINTO");
				dialog_maze_size.setForeground(Color.WHITE);
				dialog_maze_size.setFont(new Font("Design", Font.BOLD, 14));
				dialog_maze_size.setBounds(10, 25, 220, 19);
				dialog_maze_size.setBackground(color_pine);
				dialog_panel.add(dialog_maze_size);
				
				
				JTextField dialog_input_maze_size = new JTextField();
				textFieldConfig(dialog_input_maze_size);
				dialog_input_maze_size.setText(Integer.toString(build_size));
				dialog_input_maze_size.setBounds(245, 25, 40, 20);
				dialog_input_maze_size.setColumns(3);
				dialog_panel.add(dialog_input_maze_size);
				
				JButton dialog_ok = new JButton();
				dialog_ok.setText("OK");
				buttonConfig(dialog_ok, 12);
				dialog_ok.setBounds(245, 65, 80, 25);
				dialog_ok.getModel().addChangeListener(new ChangeListener() {
				    public void stateChanged(ChangeEvent e) {
				    	buttonHoverAction(dialog_ok, e);
				    }
				});
				
				dialog_ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input_size = dialog_input_maze_size.getText();
						int size;
						
						if (input_size == null) {
							return;
						}
						
						try {
							size = Integer.parseInt(input_size);
						} catch (Exception exc) {
							return;
						}
						
						if (size < 5 || size > 100) {
							return;
						}
						
						build_size = size;
						dialog_input_maze_size.setText(Integer.toString(build_size));
						
						dialog.setVisible(false);
						dialog.dispose();
						
						char[][] board = new char[size][size];
						for (int i = 0; i < size; i++) {
							for (int a = 0; a < size; a++) {
								if (i == 0 || a == 0 || a == size - 1 || i == size - 1) {
									board[a][i] = 'X';
								}
								else {
									board[a][i] = ' ';
								}
							}
						}
						
						BuildPanel build = new BuildPanel(board);
						frame.getContentPane().add(build, "build");
						
						input_dragon_type = new JComboBox<String>();
						input_dragon_type.setUI(new ComboBoxUI2());
						input_dragon_type.setBounds(570, 10, 140, 30);
						comboBoxConfig(input_dragon_type, 12, color_blue);
						input_dragon_type.addItem("TIPO DE DRAG\u00D5ES");
						input_dragon_type.addItem("EST\u00C1TICOS");
						input_dragon_type.addItem("MOVER");
						input_dragon_type.addItem("MOVER E ADORMECER");
						build.add(input_dragon_type);
						
						Object child = input_dragon_type.getAccessibleContext().getAccessibleChild(0);
						BasicComboPopup popup = (BasicComboPopup)child;
						JList<?> list = popup.getList();
						list.setSelectionBackground(color_lime);
						list.setBackground(color_pine);
						list.setForeground(Color.WHITE);
						list.setBorder(javax.swing.BorderFactory.createEmptyBorder());
						
						JButton button_back = new JButton("VOLTAR");
						buttonConfig(button_back, 12);
						button_back.getModel().addChangeListener(new ChangeListener() {
						    public void stateChanged(ChangeEvent e) {
						    	buttonHoverAction(button_back, e);
						    }
						});
						build.add(button_back);
						button_back.setBounds(710, 10, 112, 30);
						button_back.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {					
								CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
								cl.show(frame.getContentPane(), "menu");
							}});
						
						
						
						JButton button_play = new JButton("JOGAR");
						buttonConfig(button_play, 12);
						button_play.getModel().addChangeListener(new ChangeListener() {
						    public void stateChanged(ChangeEvent e) {
						    	buttonHoverAction(button_play, e);
						    }
						});
						build.add(button_play);
						button_play.setBounds(822, 10, 112, 30);
						button_play.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									game = new Game(build.getBoard());
								}
								catch (Exception exc){
									return;
								}
								// Check selected item
								if (input_dragon_type.getSelectedItem().equals(input_dragon_type.getItemAt(0))) {
									return;
								}
								
								if (input_dragon_type.getSelectedItem().equals(input_dragon_type.getItemAt(1))) {
									game.setGameMode("2");
								} else if (input_dragon_type.getSelectedItem().equals(input_dragon_type.getItemAt(2))) {
									game.setGameMode("0");
								} else {
									game.setGameMode("1");
								}
								
								GamePanel game_panel = new GamePanel(game);
								game_panel.setLayout(null);
								
								JButton button_back = new JButton("VOLTAR");
								buttonConfig(button_back, 12);
								button_back.getModel().addChangeListener(new ChangeListener() {
								    public void stateChanged(ChangeEvent e) {
								    	buttonHoverAction(button_back, e);
								    }
								});
								game_panel.add(button_back);
								button_back.setBounds(10, 10, 100, 30);
								button_back.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {					
										CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
										cl.show(frame.getContentPane(), "menu");
									}});
								
								frame.getContentPane().add(game_panel, "play");
								
								CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
								cl.show(frame.getContentPane(), "play");
								
								game_panel.repaint();
								game_panel.requestFocus();			
							}});
						
						
						
						CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
						cl.show(frame.getContentPane(), "build");
					}});
				
				dialog_panel.add(dialog_ok);
				dialog.add(dialog_panel);
				dialog.setResizable(false);
				dialog.setVisible(true);
			}
		});
		

		// ===================================================================================================
		// TERMINAR


		button_exit = new JButton("SAIR");
		button_exit.setBounds(375, 425, 200, 60);
		buttonConfig(button_exit, 15);		
		menu.add(button_exit);
		button_exit.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	buttonHoverAction(button_exit, e);
		    }
		});
		
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
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
	
	private void textFieldConfig(JTextField t) {
		t.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		t.setSelectedTextColor(color_pine);
		t.setSelectionColor(color_lime);
		t.setBackground(color_pine);
		t.setForeground(Color.WHITE);
		t.setFont(new Font("Design", Font.BOLD, 14));
		t.setCaretColor(t.getBackground());
	}
	
	private void comboBoxConfig(JComboBox<String> c, int font_size, Color bc) {
		c.setBackground(bc);
		c.setForeground(Color.WHITE);
		c.setFont(new Font("Design", Font.BOLD, font_size));
		if (bc.equals(color_blue)) {
			Border border = new LineBorder(Color.WHITE, 2);
			c.setBorder(border);
		}
		else {
			c.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		}
	
	}
	
	private class ComboBoxArrow extends BasicArrowButton {
		private static final long serialVersionUID = 1L;
		
        public ComboBoxArrow(int direction, Color background, Color shadow,
                Color darkShadow, Color highlight) {
        	super(direction, background, shadow, darkShadow, highlight);
        }
        
		@Override
		public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled) {
		    Color shadow = UIManager.getColor("controlShadow");
		    Color highlight = UIManager.getColor("controlLtHighlight");
			
			Color oldColor = g.getColor();
			int mid, i, j;

			j = 0;
			size = Math.max(size, 2);
			mid = (size / 2) - 1;

			g.translate(x, y);
			if (isEnabled)
				g.setColor(Color.WHITE);
			else
				g.setColor(Color.WHITE);

			switch (direction) {
			case NORTH:
				for (i = 0; i < size; i++) {
					g.drawLine(mid - i, i, mid + i, i);
				}
				if (!isEnabled) {
					g.setColor(highlight);
					g.drawLine(mid - i + 2, i, mid + i, i);
				}
				break;
			case SOUTH:
				if (!isEnabled) {
					g.translate(1, 1);
					g.setColor(highlight);
					for (i = size - 1; i >= 0; i--) {
						g.drawLine(mid - i, j, mid + i, j);
						j++;
					}
					g.translate(-1, -1);
					g.setColor(shadow);
				}

				j = 0;
				for (i = size - 1; i >= 0; i--) {
					g.drawLine(mid - i, j, mid + i, j);
					j++;
				}
				break;
			case WEST:
				for (i = 0; i < size; i++) {
					g.drawLine(i, mid - i, i, mid + i);
				}
				if (!isEnabled) {
					g.setColor(highlight);
					g.drawLine(i, mid - i + 2, i, mid + i);
				}
				break;
			case EAST:
				if (!isEnabled) {
					g.translate(1, 1);
					g.setColor(highlight);
					for (i = size - 1; i >= 0; i--) {
						g.drawLine(j, mid - i, j, mid + i);
						j++;
					}
					g.translate(-1, -1);
					g.setColor(shadow);
				}

				j = 0;
				for (i = size - 1; i >= 0; i--) {
					g.drawLine(j, mid - i, j, mid + i);
					j++;
				}
				break;
			}
			g.translate(-x, -y);
			g.setColor(oldColor);
		}
	}

	private class ComboBoxUI extends BasicComboBoxUI {
		@Override
		public ComboBoxArrow createArrowButton() {
			ComboBoxArrow a = new ComboBoxArrow(SwingConstants.SOUTH, color_pine, color_pine, color_pine, color_pine);
			
			return a;
		}
	}
	
	private class ComboBoxUI2 extends BasicComboBoxUI {
		@Override
		public ComboBoxArrow createArrowButton() {
			ComboBoxArrow a = new ComboBoxArrow(SwingConstants.SOUTH, color_blue, color_blue, color_blue, color_blue);
			
			return a;
		}
	}
	
}
