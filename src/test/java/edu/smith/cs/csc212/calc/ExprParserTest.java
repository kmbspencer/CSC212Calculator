package edu.smith.cs.csc212.calc;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

public class ExprParserTest {

	@Test
	public void testAnd() {
		Assert.assertEquals(false, ExprParser.parse("t & f").evaluate());
		Assert.assertEquals(false, ExprParser.parse("f & f").evaluate());
		Assert.assertEquals(false, ExprParser.parse("f & t").evaluate());
		Assert.assertEquals(true, ExprParser.parse("t & t").evaluate());
	}
	@Test
	public void testOr() {
		Assert.assertEquals(true, ExprParser.parse("t | t").evaluate());
		Assert.assertEquals(true, ExprParser.parse("t | f").evaluate());
		Assert.assertEquals(true, ExprParser.parse("f | t").evaluate());
		Assert.assertEquals(false, ExprParser.parse("f | f").evaluate());
	}
	@Test
	public void testMultiple() {
		Assert.assertEquals(true, ExprParser.parse("t | t & t").evaluate());
		Assert.assertEquals(true, ExprParser.parse("t | f & t").evaluate());
		Assert.assertEquals(true, ExprParser.parse("f | t & t").evaluate());
		Assert.assertEquals(false, ExprParser.parse("f | f & t").evaluate());
		Assert.assertEquals(false, ExprParser.parse("f | f & f").evaluate());
		
	}
	@Test
	public void testNot() {
		Assert.assertEquals(false, ExprParser.parse("!t").evaluate());
		Assert.assertEquals(true, ExprParser.parse("!f").evaluate());
		
	}
	@Test
	public void testDemorgan() {
		Assert.assertEquals(ExprParser.parse("!t | !t").evaluate(), ExprParser.parse("!(t & t)").evaluate());
		Assert.assertEquals(ExprParser.parse("!t | !f").evaluate(), ExprParser.parse("!(t & f)").evaluate());
		Assert.assertEquals(ExprParser.parse("!f | !t").evaluate(), ExprParser.parse("!(f & t)").evaluate());
		Assert.assertEquals(ExprParser.parse("!f | !f").evaluate(), ExprParser.parse("!(f & f)").evaluate());
	}
	
	

}
