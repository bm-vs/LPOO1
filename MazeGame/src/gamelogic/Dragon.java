package gamelogic;

import java.util.Random;

import gamelogic.Maze;

/**
 * Class to represent the dragons present in the game.
 * */
public class Dragon {
	private int x;
	private int y;
	private char symbol;

	/**
	 * Creates a dragon with the given coordinates.
	 * <p>
	 * Initializes the dragon to the default symbol 'D'.
	 * </p>
	 * @param x the x-coordinate of the dragon on the maze grid
	 * @param y the y-coordinate of the dragon on the maze grid
	 */
	public Dragon(int x, int y) {
		this.x = x;
		this.y = y;

		this.symbol = 'D';
	}

	
	/**
	 * Creates a dragon with random coordinates and places it on the given maze.
	 * <p>
	 * The allowed coordinates are limited by the maze given.
	 * </p>
	 * @param maze the maze it which to insert the dragon
	 */
	public Dragon(Maze maze) {
		Random rand = new Random();

		// Get random starting position for dragon
		while (true) {
			x = rand.nextInt(maze.getGrid().length - 2);
			y = rand.nextInt(maze.getGrid().length - 2);

			x++;// the zero position is wall
			y++;//

			if (maze.getGrid()[y][x] == ' '
					&& maze.getGrid()[y - 1][x] != 'H'
					&& maze.getGrid()[y + 1][x] != 'H'
					&& maze.getGrid()[y][x - 1] != 'H'
					&& maze.getGrid()[y][x + 1] != 'H') {
				break;
			}
		}

		this.symbol = 'D';

		// Place dragon on maze
		maze.getGrid()[y][x] = symbol;
	}

	/**
	 * Returns the x-coordinate of the dragon on the maze grid.
	 * @return x-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y-coordinate of the dragon on the maze grid.
	 * @return y-coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns the symbol of dragon.
	 * @return symbol
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * Sets the x-coordinate of the dragon on the maze grid.
	 * @param x x-coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the y-coordinate of the dragon on the maze grid.
	 * @param y y-coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets the symbol.
	 * @param symbol symbol to set
	 */
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	/**
	 * Sets the position of the dragon on the maze grid and places it on the maze.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param maze maze
	 */
	public void setPosition(int x, int y, Maze maze) {
		maze.getGrid()[y][x] = ' ';
		this.x = x;
		this.y = y;

		// Place dragon on maze
		maze.getGrid()[y][x] = symbol;

	}

	/**
	 * Returns 0 or 1 randomly.
	 * <p>
	 * 0 signals the dragon to move.
	 * </p>
	 * <p>
	 * 1 signals the dragon to go to sleep.
	 * </p>
	 * @param maze maze the dragon is in
	 * @return 0 or 1 randomly
	 */
	public int mode(Maze maze) {
		Random rand = new Random();
		int mode = rand.nextInt() % 2; // 0 - move, 1 - sleep

		return mode;

	}

	/**
	 * Signals the dragon to wake up and changes its symbol accordingly.
	 * @param maze maze the dragon is in
	 */
	public void wakeUp(Maze maze) {
		symbol = 'D';
		maze.getGrid()[y][x] = symbol;

		if (maze.getSword().getX() == x && maze.getSword().getY() == y) {
			maze.getGrid()[y][x] = 'F';
		}
	}

	/**
	 * Signals the dragon to fall asleep and changes its symbol accordingly.
	 * @param maze maze the dragon is in
	 */
	public void fallAsleep(Maze maze) {
		symbol = 'd';
		maze.getGrid()[y][x] = symbol;
	}

	/**
	 * Signals the dragon has died and removes it from the maze.
	 * @param maze maze the dragon is in
	 */
	public void dies(Maze maze) {
		maze.getGrid()[y][x] = ' ';
	}

	/**
	 * Moves the dragon within the maze.
	 * @param maze maze the dragon is in
	 * @throws FightHero if the dragon moves to a cell adjacent do the hero
	 */	
	public void move(Maze maze) throws FightHero{
		Random rand = new Random();

		while (true) {
			int i = rand.nextInt() % 5;

			if (i == 0) {
				if (maze.getGrid()[y - 1][x] == ' ') {
					maze.getGrid()[y][x] = ' ';
					y--;
					maze.getGrid()[y][x] = symbol;
					break;
				} else if (maze.getGrid()[y - 1][x] == 'E') {
					maze.getGrid()[y][x] = ' ';
					y--;
					maze.getGrid()[y][x] = 'F';
					break;
				}
			}

			if (i == 1) {
				if (maze.getGrid()[y][x + 1] == ' ') {
					maze.getGrid()[y][x] = ' ';
					x++;
					maze.getGrid()[y][x] = symbol;
					break;
				} else if (maze.getGrid()[y][x + 1] == 'E') {
					maze.getGrid()[y][x] = ' ';
					x++;
					maze.getGrid()[y][x] = 'F';
					break;
				}
			}

			if (i == 2) {
				if (maze.getGrid()[y + 1][x] == ' ') {
					maze.getGrid()[y][x] = ' ';
					y++;
					maze.getGrid()[y][x] = symbol;
					break;
				} else if (maze.getGrid()[y + 1][x] == 'E') {
					maze.getGrid()[y][x] = ' ';
					y++;
					maze.getGrid()[y][x] = 'F';
					break;
				}
			}

			if (i == 3) {
				if (maze.getGrid()[y][x - 1] == ' ') {
					maze.getGrid()[y][x] = ' ';
					x--;
					maze.getGrid()[y][x] = symbol;
					break;
				} else if (maze.getGrid()[y][x - 1] == 'E') {
					maze.getGrid()[y][x] = ' ';
					x--;
					maze.getGrid()[y][x] = 'F';
					break;
				}
			}
			if (i == 4) {
				break;
			}
		}
		
		if (maze.getGrid()[y][x+1] == 'H' || maze.getGrid()[y][x-1] == 'H' || maze.getGrid()[y+1][x] == 'H' || maze.getGrid()[y-1][x] == 'H') {
			throw new FightHero(true);
		}
		if (maze.getGrid()[y][x+1] == 'A' || maze.getGrid()[y][x-1] == 'A' || maze.getGrid()[y+1][x] == 'A' || maze.getGrid()[y-1][x] == 'A') {
			throw new FightHero(false);
		}
		
		

		if ((maze.getSword().getY() != y || maze.getSword().getX() != x)
				&& maze.swordExists()) {
			maze.getGrid()[maze.getSword().getY()][maze.getSword().getX()] = 'E';
		}
	}

}