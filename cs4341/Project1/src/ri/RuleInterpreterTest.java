package ri;

import static org.junit.Assert.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Test;

import ri.rule.predicate.Predicate;

public class RuleInterpreterTest {

	@Test
	public void testCreatePredicate() {
		
    	
        // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");
		RuleInterpreter ri = new RuleInterpreter(engine);
		
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

}
