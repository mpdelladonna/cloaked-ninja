package ri.rule.predicate;
/**
 * Represents a predicate that can be evaluated
 * 
 * @author Mike Della Donna
 *
 */
public interface Predicate 
{
	/**
	 * returns a boolean indicating the value of this predicate
	 * 
	 * @return true or false
	 */
	public boolean result() throws UnknownValueException;
	
	/**
	 * represents the granularity of this predicate
	 * higher integers represent more descriptive predicates
	 * 
	 * @return an integer 
	 */
	public int granularity();
}
