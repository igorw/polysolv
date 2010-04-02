package test;

import static org.junit.Assert.*;

import misc.PolyFunction;

import org.junit.Test;

import finders.InvalidFuncException;
import finders.LinearFinder;

public class FinderTest {
	@Test public void testLinearFinder() {
		LinearFinder finder = new LinearFinder();
		
		// -2/3x + 5
		PolyFunction f1 = new PolyFunction().
			setKoeff(0, 5.0).
			setKoeff(1, -2.0/3.0);
		try {
			assertEquals((Double) 7.5, finder.find(f1).firstElement());
		} catch (InvalidFuncException e) {
			fail();
		}
	}
}
