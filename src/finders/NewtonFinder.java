package finders;

import java.util.Collections;
import java.util.Vector;

import misc.Differentiator;
import misc.PolyFunction;

// y = ax^3 + bx^2 + cx + d
// mšgliche nullstellen 1-3
public class NewtonFinder implements FinderInterface {
	// constants
	public static final int SEARCH_UNKNOWN = 0;
	public static final int SEARCH_LEFT = 1;
	public static final int SEARCH_RIGHT = 2;
	
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
				int searchWhere = SEARCH_UNKNOWN;
				
				// von positivem polynom 3ten grades ausgehen
				// von unten links nach oben rechts
				if (f.calculate(x1) > 0.0) {
					// extremum Ÿber x-achse
					// => links suchen
					searchWhere = SEARCH_LEFT;
				} else /*if (f.calculate(x1) < 0.0)*/ {
					// extremum unter x-achse
					// => rechts suchen
					searchWhere = SEARCH_RIGHT;
				}
				
				if (a < 0.0) {
					// negatives polynom 3ten grades
					// von oben links nach unten rechts
					// searchWhere muss invertiert werden
					searchWhere = (searchWhere == SEARCH_LEFT) ? SEARCH_RIGHT : SEARCH_LEFT;
				}
				
				// do the searching
				// search either left or right
				results.add(newton(f, x1 + (searchWhere == SEARCH_LEFT ? -1 : 1), newtonDepth));
			}
		} else {
			// positives polynom 3ten grades
			// von unten links nach oben rechts
			if (a > 0.0) {
				Double prev = null;
				for (Double x : fax) {
					// iterate over all extrema
					
					if (f.calculate(x) == 0.0) {
						// extremum = nullstelle
						results.add(x);
						
						prev = x;
						continue;
					}
					
					if (prev == null) {
						// first iteration
						if (f.calculate(x) > 0.0) {
							// Ÿber x-achse
							// links suchen
							results.add(newton(f, x - 1, newtonDepth));
						}
						
						prev = x;
						continue;
					}
					
					// calculate coordinates of prev and current
					double y1 = f.calculate(prev);
					double y2 = f.calculate(x);
					
					// vorzeichen vergleichen
					// falls es Šndert ist nullstelle dazwischen
					// mit mittelwert suchen
					if (y1 < 0 && y2 > 0 || y1 > 0 && y2 < 0) {
						results.add(newton(f, (prev + x) / 2, newtonDepth));
					}
					
					prev = x;
				}
				// after iteration
				if (f.calculate(prev) < 0.0) {
					// unter x-achse
					// rechts suchen
					results.add(newton(f, prev + 1, newtonDepth));
				}
			}
			System.out.println(results);
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
