package ri.rule.predicate;

/**
 * extends boolean operator
 * this class returns its result as a logical AND of its two predicates
 * 
 * @author Mike Della Donna
 *
 */
public class And extends BooleanOperator {

	public And(Predicate LHS, Predicate RHS) {
		super(LHS, RHS);
	}

	@Override
	public boolean result() throws UnknownValueException {
		return LHS.result() && RHS.result();
	}
	
	public String toString()
	{
		return "( "+LHS+" AND "+RHS+" )";
	}

}
