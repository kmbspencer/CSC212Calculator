package edu.smith.cs.csc212.calc;

import java.util.Map;

public class Variable implements Expr {
	/**
	 * allows tree to store a letter variable instead of a truth value. Not used in Main.
	 */
	private String name;

	public Variable(String name) {
		this.name = name;
	}

	@Override
	public boolean evaluate(Map<String, Boolean> vars) {
		boolean value = vars.get(name);
		return value;
	}

	@SuppressWarnings("serial")
	public static class BadNameError extends RuntimeException {
		public BadNameError(String what) {
			super(what);
		}
	}
}
