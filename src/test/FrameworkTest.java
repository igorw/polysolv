package test;

import static org.junit.Assert.*;

import misc.PolyFunction;

import org.junit.Test;

public class FrameworkTest {
	@Test public void testPolyFunctionClone() {
		PolyFunction f = new PolyFunction().
			setKoeff(0, 5.0).
			setKoeff(1, 1.0);
		
		try {
			PolyFunction f2 = f.clone();
			
			assertEquals(f, f2);
		} catch (CloneNotSupportedException e) {
			fail();
		}
	}
}
