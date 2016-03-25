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

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Game game = new Game();
		
		frmMazeGame = new JFrame();
		frmMazeGame.getContentPane().setForeground(Color.BLACK);
		frmMazeGame.setTitle("Maze Game");
		frmMazeGame.setBounds(100, 100, 900, 600);
		frmMazeGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMazeGame.getContentPane().setLayout(null);
		
		JLabel maze_size = new JLabel("Dimens\u00E3o do labirinto");
		maze_size.setBounds(52, 42, 162, 14);
		frmMazeGame.getContentPane().add(maze_size);
		
		maze_size_input = new JTextField();
		maze_size_input.setText("11");
		maze_size_input.setBounds(224, 39, 65, 20);
		frmMazeGame.getContentPane().add(maze_size_input);
		maze_size_input.setColumns(10);
		
		JLabel number_dragons = new JLabel("N\u00FAmero de drag\u00F5es");
		number_dragons.setBounds(52, 78, 162, 14);
		frmMazeGame.getContentPane().add(number_dragons);
		
		number_dragons_input = new JTextField();
		number_dragons_input.setText("1");
		number_dragons_input.setColumns(10);
		number_dragons_input.setBounds(224, 75, 65, 20);
		frmMazeGame.getContentPane().add(number_dragons_input);
		
		JLabel dragon_type = new JLabel("Tipo de drag\u00F5es");
		dragon_type.setBounds(51, 115, 162, 14);
		frmMazeGame.getContentPane().add(dragon_type);
		
		JComboBox dragon_type_input = new JComboBox();
		dragon_type_input.setBounds(223, 112, 138, 20);
		frmMazeGame.getContentPane().add(dragon_type_input);
		dragon_type_input.addItem("Estáticos");
		dragon_type_input.addItem("Mover");
		dragon_type_input.addItem("Mover e adormecer");
		
		JTextArea maze_area = new JTextArea();
		maze_area.setFont(new Font("Courier New", Font.PLAIN, 13));
		maze_area.setBounds(52, 164, 422, 370);
		frmMazeGame.getContentPane().add(maze_area);
		
		JButton move_up = new JButton("Cima");
		move_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String game_mode;
				if (dragon_type_input.getSelectedItem().equals("Estáticos")) {
					game_mode = "2";
				}
				else if (dragon_type_input.getSelectedItem().equals("Mover")) {
					game_mode = "0";
				}
				else {
					game_mode = "1";
				}
				
				switch(game.play(game_mode, "w")) {
				case 1: maze_area.setText(game.return_board() + "\n\n The hero has won!");	// WIN
						break;
				case 2: maze_area.setText(game.return_board() + "\n\n The hero has died!");	 // LOSE
						break;
				case 3: maze_area.setText(game.return_board() + "\n\n The dragon has died!"); // DRAGON DIES
						break;
				}
				
				maze_area.setText(game.return_board());
			}
		});
		move_up.setBounds(632, 285, 88, 44);
		frmMazeGame.getContentPane().add(move_up);
		
		JButton move_down = new JButton("Baixo");
		move_down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String game_mode;
				if (dragon_type_input.getSelectedItem().equals("Estáticos")) {
					game_mode = "2";
				}
				else if (dragon_type_input.getSelectedItem().equals("Mover")) {
					game_mode = "0";
				}
				else {
					game_mode = "1";
				}
				
				switch(game.play(game_mode, "s")) {
				case 1: maze_area.setText(game.return_board() + "\n\n The hero has won!");	// WIN
						break;
				case 2: maze_area.setText(game.return_board() + "\n\n The hero has died!");	 // LOSE
						break;
				case 3: maze_area.setText(game.return_board() + "\n\n The dragon has died!"); // DRAGON DIES
						break;
				}
				
				maze_area.setText(game.return_board());
			}
		});
		move_down.setBounds(632, 340, 88, 44);
		frmMazeGame.getContentPane().add(move_down);
		
		JButton move_right = new JButton("Direita");
		move_right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String game_mode;
				if (dragon_type_input.getSelectedItem().equals("Estáticos")) {
					game_mode = "2";
				}
				else if (dragon_type_input.getSelectedItem().equals("Mover")) {
					game_mode = "0";
				}
				else {
					game_mode = "1";
				}
				
				switch(game.play(game_mode, "d")) {
				case 1: maze_area.setText(game.return_board() + "\n\n The hero has won!");	// WIN
						break;
				case 2: maze_area.setText(game.return_board() + "\n\n The hero has died!");	 // LOSE
						break;
				case 3: maze_area.setText(game.return_board() + "\n\n The dragon has died!"); // DRAGON DIES
						break;
				}
				
				maze_area.setText(game.return_board());
			}
		});
		move_right.setBounds(730, 313, 88, 44);
		frmMazeGame.getContentPane().add(move_right);
		
		JButton move_left = new JButton("Esquerda");
		move_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String game_mode;
				if (dragon_type_input.getSelectedItem().equals("Estáticos")) {
					game_mode = "2";
				}
				else if (dragon_type_input.getSelectedItem().equals("Mover")) {
					game_mode = "0";
				}
				else {
					game_mode = "1";
				}
				
				switch(game.play(game_mode, "a")) {
				case 1: maze_area.setText(game.return_board() + "\n\n The hero has won!");	// WIN
						break;
				case 2: maze_area.setText(game.return_board() + "\n\n The hero has died!");	 // LOSE
						break;
				case 3: maze_area.setText(game.return_board() + "\n\n The dragon has died!"); // DRAGON DIES
						break;
				}
				
				maze_area.setText(game.return_board());
			}
		});
		move_left.setBounds(534, 313, 88, 44);
		frmMazeGame.getContentPane().add(move_left);
		
		
		JButton new_game = new JButton("Gerar novo labirinto");
		new_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move_up.setEnabled(true);
				move_down.setEnabled(true);
				move_left.setEnabled(true);
				move_right.setEnabled(true);
			
				game.maze = new Maze(Integer.parseInt(maze_size_input.getText()));
				game.hero = new Hero(game.maze);

				for (int i = 0; i < Integer.parseInt(number_dragons_input.getText()); i++) {
					game.dragons.add(new Dragon(game.maze));
				}
				
				maze_area.setText(game.return_board());
			}
		});
		new_game.setBounds(584, 42, 178, 43);
		frmMazeGame.getContentPane().add(new_game);
		
		JButton exit = new JButton("Terminar programa");
		exit.setBounds(584, 92, 178, 43);
		frmMazeGame.getContentPane().add(exit);
	}
}
