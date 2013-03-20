package ri.rule.predicate;

/**
 * represents predicates that are composed of two other predicates
 * 
 * @author Mike Della Donna
 *
 */
public abstract class BooleanOperator implements Predicate {

	protected Predicate LHS; //left hand side predicate
	protected Predicate RHS; //right hand side predicate
	
	public BooleanOperator(Predicate LHS, Predicate RHS)
	{
		this.LHS = LHS;
		this.RHS = RHS;
	}
	
	@Override
	public int granularity() {
		
		return LHS.granularity() + RHS.granularity();
	}

}
