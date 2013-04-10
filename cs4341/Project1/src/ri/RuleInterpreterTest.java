package ri;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

import ri.rule.predicate.Predicate;
/**
 * 
 * @author Mike Della Donna
 *
 */
public class RuleInterpreterTest {

	@Test
	public void testCreatePredicate() {
		
    	
        // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");
		RuleInterpreter ri = new RuleInterpreter("","",new LinkedList<String>());
		
		Predicate p = null;
		Predicate q = null;
		Predicate r = null;
		Predicate s = null;
		Predicate t = null;
		Predicate u = null;
		Predicate v = null;
		
		try {
			p = ri.createPredicate("(set(green) AND set(chicken))");
			q = ri.createPredicate("(set(green) AND (set(green) AND set(chicken)))");
			r = ri.createPredicate("(set(green) OR set(chicken))");
			s = ri.createPredicate("(set(green) OR (set(green) AND set(chicken)))");
			t = ri.createPredicate("(NOT set(chicken))");
			u = ri.createPredicate("(NOT (set(green) AND set(chicken)))");
			
		} catch (RuleParseException e) {
			fail();
		}
		
		//at this point in the test I used the debugger to manually inspect each variable
		System.out.println(p);
	}
	
	@Test
	public void testCreateRule()
	{
		RuleInterpreter ri = new RuleInterpreter("","",new LinkedList<String>());
		
		try {
			ri.createRule("IF (set(green) OR (set(green) AND set(chicken))) THEN setColor('purple') setChicken('cluck') ");
		} catch (RuleParseException e) {
			fail();
		}
	}
	
	@Test
	public void testCreateAction()
	{
		RuleInterpreter ri = new RuleInterpreter("","",new LinkedList<String>());
		
		try {
			ri.createRule("IF (set(green) OR (set(green) AND set(chicken))) THEN setColor('purple') setChicken('cluck') ");
		} catch (RuleParseException e) {
			fail();
		}
	}
	
	@Test
	public void testParseRules()
	{
		LinkedList<String> rules = new LinkedList<String>();
		rules.add("IF (set(green) OR (set(green) AND set(chicken))) THEN setColor('purple') setChicken('cluck') ");
		rules.add("IF (set(green) OR (set(green) AND set(chicken))) THEN setColor('purple') setChicken('cluck') ");
		rules.add("IF (set(green) OR (set(green) AND set(chicken))) THEN setColor('purple') setChicken('cluck') ");
		rules.add("IF (set(green) OR (set(green) AND set(chicken))) THEN setColor('purple') setChicken('cluck') ");
		rules.add("IF (set(green) OR (set(green) AND set(chicken))) THEN setColor('purple') setChicken('cluck') ");
		rules.add("IF (set(green) OR (set(green) AND set(chicken))) THEN setColor('purple') setChicken('cluck') ");
		rules.add("IF (set(green) OR (set(green) AND set(chicken))) THEN setColor('purple') setChicken('cluck') ");
		
		RuleInterpreter ri = new RuleInterpreter("","", rules);
		
		try {
			ri.parseRules();
		} catch (RuleParseException e) {
			fail();
		}
		System.out.print(ri);

	}
	
	@Test
	public void testJavaScriptException()
	{
        // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        
        try {
			engine.eval("throw new java.lang.Exception( 'RuleInterpreterSTOP' )");
		} catch (ScriptException e) {
			if(e.getCause().getMessage().contains("RuleInterpreterSTOP"))
			{
				System.out.println("Stopped");
			}
			else
			{
				System.out.println("Something else");
			}
		}
        
	}

}
