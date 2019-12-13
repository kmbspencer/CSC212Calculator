package edu.smith.cs.csc212.calc;

import java.util.Map;

public class NotExpr implements Expr {
	Expr child;
	
	public NotExpr(Expr child) {
		this.child = child;
	}

	@Override
	public boolean evaluate(Map<String, Boolean> variables) {
		return !child.evaluate(variables);
	}

}
