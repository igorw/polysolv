package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FrameworkTest.class,
	DifferentiatorTest.class,
	FinderTest.class,
	NewtonInternalsTest.class,
})
public class AllTests {
	// run all tests
}
