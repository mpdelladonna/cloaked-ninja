/**
 *  @author Mike Della Donna
 */
package project0;

import java.util.List;

import land.Cell;
import land.Land;
import land.Point;

import search.BreadthFirstSearch;
import search.DepthFirstSearch;
import search.SearchStrategy;
import search.UniformCostSearch;

public class MainTestCase1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Cell[] row4 = {new Cell(true,true,false,true,0,0,"rock",new Point(4,0)),
						new Cell(false, false, false, true, 0, 0, null, new Point(4,1)),
						new Cell(false, false, false, true, 0, 0, null, new Point(4,2)),
						new Cell(false, false, false, true, 0, 0, null, new Point(4,3)),
						new Cell(false, false, false, true, 0, 0, null, new Point(4,4))};
		
		Cell[] row3 = {new Cell(false,false,false,true,0,0,null,new Point(3,0)),
						new Cell(true, true, false, true, 0, 0, "rock", new Point(3,1)),
						new Cell(false,false,false,true,4,10,null, new Point(3,2)),
						new Cell(false,false,false,true,2,10,null, new Point(3,3)),
						new Cell(false, false, false, true, 0, 0, null, new Point(3,4))};
		
		Cell[] row2 = {new Cell(false, false, false, true, 0, 0, null, new Point(2,0)),
						new Cell(false, false, false, true, 4, 10, null, new Point(2,1)),
						new Cell(false, false, false, true, 6, 10, null, new Point(2,2)),
						new Cell(false, false, false, true, 4, 10, null, new Point(2,3)),
						new Cell(false, false, false, true, 0, 0, null, new Point(2,4))};
		
		Cell[] row1 = {new Cell(false, false, false, true, 0, 0, null, new Point(1,0)),
					   new Cell(false, false, false, true, 2, 10, null, new Point(1,1)),
					   new Cell(false, false, false, true, 4, 10, null, new Point(1,2)),
					   new Cell(true,  true,  false, true, 0, 0,"rock",new Point(1,3)),
					   new Cell(false, false, false, true, 0, 0, null, new Point(1,4))};
		
		Cell[] row0 = {new Cell(false, false, false, true, 0, 0, null,new Point(0,0)),
				new Cell(false, false, false, true, 0, 0, null, new Point(0,1)),
				new Cell(false, false, false, true, 0, 0, null, new Point(0,2)),
				new Cell(false, false, false, true, 0, 0, null, new Point(0,3)),
				new Cell(true,true,false,true,0,0,"rock", new Point(0,4))};
		
		Cell[][] grid = {row0, row1, row2, row3, row4};
		
		
		Land land = new Land(5,5);
		
		land.populateLand(grid);
		
		SearchStrategy dfs = new DepthFirstSearch();
		SearchStrategy bfs = new BreadthFirstSearch();
		SearchStrategy ucs = new UniformCostSearch();
		
		List<Cell> list = land.search(new Point(0,0), new Point(4,4),dfs);
		System.out.println("DFS");
		for(String s : dfs.getLogger().getLog())
			System.out.println(s);
		System.out.println();
		for(Cell c : list)
			System.out.println(c);
		System.out.println(dfs.getLogger().getLog().size()+" steps executed");
		System.out.println(dfs.getLogger().getVistedCells()+" cells visited");
		System.out.println("Total cost of this path: "+land.calculateCost(list));
		System.out.println();
		
		list = land.search(new Point(0,0), new Point(4,4),bfs);
		System.out.println("BFS");
		for(String s : bfs.getLogger().getLog())
			System.out.println(s);
		System.out.println();
		for(Cell c : list)
			System.out.println(c);
		System.out.println(bfs.getLogger().getLog().size()+" steps executed");
		System.out.println(bfs.getLogger().getVistedCells()+" cells visited");
		System.out.println("Total cost of this path: "+land.calculateCost(list));
		
		
		System.out.println();
		list = land.search(new Point(0,0), new Point(4,4),ucs);
		System.out.println("UCS");
		for(String s : ucs.getLogger().getLog())
			System.out.println(s);
		System.out.println();
		for(Cell c : list)
			System.out.println(c);
		System.out.println(ucs.getLogger().getLog().size()+" steps executed");
		System.out.println(ucs.getLogger().getVistedCells()+" cells visited");
		System.out.println("Total cost of this path: "+land.calculateCost(list));
		
	}

}
