package edu.smith.cs.csc212.calc;

import java.util.Map;

public class BinaryExpr implements Expr {
	private String op;
	Expr left;
	Expr right;
	
	/**
	 * 
	 * @param op, the operation that we want to do (&, |)
	 */
	public BinaryExpr(String op) {
		this(op, null, null);
	}
	/**
	 * creates a tree out of an expression.
	 * 
	 * @param op - opperator (& or |)
	 * @param left - nodes to the left in the logical tree
	 * @param right - nodes to the right in the logical tree
	 */
	public BinaryExpr(String op, Expr left, Expr right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	/**
	 * takes in a Map of strings of t or f and returns the truth value by traversing the tree
	 */
	@Override
	public boolean evaluate(Map<String, Boolean> vars) {
		switch(op) {
			case "&":
				return left.evaluate(vars) && right.evaluate(vars);
			case "|":
				return left.evaluate(vars) || right.evaluate(vars);			
			default:
				throw new UnsupportedOperationException(op);
		}
	}
	@Override
	public String toString() {
		return "("+op+" "+left+" "+right+")";
	}
}