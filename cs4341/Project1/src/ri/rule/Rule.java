package ri.rule;

import java.util.List;

import ri.rule.action.Action;
import ri.rule.predicate.Predicate;
/**
 * 
 * @author Mike Della Donna
 *
 */
public class Rule 
{
	private Predicate p;
	private List<Action> a;
	
	public Rule(Predicate p, List<Action> a)
	{
		this.p = p;
		this.a = a;
	}
	
	/**
	 * returns the predicate held by this rule
	 * @return A predicate
	 */
	public Predicate getPredicate()
	{
		return p;
	}
	
	/**
	 * returns the list of actions held by this rule
	 * @return A List<Action>
	 */
	public List<Action> getAction()
	{
		return a;
	}
	
	public String toString()
	{
		String actions = "";
		for(Action ac : a)
		{
			actions += ac;
		}
		return "IF " + p + " THEN "+actions; 
	}
}
