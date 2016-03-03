package game.logic;

import java.util.Random;
import java.lang.Object;

public class Maze {

	public Random rand = new Random(System.currentTimeMillis());
	public char board[][] = {
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', 'D', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', 'E', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

	public class Exit {
		public int x;
		public int y;
	}

	public class Sword {
		public int x;
		public int y;
	}

	public Exit exit = new Exit();
	public Sword sword = new Sword();

	public Maze() {
		exit.x = 9;
		exit.y = 5;

		sword.x = 1;
		sword.y = 5;
	}

	public boolean sword_exists = true;
	
	public String return_board () {
		String board_string = "";
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board_string += board[i][j] + " ";
			}

			board_string += System.lineSeparator();
		}
		
		return board_string;
	}
}