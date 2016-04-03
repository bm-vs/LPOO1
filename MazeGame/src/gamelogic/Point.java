package gamelogic;

/**
 * Class that represents a point with x,y coordinates.
 */
public class Point {
	private int x;
	private int y;
	
	/**
	 * Creates a point with 0,0 coordinates.
	 */
	public Point() {
		x = 0;
		y = 0;
	}
	
	/**
	 * Creates a point with the x,y coordinates.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;		
	}
	
	/**
	 * Returns the x-coordinate of the point.
	 * @return x-coordinate
	 */	
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the y-coordinate of the point.
	 * @return y-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the x-coordinate of the point.
	 * @param x x-coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets the y-coordinate of the point.
	 * @param y y-coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	
	/**
	 * Compares the point to the given object.
	 * @return true if both coordinates are the same. false if otherwise.
	 */
	public boolean equals (Object p)
	{
		return (((Point)p).x == x && ((Point)p).y == y);
	}
}
