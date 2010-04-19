package test;

import static org.junit.Assert.*;

import misc.Differentiate;
import misc.PolyFunction;

import org.junit.Test;

public class DifferentiatorTest {
	@Test public void testDifferentiate1() {
		// 15x^3 + 10x^2 + 5x
		PolyFunction f = new PolyFunction().
			setCoeff(15.0,	3).
			setCoeff(10.0,	2).
			setCoeff(5.0,	1);
		
		// 3*15^2 + 2*10x + 5
		PolyFunction df = new PolyFunction().
			setCoeff(3 * 15.0,	2).
			setCoeff(2 * 10.0,	1).
			setCoeff(1 * 5.0,	0);
			
		
		assertEquals(df, Differentiate.differentiate(f));
	}

}
