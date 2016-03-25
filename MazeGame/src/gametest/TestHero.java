package gametest;

import static org.junit.Assert.*;

import org.junit.Test;

import gamelogic.*;

public class TestHero {

	char[][] m1 = {
			{ 'X', 'X', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', 'H', 'S' },
			{ 'X', ' ', 'X', ' ', 'X' }, 
			{ 'X', 'E', ' ', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };

	@Test
	public void testMoveHeroToFreeCell() {
		Game game = new Game(5,1);

		game.maze.board = m1;
		game.hero.x = 3;
		game.hero.y = 1;

		assertEquals(new Point(1,3), game.hero.getHeroPosition());
		game.hero.move("a", game.maze);
		
		assertEquals(new Point(1,2), game.hero.getHeroPosition());
	};
	
	@Test
	public void testHeroWall() {
		Game game = new Game(5,1);

		game.maze.board = m1;
		game.hero.x = 3;
		game.hero.y = 1;
		game.maze.sword.x=1;
		game.maze.sword.y = 3;
		
		game.hero.move("a", game.maze);
		game.hero.move("a", game.maze);
		game.hero.move("s", game.maze);
		game.hero.move("s", game.maze);
			
		assertEquals(true,game.hero.pickUpSword(game.maze.sword, game.maze));
	}
	
	@Test
	public void testHeroSword() {
		Game game = new Game(5,1);

		game.maze.board = m1;
		game.hero.x = 3;
		game.hero.y = 1;
		
		game.maze.sword.x = 1;
		game.maze.sword.y = 3;
		
		
		game.hero.move("a", game.maze);
		game.hero.move("a", game.maze);
		game.hero.move("s", game.maze);
		game.hero.move("s", game.maze);
		
		assertEquals(true,game.hero.pickUpSword(game.maze.sword,game.maze));
		
	
	}

	@Test
	public void testHeroDies() {
		Game game = new Game(5,1);

		game.maze.board = m1;
		game.hero.x = 3;
		game.hero.y = 1;
		
		game.dragons.get(0).x = 3;
		game.dragons.get(0).y = 3;
		
		game.hero.move("s", game.maze);
			
		assertEquals(1, game.hero.fightDragon(game.dragons.get(0)));
	}
	
	@Test
	public void testHeroKillDragon() {
		Game game = new Game(5,1);

		game.maze.board = m1;
		game.hero.x = 3;
		game.hero.y = 1;
		game.hero.has_sword = 'A';
		
		game.dragons.get(0).x = 3;
		game.dragons.get(0).y = 3;

		game.hero.move("s", game.maze);
		
		assertEquals(2, game.hero.fightDragon(game.dragons.get(0)));
	
	
	}
	
	@Test
	public void testHerowins() {
		Game game = new Game(5,1);

		game.maze.board = m1;
		game.hero.x = 3;
		game.hero.y = 1;
		game.hero.has_sword = 'A';//pickup sword
		
		game.maze.exit.x = 4;
		game.maze.exit.y = 1;
		game.dragons.get(0).x = 3;
		game.dragons.get(0).y = 3;
		
				
		game.hero.move("s", game.maze);
		if(game.hero.fightDragon(game.dragons.get(0))== 2)
		{
			game.maze.board[1][4]=' ';
		}
		assertEquals(' ', game.maze.board[1][4]);//killed dragon
		
		game.hero.move("w", game.maze);
		game.hero.move("d", game.maze);
		
		assertEquals(true, (game.hero.x == game.maze.exit.x && game.hero.y == game.maze.exit.y));	
	}

	@Test
	public void testHeroOut() {
		Game game = new Game(5,1);

		game.maze.board = m1;
		game.hero.x = 3;
		game.hero.y = 1;

		game.maze.exit.x = 4;
		game.maze.exit.y = 1;


		game.hero.move("d", game.maze);
		
		assertEquals(false, (game.hero.x == game.maze.exit.x && game.hero.y == game.maze.exit.y));	
	}
	
	@Test
	public void testHeroOutWithSword() {
		Game game = new Game(5,1);

		game.maze.board = m1;
		game.hero.x = 3;
		game.hero.y = 1;
		game.hero.has_sword = 'A';

		game.maze.exit.x = 4;
		game.maze.exit.y = 1;


		game.hero.move("d", game.maze);
		
		assertEquals(false, (game.hero.has_sword == 'A' && game.hero.x == game.maze.exit.x && game.hero.y == game.maze.exit.y));	
	}
	
}