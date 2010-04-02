package misc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

// represents a polynomial function
public class PolyFunction implements Iterable<Entry<Integer, Double>>, Cloneable {
	private Map<Integer, Double> koeffMap = new HashMap<Integer, Double>();
	
	// in case of change from primitive
	// change equals() too
	private int maxGrade = 0;
	
	public Double getKoeff(Integer grade) {
		return hasKoeff(grade) ? koeffMap.get(grade) : 0.0;
	}
	
	public PolyFunction setKoeff(Integer grade, Double koeff) {
		// empty koeffs are useless
		if (koeff.equals(0.0)) {
			return this;
		}
		
		koeffMap.put(grade, koeff);
		if (grade > maxGrade) {
			maxGrade = grade;
		}
		
		return this;
	}
	
	public boolean hasKoeff(Integer grade) {
		return koeffMap.containsKey(grade);
	}
	
	public int getMaxGrade() {
		return maxGrade;
	}
	
	public double calculate(double x) {
		double result = 0;
		
		for (Entry<Integer, Double> entry : this) {
			result += entry.getValue() * Math.pow(x, entry.getKey());
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		result.append("f(x) = ");
		
		for (int i = maxGrade; i >= 0; i--) {
			// skip non-existent koeffs
			if (!hasKoeff(i)) {
				continue;
			}
			
			// add initial - or +/- before next item
			if (i == maxGrade) {
				if (getKoeff(i) < 0.0) {
					result.append("-");
				}
			} else {
				result.append((getKoeff(i) >= 0.0) ? " + " : " - ");
			}
			
			// x^0 == 1
			if (i == 0) {
				result.append(Math.abs(getKoeff(i)));
				continue;
			}
			
			// x^1 == x
			if (i == 1) {
				result.append(Math.abs(getKoeff(i)) + "x");
				continue;
			}
			
			// x^i
			result.append(Math.abs(getKoeff(i)) + "x^" + i);
		}
		
		return result.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		return hashCode() == o.hashCode();
	}
	
	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        
        for (Entry<Integer, Double> entry : this) {
        	result = prime * result + entry.getKey().hashCode();
        	result = prime * result + entry.getValue().hashCode();
        }
        
        return result;
	}
	
	@Override
	public PolyFunction clone() throws CloneNotSupportedException {
		PolyFunction f = (PolyFunction) super.clone();
		return f;
	}
	
	public Iterator<Entry<Integer, Double>> iterator() {
		return koeffMap.entrySet().iterator();
	}
}
