package gamelogic;

import java.util.Random;

import gamelogic.Maze;

public class Dragon {
	private int x;
	private int y;
	private char symbol;

	public Dragon(int x, int y) {
		this.x = x;
		this.y = y;

		this.symbol = 'D';
	}

	public Dragon(Maze maze) {
		Random rand = new Random();

		// Get random starting position for dragon
		while (true) {
			x = rand.nextInt(maze.getBoard().length - 2);
			y = rand.nextInt(maze.getBoard().length - 2);

			x++;// the zero position is wall
			y++;//

			if (maze.getBoard()[y][x] == ' '
					&& maze.getBoard()[y - 1][x] != 'H'
					&& maze.getBoard()[y + 1][x] != 'H'
					&& maze.getBoard()[y][x - 1] != 'H'
					&& maze.getBoard()[y][x + 1] != 'H') {
				break;
			}
		}

		this.symbol = 'D';

		// Place dragon on maze
		maze.getBoard()[y][x] = symbol;
	}

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

	public char getSymbol() {
		return symbol;
	}

	public void set_Position(int x, int y, Maze maze) {
		maze.getBoard()[y][x] = ' ';
		this.x = x;
		this.y = y;

		// Place dragon on maze
		maze.getBoard()[y][x] = symbol;

	}

	public int mode(Maze maze) {
		Random rand = new Random();
		int mode = rand.nextInt() % 2; // 0 - move, 1 - sleep

		return mode;

	}

	public void wakeUp(Maze maze) {
		symbol = 'D';
		maze.getBoard()[y][x] = symbol;

		if (maze.getSword().getX() == x && maze.getSword().getY() == y) {
			maze.getBoard()[y][x] = 'F';
		}

	}

	public void fallAsleep(Maze maze) {
		symbol = 'd';
		maze.getBoard()[y][x] = symbol;
	}

	public void dies(Maze maze) {
		maze.getBoard()[y][x] = ' ';
	}

	public void move(Maze maze) {
		Random rand = new Random();

		while (true) {
			int i = rand.nextInt() % 5;

			if (i == 0) {
				if (maze.getBoard()[y - 1][x] == ' ') {
					maze.getBoard()[y][x] = ' ';
					y--;
					maze.getBoard()[y][x] = symbol;
					break;
				} else if (maze.getBoard()[y - 1][x] == 'E') {
					maze.getBoard()[y][x] = ' ';
					y--;
					maze.getBoard()[y][x] = 'F';
					break;
				}
			}

			if (i == 1) {
				if (maze.getBoard()[y][x + 1] == ' ') {
					maze.getBoard()[y][x] = ' ';
					x++;
					maze.getBoard()[y][x] = symbol;
					break;
				} else if (maze.getBoard()[y][x + 1] == 'E') {
					maze.getBoard()[y][x] = ' ';
					x++;
					maze.getBoard()[y][x] = 'F';
					break;
				}
			}

			if (i == 2) {
				if (maze.getBoard()[y + 1][x] == ' ') {
					maze.getBoard()[y][x] = ' ';
					y++;
					maze.getBoard()[y][x] = symbol;
					break;
				} else if (maze.getBoard()[y + 1][x] == 'E') {
					maze.getBoard()[y][x] = ' ';
					y++;
					maze.getBoard()[y][x] = 'F';
					break;
				}
			}

			if (i == 3) {
				if (maze.getBoard()[y][x - 1] == ' ') {
					maze.getBoard()[y][x] = ' ';
					x--;
					maze.getBoard()[y][x] = symbol;
					break;
				} else if (maze.getBoard()[y][x - 1] == 'E') {
					maze.getBoard()[y][x] = ' ';
					x--;
					maze.getBoard()[y][x] = 'F';
					break;
				}
			}
			if (i == 4) {
				break;
			}
		}

		if ((maze.getSword().getY() != y || maze.getSword().getX() != x)
				&& maze.swordExists()) {
			maze.getBoard()[maze.getSword().getY()][maze.getSword().getX()] = 'E';
		}
	}

}