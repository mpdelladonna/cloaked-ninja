package project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import javax.script.*;

public class Project1Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Project1Test test = new Project1Test();
		
		String workingDirectory = test.getClass().getClassLoader().getResource(".").getPath();
		
		System.out.println(workingDirectory);
		
		//TODO move these into command line args
		//String path = "..\\script.js";
		String scriptpath = "script.js";
		
		String predicatesFile = "predicates.js";
		String actionsFile = "acitons.js";
		String rulesFile = "rules.js";
		String initialWorkingMemoryFile = "workingMemory.js";
		
		String path = "D:\\Workspaces\\Repositories\\cloaked-ninja\\cs4341\\Project1\\src\\";
		
		String wm = null;
		StringBuilder builder = new StringBuilder();
		//line temporarily holds the last line read in
    	String line = "";
    	
    	//create a list to hold all of the rules
    	List<String> importedScript = new LinkedList<String>();
    	try  
    	{
    		//create a filereader and a bufferedreader to open our file
    	    FileReader fstream = new FileReader(workingDirectory + scriptpath);
    	    BufferedReader read = new BufferedReader(fstream);
    	    //initialize line to the first line of the file
    	    line = read.readLine();
    	    
    	    //add all the lines to the list of statements
    	    while(line != null)
    	    {
    	    	importedScript.add(line);
    	    	line = read.readLine();
    	    }
    	    //close the open file
    	    read.close();
    	    
    	    //read in the initial working memory
    	    fstream = new FileReader(workingDirectory + initialWorkingMemoryFile);
    	    read = new BufferedReader(fstream);
    	    
    	    while((line = read.readLine()) != null)
    	    {
    	    	builder.append(line);
    	    }
    	    
    	    wm = new String(builder);
    	}
    	catch (Exception e)
    	{
    	    System.err.println("Error: " + e.getMessage());
    	    System.exit(0);
    	}
    	
        // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        
        try{
	        // evaluate JavaScript code from String
	        engine.eval(wm);
	        
	        engine.eval("wm.print();");
        	
	        //evaluate all the lines
	        for(String js : importedScript)
	        {
	        	engine.eval(js);
	        }
        }catch(ScriptException e)
        {
        	System.err.println(e.getMessage());
        }

	}
	
	public Project1Test()
	{
		
	}

}
