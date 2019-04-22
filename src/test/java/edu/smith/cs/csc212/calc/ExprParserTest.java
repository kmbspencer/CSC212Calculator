package edu.smith.cs.csc212.calc;

import org.junit.Assert;
import org.junit.Test;

public class ExprParserTest {

	@Test
	public void testAdd() {
		Assert.assertEquals(3, ExprParser.parse("1+2").evaluate());
	}
	@Test
	public void testSub() {
		Assert.assertEquals(-1, ExprParser.parse("1-2").evaluate());
		Assert.assertEquals(1, ExprParser.parse("3-2").evaluate());
	}
	@Test
	public void testMul() {
		Assert.assertEquals(2, ExprParser.parse("1*2").evaluate());
		Assert.assertEquals(6, ExprParser.parse("3*2").evaluate());
	}
	@Test
	public void testDiv() {
		Assert.assertEquals(0, ExprParser.parse("1/2").evaluate());
		Assert.assertEquals(5, ExprParser.parse("10/2").evaluate());
	}
	@Test
	public void testNegation() {
		Assert.assertEquals(-2, ExprParser.parse("-2").evaluate());
		Assert.assertEquals(2, ExprParser.parse("--2").evaluate());
		// Maybe this should be an error...
		Assert.assertEquals(2, ExprParser.parse("--------2").evaluate());
	}
	
	@Test
	public void operatorPrecedence() {
		Assert.assertEquals(7, ExprParser.parse("1+2*3").evaluate());
		Assert.assertEquals(-5, ExprParser.parse("1-2*3").evaluate());
		Assert.assertEquals(1, ExprParser.parse("1+2/3").evaluate());
		Assert.assertEquals(1, ExprParser.parse("1-2/3").evaluate());
	}
	
	@Test
	public void longExpr() {
		Assert.assertEquals(55, ExprParser.parse("1+2+3+4+5+6+7+8+9+10").evaluate());
		Assert.assertEquals(120, ExprParser.parse("1*2*3*4*5").evaluate());
	}
}
