/**
 *  @author Mike Della Donna
 */
package search;

import java.util.List;

import land.Cell;
import land.Land;
import land.Point;


public interface SearchStrategy 
{
	/**
	 * Constructs a path from start to end
	 * 
	 * @param start point indicating the starting position
	 * @param end point indicating the starting position
	 * @return A List of Cells representing the path
	 */
	public List<Cell> search(Point start, Point end, Land land);
	
	/**
	 * returns a log describing the search process
	 * 
	 * @return A log describing the search
	 */
	public Logger getLogger();
}
