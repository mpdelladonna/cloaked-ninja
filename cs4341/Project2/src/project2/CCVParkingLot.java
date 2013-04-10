package project2;

import java.util.Random;

import static java.lang.Math.abs;

public class CCVParkingLot {

	int[] lot;
	int length;
	Random gen;
	
	public CCVParkingLot(int n)
	{
		lot = new int[n];
		length = n;
		gen = new Random();
		initializeRandomLot();
	}
	
	public void setLot(int[] lot)
	{
		this.lot = lot;
	}
	
	public void solve(int numTries) throws NoPathFoundException
	{
		int tries = 0;
		int ccv, ccvMin, conflictMin;
		while(conflicts() > 0)
		{
			//printLot();
			//System.out.println(gen);
			if(tries > numTries)
				throw new NoPathFoundException();
			
			//pick random CCV that is conflicted
			ccv = gen.nextInt(length);
			while(conflicted(ccv, lot[ccv]) < 1)
			{
				ccv = gen.nextInt(length);
			}
			
			//get its best position
			ccvMin = ccv;
			conflictMin = conflicted(ccv, lot[ccv]);
			
			for(int i = 0; i < length; i++)
			{
				if(conflicted(ccv,i) < conflictMin)
				{
					ccvMin = i;
				}
			}
			
			//put it there
			lot[ccv] = ccvMin;
			
			tries++;
		}
	}
	
	public int conflicts()
	{
		int conflicts = 0;
		
		for(int i = 0; i < length; i++)
		{
			conflicts += conflicted(i,lot[i]);
		}
		
		return conflicts;
	}
	
	public int conflicted(int row, int column)
	{
		int conflicts = 0;
		
		for(int i = 0; i < length; i++)
		{
			if( i != row)
			{
				if((abs(i - row) == abs(lot[i] - column)) || (lot[i] - column) == 0)
				{
					conflicts++;
				}
			}
		}
		
		return conflicts;
	}
	
	public void setSeed(long seed)
	{
		gen = new Random(seed);
	}
	
	public void initializeRandomLot()
	{
		for(int i = 0; i < length; i++)
		{
			lot[i] = gen.nextInt(length);
		}
	}
	
	public void printLot()
	{
		System.out.println(this);
	}
	
	public String toString()
	{
		String slot = "";
		
		for(int i = 0; i < length; i++)
		{
			for(int j = 0; j < length; j++)
			{
				if(j == lot[i])
				{
					slot += "[x]";
				}
				else
				{
					slot += "[ ]";
				}
			}
			slot += "\n";
		}
		
		return slot;
	}
}
