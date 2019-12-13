package edu.smith.cs.csc212.calc;

import java.util.List;

public class ExprParser {
	/**
	 * The list of tokens so far.
	 */
	List<String> tokens;
	/**
	 * The position in this token stream.
	 */
	int position;

	/**
	 * Construct a parse from a list of tokens.
	 * 
	 * @param tokens the list of important strings.
	 */
	public ExprParser(List<String> tokens) {
		this.tokens = tokens;
		this.position = 0;
	}

	/**
	 * Advance past a given token; or crash if it is not what we think.
	 * 
	 * @param what the token to skip over.
	 */
	public void expectExact(String what) {
		String value = tokens.get(position++);
		if (!value.equals(what)) {
			throw new RuntimeException("Expected: " + what);
		}
	}
	/**
	 * takes a value and returns if it is a boolean
	 * @param value - single charecter in a string to be analyzed
	 * @return boolean true if is a t or f
	 */
	public boolean torf(String value) {
		if(value.equals("t")|| value.equals("f")) {
			return true;
		}
		return false;
	}
	/**
	 * It's either a number or a variable. If it starts with a number, try it as a
	 * number.
	 * 
	 * @return Value or Variable.
	 * 
	 */
	public Value readNumber() {
		String value = tokens.get(position++);
		return new Value(value);
	}

	/**
	 * Which token is next?
	 * 
	 * @return get the current token or null.
	 */
	public String peek() {
		if (position < tokens.size()) {
			return tokens.get(position);
		}
		return null;
	}

	/**
	 * And should be considered highest precedence. Except
	 * for parentheses. Every time we want to "recurse" here, we call the one that
	 * knows about parentheses: readExpr.
	 * 
	 * @return a tree of all the and expressions we can find.
	 */
	public Expr readAnd() {
		Expr left = readExpr();

		while (position < tokens.size()) {
			String tok = peek();

			if (tok.equals("&")) {
				position++;
				Expr right = readExpr();
				left = new BinaryExpr(tok, left, right);
			} else {
				break;
			}
		}
		return left;
	}

	/**
	 * Or should be considered lowest precedence. Every time
	 * we want to "recurse" here, we actually call "readAnd" to give
	 * and higher precedence.
	 * 
	 * @return a tree of all the and expressions we can find.
	 */
	public Expr readOr() {
	 
	
		Expr left = readAnd();

		while (position < tokens.size()) {
			String tok = peek();

			if (tok.equals("|")) {
				position++;
				Expr right = readAnd();
				left = new BinaryExpr(tok, left, right);
			} else {
				break;
			}
		}
		return left;
	}
	

	/**
	 * This rule reads parentheses, or negatives in front, or a number/value.
	 * 
	 * The BNF for this looks like:
	 * <pre>
	 * expr := '(' + orExpr + ')' 
	 *       | '!' expr 
	 *       | number 
	 *       | variable
	 * orExpr := andExpr '|' andExpr
	 *             | andExpr
	 * andExpr := expr '&' expr
	 *             | expr
	 * </pre>
	 * 
	 * In order for precedence to work inside a parentheses; we basically start at
	 * the lowest level after seeing one.
	 * 
	 * @return the expression subtree starting from here.
	 */
	public Expr readExpr() {
		String tok = tokens.get(position);
		if (tok.equals("(")) {
			expectExact("(");
			Expr e = readOr();
			expectExact(")");
			return e;
		} else if (tok.equals("!")) {
			expectExact("!");
			return new NotExpr(readExpr());
		} else {
			return readNumber();
		}
	}

	public static Expr parse(String input) {
		ExprParser p = new ExprParser(Tokenizer.tokenize(input));
		return p.readOr();
	}
}