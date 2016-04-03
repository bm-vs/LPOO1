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

	public Maze() {
	}
	
	public Maze(char[][] board) {
		this.board = board;
		
		for (int i = 0; i < board.length; i++) {
			for (int a = 0; a < board.length; a++) {
				if (board[a][i] == 'S') {
					exit.x = i;
					exit.y = a;
				}
				else if (board[a][i] == 'E') {
					sword.x = i;
					sword.y = a;
					sword_exists = true;
				}
			}
		}
	}
	
	
	
	public Maze(int maze_size) {
		MazeBuilder mb = new MazeBuilder();
		board = mb.buildMaze(maze_size);
		
		exit.x = mb.exit_x;
		exit.y = mb.exit_y;
		
		sword.x = mb.sword_x;
		sword.y = mb.sword_y;
		
		sword_exists = true;
	}
	
	public Maze(int maze_size, String empty) {
		board = new char[maze_size][maze_size];
		
		for (int i = 0; i < maze_size; i++) {
			for (int a = 0; a < maze_size; i++) {
				if (i == 0 || a == 0 || a == maze_size - 1 || i == maze_size - 1) {
					board[a][i] = 'X';
				}
			}
		}
		
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