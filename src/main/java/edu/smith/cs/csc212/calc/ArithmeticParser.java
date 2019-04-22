package edu.smith.cs.csc212.calc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArithmeticParser {	
	public static void main(String[] args) {
		String input = "5 + 3 * 7 - 20";
		System.out.println(Tokenizer.tokenize(input));
		System.out.println(ExprParser.parse(input));
		System.out.println(ExprParser.parse(input).evaluate());
		
		input = "-(5 + 3) * 7 - 20";
		System.out.println(Tokenizer.tokenize(input));
		System.out.println(ExprParser.parse(input));
		System.out.println(ExprParser.parse(input).evaluate());

		//System.out.println(input+" >>> "+postfix(input));
	}

}
