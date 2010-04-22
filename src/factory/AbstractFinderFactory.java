package factory;

import misc.PolyFunction;
import finders.FinderInterface;

public abstract class AbstractFinderFactory {
	abstract public FinderInterface getFinder(PolyFunction f);
}
