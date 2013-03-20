package ri;

import javax.script.ScriptEngine;

import ri.rule.predicate.And;
import ri.rule.predicate.Javascript;
import ri.rule.predicate.Not;
import ri.rule.predicate.Or;
import ri.rule.predicate.Predicate;

public class RuleInterpreter 
{
	private ScriptEngine engine;
	
	public RuleInterpreter(ScriptEngine engine) {
		super();
		this.engine = engine;
	}
	
	public Predicate createPredicate(String pred) throws RuleParseException
	{
		//in this case, this string is an operand
		//return a javascript predicate
		if(pred.charAt(0) != '(')
		{
			return new Javascript(pred, engine);
		}
		
		//use helper function to find the index of the first character of the operator
		int index = findOperatorIndex(pred);
		
		if(index < 0)
			throw new RuleParseException("Could not find an operator in: "+pred);
		
		//at this point, the index of the operator is stored in index
		switch(pred.charAt(index))
		{
		case 'A'://in this case the operator is an AND, recursively generate the next predicate
			return new And(
					this.createPredicate(pred.substring(1, index).trim()), //LHS
					this.createPredicate(pred.substring(index+3, pred.length()-1).trim())); //RHS
		case 'O'://in this case the operator is an OR, recursively generate the next predicate
			return new Or(
					this.createPredicate(pred.substring(1, index).trim()), //LHS
					this.createPredicate(pred.substring(index+2, pred.length()-1).trim())); //RHS
		case 'N'://in this case the operator is a NOT, recursively generate the next predicate
			return new Not(this.createPredicate(pred.substring(index+3, pred.length()-1).trim()));
		}
		
		throw new RuleParseException("There was an error parsing this predicate: " + pred);
	}
	
	private int findOperatorIndex(String predicate)
	{
		//this block of code looks for the index of the operator
		//of this particular string
		char[] p = predicate.toCharArray();//the string as a list of characters
		int paren = 0; //the current parenthesis depth
		int index = -1; //the index of the operator
		
		/*
		 * in each iteration either 
		 * * increase the parenthesis depth on '('
		 * * decrease the parenthesis depth on ')'
		 * * check for an operator
		 * * * the operators are AND OR NOT
		 * * * we only want the current one, so parenthesis depth of 1
		 * * * if we find an operator, record it's location as an index
		 * * at the end of each iteration, check to see if the operator was found
		 * * * if it was, we can exit the loop
		 */
		
		for(int i = 0; i < p.length; i++)
		{
			switch(p[i])
			{
			case '(': paren++; break;
			case ')': paren--; break;
			case 'A': 
				if(paren == 1 && p[i+1] == 'N' && p[i+2] == 'D'){
					index = i;}
				break;
			case 'O': 
				if(paren == 1 && p[i+1] == 'R'){
					index = i;}
				break;
			case 'N': 
				if(paren == 1 && p[i+1] == 'O' && p[i+2] == 'T'){
					index = i;}
				break;
			}
			
			if(index >= 0)
				break;
		}
		
		return index;
	}
}
