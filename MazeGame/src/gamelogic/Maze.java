package gamelogic;

/**
 * Class to represent the maze of the game.
 * */
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

	/**
	 * Creates an empty maze.
	 */
	public Maze() {
	}
	
	/**
	 * Creates a maze from the given grid.
	 * <p>
	 * Checks the grid for the position of the sword and exit and creates the maze accordingly.
	 * </p>
	 * @param grid grid to build the maze from
	 */
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
	
	/**
	 * Creates a randomly generated maze with the given size.
	 * @param maze_size size of the maze
	 */
	public Maze(int maze_size) {
		MazeBuilder mb = new MazeBuilder();
		grid = mb.buildMaze(maze_size);
		
		exit.x = mb.getExitX();
		exit.y = mb.getExitY();
		
		sword.x = mb.getSwordX();
		sword.y = mb.getSwordY();
		
		sword_exists = true;
	}
	
	/**
	 * Creates a maze with only the border filled.
	 * @param maze_size size of the maze
	 * @param empty string used as placeholder
	 */
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
	
	/**
	 * Returns grid.
	 * @return grid
	 */
	public char[][] getGrid() {
		return grid;
	}
	
	/**
	 * Returns sword.
	 * @return sword
	 */
	public Sword getSword() {
		return sword;
	}
	
	/**
	 * Returns exit.
	 * @return exit
	 */
	public Exit getExit() {
		return exit;
	}
	
	/**
	 * Returns sword_exists.
	 * @return sword_exists
	 */
	public boolean swordExists() {
		return sword_exists;
	}

	/**
	 * Returns the size of the maze
	 * @return the size of the maze
	 */
	public int getGridLength() {
		return grid.length;
	}

	/**
	 * Sets the sword_exits.
	 * @param sword_exists sword exists
	 */
	public void setSwordExists(boolean sword_exists) {
		this.sword_exists = sword_exists;
	}

	/**
	 * Sets maze's grid.
	 * @param grid maze's grid.
	 */
	public void setGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns a formatted string of the grid.
	 * @return formated string of the grid.
	 */
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