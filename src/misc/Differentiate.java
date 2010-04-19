package misc;

import java.util.Map.Entry;

public class Differentiate {
	// differentiate polynomial 
	static public PolyFunction differentiate(PolyFunction f) {
		PolyFunction a = new PolyFunction();
		
		for (Entry<Integer, Double> entry : f) {
			if (entry.getKey() == 0) {
				// x^0 => 0
				continue;
			}
			a.setCoeff(entry.getKey() * entry.getValue(), entry.getKey()-1);
		}
		
		return a;
	}
}
