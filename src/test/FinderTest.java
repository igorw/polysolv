package test;

import static org.junit.Assert.*;

import java.util.Vector;

import misc.PolyFunction;

import org.junit.Test;

import finders.FinderInterface;
import finders.InvalidFuncException;
import finders.LinearFinder;
import finders.QuadricFinder;

public class FinderTest {
	@Test public void testLinearFinder() {
		FinderInterface finder = new LinearFinder();
		
		// -2/3x + 5
		// result = 7.5
		PolyFunction f1 = new PolyFunction().
			setKoeff(0, 5.0).
			setKoeff(1, -2.0/3.0);
		try {
			assertEquals((Double) 7.5, finder.find(f1).firstElement());
		} catch (InvalidFuncException e) {
			fail();
		}
	}
	
	@Test public void testQuadricFinder() {
		FinderInterface finder = new QuadricFinder();
		
		// 1/2x^2 - 2x - 6
		// results = -2.0, 6.0
		PolyFunction f1 = new PolyFunction().
			setKoeff(0, -6.0).
			setKoeff(1, -2.0).
			setKoeff(2, 0.5);
		try {
			Vector<Double> results = finder.find(f1);
			assertTrue(results.contains(-2.0));
			assertTrue(results.contains(6.0));
		} catch (InvalidFuncException e) {
			fail();
		}
	}
}
