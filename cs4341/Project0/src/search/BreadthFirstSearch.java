/**
 *  @author Mike Della Donna
 */
package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import land.Cell;
import land.Land;
import land.Point;


/**
 * Constructs a path from start to end using a depth first search strategy
 * 
 * @author Mike Della Donna
 *
 */
public class BreadthFirstSearch implements SearchStrategy {

	LinkedList<Cell> queue;
	ArrayList<Cell> greylist; //cells in the queue
	ArrayList<Cell> blacklist;  //cells explored
	HashMap<Cell,Cell> parentMap;  //map of each cell to its parent
	Logger log;
	
	public BreadthFirstSearch()
	{
		queue = new LinkedList<Cell>();
		greylist = new ArrayList<Cell>();
		blacklist = new ArrayList<Cell>();
		parentMap = new HashMap<Cell, Cell>();
		log = new Logger();
	}
	
	@Override
	public List<Cell> search(Point start, Point end, Land land)
	{
		log.visitCell();
		log.log("Adding "+land.getCell(start)+" to the list of in progress cells");
		queue.add(land.getCell(start));
		greylist.add(land.getCell(start));
		return search2(start, end, land);
	}
	
	private List<Cell> search2(Point start, Point end, Land land)
	{
		Cell current;
		while(queue.size() > 0)
		{
			current = queue.pop();
			log.log("Grabbing the next cell from the queue: "+current);
			log.visitCell();
			// check to see if you have reached the destination
			log.log("Checking to see if this is the destination");
			if(current.location.equals(end))
			{
				log.log("Destination reached, putting together the path");
				// if you have, use the parent map to get back to the original start
				//create a list to hold the path
				log.log("Creating a list to hold the path");
				LinkedList<Cell> path = new LinkedList<Cell>();
				//get the cell at the end
				log.log("Adding cells in reverse order, starting with the destination");
				//push it onto the queue you're going to return
				path.addFirst(current);
				//get it's parent
				current = parentMap.get(current);
				//while it has a parent, keep pushing them onto the queue
				log.log("Adding each parent in turn until we get to the start, which has no parent");
				while(current != null)
				{
					path.addFirst(current);
					current = parentMap.get(current);
				}
				//return the queue as a list in the correct order
				log.log("Returning the path");
				return path;
			}
			
			// if you haven't, check to see if you can move to an adjacent cell
			log.log("Haven't reached the destination, blacklist this cell");
			blacklist.add(current);
			
			log.log("Checking adjacent cells");
			List<Cell> temp = land.getAdjacentCells(current.location);
			for(Cell c : temp)
			{
				log.log("Checking "+c);
				//if c is unoccupied and has not been explored yet (not on the greylist or blacklist)
				if(!c.occupied && !greylist.contains(c) && !blacklist.contains(c))
				{
					// explore this location later
					queue.add(c);
					// mark this location as being in progress
					greylist.add(c);
					// add start as its parent
					parentMap.put(c, current);
					log.log(c +" is a new cell, markingit and adding to the end of the queue");
					log.log("It's parent will be the current cell, added to the parent map");
					
				}
			}
		}

		log.log("There aren't any more cells, couldn't find the destination");
		// return destination not found
		return null;
			
	}

	@Override
	public Logger getLogger() {
		return log;
	}

}
