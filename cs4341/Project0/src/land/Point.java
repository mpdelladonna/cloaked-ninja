/**
 *  @author Mike Della Donna
 */
package land;

import java.util.ArrayList;
import java.util.List;

public class Point {

	public int x,y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Starting the lower left corner, return all surrounding points moving counter clockwise
	 * 
	 * @return a List of all the Points surrounding this point
	 */
	public List<Point> getCardinalPoints()
	{
		List<Point> temp = new ArrayList<Point>();
		
		temp.add(new Point(x-1,y-1));
		temp.add(new Point(x,y-1));
		temp.add(new Point(x+1,y-1));
		
		temp.add(new Point(x-1,y));
		
		temp.add(new Point(x+1,y+1));
		temp.add(new Point(x,y+1));
		temp.add(new Point(x-1,y+1));
		
		temp.add(new Point(x+1,y));

		return temp;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Point)
		{
			if(((Point) obj).x == this.x && ((Point) obj).y == this.y)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	

	

}
