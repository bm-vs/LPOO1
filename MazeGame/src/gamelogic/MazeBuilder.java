package gamelogic;

import java.util.*;

/**
 * Class used to create a random maze.
 */

public class MazeBuilder implements IMazeBuilder{
	private char[][] maze;
	private int exit_x;
	private int exit_y;
	private int sword_x;
	private int sword_y;
	
	/**
	 * Creates a random maze of the given size.
	 * <p>
	 * Starting from the cell in front of the exit go to a random adjacent cell. Save the cells on a stack.
	 * </p>
	 * <p>
	 * When there are no available adjacent cells pop from the stack until there are.
	 * </p>
	 * @param size size of the maze to create
	 * @return maze
	 */
	public char[][] buildMaze(int size) throws IllegalArgumentException{
		Random rnd = new Random(System.currentTimeMillis());
		maze = new char[size][size];
		
		do {
		// fill maze
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				maze[i][j] = 'X';
			}
		}
		
		// choose exit		
		do {
			int exit_border = rnd.nextInt(4);
			switch (exit_border) {
			case 0: // left
				exit_x = 0;
				exit_y = rnd.nextInt(size);
				break;
			case 1: // right
				exit_x = size - 1;
				exit_y = rnd.nextInt(size);
				break;
			case 2: // top
				exit_y = 0;
				exit_x = rnd.nextInt(size);
				break;
			case 3: // bottom
				exit_y = size - 1;
				exit_x = rnd.nextInt(size);
				break;
			}
		} while ((exit_x == 0 || exit_x == size - 1) && (exit_y == 0 || exit_y == size - 1));
		
		maze[exit_y][exit_x] = 'S';
		
		
		
		// Create path
		Stack<Point> path = new Stack<Point>();
		
		// Starting point
		Point start = new Point();
		
		if (exit_x == 0) {
			start.setX(1);
			start.setY(exit_y);
		}
		else if (exit_x == size - 1) {
			start.setX(size - 2);
			start.setY(exit_y);
		}
		else if (exit_y == 0) {
			start.setX(exit_x);
			start.setY(1);
		}
		else if (exit_y == size - 1) {
			start.setX(exit_x);
			start.setY(size - 2);
		}
		
		maze[start.getY()][start.getX()] = ' ';
		path.push(start);
	
		
		// Next point
		while (!path.empty()) {
			Point next = chooseNextPos(maze, path.peek());
			
			if (next.getX() == -1 && next.getY() == -1) {
				path.pop();
			}
			else {
				
				maze[next.getY()][next.getX()] = ' ';
				path.push(next);
			}
		}
		} while (checkWalls(maze));
		
		
		// Get random position for sword
		while (true) {
			sword_x = rnd.nextInt(maze.length - 2);
			sword_y = rnd.nextInt(maze.length - 2);
			sword_x++;
			sword_y++;
			
			
			if (maze[sword_y][sword_x] == ' ') {
				break;
			}
		}
		
		maze[sword_y][sword_x] = 'E';
		
		return maze;
	}
	
	/**
	 * Return the next point randomly.
	 * @param maze maze to get next position to
	 * @param pos starting point, from which the next one will be calculated
	 * @return next poin
	 */
	static public Point chooseNextPos(char[][] maze, Point pos) {
		Random rnd = new Random();
		Vector<Point> options = new Vector<Point>();
		options.addElement(new Point(pos.getX()+1,pos.getY()));
		options.addElement(new Point(pos.getX()-1,pos.getY()));
		options.addElement(new Point(pos.getX(),pos.getY()+1));
		options.addElement(new Point(pos.getX(),pos.getY()-1));
				
		for (int i = 4; i >= 1; i--) {
			int n = rnd.nextInt(i);
			int x = options.elementAt(n).getX();
			int y = options.elementAt(n).getY();
			
			// Ignore walls and empty spaces
			if ((maze[y][x] == 'S') || (maze[y][x] == ' ') ||
					(x == 0) || (x == maze[1].length - 1) || 
					(y == 0) || (y == maze.length - 1)) {
				
				options.removeElementAt(n);	
				continue;
			}
			
			// Impose restrictions (diagonals)
			Point diag1 = new Point();
			Point diag2 = new Point();
			
			// New point on the same vertical
			if (x == pos.getX()) {
				diag1.setX(x+1);
				diag1.setY(y+(y-pos.getY()));
				diag2.setX(x-1);
				diag2.setY(y+(y-pos.getY()));
			}
			// New point on the same horizontal
			else if (y == pos.getY()) {
				diag1.setX(x+(x-pos.getX()));
				diag1.setY(y+1);
				diag2.setX(x+(x-pos.getX()));
				diag2.setY(y-1);
			}
			
			if (maze[diag1.getY()][diag1.getX()] == ' ' || maze[diag2.getY()][diag2.getX()] == ' ') { // diagonal is ' ' and adjacent != ' '
				options.removeElementAt(n);;	
				continue;
			}
			
			// Impose restrictions (white 2x2)
			if ((maze[y][x+1] == ' ' && maze[y+1][x+1] == ' ' && maze[y+1][x] == ' ') ||
					(maze[y][x-1] == ' ' && maze[y+1][x-1] == ' ' && maze[y+1][x] == ' ') ||
					(maze[y][x+1] == ' ' && maze[y-1][x+1] == ' ' && maze[y-1][x] == ' ') ||
					(maze[y][x-1] == ' ' && maze[y-1][x-1] == ' ' && maze[y-1][x] == ' ')) {
				
				options.removeElementAt(n);;	
				continue;
			}
			
			return options.elementAt(n);
		}
		
		return new Point(-1,-1);
	}
	
	/**
	 * Check if there are 3x3 walls on the maze.
	 * @param maze maze to check
	 * @return true if yes. false if not.
	 */
	public boolean checkWalls(char[][] maze) {
		char[][] badWalls = {
				{'X', 'X', 'X'},
				{'X', 'X', 'X'},
				{'X', 'X', 'X'}};
		
		
		for (int i = 0; i < maze.length - badWalls.length; i++)
			for (int j = 0; j < maze.length - badWalls.length; j++) {
				boolean match = true;
				for (int y = 0; y < badWalls.length; y++)
					for (int x = 0; x < badWalls.length; x++) {
						if (maze[i+y][j+x] != badWalls[y][x])
							match = false;
					}
				if (match)
					return true;
			}		
		return false; 
	}
	
	/**
	 * Returns the x-coordinate of the sword on the maze grid.
	 * @return x-coordinate
	 */
	public int getSwordX() {
		return sword_x;
	}
	
	/**
	 * Returns the y-coordinate of the sword on the maze grid.
	 * @return y-coordinate
	 */
	public int getSwordY() {
		return sword_y;
	}
	
	/**
	 * Returns the x-coordinate of the exit on the maze grid.
	 * @return x-coordinate
	 */
	public int getExitX() {
		return exit_x;
	}
	
	/**
	 * Returns the y-coordinate of the exit on the maze grid.
	 * @return y-coordinate
	 */
	public int getExitY() {
		return exit_y;
	}
	
}
