package project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import javax.script.*;

import ri.RuleInterpreter;
import ri.RuleParseException;

/**
 * 
 * @author Mike Della Donna
 *
 */
public class Project1Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Project1Demo test = new Project1Demo();
		
		String workingDirectory = test.getClass().getClassLoader().getResource(".").getPath();
		
		//System.out.println(workingDirectory);
		String rulesFile;
		String functionsFile;
		String wmFile;
		if(args.length < 4)
		{
			functionsFile = workingDirectory + "part1/functions.js";
			rulesFile = workingDirectory + "part1/rules.js";
			wmFile = workingDirectory + "part1/workingMemory.js";
		}
		else
		{
			functionsFile = args[1];
			wmFile = args[2];
			rulesFile = args[3];
		}
		
		//a string to hold the inital working memory
		StringBuilder builder = new StringBuilder();
		String wm = null;
		String func = null;
    	//create a list to hold all of the rules
    	List<String> importedScript = new LinkedList<String>();
    	
		//line temporarily holds the last line read in
    	String line = "";
  
    	try  
    	{
    		//create a filereader and a bufferedreader to open our file
    	    FileReader fstream = new FileReader(rulesFile);
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
    	    fstream = new FileReader(wmFile);
    	    read = new BufferedReader(fstream);
    	    
    	    while((line = read.readLine()) != null)
    	    {
    	    	builder.append(line);
    	    }
    	    read.close();
    	    wm = new String(builder);
    	    
    	    //read in the initial working memory
    	    fstream = new FileReader(functionsFile);
    	    read = new BufferedReader(fstream);
    	    
    	    builder = new StringBuilder();
    	    
    	    while((line = read.readLine()) != null)
    	    {
    	    	builder.append(line);
    	    }
    	    read.close();
    	    func = new String(builder);
    	}
    	catch (Exception e)
    	{
    	    System.err.println("Error: " + e.getMessage());
    	    System.exit(0);
    	}
    	
    	RuleInterpreter ri = new RuleInterpreter(func,wm,importedScript);
    	
    	try {
			ri.parseRules();
		} catch (RuleParseException e) {
			e.printStackTrace();
		}
    	
    	ri.printWorkingMemory();
    	
    	ri.evaluateRules();
    	
    	ri.printWorkingMemory();
    	
    	for (String s : ri.getLog().getLog())
    	{
    		System.out.println(s);
    	}


	}
	
	public Project1Demo()
	{
		
	}

}
