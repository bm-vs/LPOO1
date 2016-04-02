package gamegui;

import gamelogic.GamePanel;
import gamelogic.BuildPanel;
import gamelogic.Dragon;
import gamelogic.Game;
import gamelogic.Hero;
import gamelogic.Maze;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class GuiFinal {

	private JFrame frame;
	private Game game;
	private JPanel menu;
	private JPanel configuration;
	private JFrame menu_frame;
	private JFrame configurar_frame;
	private JButton jogar_button;
	private JButton construir_button;
	private JButton configurar_button;
	private JButton button_exit;
	private JTextField input_maze_size;
	private JTextField input_number_dragons;
	private JComboBox<String> input_dragon_type;
	private JLabel maze_size;
	private JLabel dragon_type;
	private JLabel estado;
	private JLabel number_dragons;
	private JButton save_button;
	private JFrame jogo;
	private JButton move_up;
	private JButton move_down;
	private JButton move_left;
	private JButton move_right;
	private JTextArea maze_area;
	private JPanel build;
	private JFrame popup;
	private JTextField input_size;
	private int size = 11;
	private int dragons = 1;
	private String default_dragon_type = "Estáticos";
	
	
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public GuiFinal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// =================================================================================================
		// WINDOW
		
		game = new Game();
		game.game_mode = "2";
		
		frame = new JFrame();
		frame.setTitle("Maze game");
		frame.setAutoRequestFocus(false);
		frame.setBounds(100, 100, 960, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		
		// =================================================================================================
		// MENU
		
		
		menu = new JPanel();
		frame.getContentPane().add(menu, "menu");
		menu.setLayout(null);
		
		
		/*
		JButton play_button = new JButton("Jogar");
		play_button.setBounds(350, 125, 200, 60);
		menu.add(play_button);
		play_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
				
				cl.show(frame.getContentPane(), "menu2");
			}
		});
		

		JPanel menu2 = new JPanel();
		frame.getContentPane().add(menu2, "menu2");
		menu2.setLayout(null);
		
		
		JButton play_button2 = new JButton("Jugar");
		play_button2.setBounds(350, 125, 200, 60);
		menu2.add(play_button2);
		play_button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
				cl.show(frame.getContentPane(), "menu");
			}
		});
		*/
		
		// =================================================================================================
		// PLAY
		
		JButton button_play = new JButton("Jogar");
		button_play.setBounds(350, 125, 200, 60);
		menu.add(button_play);
		button_play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				game.maze = new Maze(size);
				game.hero = new Hero(game.maze);

				game.dragons.clear();

				for (int i = 0; i < dragons; i++) {
					game.dragons.add(new Dragon(game.maze));
				}
				
				GamePanel game_panel = new GamePanel(game);
				game_panel.setLayout(null);
				
				JButton button_back = new JButton("Voltar");
				game_panel.add(button_back);
				button_back.setBounds(0, 0, 100, 30);
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
		
		JButton button_configuration = new JButton("Configurar");
		button_configuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
				cl.show(frame.getContentPane(), "configuration");
			}
		});
		button_configuration.setBounds(350, 325, 200, 60);
		menu.add(button_configuration);
		
		
		configuration = new JPanel();
		frame.getContentPane().add(configuration, "configuration");
		configuration.setLayout(null);

		maze_size = new JLabel("Dimens\u00E3o do labirinto");
		maze_size.setBounds(52, 42, 162, 14);
		configuration.add(maze_size);

		input_maze_size = new JTextField();
		input_maze_size.setText(Integer.toString(size));
		input_maze_size.setBounds(242, 36, 65, 20);
		configuration.add(input_maze_size);
		input_maze_size.setColumns(10);

		number_dragons = new JLabel("N\u00FAmero de drag\u00F5es");
		number_dragons.setBounds(52, 78, 162, 14);
		configuration.add(number_dragons);

		input_number_dragons = new JTextField();
		input_number_dragons.setText(Integer.toString(dragons));
		input_number_dragons.setColumns(10);
		input_number_dragons.setBounds(242, 72, 65, 20);
		configuration.add(input_number_dragons);

		estado = new JLabel("You can play!");
		estado.setBounds(52, 214, 191, 20);
		configuration.add(estado);

		dragon_type = new JLabel("Tipo de drag\u00F5es");
		dragon_type.setBounds(51, 115, 162, 14);
		configuration.add(dragon_type);

		input_dragon_type = new JComboBox<String>();
		input_dragon_type.setBounds(241, 109, 138, 20);
		configuration.add(input_dragon_type);

		input_dragon_type.addItem("Estáticos");
		input_dragon_type.addItem("Mover");
		input_dragon_type.addItem("Mover e adormecer");

		save_button = new JButton("Guardar");
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					size = Integer.parseInt(input_maze_size.getText());
					dragons = Integer.parseInt(input_number_dragons.getText());
					estado.setText("You can play!");
				} catch (Exception exc) {
					estado.setText("Invalid argument!");
					return;
				}

				if (size * size < 50.0 && dragons == 1)
					estado.setText("You can play!");

				else if (dragons <= size * size / 50.0 && dragons > 0) {
					estado.setText("You can play!");
				} else {
					estado.setText("Invalid number of dragons!");
					return;
				}

				if (size < 5 || size > 100) {
					estado.setText("Invalid size!");
					return;
				}
				
				if (input_dragon_type.getSelectedItem().equals(input_dragon_type.getItemAt(0))) {
					game.game_mode = "2";
				} else if (input_dragon_type.getSelectedItem().equals(input_dragon_type.getItemAt(1))) {
					game.game_mode = "0";
				} else {
					game.game_mode = "1";
				}

				CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
				cl.show(frame.getContentPane(), "menu");
			}
		});
		save_button.setBounds(261, 209, 118, 30);
		configuration.add(save_button);
		
		
		/*
		// =================================================================================================
		// JOGO
		jogo = new JFrame();
		jogo.setTitle("jogo");
		jogo.setAutoRequestFocus(false);
		jogo.setBounds(100, 50, 620, 640);
		jogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		maze_area = new JTextArea();
		maze_area.setFont(new Font("Courier", Font.PLAIN, 13));
		maze_area.setBounds(0, 0, 300, 300);
		
		
		jogar_button = new JButton("Jogar");
		jogar_button.setBounds(350, 125, 200, 60);
		menu_frame.getContentPane().add(jogar_button);
		jogar_button.setEnabled(false);
		jogar_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				game.maze = new Maze(size);
				game.hero = new Hero(game.maze);

				game.dragons.clear();

				for (int i = 0; i < dragons; i++) {
					game.dragons.add(new Dragon(game.maze));
				}
				jogo.setVisible(true);
				
				
				GamePanel game_panel = new GamePanel(game);
				jogo.getContentPane().add(game_panel);
				game_panel.repaint();
				
				menu_frame.setVisible(false);
				game_panel.requestFocus();
			}
		});

		// =================================================================================================
		// CONFIGURAR
		
		
		configurar_button = new JButton("Configurar");
		configurar_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_frame.setVisible(false);
				configurar_frame.setVisible(true);
			}
		});
		configurar_button.setBounds(350, 325, 200, 60);
		menu_frame.getContentPane().add(configurar_button);
		
		
		configurar_frame = new JFrame();
		configurar_frame.setTitle("Configurar");
		configurar_frame.setAutoRequestFocus(false);
		configurar_frame.setBounds(100, 100, 450, 300);
		configurar_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		configurar_frame.getContentPane().setLayout(null);

		maze_size = new JLabel("Dimens\u00E3o do labirinto");
		maze_size.setBounds(52, 42, 162, 14);
		configurar_frame.getContentPane().add(maze_size);

		input_maze_size = new JTextField();
		input_maze_size.setText("11");
		input_maze_size.setBounds(242, 36, 65, 20);
		configurar_frame.getContentPane().add(input_maze_size);
		input_maze_size.setColumns(10);

		number_dragons = new JLabel("N\u00FAmero de drag\u00F5es");
		number_dragons.setBounds(52, 78, 162, 14);
		configurar_frame.getContentPane().add(number_dragons);

		input_number_dragons = new JTextField();
		input_number_dragons.setText("1");
		input_number_dragons.setColumns(10);
		input_number_dragons.setBounds(242, 72, 65, 20);
		configurar_frame.getContentPane().add(input_number_dragons);

		estado = new JLabel("You can play!");
		estado.setBounds(52, 214, 191, 20);
		configurar_frame.getContentPane().add(estado);

		dragon_type = new JLabel("Tipo de drag\u00F5es");
		dragon_type.setBounds(51, 115, 162, 14);
		configurar_frame.getContentPane().add(dragon_type);

		input_dragon_type = new JComboBox<String>();
		input_dragon_type.setBounds(241, 109, 138, 20);
		configurar_frame.getContentPane().add(input_dragon_type);

		input_dragon_type.addItem("Estáticos");
		input_dragon_type.addItem("Mover");
		input_dragon_type.addItem("Mover e adormecer");

		guardar_button = new JButton("Guardar");
		guardar_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer.parseInt(input_maze_size.getText());
					Integer.parseInt(input_number_dragons.getText());
					estado.setText("You can play!");
				} catch (Exception exc) {
					estado.setText("Invalid argument!");
					return;
				}

				size = Integer.parseInt(input_maze_size.getText());
				dragons = Integer.parseInt(input_number_dragons.getText());

				if (size * size < 50.0 && dragons == 1)
					estado.setText("You can play!");

				else if (dragons <= size * size / 50.0 && dragons > 0) {
					estado.setText("You can play!");
				} else {
					estado.setText("Invalid number of dragons!");
					return;
				}

				if (size < 5 || size > 100) {
					estado.setText("Invalid size!");
					return;
				}
				
				if (input_dragon_type.getSelectedItem().equals(input_dragon_type.getItemAt(0))) {
					game.game_mode = "2";
				} else if (input_dragon_type.getSelectedItem().equals(input_dragon_type.getItemAt(1))) {
					game.game_mode = "0";
				} else {
					game.game_mode = "1";
				}

				configurar_frame.setVisible(false);
				menu_frame.setVisible(true);
				jogar_button.setEnabled(true);
			}
		});
		
		guardar_button.setBounds(261, 209, 118, 30);
		configurar_frame.getContentPane().add(guardar_button);
		*/
		// ===================================================================================================
		// BUILD
		
		JButton button_build = new JButton("Construir");
		button_build.setBounds(350, 225, 200, 60);
		menu.add(button_build);
		
		button_build.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popup.setVisible(true);
				popup.setAutoRequestFocus(true);
			}
		});
		
		
		build = new JPanel();
		frame.getContentPane().add(build, "build");
		build.setLayout(null);
		
		popup = new JFrame();
		popup.setAutoRequestFocus(true);
		popup.setBounds(100, 50, 272, 170);
		popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		popup.getContentPane().setLayout(null);
		
		JLabel size = new JLabel("Tamanho");
		size.setBounds(25, 41, 56, 24);
		popup.getContentPane().add(size);
		
		
		input_size = new JTextField();
		input_size.setText("11");
		input_size.setBounds(91, 43, 86, 20);
		popup.getContentPane().add(input_size);
		input_size.setColumns(10);
		
		
		JButton button_ok = new JButton("OK");
		button_ok.setBounds(157, 98, 89, 23);
		popup.getContentPane().add(button_ok);
		button_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int size;
				
				try {
					size = Integer.parseInt(input_size.getText());
				} catch (Exception exc) {
					return;
				}
				
				if (size < 5 || size > 100) {
					return;
				}
				
				
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
				
				popup.dispose();
				
				BuildPanel build = new BuildPanel(board);
				frame.getContentPane().add(build, "build");
				
				dragon_type = new JLabel("Tipo de drag\u00F5es");
				dragon_type.setBounds(0, 40, 162, 14);
				build.add(dragon_type);

				input_dragon_type = new JComboBox<String>();
				input_dragon_type.setBounds(140, 40, 138, 20);
				build.add(input_dragon_type);

				input_dragon_type.addItem("Estáticos");
				input_dragon_type.addItem("Mover");
				input_dragon_type.addItem("Mover e adormecer");
				
				JButton button_play = new JButton("Jogar");
				build.add(button_play);
				button_play.setBounds(500, 0, 100, 30);
				button_play.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						game = new Game(build.board);
						
						if (input_dragon_type.getSelectedItem().equals(input_dragon_type.getItemAt(0))) {
							game.game_mode = "2";
						} else if (input_dragon_type.getSelectedItem().equals(input_dragon_type.getItemAt(1))) {
							game.game_mode = "0";
						} else {
							game.game_mode = "1";
						}
						
						GamePanel game_panel = new GamePanel(game);
						game_panel.setLayout(null);
						
						JButton button_back = new JButton("Voltar");
						game_panel.add(button_back);
						button_back.setBounds(0, 0, 100, 30);
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
			}
		});
		

		// ===================================================================================================
		// TERMINAR


		button_exit = new JButton("Sair");
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		button_exit.setBounds(350, 425, 200, 60);
		menu.add(button_exit);
	}
}
