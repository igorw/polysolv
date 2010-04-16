package test;

import static org.junit.Assert.*;

import java.util.Vector;

import misc.PolyFunction;

import org.junit.Test;

import finders.FinderInterface;
import finders.InvalidFuncException;
import finders.LinearFinder;
import finders.NewtonFinder;
import finders.QuadraticFinder;

public class FinderTest {
	@Test public void testLinearFinder() {
		FinderInterface finder = new LinearFinder();
		
		// probeprüfung 2a
		// -2/3x + 5
		// result = 7.5
		PolyFunction f1 = new PolyFunction().
			setKoeff(1, -2.0/3.0).
			setKoeff(0, 5.0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(1, results.size());
		assertEquals((Double) 7.5, results.firstElement());
	}
	
	@Test public void testQuadraticFinder1() {
		FinderInterface finder = new QuadraticFinder();
		
		// probeprüfung 2b
		// 1/2x^2 - 2x - 6
		// results = -2, 6
		PolyFunction f1 = new PolyFunction().
			setKoeff(2, 0.5).
			setKoeff(1, -2.0).
			setKoeff(0, -6.0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(2, results.size());
		assertTrue(results.contains(-2.0));
		assertTrue(results.contains(6.0));
	}
	
	@Test public void testQuadraticFinder2() {
		FinderInterface finder = new QuadraticFinder();
		
		// prüfung 1a
		// -2x^2 + 4x + 6
		// results = -1, 3
		PolyFunction f1 = new PolyFunction().
			setKoeff(2, -2.0).
			setKoeff(1, 4.0).
			setKoeff(0, 6.0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(2, results.size());
		assertTrue(results.contains(-1.0));
		assertTrue(results.contains(3.0));
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
		
		Vector<Double> results = finder.find(f1);
		assertEquals(2, results.size());
		assertTrue(results.contains(-1.0));
		assertTrue(results.contains(2.0));
	}
	
	@Test public void testNewtonFinder2() {
		FinderInterface finder = new NewtonFinder();
		
		// x^3
		// result = 0
		PolyFunction f1 = new PolyFunction().
			setKoeff(3, 1.0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(1, results.size());
		assertEquals((Double) 0.0, results.firstElement());
	}
	
	@Test public void testNewtonFinder3() {
		FinderInterface finder = new NewtonFinder();
		
		// ableitung hat keine nullstelle
		// -x^3 - 3x
		// result = 0
		PolyFunction f1 = new PolyFunction().
			setKoeff(3, -1.0).
			setKoeff(1, -3.0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(1, results.size());
		assertEquals((Double) 0.0, results.firstElement());
	}
	
	@Test public void testNewtonFinder4() {
		FinderInterface finder = new NewtonFinder();
		
		// -x^3
		// result = 0
		PolyFunction f1 = new PolyFunction().
			setKoeff(3, -1.0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(1, results.size());
		assertEquals((Double) 0.0, results.firstElement());
	}
	
	@Test public void testNewtonFinder5() {
		FinderInterface finder = new NewtonFinder();
		
		// probeprüfung 2d
		// -1/3x^4 + 4/3x^3
		// results = 0, 4
		PolyFunction f1 = new PolyFunction().
			setKoeff(4, -1.0/3.0).
			setKoeff(3, 4.0/3.0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(2, results.size());
		assertTrue(results.contains(-1.0));
		assertTrue(results.contains(2.0));
	}
	
	@Test public void testNewtonFinder6() {
		FinderInterface finder = new NewtonFinder();
		
		// probeprüfung 7
		// 2/3x^3 - 1/2x^2 - 36x + 6
		// results = -7.0690, 0.1664, 7.6527
		PolyFunction f1 = new PolyFunction().
			setKoeff(3, 2.0/3.0).
			setKoeff(2, -0.5).
			setKoeff(1, -36.0).
			setKoeff(0, 6.0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(3, results.size());
		assertTrue(results.contains(-7.069));
		assertTrue(results.contains(0.166));
		assertTrue(results.contains(7.653));
	}
	
	@Test public void testNewtonFinder7() {
		FinderInterface finder = new NewtonFinder();
		
		// probeprüfung 2f (first part)
		// -2.0x^3 + 6x
		// results = -1.7320, 0, 1.7320
		PolyFunction f1 = new PolyFunction().
			setKoeff(3, -2.0).
			setKoeff(1, 6.0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(3, results.size());
		assertTrue(results.contains(-1.732));
		assertTrue(results.contains(0.0));
		assertTrue(results.contains(1.732));
	}
	
	@Test public void testNewtonFinder8() {
		FinderInterface finder = new NewtonFinder();
		
		// tobias special case
		// 1.5x^4 - x^3 - x^2 + 0.1
		// results = 0.2930, 1.1880
		PolyFunction f1 = new PolyFunction().
			setKoeff(4, 1.5).
			setKoeff(3, -1.0).
			setKoeff(2, -1.0).
			setKoeff(0, 0.1);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(2, results.size());
		assertTrue(results.contains(0.293));
		assertTrue(results.contains(1.188));
	}
	
	@Test public void testNewtonFinder9() {
		FinderInterface finder = new NewtonFinder();
		
		// prüfung 1b
		// 0.5x^4 - x^3
		// results = 0, 2
		PolyFunction f1 = new PolyFunction().
			setKoeff(4, 0.5).
			setKoeff(3, -1.0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(2, results.size());
		assertTrue(results.contains(0.0));
		assertTrue(results.contains(2.0));
	}
	
	@Test public void testNewtonFinder10() {
		FinderInterface finder = new NewtonFinder();
		
		// prüfung 1c
		// -2x^3 + 3x^2
		// results = 0, 1.5
		PolyFunction f1 = new PolyFunction().
			setKoeff(3, -2.0).
			setKoeff(2, 3.0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(2, results.size());
		assertTrue(results.contains(0.0));
		assertTrue(results.contains(1.5));
	}
	
	@Test public void testNewtonFinder11() {
		FinderInterface finder = new NewtonFinder();
		
		// prüfung 6
		// 1/3x^3 + x^2 - 24x + 14
		// results = -10.3492, 0.6014, 6.7477
		PolyFunction f1 = new PolyFunction().
			setKoeff(3, 1.0/3.0).
			setKoeff(2, 1.0).
			setKoeff(1, -24.0).
			setKoeff(0, 14.0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(3, results.size());
		assertTrue(results.contains(-10.349));
		assertTrue(results.contains(0.601));
		assertTrue(results.contains(6.748));
	}
}
