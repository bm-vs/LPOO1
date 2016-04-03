package gamelogic;

import java.util.Random;

import gamelogic.Dragon;
import gamelogic.Maze;

public class Hero {
	private int x;
	private int y;
	private char symbol;
	
	public Hero() {
	}
	
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
	
	public Point getHeroPosition() {
		return  (new Point(y, x));
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
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
