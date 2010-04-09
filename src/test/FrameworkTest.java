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
	
	// text maxGrade
	@Test public void testPolyFunctionMaxGrade() {
		PolyFunction f1 = new PolyFunction()
			.setKoeff(4, 1.0);
		assertEquals(4, f1.getMaxGrade());
		
		PolyFunction f2 = new PolyFunction()
			.setKoeff(4, 1.0)
			.setKoeff(2, 1.0);
		assertEquals(4, f2.getMaxGrade());
		
		PolyFunction f3 = new PolyFunction()
			.setKoeff(2, 1.0);
		assertEquals(2, f3.getMaxGrade());
		
		// set a koeff to 0
		PolyFunction f4 = new PolyFunction()
			.setKoeff(1, 1.0)
			.setKoeff(2, 1.0)
			.setKoeff(2, 0.0);
		assertEquals(1, f4.getMaxGrade());
	}
}
