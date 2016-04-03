package gamegui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import gamelogic.*;

import java.awt.Font;

public class Gui {

	private JFrame frmMazeGame;
	private JTextField maze_size_input;
	private JTextField number_dragons_input;
	private JComboBox<String> dragon_type_input;
	private JLabel dragon_type;
	private JLabel estado;
	private JLabel number_dragons;
	private JLabel maze_size;
	private Game game;  
	private JButton move_up;
	private JButton move_down;
	private JButton move_left;
	private JButton move_right;
	private JTextArea maze_area;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmMazeGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Gui() {
		initialize();
	}


	private void initialize() {
		game = new Game();

		frmMazeGame = new JFrame();
		frmMazeGame.getContentPane().setForeground(Color.BLACK);
		frmMazeGame.setTitle("Maze Game");
		frmMazeGame.setBounds(100, 100, 900, 600);
		frmMazeGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMazeGame.getContentPane().setLayout(null);

		maze_size = new JLabel("Dimens\u00E3o do labirinto");
		maze_size.setBounds(52, 42, 162, 14);
		frmMazeGame.getContentPane().add(maze_size);

		maze_size_input = new JTextField();
		maze_size_input.setText("11");
		maze_size_input.setBounds(224, 39, 65, 20);
		frmMazeGame.getContentPane().add(maze_size_input);
		maze_size_input.setColumns(10);

		number_dragons = new JLabel("N\u00FAmero de drag\u00F5es");
		number_dragons.setBounds(52, 78, 162, 14);
		frmMazeGame.getContentPane().add(number_dragons);

		number_dragons_input = new JTextField();
		number_dragons_input.setText("1");
		number_dragons_input.setColumns(10);
		number_dragons_input.setBounds(224, 75, 65, 20);
		frmMazeGame.getContentPane().add(number_dragons_input);

		estado = new JLabel("You can play!");
		estado.setBounds(62, 536, 150, 14);
		frmMazeGame.getContentPane().add(estado);

		dragon_type = new JLabel("Tipo de drag\u00F5es");
		dragon_type.setBounds(51, 115, 162, 14);
		frmMazeGame.getContentPane().add(dragon_type);

		dragon_type_input = new JComboBox<String>();
		dragon_type_input.setBounds(223, 112, 138, 20);
		frmMazeGame.getContentPane().add(dragon_type_input);
		dragon_type_input.addItem("Est\u00E1ticos");
		dragon_type_input.addItem("Mover");
		dragon_type_input.addItem("Mover e adormecer");

		maze_area = new JTextArea();
		maze_area.setFont(new Font("Courier", Font.PLAIN, 13));
		maze_area.setBounds(52, 155, 422, 379);
		frmMazeGame.getContentPane().add(maze_area);

		move_up = new JButton("Cima");
		move_left = new JButton("Esquerda");
		move_down = new JButton("Baixo");
		move_right = new JButton("Direita");

		move_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyAction("w");
			}});

		move_up.setBounds(632, 285, 88, 44);
		frmMazeGame.getContentPane().add(move_up);

		move_down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyAction("s");
			}});
		
		move_down.setBounds(632, 340, 88, 44);
		frmMazeGame.getContentPane().add(move_down);

		move_right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyAction("d");
			}});
		
		move_right.setBounds(730, 313, 88, 44);
		frmMazeGame.getContentPane().add(move_right);

		move_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyAction("a");
			}});
		
		move_left.setBounds(534, 313, 88, 44);
		frmMazeGame.getContentPane().add(move_left);

		JButton new_game = new JButton("Gerar novo labirinto");
		new_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Integer.parseInt(maze_size_input.getText());
					Integer.parseInt(number_dragons_input.getText());
					estado.setText("You can play!");
				} catch (Exception e) {
					estado.setText("Invalid argument!");
				}
		
				int size = Integer.parseInt(maze_size_input.getText());
				int dragons = Integer.parseInt(number_dragons_input.getText());
				boolean accepted = true;
				
				if (size * size < 50.0 && dragons == 1)
					estado.setText("You can play!");

				else if (dragons <= size * size / 50.0 && dragons > 0) {
					estado.setText("You can play!");
				} else {
					estado.setText("Invalid number of dragons!");
					maze_area.setText("");
					accepted = false;
				}

				if (size < 5 || size > 33) {
					estado.setText("Invalid size!");
					maze_area.setText("");
					accepted = false;
				}

				if (accepted) {
					
					if (size > 25) {
						frmMazeGame.setBounds(100, 10, 950, 750);
						maze_area.setBounds(50, 155, 525, 500);
						estado.setBounds(62, 660, 150, 14);
						move_left.setBounds(584, 313, 88, 44);
						move_right.setBounds(780, 313, 88, 44);
						move_down.setBounds(682, 340, 88, 44);
						move_up.setBounds(682, 285, 88, 44);

					}
					if (size < 25) {
						frmMazeGame.setBounds(100, 100, 900, 600);
						maze_area.setBounds(52, 155, 422, 379);
						estado.setBounds(62, 536, 150, 14);
						move_left.setBounds(534, 313, 88, 44);
						move_right.setBounds(730, 313, 88, 44);
						move_down.setBounds(632, 340, 88, 44);
						move_up.setBounds(632, 285, 88, 44);

					}
					move_up.setEnabled(true);
					move_down.setEnabled(true);
					move_left.setEnabled(true);
					move_right.setEnabled(true);

					game.setMaze(new Maze(size));
					game.setHero(new Hero(game.getMaze()));
					
					game.clearDragons();			

					for (int i = 0; i < dragons; i++) {
						game.addDragon(new Dragon(game.getMaze()));
					}

					maze_area.setText(game.return_board());
				}
			}
		});

		new_game.setBounds(584, 42, 178, 43);
		frmMazeGame.getContentPane().add(new_game);

		JButton exit = new JButton("Terminar programa");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exit.setBounds(584, 92, 178, 43);
		frmMazeGame.getContentPane().add(exit);

	}
	
	private void close() {
		maze_area.setText(game.return_board());
		move_up.setEnabled(false);
		move_down.setEnabled(false);
		move_left.setEnabled(false);
		move_right.setEnabled(false);
	}
	
	private void keyAction(String key) {
		String game_mode;
		if (dragon_type_input.getSelectedItem().equals(dragon_type_input.getItemAt(0))) {
			game_mode = "2";
		} else if (dragon_type_input.getSelectedItem().equals(dragon_type_input.getItemAt(1))) {
			game_mode = "0";
		} else {
			game_mode = "1";
		}

		switch (game.play(game_mode, key)) {
		case 1:
			maze_area.setText(game.return_board()); // WIN
			estado.setText("The hero has won!");
			close();
			break;
		case 2:
			maze_area.setText(game.return_board());
			estado.setText("The hero has died!"); // LOSE
			close();
			break;
		case 3:
			maze_area.setText(game.return_board());
			estado.setText("The dragon has died!"); // DRAGON DIES
			break;
		}

		maze_area.setText(game.return_board());
	}

}
