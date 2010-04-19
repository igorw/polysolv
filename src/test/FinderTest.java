package test;

import static org.junit.Assert.*;

import java.util.Vector;

import misc.PolyFunction;

import org.junit.Test;

import finders.FinderInterface;
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
			setCoeff(-2.0/3.0,	1).
			setCoeff(5.0, 		0);
		
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
			setCoeff(0.5,	2).
			setCoeff(-2.0,	1).
			setCoeff(-6.0,	0);
		
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
			setCoeff(-2.0,	2).
			setCoeff(4.0,	1).
			setCoeff(6.0,	0);
		
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
			setCoeff(1.0,	3).
			setCoeff(-3.0,	1).
			setCoeff(-2.0,	0);
		
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
			setCoeff(1.0, 3);
		
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
			setCoeff(-1.0,	3).
			setCoeff(-3.0,	1);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(1, results.size());
		assertEquals((Double) 0.0, results.firstElement());
	}
	
	@Test public void testNewtonFinder4() {
		FinderInterface finder = new NewtonFinder();
		
		// -x^3
		// result = 0
		PolyFunction f1 = new PolyFunction().
			setCoeff(-1.0, 3);
		
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
			setCoeff(-1.0/3.0,	4).
			setCoeff(4.0/3.0,	3);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(2, results.size());
		assertTrue(results.contains(0.0));
		assertTrue(results.contains(4.0));
	}
	
	@Test public void testNewtonFinder6() {
		FinderInterface finder = new NewtonFinder();
		
		// probeprüfung 7
		// 2/3x^3 - 1/2x^2 - 36x + 6
		// results = -7.0690, 0.1664, 7.6527
		PolyFunction f1 = new PolyFunction().
			setCoeff(2.0/3.0,	3).
			setCoeff(-0.5,		2).
			setCoeff(-36.0,		1).
			setCoeff(6.0,		0);
		
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
			setCoeff(-2.0,	3).
			setCoeff(6.0,	1);
		
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
			setCoeff(1.5,	4).
			setCoeff(-1.0,	3).
			setCoeff(-1.0,	2).
			setCoeff(0.1,	0);
		
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
			setCoeff(0.5,	4).
			setCoeff(-1.0,	3);
		
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
			setCoeff(-2.0,	3).
			setCoeff(3.0,	2);
		
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
			setCoeff(1.0/3.0,	3).
			setCoeff(1.0,		2).
			setCoeff(-24.0,		1).
			setCoeff(14.0,		0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(3, results.size());
		assertTrue(results.contains(-10.349));
		assertTrue(results.contains(0.601));
		assertTrue(results.contains(6.748));
	}
	
	@Test public void testNewtonFinder12() {
		FinderInterface finder = new NewtonFinder();
		
		// -7/6x^7 + 1/3x^4 + 4/3x^3
		// results = -0.96499, 0, 1.09862
		PolyFunction f1 = new PolyFunction().
			setCoeff(-7.0/6.0,	7).
			setCoeff(1.0/3.0,	4).
			setCoeff(4.0/3.0,	3);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(3, results.size());
		assertTrue(results.contains(-0.965));
		assertTrue(results.contains(0.0));
		assertTrue(results.contains(1.099));
	}
	
	@Test public void testNewtonFinder13() {
		FinderInterface finder = new NewtonFinder();
		
		// teil von testNewtonFinder12
		// ableitung hat nur eine nullstelle
		// -245x^4 + 8x + 8
		// results = -0.377575, 0.467902
		PolyFunction f1 = new PolyFunction().
			setCoeff(-245.0,	4).
			setCoeff(8.0,		1).
			setCoeff(8.0,		0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(2, results.size());
		assertTrue(results.contains(-0.378));
		assertTrue(results.contains(0.468));
	}
	
	@Test public void testNewtonFinder14() {
		FinderInterface finder = new NewtonFinder();
		
		// keine nullstelle
		// 53x^4 + 9x^3 + 38x^2 + 4x + 20
		// results = none
		PolyFunction f1 = new PolyFunction().
			setCoeff(53.0,	4).
			setCoeff(9.0,	3).
			setCoeff(38.0,	2).
			setCoeff(4.0,	1).
			setCoeff(20.0,	0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(0, results.size());
	}
	
	@Test public void testNewtonFinder15() {
		FinderInterface finder = new NewtonFinder();
		
		// -6x^12 + 4x^9 - 9x^6 + 2
		// results = -0.745162, 0.78614
		PolyFunction f1 = new PolyFunction().
			setCoeff(-6.0,	12).
			setCoeff(4.0,	9).
			setCoeff(-9.0,	6).
			setCoeff(2.0,	0);
		
		Vector<Double> results = finder.find(f1);
		assertEquals(2, results.size());
		assertTrue(results.contains(-0.745));
		assertTrue(results.contains(0.786));
	}
}
