/**
 *  @author Mike Della Donna
 */
package land;

public class InvalidContentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6618856552053103269L;

	/**
	 * 
	 * @param msg - the msg to present to the User
	 */
	
	public InvalidContentException(String msg)
	{
		super(msg);
	}
	
}
