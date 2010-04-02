package test;

import static org.junit.Assert.*;

import misc.Apex;
import misc.Point;
import misc.PolyFunction;

import org.junit.Test;

import finders.InvalidFuncException;

public class ApexTest {
	@Test public void testApex1() {
		// probeprüfung 3a
		// 0.5x^2 - 2x - 6
		// expected: 2.0 | -8.0
		PolyFunction f = new PolyFunction().
			setKoeff(2, 0.5).
			setKoeff(1, -2.0).
			setKoeff(0, -6.0);
		
		try {
			assertEquals(new Point(2.0, -8.0), Apex.apex(f));
			assertEquals((Double) (-8.0), f.calculate(2.0));
		} catch (InvalidFuncException e) {
			fail();
		}
	}

}
