package gametest;

import org.junit.Test;
import gamelogic.*;


public class TestDragon {
	char[][] m1 = {
			{ 'X', 'X', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', ' ', 'S' },
			{ 'X', ' ', 'D', ' ', 'X' }, 
			{ 'X', 'E', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };
	
	@Test (timeout=1000)
	public void testDragonMoves() { // Check if every direction
		Game game = new Game(5,1);
		
		game.maze.board = m1;
		
		game.maze.sword.x = 1;
		game.maze.sword.y = 3;
		
		game.dragons.get(0).x = 2;
		game.dragons.get(0).y = 2;
		
		boolean move_left = false;
		boolean move_right = false;
		boolean move_down = false;
		boolean move_up = false;
		
		while (!move_left || !move_right || !move_down || !move_up) {
			int tmp_x = game.dragons.get(0).x;
			int tmp_y = game.dragons.get(0).y;
			
			game.dragons.get(0).move(game.maze);
			
			if (game.dragons.get(0).x - tmp_x == 1) {
				move_right = true;
				System.out.println("right");
			}
			else if (game.dragons.get(0).x - tmp_x == -1) {
				move_left = true;

			}
			else if (game.dragons.get(0).y - tmp_y == 1) {
				move_down = true;
			}
			else if (game.dragons.get(0).y - tmp_y == -1) {
				move_up = true;
			}
		}
	}
	
	@Test
	public void testDragonSleep() {
		Game game = new Game(5,1);
		
		game.maze.board = m1;
		game.dragons.get(0).x = 2;
		game.dragons.get(0).y = 2;
		
		boolean sleep = false;
		boolean wake_up = false;
		
		while (!sleep || !wake_up) {
			if (game.dragons.get(0).is_sleeping == 'D') {
				if (game.dragons.get(0).mode(game.maze) == 1) {
					game.dragons.get(0).fallAsleep(game.maze);
					sleep = true;
				}
			}
			else if (game.dragons.get(0).is_sleeping == 'd') {
				if (game.dragons.get(0).mode(game.maze) == 0) {
					game.dragons.get(0).wakeUp(game.maze);
					wake_up = true;
				}
			}
		}
	}
}
