package misc;

import java.util.HashMap;

public class PolyFunction {
	private HashMap<Integer, Double> koeffMap = new HashMap<Integer, Double>();
	
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
}
