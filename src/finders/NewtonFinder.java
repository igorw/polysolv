package finders;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Vector;

import misc.Differentiate;
import misc.PolyFunction;

import org.apache.commons.lang.StringUtils;

// y = ax^3 + bx^2 + cx + d
// mögliche nullstellen 1-3
public class NewtonFinder implements FinderInterface {
	
	// precision/depth of recursive newton algorithm
	private int newtonDepth = 1000;
	
	private double a;
	
	private Vector<Double> results = new Vector<Double>();
	
	public Vector<Double> find(PolyFunction f) {
		// can only solve grade 3+
		if (f.getMaxGrade() < 3) {
			throw new InvalidFuncException("NewtonFinder only supports grade >= 3");
		}
		
		// initialize
		results = new Vector<Double>();
		
		a = f.getKoeff(f.getMaxGrade());
		
		// ableiten damit sie quadratisch ist
		// nullstellen x koordinaten der abgeleiteten funktion
		// entsprechen den extrema von f
		PolyFunction fa = Differentiate.differentiate(f);
		Vector<Double> extrema = null;
		
		if (f.getMaxGrade() == 3) {
			// ableiten damit sie quadratisch ist
			// nullstellen x koordinaten der abgeleiteten funktion
			// entsprechen den extrema von f
			extrema = new QuadraticFinder().find(fa);
		} else if (f.getMaxGrade() > 3) {
			// recursion
			extrema = new NewtonFinder().find(fa);
		}
		
		// polynom ungeraden grades
		// ableitung hat keine oder nur eine nullstelle
		// an irgendeinem ort suchen
		// f hat nur eine nullstelle
		if (!isEven(f.getMaxGrade()) && extrema.size() < 2) {
			addResult(newton(f, 1.0, newtonDepth));
			return results;
		}
		
		// extrema aufsteigend sortieren
		Collections.sort(extrema);
		
		Double firstElement = extrema.firstElement();
		if (!isEven(f.getMaxGrade())) {
			// positive 3ten grades
			// erstes extremum über null
			// ODER
			// negative 3ten grades
			// erstes extremum unter null
			//
			// => links suchen
			if (a > 0.0 && f.calculate(firstElement) > 0.0 ||
				a < 0.0 && f.calculate(firstElement) < 0.0) {
					addResult(newton(f, firstElement - 1, newtonDepth));
				}
		} else {
			// positive 4ten grades
			// erstes extremum unter null
			// ODER
			// negative 4ten grades
			// erstes extremum über null
			//
			// => links suchen
			if (a > 0.0 && f.calculate(firstElement) < 0.0 ||
				a < 0.0 && f.calculate(firstElement) > 0.0) {
					addResult(newton(f, firstElement - 1, newtonDepth));
				}
		}
		
		
		// von erster bis vorletzer nullstelle
		// immer mit nächster vergleichen
		for (int i = 0; i < extrema.size() - 1; i++) {
			Double x1 = extrema.get(i);
			Double x2 = extrema.get(i + 1);
			
			// first iteration, check x1
			// nullstelle direkt auf extremum x1
			if (i == 0 && f.calculate(x1) == 0.0) {
				addResult(x1);
				
				// nächstes extremum kann keine nullstelle dazwischen haben
				continue;
			}
			
			// nullstelle direkt auf extremum x2
			if (f.calculate(x2) == 0.0) {
				addResult(x2);
				
				// vorheriges extremum kann keine nullstelle dazwischen haben
				continue;
			}
			
			// vorzeichen wechsel
			// nullstelle suchen
			if (signChange(f.calculate(x1), f.calculate(x2))) {
				addResult(newton(f, (x1 + x2) / 2.0, newtonDepth));
			}
		}
		
		// positive 3ten/4ten grades
		// letztes extremum unter null
		// ODER
		// negative 3ten/4ten grades
		// letztes extremum über null
		//
		// => rechts suchen
		Double lastElement = extrema.lastElement();
		if (a > 0.0 && f.calculate(lastElement) < 0.0 ||
			a < 0.0 && f.calculate(lastElement) > 0.0) {
			addResult(newton(f, lastElement + 1, newtonDepth));
		}
		
		return results;
	}
	
	// recursive newton
	// Xn+1 = Xn - (f(Xn) / f'(Xn))
	// depth > 0
	// iterative implementation
	public Double newton(PolyFunction f, Double startValue, Integer depth) {
		PolyFunction fa = Differentiate.differentiate(f);
		
		// initialize the new value
		Double newValue = startValue;
		
		// calculate new value iteratively
		do {
			newValue = newValue - (f.calculate(newValue) / fa.calculate(newValue));
		} while (--depth > 0);
		
		return newValue;
	}
	
	// clean a result and add it to results
	public void addResult(Double result) {
		// prevent -0.0 issues
		if (result == -0.0) {
			result = 0.0;
		}
		
		// round
		result = round(result);
		
		results.add(result);
	}
	
	// auf 3 stellen runden
	static public Double round(Double value) {
		return round(value, 3);
	}
	
	// auf n stellen runden
	static public Double round(Double value, Integer precision) {
		DecimalFormat df = new DecimalFormat("#." + StringUtils.repeat("#", precision));
		return Double.valueOf(df.format(value));
	}
	
	// helper function to check if sign changed
	static public boolean signChange(Double y1, Double y2) {
		return (y1 < 0 && y2 > 0 ||
				y1 > 0 && y2 < 0);
	}
	
	// helper function to check if value is even or odd
	static public boolean isEven(Integer value) {
		return value % 2 == 0;
	}
}
