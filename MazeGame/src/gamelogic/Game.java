package gamelogic;

import gamelogic.Dragon;
import gamelogic.Hero;
import gamelogic.Maze;

public class Game {
	public Maze maze;
	public Hero hero;
	public Dragon dragon;
	
	public Game() {		
		maze = new Maze();
		hero = new Hero(1,1);
		dragon = new Dragon(1,3);
	}
	
	public String return_board() {
		return maze.return_board();
	}
	
	public int play(String game_mode, String key) {	
		if (game_mode.equals("0") && dragon.is_alive) {
			dragon.move(maze);
		}
		if (game_mode.equals("1") && dragon.is_alive) {
			if (dragon.is_sleeping == 'D') {
				if (dragon.mode(maze) == 0)
					dragon.move(maze);

				else if (dragon.mode(maze) == 1)
					dragon.fallAsleep(maze);
			}
			else if (dragon.is_sleeping == 'd')
				if (dragon.mode(maze) == 0)
					dragon.wakeUp(maze);

				else if (dragon.mode(maze) == 1)
					dragon.wakeUp(maze);
		}
		// gameMode == "2" -> stand still
		
		
		
		hero.move(key, maze);
		if (hero.x == maze.exit.x && hero.y == maze.exit.y) {
			return 1;
		}
		
		hero.pickUpSword(maze.sword, maze);		
		
		switch(hero.fightDragon(dragon)) {
		case 1:	return 2; 				 // LOSE
		case 2: dragon.dies(maze); 		 // WIN
				return 3; 
		}
		
		return 0;		
	}
}