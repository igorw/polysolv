package misc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

// represents a polynomial function
// ax^n + bx^(n-1) + cx^(n-2) ... zx^0
public class PolyFunction implements Iterable<Entry<Integer, Double>> {
	private Map<Integer, Double> coeffMap = new HashMap<Integer, Double>();
	
	// in case of change from primitive
	// change equals() too
	private int maxGrade = 0;
	
	public Double getCoeff(Integer grade) {
		return hasCoeff(grade) ? coeffMap.get(grade) : 0.0;
	}
	
	public PolyFunction setCoeff(Double coeff, Integer grade) {
		// ignore empty coeffs
		if (coeff.equals(0.0) && !hasCoeff(grade)) {
			return this;
		}
		
		if (coeff.equals(0.0)) {
			// settings a coeff 0.0 means removing it
			coeffMap.remove(grade);
		} else {
			// store
			coeffMap.put(grade, coeff);
		}
		
		// refresh maxGrade
		maxGrade = 0;
		for (Entry<Integer, Double> entry : this) {
			Integer currentGrade = entry.getKey();
			if (currentGrade > maxGrade) {
				maxGrade = currentGrade;
			}
		}
		
		return this;
	}
	
	public boolean hasCoeff(Integer grade) {
		return coeffMap.containsKey(grade);
	}
	
	public int getMaxGrade() {
		return maxGrade;
	}
	
	public Double calculate(double x) {
		Double result = 0.0;
		
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
			// skip non-existent coeffs
			if (!hasCoeff(i)) {
				continue;
			}
			
			// add initial - or +/- before next item
			if (i == maxGrade) {
				if (getCoeff(i) < 0.0) {
					result.append("-");
				}
			} else {
				result.append((getCoeff(i) >= 0.0) ? " + " : " - ");
			}
			
			// x^0 == 1
			if (i == 0) {
				result.append(Math.abs(getCoeff(i)));
				continue;
			}
			
			// x^1 == x
			if (i == 1) {
				result.append(Math.abs(getCoeff(i)) + "x");
				continue;
			}
			
			// x^i
			result.append(Math.abs(getCoeff(i)) + "x^" + i);
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
	
	public Iterator<Entry<Integer, Double>> iterator() {
		return coeffMap.entrySet().iterator();
	}
}
