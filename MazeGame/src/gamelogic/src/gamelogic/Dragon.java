package gamelogic;

import java.util.Random;

import gamelogic.Maze;

public class Dragon {
	public int x;
	public int y;
	public char is_sleeping;

	public Dragon(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.is_sleeping = 'D';
	}
	
	
	public Dragon(Maze maze) {
		Random rand = new Random(System.currentTimeMillis());

		// Get random starting position for dragon
		while (true) {
			x = rand.nextInt(maze.board.length - 2);
			y = rand.nextInt(maze.board.length - 2);
			
			x++;//the zero position is wall
			y++;//
			
			if (maze.board[y][x] == ' ' && maze.board[y - 1][x] != 'H'
					&& maze.board[y + 1][x] != 'H'
					&& maze.board[y][x - 1] != 'H'
					&& maze.board[y][x + 1] != 'H') {
				break;
			}
		}

		this.is_sleeping = 'D';

		// Place dragon on maze
		maze.board[y][x] = is_sleeping;
	}

	public void set_Position(int x, int y, Maze maze) {
		maze.board[y][x] = ' ';
		this.x = x;
		this.y = y;

		// Place dragon on maze
		maze.board[y][x] = is_sleeping;

	}

	public int mode(Maze maze) {
		Random rand = new Random(System.currentTimeMillis());
		int mode = rand.nextInt() % 2; // 0 - move, 1 - sleep

		return mode;

	}

	public void wakeUp(Maze maze) {
		is_sleeping = 'D';
		maze.board[y][x] = is_sleeping;
	}

	public void fallAsleep(Maze maze) {
		is_sleeping = 'd';
		maze.board[y][x] = is_sleeping;
	}

	public void dies(Maze maze) {
		maze.board[y][x] = ' ';
	}

	public void move(Maze maze) {
		Random rand = new Random(System.currentTimeMillis());

		int i = rand.nextInt() % 4;

		if (i == 0) {
			if (maze.board[y - 1][x] == ' ') {
				maze.board[y][x] = ' ';
				y--;
				maze.board[y][x] = is_sleeping;
			} else if (maze.board[y - 1][x] == 'E') {
				maze.board[y][x] = ' ';
				y--;
				maze.board[y][x] = 'F';
			}
		}

		if (i == 1) {
			if (maze.board[y][x + 1] == ' ')

			{
				maze.board[y][x] = ' ';
				x++;
				maze.board[y][x] = is_sleeping;
			} else if (maze.board[y][x + 1] == 'E')

			{
				maze.board[y][x] = ' ';
				x++;
				maze.board[y][x] = 'F';
			}
		}

		if (i == 2) {
			if (maze.board[y + 1][x] == ' ') {
				maze.board[y][x] = ' ';
				y++;
				maze.board[y][x] = is_sleeping;

			} else if (maze.board[y + 1][x] == 'E') {
				maze.board[y][x] = ' ';
				y++;
				maze.board[y][x] = 'F';
			}
		}

		if (i == 3) {
			if (maze.board[y][x - 1] == ' ') {
				maze.board[y][x] = ' ';
				x--;
				maze.board[y][x] = is_sleeping;
			} else if (maze.board[y][x - 1] == 'E') {
				maze.board[y][x] = ' ';
				x--;
				maze.board[y][x] = 'F';
			}
		}

		if ((maze.sword.y != y || maze.sword.x != x) && maze.sword_exists) {
			maze.board[maze.sword.y][maze.sword.x] = 'E';
		}
	}

}