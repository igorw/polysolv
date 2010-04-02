package finders;

import java.util.Vector;

import misc.PolyFunction;

// y = ax + b
// mšgliche nullstellen: genau eine
public class LinearFinder implements FinderInterface {
	public Vector<Double> find(PolyFunction f) throws InvalidFuncException {
		// can only solve up to grade 1
		if (f.getMaxGrade() > 1) {
			throw new InvalidFuncException("LinearFinder only supports grade <= 1");
		}
		
		Vector<Double> results = new Vector<Double>();
		
		double a = f.getKoeff(1);
		double b = f.getKoeff(0);
		
		// -b / a
		double x = -b / a;
		results.add(x);
		
		return results;
	}
}
