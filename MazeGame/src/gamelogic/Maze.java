package gamelogic;

public class Maze {
	private char grid[][];
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
	
	public Maze(char[][] grid) {
		this.grid = grid;
		
		for (int i = 0; i < grid.length; i++) {
			for (int a = 0; a < grid.length; a++) {
				if (grid[a][i] == 'S') {
					exit.x = i;
					exit.y = a;
				}
				else if (grid[a][i] == 'E') {
					sword.x = i;
					sword.y = a;
					sword_exists = true;
				}
			}
		}
	}
	
	public Maze(int maze_size) {
		MazeBuilder mb = new MazeBuilder();
		grid = mb.buildMaze(maze_size);
		
		exit.x = mb.getExitX();
		exit.y = mb.getExitY();
		
		sword.x = mb.getSwordX();
		sword.y = mb.getSwordY();
		
		sword_exists = true;
	}
	
	public Maze(int maze_size, String empty) {
		grid = new char[maze_size][maze_size];
		
		for (int i = 0; i < maze_size; i++) {
			for (int a = 0; a < maze_size; a++) {
				if (i == 0 || a == 0 || a == maze_size - 1 || i == maze_size - 1) {
					grid[a][i] = 'X';
				}
				else {
					grid[a][i] = ' ';
				}
			}
		}
		
	}
	
	public char[][] getGrid() {
		return grid;
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

	public int getGridLength() {
		return grid.length;
	}

	public void setSwordExists(boolean sword_exists) {
		this.sword_exists = sword_exists;
	}

	public void setGrid(char[][] grid) {
		this.grid = grid;
	}
	
	
	public String return_grid () {
		String grid_string = "";
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid_string += grid[i][j] + " ";
			}

			grid_string += System.lineSeparator();
		}
		
		return grid_string;
	}
}