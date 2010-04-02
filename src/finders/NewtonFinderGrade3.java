package finders;

import java.util.Collections;
import java.util.Vector;

import misc.Differentiator;
import misc.PolyFunction;

// y = ax^3 + bx^2 + cx + d
// m�gliche nullstellen 1-3
public class NewtonFinderGrade3 implements FinderInterface {
	// precision/depth of recursive newton algorithm
	private int newtonDepth = 10;
	
	public Vector<Double> find(PolyFunction f) throws InvalidFuncException {
		Vector<Double> results = new Vector<Double>();
		
		double a = f.getKoeff(3);
		double b = f.getKoeff(2);
		double c = f.getKoeff(1);
		double d = f.getKoeff(0);
		
		// ableiten damit sie quadratisch ist
		PolyFunction fa = Differentiator.differentiate(f);
		
		// nullstellen x koordinaten der abgeleiteten funktion
		// entsprechen den extrema von f
		Vector<Double> fax = new QuadricFinder().find(fa);
		
		// extrema aufsteigend sortieren
		Collections.sort(fax);
		
		if (fax.size() == 1) {
			// nur ein extremum
			// => sattelpunkt
			// => nur eine nullstelle
			
			double x1 = fax.firstElement();
			
			if (f.calculate(x1) == 0.0) {
				// extremum = nullstelle
				results.add(x1);
			} else {
				// vorzeichen beachten
				if (a > 0.0) {
					// positives polynom 3ten grades
					// von unten links nach oben rechts
					
					if (f.calculate(x1) > 0.0) {
						// extremum �ber x-achse
						// => links suchen
						results.add(newton(f, x1 - 1, newtonDepth));
						
					} else /*if (f.calculate(x1) < 0.0)*/ {
						// extremum unter x-achse
						// => rechts suchen
						results.add(newton(f, x1 + 1, newtonDepth));
						
					}
				} else if (a < 0.0) {
					// negatives polynom 3ten grades
					// von oben links nach unten rechts
				}
			}
		} else {
			// mehr als ein extremum
		}
		
		return results;
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
