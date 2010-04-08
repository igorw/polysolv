package test;

import static org.junit.Assert.*;

import misc.PolyFunction;

import org.junit.Test;

import finders.InvalidFuncException;
import finders.LinearFinder;
import finders.NewtonFinder;
import finders.QuadraticFinder;

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
	
	// do not accept bad PolyFunctions 
	@Test public void testFinderInvalidFunc() {
		PolyFunction f1 = new PolyFunction();
		try {
			new LinearFinder().find(f1);
			fail();
		} catch (InvalidFuncException e) {
			// success
		}
		try {
			new QuadraticFinder().find(f1);
			fail();
		} catch (InvalidFuncException e) {
			// success
		}
		try {
			new NewtonFinder().find(f1);
			fail();
		} catch (InvalidFuncException e) {
			// success
		}
	}
}
