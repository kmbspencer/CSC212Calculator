package edu.smith.cs.csc212.calc;

import java.util.Map;

public class BinaryExpr implements Expr {
	private String op;
	Expr left;
	Expr right;
	
	public BinaryExpr(String op) {
		this(op, null, null);
	}
	public BinaryExpr(String op, Expr left, Expr right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	@Override
	public int evaluate(Map<String, Integer> vars) {
		switch(op) {
			case "+":
				return left.evaluate(vars) + right.evaluate(vars);
			case "-":
				return left.evaluate(vars) - right.evaluate(vars);
			case "*":
				return left.evaluate(vars) * right.evaluate(vars);
			case "/":
				return left.evaluate(vars) / right.evaluate(vars);
			default:
				throw new UnsupportedOperationException(op);
		}
	}
	@Override
	public String toString() {
		return "("+op+" "+left+" "+right+")";
	}
}