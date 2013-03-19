/**
 *  @author Mike Della Donna
 */
package search;

import java.util.ArrayList;
import java.util.List;

public class Logger {

	private List<String> log;
	private int visited;
	
	public Logger()
	{
		log = new ArrayList<String>();
		visited = 0;
	}
	
	/**
	 * Adds a message to the log
	 * @param msg - the msg to add to the log
	 */
	public void log(String msg)
	{
		log.add(msg);
	}
	
	/**
	 * returns the log as a list of strings
	 * 
	 * @return the log
	 */
	public List<String> getLog()
	{
		return log;
	}
	
	/**
	 * increases the number of cells visited
	 */
	public void visitCell()
	{
		visited++;
	}
	
	/**
	 * @return the number of visted cells as an int
	 */
	public int getVistedCells()
	{
		return visited;
	}
}
