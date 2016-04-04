package gamelogic;

import java.util.ArrayList;

import gamelogic.Dragon;
import gamelogic.Hero;
import gamelogic.Maze;

/**
 * Class to represent the game state.
 **/
public class Game {
	private Maze maze;
	private Hero hero;
	private String game_mode;
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();

	/**
	 * Creates an empty game.
	 */
	public Game() {
		maze = new Maze();
		hero = new Hero();
	}
	
	/**
	 * Creates a game from the given grid.
	 * <p>
	 * Checks the grid for the position of the sword, exit, hero and dragons and creates the game state accordingly.
	 * </p>
	 * @param grid grid to build the game from
	 * @throws NotFound if either one of the elements are missing from grid.
	 */
	public Game (char[][] grid) throws NotFound {
		try {
			 maze = new Maze(grid);
		}
		catch(Exception e) {
			throw new NotFound();
		}
		
		try {
			hero = new Hero(grid);
		}
		catch(Exception e) {
			throw new NotFound();
		}
		
		for (int i = 0; i < grid.length; i++) {
			for (int a = 0; a < grid.length; a++) {
				if (grid[a][i] == 'D') {
					dragons.add(new Dragon(i,a));
				}
			}
		}
		
		if (dragons.size() == 0) {
			throw new NotFound();
		}
	}
	
	/**
	 * Creates a game with the given maze size and number of dragons.
	 * <p>
	 * Creates a random maze with the given size and places the sword, exit, hero and dragons randomly on it.
	 * </p>
	 * @param maze_size size of the maze
	 * @param number_dragons number of dragons
	 */
	public Game(int maze_size, int number_dragons) {		
		maze = new Maze(maze_size);
		hero = new Hero(maze);

		for (int i = 0; i < number_dragons; i++) {
			dragons.add(new Dragon(maze));
		}
	} 

	/**
	 * Returns the maze of the game.
	 * @return maze
	 */
	public Maze getMaze() {
		return maze;
	}
	
	/**
	 * Returns the hero of the game.
	 * @return hero
	 */
	public Hero getHero() {
		return hero;
	}
	
	/**
	 * Returns the game mode that defines the dragons' behavior.
	 *  <p>
	 *  "0" makes the dragons move.
	 *  </p>
	 *  <p>
	 *  "1" makes the dragons move and sleep.
	 *  </p>
	 *  <p>
	 *  "2" makes the dragons stand still.
	 *  </p>
	 * @return game mode
	 */
	public String getGameMode() {
		return game_mode;
	}
	
	/**
	 * Returns the dragons of the game.
	 * @return dragons
	 */
	public ArrayList<Dragon> getDragons() {
		return dragons;
	}
	
	/**
	 * Sets the maze of the game.
	 * @param maze maze
	 */
	public void setMaze(Maze maze) {
		this.maze = maze;
	}
	
	/**
	 * Sets the hero of the game.
	 * @param hero hero
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	/**
	 * Sets the game mode that defines the dragons' behavior.
	 *  <p>
	 *  "0" makes the dragons move.
	 *  </p>
	 *  <p>
	 *  "1" makes the dragons move and sleep.
	 *  </p>
	 *  <p>
	 *  "2" makes the dragons stand still.
	 *  </p>
	 * @param game_mode game mode
	 */
	public void setGameMode(String game_mode) {
		this.game_mode = game_mode;
	}
	
	/**
	 * Sets the dragons of the game.
	 * @param dragons dragons
	 */
	public void setDragons(ArrayList<Dragon> dragons) {
		this.dragons = dragons;
	}

	/**
	 * Removes all dragons from the game.
	 */
	public void clearDragons() {
		dragons.clear();
	}

	/**
	 * Adds the given dragon to the game.
	 * @param d dragon to add
	 */
	public void addDragon(Dragon d) {
		dragons.add(d);
	}

	/**
	 * Returns the number of dragons in the game.
	 * @return number of dragons in the game
	 */
	public int getNumDragons() {
		return dragons.size();
	}
	
	/**
	 * Returns a formatted string of the maze grid.
	 * @return formatted string of the maze grid
	 */
	public String return_grid() {
		return maze.return_grid();
	}

	/**
	 * Simulates a user's play.
	 * <p>
	 * Moves the hero according to the key string given("a"-left, "d"-right, "w"-up, "s"-down).
	 * </p>
	 * <p>
	 * Simulates the dragons' actions according to the given game mode("0"-dragons move,"1"-dragons move/sleep,"2"-dragons stand still).
	 * </p>
	 * <p>
	 * Simulates the fight between hero and dragons if they are in adjacent cells.
	 * </p>
	 * @param game_mode gives the dragon behavior
	 * @param key gives the hero's movement
	 * @return 1 if the hero is at the exit. 2 if the hero has died. 3 if all dragons have died. 0 for default.
	 */
	public int play(String game_mode, String key) {
		if (game_mode.equals("0")) {
			for (int i = 0; i < dragons.size(); i++) {
				try {
					dragons.get(i).move(maze);
				}
				catch (FightHero e) {
					if (e.getHeroDies()) {
						return 2;
					}
					else {
						dragons.get(i).dies(maze);
						dragons.remove(dragons.get(i));
						if (dragons.isEmpty())
							maze.getGrid()[maze.getExit().getY()][maze.getExit().getX()] = ' ';
						
						return 3;
					}
				}
			}
		}

		if (game_mode.equals("1")) {
			for (int i = 0; i < dragons.size(); i++) {
				if (dragons.get(i).getSymbol() == 'D') {
					if (dragons.get(i).mode(maze) == 0)
						try {
							dragons.get(i).move(maze);
						}
						catch (FightHero e) {
							if (e.getHeroDies()) {
								return 2;
							}
							else {
								dragons.get(i).dies(maze);
								dragons.remove(dragons.get(i));
								if (dragons.isEmpty())
									maze.getGrid()[maze.getExit().getY()][maze.getExit().getX()] = ' ';
								
								return 3;
							}
						}

					else if (dragons.get(i).mode(maze) == 1)
						dragons.get(i).fallAsleep(maze);

				} else if (dragons.get(i).getSymbol() == 'd')
					if (dragons.get(i).mode(maze) == 0 || dragons.get(i).mode(maze) == 1)
						dragons.get(i).wakeUp(maze);	
			}
		}
		// gameMode == "2" -> stand still


		if (!hero.move(key, maze)) {
			return 0;
		}
		
		if (hero.getX() == maze.getExit().getX() && hero.getY() == maze.getExit().getY()) {
			return 1;
		}

		hero.pickUpSword(maze.getSword(), maze);		

		for (Dragon d : dragons) {

			switch (hero.fightDragon(d)) {
			case 1:
				return 2; // LOSE
			case 2:
				d.dies(maze); // WIN
				dragons.remove(d);
				if (dragons.isEmpty())
					maze.getGrid()[maze.getExit().getY()][maze.getExit().getX()] = ' ';
				
				return 3;
			}
		}

		return 0;
	}
}