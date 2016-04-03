package gamelogic;

/**
 * Interface used for the creation of maze builder.
 */

public interface IMazeBuilder {
	public char[][] buildMaze(int size) throws IllegalArgumentException;
}
