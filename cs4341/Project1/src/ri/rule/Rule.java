package ri.rule;

import ri.rule.predicate.Predicate;

public class Rule 
{
	private Predicate p;
	
	public Rule(Predicate p)
	{
		this.p = p;
	}
	
	public Predicate getPredicate()
	{
		return p;
	}
}
