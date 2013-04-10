package project2;

public class Project2DemoP2 
{

	public static void main(String[] args) 
	{
		CCVParkingLot lot = new CCVParkingLot(8);
		
		//lot.setSeed(2345);
		lot.initializeRandomLot();
		
		//int[] newLot = {2,0,3,1};
		//int[] newLot = {3,0,2,1};
		//lot.setLot(newLot);
		
		//System.out.println(lot.conflicts());
		
		try {
			lot.solve(1000000);
		} catch (NoPathFoundException e) {
			System.out.println("solution not found");
		}
		lot.printLot();
	}
}
