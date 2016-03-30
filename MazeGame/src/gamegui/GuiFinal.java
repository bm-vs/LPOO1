package gamegui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiFinal {

	private JFrame menu_frame;
	private JFrame configurar_frame;
	private JButton jogar_button;
	private JButton construir_button;
	private JButton configurar_button;
	private JButton terminar_button;
	private JTextField maze_size_input;
	private JTextField number_dragons_input;
	private JComboBox<String> dragon_type_input;
	private JLabel maze_size;
	private JLabel dragon_type;
	private JLabel estado;
	private JLabel number_dragons;
	private JButton guardar_button;
	
	int size;
	int dragons;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiFinal window = new GuiFinal();
					window.menu_frame.setVisible(true);
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
		menu_frame = new JFrame();
		menu_frame.setTitle("Maze game");
		menu_frame.setAutoRequestFocus(false);
		menu_frame.setBounds(100, 100, 900, 600);
		menu_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu_frame.getContentPane().setLayout(null);
	
		//=================================================================================================
		// CONFIGURAR	
		
		configurar_frame = new JFrame();
		configurar_frame.setTitle("Configurar");
		configurar_frame.setAutoRequestFocus(false);
		configurar_frame.setBounds(100, 100, 450, 300);
		configurar_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		configurar_frame.getContentPane().setLayout(null);
		
		
		maze_size = new JLabel("Dimens\u00E3o do labirinto");
		maze_size.setBounds(52, 42, 162, 14);
		configurar_frame.getContentPane().add(maze_size);

		maze_size_input = new JTextField();
		maze_size_input.setText("11");
		maze_size_input.setBounds(242, 36, 65, 20);
		configurar_frame.getContentPane().add(maze_size_input);
		maze_size_input.setColumns(10);

		number_dragons = new JLabel("N\u00FAmero de drag\u00F5es");
		number_dragons.setBounds(52, 78, 162, 14);
		configurar_frame.getContentPane().add(number_dragons);

		number_dragons_input = new JTextField();
		number_dragons_input.setText("1");
		number_dragons_input.setColumns(10);
		number_dragons_input.setBounds(242, 72, 65, 20);
		configurar_frame.getContentPane().add(number_dragons_input);

		estado = new JLabel("You can play!");
		estado.setBounds(52, 214, 191, 20);
		configurar_frame.getContentPane().add(estado);

		dragon_type = new JLabel("Tipo de drag\u00F5es");
		dragon_type.setBounds(51, 115, 162, 14);
		configurar_frame.getContentPane().add(dragon_type);

		dragon_type_input = new JComboBox<String>();
		dragon_type_input.setBounds(241, 109, 138, 20);
		configurar_frame.getContentPane().add(dragon_type_input);
		
		dragon_type_input.addItem("Estáticos");
		dragon_type_input.addItem("Mover");
		dragon_type_input.addItem("Mover e adormecer");
		
		guardar_button = new JButton("Guardar");
		guardar_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer.parseInt(maze_size_input.getText());
					Integer.parseInt(number_dragons_input.getText());
					estado.setText("You can play!");
				} catch (Exception exc) {
					estado.setText("Invalid argument!");
					return;
				}
				
				size = Integer.parseInt(maze_size_input.getText());
				dragons = Integer.parseInt(number_dragons_input.getText());
				
				if (size * size < 50.0 && dragons == 1)
					estado.setText("You can play!");

				else if (dragons <= size * size / 50.0 && dragons > 0) {
					estado.setText("You can play!");
				} else {
					estado.setText("Invalid number of dragons!");
					return;
				}

				if (size < 5 || size > 33) {
					estado.setText("Invalid size!");
					return;
				}
				
				
				configurar_frame.setVisible(false);
				menu_frame.setVisible(true);
			}
		});
		
		guardar_button.setBounds(261, 209, 118, 30);
		configurar_frame.getContentPane().add(guardar_button);
		
		
		//===================================================================================================
		
		
		jogar_button = new JButton("Jogar");
		jogar_button.setBounds(350, 125, 200, 60);
		menu_frame.getContentPane().add(jogar_button);
		
		construir_button = new JButton("Construir");
		construir_button.setBounds(350, 225, 200, 60);
		menu_frame.getContentPane().add(construir_button);
		
		configurar_button = new JButton("Configurar");
		configurar_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_frame.setVisible(false);
				configurar_frame.setVisible(true);
			}
		});
		configurar_button.setBounds(350, 325, 200, 60);
		menu_frame.getContentPane().add(configurar_button);
		
		terminar_button = new JButton("Terminar jogo");
		terminar_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		terminar_button.setBounds(350, 425, 200, 60);
		menu_frame.getContentPane().add(terminar_button);		
	}
}
