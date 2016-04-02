package gamegui;

import gamelogic.GamePanel;
import gamelogic.BuildPanel;
import gamelogic.Dragon;
import gamelogic.Game;
import gamelogic.Hero;
import gamelogic.Maze;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Font;

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
	private JButton save_button;
	private JFrame popup;
	private JTextField input_size;
	private int size = 11;
	private int dragons = 1;
	
	
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
		
		int max = 0;
		int m = 0;
		int min = 600;
		int n = 0;
		
		for (int i = 5; i <= 100; i++) {
			if ((600 % i) > max) {
				max = (600 % i);
				m = i;
			}
			if ((600 % i) < min && i >= 15) {
				min = (600 % i);
				n = i;
			}
			
		}
		
		System.out.println("max" + m + " - " + max);
		System.out.println("min" + n + " - " + min);	
	}

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
		
		// =================================================================================================
		// PLAY
		
		JButton button_play = new JButton("Jogar");
		button_play.setBounds(380, 125, 200, 60);
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
		button_configuration.setBounds(380, 325, 200, 60);
		menu.add(button_configuration);
		
		
		configuration = new JPanel();
		frame.getContentPane().add(configuration, "configuration");
		configuration.setLayout(null);

		maze_size = new JLabel("Dimens\u00E3o do labirinto");
		maze_size.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maze_size.setBounds(310, 146, 175, 19);
		configuration.add(maze_size);

		input_maze_size = new JTextField();
		input_maze_size.setFont(new Font("Tahoma", Font.PLAIN, 14));
		input_maze_size.setText(Integer.toString(size));
		input_maze_size.setBounds(513, 145, 65, 20);
		configuration.add(input_maze_size);
		input_maze_size.setColumns(10);

		number_dragons = new JLabel("N\u00FAmero de drag\u00F5es");
		number_dragons.setFont(new Font("Tahoma", Font.PLAIN, 14));
		number_dragons.setBounds(310, 182, 175, 19);
		configuration.add(number_dragons);

		input_number_dragons = new JTextField();
		input_number_dragons.setFont(new Font("Tahoma", Font.PLAIN, 14));
		input_number_dragons.setText(Integer.toString(dragons));
		input_number_dragons.setColumns(10);
		input_number_dragons.setBounds(513, 181, 65, 20);
		configuration.add(input_number_dragons);

		config_status = new JLabel("");
		config_status.setBounds(310, 280, 341, 20);
		configuration.add(config_status);

		dragon_type = new JLabel("Tipo de drag\u00F5es");
		dragon_type.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dragon_type.setBounds(310, 217, 163, 19);
		configuration.add(dragon_type);

		input_dragon_type = new JComboBox<String>();
		input_dragon_type.setFont(new Font("Tahoma", Font.PLAIN, 14));
		input_dragon_type.setBounds(513, 216, 138, 20);
		configuration.add(input_dragon_type);

		input_dragon_type.addItem("Est\u00E1ticos");
		input_dragon_type.addItem("Mover");
		input_dragon_type.addItem("Mover e adormecer");

		save_button = new JButton("Guardar");
		save_button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					size = Integer.parseInt(input_maze_size.getText());
					dragons = Integer.parseInt(input_number_dragons.getText());
				} catch (Exception exc) {
					config_status.setText("Invalid arguments");
					return;
				}

				if (size * size < 50.0 && dragons == 1)
					config_status.setText("");
				else if (dragons <= size * size / 50.0 && dragons > 0) {
					config_status.setText("");
				} else {
					config_status.setText("Invalid number of dragons");
					return;
				}

				if (size < 5 || size > 100) {
					config_status.setText("Invalid maze size");
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
		save_button.setBounds(421, 332, 118, 37);
		configuration.add(save_button);
		
		// ===================================================================================================
		// BUILD
		
		JButton button_build = new JButton("Construir");
		button_build.setBounds(380, 225, 200, 60);
		menu.add(button_build);
		
		button_build.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popup.setVisible(true);
				popup.setAutoRequestFocus(true);
			}
		});
		
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

				input_dragon_type.addItem("Est\u00E1ticos");
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
		button_exit.setBounds(380, 425, 200, 60);
		menu.add(button_exit);
	}
}
