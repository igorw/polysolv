package test;

import static org.junit.Assert.*;

import misc.Differentiator;
import misc.PolyFunction;

import org.junit.Test;

public class DifferentiatorTest {
	@Test public void testDifferentiate1() {
		// 15x^3 + 10x^2 + 5x
		PolyFunction f = new PolyFunction().
			setKoeff(3, 15.0).
			setKoeff(2, 10.0).
			setKoeff(1, 5.0);
		
		// 3*15^2 + 2*10x + 5
		PolyFunction df = new PolyFunction().
			setKoeff(2, 3 * 15.0).
			setKoeff(1, 2 * 10.0).
			setKoeff(0, 1 * 5.0);
			
		
		assertEquals(df, Differentiator.differentiate(f));
	}

}
