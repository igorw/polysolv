package finders;

import java.util.Vector;

import misc.PolyFunction;

// y = ax^2 + bx + c
// mšgliche nullstellen: 0-2
public class QuadraticFinder implements FinderInterface {
	public Vector<Double> find(PolyFunction f) throws InvalidFuncException {
		// can only solve grade 2
		if (f.getMaxGrade() != 2) {
			throw new InvalidFuncException("QuadraticFinder only supports grade 2");
		}
		
		Vector<Double> results = new Vector<Double>();
		
		double a = f.getKoeff(2);
		double b = f.getKoeff(1);
		double c = f.getKoeff(0);
		
		// discriminant determines amount of solutions
		// D = b^2 - 4ac
		double discriminant = Math.pow(b, 2) - 4*a*c;
		
		if (discriminant > 0.0) {
			// two solutions
			
			// (-b +- sqrt(b^2-4ac)) / 2a
			double x1 = (double) ((-b + Math.sqrt(Math.pow(b, 2)-4*a*c)) / (2*a));
			results.add(x1);
			double x2 = (double) ((-b - Math.sqrt(Math.pow(b, 2)-4*a*c)) / (2*a));
			results.add(x2);
			
		} else if (discriminant == 0.0) {
			// one solution
			
			// (-b + sqrt(b^2-4ac)) / 2a
			double x1 = (double) ((-b + Math.sqrt(Math.pow(b, 2)-4*a*c)) / (2*a));
			results.add(x1);
			
		} else /* if (discriminant < 0.0) */ {
			// no solutions
		}
		
		return results;
	}
}
