package test;

import static org.junit.Assert.*;

import misc.PolyFunction;

import org.junit.Test;

public class FrameworkTest {
	// make sure PolyFunction is Cloneable
	@Test public void testPolyFunctionClone() {
		PolyFunction f = new PolyFunction().
			setKoeff(1, 1.0).
			setKoeff(0, 5.0);
		
		try {
			PolyFunction f2 = f.clone();
			
			assertEquals(f, f2);
		} catch (CloneNotSupportedException e) {
			fail();
		}
	}
	
	// empty koeffs should be 0
	@Test public void testPolyFunctionEmptyKoeff() {
		PolyFunction f = new PolyFunction().
			setKoeff(2, 7.0).
			setKoeff(0, 5.0);
		
		assertEquals((Double) 0.0, f.getKoeff(1));
	}
}
