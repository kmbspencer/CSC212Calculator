package edu.smith.cs.csc212.calc;

import java.util.List;

class ExprParser {
	List<String> tokens;
	int position;
	public ExprParser(List<String> tokens) {
		this.tokens = tokens;
		this.position = 0;
	}
	
	public void expectExact(String what) {
		String value = tokens.get(position++);
		if (!value.equals(what)) {
			throw new RuntimeException("Expected: "+what);
		}
	}
	
	public Expr readNumber() {
		String value = tokens.get(position++);
		return new Value(Integer.parseInt(value));
	}
	
	public String peek() {
		if (position < tokens.size()) {
			return tokens.get(position);
		}
		return null;
	}
	
	public Expr readMulDivExpr() {
		Expr left = readExpr();
		
		while (position < tokens.size()) {
			String tok = peek();

			if (tok.equals("*") || tok.equals("/")) {
				position++;
				Expr right = readExpr();
				left = new BinaryExpr(tok, left, right);
			} else {
				break;
			}
		}
		return left;
	}
	
	public Expr readAddSubExpr() {
		Expr left = readMulDivExpr();
		
		while(position < tokens.size()) {
			String tok = peek();

			if (tok.equals("+") || tok.equals("-")) {
				position++;
				Expr right = readMulDivExpr();
				left = new BinaryExpr(tok, left, right);
			} else {
				break;
			}
		}
		return left;
	}
	
	public Expr readExpr() {
		String tok = tokens.get(position);
		if (tok.equals("(")) {
			expectExact("(");
			Expr e = readAddSubExpr();
			expectExact(")");
			return e;
		} else if (tok.equals("-")) {
			expectExact("-");
			return new BinaryExpr("-", new Value(0), readExpr());
		} else {
			return readNumber();
		}
	}
	
	public static Expr parse(String input) {
		ExprParser p = new ExprParser(Tokenizer.tokenize(input));
		return p.readAddSubExpr();
	}
}