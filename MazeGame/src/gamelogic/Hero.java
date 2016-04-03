package gamelogic;

import java.util.Random;

import gamelogic.Dragon;
import gamelogic.Maze;

/**
 * Class to represent the hero.
 **/
public class Hero {
	private int x;
	private int y;
	private char symbol;
	
	/**
	 * Creates an empty hero.
	 */
	public Hero() {
	}
	
	/**
	 * Creates a hero from the given grid.
	 * <p>
	 * Checks the grid for the position of the hero creates the hero accordingly.
	 * </p>
	 * @param grid grid to create the hero from
	 */
	public Hero(char[][] grid) {		
		for (int i = 0; i < grid.length; i++) {
			for (int a = 0; a < grid.length; a++) {
				if (grid[a][i] == 'H') {
					x = i;
					y = a;
				}
			}
		}
		
		this.symbol = 'H';
	}
	
	/**
	 * Creates a hero with random coordinates and places it on the given maze.
	 * <p>
	 * The allowed coordinates are limited by the maze given.
	 * </p>
	 * @param maze the maze it which to insert the hero
	 */
	public Hero(Maze maze) {
		Random rand = new Random(System.currentTimeMillis());
		
		// Get random starting position for hero
		while (true) {
			x = rand.nextInt(maze.getGridLength() - 2);
			y = rand.nextInt(maze.getGridLength() - 2);
			x++;
			y++;
			
			if (maze.getGrid()[y][x] == ' ') {
				break;
			}
		}
		
		// Place hero on grid
		this.symbol = 'H';
		maze.getGrid()[y][x] = symbol;
	}
	
	/**
	 * Return the hero's position on the maze.
	 * @return Point with the coordinates of the hero
	 */
	public Point getHeroPosition() {
		return  (new Point(y, x));
	}
	
	/**
	 * Returns the x-coordinate of the hero on the maze grid.
	 * @return x-coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the y-coordinate of the hero on the maze grid.
	 * @return y-coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns the symbol of hero.
	 * @return symbol
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * Sets the x-coordinate of the hero on the maze grid.
	 * @param x x-coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets the y-coordinate of the hero on the maze grid.
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
	 * Moves the hero within the maze.
	 * @param maze maze the hero is in
	 */	
	public boolean move(String key, Maze maze) {		
		if (key.equals("w")) {
			if (maze.getGrid()[y - 1][x] == ' ' || maze.getGrid()[y - 1][x] == 'E') {
				maze.getGrid()[y][x] = ' ';
				y --;
				maze.getGrid()[y][x] = symbol;
			
				return true;
			}
		}
		else if (key.equals("a")) {
			if (maze.getGrid()[y][x - 1] == ' ' || maze.getGrid()[y][x - 1] == 'E') {
				maze.getGrid()[y][x] = ' ';
				x --;
				maze.getGrid()[y][x] = symbol;
				
				return true;
			}
		}
		else if (key.equals("d")) {
			if (maze.getGrid()[y][x + 1] == ' ' || maze.getGrid()[y][x + 1] == 'E') {
				maze.getGrid()[y][x] = ' ';
				x ++;
				maze.getGrid()[y][x] = symbol;
				
				return true;
			}
		}
		else if (key.equals("s")) {
			if (maze.getGrid()[y + 1][x] == ' ' || maze.getGrid()[y + 1][x] == 'E') {
				maze.getGrid()[y][x] = ' ';
				y ++;
				maze.getGrid()[y][x] = symbol;
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Makes the hero fight the dragon.
	 * @param dragon dragon to fight
	 * @return 0 if hero and dragon aren't adjacent or if the dragon is asleep. 1 if the hero is unarmed. 2 if the hero is armed. 
	 */
	public int fightDragon(Dragon dragon) {
		if (dragon.getX() == x) {
			if (dragon.getY() == y - 1 || dragon.getY() == y + 1) {
				if (symbol == 'H') {
					if (dragon.getSymbol() == 'd')
						return 0; // works as wall

					return 1;
				} else if (symbol == 'A') {
					return 2;
				}
			}
		}
		if (dragon.getY() == y) {
			if (dragon.getX() == x - 1 || dragon.getX() == x + 1) {
				if (symbol == 'H') {
					if (dragon.getSymbol() == 'd')
						return 0; // works as wall
					return 1;
				} else if (symbol == 'A') {
					return 2;
				}
			}
		}

		return 0;
	}
	
	/**
	 * Makes the hero pick up the sword
	 * @param sword the sword on the maze
	 * @param maze maze
	 * @return true if the hero is on the sword. false if otherwise.
	 */
	public boolean pickUpSword(Maze.Sword sword, Maze maze) {
		if (sword.getX() == x && sword.getY() == y) {
			symbol = 'A';
			maze.getGrid()[y][x] = symbol;
			maze.setSwordExists(false);
			
			return true;
		}
		
		return false;
	}
}
