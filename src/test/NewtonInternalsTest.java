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
	
	@Test public void testRound() {
		assertEquals((Double) 3.333, NewtonFinder.round(3.3333333));
		assertEquals((Double) 1.0, NewtonFinder.round(1.0));
		assertEquals((Double) 1.123, NewtonFinder.round(1.12345));
		assertEquals((Double) (-1.123), NewtonFinder.round(-1.12345));
	}
	
	@Test public void testIsEven() {
		assertEquals(true, NewtonFinder.isEven(0));
		assertEquals(true, NewtonFinder.isEven(2));
		assertEquals(true, NewtonFinder.isEven(4));
		assertEquals(true, NewtonFinder.isEven(-2));
		assertEquals(true, NewtonFinder.isEven(-4));
		assertEquals(true, NewtonFinder.isEven(124356328));
		assertEquals(true, NewtonFinder.isEven(-124356328));
		assertEquals(false, NewtonFinder.isEven(1));
		assertEquals(false, NewtonFinder.isEven(3));
		assertEquals(false, NewtonFinder.isEven(5));
		assertEquals(false, NewtonFinder.isEven(-1));
		assertEquals(false, NewtonFinder.isEven(-3));
		assertEquals(false, NewtonFinder.isEven(-5));
		assertEquals(false, NewtonFinder.isEven(124356329));
		assertEquals(false, NewtonFinder.isEven(-124356329));
	}
}
