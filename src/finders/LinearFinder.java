package finders;

import java.util.Vector;

import misc.PolyFunction;

// y = ax + b
// mšgliche nullstellen: genau eine
public class LinearFinder implements FinderInterface {
	public Vector<Double> find(PolyFunction f) {
		// can only solve grade 1
		if (f.getMaxGrade() != 1) {
			throw new InvalidFuncException("LinearFinder only supports grade <= 1");
		}
		
		Vector<Double> results = new Vector<Double>();
		
		double a = f.getCoeff(1);
		double b = f.getCoeff(0);
		
		// -b / a
		double x = -b / a;
		results.add(x);
		
		return results;
	}
}
