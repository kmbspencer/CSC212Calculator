package edu.smith.cs.csc212.calc;

import java.util.Map;

public class Variable implements Expr {
	private String name;

	public Variable(String name) {
		this.name = name;
	}

	@Override
	public int evaluate(Map<String, Integer> vars) {
		Integer value = vars.get(name);
		if (value == null) {
			throw new BadNameError(name);
		}
		return value;
	}

	@SuppressWarnings("serial")
	public static class BadNameError extends RuntimeException {
		public BadNameError(String what) {
			super(what);
		}
	}
}
