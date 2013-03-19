/**
 *  @author Mike Della Donna
 */
package land;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import search.SearchStrategy;


public class Land {

	private Cell[][] land;
	private int width;
	private int height;
	
	public Land(int width, int height)
	{
		land = new Cell[width][height];
		this.width = width;
		this.height = height;
	}
	
	public void populateLand(Cell[][] newLand)
	{
		this.land = newLand;
		this.width = newLand.length;
		this.height = newLand[0].length;
	}
	
	/**
	 * Constructs a path from start to end using specified strategy
	 * 
	 * @param start point indicating the starting position
	 * @param end point indicating the starting position
	 * @return A List of Cells representing the path
	 */
	public List<Cell> search(Point start, Point end, SearchStrategy strategy)
	{
		return strategy.search(start, end, this);
	}
	
	/**
	 * Returns the cell at that point
	 * @param p
	 * @return null if location is invalid, otherwise return Cell at that point
	 */
	public Cell getCell(Point p)
	{
		if(p.x >= 0 && p.y >= 0 && p.x < width && p.y < height)
		{
			return land[p.x][p.y];
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * returns a list of all the cells in this land
	 * no guaranteed order
	 * 
	 * @return A list of all the cells in this Land
	 */
	public List<Cell> getCells()
	{
		List<Cell> cells = new LinkedList<Cell>();
		for(Cell[] c : land)
			cells.addAll(java.util.Arrays.asList(c));
		return cells;
	}
	
	
	/**
	 * Returns a List of all the viable cells adjacent to the given point
	 * 
	 * @param p the point in question
	 * @return a list of all the adjacent cells
	 */
	public List<Cell> getAdjacentCells(Point p)
	{
		List<Cell> temp = new ArrayList<Cell>();
		
		for(Point pp : p.getCardinalPoints())
		{
			if(pp.x >= 0 && pp.y >= 0 && pp.x < width && pp.y < height)
				temp.add(land[pp.x][pp.y]);
		}
		
		return temp;
	}
	
	/**
	 * calculates the cost of a given path
	 * the path size must be at least 1
	 * 
	 * @param path a List of Cell that represents the path
	 * @return a double representing the cost of the path
	 */
	public double calculateCost(List<Cell> path)
	{
		if(path.size() < 1)
			return 0;
			
		double cost = 0;
		int previousElevation = path.get(0).elevation;
		double gas = 0;
		
		for(Cell c : path)
		{
			gas = 1;
			
			if(c.elevation > previousElevation)
			{
				gas *= Math.abs(c.elevation - previousElevation);
			}
			else if(c.elevation > previousElevation)
			{
				gas /= Math.abs(previousElevation - c.elevation);
			}
			
			cost += (c.cost) + gas;
			previousElevation = c.elevation;
		}
		
		return cost;
	}
}
