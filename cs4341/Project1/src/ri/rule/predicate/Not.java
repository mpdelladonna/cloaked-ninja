package ri.rule.predicate;

/**
 * This class represnts the application of the unary NOT operator to it's argument
 * 
 * @author Mike Della Donna	
 *
 */
public class Not implements Predicate {

	private Predicate pred;
	
	public Not(Predicate pred)
	{
		this.pred = pred;
	}
	
	@Override
	public boolean result() throws UnknownValueException {
		return !pred.result();
	}

	@Override
	public int granularity() {
		return pred.granularity();
	}
	
	public String toString()
	{
		return "(NOT "+pred+")";
	}

}
