package gamelogic;

import gamelogic.Maze;

public class Dragon {
	public int x;
	public int y;
	public char is_sleeping;
	public boolean is_alive;

	public Dragon(int x, int y) {
		this.x = x;
		this.y = y;
		this.is_sleeping = 'D';
		this.is_alive = true;
	}

	public int mode(Maze maze){
		int mode = maze.rand.nextInt() % 2; // 0 - move  // 1 - sleep
		
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
		maze.board[maze.exit.y][maze.exit.x] = ' ';
		is_alive = false;
	}

	public void move(Maze maze) {
		int i = maze.rand.nextInt() % 4;

		if (i == 0) {
			if (maze.board[y - 1][x] == ' ') {
				maze.board[y][x] = ' ';
				y--;
				maze.board[y][x] = is_sleeping;
			}
			else if (maze.board[y - 1][x] == 'E') {
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
			}
			else if (maze.board[y][x + 1] == 'E')

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
			
			}
			else if (maze.board[y + 1][x] == 'E') {
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
			}
			else if (maze.board[y][x - 1] == 'E') {
				maze.board[y][x] = ' ';
				x--;
				maze.board[y][x] = 'F';
			}
		}
		
		
		if ((maze.sword.y != y || maze.sword.x !=x) && maze.sword_exists) {
			maze.board[maze.sword.y][maze.sword.x] = 'E';
		}
	}

}