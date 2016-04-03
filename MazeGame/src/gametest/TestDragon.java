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
		
		game.getMaze().setBoard(m1);
		
		game.getMaze().getSword().setX(1);
		game.getMaze().getSword().setY(3);
		
		game.getDragons().get(0).setX(2);
		game.getDragons().get(0).setY(2);
		
		boolean move_left = false;
		boolean move_right = false;
		boolean move_down = false;
		boolean move_up = false;
		
		while (!move_left || !move_right || !move_down || !move_up) {
			int tmp_x = game.getDragons().get(0).getX();
			int tmp_y = game.getDragons().get(0).getY();
			
			game.getDragons().get(0).move(game.getMaze());
			
			if (game.getDragons().get(0).getX() - tmp_x == 1) {
				move_right = true;
				System.out.println("right");
			}
			else if (game.getDragons().get(0).getX() - tmp_x == -1) {
				move_left = true;

			}
			else if (game.getDragons().get(0).getY() - tmp_y == 1) {
				move_down = true;
			}
			else if (game.getDragons().get(0).getY() - tmp_y == -1) {
				move_up = true;
			}
		}
	}
	
	@Test
	public void testDragonSleep() {
		Game game = new Game(5,1);
		
		game.getMaze().setBoard(m1);
		game.getDragons().get(0).setX(2);
		game.getDragons().get(0).setY(2);
		
		boolean sleep = false;
		boolean wake_up = false;
		
		while (!sleep || !wake_up) {
			if (game.getDragons().get(0).getSymbol() == 'D') {
				if (game.getDragons().get(0).mode(game.getMaze()) == 1) {
					game.getDragons().get(0).fallAsleep(game.getMaze());
					sleep = true;
				}
			}
			else if (game.getDragons().get(0).getSymbol() == 'd') {
				if (game.getDragons().get(0).mode(game.getMaze()) == 0) {
					game.getDragons().get(0).wakeUp(game.getMaze());
					wake_up = true;
				}
			}
		}
	}
}
