package factory;

import misc.PolyFunction;
import finders.FinderInterface;
import finders.LinearFinder;
import finders.NewtonFinder;
import finders.QuadraticFinder;

public class FinderFactory extends AbstractFinderFactory {
	@Override
	public FinderInterface getFinder(PolyFunction f) {
		switch (f.getMaxGrade()) {
			case 0:
				throw new FinderNotFoundException("No possible solution for grade 0");
			case 1:
				return new LinearFinder();
			case 2:
				return new QuadraticFinder();
			default:
				return new NewtonFinder();
		}
	}
}
