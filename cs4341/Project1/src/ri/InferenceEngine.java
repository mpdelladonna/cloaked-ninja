package ri;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import ri.rule.Rule;
import ri.rule.action.Action;
import ri.rule.predicate.UnknownValueException;

/**
 * 
 * @author Mike Della Donna
 *
 */
public class InferenceEngine 
{
	public static void evaluateRules(ScriptEngine engine, List<Rule> rules, Logger log)
	{
		boolean matchingRules = true;
		
		//define a new comparator for the java.util.TreeSet
		Comparator<Rule> compare = new Comparator<Rule>() {
			@Override
			public int compare(Rule o1, Rule o2) {
				return o1.getPredicate().granularity() - o2.getPredicate().granularity();
			}
		};
		
		TreeSet<Rule> triggeredRules;
		
		log.log("Beginning rule evaluation");
		//while rules exist and there are rules that match the current working memory
		while(rules.size() > 0 && matchingRules)
		{
			log.log("Creating a list of triggered rules");
			//make a new set of triggered rules
			triggeredRules = new TreeSet<Rule>(compare);
			
			//put rules that match the current working memory into the triggered set
			for(Rule r : rules)
			{
				log.log("Evaluating rule :" + r);
				try {
					if(r.getPredicate().result())
					{
						log.log("Rule has been triggered");
						triggeredRules.add(r);
					}
				} catch (UnknownValueException e) {
					//This rule does not apply, not enough information is available.
					log.log("This rule requires information not yet available");
				}
			}
			
			
			//check if any rules matched the current wm
			if(triggeredRules.isEmpty())
			{
				log.log("There were no triggered rules, halting");
				//if none of them did then halt the IE
				matchingRules = false;
			}
			else
			{
				log.log("Most specific rule is: "+ triggeredRules.last());
				//the most specific rule is in triggeredRules.last()
				//evaluate the actions of that rule
				for(Action a : triggeredRules.last().getAction())
				{
					log.log("Executing action: "+a);
					try {
						a.evaluateAction();
					} catch (ScriptException e) {
						//this is the built in stop function, this action causes the engine to stop where it is.
						if(e.getCause().getMessage().contains("RuleInterpreterSTOP"))
						{
							log.log("Stop action detected, halting");
							matchingRules = false;
						}
						else
						{
							System.err.println(e.getMessage());
							System.exit(13);
						}
					}
				}
				
				log.log("Removing triggered rule");
				//once the rule has been executed, remove it from the list
				rules.remove(triggeredRules.last());
				log.log("re evaluating all remaining rules");
			}
			
		}
		log.log("Rule evaluation finished");
	}
	
	
}
