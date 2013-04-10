/**
 *  @author Mike Della Donna
 */
package ri;

import java.util.ArrayList;
import java.util.List;

public class Logger {

	private List<String> log;
	
	public Logger()
	{
		log = new ArrayList<String>();
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
	

}
