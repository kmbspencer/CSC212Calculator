package edu.smith.cs.csc212.calc;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
	public RuntimeException error(String msg) {
		return new RuntimeException(msg+": "+this.toString());
	}
	private char[] data;
	int position;
	
	/**
	 * takes input and adds to list
	 * @param input
	 */
	public Tokenizer(String input) {
		this.data = input.toCharArray();
		this.position = 0;
	}
	/**
	 * 
	 * @return next element
	 */
	private int peek() {
		if (position < data.length) {
			return data[position];
		}
		return -1;
	}
	/**
	 * 
	 * @return how many mre item in list
	 */
	public int remaining() {
		return data.length - position;
	}
	
	// rest="abcd" 
	// (then .getc()=> "a") 
	// then rest="bcd"
	public String rest() {
		if (position >= data.length) {
			return "";
		}
		return new String(data, position, this.remaining());
	}
	
	
	public String toString() {
		return "Tokenizer(@"+position+", ..."+rest()+")";
	}
	
	// "abcd".consume(2) => "ab", rest="cd"
	public String consume(int amt) {
		String out = new String(data, position, amt);
		position += amt;
		return out;
	}
	/**
	 * skips over white space
	 */
	public void skipWhitespace() {
		while(true) {
			int next = peek();
			if (next == -1) {
				return;
			}
			char ch = (char) next;
			if (Character.isWhitespace(ch)) {
				position++;
				continue;
			}
			break;
		}
	}
	/**
	 * finds the next character that isn't an opperator or t or f
	 * @return 
	 */
	public String nextToken() {
		skipWhitespace();
		int next = peek();
		if (next == -1) {
			return null;
		}
		char ch = (char) next;
		if (ch == 't' || ch == 'f' || ch == '&' || ch == '|' || ch == '!' || ch == '(' || ch == ')') {
			return consume(1);
		}
		
		// Assume it's part of a number or variable:
		StringBuilder id = new StringBuilder();
		while (Character.isLetterOrDigit(ch)) {
			id.append(ch);
			position++;
			next = peek();
			if (next == -1 || ch == 't' || ch == 'f' || ch == '&' || ch == '|' || ch == '!' || ch == '(' || ch == ')') {
				break;
			}
			ch = (char) next;
		}
		if (id.length() > 0) {
			return id.toString();
		}
		throw error("Unknown token.");
	}
	
	public static List<String> tokenize(String input) {
		List<String> output = new ArrayList<>();
		Tokenizer tok = new Tokenizer(input);
		while(true) {
			String token = tok.nextToken();
			if (token == null) break;
			output.add(token);
		}
		return output;
	}
	
}