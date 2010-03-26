package finders;

import java.util.Vector;

import misc.Differentiator;
import misc.PolyFunction;

// y = ax^3 + bx^2 + cx + d
public class NewtonFinderGrade3 implements FinderInterface {
	public Vector<Double> find(PolyFunction f) throws InvalidFuncException {
		Vector<Double> nullstellen = new Vector<Double>();
		
		/*double a = f.getKoeff(3);
		double b = f.getKoeff(2);
		double c = f.getKoeff(1);
		double d = f.getKoeff(0);*/
		
		// ableiten damit sie quadratisch ist
		PolyFunction fa = Differentiator.differentiate(f);
		
		System.out.println(fa);
		
		// nullstellen x koordinaten der abgeleiteten funktion
		Vector<Double> fax = new QuadricFinder().find(fa);
		double x1 = fax.get(0);
		double x2 = fax.get(1);
		
		// aufsteigend sortieren
		if (x1 > x2) {
			double tmp = x1;
			x1 = x2;
			x2 = tmp;
		}
		
		// still one missing in between
		System.out.println(newton(f, x1 - 0.00001, 10));
		System.out.println(newton(f, x2 + 0.00001, 10));
		
		return nullstellen;
	}
	
	// recursive newton
	// prevValue = startValue
	// Xn+1 = Xn - (f(Xn) / f'(Xn))
	public Double newton(PolyFunction f, double prevValue, int depth) {
		PolyFunction fa = Differentiator.differentiate(f);
		double x = prevValue - (f.calculate(prevValue) / fa.calculate(prevValue));
		if (depth > 0) {
			return newton(f, x, --depth);
		} else {
			return x;
		}
	}
}
