package game.logic;

public class Point {
	public int x;
	public int y;
	
	public Point(int y, int x)
	{
		this.x = x;
		this.y = y;		
	}
	
	public boolean equals (Object p)
	{
		return (((Point)p).x == x && ((Point)p).y == y);
	}
}
