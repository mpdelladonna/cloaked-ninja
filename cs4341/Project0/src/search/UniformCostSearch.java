/**
 *  @author Mike Della Donna
 */
package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import land.Cell;
import land.Land;
import land.Point;



/**
 * Constructs a path from start to end using a depth first search strategy
 * 
 * @author Mike Della Donna
 *
 */
public class UniformCostSearch implements SearchStrategy {

	PriorityQueue<CellNode> queue;
	ArrayList<Cell> greylist;  // cells in the queue
	ArrayList<Cell> blacklist; // cells that have been explored
	HashMap<CellNode,CellNode> parentMap; //map of each cell to it's parent
	Logger log;
	
	public UniformCostSearch()
	{
		queue = new PriorityQueue<CellNode>();
		greylist = new ArrayList<Cell>();
		blacklist = new ArrayList<Cell>();
		parentMap = new HashMap<CellNode, CellNode>();
		log = new Logger();
	}
	
	@Override
	public List<Cell> search(Point start, Point end, Land land)
	{
		//add the first node to the queue
		log.log("Add the starting cell to the queue, mark as added");
		log.visitCell();
		queue.add(new CellNode(land.getCell(start),0,null));
		greylist.add(land.getCell(start));
		return search2(start,end,land);
	}
	
	public List<Cell> search2(Point start, Point end, Land land)
	{
		CellNode current;
		//while queue has elements
		log.log("While the queue has elements in it, pop, check, search");
		
		while(queue.size() > 0)
		{
			log.visitCell();
			//pop next destination from the queue
			current = queue.remove();
			log.log("Popping the next destination from the queue: "+current.cell);
			
			//is it the destination?
			log.log("Checking if the current cell is the destination.");
			if(current.cell.location.equals(end))
			{
				//yes,
				//construct a list from it's parents
				//return that list
				log.log("Destination has been found.");
				log.log("Constructing a path by following it's parents.");
				LinkedList<Cell> path = new LinkedList<Cell>();
				while(current != null)
				{
					path.addFirst(current.cell);
					current = current.parent;
				}
				log.log("Returning the path");
				return path;
				
			}
			else
			{
				//no
				//blacklist the node
				//add all the adjacent nodes not in blacklist into the priority queue with the current node as the parent
				//if the node is already in the queue, and has a lower cost, replace it's parent and cost
				log.log("The current cell is not the destination,bBlacklisting the current node");
				blacklist.add(current.cell);
				
				log.log("Getting cells adjacent to the current cell");
				List<Cell> adjacent = land.getAdjacentCells(current.cell.location);
				
				log.log("Checking each adjacent cell to see if it is in the blacklist");
				for(Cell c : adjacent)
				{
					log.log("Checking "+c);
					if(!blacklist.contains(c)  && !c.occupied)
					{
						log.log("Has not been explored, checking to see if it is in the queue");
						if(!greylist.contains(c))
						{
							log.log("Brand new cell, adding to queue and marking");
							greylist.add(c);
							queue.add(new CellNode(c,computeCost(current.cell, c),current));
						}
						else
						{
							log.log("This cell is already in the queue");
							log.log("Checking to see if this route is cheaper");
							
							Iterator<CellNode> it = queue.iterator();
							CellNode n;
							CellNode replace = null;
							while(it.hasNext())
							{
								n = it.next();
								if(n.cell.equals(c))
								{
									if(computeCost(current.cell,c) < n.cost)
									{
										log.log("This route is cheaper, replacing the old reference");
										it.remove();
										replace = new CellNode(c,computeCost(current.cell,c),current);
										break;
									}
								}
							}
							
							if(replace != null)
							{
								queue.add(replace);
							}
							else
							{
								log.log("This route was not cheaper");
							}
						}
					}
					else
					{
						log.log("Blacklisted");
					}
				}
				
			}
		}
		log.log("No destination found.");
		return null;
	}

	@Override
	public Logger getLogger() {
		return log;
	}
	
	private double computeCost(Cell start, Cell end)
	{
		double gas = 1;
		
		if(start.elevation < end.elevation)
		{
			gas *= Math.abs(start.elevation - end.elevation);
		}
		else if(start.elevation > end.elevation)
		{
			gas /= Math.abs(end.elevation - start.elevation);
		}
		
		return end.cost + gas;
	}

}

class CellNode implements Comparable<CellNode>
{
	public CellNode(Cell cell, double cost, CellNode parent) 
	{
		this.cell = cell;
		this.cost = cost;
		this.parent = parent;
	}

	Cell cell;
	double cost;
	CellNode parent;
	
	@Override
	public int compareTo(CellNode o) {
		
		double test = this.cost - o.cost;
		
		int compare = 0;
		
		if (test > 0.0)
			compare = 1;
		
		if(test < 0.0)
			compare = -1;
		
		return compare;
	}
	
	@Override
	public boolean equals(Object obj) {
		return cell.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return cell.hashCode();
	}
}
