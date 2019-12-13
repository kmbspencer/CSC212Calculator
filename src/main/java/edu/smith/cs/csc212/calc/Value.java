package edu.smith.cs.csc212.calc;

import java.util.Map;

/**
 * This class represents a "literal" integer value in our calculator.
 * @author jfoley
 *
 */
public class Value implements Expr{
	/**
	 * What number is it?
	 */
	private boolean num;
	/**
	 * Must give a number to construct this.
	 * @param n
	 */
	public Value(String n) {
		if(n.contentEquals("t")){
			this.num = true;
		} else if(n.contentEquals("f")){
			this.num = false;
		} else {
			throw new RuntimeException(n+" can't be a Value!");
		}
				
	}
	
	public boolean evaluate(Map<String, Boolean> vars) {
		return num;
	}
	@Override
	public String toString() {
		return ""+this.num;
	}
}