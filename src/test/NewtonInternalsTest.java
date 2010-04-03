package test;

import static org.junit.Assert.*;

import org.junit.Test;

import finders.NewtonFinder;

// test some of NewtonFinder's internal methods
public class NewtonInternalsTest {
	@Test public void testSignChange() {
		assertTrue(NewtonFinder.signChange(-1.0, 1.0));
		assertTrue(NewtonFinder.signChange(1.0, -1.0));
		assertTrue(NewtonFinder.signChange(-5.0, 5.0));
		assertFalse(NewtonFinder.signChange(0.0, -0.0));
		assertFalse(NewtonFinder.signChange(0.0, 1.0));
		assertFalse(NewtonFinder.signChange(6.0, 1.0));
		assertFalse(NewtonFinder.signChange(-6.0, -1.0));
	}
	
	/*@Test public void testFindRangeLeft() {
		NewtonFinder newton = new NewtonFinder();
		
		result = newton.findRangeLeft(x, f)
	}*/
}
