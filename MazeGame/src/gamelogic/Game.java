package gamelogic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import gamelogic.Dragon;
import gamelogic.Hero;
import gamelogic.Maze;

public class Game {
	
	public Random rand = new Random(System.currentTimeMillis());
	public Maze maze;
	public Hero hero;
	public ArrayList<Dragon> dragons = new ArrayList<Dragon>();

	public Game() {
		maze = new Maze();
		hero = new Hero(1, 1); 

		int numero = rand.nextInt() % 5 + 1;// numero de dragons de 1 a 5
		for (int i = 0; i < numero; i++) {
			int x = rand.nextInt() % 8 + 1;// depois temos que mudar
			int y = rand.nextInt() % 8 + 1;

			dragons.add(new Dragon(x, y));
		}

	}

	public String return_board() {
		return maze.return_board();
	}

	public int play(String game_mode, String key) {
		if (game_mode.equals("0")) {
			for (Dragon d : dragons)
				if (d.is_alive)
					d.move(maze);
		}

		if (game_mode.equals("1")) {
			for (Dragon d : dragons) {
				if (d.is_alive) {
					if (d.is_sleeping == 'D') {
						if (d.mode(maze) == 0)
							d.move(maze);

						else if (d.mode(maze) == 1)
							d.fallAsleep(maze);

					} else if (d.is_sleeping == 'd')
						if (d.mode(maze) == 0 || d.mode(maze) == 1)
							d.wakeUp(maze);
				}
			}
		}
		// gameMode == "2" -> stand still

		hero.move(key, maze);
		if (hero.x == maze.exit.x && hero.y == maze.exit.y) {
			return 1;
		}

		hero.pickUpSword(maze.sword, maze);

		for (Dragon d : dragons) {

			int dragonsDead = 0;

			switch (hero.fightDragon(d)) {
			case 1:
				return 2; // LOSE

			case 2:
				dragonsDead++;
				d.dies(maze); // WIN
				if (dragonsDead == dragons.size())
					return 3;
			}
		}

		return 0;
	}
}