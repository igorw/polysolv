package test;

import static org.junit.Assert.*;

import java.util.Vector;

import misc.PolyFunction;

import org.junit.Test;

import finders.FinderInterface;
import finders.InvalidFuncException;
import finders.LinearFinder;
import finders.NewtonFinder;
import finders.QuadricFinder;

public class FinderTest {
	@Test public void testLinearFinder() {
		FinderInterface finder = new LinearFinder();
		
		// probeprüfung 2a
		// -2/3x + 5
		// result = 7.5
		PolyFunction f1 = new PolyFunction().
			setKoeff(1, -2.0/3.0).
			setKoeff(0, 5.0);
		try {
			assertEquals((Double) 7.5, finder.find(f1).firstElement());
		} catch (InvalidFuncException e) {
			fail();
		}
	}
	
	@Test public void testQuadricFinder() {
		FinderInterface finder = new QuadricFinder();
		
		// probeprüfung 2b
		// 1/2x^2 - 2x - 6
		// results = -2, 6
		PolyFunction f1 = new PolyFunction().
			setKoeff(2, 0.5).
			setKoeff(1, -2.0).
			setKoeff(0, -6.0);
		try {
			Vector<Double> results = finder.find(f1);
			assertTrue(results.contains(-2.0));
			assertTrue(results.contains(6.0));
		} catch (InvalidFuncException e) {
			fail();
		}
	}
	
	@Test public void testNewtonFinder1() {
		FinderInterface finder = new NewtonFinder();
		
		// probeprüfung 2e
		// x^3 - 3x - 2
		// results = -1, 2
		PolyFunction f1 = new PolyFunction().
			setKoeff(3, 1.0).
			setKoeff(1, -3.0).
			setKoeff(0, -2.0);
		try {
			Vector<Double> results = finder.find(f1);
			assertTrue(results.contains(-1.0));
			assertTrue(results.contains(2.0));
		} catch (InvalidFuncException e) {
			fail();
		}
	}
	
	@Test public void testNewtonFinder2() {
		FinderInterface finder = new NewtonFinder();
		
		// x^3
		// result = 0
		PolyFunction f1 = new PolyFunction().
			setKoeff(3, 1.0);
		try {
			Vector<Double> results = finder.find(f1);
			assertEquals(1, results.size());
			assertEquals((Double) 0.0, results.firstElement());
		} catch (InvalidFuncException e) {
			fail();
		}
	}
}
