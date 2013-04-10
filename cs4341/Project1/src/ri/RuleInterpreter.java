package ri;

import java.util.LinkedList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import ri.rule.Rule;
import ri.rule.action.Action;
import ri.rule.predicate.And;
import ri.rule.predicate.Javascript;
import ri.rule.predicate.Not;
import ri.rule.predicate.Or;
import ri.rule.predicate.Predicate;
/**
 * 
 * @author Mike Della Donna
 *
 */
public class RuleInterpreter 
{
	private ScriptEngine engine;//engine to execute javascript
	private List<Rule> rules;//the rules that will govern interpreter behavior
	
	//the script that initializes the working memory object
	private final String workingMemory = "var wm;"+
										"wm = new Object();"+
										"wm.print = function()"+
										"{"+
										"	print('\\n');"+//this is important, remember to double escape
										"	print(JSON.stringify(this));"+
										"};" +
										"" +
										"function stop()" +
										"{" +
										"	throw new java.lang.Exception( 'RuleInterpreterSTOP' );" +
										"}";
	
	private List<String> userRules;
	private String functions;
	private String initialWM;
	
	private Logger log;
	
	/**
	 * Creates a new RuleInterpreter
	 * 
	 * @param functionsAndInitialWorkingMemory - a string containing function definitions and optionally the initial working memory
	 * @param rules - a List<String> containing the rules for this engine
	 */
	public RuleInterpreter(String functions, String initialWorkingMemory, List<String> rules) 
	{
		super();
        // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        engine = factory.getEngineByName("JavaScript");
        //try to initialize the working memory object
        try {
			engine.eval(workingMemory);
		} catch (ScriptException e) {
			System.err.println("Problem initializing working memory: "+e.getMessage());
		}
        
        this.userRules = rules;
        this.functions = functions;
        this.initialWM = initialWorkingMemory;
        this.rules = new LinkedList<Rule>();
        
        this.log = new Logger();
	}
	
	/**
	 * converts the given strings into an executable set of Rules
	 * @throws RuleParseException - if there is a problem evaluating the function definitions or initial working memory
	 */
	public void parseRules() throws RuleParseException
	{
		try {
			engine.eval(functions);
			engine.eval(initialWM);
		} catch (ScriptException e) {
			throw new RuleParseException("There was an error evaluating rule functions: " + e.getMessage());
		}
		
		for(String s : userRules)
		{
			rules.add(createRule(s));
		}
	}
	
	/**
	 * Adds the given object to the javascript context
	 * 
	 * i.e. allows the javascript engine to access the given object by using the given name
	 * 
	 * @param name - The name for the new object
	 * @param var - The object to add
	 */
	public void addExternalObject(String name, Object var)
	{
		engine.put(name, var);
	}
	
	/**
	 * Creates a rule based on the given String
	 * @param rule - a String representing the rule
	 * @return a Rule based on the given string
	 * @throws RuleParseException - if a predicate is invalid, a RuleParseException can be thrown
	 */
	public Rule createRule(String rule) throws RuleParseException
	{
		Rule r = null;
		
		int IF = rule.indexOf("IF"); //get the index of the beginning of the rule
		int THEN = rule.indexOf("THEN"); //get the index of the middle of the rule
		
		String predicate = rule.substring(IF+2,THEN);//this substring is the predicate
		String action = rule.substring(THEN+4);//this substring is the action
		
		//System.out.println("if -- "+predicate+" --then--"+action );
		r = new Rule(createPredicate(predicate),createActions(action));
		
		return r;
	}
	
	/**
	 * Creates a predicate from a String
	 * 
	 * @param pred - a string representation of a predicate
	 * 
	 * @return A Predicate object that represents the given string
	 * 
	 * @throws RuleParseException - If a predicate contains invalid syntax
	 */
	public Predicate createPredicate(String pred) throws RuleParseException
	{
		pred = pred.trim();
		//in this case, this string is an operand
		//return a javascript predicate
		if(pred.charAt(0) != '(')
		{
			return new Javascript(pred+";", engine);
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
					this.createPredicate(pred.substring(1, index)), //LHS
					this.createPredicate(pred.substring(index+3, pred.length()-1))); //RHS
		case 'O'://in this case the operator is an OR, recursively generate the next predicate
			return new Or(
					this.createPredicate(pred.substring(1, index)), //LHS
					this.createPredicate(pred.substring(index+2, pred.length()-1))); //RHS
		case 'N'://in this case the operator is a NOT, recursively generate the next predicate
			return new Not(this.createPredicate(pred.substring(index+3, pred.length()-1)));
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
	
	/**
	 * creates a list of action objects out of a string representing them
	 * @param action - a string representing one or more actions
	 * @return a list of Actions
	 */
	public List<Action> createActions(String action)
	{
		LinkedList<Action> actions = new LinkedList<Action>();
		int next;
		
		while((next = action.indexOf(')')) != -1)
		{
			actions.add(new Action(action.substring(0, next+1).trim()+";",this.engine));
			action = action.substring(next+1);
		}
		
		return actions;
	}
	
	/**
	 * returns a string representation of the Working Memory
	 * 
	 * @return String
	 */
	public String getWorkingMemory()
	{
		try {
			return (String) engine.eval("JSON.stringify(wm)");
		} catch (ScriptException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Prints a string representation of the working memory
	 */
	public void printWorkingMemory()
	{
		try {
			engine.eval("wm.print()");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Causes the inference engine to evaluate all of the rules stored in the rule interpreter
	 * The inference engine will stop when there are no more rules that apply OR when the stop action is executed
	 */
	public void evaluateRules()
	{
		InferenceEngine.evaluateRules(engine, rules, log);
	}
	
	public Logger getLog()
	{
		return log;
	}
}
