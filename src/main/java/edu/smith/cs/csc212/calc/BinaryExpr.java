package edu.smith.cs.csc212.calc;

class BinaryExpr implements Expr {
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
	public int evaluate() {
		switch(op) {
			case "+":
				return left.evaluate() + right.evaluate();
			case "-":
				return left.evaluate() - right.evaluate();
			case "*":
				return left.evaluate() * right.evaluate();
			case "/":
				return left.evaluate() / right.evaluate();
			default:
				throw new UnsupportedOperationException(op);
		}
	}
	public String toString() {
		return "("+op+" "+left+" "+right+")";
	}
}