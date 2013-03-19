package project1;

import javax.script.*;

public class Project1Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        
        // create a JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        
        String javascript;
        
        javascript = "var workingmemory = new Object();" +
        		"workingmemory.banana = 'yellow';" +
        		"print(workingmemory.banana)";
        
        // evaluate JavaScript code from String
        try {
			engine.eval(javascript);
		} catch (ScriptException e) {
			e.printStackTrace();
		}

	}

}
