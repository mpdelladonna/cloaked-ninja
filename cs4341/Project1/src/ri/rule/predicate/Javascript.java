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
	
	public Javascript(String javascript, ScriptEngine engine)
	{
		this.engine = engine;
		this.javascript = javascript;
	}
	
	@Override
	public boolean result() {
		//first try to evaluate the given expression normally
		try {
			return (Boolean) engine.eval(javascript);
		} catch (ScriptException e) {
			//in this case, the given javascript contained an error
			//and could not be evaluated
			System.err.println();
        	System.err.println("There was a problem with the evaluated predicate, the javascript was malformed");
        	System.err.println(e.getMessage());
			System.exit(13);
			return false;
			
		} catch (ClassCastException e) {
			//in this case, the javascript did not evaluate to
			//a boolean
			System.err.println();
        	System.err.println("There was a problem with the evaluated predicate, a boolean was not returned");
        	System.err.println(e.getMessage());
        	System.exit(13);
        	return false;
		}
	}

	@Override
	public int granularity() {
		//a single function always as a granularity of 1
		return 1;
	}

}
