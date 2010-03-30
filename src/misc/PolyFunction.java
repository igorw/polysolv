package misc;

import java.util.HashMap;

public class PolyFunction {
	private HashMap<Integer, Double> koeffMap = new HashMap<Integer, Double>();
	
	// in case of change from primitive
	// change equals() too
	private int maxGrade = 0;
	
	public Double getKoeff(Integer grade) {
		return koeffMap.get(grade);
	}
	
	public void setKoeff(Integer grade, Double koeff) {
		koeffMap.put(grade, koeff);
		if (grade > maxGrade) {
			maxGrade = grade;
		}
	}
	
	public boolean hasKoeff(Integer grade) {
		return koeffMap.containsKey(grade);
	}
	
	public int getMaxGrade() {
		return maxGrade;
	}
	
	public double calculate(double x) {
		double result = 0;
		
		for (int i = maxGrade; i >= 0; i--) {
			if (!hasKoeff(i)) {
				continue;
			}
			result += getKoeff(i) * Math.pow(x, i);
		}
		
		return result;
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		result.append("f(x) = ");
		
		for (int i = maxGrade; i >= 0; i--) {
			if (i != maxGrade) {
				result.append(" + ");
			}
			if (i == 0) {
				result.append(getKoeff(i));
				continue;
			}
			if (i == 1) {
				result.append(getKoeff(i) + "x");
				continue;
			}
			result.append(getKoeff(i) + "x^" + i);
		}
		
		return result.toString();
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof PolyFunction)) {
			return false;
		}
		
		PolyFunction f = (PolyFunction) o;
		
		if (f.getMaxGrade() != getMaxGrade()) {
			return false;
		}
		
		for (int i = 0; i <= maxGrade; i++) {
			if (!f.hasKoeff(i)) {
				return false;
			}
			
			if (!f.getKoeff(i).equals(getKoeff(i))) {
				System.out.println(f.getKoeff(i).equals(getKoeff(i)));
				return false;
			}
		}
		
		return true;
	}
}
