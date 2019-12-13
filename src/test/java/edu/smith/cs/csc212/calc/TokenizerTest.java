package edu.smith.cs.csc212.calc;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

public class TokenizerTest {

	@Test
	public void testTokenizer() {
		String input = "(t|f&t|f)";
		Assert.assertEquals(Arrays.asList("(", "t", "|", "f", "&", "t", "|", "f", ")"),
				Tokenizer.tokenize(input));
	}
	
	@Test
	public void testTokenizerWS() {
		String input = " ( t | f &  t | f )  ";
		Assert.assertEquals(Arrays.asList("(", "t", "|", "f", "&", "t", "|", "f", ")"),
				Tokenizer.tokenize(input));
	}
	
	@Test
	public void testTokenizerNothing() {
		String input = "  ";
		Assert.assertEquals(Collections.emptyList(), Tokenizer.tokenize(input));
	}
	
	/**@Test
	public void testTokenizerJustNumber() {
		String input = "1234";
		Assert.assertEquals(Arrays.asList("1234"), Tokenizer.tokenize(input));
	}
	**/
	
	@Test(expected=RuntimeException.class)
	public void testTokenizerError() {
		String input = " 1 *";
		Tokenizer.tokenize(input);
	}
	
	@Test
	public void testVars() {
		String input = "(a|b|c&d&e)";
		Assert.assertEquals(Arrays.asList("(", "a", "|", "b", "|", "c", "&", "d", "&", "e", ")"),
				Tokenizer.tokenize(input));
	}
	
	@Test
	public void testVarsWS() {
		String input = "( a |b |c & d & e)";
		Assert.assertEquals(Arrays.asList("(", "a", "|", "b", "|", "c", "&", "d", "&", "e", ")"),
				Tokenizer.tokenize(input));
	}
	
}
