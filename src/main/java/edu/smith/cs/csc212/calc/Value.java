package edu.smith.cs.csc212.calc;

class Value implements Expr {
	private int num;
	public Value(int n) {
		this.num = n;
	}
	public int evaluate() {
		return num;
	}
	public String toString() {
		return ""+this.num;
	}
}