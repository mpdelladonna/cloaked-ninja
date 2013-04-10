
package project2;

import java.util.PriorityQueue;

/**
 * 
 * @author Mike Della Donna
 *
 */
public class Project2DemoP1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
			int[][] initial = {{7,4,2},{8,1,3},{5,6,0}};
			//int[][] initial = {{1,2,3},{4,5,6},{7,8,0}};//solved
			//int[][] initial = {{1,2,3},{4,5,6},{7,0,8}};
			ParkingLot p = new ParkingLot(initial,null,0);
			
			/*for(ParkingLot pl : p.createNextLots())
			{
				System.out.println("lot\n"+pl);
			}*/
			
			
			try {
				System.out.println(astar(p));
			} catch (NoPathFoundException e) {
				System.out.println("No path was found");
			}
	}
	
	public static ParkingLot astar(ParkingLot initialLot) throws NoPathFoundException
	{
		PriorityQueue<ParkingLot> pq = new PriorityQueue<ParkingLot>();
		
		ParkingLot currentLot;
		pq.add(initialLot);
		
		while(pq.size() > 0)
		{
			currentLot = pq.remove();
			//System.out.println(currentLot);
			if(currentLot.costToGoal() == 0)
			{
				return currentLot;
			}
			else
			{
				for(ParkingLot p : currentLot.createNextLots())
				{
					pq.add(p);
				}
			}
		}
		
		throw new NoPathFoundException();
	}

}
