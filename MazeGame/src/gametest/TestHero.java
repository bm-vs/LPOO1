package gametest;

import static org.junit.Assert.*;

import org.junit.Test;

import gamelogic.*;

public class TestHero {

	char[][] m1 = {
			{ 'X', 'X', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', 'H', 'S' },
			{ 'X', ' ', ' ', ' ', 'X' }, 
			{ 'X', 'E', ' ', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };

	@Test
	public void testStarts() {
		Hero h= new Hero(m1);
		assertequal(3,h.getX());
		assertequal(1,h.getY());
		
	};
	
	private void assertequal(int i, int x) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testMoveHeroToFreeCell() {
		Game game = new Game(5,1);

		game.getMaze().setGrid(m1);
		game.getHero().setX(3);
		game.getHero().setY(1);

		assertEquals(new Point(1,3), game.getHero().getHeroPosition());
		game.getHero().move("a", game.getMaze());
		
		assertEquals(new Point(1,2), game.getHero().getHeroPosition());
	};
	
	@Test 
	public void testHeroWall() {
		Game game = new Game(5,1);

		game.getMaze().setGrid(m1);
		game.getHero().setX(3);
		game.getHero().setY(1);
		game.getMaze().getSword().setX(1);
		game.getMaze().getSword().setY(3);
		
		assertEquals(false,game.getHero().move("w", game.getMaze()));
	
	}
	
	@Test
	public void testHeroSword() {
		Game game = new Game(5,1);

		game.getMaze().setGrid(m1);
		game.getHero().setX(3);
		game.getHero().setY(1);
		
		game.getMaze().getSword().setX(1);
		game.getMaze().getSword().setY(3);
		
		
		game.getHero().move("a", game.getMaze());
		game.getHero().move("a", game.getMaze());
		game.getHero().move("s", game.getMaze());
		game.getHero().move("s", game.getMaze());
		
		assertEquals(true,game.getHero().pickUpSword(game.getMaze().getSword(),game.getMaze()));
		
	
	}

	@Test
	public void testHeroDies() {
		Game game = new Game(5,1);

		game.getMaze().setGrid(m1);
		game.getHero().setX(3);
		game.getHero().setY(1);
		
		game.getDragons().get(0).setX(3);
		game.getDragons().get(0).setY(3);
		
		game.getHero().move("s", game.getMaze());
			
		assertEquals(1, game.getHero().fightDragon(game.getDragons().get(0)));
	}
	
	@Test
	public void testHeroKillDragon() {
		Game game = new Game(5,1);

		game.getMaze().setGrid(m1);
		game.getHero().setX(3);
		game.getHero().setY(1);
		game.getHero().setSymbol('A');
		
		game.getDragons().get(0).setX(3);
		game.getDragons().get(0).setY(3);

		game.getHero().move("s", game.getMaze());
		
		assertEquals(2, game.getHero().fightDragon(game.getDragons().get(0)));

	}
	
	@Test
	public void testHerowins() {
		Game game = new Game(5,1);

		game.getMaze().setGrid(m1);
		game.getHero().setX(3);
		game.getHero().setY(1);
		game.getHero().setSymbol('A');//pickup sword
		
		game.getMaze().getExit().setX(4);
		game.getMaze().getExit().setY(1);
		game.getDragons().get(0).setX(3);
		game.getDragons().get(0).setY(3);
		
				
		game.getHero().move("s", game.getMaze());
		if(game.getHero().fightDragon(game.getDragons().get(0))== 2)
		{
			game.getMaze().getGrid()[1][4]=' ';
		}

		assertEquals(' ', game.getMaze().getGrid()[1][4]);//killed dragon
		
		game.getHero().move("w", game.getMaze());
		game.getHero().move("d", game.getMaze());
		
		assertEquals(true, (game.getHero().getX() == game.getMaze().getExit().getX() && game.getHero().getY() == game.getMaze().getExit().getY()));	
	}

	@Test
	public void testHeroOut() {
		Game game = new Game(5,1);

		game.getMaze().setGrid(m1);
		game.getHero().setX(3);
		game.getHero().setY(1);

		game.getMaze().getExit().setX(4);
		game.getMaze().getExit().setY(1);


		game.getHero().move("d", game.getMaze());
		
		assertEquals(false, (game.getHero().getX() == game.getMaze().getExit().getX() && game.getHero().getY() == game.getMaze().getExit().getY()));	
	}
	
	@Test
	public void testHeroOutWithSword() {
		Game game = new Game(5,1);

		game.getMaze().setGrid(m1);
		game.getHero().setX(3);
		game.getHero().setY(1);
		game.getHero().setSymbol('A');

		game.getMaze().getExit().setX(4);
		game.getMaze().getExit().setY(1);


		game.getHero().move("d", game.getMaze());
		
		assertEquals(false, (game.getHero().getSymbol() == 'A' && game.getHero().getX() == game.getMaze().getExit().getX() && game.getHero().getY() == game.getMaze().getExit().getY()));	
	}
	
}