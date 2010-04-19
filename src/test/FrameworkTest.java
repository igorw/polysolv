package test;

import static org.junit.Assert.*;

import misc.PolyFunction;

import org.junit.Test;

import finders.InvalidFuncException;
import finders.LinearFinder;
import finders.NewtonFinder;
import finders.QuadraticFinder;

public class FrameworkTest {
	// empty coeffs should be 0
	@Test public void testPolyFunctionEmptyCoeff() {
		PolyFunction f = new PolyFunction().
			setCoeff(7.0,	2).
			setCoeff(5.0,	0);
		
		assertEquals((Double) 0.0, f.getCoeff(1));
	}
	
	// do not accept bad PolyFunctions
	@Test(expected = InvalidFuncException.class) public void testLinearFinderInvalidFunc() {
		PolyFunction f1 = new PolyFunction();
		new LinearFinder().find(f1);
	}
	@Test(expected = InvalidFuncException.class) public void testQuadraticFinderInvalidFunc() {
		PolyFunction f1 = new PolyFunction();
		new QuadraticFinder().find(f1);
	}
	@Test(expected = InvalidFuncException.class) public void testNewtonFinderInvalidFunc() {
		PolyFunction f1 = new PolyFunction();
		new NewtonFinder().find(f1);
	}
	
	// text maxGrade
	@Test public void testPolyFunctionMaxGrade() {
		PolyFunction f1 = new PolyFunction()
			.setCoeff(1.0, 4);
		assertEquals(4, f1.getMaxGrade());
		
		PolyFunction f2 = new PolyFunction()
			.setCoeff(1.0, 4)
			.setCoeff(1.0, 2);
		assertEquals(4, f2.getMaxGrade());
		
		PolyFunction f3 = new PolyFunction()
			.setCoeff(1.0, 2);
		assertEquals(2, f3.getMaxGrade());
		
		// set a coeff to 0
		PolyFunction f4 = new PolyFunction()
			.setCoeff(1.0, 1)
			.setCoeff(1.0, 2)
			.setCoeff(0.0, 2);
		assertEquals(1, f4.getMaxGrade());
	}
}
