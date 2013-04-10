package ri.rule.action;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
/**
 * 
 * @author Mike Della Donna
 *
 */
public class Action 
{
	private String action;
	private ScriptEngine engine;
	
	public Action(String action, ScriptEngine engine) {
		super();
		this.action = action;
		this.engine = engine;
	}

	
	/**
	 * holds JavaScript code as a string 
	 * and is able to evaluate it using the provided engine context
	 * 
	 * @throws ScriptException - if there is a problem evaluating the script
	 */
	public void evaluateAction() throws ScriptException
	{
		engine.eval(action);
	}
	
	public String toString()
	{
		return action;
	}
}
