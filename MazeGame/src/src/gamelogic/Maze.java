package gamelogic;

public class Maze {
	public char board[][];
	public boolean sword_exists;

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

	public Maze(int maze_size) {
		MazeBuilder mb = new MazeBuilder();
		board = mb.buildMaze(maze_size);
		
		exit.x = mb.exit_x;
		exit.y = mb.exit_y;
		
		sword.x = mb.sword_x;
		sword.y = mb.sword_y;
		
		sword_exists = true;
	}
	
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