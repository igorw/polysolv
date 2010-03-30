package test;

import static org.junit.Assert.*;

import misc.Differentiator;
import misc.PolyFunction;

import org.junit.Test;

public class DifferentiatorTest {
	@Test public void testDifferentiate1() {
		PolyFunction f = new PolyFunction().
			setKoeff(1, 5.0).
			setKoeff(2, 10.0).
			setKoeff(3, 15.0);
		
		PolyFunction df = new PolyFunction().
			setKoeff(0, 1 *5.0).
			setKoeff(1, 2 * 10.0).
			setKoeff(2, 3 * 15.0);
		
		assertEquals(df, Differentiator.differentiate(f));
	}

}
