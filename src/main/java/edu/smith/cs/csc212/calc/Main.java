package edu.smith.cs.csc212.calc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/**
 * 
 * @author kmbspencer
 * Smith College CSC 212 Final Project Logic Trees (12/12/19)
 * Starter code: JJ Foley, Smith College Visiting Assistant Professor
 *
 */
public class Main {
	
	/**
	 * Takes a logical formula as an input, returns a set of the variables
	 * @param String input, the formula the user wants to analyze
	 * @return set of variables
	 */
	public static HashSet<String> allVariable(String input){
		HashSet<String> allVar = new HashSet<String>();
		for(int i = 0; i<input.length(); i++) {
			if(Character.isLetterOrDigit(input.charAt(i))) {
				allVar.add(input.substring(i, i+1));
			}
		}
		return allVar;
	}
	/**
	 * 
	 * @param vars - set of variables
	 * @param formula - logical formula to be analyze
	 * @return a list with as many copies of the logical formula as there are possible truth conditions 
	 */
	public static List<String> truthConditions(HashSet<String> vars, String formula){
		List<String> conditions = new ArrayList<String>();
		List<String> lvars = new ArrayList<String>(vars);
		
		for(int i =0; i< (Math.pow(2, lvars.size())); i++) {
			conditions.add(formula);
			
		}
		for(int k = 0; k<lvars.size(); k++) {
			conditions = generate(conditions, lvars.get(k) , k+1, lvars.size());
			
		}
		
		return conditions;
	}
	/**takes a list of logical expressions (eg: p|q, p|q, p|q, p|q) and substitutes them for all of the possible truth conditions. (eg: t|t, t|f, f|t, f|f) 
	 * This method works based on the fact that for any given number of variables (v) it takes 2^v logical formulas to generate a truth table. 
	 * the challenge is ensuring that every permeatation is represented.
	 * p  q
	 * t  t 
	 * t  f
	 * f  t
	 * f  f
	 * 
	 * for the first variable in any truth table, the first half of the conditions will be true, the second half false. For the last variable they alternate. 
	 * by looking at larger truth tables (for simplicity, not represented here) I was able to determine that for each middle variable, the truth value should
	 * swap from true to false after 1/(2^position) permitations. the position here is it's position in the list, (note, starting at 1). 
	 * 
	 * @param conditions a list with as many copies of the logical formula as there are possible truth conditions 
	 * @param var - the letter that truth values are being substituted for
	 * @param pos - what position in the list "var" is
	 * @param total - the number of variables that there are in the expression
	 * @return the list of conditions where var has been substituted for the truth condition
	 */
	public static List<String> generate(List<String> conditions, String var, int pos, int total){
		//the total number of truth conditions (same as conditions.length())
		int size = (int) (Math.pow(2, total));
		//indicates how many "blocks" of true and false there are
		int loops = (int) Math.pow(2, pos);
		int count = 0;
		//iterates once for each block of true or false
		for(int i =1; i<=loops; i++) {
			//loops inside each true or false block to address each item in conditions
			for(int j = 0; j<(size/(Math.pow(2, pos))); j++) {
				if((i%2)==1) {
					conditions.set(count, conditions.get(count).replace(var, "t"));
				}else if((i%2)==0) {
					conditions.set(count, conditions.get(count).replace(var, "f"));

				}
				count++;
			}
			
		}
		return conditions;
		
	}
	/**takes a list of logical formulas, and evaluates them
	 * 
	 * @param conditions, a list of logical formulas (eg: t|t, t|f)
	 * @return a list of truth values (eg: true, true)
	 */
	public static List<String> evaluateList(List<String> conditions){
		List<String> results = new ArrayList<>(conditions.size());
		
		for(String n : conditions) {
			results.add(Boolean.toString(ExprParser.parse(n).evaluate()));
			
		}
		return results;
	}
	/**
	 * Ensures that the user only entered a letter or '&' or '!' or '|'
	 * @param the users inputed formula/expression
	 * @return true if this is an O.K. input, false if it is a invalid input.
	 */
	public static boolean checkInput(String input) {
		String x = input;
		//List
		for(int i = 0; i< x.length(); i++) {
			if(!Character.isLetter(x.charAt(i))&& x.charAt(i) != '&' && x.charAt(i) != '|' && x.charAt(i) !='!') {
				return false;
			}
		}
		return true;
	}
	/**
	 * Main method, interfaces with the user and allows them to access the logic tree. Uses Text Input.
	 * 
	 */
  public static void main(String[] args) {
	  TextInput input = new TextInput();
	  String confirm = "Would you like to go again?";
	  System.out.println("Welcome to the Logic Tree!");
	  System.out.println("I can either evaluate your logical formula, or I can print you a truth table.");
	  while(true) {
		  String q = "Type 'f' to evaluate a logical formula, or 'p' to print a truth table.";
		  List<String> response = new ArrayList<String>();
		  response = input.getUserWords(q);
		  if(response.get(0).equals("f")) {
			  String fq = "Enter the logical formula!\nUse t and f for true and false, & for and, | for or, and ! for not.";
			  String fformula = input.getUserWordsString(fq).toLowerCase();
			  if(!checkInput(fformula)) {
				  System.out.println("Your input was invalid. Please try again");
			  }
			  System.out.println("Your expression " +fformula +" was "+ ExprParser.parse(fformula).evaluate());
			  if(input.confirm(confirm)) {
				  continue;
			  } else {
				  break;
			  }
		  } else if(response.get(0).equals("p")) {
			  String pq = "Enter the logical formula you would like a truth table for. \nUse any letter for your variables, & for and, | for or, and ! for not.";
			  String pformula = input.getUserWordsString(pq).toLowerCase();
			  if(!checkInput(pformula)) {
				  System.out.println("Your input was invalid. Please try again");
			  }
			  HashSet<String> allVar = new HashSet<String>();
			  allVar = allVariable(pformula);
			  List<String> conditions = new ArrayList<String>();
			  conditions = truthConditions(allVar, pformula);
			  
			  List<String> results = evaluateList(conditions);
			  System.out.println("The Truth Table for: "+ pformula);
			  for(int i =0; i< results.size(); i++) {
				  System.out.println(conditions.get(i)+" "+results.get(i));
			  }

			  if(input.confirm(confirm)) {
				  System.out.println("");
				  continue;
			  } else {
				  break;
			  }
		  } else {
			  System.out.println("I did not understand that, please try again.");
		  }
		  

	  }
	  System.out.println("Thank you for using the Logic Tree!");
	 
	  
  }
}
