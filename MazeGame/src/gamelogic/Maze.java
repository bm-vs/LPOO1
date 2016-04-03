package gamelogic;

public class Maze {
	private char board[][];
	private boolean sword_exists;

	public class Exit {
		private int x;
		private int y;
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public void setX(int x) {
			this.x = x;
		}
		
		public void setY(int y) {
			this.y = y;
		}
	}

	public class Sword {
		private int x;
		private int y;
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public void setX(int x) {
			this.x = x;
		}
		
		public void setY(int y) {
			this.y = y;
		}
	}

	private Exit exit = new Exit();
	private Sword sword = new Sword();

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
		
		exit.x = mb.getExitX();
		exit.y = mb.getExitY();
		
		sword.x = mb.getSwordX();
		sword.y = mb.getSwordY();
		
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
	
	public char[][] getBoard() {
		return board;
	}
		
	public Sword getSword() {
		return sword;
	}
	
	public Exit getExit() {
		return exit;
	}
	
	public boolean swordExists() {
		return sword_exists;
	}

	public int getBoardLength() {
		return board.length;
	}

	public void setSwordExists(boolean sword_exists) {
		this.sword_exists = sword_exists;
	}

	public void setBoard(char[][] board) {
		this.board = board;
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