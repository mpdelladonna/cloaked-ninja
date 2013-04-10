package ri.rule.predicate;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * terminal implementation of the Predicate interface
 * this class evaluates predicates formatted as javascript functions
 * @author Mike Della Donna
 *
 */
public class Javascript implements Predicate {

	//The javascript to be executed
	private String javascript;
	//The javascript interpreter
	private ScriptEngine engine;
	
	/**
	 * 
	 * @param javascript valid javascript payload
	 * @param engine - the script engine to be used to evaluate the payload
	 */
	public Javascript(String javascript, ScriptEngine engine)
	{
		this.engine = engine;
		this.javascript = javascript;
	}
	
	@Override
	public boolean result() throws UnknownValueException {
		//first try to evaluate the given expression normally
		try {
			return (Boolean) engine.eval(javascript);
		} catch (ScriptException e) {
			//in this case, the given javascript contained an error
			//and could not be evaluated
			System.err.println();
        	System.err.println("There was a problem with the evaluated predicate, the javascript was malformed: "+javascript);
        	System.err.println(e.getMessage());
			System.exit(13);
			return false;
			
		} catch (ClassCastException e) {
			//in this case, the javascript did not evaluate to
			//a boolean
			System.err.println();
        	System.err.println("There was a problem with the evaluated predicate, a boolean was not returned: "+javascript);
        	System.err.println(e.getMessage());
        	System.exit(13);
        	return false;
		} catch (NullPointerException e)
		{
			throw new UnknownValueException("That value is unknown at this time");
		}
	}

	@Override
	public int granularity() {
		//a single function always as a granularity of 1
		return 1;
	}
	
	public String toString()
	{
		return javascript;
	}

}
