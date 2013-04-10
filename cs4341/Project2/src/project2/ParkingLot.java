package project2;

import static java.lang.Math.*;

import java.util.LinkedList;
import java.util.List;

public class ParkingLot implements Comparable<ParkingLot>
{
	private int[][] spaces; 
	public ParkingLot previous;
	public int previousLots;
	
	public ParkingLot(int[][] initialSpots, ParkingLot previous, int numPrev)
	{
		this.previous = previous;
		this.spaces = initialSpots;
		this.previousLots = numPrev;
	}
	
	public int costToGoal()
	{
		int cost = 0;
		
		for(int i = 0; i < spaces.length; i++ )
		{
			for(int j = 0; j < spaces.length; j++ )
			{
				cost += abs(i - getCostX(spaces[i][j])) + abs(j - getCostY(spaces[i][j]));
			}
		}
		
		return cost;
	}
	
	public List<ParkingLot> createNextLots()
	{
		//"expands" this node by returning a list of possible moves from this state
		List<ParkingLot> p = new LinkedList<>();
		
		//gets the location of the empty space for this state
		int[] zero = findZero();
		//gets the empty space in the previous state to prevent going backwards
		// it starts out as -1,-1 to account for the initial lot, where previous would be null
		// the bounds checks function normally because zero[0] and zer[1] will never be -1 at the same time
		int[] pastZero  = {-1, -1};
		if(previous != null)
		{
			pastZero = previous.findZero();
		}
		
		int[] up = {zero[0],zero[1]+1};
		int[] down = {zero[0],zero[1]-1};
		int[] left = {zero[0]-1,zero[1]};
		int[] right = {zero[0]+1,zero[1]};
		
		

		//findZero returns an array where int[0] = x and int[1] = y
		
		int temp;
		int[][] tempArray;
		
		try{
			if(!( up[0] == pastZero[0] && up[1] == pastZero[1] ))
			{
				//swaps the two spots, effectively moving into an empty square
				tempArray = cloneArray(spaces);
				temp = tempArray[up[0]][up[1]];
				tempArray[up[0]][up[1]] = tempArray[zero[0]][zero[1]];
				tempArray[zero[0]][zero[1]] = temp;
				p.add(new ParkingLot(tempArray, this, previousLots+1));
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			//do nothing, this was an invalid move
		}
		
		try{
			if(!( down[0] == pastZero[0] && down[1] == pastZero[1] ))
			{
				//swaps the two spots, effectively moving into an empty square
				tempArray = cloneArray(spaces);
				temp = tempArray[down[0]][down[1]];
				tempArray[down[0]][down[1]] = tempArray[zero[0]][zero[1]];
				tempArray[zero[0]][zero[1]] = temp;
				p.add(new ParkingLot(tempArray, this, previousLots+1));
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			//do nothing, this was an invalid move
		}
		
		try{
			if(!( left[0] == pastZero[0] && left[1] == pastZero[1] ))
			{
				//swaps the two spots, effectively moving into an empty square
				tempArray = cloneArray(spaces);
				temp = tempArray[left[0]][left[1]];
				tempArray[left[0]][left[1]] = tempArray[zero[0]][zero[1]];
				tempArray[zero[0]][zero[1]] = temp;
				p.add(new ParkingLot(tempArray, this, previousLots+1));
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			//do nothing, this was an invalid move
		}
		
		try{
			if(!( right[0] == pastZero[0] && right[1] == pastZero[1] ))
			{
				//swaps the two spots, effectively moving into an empty square
				tempArray = cloneArray(spaces);
				temp = tempArray[right[0]][right[1]];
				tempArray[right[0]][right[1]] = tempArray[zero[0]][zero[1]];
				tempArray[zero[0]][zero[1]] = temp;
				p.add(new ParkingLot(tempArray, this, previousLots+1));
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			//do nothing, this was an invalid move
		}
		
		return p;
	}

	private int[] findZero() 
	{
		int[] z = {-1,-1};
		for(int i = 0; i < spaces.length; i++ )
		{
			for(int j = 0; j < spaces.length; j++ )
			{
				if(spaces[i][j] == 0)
				{
					z[0] = i; 
					z[1] = j;
					//System.out.println("z: {"+i+","+j+"}");
					return z;
				}
			}
		}
		return z;
	}

	private int getCostX(int i) {
		switch(i)
		{
		case 1:
		case 2:
		case 3:
			return 0;
		case 4:
		case 5:
		case 6:
			return 1;
		case 7:
		case 8:
		case 0:
			return 2;
		}
		throw new IllegalArgumentException();
	}

	private int getCostY(int i) {
		switch(i)
		{
		case 1:
		case 4:
		case 7:
			return 0;
		case 2:
		case 5:
		case 8:
			return 1;
		case 3:
		case 6:
		case 0:
			return 2;
		}
		throw new IllegalArgumentException();
	}

	
	@Override
	public int compareTo(ParkingLot arg0) {
		return (this.previousLots + this.costToGoal()) - (arg0.previousLots + arg0.costToGoal());
	}
	
	@Override
	public String toString()
	{
		String s = "";
		
		s += spaces[0][0] + " " + spaces[0][1] + " " + spaces[0][2] + "\n";
		s += spaces[1][0] + " " + spaces[1][1] + " " + spaces[1][2] + "\n";
		s += spaces[2][0] + " " + spaces[2][1] + " " + spaces[2][2] + "\n";

		if(previous != null)
		{
			return previous + "\n" + s;
		}
		else
		{
			return s;
		}
	}
	
	/**
	 * clones 3x3 int arrays
	 * @param arr
	 * @return
	 */
	public static int[][] cloneArray(int[][] arr)
	{
		int[][] a = new int[3][3];
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				a[i][j] = arr[i][j];
			}
		}
		
		return a;
	}
}
